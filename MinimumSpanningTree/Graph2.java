package graph;

import java.util.PriorityQueue;

public class Graph2 {
	//********************************************************************************************
	//     Author: Thomson Kneeland                                CS212_01 Data Structures
	//  Implementation of Minimum Spanning Tree using Prim's Algorithm and PriorityQueue
	//  uses PrimNode class to organize nodes, new compareTo method to organize PriorityQueue by
	//  weight
	//  returns total weight of all edges of minimum spanning tree
	//  initially set Node values to Integer.MAX_VALUE, but PriorityQueue is not ordering them correctly!
	//  I left in test code for further future troubleshooting of infinity values
	//********************************************************************************************
	
	public int n;	//number of vertices
	public int[][] A;	//the adjacency matrix
	public PriorityQueue<PrimNode> Q = new PriorityQueue<PrimNode>(); // PriorityQueue for all nodes
	public PrimNode head; // node to store extracted head of queue
	public int sum; //store sum of heads as extracted from queue
	public PrimNode node;
	
	public Graph2 () {
		n = 0;
		A = null;
	}
	
	public Graph2 (int _n, int[][] _A) {
		this.n = _n;
		this.A = _A;
	}
	
	public int prim (int r) {
		//Create PriorityQueue
		for (int i=0;i<n;i++){
			if (i==r){//initialize source node
				PrimNode node = new PrimNode(r);
				node.setWeight(0);//set source node to 0
				Q.add(node);
			} else {//all other nodes initialized with standard weights
				PrimNode node = new PrimNode(i);//initialize all nodes with positions 0:n-1
				node.setWeight(node.getWeight()+i);//infinity not working for compareTo method!! alternative
				Q.add(node);
			}
		}
		
		for (PrimNode num:Q){
			System.out.println(num.getPosition()+" weight " +num.getWeight());
		} ///works
		
		while(!Q.isEmpty()){
			head = Q.poll();//get head of Queue
			sum += head.getWeight();
//			for (PrimNode num:Q){
//				System.out.println(num.getPosition()+" weight "+num.getWeight());
//			}//INCORRECT with infinity values!!!!!!!!!!!!!
//			System.out.println("pos is "+head.getPosition()+" head weight is "+head.getWeight());
			
			for (int num=0; num<n;num++){
				if (A[head.getPosition()][num]>0){//find adjacent nodes && check if weight greater than head
					for (PrimNode node : Q){
						if((node.getPosition()==num)&&(node.getWeight()>A[head.getPosition()][num])){
							node.setWeight(A[head.getPosition()][num]);
							node.setParent(head.getPosition());
							//System.out.println("node #: "+node.getPosition()+" weight: "+node.getWeight()+" parent "+node.getParent());
						}
					}
				}
			}
		}
		return sum;// sum of the whole minimum spanning tree
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 9;//number of nodes
		int A[][] = {
				{0, 4, 0, 0, 0, 0, 0, 8, 0}, 
				{4, 0, 8, 0, 0, 0, 0, 11, 0}, 
				{0, 8, 0, 7, 0, 4, 0, 0, 2}, 
				{0, 0, 7, 0, 9, 14, 0, 0, 0}, 
				{0, 0, 0, 9, 0, 10, 0, 0, 0}, 
				{0, 0, 4, 14, 10, 0, 2, 0, 0}, 
				{0, 0, 0, 0, 0, 2, 0, 1, 6}, 
				{8, 11, 0, 0, 0, 0, 1, 0, 7}, 
				{0, 0, 2, 0, 0, 0, 6, 7, 0} 
		};
		Graph2 g = new Graph2(n, A);
		System.out.println(g.prim(0));
	}
}