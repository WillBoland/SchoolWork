//hello.c
//Will Boland
//1/15/2020

#include <stdio.h>
#include <unistd.h>

int main(void) {
  while(1) {
    printf("Hello, world\n");
    sleep(3);
  }
  
  return(0);
}
