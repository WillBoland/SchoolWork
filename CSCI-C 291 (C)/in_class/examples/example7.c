#include <stdio.h>

int main(void) {
  int* myIntPtr;
  double* myDoublePtr; 
  char* myCharPtr;

  int myInt = 100;
  double myDouble = 11.8;
  char myChar = 'F';

  myIntPtr = &myInt;
  myDoublePtr = &myDouble;
  myCharPtr = &myChar;

  printf("int: %p, int_p: %p\n", myInt, *myIntPtr);
  printf("Address: %p, address_ptr: %p\n", &myInt, myIntPtr);
  printf("size: %p, size_ptr: %p\n", sizeof(myInt), sizeof(myIntPtr));

  printf("\ndouble: %f, double_p: %f\n", myDouble, *myDoublePtr);
  printf("Address: %p, address_ptr: %p\n", &myDouble, myDoublePtr);
  printf("size: %p, size_ptr: %p\n", sizeof(myDouble), sizeof(myDoublePtr));

  printf("\nchar: %c, char_p: %c\n", myChar, *myCharPtr);
  printf("Address: %p, address_ptr: %p\n", &myChar, myCharPtr);
  printf("size: %p, size_ptr: %p\n\n", sizeof(myChar), sizeof(myCharPtr));

  myIntPtr++;
  printf("Address: %p, address_ptr: %p\n", &myInt, myIntPtr);
  myDoublePtr++;
  printf("Address: %p, address_ptr: %p\n", &myDouble, myDoublePtr);
  myCharPtr++;
  printf("Address: %p, address_ptr: %p\n", &myChar, myCharPtr);
  
}
