/* tile.c ---------
 *
 * Filename: cell.c
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
 *
 */

/* Copyright (c) 2018 The Trustees of Indiana University and
 * Indiana University Research and Technology Corporation.
 *
 * All rights reserved.
 *
 * Additional copyrights may follow
 */

/* Code: */

#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <math.h>
#include <ncurses.h>
#include "tile.h"

int move_enemy_of_type(char symbol, tile* player, int x_off, int y_off, int x, int y, tile* t[100][150]);

/*
 * Returns a pointed to a tile object
 * - Parameter init_x: the initial x value of the tile
 * - Parameter init_y: the initial y value of the tile
 * - Parameter d_x:    the drawing x cordinate for the tile
 * - Parameter d_y:    the drawing y cordinate for the tile
 * - Parameter isPlayer: if the tile should be the player
 * - Parameter isLoot:   if the tile should be loot
 * - Parameter isEmpty:  if the tile should be empty
 */
tile* create_tile(int init_x, int init_y, int d_x, int d_y, int isPlayer, int isLoot, int isEmpty)
{
  
  //create the tile in memory
  tile* t = malloc(sizeof(tile));
  t->x = init_x; //set initial x
  t->y = init_y; //set initial y
  
  t->draw_x = d_x;
  t->draw_y = d_y;
  
  t->door = 0;
  t->stair = 0;
    
  //set tile status to player, loot, enemy, or empty
  if(isPlayer){
    t->state[CURRENT] = PLAYER;
    t->symbol = '@';
  }else if(isLoot){
    t->state[CURRENT] = LOOT;
    t->symbol = '$';
  }else if(isEmpty){
    t->state[CURRENT] = EMPTY;
    t->symbol = ' ';
  }else if(rand()%100 == 1){
    t->state[CURRENT] = LOOT;
    t->symbol = '$';
  }else if(rand()%500 == 1) {
    t->state[CURRENT] = ENEMY;
    if(rand()%5 == 0) { //50 point enemy
      t->symbol = 'A';
    } else if(rand()%4 == 0) { //30 point enemy
      t->symbol = 'B';
    } else if(rand()%3 == 0) { //25 point enemy
      t->symbol = 'C';
    } else { //default enemy (15 point)
      t->symbol = 'E';
    }
  }else{
    t->state[CURRENT] = EMPTY;
    t->symbol = ' ';
  }

  if(t->door)
    t->symbol = 'D';
  if(t->stair)
    t->symbol = 'H';
  t->state[NEW] = UNCHANGED;
  
  return(t);
}

/*
 * Destroys a tile and returns that it was successful
 */
int destroy_tile (tile* t)
{
  free(t);
  return SUCCESS;
}

/*
 * Updates the tile to a new state and returns that it succeeded
 */
int update_tile (tile* t){
  if(t->state[NEW] != UNCHANGED){
    t->state[OLD] = t->state[CURRENT];
    t->state[CURRENT] = t->state[NEW];
    t->state[NEW] = UNCHANGED;
  }
  
  return SUCCESS;
}

/*
 * updates the tiles for the entire game given an x offset and y offset
 */
void update_tiles(int x_off, int y_off, int x, int y, tile* t[100][150]){
  int i, j;
  for(i=0;i<x;i++)
  for(j=0;j<y;j++)
  update_tile(t[i+x_off][j+y_off]);
}

/*
 * Displays the tile for the corresponding current state it is in
 */
void display_tile (tile* t) {
  //colors
  use_default_colors();
  start_color();
  init_pair(1, COLOR_CYAN, -1); //player
  init_pair(2, COLOR_YELLOW, -1);//loot
  init_pair(3, COLOR_RED, -1);  //enemy
  init_pair(5, COLOR_MAGENTA, -1); //stairs

  if (t->state[CURRENT] == PLAYER){
    t->symbol = '@';
   attron(COLOR_PAIR(1));
   mvprintw(t->draw_y, t->draw_x, "%c", t->symbol); //player
   attroff(COLOR_PAIR(1));
  }else if(t->state[CURRENT] == LOOT){
    t->symbol = '$';
   attron(COLOR_PAIR(2));
   mvprintw(t->draw_y, t->draw_x, "%c", t->symbol); //loot
   attroff(COLOR_PAIR(2));
  }else if(t->state[CURRENT] == ENEMY){

   attron(COLOR_PAIR(3));
   mvprintw(t->draw_y, t->draw_x, "%c", t->symbol); //enemy
   attroff(COLOR_PAIR(3));
  }else if(t->door){
    t->symbol = 'D';
   mvprintw(t->draw_y, t->draw_x, "%c", t->symbol); //door
  }else if(t->stair){
    t->symbol = 'H';
   attron(COLOR_PAIR(5));
   mvprintw(t->draw_y, t->draw_x, "%c", t->symbol); //stairs
   attroff(COLOR_PAIR(5));
  }else{
    t->symbol = ' ';
   mvprintw(t->draw_y, t->draw_x, "%c", t->symbol); //empty space
  }
}

/*
 * Displays all the tiles on the board
 */
void display_tiles (int x_off, int y_off, int width, int height, tile* t[100][150]) {
  int x, y;
  for (x = 0; x < width; x++)
  for (y = 0; y < height; y++)
  display_tile(t[x+x_off][y+y_off]);
}

/*
 * Moves all the enemies on the board towards the player
 */
