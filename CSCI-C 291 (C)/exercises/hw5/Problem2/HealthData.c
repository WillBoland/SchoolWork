//Will Boland
//11/2/2019

//MARK: Include Statements
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "HealthData.h"

//MARK: Function Definitions
//Returns a pointer to a health profile with the given data
struct HealthProfile parseData(char* firstName, char* lastName, char* gender, int* height, int* weight, struct Date* dateOfBirth, struct FamilyHistoryIssues* familyHistory) {
  struct HealthProfile member = {firstName, lastName, gender, height, weight, dateOfBirth, familyHistory};
  return member;
}

//returns the age in years from a given date
int age(struct Date* fromDate) {
  return 2019 - fromDate->year;
}

//returns the maximum heart rate given their age
int maxHeartRate(int age) {
  return (200 - age);
}

//returns a pointer to an array that contains 2 values, the lower bound and upper bound the the target heart rate given their age
float* targetHeartRate(int age) {
  float* target = (float*)malloc(sizeof(float)*2);
  target[0] = maxHeartRate(age) * 0.55;
  target[1] = maxHeartRate(age) * 0.75;
  return target;
}

//returns the BMI given a pointer to a HealthProfile
float bmi(struct HealthProfile* profile) {
  printf("\nBMI VALUES\nUnderweight:\tless than 18.5\nNormal:\t\tbetween 18.5 amd 24.9\nOverweight:\tbetween 25 and 29.9\nObese\t30 or greater\n\n");
  float weight = (float)*(profile->weight);
  float height = (float)*(profile->height);
  return (weight * 703.0)/(height * height);
}
