#include <stdio.h>

int myFunc1(const int [], int); //Prototype

int main(void) {
  
  int myArr[5] = {1, 2, 3, 4, 6};
  int i, someVar = 100;
  printf("\n%d ", someVar);
  for(i = 0; i < 5; i++)
    printf("%d ", myArr[i]);
  myFunc1(myArr, someVar); 
  printf("\n%d ", someVar);
  for(i = 0; i < 5; i++)
    printf("%d ", myArr[i]);

// printf("size: %d\n", myFunc1(myArr));  

/*
  int myArr[5] = {1, 20, 30};
  int i;
  for (i = 0; i < 5; i++) {
    printf("myArr[%d]: %d at address %p\n", i, myArr[i], &myArr[i]);
  }

  printf("\n");

  //The name of the array is the memory address (constant pointer)
  printf("Array address %p %p %p\n", &myArr[0], myArr, &myArr);

  printf("\n");

  char myChar[5] = "Hi";
  for (i = 0; i < 5; i++) {
    printf("myArr[%d]: %c at address %p\n", i, myChar[i], &myChar[i]);
  }

  printf("\n");

  

  char string2[20];
  scanf("%10s", string2);
  printf("\n%s\n", string2);
*/
  return 0;
}

//Returns the sizeof the given integer
int myFunc1(const int x[], int someVar) {
  int i;
  someVar += 1;
  for(i = 0; i < 5; i++)
    x[i] += 1; 
  
  return 1;
}
