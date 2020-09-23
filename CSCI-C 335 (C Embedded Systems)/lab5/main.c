//Will Boland
//Partner: Kenjie Moore

//main.c for lab6
#include <f3d_uart.h>
#include <stdio.h>
#include <f3d_gyro.h>
#include <f3d_led.h>
#include <f3d_user_btn.h>

void delay(void) {
  int i = 2000000;
  while (i-- > 0) {
    asm("nop"); /* This stops it optimising code out */
  }
}

int main(void){
  //init
  f3d_gyro_init();
  f3d_uart_init();
  user_btn_init();
  f3d_led_init();

  setvbuf(stdin, NULL, _IONBF, 0);
  setvbuf(stdout, NULL, _IONBF, 0);
  setvbuf(stderr, NULL, _IONBF, 0);

  //our full gyro data
  float data[3];
  int selectedAxis = 0;
  printf("Choose X, Y, or Z: ");
  fflush(stdout);

char ans = getchar();
    if(ans == 'x' || ans == 'X') {
      selectedAxis = 0;
    } else if(ans == 'y' || ans == 'Y') {
      selectedAxis = 1;
    } else if(ans == 'z' || ans == 'Z') {
      selectedAxis = 2;
    }

  //loop forever
  while(1) {
    f3d_gyro_getdata(data); //get gyro data
    float curPoint = data[selectedAxis];
    printf("Axis %c: %f\n", (selectedAxis == 0 ? 'X' : (selectedAxis == 1 ? 'Y' : 'Z')), curPoint);
    /*
    char ans = getchar();
    if(ans == 'x' || ans == 'X') {
      selectedAxis = 0;
    } else if(ans == 'y' || ans == 'Y') {
      selectedAxis = 1;
    } else if(ans == 'z' || ans == 'Z') {
      selectedAxis = 2;
    }
    */
    //user button, change axis by 1
    if(user_btn_read()) {
      selectedAxis = (selectedAxis + 1) % 3;
      while(user_btn_read()); //still pressing
    }

    //eastern
    if(curPoint < 0) {
      switch((int)(-curPoint) % 5) {
      case 1: f3d_led_on(9);
        break;
      case 2: f3d_led_on(10);
f3d_led_on(9);
	break;
      case 3: f3d_led_on(10);
f3d_led_on(9);f3d_led_on(11);
	break;
      case 4: f3d_led_on(12); f3d_led_on(10);
f3d_led_on(9);f3d_led_on(11);
	break;
      case 5: f3d_led_on(13); f3d_led_on(12); f3d_led_on(10);
f3d_led_on(9);f3d_led_on(11);
	break;
      default: 
	f3d_led_all_off(); 
	break;
      }
    } else {
      switch((int)curPoint % 5) {
      case 1: f3d_led_on(9);
        break;
      case 2: f3d_led_on(8); f3d_led_on(9);
	break;
      case 3: f3d_led_on(15); f3d_led_on(8); f3d_led_on(9);
	break;
      case 4: f3d_led_on(14); f3d_led_on(15); f3d_led_on(8); f3d_led_on(9);
	break;
      case 5: f3d_led_on(13); f3d_led_on(14); f3d_led_on(15); f3d_led_on(8); f3d_led_on(9);
	break;
      default: 
	f3d_led_all_off(); 
	break;
      }
    }

  }
}

void assert_failed(uint8_t* file, uint32_t line) {
/* Infinite loop */
/* Use GDB to find out why we're here */
  while (1);
}
