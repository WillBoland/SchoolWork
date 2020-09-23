//Will Boland
//Partner: Kenjie Moore

//main.c for lab6
#include <stm32f30x.h>  // Pull in include files for F30x standard drivers 
#include <f3d_uart.h>
#include <stdio.h>
#include <f3d_gyro.h>
#include <f3d_led.h>
#include <f3d_user_btn.h>
#include <f3d_lcd_sd.h>

int main(void){
  //init
  f3d_gyro_init();
  f3d_uart_init();
  user_btn_init();
  f3d_led_init();
  f3d_lcd_init();

  setvbuf(stdin, NULL, _IONBF, 0);
  setvbuf(stdout, NULL, _IONBF, 0);
  setvbuf(stderr, NULL, _IONBF, 0);

  //our full gyro data
  float data[3];
  int selectedAxis = 0;

  f3d_lcd_fillScreen(BLUE);
  //X, Y, String, Colors
  f3d_lcd_drawString(0,0,"X:             ",BLACK,BLUE);
  f3d_lcd_drawString(0,20,"Y:             ",BLACK,BLUE);
  f3d_lcd_drawString(0,40,"Z:             ",BLACK,BLUE);

  //loop forever
  while(1) {
    f3d_gyro_getdata(data); //get gyro data

    //add LCD stuff here
    char x[12];
    sprintf(x, "%s%.4f", "x: ", data[0]);

    char y[12];
    sprintf(y, "%s%.4f", "y: ", data[1]);

    char z[12];
    sprintf(z, "%s%.4f", "z: ", data[2]);

    f3d_lcd_drawString(0,0,x,BLACK,BLUE);
    f3d_lcd_drawString(0,20,y,BLACK,BLUE);
    f3d_lcd_drawString(0,40,z,BLACK,BLUE);

    //graphing
    //x
    //printf("%d", ST7735_width);
    unsigned int i;
    uint16_t color = (data[0] < 0 ? RED : GREEN); //red negative, green positive
    for(i = 0; i < ST7735_width; i += 1) {
      f3d_lcd_drawPixel(i, 50, (i < (color == RED ? -data[0] : data[0]) ? color : BLUE));
      f3d_lcd_drawPixel(i, 51, (i < (color == RED ? -data[0] : data[0]) ? color : BLUE));

    }
    //y
    color = (data[1] < 0 ? RED : GREEN); //red negative, green positive
    for(i = 0; i < ST7735_width; i += 1) {
      f3d_lcd_drawPixel(i, 60, (i < (color == RED ? -data[1] : data[1]) ? color : BLUE));
      f3d_lcd_drawPixel(i, 61, (i < (color == RED ? -data[1] : data[1]) ? color : BLUE));

    }
    //z
    color = (data[2] < 0 ? RED : GREEN); //red negative, green positive
    for(i = 0; i < ST7735_width; i += 1) {
      f3d_lcd_drawPixel(i, 70, (i < (color == RED ? -data[2] : data[2]) ? color : BLUE));
      f3d_lcd_drawPixel(i, 71, (i < (color == RED ? -data[2] : data[2]) ? color : BLUE));

    }

    printf("%s\n%s\n%s\n\n", x, y, z);

    delay(5);
  }
}

void assert_failed(uint8_t* file, uint32_t line) {
/* Infinite loop */
/* Use GDB to find out why we're here */
  while (1);
}
