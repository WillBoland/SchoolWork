# In Class Examples #
This provides in class examples with notes in the .c files.    
Remember to use strcpy(myArray, "String I want to put in");   
  
## Number System  ##  
  
  
|Bin. |  Dec | Hex | Oct|
|-----|------|-----|----|
|0000 |  0   | 0   | 0  |
|0001 |  1   | 1   | 1  |
|0010 |  2   | 2   | 2  |
|0011 |  3   | 3   | 3  |
|0100 |  4   | 4   | 4  |
|0101 |  5   | 5   | 5  |
|0110 |  6   | 6   | 6  |
|0111 |  7   | 7   | 7  |
|1000 |  8   | 8   | 10 |
|1001 |  9   | 9   | 11 | 
|1010 |  10  | A   | 12 | 
|1011 |  11  | B   | 13 | 
|1100 |  12  | C   | 14 | 
|1101 |  13  | D   | 15 | 
|1110 |  14  | E   | 16 | 
|1111 |  15  | F   | 17 | 
  
**Decimal:**  Base 10. numbers 0, 1, 2, 3, 4, 5, 6, 7, 8, 9  
	  PowerSeries: 10^x 10^0 = 1, 10, 100, 1000, 10000, ...  
  
**Binary:**	Base 2: 0, 1   
  
**Hexadecimal:**	Base 16, 0-9 A-F, 16^x  
  
  Conversion from Hex to Binary: Groups of 4  
  Conversion from Binary to Hex: Groups of 4 then corresponding HEX letter
  
**Octal:**	Base 8: 0-7, 8^x  
	Conversion to binary: groups of 3  
	Conversion from binary: groups of 3  
	
	
	
**Negative #:**	sign bit, 1-complement, 2-complement  
left most bit == sign-bit  
MSB <-> LSB  
0 = +  
1 = -  
  
  010 ~ 101 (the complement)  
  101 + 1 = 110 (2-complement)  
  flip every digit and add 1 to it  
  
  signed int VS unsigned int  
  largest unsigned int is the smallest signed int  
    
    
## Pointers ##  
```C
int v = 10;  
//name of var is v  
//type is int => 4 bytes  
//value is 10  
//&v will give me address for this var.  

//Varialbes have values that are stored at some mem. address  
printf("%d", v); //will print the value  
printf("%p", &v); //will print 0x1013fc

//pointers are variables or containers for memory addresses
int* p; //name of variable is p. Type is int*.
//&p will give me address where the var is stored.
//*p is a dereferencing operator

printf("%p", p); //0x1013FC
printf("%p", &p); //0x208E0
printf("%p", *p); //0xA
//*&p and &*p and p are all the same
```
**Variables**  
  
| Variable | Memory Address | Value |
|----------|----------------|-------|
| v        | 0x1013fc       | 10    |
  
**Pointers**  
  
| Name | Address | Value  |  
|------|---------|--------|  
|p     | 0x208E0 |0x1013fc|  
  
**Pass by Value** 
```C
int F1(int);
void main(void) {
  int x = 10;
  F1(x);
  printf("%d", x); //10
}
F1(int x) {
  x++;
  printf("%d", x) //11
}
```  
  
**Pass by Reference**
```C
int F1(int*);
void main(void) {
  int x = 10;
  F1(&x);
  printf("%d", x); //11
}
F1(int* x) {
  *x = *x + 1;
  printf("%d", *x) //11
}
```  
Const keyword is used to restrict a pointer to read-only.  
  
Principal of Least Priviladges  
  ```C
//const pointed with non-const data (by dereferencing)
//const pointer with const data
//non-const pointer with non-const data
//non-const pointer with const data
int v = 10; int* p = &v; //non-const pointer, non-const data
int* const p = &v; //const pointer, non-const data
const int* p = &v; //non-const pointer, const data
const int* const p = &v; //const pointer, const data
```  
**For all of these, `v = 20;` is still allowed!**  
  
## Pointer Arithmatic ##  
```C
int v = 10; v++; v--;
int* p = &v; p++; p--;
```  
  
**Arrays** 
```C
int v = [5];
int* p = v;
p++; //equivalent to &v[1]
p++; //equivalent to &v[2]

printf("%p", v[0]);
printf("%p", *p);
printf("%p", *(p + 1));
printf("%p", p[0]);
```  
  
