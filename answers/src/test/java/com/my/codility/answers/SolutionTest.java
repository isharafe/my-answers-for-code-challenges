package com.my.codility.answers;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SolutionTest {

	@Test
	/*
	 * assert answers here are "my answers",
	 * don't remember the correct answers from the test :P
	 */
	public void testSolution() {
		Solution s = new Solution();

		int[] A1 = new int[] {1,0,0,1,1,1}; // = -23
		int[] solution1 = s.solution(A1);
		assertArrayEquals(solution1, new int[] {1,0,1,0,1,1});

		int[] A2 = new int[] {0,0,1}; // = 4
		int[] solution2 = s.solution(A2);
		assertArrayEquals(solution2, new int[] {0,1,1});

		int[] A3 = new int[] {1,0,0,1,1}; // = 9
		int[] solution3 = s.solution(A3);
		assertArrayEquals(solution3, new int[] {1,0,1});
	}

}
