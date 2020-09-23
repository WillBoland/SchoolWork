//Will Boland
//11/5/2019

/*
 * Includes function definitions and constant variable definitions for the plane
 */

#include "Plane.h"
#include <stdio.h>
#include <string.h>

//-------------------- 
//MARK: Variables
const unsigned int NUMBER_SEATS = 72;
const unsigned int NUMBER_SEATS_PER_ROW = 6;
const unsigned int NUMBER_ROWS = 12;
const char SEAT_LABELS[6] = {'A', 'B', 'C', 'D', 'E', 'F'};

unsigned int seatChart[12][6] = {{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}}; // All seats are initially open. 0 = open, 1 = filled
char passengerNames[72][40] = {""}; //the passenger names organized by seat

//--------------------
//MARK: Function Definitions
//----------------------------------------------------------------------------
/*
 * Prints out the seating chart for the plane, using 0 as empty and 1 as taken. Green is first class, Magenta is business, Economy is white
 */
void printSeatChart() {
  printf("\n\n\t   A B C  D E F\n\t   - - -  - - - \n");
  
  int row, column;
  for(row = 0; row < NUMBER_ROWS; row++) {
    printf("\033[0m"); //reset color to white
    printf("Row %d\t|  ", row + 1);
    for(column = 0; column <  NUMBER_SEATS_PER_ROW; column++) {
      if(row == 0 || row == 1) { printf("\033[0;32m"); } //first class color is green
      else if (row == 2 || row == 3 || row == 4) { printf("\033[0;35m"); } //business class is magenta
      (column == 2) ? printf("%d  ", seatChart[row][column]) : printf("%d ", seatChart[row][column]); //put spaces between column C and D for isle
    }
    printf("\n");
  }
  printf("\n\n");
}

//----------------------------------------------------------------------------
/*
 * Prints out the passenger manifest in a tabular format.
 */
void printPassengerManifest() {
  printf("\033[1;33m"); //Bold Yellow
  printf("\n\nPassenger Manifest\n-----------------\n");
  printf("\033[0m"); //reset color to white
  int seatNumber;
  for(seatNumber = 0; seatNumber < NUMBER_SEATS; seatNumber++) {
    if(seatNumber + 1 <= 12) { printf("\033[0;32m"); } else if(seatNumber + 1 <= 30) { printf("\033[0;35m"); } else { printf("\033[0m"); }
    if(isSeatAvailable(seatNumber + 1) == 0) {
      printNumberToSeatAisle(seatNumber + 1);
	printf(" %s", passengerNames[seatNumber]);
    }
  }
  printf("\033[1;33m");  //Bold Yellow
  printf("-----------------");
  printf("\033[0m"); //reset color to white 
}

//----------------------------------------------------------------------------
/*
 * Assigns the given seat, regardless of seat availablility. Puts name into passenger names at corresponding place.
 */
void assignSeat(int seatNumber, char name[40]) {
  seatChart[0][seatNumber - 1] = 1; //Seat is now filled
  strcpy(passengerNames[seatNumber - 1], name); //Fills corrosponding name list
}

//----------------------------------------------------------------------------
/*
 * Checks if a seat is available. Returns 0 if false, 1 if true
 */
int isSeatAvailable(int seatNumber) {
  if(seatChart[0][seatNumber - 1] == 0) { return 1; }
  return 0;
}

//----------------------------------------------------------------------------
/*
 * Checks if first class is not full. Returns 0 if full, 1 if not full
 */
int isFirstClassAvailable() {
  int seat;
  for(seat = 1; seat <= 12; seat++) {
    if(isSeatAvailable(seat))
      return 1;
  }
  return 0;
}

//----------------------------------------------------------------------------
/*
 * Checks if business class is not full. returns 0 if full, 1 if not full
 */
int isBusinessClassAvailable() {
  int seat;
  for(seat = 13; seat <= 30; seat++) {
    if(isSeatAvailable(seat))
      return 1;
  }
  return 0;
}

//----------------------------------------------------------------------------
/*
 * Checks if economy class is not full. Returns 0 if full, 1 if not full 
 */
int isEconomyClassAvailable() {
  int seat;
  for(seat = 31; seat <= NUMBER_SEATS; seat++) {
    if(isSeatAvailable(seat))
      return 1;
  }
  return 0;
}

//----------------------------------------------------------------------------
/*
 * Takes a seat (such as '12A' and returns the corresponding seat number out of 72. EX: '1A' is seat 1. Returns -1 if not a valid seat number
 */
int seatAisleToNumber(int row, char aisle) {
  int aisleAsNumber = 0;
  switch(toupper(aisle)) {
    case 'B': aisleAsNumber = 1; break;
    case 'C': aisleAsNumber = 2; break;
    case 'D': aisleAsNumber = 3; break;
    case 'E': aisleAsNumber = 4; break;
    case 'F': aisleAsNumber = 5; break;
    default: break;
  }

  if(row == 1) { return 1 + aisleAsNumber; }
  if(row == 2) { return 7 + aisleAsNumber; }
  if(row == 3) { return 13 + aisleAsNumber; }
  if(row == 4) { return 19 + aisleAsNumber; }
  if(row == 5) { return 25 + aisleAsNumber; }
  if(row == 6) { return 31 + aisleAsNumber; }
  if(row == 7) { return 37 + aisleAsNumber; }
  if(row == 8) { return 43 + aisleAsNumber; }
  if(row == 9) { return 49 + aisleAsNumber; }
  if(row == 10) { return 55 + aisleAsNumber; }
  if(row == 11) { return 61 + aisleAsNumber; }
  if(row == 12) { return 67 + aisleAsNumber; }

  return -1;
}

//----------------------------------------------------------------------------
/*
 * Takes a number, such as 23 and prints the corresponding SeatAisle combo. EX:  number 1 is '1A'
 */
void printNumberToSeatAisle(int number) {
  if(number >= 67) { printf("12"); }
  else if(number >= 61) { printf("11"); }
  else if(number >= 55) { printf("10"); }
  else if(number >= 49) { printf("9"); }
  else if(number >= 43) { printf("8"); }
  else if(number >= 37) { printf("7"); }
  else if(number >= 31) { printf("6"); }
  else if(number >= 25) { printf("5"); }
  else if(number >= 19) { printf("4"); }
  else if(number >= 13) { printf("3"); }
  else if(number >= 7) { printf("2"); }
  else if(number >= 1) { printf("1"); }

  //prints the char for the aisle
  (number % 6 == 0) ? printf("F ") : printf("%c ", 64 + (number % 6));
}

//----------------------------------------------------------------------------
/*
 * prints the passenger name for the given seat.
 */
int passengerNameForSeat(int seatNumber) {
  printf("%s", passengerNames[seatNumber - 1]);
}
