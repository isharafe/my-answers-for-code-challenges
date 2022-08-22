package com.my.codility.answers;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class Solution {

	public int solution(int[] A) {
		Arrays.sort(A);

		int res = 1;
		for(int i=0; i<A.length; i++) {
			int cur = A[i];

			if(cur == res) {
				res++;
			}
		}

		return res;
	}
}
