//Will Boland
//11/4/2019

//Program to read integers into a 3X3 matrix and display them

#include <stdio.h>

//MARK: Function Prototypes
//Displays a matrix of 3X3.
void display(int matrix[3][3], int size);

//MARK: Main()
int main(void) {
	int size = 3; //the size of the matrix
	int matrix[size][size];

	printf("Enter 9 elements of the matrix:\n");
	int i;
	for (i = 0; i < size; i++)
    	{
      		int j;
      		for (j = 0; j < size; j++){
			printf("Enter element for Matrix[%d][%d]: ", i, j);
        		scanf(" %d", &matrix[i][j]);
      		}
    	}

	display(matrix,3);
	return 0;
}


//Displays a matrix of a given size (3X3)
void display(int matrix[3][3], int size) {
	int i;
	for (i = 0; i < size; i++) {
		int j;
		for (j = 0; j < size; j++) 
      		{
        		printf("%d, ", matrix[i][j]);
      		}
	printf("\n");
	}
}
