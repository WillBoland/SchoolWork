//Will Boland
//12/16/2019

#include <stdio.h>
#define OLDMAST "oldmast.dat"
#define TRANS "trans.dat"


//Makes the test data
void makeTestFiles(void) {
  FILE* ofPtr = fopen(OLDMAST, "w");
  FILE* tfPtr = fopen(TRANS, "w");

  //makes master file
  fprintf(ofPtr, "%d %s %.2f\n", 100, "Alan Jones",  348.17);
  fprintf(ofPtr, "%d %s %.2f\n", 300, "Mary Smith", 27.19);
  fprintf(ofPtr, "%d %s %.2f\n", 500, "Sam Sharp", 0.00);
  fprintf(ofPtr, "%d %s %.2f\n", 700, "Suzy Green", -14.22);

  //makes transaction file
  fprintf(tfPtr, "%d %.2f\n", 100, 27.14);
  fprintf(tfPtr, "%d %.2f\n", 300, 62.11);
  fprintf(tfPtr, "%d %.2f\n", 400, 100.56);
  fprintf(tfPtr, "%d %.2f\n", 900, 82.17);

  fclose(ofPtr);
  fclose(tfPtr);
}

//makes a new master record and file matches
void filematch(void) {
  int oldAccount;
  int transAccount;

  char fName[20];
  char lName[20];

  float oldBalance;
  float transBalance;

  FILE* ofPtr = fopen(OLDMAST, "r");
  FILE* tfPtr = fopen(TRANS, "r");
  FILE* nfPtr = fopen("newmast.dat", "w");


  while(!feof(ofPtr)) {
    fscanf(ofPtr, "%d %s %s %f\n", &oldAccount, fName, lName, &oldBalance); //read values for old master
    fscanf(tfPtr, "%d %f\n", &transAccount, &transBalance); //read values for transaction

    if(oldAccount == transAccount) { //accounts match
      fprintf(nfPtr, "%d %s %s %.2f\n", oldAccount, fName, lName, oldBalance + transBalance);
    } else { //accounts do not match
      fprintf(nfPtr, "%d %s %s %.2f\n", oldAccount, fName, lName, oldBalance);
      printf("Unmatched transaction record for account number: %d\n", transAccount);
    }
  }

  fclose(ofPtr);
  fclose(tfPtr);
  fclose(nfPtr);
}



int main(void) {
  makeTestFiles();
  filematch();

  return 0;
}

