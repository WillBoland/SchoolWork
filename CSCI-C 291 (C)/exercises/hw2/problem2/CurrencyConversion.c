//Homework 2, Question 2
//Will Boland
//11/2/2019

/*
* This program performs basic currency conversion for Euros (E), Pounds Sterling (P), Australian Dollars (A),
* Chinese Renminbi (R), Turkish Lira (L), Brazilian Real (B), and Bulgarian Lev (G).
*
* 'Q' will quit the program. 'X' will convert the dollar amount to all currencies.
* The allowable range for amounts is [10.00, 100.00].
*
* The default conversion is to USD. If you want to go from USD to another currency, type '#'.
*
* The current conversion as of 11/2/2019 are as follows:
*	Euro to USD: 		1.12
*	Pounds Sterling to USD:	1.29
*	AU Dollar to USD:	0.69
*	CN Renminbi to USD: 	0.14
*	Turkish Lira to USD: 	0.18
*	Brazilian Real to USD: 	0.25
*	Bulgarian Lev to USD: 	0.57
*
*	USD to Euro:		0.89
*	USD to Pounds Sterling:	0.77
*	USD to AU Dollar:	1.45
*	USD to CN Renminbi:	7.04
*	USD to Turkish Lira:	5.71
*	USD to Brazilian Real:	3.99
*	USD to Bulgarian Lev:	1.75
*
*/

#include <stdio.h>
#include "conversions.h" //my functions for converting

//gets the user's desired command, if acceptable
char getUserCommand();

//--------------------
//MARK: Main
int main(void) {
  char userCommand;	//The command ('q', 'x') or currency ('e') the user input into the machine
  float amount = 0;	//The amount the user wants converted

  printf("\n\n\nWelcome to my Currency Converter. We allow basic currency conversion for Euros (E), Pounds Sterling (P), Australian Dollars (A), Chinese Renminbi (R), Turkish Lira (L), Brazilian Real (B), and Bulgarian Lev (G), all to USD. \nWe also allow USD to all options. Enter # to show options for converting USD to other currencies.\n\tX will show conversions from all currencies to USD.\n\t# can also show you conversion from USD to all currencies. When # is entered, it will ask for an amount and finally another command. If X is entered here, it will show USD to all currencies.\n\tQ will quit.\n\n\n");
  print_rates();  

  //user has not quit so repeat until doing so  
  while((userCommand = getUserCommand()) != 'Q') {

    //ask for amount
    printf("Please enter an amount from [10.00, 100.00]: ");
    scanf(" %f", &amount);

    //not a valid range for amount, ask again
    while(amount < 10.00 || amount > 100.00) {
      printf("The amount has to be in range [10.00, 100.00]. Please enter a new amount: ");
      scanf(" %f", &amount);
    }
    
    
    //Handles all "to USD" conversions as well as option 'X'
    switch(userCommand) {
      case 'E':
	printf("\n\n\n%.2f Euros to USD is $%.2f\n\n\n", amount, euro(amount));
	break;
      case 'P':
	printf("\n\n\n%.2f Pounds to USD is $%.2f\n\n\n", amount, pounds(amount));
	break;
      case 'A':
	printf("\n\n\n%.2f AUD to USD is $%.2f\n\n\n", amount, auDollar(amount));
	break;
      case 'R':
	printf("\n\n\n%.2f Chinese Renminbi to USD is $%.2f\n\n\n", amount, renminbi(amount));
	break;
      case 'L':
	printf("\n\n\n%.2f Lira to USD is $%.2f\n\n\n", amount, lira(amount));
	break;
      case 'B':
	printf("\n\n\n%.2f Real to USD is $%.2f\n\n\n", amount, real(amount));
	break;
      case 'G':
	printf("\n\n\n%.2f Lev to USD is $%.2f\n\n\n", amount, lev(amount));
	break;
      case 'X':
	printf("\n\n\n"); all_toUSD(amount); printf("\n\n\n");
	break;
      default:
	break;
    }

    //from supported currencies to USD. Includes X to show all Currencies from USD
    if(userCommand == '#') {
      printf("Conversions now go from USD to selected currency. X will show USD to All currencies. ");
      userCommand = getUserCommand();
      
      //Handles all "from USD" conversions as well as option 'X'
      switch(userCommand) {
        case 'E':
          printf("\n\n\n$%.2f to Euros is %.2f\n\n\n", amount, usd_Euro(amount));
          break;
        case 'P':
          printf("\n\n\n$%.2f to Pounds is %.2f\n\n\n", amount, usd_Pounds(amount));
          break;
        case 'A':
          printf("\n\n\n$%.2f to AUD is %.2f\n\n\n", amount, usd_AuDollar(amount));
          break;
        case 'R':
          printf("\n\n\n$%.2f to  Chinese Renminbi is %.2f\n\n\n", amount, usd_Renminbi(amount));
          break;
        case 'L':
          printf("\n\n\n$%.2f to Lira is %.2f\n\n\n", amount, usd_Lira(amount));
          break;
        case 'B':
          printf("\n\n\n$%.2f to Real is %.2f\n\n\n", amount, usd_Real(amount));
          break;
        case 'G':
          printf("\n\n\n$%.2f to Lev is %.2f\n\n\n", amount, usd_Lev(amount));
          break;
        case 'X':
	  printf("\n\n\n"); usd_toAll(amount);  printf("\n\n\n");
          break;
        default:
          break;
      }
    }
  printf("Welcome to my Currency Converter. We allow basic currency conversion for Euros (E), Pounds Sterling (P), Australian Dollars (A), Chinese Renminbi (R), Turkish Lira (L), Brazilian Real (B), and Bulgarian Lev (G), all to USD. \nWe also allow USD to all options. Enter # to show options for converting USD to other currencies.\n\tX will show conversions from all currencies to USD.\n\t# can also show you conversion from USD to all currencies. When # is entered, it will ask for an amount and finally another command. If X is entered here, it will show USD to all currencies.\n\tQ will quit.\n\n\n");
  }
  return 0;
}



//Asks for user command until proper command is given
char getUserCommand() {
  char userCommand;
  printf("Please enter a command: ");
  scanf(" %c", &userCommand);
  userCommand = toupper(userCommand); //for case insensitive

    //not a valid command, ask again
    while(!(userCommand == 'E' || userCommand == 'P' || userCommand == 'A' || userCommand == 'R' || userCommand == 'L' || userCommand == 'B' || userCommand == 'G' ||
           userCommand == 'Q' || userCommand == 'X' || userCommand == '#')) {
      printf("Not a valid command. Please enter a valid command: ", userCommand);
      scanf(" %c", &userCommand);
      userCommand = toupper(userCommand); //for case insensitive
    }

  return userCommand;
}
