package com.my.codility.answers;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SolutionTest {

	@Test
	public void testSolution() {
		Solution s = new Solution();

		/*
		 * data format,
		 * [0] -> array, [1] -> distinct char number, [2] -> expected output
		 */
		Object[] testCases = new Object[] {
				Arrays.asList(1, 1, 2, 2, 3, 3, 4, 5), 3, 3,

		};

		for(int i=0; i<testCases.length; i=i+3) {
			int answer = s.solution((List<Integer>) testCases[i], (int) testCases[i+1]);
			assertEquals((int) testCases[i+2], answer);
		}

	}
}
