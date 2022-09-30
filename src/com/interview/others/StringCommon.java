package com.interview.others;

import java.util.Arrays;

public class StringCommon {

	public static void main(String[] args) {
		StringCommon sc = new StringCommon();
		char[] charArray = "word".toCharArray();
		sc.reverseString(charArray);
		System.out.println(Arrays.toString(charArray));
		charArray = "words".toCharArray();
		sc.reverseString(charArray);
		System.out.println(Arrays.toString(charArray));
	}

	public void reverseString(char[] s) {
		int i = 0;
		int j = s.length - 1;

		while (i < j) {
			char c = s[i];
			s[i++] = s[j];
			s[j--] = c;
		}

	}

	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		int[] table = new int[26];
		for (int i = 0; i < s.length(); i++) {
			table[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < t.length(); i++) {
			table[t.charAt(i) - 'a']--;
			if (table[t.charAt(i) - 'a'] < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * https://leetcode.com/problems/longest-common-prefix/solution/
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++)
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty())
					return "";
			}
		return prefix;
	}

	public String longestCommonPrefix2(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		for (int i = 0; i < strs[0].length(); i++) {
			char c = strs[0].charAt(i);
			for (int j = 1; j < strs.length; j++) {
				if (i == strs[j].length() || strs[j].charAt(i) != c)
					return strs[0].substring(0, i);
			}
		}
		return strs[0];
	}

	/**
	 * Find all permutations of strings
	 * https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
	 * @param str
	 * @param l
	 * @param r
	 */
	private void permute(String str, int l, int r) {
		if (l == r)
			System.out.println(str);
		else {
			for (int i = l; i <= r; i++) {
				str = swap(str, l, i);
				permute(str, l + 1, r);
				str = swap(str, l, i);
			}
		}
	}

	public String swap(String a, int i, int j) {
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}

}
