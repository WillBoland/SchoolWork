//Will Boland
//11/20/2019

//This program includes a struct called cat that is used to store information about a given cat
//The program should ask the user for information about their cat and initialize a cat structure with the given information
//The program should also print the information using a pointer to the initialized struct
//The information printed using the struct and the pointer should be the same

#include <stdio.h>
#include <string.h>

struct Cat {
  char name[20];
  int age;
  float speed;
};

int main(void){
  char name[20];
  int age = 0;
  float speed = 0.0;

  //gets cat name
  printf("What is your favorite cat's name: ");
  fgets(name, 20, stdin);
  name[strlen(name) - 1] = '\0'; 

  //gets cat age
  printf("How old is it: " );
  scanf(" %d", &age);

  //gets speed of cat
  printf("How much speed does it give: ");
  scanf(" %f", &speed);

  //sets struct params
  struct Cat cat;
  strcpy(cat.name, name); cat.age = age; cat.speed = speed;
  struct Cat* catPtr = &cat;
  
  printf("Cat's name is %s, and should be the same as %s.\n", cat.name, (*catPtr).name);
  printf("Cat's age is %d, and should be the same as %d.\n", cat.age, (*catPtr).age);
  printf("Cat's speed is %f, and should be the same as %f.\n", cat.speed , (*catPtr).speed);

  return 1;
}
