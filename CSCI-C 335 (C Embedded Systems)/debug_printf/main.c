//Will Boland
//Partner: Kenjie Moore

//main.c for lab6
#include <f3d_uart.h>
#include <stdio.h>
#include <f3d_gyro.h>
#include <f3d_led.h>
#include <f3d_user_btn.h>

//delay function
void delay(void) {
  int i = 200000;
  while (i-- > 0) {
    asm("nop"); /* This stops it optimising code out */
  }
}

int main(void){
  //inits what we need
  f3d_uart_init();
  f3d_gyro_init();
  user_btn_init(); 
  f3d_led_init();

  //so we can use printf
  setvbuf(stdin, NULL, _IONBF, 0);
  setvbuf(stdout, NULL, _IONBF, 0);
  setvbuf(stderr, NULL, _IONBF, 0);

  //infinite loop :)
  while(1) {
    f3d_led_on(9); //our north light
    unsigned int i = 0;
    for(; i < 10; i += 1) { //start printing numbers 1-9
      printf("Number: %d\n", i);
    }
    f3d_led_all_off(); //turn off the lights
    delay(); //delays so we can actually see it happen
    delay();
    delay();
    delay();
  }
}

void assert_failed(uint8_t* file, uint32_t line) {
/* Infinite loop */
/* Use GDB to find out why we're here */
  while (1);
}
