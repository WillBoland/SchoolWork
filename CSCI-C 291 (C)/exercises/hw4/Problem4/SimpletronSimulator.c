//Will Boland
//11/17/2019

/*
 * Welcome to SimpletronSimulator!!!
 * You can add, subtract, divide, multiply, get the remainder, exponentation, SQRT, and factorial.
 * You can also read a string and save the size + character ASCII values (UPPER CASE VALUES)
 */

// Exercise 7.28
#include <stdbool.h>
#include <stdio.h>
#include <math.h>
#include <ctype.h>
// define commands
#define SIZE 800
#define SENTINEL -99999

#define READ 10 
#define WRITE 11
#define READ_STRING 12 //reads a string and saves ASCII values starting at memory location 100

#define LOAD 20
#define STORE 21

#define ADD 30
#define SUBTRACT 31
#define DIVIDE 32
#define MULTIPLY 33
#define REMAINDER 34 //allows for remainder calculations
#define EXPONENTIATION 35 //allows for exponentiation operands using math.h
#define SQRT 36 //allows for Squareroot of a value at a given memory address
#define FACT 37 //allows for factorial of a value at a given memory address

#define BRANCH 40
#define BRANCHNEG 41
#define BRANCHZERO 42
#define HALT 43

// function prototypes
void load(float *loadMemory);
void execute(float *memory, float *acPtr, size_t *icPtr, int *irPtr,
             int *opCodePtr, int *opPtr);
void dump(float *memory, float accumulator, size_t instructionCounter,
          int instructionRegister, int operationCode,
          int operand);
bool validWord(int word);

int main()
{ 
   float memory[SIZE]; // define memory array
   float ac = 0; // accumulator
   size_t ic = 0; // instruction counter
   int opCode = 0; // operation code
   int op = 0; // operand
   int ir = 0; // instruction register

   // clear memory
   for (size_t i = 0; i < SIZE; ++i) {
      memory[i] = 0;
   } 
   
   load(memory); 
   execute(memory, &ac, &ic, &ir, &opCode, &op);  
   dump(memory, ac, ic, ir, opCode, op);
} 

// function loads instructions
void load(float *loadMemory)
{ 
   size_t i = 0; // indexing variable

   printf("%s\n\n%s\n%s\n%s\n%s\n%s\n%s\n\n", 
          "***           Welcome to Simpletron             ***",
          "*** Please enter your program one instruction   ***",
          "*** (or data word) at a time. I will type the ***",
          "*** location number and a question mark (?).  ***",
          "*** You then type the word for that location.   ***",
          "*** Type the sentinel -99999 to stop entering   ***",
          "*** your program.                               ***");
          
   printf("%s", "00 ? ");
   int instruction; // current instruction
   scanf("%d", &instruction); // read instruction
   
   // while sentinel is not read from user
   while (instruction != SENTINEL) { 

      // test instruction for validity
      if (!validWord(instruction)) {
         puts("Number out of range. Please enter again.\n");
      } 
      else { // load instruction
         loadMemory[i++] = instruction;
      } 

      printf("%02d ? ", i);
      scanf("%d", &instruction);
   } 

} 

