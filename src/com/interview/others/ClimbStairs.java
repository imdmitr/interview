package com.interview.others;

/**
 * https://leetcode.com/problems/climbing-stairs/solution/
 * 
 * @author imdmi
 *
 */
public class ClimbStairs {

	// Fib approach
	public int climbStairsFib(int n) {
		if (n == 1) {
			return 1;
		}
		int first = 1;
		int second = 2;
		for (int i = 3; i <= n; i++) {
			int third = first + second;
			first = second;
			second = third;
		}
		return second;
	}

	public int climbStairsMemo(int n) {
		int memo[] = new int[n + 1];
		return climb_Stairs(0, n, memo);
	}

	public int climb_Stairs(int i, int n, int memo[]) {
		if (i > n) {
			return 0;
		}
		if (i == n) {
			return 1;
		}
		if (memo[i] > 0) {
			return memo[i];
		}
		memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
		return memo[i];
	}

}
