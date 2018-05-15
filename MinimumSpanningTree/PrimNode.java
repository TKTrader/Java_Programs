package graph;

public class PrimNode implements Comparable<PrimNode>{
	//***********************************************************************************************
	//     Author: Thomson Kneeland                                     CS212_01 Data Structures
	//     Creation of node to implement Minimum Spanning Tree
	//     name = position, parent = attached node, weight = edge value
	//     includes overriden compareTo method so that PriorityQueue can organize PrimNodes by weight
	//     includes set/get methods
	//***********************************************************************************************
	public int position; //node position in graph
	public int weight;//weight between node and parent in minimum spanning tree
	public int parent;//parent
	
	public PrimNode (int pos) {
		position = pos; //node position in graph
		weight = 16000;//final weight of edge in node
		//weight = Integer.MAX_VALUE;//not working!!!!!!!!!
		parent=-1;//null parent
	}
	
	//override compareTo method for PriorityQueue comparison
	public int compareTo(PrimNode node) {
        if (node.getWeight()<this.getWeight()){
        	return 1;
        } else if(node.getWeight()>this.getWeight()){
        	return -1;
        }
        return 0;
   }
	
	//set parent node
	public void setParent(int p){
		this.parent = p;
	}
	
	//get node position
	public int getPosition(){
		//System.out.println(this.position);
	return this.position;
	}
	
	//get parent node
	public int getParent(){
		return this.parent;
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
