//Will Boland        2020
//Partner: Kenjie Moore

//main.c for lab7
#include <stm32f30x.h>  // Pull in include files for F30x standard drivers 
#include <f3d_uart.h>
#include <stdio.h>
#include <f3d_user_btn.h>
#include <f3d_lcd_sd.h>
#include <f3d_i2c.h>
#include <f3d_accel.h>
#include <f3d_mag.h>
#include <f3d_nunchuk.h>
#include <f3d_gyro.h>
#include <math.h>

void resetScreen(void);
void chooseScreen(int cur);
void dispGyro(void);
void dispBoardTilt(void);
void dispBoardCompass(void);

//----------------------------------------------------------------------------
int main(void){
  //init
  f3d_uart_init();
  user_btn_init();
  f3d_lcd_init();
  delay(12);
  f3d_i2c1_init();
  delay(12);
  f3d_accel_init();
  delay(12);
  f3d_mag_init();
  delay(12);
  f3d_gyro_init();
  delay(12);

  setvbuf(stdin, NULL, _IONBF, 0);
  setvbuf(stdout, NULL, _IONBF, 0);
  setvbuf(stderr, NULL, _IONBF, 0);

  resetScreen();
  delay(12);

  int currentScreen = 0; //0 = gyro; 1 = bTilt; 2 = bComp;

  while(1) {
    //change displays
    if(user_btn_read()) {
      resetScreen();
      delay(12);
      currentScreen += 1;
      currentScreen %= 3;
      while(user_btn_read());
    }
    
    //display selected screen
    chooseScreen(currentScreen);
   }
}

//----------------------------------------------------------------------------
void resetScreen() {
  f3d_lcd_fillScreen(BLUE);
}

//----------------------------------------------------------------------------
void chooseScreen(int cur) {
  if(cur == 0) {
    dispGyro();
  } else if (cur == 1) {
    dispBoardTilt();
  } else if(cur == 2) {
    dispBoardCompass();
  }
}

//----------------------------------------------------------------------------
void dispGyro() {
  f3d_lcd_drawString(0,0,"Gyro",BLACK,GREEN);
  float data[3];
  f3d_gyro_getdata(data); //get gyro data

  //add LCD stuff here
  char x[12];
  sprintf(x, "%s%.4f", "x: ", data[0]);

  char y[12];
  sprintf(y, "%s%.4f", "y: ", data[1]);

  char z[12];
  sprintf(z, "%s%.4f", "z: ", data[2]);

  f3d_lcd_drawString(0,20,x,BLACK,BLUE);
  f3d_lcd_drawString(0,40,y,BLACK,BLUE);
  f3d_lcd_drawString(0,60,z,BLACK,BLUE);

  //graphing
  //x
  //printf("%d", ST7735_width);
  unsigned int i;
  uint16_t color = (data[0] < 0 ? RED : GREEN); //red negative, green positive
  for(i = 0; i < ST7735_width; i += 1) {
    f3d_lcd_drawPixel(i, 70, (i < (color == RED ? -data[0] : data[0]) ? color : BLUE));
    f3d_lcd_drawPixel(i, 71, (i < (color == RED ? -data[0] : data[0]) ? color : BLUE));

  }
  //y
  color = (data[1] < 0 ? RED : GREEN); //red negative, green positive
  for(i = 0; i < ST7735_width; i += 1) {
    f3d_lcd_drawPixel(i, 80, (i < (color == RED ? -data[1] : data[1]) ? color : BLUE));
    f3d_lcd_drawPixel(i, 81, (i < (color == RED ? -data[1] : data[1]) ? color : BLUE));

  }
  //z
  color = (data[2] < 0 ? RED : GREEN); //red negative, green positive
  for(i = 0; i < ST7735_width; i += 1) {
    f3d_lcd_drawPixel(i, 90, (i < (color == RED ? -data[2] : data[2]) ? color : BLUE));
    f3d_lcd_drawPixel(i, 91, (i < (color == RED ? -data[2] : data[2]) ? color : BLUE));

  }
  printf("%s\n%s\n%s\n\n", x, y, z);
  delay(5);  
}

//----------------------------------------------------------------------------
void dispBoardTilt() {
  float accelData[3];
  //accel
  f3d_lcd_drawString(0,0,"Accel",BLACK,GREEN);
  f3d_accel_read(accelData);
  //compute board title angle
  double pitch = atan(accelData[0]/(sqrt(pow(accelData[1], 2) + pow(accelData[2], 2)))) * (180 / 3.1415926535);
  double roll = atan(accelData[1]/(sqrt(pow(accelData[0], 2) + pow(accelData[2], 2)))) * (180 / 3.1415926535);
  char accelP[12];
  sprintf(accelP, "%s%.4f", "P: ", pitch);
  char accelR[12];
  sprintf(accelR, "%s%.4f", "R: ", roll);
  f3d_lcd_drawString(0,10,accelP,BLACK,BLUE);
  f3d_lcd_drawString(0,20,accelR,BLACK,BLUE);

  //bar graph for accel
  unsigned int i;
  uint16_t color = (pitch < 0 ? RED : GREEN); //red negative, green positive
  for(i = 0; i < ST7735_width; i += 1) {
    f3d_lcd_drawPixel(i, 70, (i < (color == RED ? -pitch : pitch) ? color : BLUE));
    f3d_lcd_drawPixel(i, 71, (i < (color == RED ? -pitch : pitch) ? color : BLUE));
  }
  //roll
  color = (roll < 0 ? RED : GREEN); //red negative, green positive
  for(i = 0; i < ST7735_width; i += 1) {
    f3d_lcd_drawPixel(i, 75, (i < (color == RED ? -roll : roll) ? color : BLUE));
    f3d_lcd_drawPixel(i, 76, (i < (color == RED ? -roll : roll) ? color : BLUE));
  }
  
}

//----------------------------------------------------------------------------
void dispBoardCompass() {
  f3d_lcd_drawString(0,0,"Heading",BLACK,GREEN);
  float magData[3];
  f3d_mag_read(magData);
  //mag
  f3d_lcd_drawString(0,40,"Mag",BLACK,GREEN);

  //add mag stuff here
  char magH[12];
  //compute board compass heading
  double heading = atan2(magData[1], magData[0]) * (180 / 3.1415926535);
  sprintf(magH, "%s%.4f", "Heading: ", heading);
  f3d_lcd_drawString(0,50,magH,BLACK,BLUE);
}

//----------------------------------------------------------------------------
void assert_failed(uint8_t* file, uint32_t line) {
/* Infinite loop */
/* Use GDB to find out why we're here */
  while (1);
}
