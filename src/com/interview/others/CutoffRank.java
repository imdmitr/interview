package com.interview.others;

import java.util.Arrays;

public class CutoffRank {
	public static int cutOffRank(int cutOffRank, int num, int[] scores) {
	    int rank = 1;
	    int position = 1;
	    Arrays.sort(scores);
	    for (int i = num - 1; i >= 0; i--) {
	        if (i == num - 1 || scores[i] != scores[i + 1]) {
	            rank = position;
	            if (rank > cutOffRank) return position - 1;
	        }
	        position++;
	    }
	    return num;
	}
	
	public static void main(String[] args) {
		System.out.println(cutOffRank(3, 4, new int[] {100, 50, 50, 25}));
		System.out.println(cutOffRank(4, 5, new int[] {2, 2, 3, 4, 5}));
	}
	
}
