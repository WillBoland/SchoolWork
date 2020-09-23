//Homework 2, Question 1
//Will Boland
//11/2/2019

/*
* NOTE: While it did not exactly follow the PsuedoCode.txt file, that lays the ground work. Things come up
* during the devlopment that make it neccessary to alter the .c and not the psuedo code, such as edge cases.
*/


/*
* This program will control the number of people allowed to enter/exit in a room.
* It accepts user input (+ or -) to either add a group or remove a group.
* If adding, only 149 people may be in room at a time.
*/

#include <stdio.h>

int main(void) {
  
  //MARK: Variables
  const int MAX_OCCUPANCY = 149; //maximum number of people allowed in the room
  const int NUM_GUARDS = 5; //the number of guards ALWAYS in the room
  unsigned int consecutiveGroups = 0; //the number of consecutive groups who were unable to enter
  unsigned int currentlyInRoom = NUM_GUARDS; //number of people currently in room, with 5 security guards

  //MARK: Room Logic
  while(consecutiveGroups < 2) { //continue until 2 groups are unable to enter any # of people
    
    //ask for group size
    printf("\n\n\nEnter the number entering/departing the room (use - to depart): \n");
    int groupSize;
    scanf("%d", &groupSize);

    //if noone is in the group, go back to the top of the loop
    if(groupSize == 0) {
      printf("Group size of 0 is not valid.\n");
      continue;
    }

    //if group is trying to enter
    if(groupSize > 0) {
      if(currentlyInRoom == MAX_OCCUPANCY) { //room is full
        printf("Room is full. No entry allowed until people leave the room.\n");
        consecutiveGroups += 1;
      } else { //room is not full, add full or partial group
        currentlyInRoom = (currentlyInRoom + groupSize >= MAX_OCCUPANCY) ? MAX_OCCUPANCY  : currentlyInRoom + groupSize;
        consecutiveGroups = 0;
        printf("The group entered the room.\n");
      }
    }

    //if group is departing the room
    if(groupSize < 0) {
      if(-groupSize > currentlyInRoom - NUM_GUARDS) { //departing number too large
        printf("The group size trying to leave exceeds the number of people in the room.\n");
      } else { //remove from room
        currentlyInRoom -= -groupSize;
        printf("The group left the room.\n");
      }
    }
 

   //check to see if room is full
    if(currentlyInRoom == MAX_OCCUPANCY) {
      printf("The room is full. There are %d of %d in the room.\n", currentlyInRoom, MAX_OCCUPANCY);
    } else {
      printf("There are %d of %d people in the room.\n", currentlyInRoom, MAX_OCCUPANCY);
    }
  }

  return 0;
}
