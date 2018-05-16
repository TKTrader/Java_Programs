package bt;

import java.util.Arrays;
import java.util.LinkedList;

public class BPlusTree {
	//*******************************************************************************************************
	// BPlusTree  Implementation - Thomson Kneeland  - CS 212
	// Implementation of BPlusTree
	// Used Cormen's BTree as starting point in terms of class layout and functions
	// otherwise no pseudocode used, as the methods were so different
	// B Plus Tree of order n features a root, Internal Pages , and Leaf Pages
	// Internal Pages can have n children and n-1 keys
	// Leaf Nodes have n-1 keys, 1 parent, and a link to a neighboring leaf node for range queries
	// B+ Tree is an ideal database layout
	// Insert function and Search functions implemented and working for multiple test cases
	// bugs can certainly be found for other test cases using more random test cases, a work in progress
	// no delete page function was implemented
	// helper function SplitChild implemented (like Cormen BTree), added splitParent and other functions as well
	// Stores leafPages and InternalPages in linkedlists for retrieval/printout
	//for future, own implementation of array sort may be better
	//******************************************************************************************************
	
	static Page root;
	static LeafPage leafRoot;//root of BTree//issue with object type
	static InternalPage internalRoot;
	static boolean isRootLeaf;//variable for selecting root to avoid Object class issues in methods (PROGRAMMING FAILURE)
	static int order;//order of tree
	static LinkedList<LeafPage> leaves = new LinkedList<LeafPage>(); //linked list for leaves
	static LinkedList<InternalPage> index = new LinkedList<InternalPage>();
	static int[] split;

	//constructor
	public BPlusTree(int t, int k){
		order = t; //order of tree
		isRootLeaf=true;//variable for determining which root to use
		LeafPage x = new LeafPage(order, k);//create initial root node with value k
		leaves.add(x);//add root to Linked List
		this.setRoot(x);//make this node root
		split = new int[order];//initialize array used for splitting 
		printLeaves();//print to console
	}
	
	//method for setting root
	public void setRoot(LeafPage x){
		leafRoot=x;
	}
	
	//method for setting root
	public void setRoot(InternalPage x){
		internalRoot=x;
	}
	
	//split-child method, insert key i into full LeafPage node
	static void splitChild(LeafPage x, int i){//MAY BE AN ISSUE NOT UPDATING INTERNAL NODE Z WHEN PARENT SPLITS
		System.out.println("**********************");
		System.out.println("splitChild function");
		System.out.println("**********************");
		//change root to InternalRoot, if needed
		if (isRootLeaf){
			isRootLeaf=false;
		}
		//store keys from node into array, sort, and split
		for (int j=0; j<order-1;j++){
			split[j]=x.keys[j];
		}
		split[order-1]=i;//insert new key into array
		Arrays.sort(split);//sort array WORKS
		
		//Print split TEST
		for (int num:split){
			System.out.println(num);
		}
		
		//initialize new LeafPage z and add first half of keys
		LeafPage z = new LeafPage(order);
		for (int k=0; k<order /2;k++){
			z.setKeyNoSort(k, split[k]);
		}
		z.setCounter(order/2);//reset z counter
		z.setLink(x);
		
		//add second half of keys to x and 0's for remaining
		for (int p=0; p<(order-1);p++){
			if (p<(order+1)/2){//use ceiling function to access remainder of array
					x.setKeyNoSort(p, split[p+(order)/2]);
			}else{
				x.setKeyNoSort(p,0);
					}
			}
			x.setCounter((order+1)/2);//reset x counter
		
		//if x is root, create 1st parentNode
		if (x.getParent()==null){
			System.out.println("x.getParent: "+x.getParent());
			InternalPage y = new InternalPage(order,split[order/2]);
			y.makeChild(z,0);//set Children
			y.makeChild(x,1);
			x.setParent(y);//set Parents
			z.setParent(y);
			internalRoot=y;//make y new root
			index.add(y);
		}else if (!x.getParent().isFull()){
		//If parent not full, insert key into parent
			System.out.println("Parent not full: counter = "+x.getParent().getCounter());
			x.getParent().insertKey(x.getKey(0));
			for (int y=0; y<x.getParent().getCounter();y++){
				System.out.println("parent keys");
				System.out.println(y);
				System.out.println(x.getParent().getKey(y));
			}//good
			Arrays.sort(x.getParent().children,0,x.getParent().numChildren);//sort children of parent
			System.out.println("Z PArent "+z.getParent());
			z.setParent(x.getParent());//set parent of z
			z.getParent().makeChild(z, z.getParent().getCounter());//set z as child
			System.out.println("Z PArent "+z.getParent());
			
		}else if (x.getParent().isFull()){//if parent full, split parent and insert key
//			System.out.println("Parent is Full: counter = "+x.getParent().getCounter());
			BPlusTree.splitParent(x.getParent(),x.getKey(0));
		}
		Arrays.sort(x.getParent().children,0,x.getParent().numChildren);//move this internally?
		//Arrays.sort(z.getParent().children,0,z.getParent().getNumChildren());//potentially unnecessary
//		for (int num=0; num< x.getParent().numChildren;num++ ){
//			System.out.println(x.getParent().getChild(num));
//		}//good
		
		leaves.add(z);//add new node to LinkedList of leaf nodes
		System.out.println("internal root: "+internalRoot.getCounter());
	}
	 