**Char Arrays**  
```C
//array of char pointers
const char* suit[4] = {"Hearts", "Diamonds", "Clubs", "Spades"};
```
  
  
# Structures #
Group variables together.  
## Declaration ##
```c
//declaration
struct Student {
  char fName[20];
  char lName[20];
  double gpa;
};
```
## Instantiation's ##
```c
//instantiation
struct Student will;
struct Student jack;
struct Student fall2019[45];

//other option
struct Student {
  //instance variables
} will, jack, fall2019[45];
  
  
typedef struct Student studentStruct;
studentStruct will; //other option due to above line
studentStruct jack;
studentStruct fall2019[45];
```

## Examples ##
```C
struct Card {
  char* face;
  char* suit;
  
}aCard, deck[52], *pCard;

pCard = &aCard;

*((*pCard).face); //equivalent
*(pCard -> face); //equivalent

//-> can ONLY be used on a pointer to struct instance
```
A structure may have another structure as a member.  
```c
struct Student2 {
  char fName[20];
  struct Student one;
}oneStudent2;

oneStudent2.fName //gives access to first name
(oneStudent2.one).fName //gives access to first name held in Student in Student2. It can have a pointer to itself as a member.
```
**Can Use**  
* =  
* sizeof  
* .  

**Cannot Use**  
* ==
* !=  
  
```c
struct Something {
  char c;
  int m;
  short s;
  short s;
}abc, def;
//(abc == def) does not work because of "memory holes"
```
  
# Bit-wise Operations #  
```c
printf("%p", ~0);
//~ is a compliment operator.
//& "whenever you see a 0, make it a 0."
//| "whenever you see a 1, make it a 1."
//^
//<< is a left shift operator.
//>> is a right shift operator.

//& operator
20 & 30 == 20
10100
11110
-----
10100

//| operator
20 | 30 == 30
10100
11110
-----
11110

//<< operator
int value = 1;
value = value << 1; //value = 2
0001
0010

//>> operator
value = value >> 1; //value = 1
0010
0001

```  
  
# Enum #
```c
enum Month {JAN, FEB, ..., DEC};
for(enum Month month = JAN; month <= DEC; ++month) {

}
```

# Printing #
%d = signed int  
%u = unsigned int  
%i = signed int (different for scanf)  
%o = octal  
%x = hex  
%X = hex (uppercase) 
  
**Floating Point Numbers**
IEE754: Single Precision and Double Precision
%f or F = fixed point (precision 6-digit) at least 1-digit on the left of the "." NO ROUNDING  
%e or E = exponential notation (ex: 1.04592) 1-digit always. YES ROUNDING  
%g or G = either FP or ExpN -> behaves as e when exponent is < -4 || > 6 (or default) otherwise does %f  
  
**OTHERS**  
%c = char symbol  
%s = c-string (expects null char at end '\0')  
%p = address in hex  
%% = % sign  
%4d = uses 4-digits to print. Default is RIGHT justified  
%-4d = uses 4-digits to print. LEFT justified  
% d = print without + sign  
%04d = 0 padded 4 digits  
\# prefix = 0 for octal, 0x for hex  
  
**To specify precision: (Default is 6)**  
%.4f = 4 digit precision  
* acts as a wild card in printf  

## Example ##
```C
printf("%*.*f", 7, 2, 98.736); //prints 98.74
```

## Escape Chars ##  
\\' = single quote  
\\n = newline (caridge return)  
\\r = CR  
\\t = tab  
\\v = vertical tab  
\\b = backspace  
  
## Scanf ##  
%d = int  
%u = unsigned int  
%i = optionally signed dec, octal, or hex int  
%o = octal  
%x = hex  
  
```C
scanf("%8s", z); //z is name of the array. Reads up to 8 chars
//REGEX can be used. See Below
scanf("%8[aeiou]", z); //scanset (a REGEX). Read vowels
scanf("%8[^aeiou]", z); //inverted scanset (a REGEX) Read everything besides vowels

scanf("%d-%d-%d", &m, &d, &y); //12-12-2012
scanf("%d%*c%d%*c%d", &m, &d, &y); //can read any date format we like. 12/12/2012 or 12,12,2012 or 12-12-2012, etc.
```
  
# File Processing (Chapter 11) #  
Use fopen to open and fprintf to write and fclose to close  
rewind goes back to start of file  
fwrite and fread to write and read  by byte size
fseek to move file pointer  

**CODES:**  
r = read  
w = write  
a = append  
see chapter 11 for more
