#include "conversions.h"
#include <stdio.h>

//--------------------
//MARK: Conversion to USD Function Definitions
//----------------------------------------------------------------------------
/*
* Returns the converted amount given in Euros to USD.
*/
float euro(float toUSD) {
return toUSD*1.12;
}

//----------------------------------------------------------------------------
/*  
* Returns the converted amount given in Pounds Sterling to USD.
*/
float pounds(float toUSD) {
  return toUSD*1.29;
}

//---------------------------------------------------------------------------
/*
* Returns the converted amount given in Australian Dollar to USD.
*/
float auDollar(float toUSD) {
  return toUSD*0.69;
}

//----------------------------------------------------------------------------
/*  
* Returns the converted amount given in Chinese Renminbi to USD.
*/
float renminbi(float toUSD) {
  return toUSD*0.14;
}

//----------------------------------------------------------------------------
/*
* Returns the converted amount given in Turkish Lira to USD.
*/
float lira(float toUSD) {
  return toUSD*0.18;
}

//----------------------------------------------------------------------------
/*
* Returns the converted amount given in Brazilian Real to USD.
*/
float real(float toUSD) {
  return toUSD*0.25;
}

//----------------------------------------------------------------------------
/*
* Returns the converted amount given in Bulgarian Lev to USD.
*/
float lev(float toUSD) {
  return toUSD*0.57;
}

//--------------------
//MARK: Conversion from USD Function Definitions
//----------------------------------------------------------------------------
/*
* Returns the converted amount given in USD to Euros.
*/
float usd_Euro(float amount) {
  return amount*0.89;
}

//----------------------------------------------------------------------------
/*
* Returns the converted amount given in USD to Pounds Sterling.
*/
float usd_Pounds(float amount) {
  return amount*0.77;
}

//----------------------------------------------------------------------------
/*
* Returns the converted amount given in USD to Australian Dollar.
*/
float usd_AuDollar(float amount) {
  return amount*1.45;
}

//----------------------------------------------------------------------------
/*
* Returns the converted amount given in USD to Chinese Renminbi.
*/
float usd_Renminbi(float amount) {
  return amount*7.04;
}

//----------------------------------------------------------------------------
/*
* Returns the converted amount given in USD to Turkish Lira.
*/
float usd_Lira(float amount) {
  return amount*5.71;
}

//----------------------------------------------------------------------------
/*
* Returns the converted amount given in USD to Brazilian Real.
*/
float usd_Real(float amount) {
  return amount*3.99;
}

//----------------------------------------------------------------------------
/*
* Returns the converted amount given in USD to Bulgarian Lev.
*/
float usd_Lev(float amount) {
  return amount*1.75;
}

//MARK: Other Functions
//----------------------------------------------------------------------------
/*
* Prints the converted amount given in USD to all supported currencies.
*/
void usd_toAll(float amount) {
  printf("$%.2f %-15s %-10.2f\n", amount, "in Euros is: ",  usd_Euro(amount));
  printf("$%.2f %-15s %-10.2f\n", amount, "in Pounds is: ", usd_Pounds(amount));
  printf("$%.2f %-15s %-10.2f\n", amount, "in AUD is: ", usd_AuDollar(amount));
  printf("$%.2f %-15s %-10.2f\n", amount, "in Renminbi is:", usd_Renminbi(amount));
  printf("$%.2f %-15s %-10.2f\n", amount, "in Lira is: ", usd_Lira(amount));
  printf("$%.2f %-15s %-10.2f\n", amount, "in Real is: ", usd_Real(amount));
  printf("$%.2f %-15s %-10.2f\n", amount, "in Lev is: ", usd_Lev(amount));
}

//----------------------------------------------------------------------------
/*
* Prints all supported currencies in USD converted.
*/
void all_toUSD(float amount) {
  printf("%.2f %-15s $%-10.2f\n", amount, "Euros is: ", euro(amount));
  printf("%.2f %-15s $%-10.2f\n", amount, "Pounds is: ", pounds(amount));
  printf("%.2f %-15s $%-10.2f\n", amount, "AUD is: ", auDollar(amount));
  printf("%.2f %-15s $%-10.2f\n", amount, "Renminbi is: ", renminbi(amount));
  printf("%.2f %-15s $%-10.2f\n", amount, "Lira is: ", lira(amount));
  printf("%.2f %-15s $%-10.2f\n", amount, "Real is: ", real(amount));
  printf("%.2f %-15s $%-10.2f\n", amount, "Lev is: ", lev(amount));
}


//----------------------------------------------------------------------------
/*
* Prints all exchange rates as of 11/2/2019
*/
void print_rates() {
  printf("* The current conversion as of 11/2/2019 are as follows:\n"
"*	Euro to USD:            1.12\n"
"*	Pounds Sterling to USD: 1.29\n"
"*	AU Dollar to USD:	0.69\n"
"*	CN Renminbi to USD:     0.14\n"
"*	Turkish Lira to USD:    0.18\n"
"*	Brazilian Real to USD:  0.25\n"
"*	Bulgarian Lev to USD:   0.57\n"
"*\n"
"*	USD to Euro:            0.89\n"
"*	USD to Pounds Sterling: 0.77\n"
"*	USD to AU Dollar:	1.45\n"
"*	USD to CN Renminbi:     7.04\n"
"*	USD to Turkish Lira:    5.71\n"
"*	USD to Brazilian Real:  3.99\n"
"*	USD to Bulgarian Lev:   1.75\n\n\n");
}