	   //split-parent method, insert key i into full InternalPage node
		static public void splitParent(InternalPage x, int i){
			System.out.println("**********************");
			System.out.println("SPLIT PARENT FUNCTION");
			System.out.println("**********************");
			//store keys from node into array for split
			for (int j=0; j<order-1;j++){
				split[j]=x.keys[j];
			}
			split[order-1]=i;//insert new key into array
			Arrays.sort(split);//sort array WORKS
			
			//create new LeafPage and add first half of keys
			InternalPage z = new InternalPage(order);
			for (int k=0; k<order/2;k++){
				z.setKeyNoSort(k, split[k]);
				z.makeChild(x.getChild(k), k);
			}
			z.setCounter(order/2);//reset z counter
			z.setParent(x.getParent());//set z parent
			index.add(z);//add new node to LinkedList of Internal nodes
			
			//re-initialize x with second half of keys
			for (int p=0; p<(order-1);p++){
				if (p<(order+1)/2){//use ceiling function to access remainder of array
					x.setKeyNoSort(p, split[p+order/2]);//good for odd
					x.makeChild(x.getChild(p+order/2),p);
				}else{
					x.setKeyNoSort(p,0);
				}
				
			}
			x.numChildren=(order+1)/2;//reset number of Children
			x.setCounter((order+1)/2);//reset x counter, good for odd
			
			//if x is root, create parentNode and set to root
			if (x.getParent()==null){
				InternalPage y = new InternalPage(order,x.getKey(0));
				y.makeChild(z,0);//set Children
				y.makeChild(x,1);
				index.add(y);
				internalRoot=y;
			}else if (!x.getParent().isFull()){//If parent not full, insert key into parent
				x.getParent().insertKey(x.getKey(0));
			}else if (x.getParent().isFull()){//if parent full, split parent and insert key
				System.out.println("split parent of parent");
				BPlusTree.splitParent(x.getParent(),x.getKey(0));
			}
		}
	
		//Method for inserting a file key into tree 
		static public void treeInsert(int k){
		//search for node through root and find node
		  //makes sure key positive//Tested and works
//			System.out.println("inserting "+k);
		  	if(k<1){
		  		System.out.println("Input Error: Key Value must be a positive integer (>0)");
		  		return;
		  	}
		  	//if root is leaf, execute accordingly:
		  	if (isRootLeaf){
		  		if (leafRoot.isFull()){//if root full, split
		  			//System.out.println("root is full");
		  			splitChild(leafRoot,k);
		  			isRootLeaf=false;//from now on use InternalRoot
		  		}else{
		  			leafRoot.InsertPage(k);
		  		}
		  		
		  	}else{//if root is not leaf, start at InternalRoot
		  		if (!internalRoot.isFull()){
//		  			System.out.println("inserting page, root not full");//GOOD
		  			internalRoot.InsertPage(k);
		  			return;
		  		}
			//if x full, split node and insert key 
			if (internalRoot.isFull() ){
//				System.out.println("inserting page, root full, 296 TREEINSERT");//GOOD
				splitParent(internalRoot,k);
				//BPlusTree.treeInsert(k);//not needed
			}
			}
		}
		
