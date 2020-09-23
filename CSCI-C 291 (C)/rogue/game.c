/* game.c ---------
 *
 * Filename: game.c
 * Description:
 * Author: Dmitrii Galantsev
 * Maintainer:
 * Created:      May 12 11:28 2018
 * Last-Updated: June 4 11:00 2019
 *           By: Matthew Fulford
 */

/* Commentary:
 *
 *
 */

/* Copyright (c) 2018 The Trustees of Indiana University and
 * Indiana University Research and Technology Corporation.
 *
 * All rights reserved.
 *
 * Additional copyrights may follow
 */

#include <unistd.h>
#include <ncurses.h>
#include <stdlib.h>
#include <time.h>
#include "tile.h"
#include "game.h"
#include "room.h"
#include "key.h"
#include "score.h"
#include "save.h"

//MARK: Function Prototypes
void quitGame(void);
void help(int, int, int, int);
void drawPause(WINDOW*, int);

struct Score* playerScore; //the players score
/*
 * floor is in this shape:
 *    1  2
 *    3  4
 *    5  6
 *    7  8
 *    9  10
 *    11 12
 * Movement would be as expected where if you are in room 5, there will be a door above, below, and to the right
 */

//function the fill the array with tiles to make a floor. Also makes doors random but in map spots
void MakeFloor(tile* tiles[100][150], int width, int height, int screen_x_offset, int screen_y_offset, int numberRooms){
  int i, j;
  int currentRoom;
  for(i = 0; i < 100; i++){
    for(j = 0; j < 150; j++){
      tiles[i][j] = create_tile(i, j, screen_x_offset + i%width, screen_y_offset + j%height, 0, 0, 0);
    }
  }

  /***Room 1***/
  if(numberRooms == 2) { //only have right side
    tiles[width - 1][rand() % (height - 3) + 2]->door = 1; //right side
  } else if (numberRooms >= 3) { //have both right and bottom
    tiles[width - 1][rand() % (height - 3) + 2]->door = 1; //right side
    tiles[rand() % (width-3) + 2][height - 1]->door = 1; //bottom
  } //draw no doors if just 1

  /***Room 2***/
  if(numberRooms == 2 || numberRooms == 3) { //only have left side
    tiles[width][rand() % (height - 3) + 2]->door = 1; //left side
  } else { //we can move down as well
    tiles[width][rand() % (height - 3) + 2]->door = 1; //left side
    tiles[rand() % (width-3) + width + 2][height - 1]->door = 1; //bottom
  } 

  /***Room 3***/
  if(numberRooms == 3) { //just have up
    tiles[rand() % (width - 3) + 2][height]->door = 1; //top
  } else if (numberRooms == 4) { //we can go up or right
    tiles[rand() % (width - 3) + 2][height]->door = 1; //top
    tiles[width - 1][rand() % (height - 3) + height + 2]->door = 1; //right side
  } else { //we can go all directions
    tiles[rand() % (width - 3) + 2][height]->door = 1; //top
    tiles[width - 1][rand() % (height - 3) + height + 2]->door = 1; //right side
    tiles[rand() % (width - 3) + 2][height * 2 - 1]->door = 1; //bottom
  }
  
  /***Room 4***/
  if(numberRooms == 4 || numberRooms == 5) { //we can go up or left
    tiles[rand() % (width - 3) + width + 2][height]->door = 1; //top
    tiles[width][rand() % (height - 3) + height + 2]->door = 1; //left side  
  } else { //we can go everywhere
    tiles[rand() % (width-3) + width + 2][height*2 - 1]->door = 1; //bottom
    tiles[rand() % (width - 3) + width + 2][height]->door = 1; //top
    tiles[width][rand() % (height - 3) + height + 2]->door = 1; //left side
  }

  /***Room 5***/
  if(numberRooms == 5) { //can only go up
    tiles[rand() % (width - 3) + 2][height*2]->door = 1; //top 
  } else if(numberRooms == 6) { //can go up or right
    tiles[rand() % (width - 3) + 2][height*2]->door = 1; //top 
    tiles[width - 1][rand() % (height - 3) + height*2 + 2]->door = 1; //right side 
  } else { //can go in all directions
    tiles[rand() % (width - 3) + 2][height*2]->door = 1; //top
    tiles[rand() % (width - 3) + 2][height * 3 - 1]->door = 1; //bottom
    tiles[width - 1][rand() % (height - 3) + height*2 + 2]->door = 1; //right side
  }

  /***Room 6***/
  if(numberRooms == 6 || numberRooms == 7) { //we can go up or to the left
    tiles[rand() % (width - 3) + width + 2][height*2]->door = 1; //top
    tiles[width][rand() % (height - 3) + height*2 + 2]->door = 1; //left side 
  } else { //we can go all directions
    tiles[rand() % (width-3) + width + 2][height*3 - 1]->door = 1; //bottom
    tiles[rand() % (width - 3) + width + 2][height*2]->door = 1; //top
    tiles[width][rand() % (height - 3) + height*2 + 2]->door = 1; //left side
  }
  
  /***Room 7***/
  if(numberRooms == 7) { //we can only go up
    tiles[rand() % (width - 3) + 2][height*3]->door = 1; //top
  } else if(numberRooms == 8) { //we can go up or right
    tiles[rand() % (width - 3) + 2][height*3]->door = 1; //top
    tiles[width - 1][rand() % (height - 3) + height*3 + 2]->door = 1; //right side
  } else {
    tiles[rand() % (width - 3) + 2][height*3]->door = 1; //top
    tiles[rand() % (width - 3) + 2][height * 4 - 1]->door = 1; //bottom
    tiles[width - 1][rand() % (height - 3) + height*3 + 2]->door = 1; //right side  
  }

  /***Room 8***/
  if(numberRooms == 8 || numberRooms == 9) { //we can go up or left
    tiles[rand() % (width - 3) + width + 2][height*3]->door = 1; //top
    tiles[width][rand() % (height - 3) + height*3 + 2]->door = 1; //left side
  } else { //we can go all directions
    tiles[rand() % (width-3) + width + 2][height*4 - 1]->door = 1; //bottom
    tiles[rand() % (width - 3) + width + 2][height*3]->door = 1; //top
    tiles[width][rand() % (height - 3) + height*3 + 2]->door = 1; //left side
  }

  /***Room 9***/
  if(numberRooms == 9) { //we can go up
    tiles[rand() % (width - 3) + 2][height*4]->door = 1; //top  
  } else if(numberRooms == 10) { //we can go up or right
    tiles[rand() % (width - 3) + 2][height*4]->door = 1; //top  
    tiles[width - 1][rand() % (height - 3) + height*4 + 2]->door = 1; //right side 
  } else { //we can go all directions
    tiles[rand() % (width - 3) + 2][height*4]->door = 1; //top
    tiles[rand() % (width - 3) + 2][height * 5 - 1]->door = 1; //bottom
    tiles[width - 1][rand() % (height - 3) + height*4 + 2]->door = 1; //right side  
  }

  /***Room 10***/
  if(numberRooms == 10 || numberRooms == 11) { //we can go up or left
    tiles[rand() % (width - 3) + width + 2][height*4]->door = 1; //top 
    tiles[width][rand() % (height - 3) + height*4 + 2]->door = 1; //left side 
  } else { //we can go everywhere
    tiles[rand() % (width-3) + width + 2][height*5 - 1]->door = 1; //bottom
    tiles[rand() % (width - 3) + width + 2][height*4]->door = 1; //top
    tiles[width][rand() % (height - 3) + height*4 + 2]->door = 1; //left side
  }

  /***Room 11***/
  if(numberRooms == 11) {
    tiles[rand() % (width - 3) + 2][height*5]->door = 1; //top 
  } else {
    tiles[rand() % (width - 3) + 2][height*5]->door = 1; //top 
    tiles[width - 1][rand() % (height - 3) + height*5 + 2]->door = 1; //right side  
  }

  /***Room 12***/
  tiles[rand() % (width - 3) + width + 2][height*5]->door = 1; //top
  tiles[width][rand() % (height - 3) + height*5 + 2]->door = 1; //left side

  //make stairs
  int x_rand;
  int y_rand;
  if(numberRooms % 2) //is even
    x_rand = rand() % (width * 2);
  else //odd
    x_rand = rand() % width;

  //time for y value
  if(numberRooms == 1) {
    y_rand = rand() % height;
  } else if(numberRooms == 2) {
    y_rand = rand() % height;
  } else if(numberRooms == 3) {
    y_rand = rand() % (height * 2);
  } else if(numberRooms == 4) {
    y_rand = rand() % (height * 2);
  } else if(numberRooms == 5) {
    y_rand = rand() % (height * 3);
  } else if(numberRooms == 6) {
    y_rand = rand() % (height * 3);
  } else if(numberRooms == 7) {
    y_rand = rand() % (height * 4);
  } else if(numberRooms == 8) {
    y_rand = rand() % (height * 4);
  } else if(numberRooms == 9) {
    y_rand = rand() % (height * 5);
  } else if(numberRooms == 10) {
    y_rand = rand() % (height * 5);
  } else if(numberRooms == 11) {
    y_rand = rand() % (height * 6);
  } else { //all 12 rooms
    x_rand = rand() % 100;
    y_rand = rand() % 150;
  }


  tiles[x_rand][y_rand]->stair = 1;

  //player
  tiles[width/2][height/2]->state[CURRENT] = PLAYER;
}

