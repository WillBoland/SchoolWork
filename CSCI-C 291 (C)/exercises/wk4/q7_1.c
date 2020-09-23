//Will Boland
//11/20/2019

//This program should read a string from the user and print it using a character pointer
//The program is setup to use pointer offset notation to get each character of the string

#include <stdio.h>
#include <string.h>

int main(void) {
  
  int size = 20;
  char string[size];
  char* stringPtr;

  printf("Please input a string.\n");
  fgets(string, size, stdin);
  stringPtr = string;

  int i = 0;
  while(i != size) {
    printf("%c", *(stringPtr + i));
    i++;
  }
  
  printf("\n");
  return 0;
}