	//starting method for search, determines which root to use
	public void rootSearch(int k){
		if (isRootLeaf){
			Search(leafRoot,k);
		}else{
			Search(internalRoot,k);
		}
	}
	//second method for search using determined root
	public void Search(Page p, int k){ //CREATE TRY CATCH  WILL HAVE SAME ISSUES AS INTERNALPAGE INSERT
		boolean found = false;
		
		if (p.getLeaf()){
			for (int i=0; i<p.getCounter();i++){
				if (p.getKey(i)==k){
					System.out.println("Search for "+k+ " complete:");
					System.out.println("Page is " + p+ ", key index is: " +i+"\n");
					found = true;
				}
			}
			if (found == false){//key not in database
				System.out.println("Search for "+k+" complete:");
				System.out.println("key not found\n");
			}
			
		//return leafRoot;
		}else{//if more than one node
			if(p.counter==1){
				if(k<p.keys[0]){
					Search(p.getChild(0),k);
				}else if (k>=p.keys[0]){
					Search(p.getChild(1),k);
				}
			}else if (k<p.keys[0]){
			//if search path follows 1st key if (k<this.keys[0]){
				Search(p.getChild(0),k);
			}else if (k>=p.keys[p.counter-1]){//if search path follows last key
				Search(p.getChild(p.counter-1),k);
			}else{
				for (int i=0;i<p.getCounter()-1;i++){//check middle terms of path
					if ((k>=p.keys[i]) && (k<p.keys[i+1])){
						Search(p.getChild(i),k);
						break;
					}
				}
			}
		}
	}

	//Method to print leaves
	public static void printLeaves(){
		System.out.println("LEAF PAGES");
		System.out.println("*****************************");
		for (LeafPage leaf : leaves){
			System.out.println("counter is: " +leaf.counter);
			for (int i=0;i<order-1;i++){ 
				System.out.println("leaves key is "+leaf.getKey(i));
			}
			System.out.println("parent is: "+leaf.getParent());
			System.out.println("link is: "+leaf.getLink());
			System.out.println("*****************************");
		}
		System.out.println();
	}
	
	//Method to print internalPages
		public static void printInternalPages(){
			System.out.println("INTERNAL PAGES");
			System.out.println("*****************************");
			for (InternalPage page : index){
				System.out.println("counter is: " +page.counter);
				for (int i=0;i<order-1;i++){ 
					System.out.println("pages key is "+page.getKey(i));
				}
				System.out.println("Children:");
				for (int i=0;i<page.getNumChildren();i++){
					System.out.println(page.getChild(i));
				}
				System.out.println("*****************************");
			}
			System.out.println();
		}
	
	public static void main(String[] args) {
		
		//TESTS
		
//		//TEST CASE  
		//Test order 20, 1000 values
//		BPlusTree q = new BPlusTree(20,1000);//good
//		int test;
//		for (int i=100; i>0;i--){
//			test=i*6+1;
//			q.treeInsert(test);
//			}
//		for (int i=0; i<100;i++){
//			test=i*6-1;
//			q.treeInsert(test);
//		}
//		q.printLeaves();
//		q.printInternalPages();
		//************************************************

		//****************************************
		//TEST CASE 2
//		BPlusTree q;
//		LeafPage x;
//		Page y;
//		int ord=4;
//		q = new BPlusTree(ord,100);//good
//		q.treeInsert(2);
//		q.printLeaves();
//		q.treeInsert(50);
//		q.printLeaves();
//		q.treeInsert(5);
//		q.printLeaves();
//		q.printInternalPages();
//		q.treeInsert(7);
//		q.printLeaves();
//		q.printInternalPages();
//		q.treeInsert(200);
//		q.printLeaves();
//		q.printInternalPages();
//		q.treeInsert(150);
//		q.printLeaves();
//		q.printInternalPages();
//		q.treeInsert(75);//NOT WORKING, ENTERING MIDDLE NODE INSTEAD OF LAST NODE//ADDS TO INTERNAL NODE OF 2..NO!!
//		q.printLeaves();
//		q.printInternalPages();
//		q.treeInsert(3);//3 NOT INSERTING PROPERLY
//		q.printLeaves();
//		q.printInternalPages();
//		q.treeInsert(768);
//		q.printLeaves();
//		q.printInternalPages();
//		q.treeInsert(80);
//		q.printLeaves();
//		q.printInternalPages();
//		
//		q.rootSearch(50);//works
//		q.rootSearch(200);//WORKS
//		q.rootSearch(201);//WORKS
		
		//*******************************************
		
//		//TEST CASE 3 
//		//Test order 20, 1000 values
		BPlusTree q2 = new BPlusTree(5,1000);//good
		for (int i=1; i<1000;i++){
			q2.treeInsert(i);
			q2.printLeaves();
			q2.printInternalPages();
			}
		//************************************************
		}
	}
