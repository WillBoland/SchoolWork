//Will Boland
//12/15/2019
#include "tile.h"
#include "score.h"

#ifndef SAVE_H  // header guard
#define SAVE_H
//This provides an easy way to save files
int saveGame(int index, struct Score* currentScore, int* currentFloor, int numberRooms, int x_offset, int y_offset, tile* player, tile* t[100][150]);
//This provides a way to load a file. Updates tiles, score, player, etc. if file is found. Returns 1. Returns 0 if no file found.
int loadGame(int index, struct Score* currentScore, int* currentFloor, int* numberRooms, int* x_offset, int* y_offset, tile* player, tile* t[100][150]);
#endif
