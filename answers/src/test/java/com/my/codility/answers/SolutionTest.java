package com.my.codility.answers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SolutionTest {

	@Test
	public void testSolution() {
		Solution s = new Solution();

		// first add 1000 random nos;

		int min = Integer.MAX_VALUE;
		int max = 0;
		for(int i=0; i<Solution.BTree.MAX_COUNT; i++) {
			int no = (int) Math.round(Math.random() * 10000);
			if(no<min) {
				min = no;
			}
			if(no > max) {
				max = no;
			}
			s.max1000(no);
		}

		assertEquals(Solution.BTree.MAX_COUNT, s.tree.count);
		assertEquals(min, s.tree.minValue.value);

		// add another 1000 (or more or less)
		for(int i=0; i<Solution.BTree.MAX_COUNT; i++) {
			int no = (int) Math.round(Math.random() * 10000);
			if(no>max) {
				max = no;
			}
			s.max1000(no);
		}

		assertEquals(Solution.BTree.MAX_COUNT, s.tree.count);
		List<Integer> list = s.tree.toList();

		assertEquals(Solution.BTree.MAX_COUNT, list.size());
		assertEquals(Integer.valueOf(max), list.get(0));

		// list is sorted
		for(int i=1; i<list.size(); i++) {
			assertTrue(list.get(i-1) >= list.get(i));
		}
	}
}
