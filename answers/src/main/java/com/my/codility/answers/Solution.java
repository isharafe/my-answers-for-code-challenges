package com.my.codility.answers;

import java.util.ArrayList;
import java.util.List;

/**
 * Not sure this solution is correct. I failed at the initial test and later I
 * finish this solution. Couldn't test this in codility
 */
public class Solution {

	public int[] solution(int[] A) {
		int base = -2;
		int val = binaryToNumber(A, base);
		int ceil = (int) Math.ceil((val) / 2.0);
		return numberToBinary(ceil, base);
	}

	private int binaryToNumber(int[] arr, int base) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i] * Math.pow(base, i);
		}
		return sum;
	}

	private int[] numberToBinary(int num, int base) {
		List<Integer> res = new ArrayList<>();
		int aBase = Math.abs(base);
		int aNum = Math.abs(num);

		while (aNum >= aBase || num < 0) {
			int mod = num % base;
			int aMod = Math.abs(mod);
			num = (num - aMod) / base;
			aNum = Math.abs(num);
			res.add(aMod);
		}
		res.add(aNum);

		int[] arr = new int[res.size()];
		for (int i = 0; i < res.size(); i++) {
			arr[i] = res.get(i);
		}

		return arr;
	}
}
