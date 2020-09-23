//Will Boland
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
#include <math.h>

void resetPos(void) {
  //NSEW
  f3d_lcd_drawString(50,20,"X",BLUE,BLUE);
  f3d_lcd_drawString(80,80,"X",BLUE,BLUE);
  f3d_lcd_drawString(20,80,"X",BLUE,BLUE);
  f3d_lcd_drawString(50,130,"X",BLUE,BLUE);

  //diags
  f3d_lcd_drawString(10,10,"X",BLACK,BLUE);
  f3d_lcd_drawString(10,140,"X",BLACK,BLUE);
  f3d_lcd_drawString(90,140,"X",BLACK,BLUE);
  f3d_lcd_drawString(90,10,"X",BLACK,BLUE);
}

int main(void){
  //init
  f3d_uart_init();
  user_btn_init();
  f3d_lcd_init();

  f3d_i2c1_init();
  delay(12);
  f3d_accel_init();
  delay(12);
  f3d_mag_init();
  delay(12);

  setvbuf(stdin, NULL, _IONBF, 0);
  setvbuf(stdout, NULL, _IONBF, 0);
  setvbuf(stderr, NULL, _IONBF, 0);

  float accelData[3];
  float magData[3];
  int isAccel = 1;
  f3d_lcd_fillScreen(BLUE);

  //accel
  f3d_lcd_drawString(0,0,"Accel",BLACK,GREEN);

  //mag
  f3d_lcd_drawString(0,40,"Mag",BLACK,GREEN);

  while(1) {
    f3d_accel_read(accelData);
    f3d_mag_read(magData);

    //compute board title angle
    double pitch = atan(accelData[0]/(sqrt(pow(accelData[1], 2) + pow(accelData[2], 2)))) * (180 / 3.1415926535);
    double roll = atan(accelData[1]/(sqrt(pow(accelData[0], 2) + pow(accelData[2], 2)))) * (180 / 3.1415926535);
    //compute board compass heading
    double heading = atan2(magData[1], magData[0]) * (180 / 3.1415926535);


    if(user_btn_read()) {
      isAccel++;
      if(isAccel % 2 == 0) {
	  f3d_lcd_fillScreen(BLUE);
	  f3d_lcd_drawString(50,0,"N",BLACK,GREEN);
	  f3d_lcd_drawString(50,150,"S",BLACK,GREEN);
	  f3d_lcd_drawString(100,80,"E",BLACK,GREEN);
	  f3d_lcd_drawString(0,80,"W",BLACK,GREEN);

	  f3d_lcd_drawString(100,0,"NE",BLACK,GREEN);
	  f3d_lcd_drawString(100,150,"SE",BLACK,GREEN);
	  f3d_lcd_drawString(0,0,"NW",BLACK,GREEN);
	  f3d_lcd_drawString(0,150,"SW",BLACK,GREEN);
      } else {
	f3d_lcd_fillScreen(BLUE);
      }
      while(user_btn_read()); //still pressing
    }

    if(isAccel % 2) {
          //add accel stuff here
    char accelP[12];
    sprintf(accelP, "%s%.4f", "P: ", pitch);
    char accelR[12];
    sprintf(accelR, "%s%.4f", "R: ", roll);
    f3d_lcd_drawString(0,10,accelP,BLACK,BLUE);
    f3d_lcd_drawString(0,20,accelR,BLACK,BLUE);

    //add mag stuff here
    char magH[12];
    sprintf(magH, "%s%.4f", "Heading: ", heading);
    f3d_lcd_drawString(0,50,magH,BLACK,BLUE);

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
    } else {
      resetPos();

      //heading data
      //facing north (+135 to -160
      if(heading >= 135 || heading <= -160) { //NORTH
	f3d_lcd_drawString(50,20,"X",BLACK,GREEN);
      } else if(heading >= -159 && heading <= -100) { //WEST
	f3d_lcd_drawString(20,80,"X",BLACK,GREEN);
      } else if(heading >= -99 && heading <= -15) { //SOUTH
	f3d_lcd_drawString(50,130,"X",BLACK,GREEN);
      } else {                                    //EAST
	f3d_lcd_drawString(80,80,"X",BLACK,GREEN);
      }

      //diags
      if(heading <= -160 && heading >= 170) { //NORTH WEST
	resetPos();
	f3d_lcd_drawString(10,10,"X",BLACK,GREEN);
      } else if(heading >= -110 && heading <= -85) { //SOUTH WEST
	resetPos();
	f3d_lcd_drawString(10,140,"X",BLACK,GREEN);
      } else if(heading >= 0 && heading <= 17) { //SOUTH EAST
	resetPos();
	f3d_lcd_drawString(90,140,"X",BLACK,GREEN);
      } else if(heading >= 110 && heading <= 140) { //NORTH EAST
	resetPos();
	f3d_lcd_drawString(90,10,"X",BLACK,GREEN);
      }
    }
    printf("%d", isAccel);
  }
}

void assert_failed(uint8_t* file, uint32_t line) {
/* Infinite loop */
/* Use GDB to find out why we're here */
  while (1);
}
