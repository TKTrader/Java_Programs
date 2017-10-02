package sorting;

import java.util.*;

public class Sort2 {
	//********************************************************************************************
	//     Author: Thomson Kneeland                                     CS212_01 Data Structures
	//a) Implementation of Heap Sort Algorithm
	//b) Implementation of Quick Sort Algorithm - recursive divide and conquer
	//c) Implementation of Counting Sort Algorithm 
	//additional functions added to print_array method for ease of viewing array output for debugging
	//********************************************************************************************
	
	public static int left (int i) {//index of left child
		return 2*(i+1)-1;
	}
	
	public static int right (int i) {//index of right child
		return 2*(i+1);
	}
	
	public static int parent (int i) {//index of parent
		return (i-1)/2;
	}
	
	public static int[] max_heapify (int[] array, int heap_size, int i) {
		int l, r; //indices for left and right children
		int largest=0;//variable to store largest value in comparison
		int swap;//variable for storing swap variables
		
		int sub[]= new int[heap_size];//create subarray based on heap size given
		for (int ind=0;ind<heap_size;ind++){//initialize subarray with corresponding values from array
			sub[ind]=array[ind];
		}
		
		l = Sort2.left(i);//index of left child
		r = Sort2.right(i);//index of right child

		if ((l<heap_size)&&(sub[l]>sub[i])){//determine largest value of left child and parent, if child exists
			largest=l;
		}else{
			largest=i;
		}
		if ((r<heap_size)&&(sub[r]>sub[largest])){//compare r value to largest value, if r exists
			largest=r;
		}
		if (largest!=i){//if largest is not parent, exchange parent value with largest child value
			swap=sub[i];
			sub[i]=sub[largest];
			sub[largest]=swap;
			Sort2.max_heapify(sub,heap_size,largest);//call maxheapify upon child with changed value
		}

		for (int ind=0;ind<heap_size;ind++){//return transformed subarray values to original array
			array[ind]=sub[ind];
		}
		return array;
	}
	
	public static int[] build_heap (int[] array) {
		int heap_size = array.length;//heap size
		int node=Sort2.parent(heap_size);//node to max_heapify, starting at middle element of binary tree
		for (int i=node; i>=0; i--){//max_heapify each node down to start of array
			Sort2.max_heapify(array,heap_size,i);
		}
		return array;
	}
	
	public static int[] heap_sort (int[] array) {
		Sort2.build_heap(array);
		int heap_size2 = array.length;//heap size
		int swap2;//variable for storing swap value
		for (int i=(array.length-1);i>0;i--){
			swap2=array[i];
			array[i]=array[0];
			array[0]=swap2;
			heap_size2-=1;// decrement heap size for next iteration, since last element now sorted
			Sort2.max_heapify(array, heap_size2, 0);
		}
		return array;
	}
	
	public static int[] quick_sort (int[] array, int p, int r) {
		int q;//index for partitioning array
		if(p<r){
			q=partition(array, p, r);//find index for partition
			quick_sort(array, p, q-1);//left array, recursively call quicksort
			quick_sort(array, q+1, r);//right array, recursively call quicksort
		}
		return array;
	}
	
	public static int partition (int[] array, int p, int r) {
		int x = array[r];//last value of array
		int i = p-1;
		int holder;//storage variable for when swapping two indices
		for (int j=p; j<r;j++){
			if(array[j]<=x){//if entry less than pivot, exchange element to left side at index i
				i+=1;
				holder=array[j];//storage variable for swap
				array[j]=array[i];//swap lesser entry to left side
				array[i]=holder;//swap left side variable to entry
			}
		}
		holder=array[r];
		array[r]=array[i+1];
		array[i+1]=holder;
		return i+1;
	}
	
