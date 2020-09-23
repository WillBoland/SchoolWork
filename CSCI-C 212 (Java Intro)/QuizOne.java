//TOTAL INCORRECT: 4
//QUESTIONS WRONG: 11, 12, 37, 45

//1: CORRECT
+---+
|   |
+---+
  //This gets printed to the console because \n causes a new line to occur, so the above is printed
  
//2: CORRECT
  import java.util.BigDecimal;
  //Assume inside class and inside main
  BigDecimal four = new BigDecimal("4.35");
  BigDecimal one = new BigDecimal("100");
  BigDecimal answer = four.multiply(one);
  //This performs the operation requested which is: 4.35*100 using BigDecimal objects

//3: CORRECT
  3 - 4 + 5 = 4;
  //3 - 4 = -1; -1 + 5 = 4

//4: CORRECT
  3 - 4 * 5 = -17;
  //PEMDAS comes out to 4 * 5 = 20, then you do 3 - 20 which is -17
  
//5: CORRECT
  2 / 3 * 5 = 0;
 //2 / 3 = 0 due to truncation; 0 * 5 = 0

//6: CORRECT
  2 * 3 / 4 = 1;
  //2 * 3 = 6; 6 / 4 = 1 due to truncation. In order for decimals, you need to have at least one of them be a floating point number or double

//7: CORRECT
  3 % 5 = 3;
  //3 % 5 = 3 because 3 / 5 = 0, so the remainder is 3

//8: CORRECT
  -3 % 5 = -3;
  //it returns -3 because -3 / 5 = 0, with a remainder equal to -3.
  
//9: CORRECT
  5 % 3 = 2;
  //returns 2 because 5 goes into 3 once, and has remainder of 2

//10: CORRECT
  5 % -3 = 2;
  //it is essentially 5 % 3 with a negative divisor, but remainder will still be 2
  
//11: INCORRECT
  3 % -5 = 3;
  //3 goes into -5 zero times, so remainder is 3 because 3 / -5 = 0; remainder is self

//12: INCORRECT
  49 / 17 % 5 = 2;
  //49 / 17 = 2; 2 % 5 = 2; So therefor, my answer of 3 is incorrect

//13: CORRECT
  49 / (17 % 5) = 24;
  //17 % 5 = 2; so 49 / 2 = 24 due to truncation
  
//14: CORRECT
  ('a'+'b') % 2 = 1;
  //'a' + 'b' == 3 because letters are numbers, then 3 % 2 == 1
  
//15: CORRECT
  false || true == true;
  //false || true will ALWAYS return true because with the || (or), if one is true, then it will run, so it is simplified to true.
  
//16: CORRECT
  ! true && false == false;
  //this will simplify to always false because ! true == false, so false && false == false
  
//17: CORRECT
  ! (true && false) == true;
  //! (true && false) == false || true == true; so therefor it simplifies to true

//18: CORRECT
  n > ++n == false;
  //3 > 4 is false because ++n adds one to n before it is evaluated
  
//19: CORRECT
  ++n - n == 0;
  //4 - 4 = 0; ++n adds one to n. which makes n four. so four - four = 0
  
//20: CORRECT
  n++ == n++ == false;
  //3 == 4 == false, because 3, then adds 1 to make n 4,
  
//21: CORRECT
  ++n == n++ == true;
  //4 == 4 == true; because adds one to n, so 4, then checks equality to 4 == 4 is true, then adds another one.
  
//22: CORRECT
  n++ == 3;
  n == 4;
  //n ++ = 3 because n = 3 then adds one. at end of everything n == 4;
  
//23: CORRECT 
  ++n == 4;
  n == 4;
  //++n adds one to n, making it equal to 4.
  
//24: CORRECT
  (n = n++ - ++n) < 0 == true;
  //the expression before the inequality is n = -2, and -2 is < 0 so it is true;
  
//25: CORRECT
  n == -2;
  //n is -2 because you're setting n = to 3 - 5 inside of the statement
  
//26: CORRECT
  "1" + (2+3) == "15";
  //2+3 = 5, then a STRING added to a number makes that number "add on" to that string essentially.
  
//27: CORRECT
  "1" + 2 + 3 == "123";
  //"1" + 2 == "12" + 3 == "123";
  
//28: CORRECT
  1 + "2" + 3 == "123";
  //same as explanation in Q27
  
