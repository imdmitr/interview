package com.interview.others;

import java.util.Arrays;

public class ArraysCommon {

	public static void main(String[] args) {
		checkMaxProfit();
	}

	/**
	 * 
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/solution/
	 * 
	 * @param prices
	 * @return
	 */
	public static int maxProfit(int[] prices) {
		int maxprofit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1])
				maxprofit += prices[i] - prices[i - 1];
		}
		return maxprofit;
	}

	private static void checkMaxProfit() {
		int[] arr1 = new int[] { 7, 1, 5, 3, 6, 4 };
		System.out.println("Max profit for [7,1,5,3,6,4] " + maxProfit(arr1));

		ArraysCommon ac = new ArraysCommon();
		ac.rotateReverse(arr1, 3);
		System.out.println("Max profit for [7,1,5,3,6,4] " + Arrays.toString(arr1));
		ac.rotateBruteForce(arr1, 3);

		int[] plusOne = ac.plusOne(new int[] { 9, 9, 9 });
		System.out.println(Arrays.toString(plusOne));

		int[] arr2 = new int[] { 7, 1, 5, 0, 3, 0, 6, 4 };
		ac.moveZeros(arr2);
		System.out.println("Move zeros " + Arrays.toString(arr2));
	}

	/**
	 * https://leetcode.com/problems/rotate-array/solution/
	 * 
	 * @param nums
	 * @param k
	 */
	public void rotateReverse(int[] nums, int k) {
		k %= nums.length;
		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);
	}

	public void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}

	public void rotateBruteForce(int[] nums, int k) {
		int[] a = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			a[(i + k) % nums.length] = nums[i];
		}
		for (int i = 0; i < nums.length; i++) {
			nums[i] = a[i];
		}
	}

	public void rotateCyclic(int[] nums, int k) {
		k = k % nums.length;
		int count = 0;
		for (int start = 0; count < nums.length; start++) {
			int current = start;
			int prev = nums[start];
			do {
				int next = (current + k) % nums.length;
				int temp = nums[next];
				nums[next] = prev;
				prev = temp;
				current = next;
				count++;
			} while (start != current);
		}
	}

	public int[] plusOne(int[] digits) {
		int val = 0;
		int d = digits.length - 1;
		for (int i = 0; i < digits.length; i++) {
			val += digits[i] * Math.pow(10.0, d--);

		}

//        int temp = val;
//        do{
//        	temp /= 10;
////digit        	temp % 10
//        } while  (temp > 0);

		return Integer.toString(val + 1).chars().map(c -> c - '0').toArray();
	}

	public void moveZeros(int[] nums) {
		int lastNonZeroFoundAt = 0;
		// If the current element is not 0, then we need to
		// append it just in front of last non 0 element we found.
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[lastNonZeroFoundAt++] = nums[i];
			}
		}
		// After we have finished processing new elements,
		// all the non-zero elements are already at beginning of array.
		// We just need to fill remaining array with 0's.
		for (int i = lastNonZeroFoundAt; i < nums.length; i++) {
			nums[i] = 0;
		}
	}

	/**
	 * https://leetcode.com/problems/rotate-image/solution/
	 * 
	 * @param matrix
	 */
	public void rotateMatrix(int[][] matrix) {
		int n = matrix.length;

		// transpose matrix
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int tmp = matrix[j][i];
				matrix[j][i] = matrix[i][j];
				matrix[i][j] = tmp;
			}
		}
		// reverse each row
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n / 2; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[i][n - j - 1];
				matrix[i][n - j - 1] = tmp;
			}
		}
	}

	/**
	 * https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
	 * 
	 * @param arr
	 * @return
	 */
	static int maxSubsetSum(int[] arr) {

		int incl = arr[0];
		int excl = 0;
		int excl_new;
		int i;

		for (i = 1; i < arr.length; i++) {
			/* current max including i */
			excl_new = (incl > excl) ? incl : excl;
			incl = excl + arr[i];
			excl = excl_new;
		}

		/* return max of incl and excl */
		return Math.max(excl, incl);
	}
	
    // Complete the maxMin function below.
    /**
     * https://www.hackerrank.com/challenges/angry-children/problem
     * 
     * @param k
     * @param arr
     * @return
     */
    static int maxMin(int k, int[] arr) {
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        
              
        int n = arr.length;
        for(int i = 0; i < n - k + 1; i++) {
            if (arr[i + k -1] - arr[i] < minDiff) {
                minDiff = arr[i + k -1] - arr[i];    
            }
            
        }
        return minDiff;
    }	

}
