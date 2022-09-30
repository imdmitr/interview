package com.interview.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class ArraysSearchSort {

	public static void main(String[] args) {
		
	}
	
	@Test
	public void testSolution() throws Exception {
		
	}

		
	@Test
	public void testFindClosestNumber() throws Exception {
		int[] a1 = createRandomArray(10);
		int[] a2 = createRandomArray(10);
		int targetSum = 50;
		
	}
	
	@Test
	public void testQuickSort() throws Exception {
		int[] data = createRandomArray(10);
		
		System.out.println("Before sort: " + Arrays.toString(data));
		
		quickSort(data);
		
		System.out.println("Quick Sort: " + Arrays.toString(data));
		
	}


	@Test
	public void testBubleSort() throws Exception {
		int[] data = createRandomArray(10);
		
		System.out.println("Before sort: " + Arrays.toString(data));
		
		bubleSort(data);
		
		System.out.println("Buble Sort: " + Arrays.toString(data));
		
	}

	@Test
	public void testSelectionSort() throws Exception {
		int[] data = createRandomArray(10);
		
		System.out.println("Before sort: " + Arrays.toString(data));
		
		selectionSort(data);
		
		System.out.println("Selection Sort: " + Arrays.toString(data));
		
	}

	
	private void selectionSort(int[] data) {
		for (int i = 0; i < data.length; i++) {
			int jMin = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < data[jMin]) {
					jMin = j;
				}
			}
			swap(data, i, jMin);
		} 
	}


	private static void swap(int[] data, int i, int jMin) {
		int t = data[jMin];
		data[jMin] = data[i];
		data[i] = t;
	}

	private void bubleSort(int[] data) {
		int n = data.length;
		boolean swapped = false;
		do {
			swapped = false;
			for (int i = 1; i < n; i++) {
				if (data[i - 1] > data[i]) {
					swap(data, i, i - 1);
					swapped = true;
				}
			} 
			n--;			
		} while (swapped);
		
	}

	private int[] createRandomArray(int size) {
		int[] data = new int[10];
		Random random = new Random();
		
		for (int i = 0; i < size; i++) {
			data[i] = random.nextInt(100);
		}
		return data;
	}
		
	@Test
	public void testBinarySearch() throws Exception {
		int[] data = new int[10];
//		Sorted arr
		for (int i = 0; i < 10; i++) {
			data[i] = i;
		}
		
		quickSort(data);
		
		assertEquals(4, binarySearch(data, 4));
		for (int i = 0; i < 10; i++) {
			assertEquals(i, binarySearch(data, i));
		}
		
		
		assertEquals(-1, binarySearch(data, 23));
	}

	/**
	 * Time complexity - O(log n)
	 * 
	 * @param data
	 * @param value
	 * @return
	 */
	private static int binarySearch(int[] data, int value) {
		int rez = -1;
		if (data == null || data.length == 0) {
			return rez;
		}
		int start = 0;
		int end = data.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			
			if (data[mid] == value) {
				return mid;
			} else if (value > data[mid]) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return rez;
	}
	
	/**
	 * Divide and conquer
	 * Average O(n log n), worst O(n^2)
	 * Space O(log n)
	 * 
	 * @param data
	 */
	public static void quickSort(int[] data) {
		if (data == null || data.length == 0) {
			return;
		}
		
		quickSort(data, 0, data.length - 1);
		
	}

	private static void quickSort(int[] data, int lo, int hi) {
		if (lo < hi) {
			int p = partition(data, lo, hi);
			quickSort(data, lo, p - 1);
			quickSort(data, p + 1, hi);
		}
	}

	private static int partition(int[] data, int lo, int hi) {
		int pivot = data[hi];
		int i = lo;
		for (int j = lo; j < hi; j++) {
			if (data[j] < pivot) {
				swap(data, i, j);
				i++;
			}
		}
		data[hi] = data[i];
		data[i] = pivot;
		return i;
	}
	
	
	/**
	 * https://en.wikipedia.org/wiki/Merge_sort
	 * O(n log n)
	 * @param data
	 */
	public static int[] mergeSort(int[] data) {
		if (data == null) {
			return null;
		}
		
		int[] merge = copyArray(data);
		mergeSort(data, 0, data.length, merge);
		return merge;
	}

	private static int[] copyArray(int[] data) {
		int[] c = new int[data.length];
		for (int i = 0; i < data.length; i++) {
			c[i] = data[i];
		}
		return c;
	}

	private static void mergeSort(int[] data, int lo, int hi, int[] merge) {
		if (hi - lo <= 1) {
			return;
		}
		
		
		int mid = (lo + hi) / 2;
		mergeSort(data, lo, mid, merge);
		mergeSort(data, lo + mid, hi - mid, merge);
		mergeData(data, lo, mid, hi, merge);
	}

	private static void mergeData(int[] data, int lo, int mid, int hi, int[] merge) {
		
		//l -> lo:mid-1, r -> mid:hi-1
		int l = lo;
		int r = mid;
		
		for (int i = lo; i < hi; i++) {
	        if (l < mid && (r >= hi || data[l] <= data[r])) {
	            merge[i] = data[l++];
	        } else {
	            merge[i] = data[r++];
	        }			
		}
		
		for (int i = lo; i < hi; i++) {
			data[i] = merge[i];
		}

	}
	
}
