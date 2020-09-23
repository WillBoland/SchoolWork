//Will Boland
//11/29/2019

//MARK: Include Statements
#include <stdio.h>
#include <string.h>

int main(void) {
  //MARK: Struct Definitions
  struct faculty {
    char lastName[20];
    char firstName[20];
    char* sis_username;
    unsigned int sis_id_number;
    struct contact_information {
      char phoneNumber[11];
      char address[60];
      char city[20];
      char state[3];
      char zipCode[6];
    } personal_address;
  } facultyRecord, *facultyPtr;
  facultyPtr = &facultyRecord;

  //TODO: Get user input?

  //A
  facultyRecord.firstName;

  //B
  facultyPtr->lastName;

  //C
  facultyRecord.sis_id_number;

  //D
  facultyPtr->sis_id_number;

  //E
  char username[strlen(facultyRecord.sis_username)];
  strcpy(username, facultyRecord.sis_username);
  printf("\n\nUsername of the structure pointed to by facultyPtr: %s\n", facultyPtr->sis_username);
  printf("Compared to the username we made: %s\n\n", username);

  //F
  //facultyRecord.personal_address.phoneNumber
  
  //G
  (facultyPtr->personal_address).address;

  //H
  (facultyPtr->personal_address).city;

  //I
  facultyRecord.personal_address.state;

  //J
  facultyRecord.personal_address.zipCode;

  //K
  struct faculty facultyRecords[5];
  strcpy(facultyRecords[0].personal_address.zipCode, "46062");
  strcpy(facultyRecords[1].personal_address.zipCode, "24242");
  strcpy(facultyRecords[2].personal_address.zipCode, "90323");
  strcpy(facultyRecords[3].personal_address.zipCode, "79154");
  strcpy(facultyRecords[4].personal_address.zipCode, "00126");

  unsigned int i;
  for(i = 0; i < 5; i++)
    printf("Zipcode for %d: %6s\n", i, facultyRecords[i].personal_address.zipCode);

  //L
  struct faculty* newPtr = facultyRecords;
  for(i = 0; i < 5; i++)
    printf("Zipcode for %d using new ptr: %6s\n", i, (newPtr + i)->personal_address.zipCode);

  return 1;
}
