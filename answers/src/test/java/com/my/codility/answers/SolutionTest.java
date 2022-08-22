package com.my.codility.answers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SolutionTest {

	@Test
	public void testSolution() {
		Solution s = new Solution();

		int[] A1 = new int[] {1, 3, 6, 4, 1, 2};
		int solution1 = s.solution(A1);
		assertEquals(solution1, 5);

		int[] A2 = new int[] {1, 2, 3};
		int solution2 = s.solution(A2);
		assertEquals(solution2, 4);

		int[] A3 = new int[] {-1, -3};
		int solution3 = s.solution(A3);
		assertEquals(solution3, 1);
	}
}
