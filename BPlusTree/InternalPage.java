package bt;

import java.util.Arrays;

public class InternalPage extends Page{
	//InternalPage subclass, used to index the B+Tree
	Page[] children;//array for maintaining children
	int numChildren=0;
	
	//Constructor
	InternalPage(int order){
		leaf=false;
		minDegree=order/2;///not sure 
		children = new Page[order];
		keys = new int[order-1];//m-1!!!!!!!!
		counter=1;//# of elements in keys array
		numChildren = 0;
		this.order=order;
	}
	//Constructor for new root node; order and key insert
	InternalPage(int order, int x){
			leaf=false;
			minDegree=order/2;///not sure 
			children = new Page[order];
			keys = new int[order-1];
			counter=1;//# of elements in keys array
			keys[0]=x;
			numChildren = 0;
			this.order=order;
		}
	
	//override compareTo method for node comparison
	public int compareTo(InternalPage node) {
		for (int i=0;i<node.getNumChildren();i++){
    	}
        if (node.getKey(0)<this.getKey(0)){
        	return -1;
        } else if(node.getKey(0)>this.getKey(0)){
        	return 1;
        }
        return 0;
   }
	
	//retrieve child designated by key x
	public Page getChild(int pos){
		//System.out.println(children[pos]);
		return children[pos];
	}
	
	public int getNumChildren(){
		return this.numChildren;
	}
	
	public void makeChild(Page b, int position){//watch object type
		this.children[position]=b;
		Arrays.sort(this.children,0, position);
		this.numChildren++;
	}
	
	//recursively search through all keys
	//insert in final leaf node when found
	public void InsertPage(int k){
		System.out.println("InsertPage "+k+": InternalPage");
		//special instance : only one key in InternalPage
		if(counter==1){
			System.out.println("counter=1");
			if(k<this.keys[0]){
				this.getChild(0).InsertPage(k);
			}else if (k>=this.keys[0]){
				this.getChild(1).InsertPage(k);
			}
		}else if (k<this.keys[0]){
		//if search path follows 1st if (k<this.keys[0]){
			this.getChild(0).InsertPage(k);
//			System.out.println("path = first");
		}else if (k>=this.keys[counter-1]){//if search path follows last key
			this.getChild(counter).InsertPage(k);
//			System.out.println("path = last");
		}else{
			for (int i=0;i<this.getCounter()-1;i++){//check middle terms of path
				if ((k>=this.keys[i]) && (k<this.keys[i+1])){
//					System.out.println("path = middle");
					this.getChild(i).InsertPage(k);
//					System.out.println(" Internal path2 =middle");
				}
			}
		}
		//Arrays.sort(this.children,0,this.numChildren);//unneeded??
	}
	
	public void insertKey(int x){
		System.out.println("insertKey: Internal");
		keys[counter]=x;
		counter++;//increment counter
		Arrays.sort(keys,0,counter);//sort//changed value BEWARE
	}
}
