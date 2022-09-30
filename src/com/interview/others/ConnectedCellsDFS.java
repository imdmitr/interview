package com.interview.others;

public class ConnectedCellsDFS {
	
	public static void main(String[] args) {
		int[][] matrix = createMatrix();
		
		int c = connectedCell(matrix);
		
		System.out.println("found max connected " + c);
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

	static int connectedCell(int[][] matrix) {
		
		int max = 0;
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[r].length; c++) {
				int region = getBiggestRegion(matrix, r, c);
				max = Math.max(max, region);
			}
		}
		return max;
    }

	private static int getBiggestRegion(int[][] matrix, int row, int col) {
		if (row >= matrix.length || row < 0 || col >= matrix.length || col < 0) {
			return 0;
		}
		
		if (matrix[row][col] == 0) {
			return 0;
		}
		matrix[row][col] = 0;
		int sum = 1;
		for (int r = row - 1; r <= row + 1; r++) {
			for (int c = col - 1; c <= col + 1; c++) {
				if (r != row || c != col) {
					sum += getBiggestRegion(matrix, r, c);
				}
			}
			
		}
		
		return sum;
	}	
}
