//Will Boland
//11/5/2019

/*
 * Provides function prototypes and constants for the plane's statistics
 */

//MARK: Variables
extern const unsigned int NUMBER_SEAT;            //Total Number of Seats the Plane Has for Passengers
extern const unsigned int NUMBER_SEATS_PER_ROW;   //The Number of Seats per Row
extern const unsigned int NUMBER_ROWS;            //The Number of Rows in The Plane
extern const char SEAT_LABELS[6];                 //The seat labels per row ('A', 'B', etc.)

unsigned int seatChart[12][6];  //The seating chart, divided into 12 rows with 6 columns ('A', 'B', etc.)
char passengerNames[72][40]; //The passenger names. Names can have size < 40. Organized similiar to seatChart.

//MARK: Function Prototypes
void printSeatChart(void);  //Prints out the seating chart for the plane, color coded by options
void printPassengerManifest(void); //Prints out the passenger manifest in a Tabular format
void printNumberToSeatAisle(int number); //Prints '2B', etc. given a # on the plane. EX: 1 would print 1A

void assignSeat(int seatNumber, char name[40]); //Assigns a seat as taken. Use (isSeatAvailable) to ensure seat is available! Also takes name.
int isSeatAvailable(int seatNumber); //returns 0 if taken, 1 if available

int isFirstClassAvailable(); //returns 0 if false, 1 if true
int isBusinessClassAvailable(); //returns 0 if false, 1 if true
int isEconomyClassAvailable(); //returns 0 if false, 1 if true

int seatAisleToNumber(int row, char aisle); //returns the seatAisle combo as a the number seat. EX: 1A is 1

int passengerNameForSeat(int seatNumber); //prints the name for passenger in a given seat. 0 if null, 1 if success
