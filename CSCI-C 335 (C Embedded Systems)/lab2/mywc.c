//Will Boland
//01/22/2020

/*
 * Welcome to my word count. It provides similiar functionality to the wc command.
 */

#include <stdio.h>

int isChar(char c);

int main(void) {
  int previousChar = 0; //previous character
  int currentChar = 0;  //current character

  int lineCount = 0;
  int wordCount = 0;
  int charCount = 0;
  
  while((currentChar = getchar()) != EOF) {
    //increase char count because we encountered a new char
    charCount += 1;

    //increase line count because newline encountered
    if(currentChar == '\n') {
      lineCount += 1;
    }
    
    //check if word ended; increase word count
    if(isChar(previousChar) && !isChar(currentChar) && previousChar != 0) {
      wordCount += 1;
    }

    previousChar = currentChar;

  }

  printf("%d %d %d", lineCount, wordCount, charCount);
  return(0);
}

/*
 * Tells us if given char is a letter (NON-NUMBER)
 * - Parameter c: character to check
 * - Return: 0 if char is not a letter, non-zero if it is
 */
int isChar(char c) {
  return((c >= 65 && c <= 90) || (c >= 91 && c <= 122));
}
