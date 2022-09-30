package com.interview.others;

import java.util.Arrays;

public class ConnectedCellsFindPath {

	public static void main(String[] args) {
		
		char[][] matrix = new char[][] {
				{'A', 'B', 'A'},
				{'A', 'A', 'A'},
				{'C', 'B', 'A'},
				};
				
		boolean pathExists = findPath(matrix, 0, 0, 0, 2, '0');
		System.out.println(pathExists);
		  
//		  int[] array = customer.values().stream().mapToInt(i -> i).toArray();
	


		int[] cookies = {2,2,3,7};
		long min1 = find_min_actions(cookies);
		long min2 = find_min2(cookies);
		System.out.println(min2 - min1);
		  
	}
	
	public static long find_min2(int[] cookies) {
		Arrays.sort(cookies);
		
		long sum = 0;
		for (int i = 1; i < cookies.length; i++) {
			sum += getCount(cookies[i] - cookies[0]);
		}
		return sum;
	}
	
	private static int getCount(int num) {
		
		int[] bars = {5, 2, 1};
		int b = 0;
		int count = 0;
		while (num > 0) {
			if (bars[b] <= num) {
				count = num / bars[b];
				num %= bars[b];
			} else {
				b++;
			}
		}
		return count;
	}

	public static long find_min_actions(int[] cookies) {

	    Arrays.sort(cookies);

	    long sum = Long.MAX_VALUE;

	    for(int base = 0; base < 3; base++) {
	        int current_sum = 0;
	        for(int i = 0; i < cookies.length; i++) {
	            int delta = cookies[i] - cookies[0] + base;
	            current_sum += (int)delta / 5 + delta % 5 / 2 + delta % 5 % 2 / 1;
	        }
	        sum = Math.min(current_sum,sum);
	    }

	    return sum;
	}
	

	private static boolean findPath(char[][] matrix, int startR, int startC, int endR, int endC, char c) {
		if (startR >= matrix.length || startR < 0) {
			return false;
		}
		
		if (startC >= matrix[startR].length || startC < 0) {
			return false;
		}
		
		if (matrix[startR][startC] == '0') {
			return false;
		}
		
		if (c == '0') {
			c = matrix[startR][startC];
		}
		
		if (matrix[startR][startC] != c) {
			return false;
		}
		
		if (startR == endR && startC ==endC) {
			return true;
		}
		
		matrix[startR][startC] = '0';
		
		boolean found = findPath(matrix, startR + 1, startC, endR, endC, c);
		found = found || findPath(matrix, startR - 1, startC, endR, endC, c);
		found = found || findPath(matrix, startR, startC + 1, endR, endC, c);
		found = found || findPath(matrix, startR, startC - 1, endR, endC, c);
		
		return found;
	}
}
