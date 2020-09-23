#include <f3d_systick.h>
#include <f3d_led.h> 
#include <f3d_user_btn.h>
#include <f3d_uart.h>
#include "enums.h"

#define NORTH 10

volatile int systick_flag = 0;
volatile int currentLed = 8;
volatile enum guess state = None;
volatile enum difficulty diff = Easy;

void f3d_systick_init(void) {
  SysTick_Config(SystemCoreClock/5);
}

void SysTick_Handler(void) {
  if(diff == Easy) {
    SysTick_Config(SystemCoreClock/5);
  } else if(diff == Medium) {
    SysTick_Config(SystemCoreClock/10);
  } else if(diff == Hard) {
    SysTick_Config(SystemCoreClock/15);
  } else {
    SysTick_Config(SystemCoreClock/25);
  }
  
  if(user_btn_read()) { //we are getting user input
    if(currentLed == NORTH) { //correct guess
      state = Correct;
    } else {
      state = Incorrect;
    }
    while(user_btn_read());
  }

  if(currentLed == 16) {
    currentLed = 8;
  }
  f3d_led_all_off();
  f3d_led_on(currentLed);
  currentLed += 1;
}

/* f3d_systick.c ends here */
