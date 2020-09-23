//Will Boland

//main.c for lab7
#include <stm32f30x.h>  // Pull in include files for F30x standard drivers 
#include <f3d_uart.h>
#include <stdio.h>
#include <f3d_user_btn.h>
#include <f3d_lcd_sd.h>
#include <math.h>
#include <f3d_i2c.h>
#include <f3d_mag.h>
#include <f3d_systick.h>
#include <f3d_mag.h>
#include "keys.h"
#include "enums.h"

extern enum guess state;
extern enum difficulty diff;

int inMenu = 0;
int score = 0;
enum bonus bonusState = Normal;

void handleGuess(void);
void handleMenu(void);
void displayMain(void);
void displayMenu(void);
void displayHelp();

int main(void){
  //init
  f3d_i2c1_init();
  delay(20);
  f3d_mag_init();
  delay(20);
  user_btn_init();
  f3d_lcd_init();
  f3d_led_init();
  f3d_uart_init();
  f3d_systick_init();
  
  setvbuf(stdin, NULL, _IONBF, 0);
  setvbuf(stdout, NULL, _IONBF, 0);
  setvbuf(stderr, NULL, _IONBF, 0);
  SysTick_Handler();

  f3d_lcd_fillScreen(BLUE);
  displayMain();

  while(1) {
    handleMenu();

    if(!inMenu) {
      handleGuess();
    }
  }
}

void handleGuess(void) {
  //Display any active modifier
  float magData[3];
  f3d_mag_read(magData);
  double heading = atan2(magData[1], magData[0]) * (180 / 3.1415926535);

  if(heading >= 135 || heading <= -160) { //NORTH
    bonusState = Normal;
    f3d_lcd_drawString(40,80,"Bonus: x1",BLACK,BLUE);
  } else if(heading >= -159 && heading <= -100) { //WEST
    bonusState = Quadruple;
    f3d_lcd_drawString(40,80,"Bonus: x4",BLACK,BLUE);
  } else if(heading >= -99 && heading <= -15) { //SOUTH
    bonusState = Triple;
    f3d_lcd_drawString(40,80,"Bonus: x3",BLACK,BLUE);
  } else {                                    //EAST
    bonusState = Double;
    f3d_lcd_drawString(40,80,"Bonus: x2",BLACK,BLUE);
  }
  
  //Modifier Bonus Active
  int modifier = 5;  
  if(bonusState == Double) {
    modifier = 10;
  } else if(bonusState == Triple) {
    modifier = 15;
  } else if(bonusState == Quadruple) {
    modifier = 20;
  }

  //Correct VS Incorrect
  if(state == Correct) {
    f3d_lcd_drawString(0,30,"CORRECT",BLACK,GREEN);
    score += modifier;
  } else if(state == Incorrect) {
    f3d_lcd_drawString(0,30,"WRONG!!",BLACK,RED);
    score -= modifier;
  }
  state = None;

  //Display Score
  char scoreLabel[12];
  sprintf(scoreLabel, "Score: %d\t", score);
  f3d_lcd_drawString(40,90,scoreLabel,BLACK,BLUE);
}

void handleMenu(void) {
  if(getchar() == KEY_1) {
    inMenu = 1;
    f3d_lcd_fillScreen(BLUE);
    displayMenu();

    int key;
    while(key != KEY_1) {
      key = getchar();

      if(key == KEY_2) {
	displayHelp();
      }
      
      if(key == KEY_5) {
	diff = Easy;
	break;
      }

      if(key == KEY_6) {
	diff = Medium;
	break;
      }

      if(key == KEY_7) {
	diff = Hard;
	break;
      }

      if(key == KEY_8) {
	diff = Impossible;
	break;
      }
    }

    f3d_lcd_fillScreen(BLUE);
    displayMain();
    inMenu = 0;
  }
}

void displayMain(void) {
  char scoreLabel[12];
  sprintf(scoreLabel, "Score: %d", score);

  f3d_lcd_drawString(20,0,"Ring of Death",BLACK,BLUE);
  f3d_lcd_drawString(0,10,"Open Menu: 1",BLACK,BLUE);
  f3d_lcd_drawString(40,90,scoreLabel,BLACK,BLUE);
}

void displayMenu(void) {
  f3d_lcd_drawString(10,0,"MENU",BLACK,BLUE);

  //Different Options
  f3d_lcd_drawString(10,10,"1: Exit to Game",BLACK,BLUE);
  f3d_lcd_drawString(10,20,"2: Help",BLACK,BLUE);

  f3d_lcd_drawString(10,40,"5: EASY",BLACK,BLUE);
  f3d_lcd_drawString(10,50,"6: MEDIUM",BLACK,BLUE);
  f3d_lcd_drawString(10,60,"7: HARD",BLACK,BLUE);
  f3d_lcd_drawString(10,70,"8: IMPOSSIBLE",BLACK,BLUE);
}

void displayHelp(void) {
  f3d_lcd_fillScreen(BLUE);
  f3d_lcd_drawString(10,0,"HELP",BLACK,BLUE);
  f3d_lcd_drawString(10,10,"1: Go Back",BLACK,BLUE);
  f3d_lcd_drawString(10,30,"Use the button to stop the LED close to north LED (9) as possible. There are 3 different difficulties. Face N/S/E/W to gain different bonus.",BLACK,BLUE);
  while(getchar() != KEY_1);
  f3d_lcd_fillScreen(BLUE);
  displayMenu();
}

void assert_failed(uint8_t* file, uint32_t line) {
/* Infinite loop */
/* Use GDB to find out why we're here */
  putchar('c');
  while (1);
}