// carry out the commands
void execute(float *memory, float *acPtr, size_t *icPtr, int *irPtr,
             int *opCodePtr, int *opPtr)
{ 
   bool fatal = false; // fatal error flag
   float temp; // temporary holding space
   int sizeOfString; //size of the string
   char stringInput[600]; //input of the string
   unsigned int i; //counter for loop
   unsigned int fullWord; //to make the word
   puts("\n************START SIMPLETRON EXECUTION************\n\n");

   // separate operation code and operand
   *irPtr = memory[*icPtr];
   if(*irPtr <= 9999) { //number is 4 digits long
     *opCodePtr = *irPtr / 100;
     *opPtr = *irPtr % 100;
   } else { //number is 5 digits long
     *opCodePtr = *irPtr / 1000;
     *opPtr = *irPtr % 1000;
   }
   // loop while command is not HALT or fatal
   while (*opCodePtr != HALT && !fatal) { 
      // determine appropriate action
      switch (*opCodePtr) { 
         // read data into location in memory
         case READ:
            puts("Enter a number: ");
            scanf("%f", &temp);
            
            // check for validity
            while (!validWord(temp)) { 
               puts("Number out of range. Please enter again: ");
               scanf("%f", &temp);
            } 
            
            memory[*opPtr] = temp; // write to memory
            ++(*icPtr);
            break; // exit switch

	    //reads a string data into location memory
      case READ_STRING:

	//we use fullWord to make the correct digits
	puts("Enter number of chars in string: ");
	scanf("%d", &sizeOfString);
	*opPtr = 100;

	puts("Enter your string: ");
	getchar();
	fgets(stringInput, sizeOfString + 1, stdin);
	//fist add size at start!
	fullWord = sizeOfString * 100 + toupper(stringInput[0]);
	memory[*opPtr] = fullWord;
	*opPtr += 1;

	//go through and add char value for 2 spaces
	for(i = 1; i < sizeOfString; i += 2) {
	  fullWord = toupper(stringInput[i]) * 100;
	  if(i != sizeOfString) {
	    fullWord += toupper(stringInput[i + 1]);
	  }
	  memory[*opPtr] = fullWord;
	  *opPtr += 1;
	}
	++(*icPtr);
	break;


         // write data from memory to screen
         case WRITE:
            printf("Contents of %02d: %f\n", *opPtr, memory[*opPtr]);
            ++(*icPtr);
            break; // exit switch

         // load data from memory into accumulator
         case LOAD:
            *acPtr = memory[*opPtr];
            ++(*icPtr);
            break; // exit switch

         // store data from accumulator into memory
         case STORE:
            memory[*opPtr] = *acPtr;
            ++(*icPtr);
            break; // exit switch

         // add data from memory to data in accumulator
         case ADD:
            temp = *acPtr + memory[*opPtr];
            
            // check validity
            if (!validWord(temp)) { 
               puts("*** FATAL ERROR: Accumulator overflow ***\n");
               puts("*** Simpletron execution ");
               puts("abnormally terminated ***\n");
               fatal = true;
            } 
            else { 
               *acPtr = temp;
               ++(*icPtr);
            } 
               
            break; // exit switch

         // subtract data in memory from data in accumulator
         case SUBTRACT:
            temp = *acPtr - memory[*opPtr];
            
            // check validity
            if (!validWord(temp)) { 
               puts("*** FATAL ERROR: Accumulator overflow ***\n");
               puts("*** Simpletron execution ");
               puts("abnormally terminated ***\n");
               fatal = true;
            } 
            else { 
               *acPtr = temp;
               ++(*icPtr);
            } 
               
            break; // exit switch

         // divide data in memory into data in accumulator
         case DIVIDE:

            // check for divide by zero error
            if (memory[*opPtr] == 0) { 
               puts("*** FATAL ERROR: Attempt to divide by zero ***\n");
               puts("*** Simpletron execution ");
               puts("abnormally terminated ***\n");
               fatal = true;
            } 
            else { 
               *acPtr /= memory[*opPtr];
               ++(*icPtr);
            } 
            
            break; // exit switch

         // multiply data in memory by data in accumulator
         case MULTIPLY:
            temp = *acPtr * memory[*opPtr];
            
            // check validity
            if (!validWord(temp)) { 
               puts("*** FATAL ERROR: Accumulator overflow ***\n");
               puts("*** Simpletron execution ");
               puts("abnormally terminated ***\n");
               fatal = true;
            } 
            else { 
               *acPtr = temp;
               ++(*icPtr);
            } 

            break; // exit switch

         // modulos data in memory by data in accumulator
         case REMAINDER:
	   temp = fmod(*acPtr, memory[*opPtr]);
            
            // check validity
            if (!validWord(temp)) { 
               puts("*** FATAL ERROR: Accumulator overflow ***\n");
               puts("*** Simpletron execution ");
               puts("abnormally terminated ***\n");
               fatal = true;
            } 
            else { 
               *acPtr = temp;
               ++(*icPtr);
            } 

            break; // exit switch

         // modulos data in memory by data in accumulator
         case EXPONENTIATION:
	   temp = pow(*acPtr , memory[*opPtr]);
            
            // check validity
            if (!validWord(temp)) { 
               puts("*** FATAL ERROR: Accumulator overflow ***\n");
               puts("*** Simpletron execution ");
               puts("abnormally terminated ***\n");
               fatal = true;
            } 
            else { 
               *acPtr = temp;
               ++(*icPtr);
            } 

            break; // exit switch

         // square roots data in memory
         case SQRT:
	   temp = sqrt(memory[*opPtr]);
            
            // check validity
            if (!validWord(temp)) { 
               puts("*** FATAL ERROR: Accumulator overflow ***\n");
               puts("*** Simpletron execution ");
               puts("abnormally terminated ***\n");
               fatal = true;
            } 
            else { 
               *acPtr = temp;
               ++(*icPtr);
            } 

            break; // exit switch

         // factorials data in memory
         case FACT:
	   temp = tgamma(memory[*opPtr] + 1); //gamma is factorial - 1 so just get one more
            
            // check validity
            if (!validWord(temp)) { 
               puts("*** FATAL ERROR: Accumulator overflow ***\n");
               puts("*** Simpletron execution ");
               puts("abnormally terminated ***\n");
               fatal = true;
            } 
            else { 
               *acPtr = temp;
               ++(*icPtr);
            } 

            break; // exit switch

         // branch to specific location in memory
         case BRANCH:
            *icPtr = *opPtr;
            break; // exit switch

         // branch to location in memory if accumulator is negative
         case BRANCHNEG:

            // if accumulator is negative
            if (*acPtr < 0) {
               *icPtr = *opPtr;
            } 
            else {
               ++(*icPtr);
            } 

            break; // exit switch

         // branch to location in memory if accumulator is zero
         case BRANCHZERO:

            // if accumulator is zero
            if (*acPtr == 0) {
               *icPtr = *opPtr;
            } 
            else {
               ++(*icPtr);
            } 

            break; // exit switch

         default:
            puts("*** FATAL ERROR: Invalid opcode detected ***\n");
            puts("*** Simpletron execution ");
            puts("abnormally terminated ***\n");
            fatal = true;     
            break; // exit switch 
      } 
      
      // separate next operation code and operand
       *irPtr = memory[*icPtr];
       if(*irPtr <= 9999) { //4 digits long
	 *opCodePtr = *irPtr / 100;
	 *opPtr = *irPtr % 100;
       } else { //5 digits long
	 *opCodePtr = *irPtr / 1000;
	 *opPtr = *irPtr % 1000;
       }   
   } 

   puts("\n*************END SIMPLETRON EXECUTION*************\n");
} 

