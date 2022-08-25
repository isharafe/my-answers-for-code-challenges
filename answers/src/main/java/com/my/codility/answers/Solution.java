package com.my.codility.answers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This is a HackerRank test
 *
 * my (late) solution for the Mercari test.
 *
 */
public class Solution {

	public int solution(List<Integer> arr, int k) {
		int minimumLength = -1;
		for(int i=0; i<arr.size(); i++) {
			int subArrayMinEnd = i + k;
			for(int j=subArrayMinEnd; j<=arr.size(); j++) {
				Set<Integer> uniques = findUniques(arr, i, j);
				if(uniques.size()==k) {
					// there won't be a minimum than k
					return k;
				}
				if(uniques.size() > k && uniques.size() < minimumLength) {
					minimumLength = uniques.size();
				}
			}
		}

		return minimumLength;
	}

	public static Set<Integer> findUniques(List<Integer> arr, int from, int to) {
		return arr.subList(from, to).stream().collect(Collectors.toSet());
	}
}
