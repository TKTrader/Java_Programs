import java.util.Scanner;

public class BinaryToHex {
	//*********************************************************************************
	//Author: Thomson Kneeland							CS230_01 Computer Systems
	//BinaryToHex -  program that converts a user input binary number to hexadecimal and
	//back to binary. Two possible methods were clear:  Using arithmetic brute force to 
	//convert the binary by multiples of 2, and then dividing by 16 iteratively.  
	// Or grouping the binary number into groups of 4 digits and converting using a 
	//switch statement. I chose the latter method, which allows for a much larger number
	//conversion, only limited to string length rather than number size
	//*********************************************************************************
	static String numStr;
	static String userNum;//user input
	static String hexResult="";
	static String sub;
	
	//binaryCheck - method that checks if input string is binary
	public static boolean binaryCheck(String bin){
		boolean binary=false;
		for (int i=0; i<bin.length();i++){
			char n = bin.charAt(i);
			if ((n!='0')&&(n!='1')){
				System.out.println("you can only enter 1's and 0's for a binary number!");
				binary=false;
				break;
			}else{
				binary=true;
			}
		}
		return binary;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		do{
			System.out.println("Please enter a binary number for conversion to hexadecimal:");
			userNum = input.next();
		}while(binaryCheck(userNum)!=true);//check if input binary
		
		//add zeros to beginning of number to make number a multiple of length 4 for easy hex conversion
		int digitsNeeded=0;
		if (userNum.length()%4!=0){
			digitsNeeded = 4-userNum.length()%4;
		}
		String addedZeros = "";
		if(digitsNeeded!=4|digitsNeeded!=0){
			for (int i=0;i<digitsNeeded;i++){
				addedZeros = addedZeros.concat("0");
			}
			numStr=addedZeros.concat(userNum);
		}
		int numHex = numStr.length()/4;//find number of Hex digits needed
		String hexDigit="";
		for (int i=0;i<numHex;i++){
			for (int j=0;j<4;j++){
				sub = numStr.substring(i*4, i*4+j+1);
			}
			//convert to hexadecimal
			switch(sub){
				case "0000":
					hexResult = hexResult.concat("0");
					break;
				case "0001":
					hexResult = hexResult.concat("1");
					break;	
				case "0010":
					hexResult = hexResult.concat("2");
					break;	
				case "0011":
					hexResult = hexResult.concat("3");
					break;
				case "0100":
					hexResult = hexResult.concat("4");
					break;	
				case "0101":
					hexResult = hexResult.concat("5");
					break;	
				case "0110":
					hexResult = hexResult.concat("6");
					break;	
				case "0111":
					hexResult = hexResult.concat("7");
					break;
				case "1000":
					hexResult = hexResult.concat("8");
					break;	
				case "1001":
					hexResult = hexResult.concat("9");
					break;	
				case "1010":
					hexResult = hexResult.concat("A");
					break;
				case "1011":
					hexResult = hexResult.concat("B");
					break;
				case "1100":
					hexResult = hexResult.concat("C");
					break;
				case "1101":
					hexResult = hexResult.concat("D");
					break;	
				case "1110":
					hexResult = hexResult.concat("E");
					break;
				case "1111":
					hexResult = hexResult.concat("F");
					break;	
				}
		}
		System.out.println(userNum +" in hexadecimal is "+hexResult);
		
		//Convert back to binary
		String binaryResult="";
		char hexDig=0;
		for (int i=0;i<hexResult.length();i++){
			hexDig = hexResult.charAt(i);
			switch(hexDig){
				case '0':
					binaryResult = binaryResult.concat("0000");
					break;
				case '1':
					binaryResult = binaryResult.concat("0001");
					break;
				case '2':
					binaryResult = binaryResult.concat("0010");
					break;
				case '3':
					binaryResult = binaryResult.concat("0011");
					break;
				case '4':
					binaryResult = binaryResult.concat("0100");
					break;
				case '5':
					binaryResult = binaryResult.concat("0101");
					break;	
				case '6':
					binaryResult = binaryResult.concat("0110");
					break;
				case '7':
					binaryResult = binaryResult.concat("0111");
					break;
				case '8':
					binaryResult = binaryResult.concat("1000");
					break;	
				case '9':
					binaryResult = binaryResult.concat("1001");
					break;	
				case 'A':
					binaryResult = binaryResult.concat("1010");
					break;	
				case 'B':
					binaryResult = binaryResult.concat("1011");
					break;	
				case 'C':
					binaryResult = binaryResult.concat("1100");
					break;	
				case 'D':
					binaryResult = binaryResult.concat("1101");
					break;
				case 'E':
					binaryResult = binaryResult.concat("1110");
					break;	
				case 'F':
					binaryResult = binaryResult.concat("1111");
					break;	
			}
		}
		//delete any initial zeros created during conversion, preserve 1 digit min
		while ((binaryResult.charAt(0)=='0')&&(binaryResult.length()>1)){
			binaryResult = binaryResult.substring(1);
		}
		//hexadecimal output
		System.out.println("Converting "+hexResult+" back to binary results in: "+binaryResult);
		
		//Test Cases:
		// 111100001111 yields F0F
		// 0  yields 0
		// 1000 yields 8
		//10101010101010 yields 2AAA
		//11110110101111101111011111 yields 3DAFBDF
		//111111111111111111111111111111111111111111111111111111111111111 yields 7FFFFFFFFFFFFFFF
	}
}