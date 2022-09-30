package com.interview.others;

/**
 * https://leetcode.com/problems/number-of-islands/
 * 
 * Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 * @author imdmi
 *
 */
public class ConnectedCellsFindIsland {
	
	public static void main(String[] args) {
		int[][] matrix = createMatrix();
		
		int c = findIslands(matrix);
		
		System.out.println("found islands " + c);
	}

    private static int[][] createMatrix() {
    	int[][] matrix = new int[][] {
    		{0, 0, 1, 1},
    		{1, 0, 1, 1},
    		{0, 0, 1, 0},
    		{1, 0, 0, 0}
    	};
		return matrix;
	}

	static int findIslands(int[][] matrix) {
		
		int count = 0;
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[r].length; c++) {
				count += getBiggestRegion(matrix, r, c);
			}
		}
		return count;
    }

	private static int getBiggestRegion(int[][] matrix, int row, int col) {
		if (row >= matrix.length || row < 0 || col >= matrix.length || col < 0) {
			return 0;
		}
		
		if (matrix[row][col] == 0) {
			return 0;
		}
		matrix[row][col] = 0;
		int count = 1;
		for (int r = row - 1; r <= row + 1; r++) {
			for (int c = col - 1; c <= col + 1; c++) {
				if (r != row || c != col) {
					getBiggestRegion(matrix, r, c);
				}
			}
			
		}
		
		return count;
	}	
}
