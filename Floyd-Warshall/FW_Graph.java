package graph;

public class FW_Graph {
	//********************************************************************************************
	//     Author: Thomson Kneeland                                     CS212_01 Data Structures
	// Implementation of Floyd-Warshall Algorithm for weighted graph
	// Finds length of shortest paths between all vertices of weighted graph
	// negative edge weights acceptable, but negative cycles not acceptable
	// accepts matrix input, and outputs corresponding shortest path matrix to console
	//********************************************************************************************
	int n;//matrix dimension
	int[][]W;//input graph
	int[][]D;//transformed graph
	int[][]Y;//transformed graph placeholder for each iteration
	
	//default constructor
	public FW_Graph () {
	}
	
	//constructor for new matrix
	public FW_Graph (int[][] matrix) {
		n = matrix.length;
		W = D = matrix;//duplicate W into our transformation matrix
		for (int i=0; i<n;i++){//make any ij values infinite as long as node not itself (i=j)
			for (int j=0;j<n;j++){
				if((i!=j)&&(W[i][j]==0)){//makes sure node's distance to itself always 0
					D[i][j]=Integer.MAX_VALUE/n;//initialize infinity values and make sure sums never exceed Infinity
				}
			}
		}
	}
	
	//print matrix function
	public void print_graph (int[][] g) {
			System.out.print("{");
		for (int i = 0; i < n; i++){
			System.out.print("{");
			for (int j=0;j<n;j++){
				System.out.print( g[i][j]);
				if (j<(n-1)){
					System.out.print(", ");
				}
			}
			if(i<n-1){
				System.out.println("}");
			}else{
				System.out.print("}}");
			}
		}
	}
	
	//Floyd-Warshall Algorithm
	public int[][] floyd_warshall () {
		//D and Y used as matrices for dynamic programming
		for (int k=0;k<n;k++){
			int[][] Y = D;//initialize placeholder matrix
			for(int i=0;i<n;i++){//for each ij
				for (int j=0; j<n;j++){//find minimum function, transfer value to Y
					if(D[i][j]<(D[i][k]+D[k][j])){//Problem: adds two infinities = negative infinity
						Y[i][j]=D[i][j];
					}else{
						Y[i][j]=D[i][k]+D[k][j];
					}
				}
			}
			//this.print_graph(this.D);
			D=Y;// store in D for next iteration
		}
		return D;
	}
	
	public static void main(String[] args) {
		//create initial graph for input
		int [][]A={
				{0,3,8,0,-4},
				{0,0,0,1,7},
				{0,4,0,0,0},
				{2,0,-5,0,0},
				{0,0,0,6,0}
		};
		//initialize graph in program		
		FW_Graph g = new FW_Graph(A);
		//g.print_graph(g.D);//print initial graph - good
		g.floyd_warshall();
		g.print_graph(g.D);
	}
}
