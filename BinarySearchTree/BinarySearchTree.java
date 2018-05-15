package ds;

public class BinarySearchTree {
	//********************************************************************************************
	//     Author: Thomson Kneeland                                     CS212_01 Data Structures
	// Implementation of Binary Search Tree
	// methods include inorder_tree_walk, search, iterative_search, minimum, maximum, insert,
	// successor and two extra methods: preorder_tree_walk, post_order_tree_walk
	// Also added subTreeMinimum method
	//********************************************************************************************
	public TreeNode root;
	
	public BinarySearchTree () {
		root = null;//set root to null for constructor
	}
	
	public void inorder_tree_walk (TreeNode x) {//walk through tree in order, should result from min to max
		if (x!=null){
			inorder_tree_walk(x.left);
			System.out.println(x.key);
			inorder_tree_walk(x.right);
		}
	}
	
	public void preorder_tree_walk (TreeNode x) {//walk through tree in order, should result from min to max
		if(x!=null){
			System.out.println(x.key);
			preorder_tree_walk(x.left);
			preorder_tree_walk(x.right);
		}
	}
	
	public void postorder_tree_walk (TreeNode x) {//walk through tree in order, should result from min to max
		if(x!=null){
			postorder_tree_walk(x.left);
			postorder_tree_walk(x.right);
			System.out.println(x.key);
		}
	}
	
	public TreeNode search (TreeNode x, int k) {//searches tree for node with key k
		if((x==null)||(k==x.key)){
			return x;
		}
		if(k<x.key){
			return search(x.left,k);
		}else{
			return search(x.right,k);
		}
	}
	
	public TreeNode iterative_search (int k) {//iterative search through binary tree
		TreeNode node = new TreeNode(k);//node to iteratively search for k
		while ((node!=null)&&(k!=node.key)){
			if (k<node.key){
				node=node.left;
			}else{
				node=node.right;
			}
		}
		return node;
	}
	
	public TreeNode minimum () {//searches through all left children to smallest key; minimum of entire tree
		TreeNode min = new TreeNode();
		min = root;
		while(min.left!=null){
			min=min.left;
		}
		return min;
	}
	
	public TreeNode subTreeMinimum (TreeNode x) {//2nd function for minimum in order to properly implement Tree-Successor from any given subtree
		while(x.left!=null){
			x=x.left;
		}
		return x;
	}
	
	public TreeNode maximum () {//searches through all right children to largest key
		TreeNode max = new TreeNode();
		max = root;
		while(max.right!=null){
			max=max.right;
		}
		return max;
	}
	
	public TreeNode successor (TreeNode x) {//iterates to right child and all subsequent left children
		TreeNode y = new TreeNode();//variable for swapping
		//TreeNode newRoot = new TreeNode();
		if(x.right!=null){//if right child exists, find minimum of right child subtree
			return subTreeMinimum(x.right);
			//newRoot = x;
			//return minimum();
		}
		y = x.p;//make y parent node of x
		while((y!=null)&&(x==y.right)){//iterates up tree until first parent on right is found
			x=y;
			y=y.p;
		}
		return y;
	}
	
	public void insert (int k) {//inserts node into tree
		TreeNode y = new TreeNode();//node for swapping
		TreeNode x = new TreeNode();//create node for root
		TreeNode z = new TreeNode(k);//create node to insert
		y=null;
		x=root;
		while (x!=null){
			y=x;
			if(z.key<x.key){
				x=x.left;
			}else{
				x=x.right;
			}
			z.p=y;
			}
		if (y==null){//if tree empty
			root = z;
		}else{
			if (z.key<y.key){
				y.left = z;
			}else{
				y.right = z;
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {15, 6, 18, 3, 7, 17, 20, 2, 4, 13, 9};
		BinarySearchTree bst;
		TreeNode n;
		
		bst = new BinarySearchTree();
		for (int i = 0; i < array.length; i++)
			bst.insert(array[i]);
		
		System.out.println("Inorder_tree_walk starts ------------------");
		bst.inorder_tree_walk(bst.root);
		System.out.println("Inorder_tree_walk ends ------------------");
		System.out.print("\n\n");
		
		System.out.println("Preorder_tree_walk starts ------------------");
		bst.preorder_tree_walk(bst.root);
		System.out.println("Preorder_tree_walk ends ------------------");
		System.out.print("\n\n");
		
		System.out.println("Postorder_tree_walk starts ------------------");
		bst.postorder_tree_walk(bst.root);
		System.out.println("Postorder_tree_walk ends ------------------");
		System.out.print("\n\n");
		
		System.out.println("Search starts ------------------");
		n = bst.search(bst.root, 13);
		System.out.println("found: " + n.key);
		System.out.println("Search ends ------------------");
		System.out.print("\n\n");

		System.out.println("Iterative search starts ------------------");
		n = bst.iterative_search(4);
		System.out.println("found: " + n.key);
		System.out.println("Iterative search ends ------------------");
		System.out.print("\n\n");
		
		System.out.println("Minimum starts ------------------");
		n = bst.minimum();
		System.out.println("Minimum key is " + n.key);
		System.out.println("Minimum ends ------------------");
		System.out.print("\n\n");
		
		System.out.println("Maximum starts ------------------");
		n = bst.maximum();
		System.out.println("Maximum key is " + n.key);
		System.out.println("Maximum ends ------------------");
		System.out.print("\n\n");

		System.out.println("Successor starts ------------------");
		//n = bst.successor(bst.root.left.right.right);
		n = bst.successor(bst.root.left.right);
		System.out.println("Successor key is " + n.key);
		System.out.println("Successor ends ------------------");
		System.out.print("\n\n");
	}

} 