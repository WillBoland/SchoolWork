//Will Boland
//11/2/2019

//MARK: Include Statements
#include <stdio.h>
#include "HealthData.h"

#define UNDERWEIGHT 18.5
#define OBESE 30

//MARK: Start of Cerner Helth Program
int main(void) {
  //for entering into the parser
  char firstName[20];
  char lastName[20];
  char gender[20];
  struct Date dob = {0, 0, 0};
  int height, weight;
  struct FamilyHistoryIssues history = {0, 0, 0};

  //ask for user input to make the healthprofile for them
  printf("\n********************************\n");
  printf("Welcome to Cerner Health Program\n");
  printf("********************************\n");

  printf("\nPlease enter your first name: ");
  fgets(firstName, 20, stdin);
  
  printf("Please enter your last name: ");
  fgets(lastName, 20, stdin);

  printf("Please enter your gender: ");
  fgets(gender, 20, stdin);

  printf("Please enter the day you were born: ");
  scanf(" %d", &(dob.day));

  printf("Please enter the month you were born: ");
  scanf(" %d", &(dob.month));

  printf("Please enter the year you were born: ");
  scanf(" %d", &(dob.year));

  printf("Please enter your height in inches: ");
  scanf(" %d", &height);

  printf("Please enter your weight in pounds: ");
  scanf(" %d", &weight);

  printf("Has anyone in your family had heart issues (0 or 1): ");
  scanf(" %d", &(history.heart));

  printf("Has anyone in your family had blood pressure issues (0 or 1): ");
  scanf(" %d", &(history.bloodPressure));

  printf("Has anyone in your family had caner (0 or 1): ");
  scanf(" %d", &(history.cancer));

  //Makes the health profile now that we have all the data
  struct HealthProfile profile = parseData(firstName, lastName, gender, &height, &weight, &dob, &history);
  
  int ageYears = age(profile.dateOfBirth); //for ease of use
  float* target = targetHeartRate(ageYears); //for readability
  float bmiCurrent = bmi(&profile); //for ease of use
  
  //print first name
  printf("Your first name is %s", profile.firstName);
  
  //print last name
  printf("Your last name is %s", profile.lastName);

  //print gender
  printf("Your gender is %s", profile.gender);

  //print date of birth
  printf("Your date-of-birth is: %d/%d/%d\n", profile.dateOfBirth->month, profile.dateOfBirth->day, profile.dateOfBirth->year);

  //print height
  printf("Your height in inches is %d\n", (*profile.height));
  
  //print weight
  printf("Your weight in pounds is %d\n", *(profile.weight));

  //prints family history
  (profile.familyHistory->heart) ? printf("Your family has had heart issues.\n") : printf("Your family has not had heart issues.\n") ;
  (profile.familyHistory->bloodPressure) ? printf("Your family has had blood pressure issues.\n") : printf("Your family has not had blood pressure issues.\n");
  (profile.familyHistory->cancer) ? printf("Your family has had cancer issues.\n\n") : printf("Your family has not had cancer issues.\n\n");


  
  //prints the age in years
  printf("\nYou are %d %s old.\n", ageYears, ageYears == 1 ? "year" : "years");
  
  //prints their BMI
  printf("Your BMI is %.2f.\n", bmiCurrent);

  //prints their max heart rate
  printf("Your max heart rate is: %d\n", maxHeartRate(ageYears));

  //prints their heart range
  printf("Your target-heart-rate range is: %.2f to %.2f.\n", target[0], target[1]);
  
  //advising to see physician (maybe)
  if(profile.familyHistory->heart || profile.familyHistory->bloodPressure)
    printf("You should see your physician!!!\n");
  
  //possible advising to see oncologist
  if((bmiCurrent < UNDERWEIGHT || bmiCurrent >= OBESE) && profile.familyHistory->cancer)
    printf("You should see your oncologist!!!\n");

  return 1;
}