//Initialize game
void init_game(void)
{
  int x,y;
}

//game is running
int game(void)
{
  srand(time(0));  //Seed random numbers
  
  static int state = INIT;
  struct timespec tim = {0,1000000};  // Each execution of while(1) is approximately 1mS
  struct timespec tim_ret;
  
  int width = 50; //room width
  int height = 25; //room height
  
  tile* tiles[100][150];  //tiles that represent the floor
  tile* player;           //pointer to keep track of the player
  
  room_t* r; //room
  
  int x_offset = 0, y_offset = 0; //offsets for room
  int screen_x_offset, screen_y_offset; //to center screen
  int x_max, y_max;
  int arrow; //arrow key
  int move_counter = 0; //player move counter
  int move_timeout = BASE_TIMEOUT / 10; //timeouts
  
  int detection; //to hold value returned by move_player
  int* currentFloor = (int*)malloc(sizeof(int)); //out of 6
  int currentRoom = 1;  //out of 12  
  int saveIndex = 1; //initial save index
  int numberOfRooms = rand() % 12 + 1; //random number of rooms changes each floor 1-12

  //Repeat until we die
  while(1) {
    switch(state) {
    case INIT:                   // Initialize the game, only run one time
      
      
      initscr();
      nodelay(stdscr, TRUE);   // Do not wait for characters using getch.
      noecho();                // Do not echo input characters
      getmaxyx(stdscr, y_max, x_max);  // Get the screen dimensions
      
      curs_set(0);
      
      screen_x_offset = (x_max / 2) - ((width) / 2);
      screen_y_offset = (y_max / 2) - ((height) / 2);
      
      //makes starting floor
      MakeFloor(tiles, width, height, screen_x_offset, screen_y_offset, numberOfRooms);
      
      //player position
      tiles[width/2][height/2]->state[CURRENT] = PLAYER;
      player = tiles[width/2][height/2];
      
      //border
      r = init_room(screen_x_offset - 1, screen_y_offset - 1, width+1, height+1);
      draw_room(r);
      
      state = STEP;
      
      //init scoreing and lives
      playerScore = (struct Score*)malloc(sizeof(struct Score));
      playerScore->currentGold = (int*)malloc(sizeof(int));
      playerScore->currentLives = (int*)malloc(sizeof(int));
      playerScore->totalGold = (int*)malloc(sizeof(int));
      playerScore->totalLives = (int*)malloc(sizeof(int));

      *(playerScore->currentGold) = 100; //starting health
      *(playerScore->currentLives) = 1; //starting lives
      *(playerScore->totalGold) = 100;
      *(playerScore->totalLives) = 1;
      *currentFloor = 1;
      
      break;
      
      //Deals with movement of the player, enemies, and updating game states
    case STEP:
      
      //handle player movement, quitting, or pausing
      arrow = read_escape(&arrow);
      switch(arrow) {
      case UP: //move up
        detection = move_player(&player, player->x,(player->y) - 1, width, height, tiles);
        break;
        
      case DOWN: //move down
        detection = move_player(&player, player->x,(player->y) + 1, width, height, tiles);
        break;
        
      case LEFT: //move left
        detection = move_player(&player, (player->x) - 1, player->y, width, height, tiles);
        break;
        
      case RIGHT: //move right
        detection = move_player(&player, (player->x) + 1, player->y, width, height, tiles);
        break;
        
      case PAUSE: //pause the game
	;WINDOW* win = newwin(height, width, screen_y_offset, screen_x_offset);
	refresh(); //refresh to create window

	box(win, 0, 0); //draw box border

	drawPause(win, 1);

	cbreak();
	keypad(win, TRUE);
	//curs_set(1); //so we can see the cursor

	wmove(win, 1, 1);
	wrefresh(win);

	int input; //what key was pressed
	int pos = 1; //current selection
        while(1) {
	  input = wgetch(win);
	  if(input == 'p' || input == 'P') //unpause
	    break;

	  if(input == KEY_UP && pos != 1) { //up
	    wmove(win, --pos, 1);
	        
	  } else if(input == KEY_DOWN && pos != 5) { //down
	    wmove(win, ++pos, 1);

	  } else if(input == 10) { //enter key

	    if(pos == 1) { //unpause
	      break;

	    } else if(pos == 2) { //save the game to a .game file
	      if(saveGame(saveIndex++, playerScore, currentFloor, numberOfRooms,  x_offset, y_offset, player, tiles) == 0)
		state = EXIT;
	      if(saveIndex == 11)
		saveIndex = 1;
	      mvwprintw(win, height/2, 1, "Saved file to: ./saves/save_%d.game", saveIndex - 1);

	    } else if(pos == 3) { //load a file
	      endwin();
	      endwin();
	      while(1) {
		int input;
		printf("\nPlease enter the save number: ");
		scanf(" %d", &input);
		if(loadGame(input, playerScore, currentFloor, &numberOfRooms, &x_offset, &y_offset, player, tiles) == 0) {
		  mvwprintw(win, height/2, 1, "No save file found for index %d.        ", input);
		} else {
		  mvwprintw(win, height/2, 1, "Loaded save file for index %d.        ", input);
		}
		break;
	      }

	    } else if(pos == 4) { //display help menu
	      help(height, width, screen_y_offset, screen_x_offset);
	      mvwprintw(win, 1, 1, "1: Resume\t\t\t\t"); //option 1
	      mvwprintw(win, 2, 1, "2: Save the game to a .game file\t\t\t\t"); //option 2
	      mvwprintw(win, 3, 1, "3: Load a save file\t\t\t\t"); //option 3
	      mvwprintw(win, 4, 1, "4: Help Menu\t\t\t\t"); //option 4
	      mvwprintw(win, 5, 1, "5: Exit the Game\t\t\t"); //option 5

	    } else if(pos == 5) { //exit game
	      endwin();endwin();quitGame(); return 1; break;
	    }
	  }
	  drawPause(win, pos);
	  wrefresh(win);
	}
	endwin();
	curs_set(0);
        break;

      case SAVE: //save the game
	if(saveGame(saveIndex++, playerScore, currentFloor, numberOfRooms, x_offset, y_offset, player, tiles) == 0)
	  state = EXIT;
	if(saveIndex == 11)
	  saveIndex = 1;
	break;

      case LOAD: //load the game from a file
	endwin();
	while(1) {
	  int input;
	  printf("\nPlease enter the save number: ");
	  scanf(" %d", &input);
	  if(loadGame(input, playerScore, currentFloor, &numberOfRooms, &x_offset, &y_offset, player,  tiles) == 0) {
	    mvprintw(10, 0, "No save file found for index %d.", input);
	  } else {
	    mvprintw(10, 0, "                                ");
	  }
	  break;
	}

	break;
        
      case QUIT: //quit the game
        state = EXIT;
        break;
        
      default:
        break;
      }
      
      //for handling detection of NON-MONSTER tiles, and will handle stairs and doors as well
      if(detection == 1) { //ran into gold!!! So add 2
        *(playerScore->currentGold) += 2;
        *(playerScore->totalGold) += 2;
        if(*(playerScore->currentGold) % 100 == 0) { //add a life if we got 100
          *(playerScore->currentLives) += 1;
          *(playerScore->totalLives) += 1;
        }
      } else if(detection == 3) { //ran into a stair
	if(*(currentFloor) != 6) { //limit to 6 floors
	  *(currentFloor) += 1; //lets make a new floor
	  currentRoom = 1;
	  x_offset = 0;
	  y_offset = 0;
	  numberOfRooms = rand() % 12 + 1;
	  MakeFloor(tiles, width, height, screen_x_offset, screen_y_offset, numberOfRooms);
	  tiles[width/2][height/2]->state[CURRENT] = PLAYER;
	  player = tiles[width/2][height/2];
	  r = init_room(screen_x_offset - 1, screen_y_offset - 1, width+1, height+1);
	  draw_room(r);
	} else { //you won cause ya reached the last floor and found the staircase out
	  state = EXIT;
	  printf("YOU WON!!!\n");
	}
	

      } else if(detection == 4) { //ran into a door
	//in room 1 we can move to room 2 or 3
	if(currentRoom == 1) {
	  if(player->x >= width - 2) {  //move to room 2 (right)
	    x_offset = width; y_offset = 0;
	    currentRoom = 2;
	  } else {               //move to room 3 (down)
	    x_offset = 0; y_offset = height;
	    currentRoom = 3;
	  }

	  //in room 2 we can move to room 1 or 4
	} else if(currentRoom == 2) {
	  if(player->x <= width + 1) { //move to room 1 (left)
	    x_offset = 0; y_offset = 0;
	    currentRoom = 1;
	  } else { //move to room 4 (down)
	    x_offset = width; y_offset = height;
	    currentRoom = 4;
	  }

	  //in room 3 we can move to room 1, 4, 5
	} else if(currentRoom == 3) {
	  if(player->y <= height + 1) { //move to room 1 (up)
	    x_offset = 0; y_offset = 0;
	    currentRoom = 1;
	  } else if(player->x >= width - 2) { //move to room 4 (right)
	    x_offset = width; y_offset = height;
	    currentRoom = 4;
	  } else { //move to room 5 (down)
	    x_offset = 0; y_offset = height * 2;
	    currentRoom = 5;
	  }

	  //in room 4 we can move to room 2, 3, 6 
	} else if(currentRoom == 4) {
	  if(player->y <= height + 1) { //move to room 2 (up)
	    x_offset = width; y_offset = 0;
	    currentRoom = 2;
	  } else if(player->x <= width + 1) { //move to room 3 (left)
	    x_offset = 0; y_offset = height;
	    currentRoom = 3;
	  } else { //move to room 6 (down)
	    x_offset = width; y_offset = height*2;
	    currentRoom = 6;
	  }

	  //in room 5 we can move to room 3, 6, 7
	} else if(currentRoom == 5) {
	  if(player->y <= height*2 + 1) { //move to room 3 (up)
	    x_offset = 0; y_offset = height;
	    currentRoom = 3;
	  } else if(player->x >= width - 2) { //move to room 6 (right)
	    x_offset = width; y_offset = height*2;
	    currentRoom = 6;
	  } else { //move to room 7 (down)
	    x_offset = 0; y_offset = height * 3;
	    currentRoom = 7;
	  }
	    
	  //in room 6 we can move to room 4, 5, 8
	} else if(currentRoom == 6) {
	  if(player->y <= height*2 + 1) { //move to room 4 (up)
	    x_offset = width; y_offset = height;
	    currentRoom = 4;
	  } else if(player->x <= width + 1) { //move to room 5 (left)
	    x_offset = 0; y_offset = height * 2;
	    currentRoom = 5;
	  } else { //move to room 8 (down)
	    x_offset = width; y_offset = height*3;
	    currentRoom = 8;
	  }

	  //in room 7 we can move to room 5, 8, 9
	} else if (currentRoom == 7) {
	  if(player->y <= height*3 + 1) { //move to room 5 (up)
	    x_offset = 0; y_offset = height * 2;
	    currentRoom = 5;
	  } else if(player->x >= width - 2) { //move to room 8 (right)
	    x_offset = width; y_offset = height*3;
	    currentRoom = 8;
	  } else { //move to room 9 (down)
	    x_offset = 0; y_offset = height * 4;
	    currentRoom = 9;
	  }

	  //in room 8 we can move to room 6, 7, 10
	} else if(currentRoom == 8) {
	  if(player->y <= height*3 + 1) { //move to room 6 (up)
	    x_offset = width; y_offset = height*2;
	    currentRoom = 6;
	  } else if(player->x <= width + 1) { //move to room 7 (left)
	    x_offset = 0; y_offset = height * 3;
	    currentRoom = 7;
	  } else { //move to room 10 (down)
	    x_offset = width; y_offset = height*4;
	    currentRoom = 10;
	  }

	  //in room 9 we can move to room 7, 10, 11
	} else if(currentRoom == 9) {
	  if(player->y <= height*4 + 1) { //move to room 7 (up)
	    x_offset = 0; y_offset = height * 3;
	    currentRoom = 7;
	  } else if(player->x >= width - 2) { //move to room 10 (right)
	    x_offset = width; y_offset = height*4;
	    currentRoom = 10;
	  } else { //move to room 11 (down)
	    x_offset = 0; y_offset = height * 5;
	    currentRoom = 11;
	  }

	  //in room 10 we can move to room 8, 9, 12
	} else if (currentRoom == 10) {
	  if(player->y <= height*4 + 1) { //move to room 8 (up)
	    x_offset = width; y_offset = height*3;
	    currentRoom = 8;
	  } else if(player->x <= width + 1) { //move to room 9 (left)
	    x_offset = 0; y_offset = height * 4;
	    currentRoom = 9;
	  } else { //move to room 12 (down)
	    x_offset = width; y_offset = height*5;
	    currentRoom = 12;
	  }

	  //in room 11 we can move to room 9, 12
	} else if(currentRoom == 11) {
	  if(player->y <= height*5 + 1) { //move to room 9 (up)
	    x_offset = 0; y_offset = height * 4;
	    currentRoom = 9;
	  } else { //move to room 12 (right)
	    x_offset = width; y_offset = height * 5;
	    currentRoom = 12;
	  }

	  //in room 12 we can move to room 10, 11
	} else if(currentRoom == 12) {
	  if(player->y <= height*5 + 1) { //move to room 10 (up) {
	    x_offset = width; y_offset = height*4;
	    currentRoom = 10;
	  } else { //move to room 11 (left)
	    x_offset = 0; y_offset = height * 5;
	    currentRoom = 11;
	  }
	}

	draw_room(r);
        update_tiles(x_offset, y_offset, width, height, tiles);
        display_tiles(x_offset, y_offset, width, height, tiles);
	teleport_player(&player, x_offset + (width/2), y_offset + (height / 2), tiles); //move him to the center of the new room he entered so there is no possibility of dieing immediatly from monsters
      }

      //print the room we are in as well as what floor we are on
      mvprintw(4, 0, "Room: %d of %d  ", currentRoom, numberOfRooms);
      mvprintw(5, 0, "Floor: %d of 6  ", *currentFloor);

      //handles running into a monster
      if((detection = check_hit(player, x_offset, y_offset, width, height, tiles))) {
	*(playerScore->currentGold) -= detection;
	if(*(playerScore->currentLives) >= 1 && *(playerScore->currentGold) <= 0) { //keep playing
	  *(playerScore->currentLives) -= 1;
	  *(playerScore->currentGold) = 100; 
	} else if(*(playerScore->currentLives) == 0 && *(playerScore->currentGold) <= 0) {
	  state = EXIT;
	}
      }

      //reset to empty space
      detection = 2;
      
      //hit our timeout, move and update
      if (move_counter > move_timeout) {
        //move enemies
        move_enemies(player, x_offset, y_offset, width, height, tiles);
        
        //update room
        draw_room(r);
        update_tiles(x_offset, y_offset, width, height, tiles);
        display_tiles(x_offset, y_offset, width, height, tiles);
        
        while(getch() != ERR);  //prevents lag from having getch and sleep in a loop
        
        move_counter = 0;
      }
      
      //update score and life label
      char* scoreLabel = "Score:";
      char* lifeLabel = "Lives:";
      mvprintw(0, 0, "%6s", scoreLabel);
      mvprintw(0, 7, "%d  ", *(playerScore->currentGold));
      mvprintw(1, 0, "%6s", lifeLabel);
      mvprintw(1, 7, "%d  ", *(playerScore->currentLives));
      //update counter
      move_counter++;
      break;
      
      //exit the game
    case EXIT:
      endwin();
      quitGame();
      return(0);
      break;
    }
    refresh();
    nanosleep(&tim,&tim_ret);
  }
}

