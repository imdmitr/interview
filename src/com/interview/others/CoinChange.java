package com.interview.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CoinChange {

	
	public static void main(String[] args) {
//		int[] conins = {2, 5, 3, 6};
		int[] conins = {50, 25, 10, 5, 1};
		int makeChange = makeChange(conins, 10);
		System.out.println(makeChange);
		
		ArrayList<Long> a = new ArrayList<>();
		for (Long long1 : a) {
			
		}
		
	}

	private static int makeChange(int[] conins, int money) {
		return makeChange(conins, money, 0, new HashMap<>());
	}
	
	private static int makeChange(int[] coins, int money, int index, Map<String, Integer> memo) {
		if (money == 0) {
			return 1;
		}
		
		if (index >= coins.length) {
			return 0;
		}
		
		String key = money + "-" + index;
		System.out.println(key);
		
		if (memo.containsKey(key)) {
			return memo.get(key);
		}
		
		int amountWithMoney = 0;
		int ways = 0;
		while (amountWithMoney <= money) {
			int remaining = money - amountWithMoney;
			ways += makeChange(coins, remaining, index + 1, memo);
			amountWithMoney += coins[index]; 
		}
		memo.put(key, ways);
		
		System.out.println("found ways " + key + " " + ways);
		return ways;
	}
}
