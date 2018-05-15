package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graph3 {
	//********************************************************************************************
	//     Author: Thomson Kneeland                                CS212_01 Data Structures
	//  Implementation of Single Source Shortest Paths with Bellman-Ford and Dijsktra Algorithms
	//  Dijkstra uses DijkstraNode class to organize nodes, new compareTo method to organize by
	//  weight. I had issues with PriorityQueue so implemented with LinkedList instead using
	//  Collections.Sort
	//********************************************************************************************
	int n;
	int[][] A;
	static int[] d;	//shortest distance
	boolean fail;
	int head;
	int[] order;//array for order to visit nodes
	ArrayList<Integer> S;//List of visited nodes
	/**
	 * @param args
	 */
	
	public Graph3 () {
		
	}
	
	public Graph3 (int _n, int[][] _A) {
		n = _n;
		A = _A;
		d = new int[n];
	}
	
	public void initialize_single_source(int s) {
		for (int i = 0; i < n; i++){
			d[i] = Integer.MAX_VALUE;
		d[s] = 0;
		}
	}
	
	public void relax (int u, int v) {
		if (d[v] > (d[u] + A[u][v])) {
			d[v] = d[u] + A[u][v];
			
		}
	}
	
	public boolean bellman_ford (int s) {
		this.initialize_single_source(s);//initialize object relative to source
		int[] order= new int[n]; //order of nodes to visit
		int zero =0;//iteration variable
		for (int x=0;x<n;x++){//populate order starting with s
			if(s+x<n){
				order[x]=s+x;
			}else{
				order[x]=zero++;//iterated order of other nodes starting at 0
			}
		}
		for (int i=0; i<n-1; i++){//relax each edge
			for (int j: order){//proceed in order starting from source
					for (int k=0;k<n;k++){
						if(A[j][k]!=0){
							this.relax(j,k);
						}
					}
				}
		}
		for (int a=0;a<n;a++){//determine whether there is a negative loop
			if (d[a]<0){
				this.fail = false;
			}else{
				this.fail = true;
			}
		}
		return this.fail;//return whether algorithm has negative loop
	}
	
	public void dijkstra (int s) {
		this.initialize_single_source(s);//initialize object relative to source
		LinkedList<DijkstraNode> R = new LinkedList<DijkstraNode>();
		
		ArrayList<Integer> S= new ArrayList<Integer>();
		for (int i=0;i<d.length;i++){//populate LinkedList, will organize by weight
			DijkstraNode node = new DijkstraNode(i);//initialize all nodes with positions 0:n-1
			node.setPosition(i);
			node.setWeight(d[i]);
			R.add(node);
		}
		while(!R.isEmpty()){//check if List empty
			Collections.sort(R);//sort LinkedList
			int position = R.get(0).getPosition();//get first element of list
			S.add(position);//add to list of visited nodes
			R.remove(0);//remove from list of unvisited nodes
			for (int i=0;i<n;i++){//check adjacency matrix
				if(A[position][i]>0&&!S.contains(i)){//find adjacent nodes that have not been visited
					this.relax(position, i);//relax adjacent nodes
					for (DijkstraNode n:R){//update relaxed nodes
						if (n.getPosition()==i){
							n.setWeight(d[i]);
							n.setParent(position);//not used
						}
					}
				}
			}
		}
	}
	
	//method to extract minimum value from array and return its position  NEVER USED
	public static int get_min(int a[]){
		int min=Integer.MAX_VALUE;//comparator value
		int pos=-1;//minimum's position in array
		for (int i=0; i<a.length;i++){
			if(a[i]<min){
				min=a[i];
				pos=i;
			}
		}
		return pos;//return position of minimum
	}
	
	public void display_distance () {
		for (int i = 0; i < n; i++){
			System.out.println(i + ": " + d[i]);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;
		int[][] A = {
		{0, 6, 0, 7, 0},
		{0, 0, 5, 8, -4},
		{0, -2, 0, 0, 0},
		{0, 0, -3, 0, 9},
		{2, 0, 7, 0, 0}
		};
		Graph3 g1 = new Graph3(n, A);
		g1.bellman_ford(0);
		g1.display_distance();
		
		System.out.println("-----------------------");
		
		int[][] B = {
		{0, 10, 0, 5, 0},
		{0, 0, 1, 2, 0},
		{0, 0, 0, 0, 4},
		{0, 3, 9, 0, 2},
		{7, 0, 6, 0, 0}
		};
		Graph3 g2 = new Graph3(n, B);
		g2.dijkstra(0);
		g2.display_distance();
	}
}