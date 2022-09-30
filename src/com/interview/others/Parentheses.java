package com.interview.others;

import com.interview.list.Stack;

public class Parentheses {
	
	char[][] brackets = new char[][] {
			{'[', ']'}, 
			{'(', ')'},
			{'{', '}'},
			};
	
	public static void main(String[] args) {
		System.out.println(isValid("z(b[a]c)"));
		System.out.println("z(b[ac) " + isValid("z(b[ac)"));
		System.out.println(isValid("()[]{}"));
		System.out.println("(] " + isValid("(]"));
		System.out.println("{[]} " + isValid("{[]}"));

	}

	private static boolean isValid(String value) {
		
		boolean hadBrakets = false;
		Stack<Integer> parentheses = new Stack<>();
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if (isOpenBracket(c)) {
				parentheses.push(Integer.valueOf(c));
				hadBrakets = true;
			}
			
			if (isCloseBracket(c)) {
				Integer last = parentheses.pop();
				
				if (last == null || getClosing((char) last.intValue()) != c) {
					System.out.println("Expecting " + getClosing((char) last.intValue()));
					return false;
				}
			}
			
		}
		
		return hadBrakets;
	}

	private static char getClosing(char open) {
		char rez = ' ';
		switch ((char) open) {
		case '(': 
			rez = ')';
			break;
		case '{': 
			rez = '}';
			break;
		case '[': 
			rez = ']';
			break;
		default:
			break;
		}
		return rez;
	}

	private static boolean isOpenBracket(char c) {
		return c == '[' || c == '{' || c == '(';
	}
	
	private static boolean isCloseBracket(char c) {
		return c == ']' || c == '}' || c == ')';
	}
}
