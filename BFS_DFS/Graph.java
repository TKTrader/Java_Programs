package graph;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	//********************************************************************************************
	//     Author: Thomson Kneeland                                     CS212_01 Data Structures
	// Implementation of Breadth First Search and Depth First Search
	// I used the Java interface Queue with a Linked List, so as to gain more experience with java
	// data structures
	// Depth first search involves two methods, created static variables to use in multiple methods. 
	// Since there are multiple outputs of time (initial and final), I stored those
	// variables in a 2D array, from which we can extricate the results.
	// The DFS method prints the output itself rather than requiring a separate print array function,
	// since the print array function uses a 1D array.
	// NOTE: DFS implementation in the book seems to always start at first node, so this is how I
	// implemented it
	//********************************************************************************************
	public int n;	//number of vertices
	public int[][] A;	//the adjacency matrix
	public int[] B; //color array
	public int[] C; //distance array
	private final int WHITE = 2;
	private final int GRAY = 3;
	private final int BLACK = 4;
	public int u;  //position variable for node being examined
	
	//depth first search variables
	static int[][] d;//time of discovery
	static int time;
	static int[] color; //color array for dfs
	
	public Graph () {
		n = 0;
		A = null;
		//int[] D = new int[n];
	}
	
	public Graph (int _n, int[][] _A) {
		this.n = _n;
		this.A = _A;
	}
	
	/*
	 * Input: s denotes the index of the source node
	 * Output: the array dist, where dist[i] is the distance between the i-th node to s
	 */
	public int[] bfs (int s) {
		//create array for colors
		int[] B = new int[n];
		for (int i=0; i<n; i ++){//make all nodes white
				B[i]=WHITE;
		}
		//create array for colors
				int[] C = new int[n];
				for (int i=0; i<n; i ++){
						C[i]=Integer.MAX_VALUE;
				}
		C[s]=0;//make distance of source = 0		
		Queue<Integer> Q = new LinkedList<Integer>();//create queue
		Q.add(s);//add source to queue
		while(!Q.isEmpty()){
			u = Q.poll();//dequeue from queue
			for (int i=0; i<n;i++){//check all adjacent nodes
				if (A[u][i]==1){//nodes = 1
					if (B[i]==WHITE){//if adjacent and color = white
						B[i]=GRAY;//change color to grey
						C[i]=C[u]+1;//increment distance array
						Q.add(i);//push adjacent node to Queue
					}
				}
			}
			B[u]=BLACK;//neighbors exhausted, make node black
		}
		return C;//return distance array
	}
	
	//Depth first Search
	public int[][] dfs (Graph g) {
		//create matrix for discover time and finish time
		d = new int[n][2];
		//create array for colors
		color = new int[n];
		for (int i=0; i<n; i ++){//make all nodes white
				color[i]=WHITE;
				d[i][0]=0;//set initial time to 0
				d[i][1]=0;//set final time to 0
		}
		time = 0;
		for (int i = 0; i<n;i++){//visit each white node once
			//System.out.println("checking node: "+i);
			if (color[i]==WHITE){//if node color is white, visit
				g.dfs_visit(g,i);
			}
		}
		//print output of dfs
		for (int num=0; num<n;num++){
			System.out.println(num+": "+d[num][0]+"/"+d[num][1]);
		}
		return d;//output array
	}
	
	//Depth First Search, Visit adjacent node method
	public void dfs_visit(Graph g, int k){
		time += 1;//increment time value for new node
		d[k][0]=time;//set discovery time
		color[k]=GRAY;//make node grey
		for (int i=0; i<n;i++){//check all adjacent nodes
			if (A[k][i]==1){//nodes = 1
				if (color[i]==WHITE){//if adjacent and color = white
					//System.out.println("Visiting adjacent node: "+i);
					g.dfs_visit(g, i);//visit any unexplored adjacent node
				}
			}
		}
		color[k]=BLACK;//when no more neighbors to explore, make black
		time+=1;//increment time
		d[k][1]=time;//add time to final time array
	}
	
	//print function
	public void print_array (int[] array) {
		for (int i = 0; i < array.length; i++)
			System.out.println(i + ": " + array[i]);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 8;
		//  Adjacency matrix  for 8 nodes
		int[][] A = 
			{{0, 1, 0, 0, 1, 0, 0, 0},
			{1, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 0, 1, 0, 1, 1, 0},
			{0, 0, 1, 0, 0, 0, 1, 1},
			{1, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 0, 0, 0, 1, 0},
			{0, 0, 1, 1, 0, 1, 0, 1},
			{0, 0, 0, 1, 0, 0, 1, 0}};
		Graph g = new Graph(n, A);
		System.out.println("Breadth First Search:");
		g.print_array(g.bfs(1));//make second node the source and run bfs()
		System.out.println("\nDepth First Search:");
		g.dfs(g);//run dfs
	}
}