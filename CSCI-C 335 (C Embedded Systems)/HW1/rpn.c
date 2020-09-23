//Will Boland
//01/25/2022

/*
 * RPN Calculator
 * This calculator converts Infix Notation into Reverse Polish Notation (RPN)
 * Please note that this calculator DOES NOT handle parentheses, as outlined in the assignment
 */

//--------------------
//MARK: Include Statements
#include <stdio.h>
#include <stdlib.h>

//--------------------
//MARK: Type Defs
typedef struct CELL* LIST;

//--------------------
//MARK: Externs and Globals
static LIST stack;
static int* size;

//--------------------
//MARK: Struct Definitions
//----------------------------------------------------------------------------
struct CELL {
  int val;
  LIST next;
};

//--------------------
//MARK: Function Prototypes
void push(int val);
int pop(void);
void printStack(LIST list);
void handleOperand(char* input);
void handleCommand(char* input);
int isOperand(char* input);
int isCommand(char* input);

//--------------------
//MARK: Main
//----------------------------------------------------------------------------
int main(void) {
  stack = (LIST) malloc(sizeof(struct CELL));
  stack->val = -99999;
  stack->next = NULL;

  size = (int*)malloc(sizeof(int));
  *size = 0;

  char* input = (char*)malloc(sizeof(char));

  //not eof, so continue execution
  while((scanf("%s", input)) != EOF) {
    //is a digit!
    if(isdigit(input[0])) {
      push(atoi(input));
    } else {    
      //q, so quit
      if(input[0] == 'q') {
        break;
      }

      //performs command if it is a command
      if(isCommand(input)) {
        handleCommand(input);
      }
      //handles operand if it is an operand
      if(isOperand(input)) {
        handleOperand(input);
      }
    }
  }

  //free memory at end of execution
  free(input);
  free(size);
  while(pop() != -99999);
  return 0;
}


//--------------------
//MARK: Function Definitions
//----------------------------------------------------------------------------
/*
 * Pushes a new element to the stack
 * - Parameter val: the value to push to the stack
 */
void push(int val) {
  LIST temp = (LIST) malloc(sizeof(struct CELL));
  if(temp) {
    *size += 1;
    temp->val = val;
    temp->next = stack;
    stack = temp;
  } else { //error; not enough memory
    printf("Not enough memory to push to stack\n");
  }
}

//----------------------------------------------------------------------------
/*
 * Returns the value at the top of stack and deallocates the cell
 * - Return: the value at top of stack; -99999 if stack is empty
 */
int pop(void) {
  int value = -99999;
  //stack is empty
  if(stack == NULL) {
    return value;
  } else {
    *size -= 1;
    LIST start = stack;
    value = stack->val;
    stack = start->next;
    free(start);
  }
  return value;
}

//----------------------------------------------------------------------------
/*
 * Prints the entire stack without modifying the elements
 */
void printStack(LIST list) {
  LIST rest = stack;
  unsigned int counter;
  for(counter = 0; counter < *size; counter += 1) {
    printf("%d\n", rest->val);
    rest = rest->next;
  }
}

//----------------------------------------------------------------------------
/*
 * handles operator [+/-*]
 */
void handleOperand(char* operator) {
  if(*size <= 1) {
    printf("stack empty\n");
    return;
  }

  if(operator[0] == '+') {
    push(pop() + pop());
  }

  if(operator[0] == '-') {
    int first = pop();
    int second = pop();
    push(second - first);
  }

  if(operator[0] == '*') {
    push(pop() * pop());
  }

  if(operator[0] == '/') {
    int first = pop();
    int second = pop();
    push(second / first);
  }

}

//----------------------------------------------------------------------------
/*
 * Handles commands [p, f, c, a, m, d]
 */
void handleCommand(char* input) {
  //p, prints the top element
  if(input[0] == 'p') {
    if(stack->val != -99999) {
      printf("%d\n", stack->val);
    } else {
      printf("stack empty\n");
    }
  }

  //f, prints entire contents
  if(input[0] == 'f') {
    printStack(stack);
  }

  //c, clears the stack completely
  if(input[0] == 'c') {
    while(stack->val != -99999) {
      pop();
    }
  }

  //a, replace the top element with the absolute value
  if(input[0] == 'a') {
    if(stack->val != -99999) {
      push(abs(pop()));
    } else {
      printf("stack empty\n");
    }
  }
    
  //m, unary minus on top element
  if(input[0] == 'm') {
    if(stack->val != -99999) {
      stack->val = -(stack->val);
    } else {
      printf("stack empty\n");
    }
  }
    
  //d, duplicates top stack element
  if(input[0] == 'd') {
    if(stack->val != -99999) {
      push(stack->val);
    } else {
      printf("stack empty\n");
    }
  }
}

//----------------------------------------------------------------------------
/*
 * returns whether a specific input is an operand or not
 */
int isOperand(char* input) {
  char op = input[0];
  return (op == '+' || op == '-' || op == '*' || op == '/');
}

//----------------------------------------------------------------------------
/*
 * returns whether a specific input is a command or not
 */
int isCommand(char* input) {
  char op = input[0];
  return (op == 'p' || op == 'f' || op == 'c' || op == 'a' || op == 'm' || op == 'd');
}
