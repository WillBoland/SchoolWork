/* main.c --- 
 * 
 * Filename: main.c
 * Description: 
 * Author: 
 * Maintainer: 
 * Created: Thu Jan 10 11:23:43 2013
 * Last-Updated: 
 *           By: 
 *     Update #: 0
 * Keywords: 
 * Compatibility: 
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
/* Code: */

#include <f3d_uart.h>
#include <stdio.h>
#include <stm32f30x.h>  // Pull in include files for F30x standard drivers 
#include <f3d_led.h>     // Pull in include file for the local drivers
#include <stdlib.h>

int isChar(char c);

/*
 * Tells us if given char is a letter (NON-NUMBER)
 * - Parameter c: character to check
 * - Return: 0 if char is not a letter, non-zero if it is
 */
int isChar(char c) {
  return((c >= 65 && c <= 90) || (c >= 91 && c <= 122));
}

// Simple looping delay function
void delay(void) {
  int i = 2000000;
  while (i-- > 0) {
    asm("nop"); /* This stops it optimising code out */
  }
}

int main(void) {
  f3d_uart_init();

  setvbuf(stdin, NULL, _IONBF, 0);
  setvbuf(stdout, NULL, _IONBF, 0);
  setvbuf(stderr, NULL, _IONBF, 0);
 

  
  int previousChar = 0; //previous character
  int currentChar = 0;  //current character

  int lineCount = 0;
  int wordCount = 0;
  int charCount = 0;
  
  while((currentChar = getchar()) != 0x1b) { //esc char
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



#ifdef USE_FULL_ASSERT
void assert_failed(uint8_t* file, uint32_t line) {
/* Infinite loop */
/* Use GDB to find out why we're here */
  while (1);
}
#endif

/* main.c ends here */
