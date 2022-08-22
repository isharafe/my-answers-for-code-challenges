package com.my.codility.answers;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class Solution {

	public int solution(int[] X, int[] Y, String colors) {

		// easy colors are in an array
		char[] cArr = colors.toCharArray();

		/*
		 * now data are in different places, X,Y,colors ! It easy to work when all
		 * realated data are in one place. Using DataPoint class for that
		 */
		DataPoint[] data = new DataPoint[X.length];
		for (int i = 0; i < X.length; i++) {
			data[i] = new DataPoint(X[i], Y[i], cArr[i]);
		}

		/*
		 * we want the maximum number of points in the circle.
		 * sort by radius helps to find this
		 */
		Arrays.sort(data, (a, b) -> {
			return Integer.compare(a.r2, b.r2);
		});

		// initial answer
		int ans = 0;

		/*
		 * count red and green point count at each data point
		 */
		int[] rCount = new int[data.length];
		int[] gCount = new int[data.length];
		int r = 0;
		int g = 0;
		for (int i = 0; i < data.length; i++) {
			DataPoint cur = data[i];

			if (cur.c == 'R') {
				r++;
			}
			if (cur.c == 'G') {
				g++;
			}

			rCount[i] = r;
			gCount[i] = g;
		}

		/*
		 * data array is sorted by radius.
		 * now check the R & G point from max radius to min radius
		 * if R & G counts match, "i" at that point (actually i+1 since i is 0 based)
		 * is the answer
		 */
		for (int i = data.length - 1; i >= 0; i--) {

			// skip first iteration, no i+1 for first iteration
			/* there can be several points with same radius.
			 * the correct R & G count is in the latest element of the
			 * array. so after checking the latest element in array,
			 *  skipping other points with same radius
			 */
			if (i != (data.length - 1) && data[i].r2 == data[i + 1].r2) {
				continue;
			}

			if (rCount[i] == gCount[i]) {
				ans = i + 1;
				break;
			}
		}

		return ans;
	}

	static class DataPoint {

		public int x;
		public int y;
		public char c;
		public int r2;

		public DataPoint(int x, int y, char c) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.r2 = (x * x) + (y * y);
		}

		@Override
		public String toString() {
			return String.format("x=%s y=%s c=%s r2=%s", x, y, c, r2);
		}
	}
}
