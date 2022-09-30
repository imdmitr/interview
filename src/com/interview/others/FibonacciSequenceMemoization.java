package com.interview.others;

import java.util.Arrays;

public class FibonacciSequenceMemoization {

	public static void main(String[] args) {
		
		System.out.println("fibonaci: " + fibonaci(8));
	}

	private static int fibonaci(int n) {
		int[] memo = new int[n + 1];
		Arrays.fill(memo, -1);
		int fibonaciSequence = getFibonaciSequence(n, memo);
		System.out.println(Arrays.toString(memo));
		return fibonaciSequence;
	}

	private static int getFibonaciSequence(int n, int[] memo) {
		if (n == 0) {
			memo[0] = 1;
			return 1;
		}
		
		if (n == 1) {
			memo[1] = 2;
			return 2;
		}
		
		if (memo[n] == -1) {
			memo[n] = getFibonaciSequence(n - 1, memo) + getFibonaciSequence(n - 2, memo); 
		}
		return memo[n]; 
	}
}
