//Will Boland
//10/26/2019

#include <stdio.h>
#include <unistd.h> //included for the "sleep()" function

int main(void) {

  printf(" \"{Hello! Welcome to a Beautiful Fall @ IU!}, /\\ This is Fall 2019's 2nd 8 Week Course\" ");

  //creates an infinite loop
  while(1) {
    printf("\nWill Boland");
    
    int count; //declare outside of for loop (until c99 standard)
    for(count = 0; count < 15; count++) {
      if (count % 5 == 0 && count != 0)
        printf("\n");

      fflush(stdout); //flush the standard output buffer so our sleep works without having to do a newline
      sleep(1);
      printf(".");
    }
  }
  
  return 0;  
}
