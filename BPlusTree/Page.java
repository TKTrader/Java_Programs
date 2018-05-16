package bt;

import java.util.Arrays;

public class Page{
	//Parent Page class 
	
	public boolean leaf;
	public int minDegree;//minimum number of keys in node
	public int order;//maximum number of keys node
	public int key;//key of node for tree hierarchy
	public InternalPage parent;
	public Page[] children;//array for maintaining children
	public int[] keys;//set to size
	int counter; 
	
	//override compareTo method for node comparison
	public int compareTo(Page node) {
		System.out.println("Page compare");
        if (node.getKey(0)<this.getKey(0)){
        	return -1;
        } else if(node.getKey(0)>this.getKey(0)){
        	return 1;
        }
        return 0;
   }
	
	//get counter
	public int getCounter(){
		return this.counter;
	}
	
	//get key
	public int getKey(int x){
		return keys[x];
	}
	
	//get index of key in node; if not found, return -1
	public int getKeyIndex(int x){
		int index=-1;
		for (int i=0; i<this.counter;i++){
			if (keys[i]==x){
				index = i;
			}
		}
		return index;
	}
	
//	public void insertKey(int x){
//		keys[counter]=x;
//		Arrays.sort(keys,0,counter);//sort//changed value BEWARE
//		counter++;//increment counter
//	}
	
	//method to check if Page contains key
	public boolean containsKey(int x){
		boolean keyInArray=false;//variable to monitor whether key is in array
		for (int num: keys){
			if (num==x){
				keyInArray= true;
			}
		}
		return keyInArray;
	}
	
	//search page for specified value.//NOT CORRECT
	public void NodeSearch(int k){
		int i=0;
		int p=0;
		while (i<this.getCounter()&&k>this.getKey(i)){//increment dummy variable up to key or maxKey in Page
			i++;
		}
		if (i<this.getCounter()&&k==this.getKey(i)){//key found in page
			System.out.println("Page is " + this + ", key index is: " +i);
		}else{
			this.getChild(p).NodeSearch(k);
		}
	}
	
	//recursively search through all keys
	//insert in final leaf node when found
	public void InsertPage(int k){
	//	System.out.println("InsertPage "+k+": Page");
		if (this.leaf){//insert only if leaf node
			setKey(counter,k);
			return;
		}
		//if search path follows 1st key
		if (k<this.getKey(1)){
			this.getChild(0).InsertPage(k);
		}else if (k>=this.keys[counter-1]){//if search path follows last key
			this.getChild(counter-1).InsertPage(k);
		}else{
			for (int i=1;i<this.getCounter()-1;i++){//check middle terms of path
				if ((k>=this.keys[i]) && (k<this.keys[i+1])){
					this.getChild(i).InsertPage(k);
					break;
				}
			}
		}
	}
	
	//set Key
	public void setKey(int pos, int value){
		keys[pos]=value;
		counter++;
		Arrays.sort(keys,0, counter);
	}
	
	//set Key, no Sort
	public void setKeyNoSort(int pos, int value){
		keys[pos]=value;
	}
	
	//set Counter
	public void setCounter(int count){
		this.counter=count;
	}
	
	//get Parent
	public InternalPage getParent(){
		return this.parent;
	}
	
	//set Parent
	public void setParent(InternalPage x){
		this.parent=x;
	}
	
	//retrieve child designated by key x
	public Page getChild(int pos){
		System.out.println(children[pos]);
		return children[pos];
	}
	
	//check if keys array full
	public boolean isFull(){
		if (counter<order-1){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getLeaf(){
		return this.leaf;
	}
	//set type of node
	public void setLeaf(boolean x){
		this.leaf=x;
	}
}