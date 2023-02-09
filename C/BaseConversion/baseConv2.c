#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int baseToDec(int base, char* bin);
char* decToBin(int dec);

int main(){
  decToBin(18);
  int temp = 18%2;
  printf("the number in decimal is %d\n", baseToDec(16, "F8"));
  return 0;
}

int baseToDec(int base, char* bin){
  //find the length of
  int length = strlen(bin);
  int array[length];

  for(int i =0; i < length; i++){
    if(bin[i] == 49){
      array[i] = 1;
    }
    if(bin[i] == 48){
      array[i] = 0;
    }
    if(bin[i] == 50){
      array[i] = 2;
    }
     if(bin[i] == 51){
      array[i] = 3;
    }
      if(bin[i] == 52){
      array[i] = 4;
    }
       if(bin[i] == 53){
      array[i] = 5;
    }
        if(bin[i] == 54){
      array[i] = 6;
    }
	 if(bin[i] == 55){
      array[i] = 7;
    }
	  if(bin[i] == 56){
      array[i] = 8;
    }
	  if(bin[i] == 65){
      array[i] = 10;
    }
     	  if(bin[i] == 66){
      array[i] = 11;
    }
    	  if(bin[i] == 67){
      array[i] = 12;
    }
     	  if(bin[i] == 68){
      array[i] = 13;
    }
     	  if(bin[i] == 69){
      array[i] = 14;
    }
      	  if(bin[i] == 70){
      array[i] = 15;
    }
  }


  //multiply each place by 10^i
  for(int i = 0; i < length; i++){
    int multiplier = pow(base, length-i-1);
    array[i] = array[i]*multiplier;
  }

  //sum them and return
  int decimalNumber = 0;
  for(int i = 0; i< length; i++){
    decimalNumber+=array[i];
  }
  return decimalNumber;

}

char* decToBin(int dec){
  //get length of array of nums
  int length = 0;
  int temp = dec;
  while(temp != 1){
    temp = temp/2;
    length++;
  }
  length+=1;

  //create an array of size length, and put numbers in
  int temp2 = dec;
  int array[length];
  for(int i =0; i <= length; i++){
    int modulus = temp2%2;
    temp2 = temp2/2;
    if(modulus > 0){
      array[i] = 1;
    }else{
      array[i] = 0;
    }
  }

  //change the array of numbers into a string
  char str[length];
  for(int i=0; i < length; i++){
    char tempC;
    if(array[i] == 0){
      tempC = '0';
    }
    if(array[i] == 1){
      tempC = '1';
    }
    str[length-i-1] = tempC;
  }

    printf("%s\n", str);

  return "hello";

}