//29: CORRECT
  1 + 2 + "3" == "33";
  //1 + 2 == 3, then 3 + "3" == 33;
  
//30: CORRECT
  "tomato".charAt(2) - "tomato".charAt(5) == -2;
  //is the same as 'm' - 'o' which is -2 because charectors are numbers;
  
//31: CORRECT
  "eggplant".length() == 8;
  //.length() returns how many charectors there are in a string, so 8;
  
//32: CORRECT
  "eggplant".substring("kale".length()) == "lant";
  //this is the same as "eggplant".substring(4) which returns "lant";
  
//33: CORRECT
  "kale".charAt(3) == 'e';
  //strings start at 0 when counting "indexes", just like Arrays!;
  
//34: CORRECT
  "eggplant".substring("eggplant".length() - 1) == "t";
  //same as substring(7) which is "t"
  
//35: CORRECT
  "beans".substring(1, 4) == "ean";
  //substring of (1, 4) is the same as [1, 4), so "ean"
  
//36: CORRECT
  !a == true simplifies to !a;
  //the opposite of a is !a, equals true would just simplify to a, so !a is simplified.
  
//37: INCORRECT
  !a != false is !a;
  //!= false simplfies to == true, so !a == true is !a.
  
//38: CORRECT
  true && !a simplifies to !a;
  //true && (some cond) will mean we only ever care about the "some cond" because true is always true;
  
//39: CORRECT
  if (n == 3) a = true; else a = false; simplifies to a = (n==3);
  //this is becayse if n == 3 a is true, and thats the only condition. so set a = (n==3)
  
//40: CORRECT
  if (n != 3) a = false; else a = true; simplifies to a = (n==3);
  //if 3 is == 3, a is false, so that means if we simplifty to if n == 3, a is true; meaning a = (n==3);

//41: CORRECT
  a = false; if (n > 3) if (n < 5) a = true; simplifies to a = (n==4);
  //if n > 3 and n < 5, it simplifies to n == 4, so a = (n==4);
  
//42: CORRECT
  if (n < 0) a = true; else a = (n > 1); simplifies to a = (n < 0 || n > 1);
  //this is because if n < 0, it is true and if n is > 1, a is also true;
  
//43: CORRECT
  n < 5 || n > 3 simplifies to true;
  //This is because all possible numbers will satisfy one of those conditions, so it is ALWAYS true
  
//44: CORRECT
  n < 3 && n > 5 simplifies to false;
  //this will always be false because n cannot be both smaller than three yet higher than five
  
//45: INCORRECT
  Can every while loop be expressed as a for loop? == YES;
  //Yes it can! You just check the same condition inside of the foor loop while running it an infinite amount of times and break once the condition is false;
  
//46: CORRECT
  Can eveery while loop as a do-while loop? == YES;
  
  EXAMPLE:
    
    while(a<5) {
    stuff();
  }
 
    
    do{
      if(a<5) {
        stuff();
      }
    } while(a<5);
  //Yes it can! All you have to do is check if the condition is the same for both statements. I'll provide an example above.
    
//47: CORRECT
  int m = 18, n = 10; if(m > 10) { if(m > 5) n = 1; } else n = 2; IN_THE_END: n = 1;
  //This is because 18 > 10 and 18 > 5, so n == 1;
  
//48: CORRECT
  int m = 18, n = 10; if(m > 10) if(m > 5) n = 1;  else n = 2; IN_THE_END: n = 1;
  //This is because 18 > 10 and 18 > 5, so n == 1; even though there is a dangling else
  
//49: CORRECT
  int m = 18, n = 10; if(m < 10) {if(m > 5) n = 1;}  else n = 2; IN_THE_END: n = 2;
  //this is because m < 10 is FALSE , then the else is after the brackets so n == 2;
  
//50: CORRECT
  int m = 18, n = 10; if(m < 10) if(m > 5) n = 1;  else n = 2; IN_THE_END: n = 10;
  //this is because of the dreaded dangling else. that else n = 2 is actually for the if of if(m > 5).
  
//51: CORRECT
  return false;
  return true;
  //my description on the paper is correct. if you use .equals(String), it will return true in both cases.
  
//52: CORRECT
  Dangling else is presented correctly;
  //This is because I did not use {} to encapsulate the if statements. Now the issue is that nothing will print if the gpa is lower than 1.5.
  
  
  
  
  
  
  
  
  
  
  
  