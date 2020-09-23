/*main.c ---------
 *
 * Filename: main.c
 * Description:
 * Author: Bryce Himebaugh
 * Maintainer:
 * Created: Thu Aug 18 16:32:20 2017
 * Last-Updated: September 23 15:38 2018
 *           By: Dmitrii Galantsev
 *
 */

/* Commentary:
 *
 *
 *
 */

/* Change log:
 *
 *
 */

/* Copyright (c) 2004-2007 The Trustees of Indiana University and
 * Indiana University Research and Technology Corporation.
 *
 * All rights reserved.
 *
 * Additional copyrights may follow
 */

/* Code: */
#include <stdio.h>
#include <ncurses.h>
#include "game.h"
#define GOLD_COLOR "\033[0;33m" //red
#define RED_COLOR "\033[0;31m"  //gold
#define RESET_COLOR "\033[0m" //reset to white


int main(void)
{
  //Welcome screen and score system
  printf("\n\n***Welcome to %sROUGE%s***\n", RED_COLOR, RESET_COLOR);
  printf("SCORING:\n\t%sGold%s is represented by '$'\n\tYour gold increases by 2 when you collect it.\n\tYou gain a life if you collect 100 or more gold.\n\tYou lose if you run out of lives. Initial lives is 1.\n", GOLD_COLOR, RESET_COLOR);

  //S to start
  printf("Please type 's', and then hit enter, to start ROUGE: ");
  char input;
  scanf("%c", &input);
  while(input != 's') {
    printf("\nPlease enter a valid command: ");
    scanf(" %c", &input);
  }

  //run game cause they said S
  game();
  
  //game over man
  return (0);
}
