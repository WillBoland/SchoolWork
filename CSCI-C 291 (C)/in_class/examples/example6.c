#include <stdio.h>

int main(void) {
  
  int myArr[3][3] = {{1, 2, 3}, {1}};
  int i, j;
  for(i = 0; i < 3; i++) {
    for(j = 0; j < 3; j++) {
      printf("myArr[%d][%d]: %d at %p\n", i, j, myArr[i][j], &myArr[i][j]);
    }
    printf("\n");
  }

  printf("myArr address %p\n", myArr);
  printf("myArr[0] address %p\n", myArr[0]);
  printf("myArr[1] address %p\n", myArr[1]);
  printf("myArr[2] address %p\n", myArr[2]);
  printf("myArr[0][0]: %d at %p\n", myArr[0][0], &myArr[0][0]);
  printf("myArr[1][0]: %d at %p\n", myArr[1][0], &myArr[1][0]);
  printf("myArr[2][0]: %d at %p\n", myArr[2][0], &myArr[2][0]);

  printf("\n");

  printf("myArr[0][3]: %d at %p\n", myArr[0][3], &myArr[0][3]);
  printf("myArr[1][0]: %d at %p\n", myArr[1][0], &myArr[1][0]);

/*
  int k;
  for(k = 0; k < 9; k++) 
    printf("%p\n", &myArr[k]);
*/

  return 0;
}
