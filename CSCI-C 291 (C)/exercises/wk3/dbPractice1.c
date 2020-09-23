//Will Boland
//11/4/2019

// The factorial of an integer is the product of the number with all
// the positive integers below it.

// For example: Factorial of 5 = 5 * 4 * 3 * 2 * 1 = 120

#include <stdio.h>

long long int factorial(long long int); //returns the factorial of a number

int main(void) {
	long long int n, fact;
	printf("Debugging Practice 1 - Quiz 3, Q3\n\n");
	printf("Please enter the number whose factorial you wish to find: ");
	scanf(" %lld", &n);
	fact = factorial(n);
	printf("The factorial of %lld is %lld\n", n, fact);
}

//returns the factorial of a number
long long int factorial(long long int number) {
  if(number <= 1)
    return 1;
  else
    return number * factorial(number - 1);
}


 

