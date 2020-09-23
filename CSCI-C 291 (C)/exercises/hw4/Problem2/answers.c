//Will Boland
//11/17/2019

//2. For each of the following, write a single statement that performs the indicated task. Assume that long integer variables value1 and value2 have been defined and that value1 has been initialized to decimal 70000.

#include <stdio.h>

int main(void) {

  long int value1 = 70000;
  long int value2;

  //A
  long int* lPtr;

  //B
  lPtr = &value1;

  //C
  printf("Value of Object Pointed to by lPtr: %ld\n", *lPtr);

  //D
  value2 = *lPtr;

  //E
  printf("Value of value2: %ld\n", value2);

  //F
  printf("Address of value1: %p\n", &value1);

  //G
  printf("Address Stored in lPtr: %p\nValues printed are the same.", lPtr);

  return 1;
}
