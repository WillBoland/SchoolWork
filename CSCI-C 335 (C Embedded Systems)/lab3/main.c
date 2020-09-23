#include <stm32f30x.h>  // Pull in include files for F30x standard drivers 
#include <f3d_led.h>     // Pull in include file for the local drivers
#include <f3d_user_btn.h>

// Simple looping delay function
void delay(void) {
  int i = 4000000;
  while (i-- > 0) {
    asm("nop"); /* This stops it optimising code out */
  }
}

int main(void) {
  unsigned int index = 8;
  f3d_led_init();
  user_btn_init();
  delay(); //initial delay

  while(1) {
    if(user_btn_read()) {//user is pressing button
      continue;
    } else { //user is not
      f3d_led_off(index); //turn off led
      if(index == 15) { //we are at end of our cycle, turn on first led and all
	f3d_led_on(8);
	delay();	
	f3d_led_all_on();
	delay();
	f3d_led_all_off();
	index = 8;
	delay();
	continue;
      }
      //we just iterate
      index += 1;
      f3d_led_on(index);
      delay();
    }
  }
}

#ifdef USE_FULL_ASSERT
void assert_failed(uint8_t* file, uint32_t line) {
/* Infinite loop */
/* Use GDB to find out why we're here */
  while (1);
}
#endif

/* main.c ends here */