//We quit the game so here are your stats
void quitGame(void) {
  printf("\nGAME OVER");
  printf("\nThank you for playing ROGUE. Below is your stats:\n");
  printf("\tTotal gold collected throughout game: %d\n", *(playerScore->totalGold));
  printf("\tTotal lives held at one point: %d\n\n", *(playerScore->totalLives));
}

//display the help screen
void help(int height, int width, int y_off, int x_off) {
  ;WINDOW* helpWin = newwin(height, width, y_off, x_off);
  refresh();
  box(helpWin, 0, 0); //draw box border
  
  //key bindings
  mvwprintw(helpWin, 1, 1, "%-10s %5s", "Arrow Keys", "Move the player");
  mvwprintw(helpWin, 2, 1, "%-10s %5s", "P/p", "Pause the game");
  mvwprintw(helpWin, 3, 1, "%-10s %5s", "S/s", "Save the game to file");
  mvwprintw(helpWin, 4, 1, "%-10s %5s", "L/l", "Load the game from file");
  mvwprintw(helpWin, 5, 1, "%-10s %5s", "Q/q", "Quit the game");

  
  cbreak();
  keypad(helpWin, TRUE);
  wmove(helpWin, 0, 0);
  wrefresh(helpWin);

  while(wgetch(helpWin) == ERR);
  endwin();
}


