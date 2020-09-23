//Will Boland
//10/27/2019

/*
  The logical errors had been corrected. Then, according to the original file, I implemented a cleaner and logically sound implementation of the 2 questions.
  It did not specify if part 2 had to use the original for loop for part 1. I seperated the for loops due to lack of clarification and to provide a more logically coherant version.
*/


#include<stdio.h>

int main (void){

  //fixed logical errors and then implemented better method for printing A-Z
  
  //Part 1: using a for loop, print all the characters from A-Z
  char alphabet = 'A';
  
  for(; alphabet <= 'Z'; alphabet++){
    printf("%c\n", alphabet);
  }

  
  //Part2: Print sum of all odd numbers 1-100
  int sum = 1;
  int counter = 3;

  for(; counter < 101; counter += 2) {
    sum += counter;
  }
  printf("%d\n", sum);
}
