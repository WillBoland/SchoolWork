/* Month 2 usually has 28 days except when it is leap year*/
/* Every year that is exactly divisible by four is a leap year, except for years that are exactly divisible by 100 */
/* But these centurial years are leap years if they are exactly divisible by 400 */
/* For example, the years 1700, 1800, and 1900 were not leap years, but the years 1600 and 2000 were*/
/* Based on these facts, evaluate the eneterd day,month,year is valid or invalid year/month/day */

//Will Boland

#include<stdio.h>
int main()
{
   int birthMo;
   int birthDay;
   int birthYr;
   const int HIGHMO = 12;
   const int LOMO   = 1;
   const int LODAY  = 1;
   const int HIDAY1 = 31;
   const int HIDAY2 = 30;
   const int HIDAY3 = 29;
   const int HIDAY4 = 28;
   const int LOYEAR = 1885;
   const int HIYEAR = 2004;
   printf( "Enter your birth month ");
   scanf(" %d", &birthMo);
   printf( "Enter your birth day ");
   scanf(" %d", &birthDay);
   printf( "Enter your birth year ");
   scanf("%d", &birthYr);


   //check year
    if(birthYr < LOYEAR){
      printf( "No one is that old\n");
    }else if(birthYr > HIYEAR){
      printf( "You are too little to be using a computer\n");
    }else{
      printf( "Valid year\n");
    }   
    //check month
    if(birthMo > HIGHMO || birthMo < LOMO) {
      printf("Invalid month\n");
    } else {
      printf("Valid month\n");
    }
    //check day
    if(birthDay < LODAY || birthDay > HIDAY1) {
      printf("Invalid day for all months\n");
    } else if(birthMo == 1 || birthMo == 3 || birthMo == 5 || birthMo == 7 || birthMo == 8 || birthMo == 10 || birthMo == 12) { //months that have 31 days ALWAYS
      printf("Valid day\n");
    } else if(birthMo == 4 || birthMo == 6 || birthMo == 9 || birthMo == 11) { //months that have 30 days ALWAYS
      (birthDay > HIDAY2) ? printf("Invalid day for month %d in %d\n", birthMo, birthYr ) : printf("Valid day\n");
    } else { //month 2
      if(birthYr % 4 == 0 && (birthYr % 100 != 0 || birthYr % 400 == 0)) { //it IS a leap year, so month 2 has 29 days
        (birthDay <= HIDAY3) ? printf("Valid day\n") : printf("Invalid day for month %d in %d\n", birthMo, birthYr);
      } else { //NOT a leap year
        (birthDay <= HIDAY4) ? printf("Valid day\n") : printf("Invalid day for month %d in %d\n", birthMo, birthYr);        
      }
    }
    return 0;
}
