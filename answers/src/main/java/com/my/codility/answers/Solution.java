package com.my.codility.answers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* a text with shuffled letters for numbers is given.
* 1. find the related numbers from those letters
* 2. find the minimum value can be formed by those numbers. (leading zero is not allowed)
*
* ex: ewtooetzrowon --> two, two, one, zero --> 2, 2, 1, 0 --> 1022
*/
public class App {

	private static Map<Integer, String> nummap = new HashMap<>();
	static {
		nummap.put(0, "zero"); //
		nummap.put(1, "one");
		nummap.put(2, "two"); //
		nummap.put(3, "three");
		nummap.put(4, "four"); //
		nummap.put(5, "five");
		nummap.put(6, "six"); //
		nummap.put(7, "seven");
		nummap.put(8, "eight"); //
		nummap.put(9, "nine");
	}

	public static void main(String[] args) {
		int t1 = solution("ewtooetzrowon"); // 1022
		System.out.println(t1);
		int t2 = solution("ttnrwoooeeefurh"); // 1234
		System.out.println(t2);
	}

	private static int solution(String str) {

		List<Character> letters = new ArrayList<>(str.length());
		for (char c : str.toCharArray()) {
			letters.add(c);
		}

		List<Integer> numbers = new ArrayList<>();

		// use unique characters to identify numbers

		checkNumbers(letters, 'z', numbers, 0);
		checkNumbers(letters, 'w', numbers, 2);
		checkNumbers(letters, 'u', numbers, 4);
		checkNumbers(letters, 'x', numbers, 6);
		checkNumbers(letters, 'g', numbers, 8);

		// check for rest of the numbers
		// 1,3,5,7,9 --> wow! these are odd numbers :O, every even number has a unique english letter in it

		List<Character> temp = new ArrayList<>();
		for (int num : new int[] { 1, 3, 5, 7, 9 }) {
			char[] chars = nummap.get(num).toCharArray();
			boolean hasNumber = true;

			while (hasNumber) {
				for (Character c : chars) {
					hasNumber = letters.remove(c);
					if (!hasNumber) {
						// undo operation
						letters.addAll(temp);
						break;
					}
					temp.add(c);
				}
				temp.clear();

				if(hasNumber) {
					numbers.add(num);
				}
			}
		}

		Collections.sort(numbers, Comparator.reverseOrder());
		int j = numbers.size() - 1;
		while (numbers.get(numbers.size() - 1).equals(0)) {
			numbers.set(j, numbers.get(j-1));
			numbers.set(--j, 0);
		}

		int ret = 0;
		for (int i = numbers.size() - 1; i >= 0; i--) {
			ret = ret * 10 + numbers.get(i);
		}

		return ret;
	}

	private static void checkNumbers(List<Character> letters, Character c, List<Integer> numbers, Integer number) {
		while (letters.contains(c)) {
			numbers.add(number);
			for(Character ch : nummap.get(number).toCharArray()) {
				letters.remove(ch);
			}
		}
	}

}
