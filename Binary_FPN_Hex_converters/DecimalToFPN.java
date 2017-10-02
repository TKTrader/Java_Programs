import java.util.Scanner;

public class DecimalToFPN {
	//*********************************************************************************
	//Author: Thomson Kneeland							CS230_01 Computer Systems
	//DecimaltoFPN -  program that converts a decimal number to 32 bit FPN (IEEE 754, 
	//single precision, with bias 127)
	//*********************************************************************************

	static String sign ="";//stores negative sign
	static String integer="";//stores decimal integer
	static String mantissa="";//stores decimal mantissa
	static String binaryInt="";//stores binary integer
	static String binaryMantissa;//stores binary mantissa
	static int exponent;//stores exponent in decimal
	static String binaryExponent;//stores exponent in binary
	
	//decimalToBinary: a method that converts a decimal integer input as string, to binary
	public static String decimalToBinary(String x){
		int number = Integer.parseInt(x);
		String result="";
		do{//iteratively divide by 2
			int remainder = number%2;
			number = number/2;
			result = remainder+result; //output remainders in reverse order
		}while (number!=0);
		return result;
	}
	
	//mantissaToBinary: a method that converts the mantissa portion to binary
	public static String mantissaToBinary(String y){
		float man = Float.parseFloat(y);
		String result="";
		do{
			man=2*man;
			String p = Float.toString(man);
			String digit =p.substring(0,1);
			result+=digit;
			p=p.substring(1);
			man=Float.parseFloat(p);
		}while(man>0&&(result.length()<24));//accounts for infinitely repeating binary decimals
		return result;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a decimal number for conversion to 32 bit FPN:");
		String num = input.next();
		
		//determine if number negative and add result as first digit to FPN
		if (num.startsWith("-")){
			sign="1";
			num = num.substring(1);//remove negative
		}
		else{
			sign="0";
		}
		
		//remove 0 if first digit
		if (num.charAt(0)=='0'){
			num = num.substring(1);
		}
		
		//assess if decimal present in input and subset into integer and mantissa
		if (num.contains(".")==false){
			num= num+".0";//add mantissa
		}
		//store substrings of integer and mantissa
		integer = num.substring(0,num.indexOf('.'));
		mantissa = num.substring(num.indexOf('.'));
		
		//translate integer and mantissa to binary and calculate exponent
		if (integer.length()>0){//cases with integer
			binaryInt = DecimalToFPN.decimalToBinary(integer);
			exponent = binaryInt.length()-1;
			binaryMantissa=DecimalToFPN.mantissaToBinary(mantissa);
		}else{//cases with no integer values and negative exponent
			binaryMantissa=DecimalToFPN.mantissaToBinary(mantissa);
			exponent = -1*binaryMantissa.indexOf("1")-1;
		}
		//convert exponent with bias 127 to binary
		exponent+=127;
		binaryExponent = DecimalToFPN.decimalToBinary(""+exponent);
		//make sure exponent is 8 bits long
		while (binaryExponent.length()<8){
			binaryExponent = "0"+binaryExponent;
		}
		
		//add integer to mantissa
		binaryMantissa=binaryInt+""+binaryMantissa;
		//since result should be normalized, find first occurrence of "1" and delete all numbers up to it
		//(Hidden 1 Principle)
		binaryMantissa=binaryMantissa.substring(binaryMantissa.indexOf("1")+1);
		//make sure mantissa is 23 bits long
		while(binaryMantissa.length()<23){
			binaryMantissa+="0";
		}
		
		if(binaryMantissa.length()>23){
			binaryMantissa=binaryMantissa.substring(0,23);
		}
		//Output FPN result
		System.out.println("\n32 bit FPN: "+sign +" "+binaryExponent+" "+binaryMantissa);
		
		//Trial Numbers
		//3.25 yields  0 10000000 10100000000000000000000  
		//12 or 12.0000  yields    0 10000010 10000000000000000000000
		//-5.25 yields 1 10000001 01010000000000000000000
		//1.0 yields 0 01111111 00000000000000000000000
		//-1 yields1 01111111 00000000000000000000000
	}
}