//Will Boland
//11/20/2019

//This program uses a function called CharacterScan to read a char from the user
//The function must take an int pointer as a parameter
//The program should print the char and ascii code for each character the user enters
//The program should only exit when the user enters escape

#include <stdio.h>

char CharacterScan(int* iPtr);

int main(void) {

  char input = 'a';

  while(1) {
    //exit = getchar();

    int aCode;
    int* iPtr = &aCode;
    input = CharacterScan(iPtr);

    if(aCode == 27){
      break;
    }
    else{
      printf("%c is ASCII code %d.\n", input, aCode);
    }
  }
}

char CharacterScan(int* iPtr){
  char c;

  printf("Enter a character: ");
  scanf(" %c", &c);

  *iPtr = (int)c;
  return c;
}
