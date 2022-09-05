package com.my.codility.answers;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Solution {

	static class BTree {
		public static int MAX_COUNT = 1000;

		int count;
		BNode root;
		BNode minValue;

		public boolean add(int value) {
			return add(new BNode(value));
		}

		private boolean add(BNode node) {

			if (count == MAX_COUNT) {
				if(node.value <= minValue.value) {
					return false;
				}

				// we have max count of numbers
				// if this value is larger than current min value,
				// remove the minNode and add this
				if(minValue == root) {
					root = minValue.right;
					root.parent = null;
				} else {
					minValue.parent.left = null;
					if(minValue.right != null) {
						minValue.parent.add(minValue.right);
					}
				}

				// go to the new min value
				minValue = minValue.parent;
				while (minValue.left != null) {
					minValue = minValue.left;
				}

				count--;
			}

			count++;
			if (root == null) {
				root = node;
				minValue = root;
			} else {
				root.add(node);
			}

			if (minValue.value > node.value) {
				minValue = node;
			}

			return true;
		}

		public List<Integer> toList() {
			List<Integer> ret = new ArrayList<Integer>(MAX_COUNT);
			BNode node = root;
			fillList(ret, node);
			return ret;
		}

		private void fillList(List<Integer> list, BNode node) {
			if (node.right != null) {
				fillList(list, node.right);
			}
			list.add(node.value);
			if (node.left != null) {
				fillList(list, node.left);
			}
		}
	}

	static class BNode {
		int value;

		private BNode parent;
		private BNode left;
		private BNode right;

		public BNode(int value) {
			this.value = value;
		}

		public void add(BNode node) {
			if (node.value > this.value) {
				if (this.right == null) {
					this.right = node;
					node.parent = this;
				} else {
					this.right.add(node);
				}
			} else {
				if (this.left == null) {
					this.left = node;
					node.parent = this;
				} else {
					this.left.add(node);
				}
			}
		}
	}

	public BTree tree = new BTree();

	public void max1000(int no) {
		boolean added = tree.add(no);
		if (!added) {
			System.out.println(
					String.format("Min value in tree is: %s: | No: %s didn't added to max tree", tree.minValue.value, no));
		}
	}

}
