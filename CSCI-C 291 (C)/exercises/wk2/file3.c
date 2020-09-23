/*Debugging quiz - File No: 3 */
/* This program should perform the duties of a basic calculator and print whether the result is >100 or <100 or equal to 100 */
/* the program should ask for the input until 'q' is enterd */
// Check for possible logical errors and rectify them 

//Will Boland

#include<stdio.h>

int main(void){
  char input;
  float num1, num2, result;

  printf("Welcome to the Calculator\nOperation choices:\tAddition(A)\n\t\t\tSubtraction(S)\n\t\t\tMultiplication(M)\n\t\t\tDivision(D)\nEnter choice: ");
   
  while(input != 'q')
  { //start while
  
  scanf("%c", &input);

  if(input == 'A' || input == 'S' || input == 'M' || input == 'D'){//start if input
    printf("Enter both numbers in required sequence: ");
    scanf("%f, %f", &num1, &num2);
    switch(input){//start switch
      case 'A': 
        result = num1 + num2;
        break;  
      case 'S': 
        result = num1 - num2;
        break;
      case 'M': 
        result = num1 * num2;
      	break; 
      case 'D': 
        result = num1 / num2; 
        break;
      default: 
        printf("Please choose a valid operation\n");
        break;
    }//end switch

    if(result > 100){//start if
      printf("Greater than 100.\n");
    }//end if
    else if(result < 100) {//start if
      printf("Less than 100\n");
    }//end if
    else if (result == 100){ //start if
      printf("Equal to 100\n");
    }//end if
   } //end if input
  else {
    printf("Enter choice for operation: ");
  }
}
printf("Quit the menu.\n");
return 0;
}
