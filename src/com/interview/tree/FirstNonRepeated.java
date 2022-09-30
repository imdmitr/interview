package com.interview.tree;

public class FirstNonRepeated {

	public static void main(String[] args) {
		String input = "aaabdfddddddvvvvvvcccs";
		
		char findFirstNonrepeated = findFirstNonrepeated(input);
		System.out.println(findFirstNonrepeated);
		
	}

	private static char findFirstNonrepeated(String input) {
		char rez = ' ';
		if (input == null || input.length() == 0) {
			return rez;
		}
		
		int[] counts = new int[128];
		
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			counts[c] += 1;
		}
		
		for (char i = 0; i < counts.length; i++) {
			if (counts[i] == 1) {
				return i;
			}
		}
		
		return rez;
	}
}
