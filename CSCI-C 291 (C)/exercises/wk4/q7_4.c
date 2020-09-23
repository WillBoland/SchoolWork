//Will Boland
//11/20/2019

//This program is supposed to scan 5 ints from the user
//Using those 5 ints, it should construct a linked list of 5 elements
//Then it prints the elements of the list using the PrintList function

#include <stdio.h>
#include <stdlib.h>

//struct Node
struct Node {
  int data;
  struct Node* next;
};

//function prototypes
void PrintList(struct Node* n);

//main
int main(void) {
  //helper
  int size = sizeof(struct Node);
  //inits pointer addresses
  struct Node* first = (struct Node*)malloc(size);
  struct Node* second = (struct Node*)malloc(size);
  struct Node* third = (struct Node*)malloc(size);
  struct Node* fourth = (struct Node*)malloc(size);
  struct Node* fifth = (struct Node*)malloc(size);

  int i;

  printf("Please enter 1st number: ");
  scanf(" %d", &i);
  first->data = i;

  printf("Please enter 2nd number: ");
  scanf(" %d", &i);
  second->data = i;
  first->next = second;

  printf("Please enter 3rd number: ");
  scanf(" %d", &i);
  third->data = i;
  second->next = third;

  printf("Please enter 4th number: ");
  scanf(" %d", &i);
  fourth->data = i;
  third->next = fourth;

  printf("Please enter 5th number: ");
  scanf(" %d", &i);
  fifth->data = i;
  fourth->next = fifth;
  fifth->next = NULL;

  PrintList(first);
}

void PrintList(struct Node* n) {
  while(n != NULL) {
    printf("%d \n", n->data);
    n = n->next;
    }
}