//draw pause and handle color
void drawPause(WINDOW* win, int pos) {
  use_default_colors();
  start_color();
  init_pair(7, COLOR_CYAN, COLOR_RED); //selected
  init_pair(8, COLOR_GREEN, -1); //not selected

  switch(pos) {
  case 1:
    wattron(win, COLOR_PAIR(7));
    mvwprintw(win, 1, 1, "1: Resume"); //option 1
    wattroff(win, COLOR_PAIR(7));

    wattron(win, COLOR_PAIR(8));
    mvwprintw(win, 2, 1, "2: Save the game to a .game file"); //option 2
    mvwprintw(win, 3, 1, "3: Load a save file"); //option 3
    mvwprintw(win, 4, 1, "4: Help Menu"); //option 4
    mvwprintw(win, 5, 1, "5: Exit the Game"); //option 5
    wattroff(win, COLOR_PAIR(8));
    break;

  case 2:

    wattron(win, COLOR_PAIR(7));
    mvwprintw(win, 2, 1, "2: Save the game to a .game file"); //option 2
    wattroff(win, COLOR_PAIR(7));

    wattron(win, COLOR_PAIR(8));
    mvwprintw(win, 1, 1, "1: Resume"); //option 1
    mvwprintw(win, 3, 1, "3: Load a save file"); //option 3
    mvwprintw(win, 4, 1, "4: Help Menu"); //option 4
    mvwprintw(win, 5, 1, "5: Exit the Game"); //option 5
    wattroff(win, COLOR_PAIR(8));
    break;

  case 3:
    wattron(win, COLOR_PAIR(7));
    mvwprintw(win, 3, 1, "3: Load a save file"); //option 3
    wattroff(win, COLOR_PAIR(7));

    wattron(win, COLOR_PAIR(8));
    mvwprintw(win, 1, 1, "1: Resume"); //option 1
    mvwprintw(win, 2, 1, "2: Save the game to a .game file"); //option 2
    mvwprintw(win, 4, 1, "4: Help Menu"); //option 4
    mvwprintw(win, 5, 1, "5: Exit the Game"); //option 5
    wattroff(win, COLOR_PAIR(8));
    break;

  case 4:
    wattron(win, COLOR_PAIR(7));
    mvwprintw(win, 4, 1, "4: Help Menu"); //option 4
    wattroff(win, COLOR_PAIR(7));

    wattron(win, COLOR_PAIR(8));
    mvwprintw(win, 1, 1, "1: Resume"); //option 1
    mvwprintw(win, 2, 1, "2: Save the game to a .game file"); //option 2
    mvwprintw(win, 3, 1, "3: Load a save file"); //option 3
    mvwprintw(win, 5, 1, "5: Exit the Game"); //option 5
    wattroff(win, COLOR_PAIR(8));
    break;

  case 5:
    wattron(win, COLOR_PAIR(7));
    mvwprintw(win, 5, 1, "5: Exit the Game"); //option 5
    wattroff(win, COLOR_PAIR(7));

    wattron(win, COLOR_PAIR(8));
    mvwprintw(win, 1, 1, "1: Resume"); //option 1
    mvwprintw(win, 2, 1, "2: Save the game to a .game file"); //option 2
    mvwprintw(win, 3, 1, "3: Load a save file"); //option 3
    mvwprintw(win, 4, 1, "4: Help Menu"); //option 4
    wattroff(win, COLOR_PAIR(8));
    break;
  }
}
