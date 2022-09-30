package com.interview.others;

public class SimpleEncodeDecode {

	
	public static void main(String[] args) {
//		"aaaabbbccca" -> "3a2b3c1a"
//		"aaa" -> "3a"
//		"abcd" -> "1a1b1c1d"
		String toEncode = "aaaabbbccca";
		String encoded = encode(toEncode);
		String decoded = decode(encoded);
		System.out.println(toEncode + " -> " + encoded);
		
		System.out.println("abcd" + " -> " + encode("abcd"));
		System.out.println("a" + " -> " + encode("a"));
		System.out.println("aa" + " -> " + encode("aa"));
		System.out.println("aaa" + " -> " + encode("aaa"));
		System.out.println("ab" + " -> " + encode("ab"));
		
		if (toEncode.equals(decoded)) {
			System.out.println("OK");
		} else {
			System.out.println("Not matching " + decoded);
		}
		
	}

	private static String decode(String encoded) {
		char[] charArray = encoded.toCharArray();
		int d = 0;
		boolean digit = true;
		StringBuilder sb = new StringBuilder();
		for (char c : charArray) {
			if (digit) {
				d = c - '0';
				digit = false;
			} else {
				for (int i = 0; i < d; i++) {
					sb.append(c);
				}
				digit = true;
			}
		}
		
		return sb.toString();
	}

	private static String encode(String toEncode) {
		char[] charArray = toEncode.toCharArray();
		int count = 0;
		char prev = ' ';
		char curr = ' ';
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < charArray.length; i++) {
			curr = charArray[i];
			if (curr != prev) {
				if (count > 0) {
					sb.append(count).append(prev);
				}
				prev = curr;
				count = 1;
			} else {
				count++;
			}
		}
		sb.append(count).append(curr);
		return sb.toString();
	}
}
