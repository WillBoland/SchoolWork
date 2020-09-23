//look up extern keyword

#include <stdio.h>
#include <unistd.h>


int funct1(int); //prototype of function


int x = 5;

int main(void) {
 /*  
  while(1) {
    fprintf(stderr, ". ");
    sleep(1);
    //fflush(stdout);
  }
 */
  
  printf("V: %d, A: %p\n", x, &x); //global variable x is in different memory location
  int x = 10;
  printf("V: %d, A: %p\n", x, &x); //local main x variable is in different memory location
  {
    int x = 20;
    printf("V: %d, A: %p\n", x, &x); //local variable x is in same memory stack with different memory location
  }
  printf("V: %d, A: %p\n", x, &x);

  funct1(x);
  
  return 0;
}


int funct1(int x) {
  printf("hi!\n");
  printf("V: %d, A: %p\n", x, &x);  
  return x+1;
}
