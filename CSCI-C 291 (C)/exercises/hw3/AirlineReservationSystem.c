//Will Boland
//11/5/2019

/*
* Provides an interface for an Airline Reservation System using the terminal.
*/

#include <stdio.h>
#include "Plane.h" //my own header file. Used for getting stats of the plane as well as changing stats.

//MARK: Variables
const unsigned int FIRST_CLASS = 1;        //Option for first class
const unsigned int BUSINESS_CLASS = 2;     //Option for business class
const unsigned int ECONOMY_CLASS = 3;      //Option for economy class
const unsigned int SEATING_CHART = 4;      //Option to print the seating chart
const unsigned int PASSENGER_MANIFEST = 5; //Option to print the passenger manifest
const unsigned int BOARDING_PASS = 6;      //Option to find a boarding pass
const unsigned int EXIT = 9;               //Option to exit the program


//MARK: Function prototypes
void assignSeatForSection(int section); //assigns a seat and asks for name for a given section


int main(void) {
  int option;
  
  //MARK: Start of Logic
  srand(time(0)); //for random seed

  printf("Welcome to the Airline Reservation System.\n\nPlease type 1 for \"First Class\".\nPlease type 2 for \"Business Class\".\nPlease type 3 for \"Economy Class\".\n");
  printf("Please type 4 to display the seating chart.\nPlease type 5 to display the passenger manifest.\nPlease type 6 to find a boarding pass.\nType 9 to quit.\n\nOption: ");
  scanf(" %d", &option);

  while(option != EXIT) {

    //Decided to sit in first class
    if(option == FIRST_CLASS) {
      if(isFirstClassAvailable()) { //if seat available, assign
        assignSeatForSection(FIRST_CLASS);
      } else { //if all first class seats are taken, ask user if business is okay
        if(isBusinessClassAvailable() == 0 && isEconomyClassAvailable() == 0) {
          printf("Sorry, the plane is full. Next plane is in 3 hours\n");
        } else {
          printf("Sorry, all seats in First Class are taken. Is business class acceptable? (Y/N): ");
          char response;
          scanf(" %c", &response);
          (toupper(response) == 'Y') ? option = BUSINESS_CLASS : printf("Next flight leaves in 3 hours.\n");
        }
      }
    }
  
    //decided to sit in business class
    if(option == BUSINESS_CLASS) {
      if(isBusinessClassAvailable()) {
        assignSeatForSection(BUSINESS_CLASS);
      } else { //no business class seats are available
        if(isFirstClassAvailable() == 0 && isEconomyClassAvailable() == 0) {
          printf("Sorry, the plane is full. Next plane is in 3 hours.\n");
        } else {        
          char response;
          printf("Sorry, all seats in Business Class are taken. ");
          if(isFirstClassAvailable()) {
            printf("Would you like to upgrade to First Class? (Y, N): "); 
            scanf(" %c", &response);
            if(toupper(response) == 'Y') { option = FIRST_CLASS; continue; }
          }
          printf("Is Economy class acceptable? (Y/N): ");
          scanf(" %c", &response);
          (toupper(response) == 'Y') ? option = ECONOMY_CLASS : printf("Next flight leaves in 3 hours.\n");
        }
      }
    }

    //decided to sit in Economy class
    if(option == ECONOMY_CLASS) {
      if(isEconomyClassAvailable()) { //if seat available, assign
        assignSeatForSection(ECONOMY_CLASS);
      } else { //if all economy class seats are taken, ask user if they want to upgrade to business class
        if(isBusinessClassAvailable() == 0 && isFirstClassAvailable() == 0) {
          printf("Sorry, the plane is full. Next plane is in 3 hours.\n"); //plane is full
        } else {        
          printf("Sorry, all seats in Economy Class are taken. Do you want to upgrade to Business Class? (Y/N): ");
          char response;
          scanf(" %c", &response);
          if(toupper(response) == 'Y') { option = BUSINESS_CLASS; continue; }
          printf("Next flight leaves in 3 hours.\n");
        }
      }
    }

    //chose to display the seating chart
    if(option == SEATING_CHART) {
      printSeatChart();
    }
    //chose to display the passenger manifest
    if(option == PASSENGER_MANIFEST) {
      printPassengerManifest();
    }

    //chose to select a boarding pass
    if(option == BOARDING_PASS) {
      int row;
      char aisle;
      printf("Please enter the row (1-12): ");
      scanf(" %d", &row);
      printf("Please enter the aisle (A-F) ");
      scanf(" %c", &aisle);

      while(isSeatAvailable(seatAisleToNumber(row, aisle))) {
        printf("Seat Unassigned - No Boarding Pass Available. Try Again!\nPlease enter the row (1-12): ");
        scanf(" %d", &row);
        printf("Please enter the aisle (A-F) ");
        scanf(" %c", &aisle);
      }
      printf("\033[1;36m"); //Green
      printf("\n\nNAME: "); passengerNameForSeat(seatAisleToNumber(row, aisle));
      printf("SEAT: %d%c\nLEVEL: ", row, toupper(aisle));

      if(row < 3)
        printf("First Class");
      else if (row < 6)
        printf("Business Class");
      else
        printf("Economy Class");
      printf("\033[0m"); //reset to white
    }

    printf("\n\nPlease type 1 for \"First Class\".\nPlease type 2 for \"Business Class\".\nPlease type 3 for \"Economy Class\".\n");
    printf("Please type 4 to display the seating chart.\nPlease type 5 to display the passenger manifest.\nPlease type 6 to find a boarding pass.\nType 9 to quit.\n\nOption: ");
    scanf(" %d", &option);
  }
  return 0;
}

//MARK: Function Definitions
void assignSeatForSection(int section) {
  int difference, starting, seat;
  char name[40];
  
  if(section == FIRST_CLASS) { difference = 12; starting = 1; }
  if(section == BUSINESS_CLASS) { difference = 18; starting = 13; }
  if(section == ECONOMY_CLASS) { difference = 42; starting = 31; }

  printf("Please enter your name: ");
  getchar(); fgets(name, 40, stdin); //removes newline then reads input
  while(!isSeatAvailable((seat = rand() % difference + starting))); //randomly assigns a seat
  assignSeat(seat, name);
  printf("\033[1;36m");
  printf("\nYour seat is "); printNumberToSeatAisle(seat); //Tells the user their seat number
  printf("\033[0m");
}
