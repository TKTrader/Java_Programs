package dp;

public class LCS {
		//********************************************************************************************
		//  Author: Thomson Kneeland                                     CS212_01 Data Structures
		// Longest Common SubSequence
		//  Program takes two string inputs and outputs the length of the maximum common length subsequence 
		// sequence elements can be nonadjacent, but must be in order increasing index
		// I added a print_LCS method, however it is worth noting that this will return one subsequence
		// and there may be multiple subsequences fulfilling the parameters
		//********************************************************************************************
	static int[][] optimal;//array for storing optimal solution
	public static int lcs_length (String X, String Y) {
		int m = X.length();//length of X
		int n = Y.length();//length of Y
		int[][] table = new int[m+1][n+1];//array for examining subsequence
		optimal = new int[m+1][n+1];//array for storing optimal solution
		for (int i=1;i<m;i++){//insert 0's into first element of each row
			table[i][0]=0;
		}
		for (int j=0;j<n;j++){//insert 0's into first element of each columns
			table[0][j]=0;
		}	
		for (int i=1;i<=m;i++){//iterate and check strings for matches
			for (int j=1;j<=n;j++){
				if (X.charAt(i-1)==Y.charAt(j-1)){//if subsequence character matches
					table[i][j] = table[i-1][j-1]+1;
					optimal[i-1][j-1]=1;//diagonal
				}else if(table[i-1][j]>=table[i][j-1]){
					table[i][j]=table[i-1][j];
					optimal[i-1][j-1]=0;//up
				}else{
					table[i][j]=table[i][j-1];
					optimal[i-1][j-1]=2;//left
				}
			}
		}
		return table[m][n];
	}
	//Method to print Longest Common Subsequence
	//Requires an input of 1 less than string length
	public static char print_LCS (int[][] b, String X, int i,int j) {//optimal array, first string, length of 1st string, length of 2nd string
		if(i==-1||j==-1){
			return ' ';
		}
		if (b[i][j]==1){
			print_LCS(b,X,i-1,j-1);
			System.out.print(X.charAt(i));
			return ' ';
		}else if (b[i][j]==0){
			print_LCS(b,X,i-1,j);
		}else{
			print_LCS(b,X,i,j-1);
		}
		return ' ';
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(LCS.lcs_length("ADC", "ABCD"));//test case
		//System.out.print("Longest Common Subsequence is: ");
		//System.out.print(LCS.print_LCS(LCS.optimal,"ADC",2,3)+"\n");//test case
		
		System.out.println(LCS.lcs_length("ABCBDAB", "BDCABA"));
		System.out.print("Longest Common Subsequence is: ");
		System.out.print(LCS.print_LCS(LCS.optimal,"ABCBDAB",6,5)+"\n");//test case
		
		System.out.println(LCS.lcs_length("ACCGGTCGAGTGCGCGGAAGCCGGCCGAA", "GTCGTTCGGAATGCCGTTGCTCTGTAAA"));
		System.out.print("Longest Common Subsequence is: ");
		System.out.print(LCS.print_LCS(LCS.optimal,"ACCGGTCGAGTGCGCGGAAGCCGGCCGAA",28,27)+"\n");//test case
	}

}
