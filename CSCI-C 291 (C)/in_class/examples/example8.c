#include <stdio.h>

int main(void) {
  int myArray[5] = {1, 2 ,3, 4, 5};
  int* myArrayPtr = myArray;

  int i;
  for(i = 0; i < 5; i++) {
    printf("myArray[%d]: %d\n", i, myArray[i]);
    printf("myArrayPtr[%d]: %d\n", i, myArrayPtr[i]);
    printf("*(myArrayPtr+%d): %d\n", i, *(myArrayPtr + i));
    printf("*(myArray+%d): %d\n\n", i, *(myArray+i));
  }

  return 0;
}
