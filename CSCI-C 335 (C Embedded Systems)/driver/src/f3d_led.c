/************************
 *f3d_led.c - contains intializations/functions for the leds
 ************************/

#include <stm32f30x.h>
#include <stm32f30x_gpio.h>
#include <stm32f30x_rcc.h>
#include <f3d_led.h>


//intializes the port and pins for the leds on the board
void f3d_led_init(void) {
  //config
  GPIO_InitTypeDef GPIO_InitStructure;
  GPIO_StructInit(&GPIO_InitStructure);

  //init pins for LED lights
  GPIO_InitStructure.GPIO_Pin = GPIO_Pin_All;

  GPIO_InitStructure.GPIO_Mode = GPIO_Mode_OUT;      //output, not input
  GPIO_InitStructure.GPIO_OType = GPIO_OType_PP;     
  GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;  //speed
  GPIO_InitStructure.GPIO_PuPd = GPIO_PuPd_NOPULL;
  //clock signal prior to config init
  RCC_AHBPeriphClockCmd(RCC_AHBPeriph_GPIOE, ENABLE);
  //init to apply configuration to the specific port pin
  GPIO_Init(GPIOE, &GPIO_InitStructure);  //port E
}

/*Turns on the appropriate led as specified by the parameter.*/
void f3d_led_on(int led) {
  switch(led) {
    case 8: 
      GPIOE->BSRR = GPIO_Pin_8; break;
    case 9: 
      GPIOE->BSRR = GPIO_Pin_9; break;
    case 10: 
      GPIOE->BSRR = GPIO_Pin_10; break;
    case 11: 
      GPIOE->BSRR = GPIO_Pin_11; break;
    case 12: 
      GPIOE->BSRR = GPIO_Pin_12; break;
    case 13: 
      GPIOE->BSRR = GPIO_Pin_13; break;
    case 14: 
      GPIOE->BSRR = GPIO_Pin_14; break;
    case 15: 
      GPIOE->BSRR = GPIO_Pin_15; break;
    default:
      break;
  }
}

/*Turns off the approiate led as specified by the parameter*/ 
void f3d_led_off(int led) {
  switch(led) {
    case 8: 
      GPIOE->BRR = GPIO_Pin_8; break;
    case 9: 
      GPIOE->BRR = GPIO_Pin_9; break;
    case 10: 
      GPIOE->BRR = GPIO_Pin_10; break;
    case 11: 
      GPIOE->BRR = GPIO_Pin_11; break;
    case 12: 
      GPIOE->BRR = GPIO_Pin_12; break;
    case 13: 
      GPIOE->BRR = GPIO_Pin_13; break;
    case 14: 
      GPIOE->BRR = GPIO_Pin_14; break;
    case 15: 
      GPIOE->BRR = GPIO_Pin_15; break;
    default:
      break;
  }
} 

/*Turns on all LEDs*/
void f3d_led_all_on(void) {
  GPIOE->BSRR = GPIO_Pin_All;
} 

/*Turns off all LEDs*/
void f3d_led_all_off(void) {
  GPIOE->BRR = GPIO_Pin_All;
} 

/* led.c ends here */