// print out name and content of each register and memory
void dump(float *memory, float accumulator, size_t instructionCounter,
          int instructionRegister, int operationCode,
          int operand)
{    
   printf("\n%s\n%-23s%+05f\n%-23s%5.2u\n%-23s%+05d\n%-23s%5.2d\n%-23s%5.2d",
          "REGISTERS:", "accumulator", accumulator, "instructioncounter",
          instructionCounter, "instructionregister", instructionRegister,
          "operationcode", operationCode, "operand", operand);

   puts("\n\nMEMORY:\n   ");
   
   // print column headers
   for (unsigned int i = 0; i <= 9; ++i) {
      printf("%5u ", i);
   } 
      
   // print row headers and memory contents
   for (unsigned int i = 0; i < SIZE; ++i) { 

      // print in increments of 10
      if (i % 10 == 0) {
         printf("\n%2u ", i);
      } 
      //if number is an int, print as such
      if (ceilf(memory[i]) == (int)memory[i]) {
	printf("+%04d ", (int)memory[i]);
      } else { //print the float value to first 3 spots
      printf("%+0.3f ", memory[i]);
      }
   } 
   
   puts("");
} 

// function tests validity of word. Increased size to make our simple system better
bool validWord(int word)
{ 
   return word >= -99999 && word <= 99999;
} 



 /*************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