int move_enemies(tile* player, int x_off, int y_off, int x, int y, tile* t[100][150]){
  static count;
  int detection;

  //speed of enemies  
  if(count % 5 == 0){
    detection = move_enemy_of_type('E', player, x_off, y_off, x, y, t);
  }
  
  if(count % 7 == 0)
    detection = move_enemy_of_type('C', player, x_off, y_off, x, y, t);
  
  if(count % 9 == 0)
    detection = move_enemy_of_type('B', player, x_off, y_off, x, y, t);

  if(count % 3 == 0)
    move_enemy_of_type('A', player, x_off, y_off, x, y, t);

  count++;
  return detection; //we didnt hit the player
}

//moves an enemy of a certain type
int move_enemy_of_type(char symbol, tile* player, int x_off, int y_off, int x, int y, tile* t[100][150]) {
  int i, j;
  for(i=0;i<x;i++)
    for(j=0;j<y;j++){
      if(t[i+x_off][j+y_off]->state[CURRENT] == ENEMY && t[i+x_off][j+y_off]->symbol == symbol){
	int x_diff = abs(player->x - (i+x_off));
	int y_diff = abs(player->y - (j+y_off));
      
	if(x_diff == 0 && y_diff == 0){
	  t[i+x_off][j+y_off]->state[CURRENT] = PLAYER;
	  return 1; //we hit the player
	}else if(x_diff > y_diff){
	  int new_x = i+x_off + (player->x - (i+x_off))/x_diff;
	  t[i+x_off][j+y_off]->state[NEW] = EMPTY;
	  t[new_x][j+y_off]->state[NEW] = ENEMY;
	  t[new_x][j+y_off]->symbol = t[i+x_off][j+y_off]->symbol;
	}else{
	  int new_y = j+y_off + (player->y - (j+y_off))/y_diff;
	  t[i+x_off][j+y_off]->state[NEW] = EMPTY;
	  t[i+x_off][new_y]->state[NEW] = ENEMY;
	  t[i+x_off][new_y]->symbol = t[i+x_off][j+y_off]->symbol;
	}
      }
    }
  
  return 0;
}

/*
 * Moves the player to a new x,y cordinate on the board
 */
int move_player (tile** player, int new_x, int new_y, int width, int height, tile* t[100][150]) {
  int down,up,left,right;
  
  //decide direction of movement
  if(new_x >= 0 && new_x < (*player)->x && new_x%width < (*player)->x%width)
  down = 1;
  else
  down=0;
  if(new_x >= 0 && new_x > (*player)->x && new_x%width > (*player)->x%width)
  up=1;
  else up=0;
  if(new_y >= 0 && new_y < (*player)->y && new_y%height < (*player)->y%height)
  left =1;
  else
  left =0;
  if(new_y >= 0 && new_y > (*player)->y && new_y%height > (*player)->y%height)
  right=1;
  else
  right=0;
  
  if(down || up || left || right){ //we can move
    if(t[new_x][new_y]->state[CURRENT] == LOOT){
      t[new_x][new_y]->state[NEW] = PLAYER;
      t[(*player)->x][(*player)->y]->state[NEW] = EMPTY;
      
      *player = t[new_x][new_y];
      return 1;    //returns 1 when the player picks up gold
    }else if(t[new_x][new_y]->state[CURRENT] == ENEMY){
      t[new_x][new_y]->state[NEW] = PLAYER;
      t[(*player)->x][(*player)->y]->state[NEW] = EMPTY;
      
      *player = t[new_x][new_y];
      return -1;    //returns -1 when the player walks into a monster
    } else if(t[new_x][new_y]->stair) {
      return 3; //returns 3 when hit a stair
    } else if(t[new_x][new_y]->door) {
      return 4; //returns 4 when hit a door
    } else {
      t[new_x][new_y]->state[NEW] = PLAYER;
      t[(*player)->x][(*player)->y]->state[NEW] = EMPTY;
      
      *player = t[new_x][new_y];
      return 2;    //returns 2 when the player walks into an empty tile
    }
  }else{
    return 0;    //returns 0 if the player isn't allowed to move
  }
}

/*
 * Returns the number of damage delt ny the certain type of monster (15, 25, 30, 50) 
 */
int check_hit(tile* player, int x_off, int y_off, int x, int y, tile* t[100][150]) {
  int i, j;
  for(i=0;i<x;i++)
    for(j=0;j<y;j++){
      if(t[i+x_off][j+y_off]->state[CURRENT] == ENEMY){
	int x_diff = abs(player->x - (i+x_off));
	int y_diff = abs(player->y - (j+y_off));
	if(x_diff == 0 && y_diff == 0) { //monster
	  t[i+x_off][j+y_off]->state[CURRENT] = PLAYER;
	  if(t[i+x_off][j+y_off]->symbol == 'E')
	    return 15;
	  else if(t[i+x_off][j+y_off]->symbol == 'C')
	    return 25;
	  else if(t[i+x_off][j+y_off]->symbol == 'B')
	    return 30;
	  else if(t[i+x_off][j+y_off]->symbol == 'A')
	    return 50;
	}
      }
    }
  return 0;
}

/*
 * Teleports the player to a given x y
 */
void teleport_player(tile** player, int x, int y, tile* t[100][150]) {
  t[x][y]->state[NEW] = PLAYER;
  t[(*player)->x][(*player)->y]->state[NEW] = EMPTY;
      
  *player = t[x][y];
}
