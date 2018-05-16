package bt;

import java.util.Arrays;

public class LeafPage extends Page implements Comparable<LeafPage>{
	// order  = maximum number of keys in a Page
	public LeafPage link; //link to next node
	
	//constructor for new LeafPage
	LeafPage(int ord){//order
		leaf=true;
		order=ord;//maximum number of keys per node
		keys=new int[order-1];//array allows for sorting before split
		counter=1;//# of elements in keys array
	}
	
	//constructor for new LeafPage
	LeafPage(int ord, int k){//order, key
		leaf=true;
		order=ord;//maximum number of keys per node
		keys=new int[order-1];//
		keys[0]=k;
		counter=1;//# of elements in keys array
	}
	
	//override compareTo method for node comparison
	public int compareTo(LeafPage node) {
//		System.out.println("Leaf compare");
        if (this.getKey(0)>node.getKey(0)){
        	return 1;
        } else if(this.getKey(0)<node.getKey(0)){
        	return -1;
        }
        return 0;
   }
	
	//insert key into Leafnode 
	public void insertKey(int x){
		System.out.println("insertKey: Leaf");
		//check if input is duplicate entry
		if (this.containsKey(x)){
			System.out.println("Error: Duplicate key entry");
			return;
		}
		//makes sure inputs positive
		if(x<1){
			System.out.println("Input Error: You cannot input a negative value for a key");
			return;
		}
		
		if ((!this.containsKey(x)) && (!this.isFull())){
			keys[counter]=x;
			counter++;
			Arrays.sort(keys,0,counter);//sort
		}else if (this.isFull()){
			Arrays.sort(keys);//sort
			BPlusTree.splitChild(this, x);
			return;
		}
		
	}
	
	public void InsertPage(int k){
		System.out.println("InsertPage "+k+": Leaf");
		if (this.leaf){//insert only if leaf node
			insertKey(k);//move to leafnode search
		}
	}
	
	//overrides Page method
	public void NodeSearch(int x){
		if (this.containsKey(x)){
			System.out.println("Page is " + this + ", key index is: " + this.getKeyIndex(x));
		}else{
			System.out.println("Key is not in database");
		}
	}
	
	public void makeRoot(){
		this.leaf = false;
	}
	
	public LeafPage getLink(){
		return this.link;
	}
	
	public void setLink(LeafPage q){
		this.link = q;
	}
	
	public LeafPage getLeafNode(){
		return this;
	}
}
