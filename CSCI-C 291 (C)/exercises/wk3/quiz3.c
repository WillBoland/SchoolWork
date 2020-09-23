//Will Boland
//11/4/2019

/* Instructions:
 *
 *
 1. Write a program to assign a grade to Student. 
	Get the number of assignmnets from user.
  Get the students score out of how many points for each assignmnet.
	Calculate percentage and return the grade associated with a range of marks.

 2. The overall score range and the associated grades are as follows:
     a. [97-100] = A+
     b. [90-97] = A
     c. [80-90] = B
     d. [70-80] = C
     e. less than 70 is a failing grade and should be given an F
     e. greater than 100 or less than 0 is invalid and your code should print "Invalid"

 3. Use the skeleton code provided below to create a new function "printGrade" to assign a grade based on the score ranges.

 4. Use print statements in your function to display the grade. For example, if the input marks is 77, the output should read:
        "Your grade is : C"

 5. Please adhere to standard C syntax conventions and use comments to explain your code. Failure to do so will result in a penalty.

 *
 *
 * */

#include <stdio.h>

void printGrade(int);

int main(void){
  char ch; //Yes or No

  //calculate scores and display until user doesn't want to continue
  do {
    float score = 0;		//the score the user actually has
    float maxScore = 0;		//the max possible score
    int assignments = 0;	//the number of assignments

    //Ask for number of assignments
    printf("How many assignmnets did you have? ");
    scanf(" %d", &assignments);
    
    //get user's score and max score for the assignment to be used in calculation
    int count;
    for(count = 0; count < assignments; count++) {
      float tempScore;

      printf("Please enter the score the student recieved for assignment %d: ", count + 1);
      scanf(" %f", &tempScore);
      score += tempScore;

      printf("Please enter the possible maximum score for assignment %d: ", count + 1);
      scanf(" %f", &tempScore);
      maxScore += tempScore;
    }

    //calculate the student's percentage in the class using the information you've gathered.
    int percent = (score/maxScore) * 100;
    printGrade(percent);

    //asks if the user wants to continue. Anything other than Y or y ends execution
    printf(" \n Do you want to continue? (Y/N) ");
    scanf(" %c",&ch);
  } while(ch == 'y'|| ch == 'Y');
}


//print the students grade based on their score in the class
void printGrade(int score){
  if(score < 0 || score > 100) { printf("Invalid\n"); return; }

  if(score >= 97) { printf("Your grade is: A+\n"); }
  else if(score >= 90) { printf("Your grade is: A\n"); }
  else if(score >= 80) { printf("Your grade is: B\n"); }
  else if(score >= 70) { printf("Your grade is: C\n"); }
  else { printf("Your grade is: F\n"); }
}
