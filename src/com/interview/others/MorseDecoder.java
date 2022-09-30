package com.interview.others;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.Stack;

import com.interview.tree.Trie;
import com.interview.tree.TrieNode;

// https://github.com/chicagoruby/hack-night-smooshed-morse-code
public class MorseDecoder {
	private static String[] CODES = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
			".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
			"--.." };

	private static Map<String, String> CODES_LETTER = new HashMap<>();

	private static int MAX_CODE_LEN = 0;
	static {
		for (char i = 0; i < CODES.length; i++) {
			String letter = String.valueOf((char) (i + 'a'));
			CODES_LETTER.put(CODES[i], letter);
		}
		OptionalInt max = CODES_LETTER.keySet().stream().mapToInt(c -> c.length()).max();
		max.ifPresent((m) -> {
			MAX_CODE_LEN = m;
		});
	}

	private Trie<Integer> trie = new Trie<>();

	private Map<String, List<String>> memo = new HashMap<>();

	public static void main(String[] args) throws IOException {
//		String sequence = "-.---..--.....--.--..-..-.--....--.--..-.--.-.....--.-......-....-......-...-.-......-.--.--.--";
		String sequence = "-.....-...";

		MorseDecoder decoder = new MorseDecoder();

		List<String> words = decoder.findAllMatches(sequence);

		words.stream().forEach(System.out::println);

		
		toMorse("bus");
		toMorse("eide");
	}

	private List<String> findAllMatches(String sequence) throws IOException {

		loadWords();

		char[] charArray = sequence.toCharArray();

		List<String> words = new ArrayList<String>();
		findLettersMemo(charArray, words);

		return words;
	}

	private void loadWords() throws IOException {

		try (BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\interview\\src\\enable1.txt"))) {
			String line;
			int count = 1;
			while ((line = br.readLine()) != null) {
				TrieNode<Integer> node = trie.insert(line);
				node.data = count++;
			}
		}

	}

	/**
	 * Using stack and remember compute. 
	 * 
	 * @param chars
	 * @param rez
	 */
	private void findLettersMemo(char[] chars, List<String> rez) {

		Set<String> visited = new HashSet<>();
		Set<String> processed = new HashSet<>();
		
		Stack<SearchState> stack = new Stack<>();
		stack.add(createSearchState(0, "", "", 0));
		
		int previousOriginal = 0;
		int previousIndex = 0;
		
		while (!stack.isEmpty()) {
			SearchState current = stack.pop();

			// DFS going down by max 4 chars
			// process 4 variations
			String tmp = "";
			for (int i = 0; i < MAX_CODE_LEN; i++) {
				int index = current.start + i;
				if (index >= chars.length) {
					continue;
				}

				//Check visited
				if (visited.contains(getMemoKey(current.original, index))) {
					if (!processed.contains(getMemoKey(current.original, index) + "-" + current.sentence)) {
						List<String> vals = getMemo(current.original, index);
						
						//Add to memo
						vals.stream().forEach(v -> rez.add(current.sentence + " " + v));
						
						processed.add(getMemoKey(current.original, index) + "-" + current.sentence);
						
//						System.out.println(String.format("Found %1$s - : %2$s", current.sentence, vals.stream().collect(Collectors.joining(" "))));
					}

					break;
				}
				
				tmp += String.valueOf(chars[index]);
				String letter = CODES_LETTER.get(tmp);
				if (letter == null) {
					continue;
				}
				
				String nextSeq = current.sequence + letter;
				List<String> words = trie.findPrefix(nextSeq);
				if (words.isEmpty()) {
					continue;
				}
				if (words.contains(nextSeq)) {
					
					getMemo(current.original, index).add(nextSeq);
					
					if (current.original < previousOriginal) {
						//Mark as visited
						visited.add(getMemoKey(previousOriginal, previousIndex +1));
					}
					previousOriginal = current.original;
					previousIndex = index;
					
					
					if (index == chars.length - 1) {
						rez.add(current.sentence + " " + nextSeq);

//						System.out.println(String.format("END %1$d - %2$d : %3$s", current.original, index, current.sentence + " " + nextSeq));
						continue;
					}

					String nextSent = current.sentence.isEmpty() ? nextSeq : current.sentence + " " + nextSeq;
					stack.push(createSearchState(index + 1, "", nextSent, index + 1));

//					System.out.println(String.format("%1$d - %2$d : %3$s", index, current.original, nextSent));
				}
				stack.push(createSearchState(index + 1, nextSeq, current.sentence, current.original));
			}
			

		}

	}

	private List<String> getMemo(int start, int end) {
		String key = getMemoKey(start, end);
		List<String> list = memo.get(key);
		if (list == null) {
			list = new ArrayList<>();
			memo.put(key, list);
		}
		return list;
	}

	private String getMemoKey(int start, int end) {
		return "" + start + "-" + end;
	}

	private SearchState createSearchState(int start, String searchFor, String sentence, int original) {
		SearchState state = new SearchState();
		state.sequence = searchFor;
		state.start = start;
		state.sentence = sentence;
		state.original = original;
		return state;
	}

	/**
	 * No memo, using recursion 
	 * 
	 * @param chars
	 * @param sequence
	 * @param start
	 * @param sentence
	 * @param rez
	 */
	private void findLetters(char[] chars, String sequence, int start, String sentence, List<String> rez) {
		String tmp = "";
		for (int i = 0; i < MAX_CODE_LEN; i++) {
			int index = start + i;
			if (index >= chars.length) {
				return;
			}

			tmp += String.valueOf(chars[index]);
			String l = CODES_LETTER.get(tmp);
			boolean validLetter = l != null;

			if (validLetter) {
				String nextSeq = sequence + l;
				List<String> words = trie.findPrefix(nextSeq);
				if (words.contains(nextSeq) && index == chars.length - 1) {
					rez.add(sentence + " " + nextSeq);
					System.out.println(index + " - " + sentence + " " + nextSeq);
					return;
				}
				if (words.isEmpty()) {
					return;
				}
				if (words.contains(nextSeq)) {
					String nextSent = sentence.isEmpty() ? nextSeq : sentence + " " + nextSeq;
					System.out.println(index + " - " + nextSent);
					findLetters(chars, "", index + 1, sentence + " " + nextSent, rez);
				}
				findLetters(chars, nextSeq, index + 1, sentence, rez);
			}

		}
	}

	private class SearchState {
		int original;
		String sequence;
		String sentence;
		int start;
	}

	public int uniqueMorseRepresentations(String[] words) {
		Set<String> coded = new HashSet<>();
		for (int i = 0; i < words.length - 1; i++) {
			String word = words[i];
			char[] charArray = word.toCharArray();
			StringBuilder sb = new StringBuilder();
			for (char c : charArray) {
				sb.append(CODES[c - 'a']);
			}
			coded.add(sb.toString());
		}
		return coded.size();
	}

	public static String toMorse(String word) {
		char[] charArray = word.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : charArray) {
			sb.append(CODES[c - 'a']);
		}
		String rez = sb.toString();
		System.out.println(rez);
		return rez;
	}
}
