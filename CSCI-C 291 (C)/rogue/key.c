/* key.c ---------
 *
 * Filename: key.c
 * Description: Controls all key inputs
 * Author: Bryce Himebaugh
 * Maintainer:
 * Created: Thu Sep 15 16:35:07 2017
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

/* Copyright (c) 2016 The Trustees of Indiana University and
 * Indiana University Research and Technology Corporation.
 *
 * All rights reserved.
 *
 * Additional copyrights may follow
 */

#include <stdio.h>
#include <ncurses.h>
#include "key.h"

//reads keyboard input and returns a correspoding code we made like UP or QUIT
int read_escape(int* read_char)
{
  int c;
  if ((c = getch()) == ERR) {
    return (NOCHAR);
  }
  else if (c==0x1b) { //escape char
    if ((c = getch()) == '[') { //oh yay arrow key
      c=getch();
      switch (c) {
      case 'A': //up
        return (UP);
        break;
      case 'B': //down
        return (DOWN);
        break;
      case 'C': //right
        return (RIGHT);
        break;
      case 'D': //left
        return (LEFT);
        break;
      default: //not a valid arrow key
        return (BADESC);
      }
    }
  } else if(c == 'p' || c == 'P') { //pause
    return (PAUSE);
  } else if(c =='q' || c == 'Q') { //quit
    return (QUIT);
  } else if(c == 's' || c == 'S') {
    return (SAVE);
  } else if(c == 'l' || c== 'L') {
    return (LOAD);
  } else { //not a valid option
    *read_char = c;
    return (REGCHAR);
  }
}