	/*
	 * the values in array range from 0 to k
	 */
	public static int[] counting_sort (int[] array, int k) {
		int n = array.length;//array length
		int[] tempC = new int[k+1];//temporary storage for counts, automatically initialized with 0's
		int[] sortedB = new int[n];//final array in sorted order
		
		//find count of elements of each value of array and store count in tempC
		for (int j=0;j<n;j++){
			tempC[array[j]]+=1;
		}
		
		//find number of elements less than or equal to i (element of tempC)
		for (int i = 1;i<k+1;i++){
			tempC[i]=tempC[i]+tempC[i-1];
		}
		
		//place element in correct sorted position and decrement tempC element to move array entry position
		for (int z = n-1; z>=0;z--){
			sortedB[(tempC[array[z]]-1)]=array[z];
			tempC[array[z]]-=1;
		}
		return sortedB;
	}
	
	/*
	 * n: the size of the output array
	 * k: the maximum value in the array
	 */
	public static int[] generate_random_array (int n, int k) {
		List<Integer> list;
		int[] array;
		Random rnd;
		
		rnd = new Random(System.currentTimeMillis());
		
		list = new ArrayList<Integer> ();
		for (int i = 1; i <= n; i++) 
			list.add(new Integer(rnd.nextInt(k+1)));
		
		Collections.shuffle(list, rnd);
		
		array = new int[n];
		for (int i = 0; i < n; i++) 
			array[i] = list.get(i).intValue();
		
		return array;
	}
	
	/*
	 * n: the size of the output array
	 */
	public static int[] generate_random_array (int n) {
		List<Integer> list;
		int[] array;
		
		list = new ArrayList<Integer> ();
		for (int i = 1; i <= n; i++) 
			list.add(new Integer(i));
		
		Collections.shuffle(list, new Random(System.currentTimeMillis()));
		
		array = new int[n];
		for (int i = 0; i < n; i++) 
			array[i] = list.get(i).intValue();
		
		return array;
	}
	
	/*
	 * Input: an integer array
	 * Output: true if the array is sorted in ascending order, otherwise return false
	 */
	public static boolean check_sorted (int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i-1] > array[i])
				return false;
		}
		return true;
	}
	
	public static void print_array (int[] array) {
		for (int i = 0; i < array.length; i++){
			System.out.print(array[i] + ", ");
			if ((i+1)%30==0){//limit output to 30 numbers per line for readability
				System.out.println();
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int k = 100;
		
		System.out.println("Heap sort starts ------------------");
		for (int n = 10; n <= 100000; n=n*2) {
			int[] array = Sort2.generate_random_array(n);
			//Sort2.print_array(array);  Uncomment to view original array
			long t1 = System.currentTimeMillis();
			array = Sort2.heap_sort(array);
			long t2 = System.currentTimeMillis();
			long t = t2 - t1;
			boolean flag = Sort2.check_sorted(array);
			//Sort2.print_array(array);  Uncomment to view sorted array
			System.out.println(n + "," + t + "," + flag);
		}
		System.out.println("Heap sort ends ------------------");

		
		System.out.println("Quick sort starts ------------------");
		for (int n = 10; n <= 100000; n=n*2) {
			int[] array = Sort2.generate_random_array(n);
			//Sort2.print_array(array);  Uncomment to view original array
			long t1 = System.currentTimeMillis();
			array = Sort2.quick_sort(array, 0, n-1);//sort entire array
			long t2 = System.currentTimeMillis();
			long t = t2 - t1;
			boolean flag = Sort2.check_sorted(array);
			//Sort2.print_array(array);  Uncomment to view sorted array
			System.out.println(n + "," + t + "," + flag);
		}
		System.out.println("Quick sort ends ------------------");
		
		System.out.println("Counting sort starts ------------------");
		for (int n = 10; n <= 100000; n=n*2) {
			int[] array = Sort2.generate_random_array(n, k);//k=100
//			Sort2.print_array(array);  //Uncomment to view original array
			long t1 = System.currentTimeMillis();
			array = Sort2.counting_sort(array, k);
			long t2 = System.currentTimeMillis();
			long t = t2 - t1;
			boolean flag = Sort2.check_sorted(array);
//			Sort2.print_array(array);  //Uncomment to view sorted array
			System.out.println(n + "," + t + "," + flag);
		}
		System.out.println("Counting sort ends ------------------");
	}
}