package sorting;

import java.util.*;

public class Sort {
	//********************************************************************************************
	//     Author: Thomson Kneeland                                     CS212_01 Data Structures
	//a) Implementation of Insertion Sort Algorithm
	//b) Implementation of Merge Sort Algorithm - uses divide and conquer method via recursion
	//additional functions added to print_array method for ease of viewing array output for debugging
	//********************************************************************************************
	
	/*
	 * INSERTION SORT ALGORITHM
	 * Input: an integer array
	 * Output: sorted array
	 */
	public static int[] insertion_sort (int[] array) {
		int i; //array position
		int key; //key to store number being sorted
		for (int j=1; j<array.length; j++){//iterate for each key in array
			key=array[j];//store key
			i=j-1;//element position to compare to key
			while(i>0 && (array[i]>key)){//compare key to values of sorted array
				array[i+1]=array[i];
				i=i-1;
			}
			array[i] = key;//place key in correct position of array
			}
		return array;
	}
	
	/*
	 * MERGE SORT ALGORITHM
	 * Input: an integer array, index of first subarray element, index of final element in subarray
	 * Output: sorted array
	 */
	public static int[] merge_sort (int[] array, int p, int r) {
		int q;//midpoint of array
		if (p<r){
			q = (p+r)/2;
//			Sort.print_array(array);  //UNCOMMENT TO VIEW INPUT ARRAY
			Sort.merge_sort(array, p, q);
			Sort.merge_sort(array, q+1, r);
			Sort.merge(array, p, q, r);
//			Sort.print_array(array); //UNCOMMENT TO VIEW OUTPUT MERGED/SORTED ARRAY
		}
		return array;
	}
	
	/*
	 * MERGE ALGORITHM
	 * Input: an integer array, p: index of first subarray element, q: split point for subarrays, r: index of final element in subarray  (p <= q < r)
	 * two subarrays must be sorted for implementation to produce sorted output; code uses recursion to do this
	 * Output: sorted parent array
	 */
	public static int[] merge (int[] array, int p, int q, int r) {
		int n1, n2;
		int[] subL, subR;
		
		n1=q-p+1; 
		n2=r-q; //variables for index endpoints of split subarray
		
		//Create two subarrays with extra element to include sentinel value
		subL = new int[n1+1];		
		subR = new int[n2+1]; 
		
		//Subdivide parent array into 2 subarrays:  Assign elements to subarrays based on midpoint of parent array
		for (int i = 0; i<n1;i++){
			subL[i]=array[p+i-1];
			//subL[i]=array[p+i];
		}
		for (int j = 0; j<n2;j++){
			subR[j]=array[q+j];
			//subR[j]=array[q+j+1];
		}
		
		Integer infinity = Integer.MAX_VALUE;  //create max sentinel value
		subL[n1]=infinity; subR[n2]=infinity;//add sentinel values to end of subarrays
		
		//merge two sorted subarrays
		int i=0; int j=0;//index variables for two subarrays
		//iteratively transfer lowest value of 2 subarrays into parent array
		for (int k=p-1;k<r;k++){
			if (subL[i]<=subR[j]){
				array[k] = subL[i];
				i++;
			} else{
				array[k]=subR[j];
				j++;
			}
		}
		return array;

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
			if (array[i-1] > array[i])//checks adjacent elements for proper sorting
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
		System.out.println("\n");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Insertion sort starts ------------------");
		for (int n = 10; n <= 100000; n=n*2) {
			int[] array = Sort.generate_random_array(n);//generate array of length n; increment by n*2 for next iteration
			long t1 = System.currentTimeMillis();
			array = Sort.insertion_sort(array);
			long t2 = System.currentTimeMillis();
			long t = t2 - t1;//find time of sort operation
			boolean flag = Sort.check_sorted(array);//check if array sorted or not
			System.out.println(n + "," + t + "," + flag);
		}
		System.out.println("Insertion sort ends ------------------");

		
		System.out.println("Merge sort starts ------------------");
		for (int n = 10; n <= 100000; n=n*2) {
			int[] array = Sort.generate_random_array(n);//generate array of length n; increment by n*2 for next iteration
			long t1 = System.currentTimeMillis();
			array = Sort.merge_sort(array, 1, n);
			long t2 = System.currentTimeMillis();
			long t = t2 - t1;//find time of sort operation
			boolean flag = Sort.check_sorted(array);//check if array sorted
			System.out.println(n + "," + t + "," + flag);
		}
		System.out.println("Merge sort ends ------------------");
	}
}