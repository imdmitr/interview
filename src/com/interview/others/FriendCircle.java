package com.interview.others;

/**
 * https://leetcode.com/problems/friend-circles/
 * 
 * @author imdmi
 *
 */
public class FriendCircle {
	
	public int findCircleNum(int[][] M) {
		int friendCount = 0;
		// a boolean array for visited
		int row = M.length;
		boolean[] visited = new boolean[row];
		for (int i = 0; i < row; i++) {
			if (!visited[i]) {
				// do the dfs and increase the count
				dfs(M, visited, i);
				friendCount += 1;
			}
		}
		return friendCount;
	}

	public void dfs(int[][] M, boolean[] visited, int current) {
		if (visited[current])
			return;
		visited[current] = true;
		for (int i = 0; i < M[current].length; i++) {
			if (!visited[i] && M[current][i] == 1)
				// continue the dfs
				dfs(M, visited, i);
		}
	}
	
	public static void main(String[] args) {
		FriendCircle fc = new FriendCircle();
		int[][] students = {{1,1,0},
		                    {1,1,0},
		                    {0,0,1}};
		
		
		System.out.println(fc.findCircleNum(students));
		
		int[][] students2  ={{1,1,0},
                {1,1,1},
                {0,1,1}};
		
		
		System.out.println(fc.findCircleNum(students2));

	}
}
