//Will Boland
//11/2/2019

//MARK: Include Statements
#include <stdbool.h>
#include <stdio.h>
#include <string.h>

//MARK: Struct Definitions
//Allows the creation of a Health Profile, similiar to Cerner or Epic
struct HealthProfile {
  char* firstName;
  char* lastName;
  char* gender;
  int* height; //inches
  int* weight; //pounds

  struct Date* dateOfBirth;
  struct FamilyHistoryIssues* familyHistory;
};

//Allows the creation of dates in the format day month year during initialization
struct Date {
  int day;
  int month;
  int year;
};

//Allows the creation of a family history containing of booleans for certain questions
struct FamilyHistoryIssues {
  int heart;
  int bloodPressure;
  int cancer;
};

//MARK: Function Prototypes
//Returns a health profile with the given data
struct HealthProfile parseData(char* firstName, char* lastName, char* gender, int* height, int* weight, struct Date* dateOfBirth, struct FamilyHistoryIssues* familyHistory);
//returns the age in years from a given date
int age(struct Date* fromDate);
//returns the maximum heart rate given their age
int maxHeartRate(int age);
//returns a pointer to an array that contains 2 values, the lower bound and upper bound the the target heart rate given their age
float* targetHeartRate(int age);
//returns the BMI given a pointer to a HealthProfile
float bmi(struct HealthProfile* profile);
