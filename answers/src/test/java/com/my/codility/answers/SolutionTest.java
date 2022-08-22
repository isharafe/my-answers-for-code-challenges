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

		int[] X1 = new int[] {4, 0, 2, -2};
		int[] Y1 = new int[] {4, 1, 2, -3};
		String colors1 = "RGRR";
		int solution1 = s.solution(X1, Y1, colors1);
		assertEquals(solution1, 2);

		int[] X2 = new int[] {1, 1, -1, -1};
		int[] Y2 = new int[] {1, -1, 1, -1};
		String colors2 = "RGRG";
		int solution2 = s.solution(X2, Y2, colors2);
		assertEquals(solution2, 4);

		int[] X3 = new int[] {1, 0, 0};
		int[] Y3 = new int[] {0, 1, -1};
		String colors3 = "GGR";
		int solution3 = s.solution(X3, Y3, colors3);
		assertEquals(solution3, 0);

		int[] X4 = new int[] {5, -5, 5};
		int[] Y4 = new int[] {1, -1, -3};
		String colors4 = "GRG";
		int solution4 = s.solution(X4, Y4, colors4);
		assertEquals(solution4, 2);

		int[] X5 = new int[] {3000, -3000, 4100, -4100, -3000};
		int[] Y5 = new int[] {5000, -5000, 4100, -4100, 5000};
		String colors5 = "RRGRG";
		int solution5 = s.solution(X5, Y5, colors5);
		assertEquals(solution5, 2);
	}
}
