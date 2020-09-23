//Will Boland

//main.c for lab7
#include <stm32f30x.h>  // Pull in include files for F30x standard drivers 
#include <f3d_uart.h>
#include <stdio.h>
#include <f3d_user_btn.h>
#include <f3d_lcd_sd.h>
#include <f3d_i2c.h>
#include <f3d_accel.h>
#include <f3d_mag.h>
#include <math.h>
#include <f3d_systick.h>

int main(void){
  //init
  user_btn_init();
  f3d_led_init();
  f3d_uart_init();
  f3d_systick_init();
  
  setvbuf(stdin, NULL, _IONBF, 0);
  setvbuf(stdout, NULL, _IONBF, 0);
  setvbuf(stderr, NULL, _IONBF, 0);

  SysTick_Handler();
  
  printf("test");
  while(1) {
    putchar(getchar());
  }
}

void assert_failed(uint8_t* file, uint32_t line) {
/* Infinite loop */
/* Use GDB to find out why we're here */
  while (1);
}
