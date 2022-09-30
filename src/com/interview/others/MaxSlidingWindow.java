package com.interview.others;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * 
 * @author imdmi
 *
 */
public class MaxSlidingWindow {

	public static int[] maxSlidingWindow(int[] nums, int k) {
		Deque<int[]> d = new LinkedList<>();
		int ans[] = new int[nums.length - k + 1];
		for (int i = 0; i < nums.length; i++) {
			while (d.size() > 0 && (d.peekLast()[0] <= nums[i] || d.peekLast()[1] <= i - k))
				d.pollLast();
			d.addLast(new int[] { nums[i], i });
			while (d.peekFirst()[1] <= i - k)
				d.pollFirst();
			if (i >= k - 1) {
				ans[i - k + 1] = d.peekFirst()[0];
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		int[] maxSlidingWindow = maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3);
		System.out.println(Arrays.toString(maxSlidingWindow));
	}
	
    public int[] maxSlidingWindowTree(int[] nums, int k) {
        
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(); // <number, occurrence>
        
        for (int i = 0; i < k-1; i++)
            treeMap.put(nums[i], treeMap.getOrDefault(nums[i], 0) + 1);
        
        int[] result = new int[nums.length - k + 1];
        
        for (int i = k-1; i < nums.length; i++)
        {
            treeMap.put(nums[i], treeMap.getOrDefault(nums[i], 0) + 1);
            result[i-k+1] = treeMap.lastKey();
            
            // Remove the first number of the sliding window
            int cnt = treeMap.get(nums[i-k+1]);
            
            if (cnt == 1)
                treeMap.remove(nums[i-k+1]);
            else
                treeMap.put(nums[i-k+1], cnt-1);
        }
        
        return result;
    }	
}
