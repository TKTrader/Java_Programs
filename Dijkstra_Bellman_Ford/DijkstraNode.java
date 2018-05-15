package graph;

public class DijkstraNode implements Comparable<DijkstraNode>{
	//***********************************************************************************************
	//     Author: Thomson Kneeland                                     CS212_01 Data Structures
	//     Creation of node to implement Dijsktra's Algorithm
	//     name = position, parent = attached node, weight = edge value
	//     includes overriden compareTo method so that PriorityQueue can organize nodes by weight
	//     includes set/get methods
	//***********************************************************************************************
	public int position; //node position in graph
	public int weight;//weight between node and parent in minimum spanning tree
	public int parent;//parent
	
	public DijkstraNode (int pos) {
		position = pos; //node position in graph
		weight = Integer.MAX_VALUE;//initialize weight
		parent = -1;
	}
	
	//override compareTo method for PriorityQueue comparison
	public int compareTo(DijkstraNode node) {
        if (node.getWeight()<this.getWeight()){
        	return 1;
        } else if(node.getWeight()>this.getWeight()){
        	return -1;
        }
        return 0;
   }
	
	//get node position
	public int getParent(){
		//System.out.println(this.position);
	return this.parent;
	}
	
	//set position
	public void setParent(int p){
		this.parent=p;
		return;
	}
	
	//get node position
	public int getPosition(){
		//System.out.println(this.position);
	return this.position;
	}
	
	//set position
	public void setPosition(int p){
		this.position=p;
		return;
	}
	
	//set edge weight
	public void setWeight(int w){
		this.weight = w;
	}
	
	//get edge weight
	public int getWeight(){
		return this.weight;
	}
}