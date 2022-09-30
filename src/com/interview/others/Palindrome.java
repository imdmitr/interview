package com.interview.others;

import java.util.Arrays;

public class Palindrome {

	public static void main(String[] args) {
		System.out.println("madam " + isPalindrome("madam"));
		System.out.println("massam " + isPalindrome("massam"));
		System.out.println("racecar " + isPalindrome("racecar"));
		System.out.println("racezcar " + isPalindrome("raczecar"));
		System.out.println("aacecar " + isPalindrome("aacecar"));
		
		
		//Make palindrom
		System.out.println("mamad " + makePalindrome("mamad"));
		System.out.println("asflkj " + makePalindrome("asflkj"));
		System.out.println("aabb " + makePalindrome("aabb"));
		System.out.println("ntiin " + makePalindrome("ntiin"));
		System.out.println("niitn " + makePalindrome("niitn"));
		System.out.println("znatiianz " + makePalindrome("znatiianz"));
		System.out.println("znataiinz " + makePalindrome("znataiinz"));
		
		
	}

	private static boolean isPalindrome(String word) {
		if (word == null || word.isEmpty()) {
			return false;
		}
		
		char[] chars = word.toCharArray();
		int forward = 0;
		int backward = chars.length -1;
		while (backward > forward) {
			if (chars[forward++] != chars[backward--]) {
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * Return shuffle counts or -1 if not possible 
	 * @param word
	 * @return
	 */
	private static int makePalindrome(String word) {
		if (word == null || word.isEmpty()) {
			return -1;
		}
		
		char[] chars = word.toCharArray();
		int forward = 0;
		int backward = chars.length -1;
		int count = 0;
		while (backward > forward) {
			if (chars[forward] != chars[backward]) {
				//find forward + 1 and replace with backward 
				int shuffles = swapIfPossible(chars, chars[forward],  forward + 1, backward);
				if (shuffles == -1) {
					return -1;
				}
				count += shuffles;
			}
			forward++;
			backward--;
		}
		return count;
	}

	private static int swapIfPossible(char[] chars, char search, int start, int end) {
		if (start >= end) {
			return -1;
		}
		
		int count = 0;
		for (int i = start; i < end; i++) {
			if (chars[i] == search) {
				int t = i;
				while (t < end && t + 1 <= end) {
					swap(chars, t, t + 1);
					t++;
					count++;
				}
				return count;
			}
		}
		
		//Not found but it's half
		if (start == chars.length / 2 && chars.length % 2 == 1) {
			swap(chars, start - 1, start);
			return 1;
		}
		
		return -1;
	}

	private static void swap(char[] chars, int i, int k) {
		char tmp = chars[k];
		chars[k] = chars[i];
		chars[i] = tmp;
		System.out.println("Swaping :" + new String(chars));
	}
	
	/**
	 * Find longest palindrome in string
	 * @param chars
	 */
	private static void findPalindome(char[] chars) {
	    int n = chars.length; 
	    
	    int maxLength = 1; 
		int start = 0;		
		
	    for (int i = 0; i < chars.length; i++) { 
	        for (int j = i; j < chars.length; j++) { 
	            int flag = 1; 
	  
	            // Check palindrome 
	            for (int k = 0; k < (j - i + 1) / 2; k++) 
	                if (chars[i + k] != chars[j - k]) 
	                    flag = 0; 
	  
	            // Palindrome 
	            if (flag == 1 && (j - i + 1) > maxLength) { 
	                start = i; 
	                maxLength = j - i + 1; 
	            } 
	        } 
	    }
	    
	    String pp = new String(Arrays.copyOfRange(chars, start, start + maxLength));
	    	System.out.println(pp);
	}	
	
}
