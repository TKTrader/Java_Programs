import java.util.Scanner;

public class FPNToDecimal {
	//*********************************************************************************
	//Author: Thomson Kneeland							CS230_01 Computer Systems
	//FPNtoDecimal -  program that converts a 32 bit FPN (IEEE 754, single precision,
	//with bias 127) into its decimal equivalent
	//*********************************************************************************
	static String result="";//variable to store final result
	static boolean sizeBool = false;//boolean to insure user input usable
	static String num, expString, mantissaString, mantissaInteger, mantissaDecimal;
	static int exponent;
	static float mantissa;
	
	//BinaryToDecimal - method to convert binary to decimal using string input 
	//(string input avoids length of number issues)
	public static int binaryToDecimal(String x){
		int decimal = 0;//variable for decimal result
		int exponent = 0; //first exponent is 0;  (2^0) 
		for (int i=x.length()-1; i>=0; i--){//iterate on string in reverse order
			int digit = Character.getNumericValue(x.charAt(i));//convert character to numeric
			decimal+=digit*(Math.pow(2, exponent));//add digit value, base 2, to total
			exponent+=1;//increment exponent for next digit
		}
		return decimal;
	}
	
	//BinaryMantissaToDecimal - method to convert binary mantissa to decimal using string input 
	//(string input avoids length of number issues)
	public static float binaryMantissaToDecimal(String y){
		float mantissa = 0;//variable for decimal result
		int exponent = -1; //first exponent is 0;  (2^0) 
		for (int j=0; j<y.length(); j++){
			int digit = Character.getNumericValue(y.charAt(j));//convert character to numeric
			mantissa +=digit*(Math.pow(2, exponent));//add digit value, base 2, to total
			exponent-=1;//increment exponent for next digit
		}
		return mantissa;
	}
	
	//binaryCheck - method that checks if input binary
	public static boolean binaryCheck(String bin){
		boolean binary=false;
		for (int i=0; i<bin.length();i++){
			char n = bin.charAt(i);
			if ((n!='0')&&(n!='1')){//check if all characters are 1 and 0
				System.out.println("you can only enter 1's and 0's for a binary number!");
				binary=false;
				break;
			}else{
				binary=true;
			}
		}
		return binary;
	}
	
	//bitCheck - method that checks if input string is 32 bits
	public static boolean bitCheck(String x){
		int numLength = x.length();
		if (numLength==32){
			return true;
		}else{
			System.out.println(num +" is not 32 bits long");
			return false;
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(sizeBool!=true){
			System.out.println("Please enter a 32 bit FPN for conversion into decimal:");
			num = input.nextLine();
			num = num.replaceAll(" ","");//eliminate any spaces from user input
			
			//check if input is binary digits and 32 bits long
			if((binaryCheck(num)!=true)||bitCheck(num)!=true){
				sizeBool=false;
			}else{
				sizeBool=true;
			}
		}
		//examine first digit and add negative sign, if needed
		if(num.charAt(0)=='1'){
			result = result.concat("-");
		}
		
		//Calculate exponent portion of FPN
		expString = num.substring(1, 9);//extract 8 bits of exponent
		exponent = FPNToDecimal.binaryToDecimal(expString)-127;//convert exponent to binary and remove bias
		
		//Calculate Mantissa portion of FPN
		mantissaString = num.substring(9);		
		
		mantissaInteger="";
		mantissaDecimal = "";
		if (exponent>0){
			//Apply exponent to mantissa to separate integer/fraction
			mantissaInteger = "1"+mantissaString.substring(0,exponent);//add 1 to mantissa
			mantissaDecimal = mantissaString.substring(exponent);
		}
		
		//If exponent is negative, add zeros and 1 to mantissa
		String zeros="";
		if (exponent<0){
			for (int num = exponent; num<0; num++){
				zeros =   zeros.concat("0");
			}
			mantissaInteger ="0";
			mantissaDecimal = zeros+"1"+mantissaString;
		}
		//account for exponent = 0
		if (exponent==0){
			mantissaInteger = "1";
			mantissaDecimal = "0";
		}
		
		//eliminate extraneous zeros at end of mantissa
		while((mantissaDecimal.length()>0) && (mantissaDecimal.charAt(mantissaDecimal.length()-1)=='0')){
			mantissaDecimal=mantissaDecimal.substring(0,mantissaDecimal.length()-1);
		}
		
		//combine two components of mantissa
		mantissa = FPNToDecimal.binaryToDecimal(mantissaInteger) + FPNToDecimal.binaryMantissaToDecimal(mantissaDecimal);
		result += mantissa;
		System.out.println("The decimal equivalent is "+result);
		
		//Tests:
		//1 10000010 00100000000000000000000  Yields -9.0	
		//1 10000010 11000000000000000000000  Yields -14.0
		//1 10000001 01000000000000000000000  Yields -5.0
		//0 10000000 10010001111010111000011  yields 3.14 
		//0 10000000 11000000000000000000000  yields 3.5
		//1 10000000 11000000000000000000000  yields -3.5
		//0 01111111 00000000000000000000000 yields 1.0
	}
}