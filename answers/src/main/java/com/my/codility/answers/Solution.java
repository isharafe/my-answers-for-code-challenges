package com.my.codility.answers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a HackerRank test
 *
 * my (late) solution for the Mercari test.
 *
 * got this solution from an online article
 * https://www.geeksforgeeks.org/smallest-subarray-k-distinct-numbers/
 *
 * think of a window on top of the array. initially window width is 0 window
 * expand until it has "K" distinct numbers inside it. At this point we have our
 * first sub-array with K elements. enter each number to a map. map's key size
 * is distinct numbers and put the occurrence of each number in value shift
 * window 1 point to the right the number at the left margin disappear from
 * window and a new number enters from right side reduce left number from the
 * map add right number to the map
 */
public class Solution {

	public int solution(List<Integer> arr, int k) {
		Map<Integer, Integer> numCount = new HashMap<>();

		int minWindowWidth = arr.size();
//
//		int _start=0, _end=0;
//		while(true) {
//			if(_end < arr.size()) {
//				_end++;
//			} else {
//				_start++;
//			}
//
//			break;
//		}

		for (int start = 0, end = start; end < arr.size(); end++) {
			numCount.put(arr.get(end), numCount.getOrDefault(arr.get(end), 0) + 1);

			// if we have k distinct numbers
			while (numCount.size() == k) {
				// if current window length is less than previous minimum window length
				int currentWindowLength = end - start + 1; // +1 since end is 0 based
				if (currentWindowLength < minWindowWidth && currentWindowLength >= k) {
					minWindowWidth = currentWindowLength;
				}

				if(minWindowWidth == k) {
					// there won't be a minimum window less than k
					return minWindowWidth;
				}

				/*
				 * shift window to right by one.
				 * remove the left most number from the map.
				 */

				int leftMostNumberInWindow = arr.get(start);
				int leftMostNumberCount = numCount.get(leftMostNumberInWindow);
				if (leftMostNumberCount == 1) {
					numCount.remove(leftMostNumberInWindow);
				} else {
					numCount.put(leftMostNumberInWindow, leftMostNumberCount - 1);
				}

				start++;
			}
		}

		return (minWindowWidth == arr.size() && numCount.size() < k) ? -1 : minWindowWidth;
	}
}
