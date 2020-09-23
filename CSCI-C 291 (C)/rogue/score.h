#ifndef SCORE_H
#define SCORE_H

//Will Boland
//12/07/2019

//A struct that allows easy scoring and life handling
struct Score {
  int* currentGold;  //amount user has in gold
  int* currentLives; //number of lives remaining

  int* totalGold;    //amount of gold user ever collected throughout course of the game
  int* totalLives;   //amount of lives user ever had at one point
};

#endif
