package com.my.codility.answers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class Solution {

	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

		if(beginWord.equals(endWord)) {
			return 0;
		}

		// for easy processing
		if(!wordList.contains(beginWord)) {
			wordList.add(beginWord);
		}

		// create a graph/map with word connections
		Map<String, Set<String>> ladderMap = new HashMap<>();
		for(String node : wordList) {
			Set<String> connections = new HashSet<>();
			for(String word : wordList) {
				if(areLadderWords(node, word)) {
					connections.add(word);
				}
			}
			// remove self connection
			connections.remove(node);

			ladderMap.put(node, connections);
		}

		Queue<Set<String>> q = new LinkedList<>();
		q.add(ladderMap.get(beginWord));

		Set<String> visited = new HashSet<>();
		visited.add(beginWord);

		int count = 0;

		while(!q.isEmpty()) {
			Set<String> nextLevel = q.poll();
			count++;

			if(nextLevel.contains(endWord)) {
				return count;
			}

			for(String s : nextLevel) {
				if(!visited.contains(s)) {
					q.add(ladderMap.get(s));
					visited.add(s);
				}
			}
		}

		return 0;
	}

	public static boolean areLadderWords(String a, String b) {
		if (a == null && b != null) {
			return false;
		}
		if (a.length() != b.length()) {
			return false;
		}
		int mismatches = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				mismatches++;
			}
			if (mismatches > 1) {
				return false;
			}
		}

		return true;
	}
	
	
	public static void main(String[] args) {
		List<String> words = Arrays.asList("lest","leet","lose","code","lode","robe","lost");
		String begin = "leet";
		String end = "code";
		int len = ladderLength(begin, end, words);
		System.out.println(len);
	}
}
