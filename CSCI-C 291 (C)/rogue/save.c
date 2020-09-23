//Will Boland
//12/15/2019
#include <stdio.h>
#include <dirent.h>
#include "save.h"
#include <string.h>

/*
 * NOTE: This is one working version of save.
 * I also implemented a version where the save files from stage 2 work. I did it this way so grader can see clear representation of the board.
 * In this case, Board is saved: currentState followed by symbol for that state (to save enemy types) followed by if it is a door.
 */

FILE* sfPtr; //save file pointer
char fileName[30] = "./saves/save_N.game"; //path to files and name of file

//returns 0 if file failed to save, currentIndex otherwise
int saveGame(int index, struct Score* currentScore, int* currentFloor, int numberRooms, int x_offset, int y_offset, tile* player, tile* t[100][150]) {
  //check if we need to make a directory
  DIR* directory = opendir("saves");
  if(directory == NULL)
    mkdir("saves");
  closedir(directory);
  system("chmod 700 saves/");

  sprintf(fileName, "./saves/save_%d.game", index);
    
  if((sfPtr = fopen(fileName, "w")) == NULL) { //failed to open
    return 0; 
  } else { //we can open/create
    //saves player stats
    fprintf(sfPtr, "Gold: %d, Lives: %d, TotalGold: %d, TotalLives: %d\n", *(currentScore->currentGold), *(currentScore->currentLives), *(currentScore->totalGold),*(currentScore->totalLives));
    fprintf(sfPtr, "CurrentFloor: %d NumberRooms: %d\n", *currentFloor, numberRooms);
    fprintf(sfPtr, "Player: (%d, %d) x_off: %d y_off: %d\nBoard: ", player->x, player->y, x_offset, y_offset);
    //saves tile states
    int i = 0, j = 0;
    for(i = 0; i < 100; i++) {
      for(j = 0; j < 150; j++) {
	//state, symbol, door, stair
	fprintf(sfPtr, "%d%c%d,%d \t", t[i][j]->state[CURRENT], t[i][j]->symbol, t[i][j]->door, t[i][j]->stair);
      }
    }
    
    fclose(sfPtr);
    return index;
  }
}


//allows loading of a file. See .h for more info
int loadGame(int index, struct Score* currentScore, int* currentFloor, int *numberRooms, int* x_offset, int* y_offset, tile* player, tile* t[100][150]) {
  //check saves directory
  DIR* directory = opendir("saves");
  if(directory == NULL)
    mkdir("saves");
  closedir(directory);
  system("chmod 700 saves/");
  
  //load save file
  sprintf(fileName, "./saves/save_%d.game", index);
  sfPtr = fopen(fileName, "r"); //file pointer
  
  if(sfPtr == NULL) { //no save file located, return 0
    return 0;
  } else { //it exists!!!
    int currentGold, currentLives, totalGold, totalLives, floor, x_off, y_off, x, y, number;
    tile* oT[100][150];

    //read the values
    fscanf(sfPtr, "Gold: %d, Lives: %d, TotalGold: %d, TotalLives: %d\n", &currentGold, &currentLives, &totalGold, &totalLives);
    fscanf(sfPtr, "CurrentFloor: %d NumberRooms: %d\n", &floor, &number);
    fscanf(sfPtr, "Player: (%d, %d) x_off: %d y_off: %d\nBoard: ", &x, &y, &x_off, &y_off);

    int i, j, state, symbol, door, stair;
    for(i = 0; i < 100; i++) {
      for(j = 0; j < 150; j++) {
	fscanf(sfPtr, "%d%c%d,%d \t", &state, &symbol, &door, &stair);
	t[i][j]->state[CURRENT] = state;
	t[i][j]->symbol = symbol;
	t[i][j]->door = door;
	t[i][j]->stair = stair;
      }
    }
    

    //set the values
    *(currentScore->currentGold) = currentGold;
    *(currentScore->currentLives) = currentLives;
    *(currentScore->totalGold) = totalGold;
    *(currentScore->totalLives) = totalLives;
    *currentFloor = floor;
    player->x = x;
    player->y = y;
    *x_offset = x_off;
    *y_offset = y_off;
    *numberRooms = number;
  }
  
  fclose(sfPtr);
  return 1;
}
