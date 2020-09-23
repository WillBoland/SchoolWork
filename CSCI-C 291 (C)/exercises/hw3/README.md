# Psuedo Code for ARS
## Will Boland 11/5/2019
## Resources  
**Coloring output resource:** http://web.theurbanpenguin.com/adding-color-to-your-output-from-c/  
**Random Number resource:** https://www.geeksforgeeks.org/rand-and-srand-in-ccpp/  
## General Overview
Files:
 - AirlineReservationSystem.c
 - Plane.h
 - Plane.c
  
**AirlineReservationSystem:** Provides logic for the reservation system, and allows IO for the system.  
**Plane.h:** Provides function prototypes and const's for the Plane used. Has information regarding the plane, as well as interactions for seats and maps.  
**Plane.c:** Provides function definitions and const's for Plane.h.  
  
## AirlineReservationSystem.c  
Include statements  
Variables   
main() {  
 - print commands and get input  
 - handle first class  
 - handle business class  
 - handle economy class  
 - handle printing option  
 - handle manifest option  
 - handle boarding pass option
}  
  
helper function to assign seats randomly  
  
## Plane.h  
Constant Variables  
Function Prototypes  
  
## Plane.c  
Constant Variable Assignments  
 - number of seats  
 - number of seats per row  
 - number of rows  
 - seat labels  
  
Function Definitions:  
 - printing functions (seat chart, mainfest, etc.)  
 - assign a seat (NOT RANDOM HERE. Random is done in Airline Reservation System!!!)  
 - check if seat is available  
 - check if sections available (first class, business, economy)  
 - other misc. functions