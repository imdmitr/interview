package com.interview.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyTech {

	public static void main(String[] args) {
//		getPrimeNumberTask();
		
		int[] numbers = new int[] {54, 548, 60};
		
		List<String> items = new ArrayList<String>();
		for (int n : numbers) {
			items.add(String.valueOf(n));
		}
		
		List<Integer> items2 = new ArrayList<Integer>();
		for (int n : numbers) {
			items2.add(n);
		}
		
		int num = findMax2(items2);
		System.out.println(num);
	}
	

	private static void getPrimeNumberTask() {
		int floors = 68;
		int count = 0;
		List<Integer> primes = new ArrayList<>();
		List<Integer> floorsToCheck = new ArrayList<>();
		for (int i = 2; i < floors; i++) {
			if (isPrime(i)) {
				if (i < 10) {
					primes.add(i);
				}
				System.out.println(i);
			} else {
				if (!isContainPrime(i, primes)) {
					continue;
				}
				floorsToCheck.add(i);
				count++;
			}
		}
//		System.out.println("Count " + count);

//		findDoor(100);
		
//		getMinimumCost(3, new int[] {1, 3, 5, 7, 9});
	}
	
    
    static int findMax(List<String> numbers) {
    	Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				String s12 = o1 + o2;
				String s21 = o2 + o1;
				
				return -1 * s12.compareTo(s21);
			}
		};
		Collections.sort(numbers, comparator);
		
		StringBuilder sb = new StringBuilder();
		numbers.stream().forEach(s -> sb.append(s));
		return Integer.valueOf(sb.toString());
    }
    
    static int findMax2(List<Integer> numbers) {
    	Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				
				Integer s12 = getSpecialSum(o1, o2);
				Integer s21 = getSpecialSum(o2, o1);
				System.out.println( s12 + " : " + s21);
				return -1 * s12.compareTo(s21);
			}
		};
		Collections.sort(numbers, comparator);
		
		int sum = 0;
		for (Integer s : numbers) {
			sum = getSpecialSum(sum, s);
		}
		return sum;
    }
    
    static int getSpecialSum(int a, int b) {
    	int rez = a;
    	int tmp = b;
    	while (tmp > 0) {
    		tmp = tmp / 10;
    		rez = rez * 10;
    	}
    	
    	return rez + b;
    }

    
	// Complete the getMinimumCost function below.
	/**
	 * https://www.hackerrank.com/challenges/greedy-florist/problem
	 * 
	 * @param k
	 * @param c
	 * @return
	 */
	static int getMinimumCost(int k, int[] c) {

		Arrays.sort(c);
		int i = c.length - 1;
		int bought = 0;
		int total = 0;
		while (i >= 0) {
			// Calculate total
			// increment bought by 1 when everyone in the group has bought equal number of
			// flowers
			for (int j = 0; j < k && i >= 0; j++) {
				total += (1 + bought) * c[i];
				i--;
			}
			bought++;
		}
		return total;

	}

	public static int findDoor(int num) {
		int i = 0;
		int j = num;
		while (i < j) {
			i += 2;
			j -= 3;
		}
		System.out.println(i);
		System.out.println(j);
		return i;
	}

	private static boolean isContainPrime(int num, List<Integer> primes) {
		for (Integer p : primes) {
			if ((num - p) % 10 == 0) {
				System.out.println("Contain prime " + num);
				return true;
			}
		}
		return false;
	}

	private static boolean isPrime(int num) {
		boolean flag = false;
		for (int i = 2; i <= num / 2; ++i) {
			// condition for nonprime number
			if (num % i == 0) {
				flag = true;
				break;
			}
		}
		return !flag;
	}
}
