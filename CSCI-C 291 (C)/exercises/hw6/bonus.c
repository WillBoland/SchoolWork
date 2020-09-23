//Will Boland
//12/17/2019
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

//returns a c string containing the described input
char* read_strings(void) {
  char* strings = (char*)malloc(sizeof(char) * 16);
  strcpy(strings, "john'john'\"john\"");
  return strings;
}

//cleans the quotes and single quotes but leaves apostrophe s
char* clean_quotes(char* string) {
  char* cleanedString = (char*)malloc(strlen(string)*sizeof(char));

  //removes single and double, up until the one before the end
  unsigned int index;
  for(index = 0; index < strlen(string) - 1; index++) {
    char nextLetter = toupper(string[index + 1]);
    if(string[index] == '"' || (string[index] == '\'' && nextLetter != 'S')) { //remove char
      cleanedString[index] = ' ';
    } else { //put char into our array
      cleanedString[index] = string[index];
    }
  }

  //at end of string, check to remove last char
  if(string[strlen(string) - 1] == '"' || string[strlen(string) - 1] == '\'') {
    cleanedString[strlen(string) - 1] = ' ';
  } else {
    cleanedString[strlen(string) - 1] = string[strlen(string) - 1];;
  }

  return cleanedString;
}

int main(void) {
  //PART 1 (reading john) it does not ignore the " and '
  char* strings = read_strings();
  printf("PART 1: %s\n\nPART 2: \n", strings);

  //PART 2 (cleaning) you can enter input here to be cleaned
  char* input = (char*)malloc(sizeof(char) * 50);
  printf("Enter what you want cleaned: ");
  fgets(input, 50, stdin);
  char* answer = clean_quotes(input);
  puts(answer);
  return 0;
}
