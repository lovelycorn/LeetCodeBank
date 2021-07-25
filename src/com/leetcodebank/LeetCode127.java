package com.leetcodebank;

import java.util.*;

public class LeetCode127 {
    static class Node {
        public String word;
        public int deep;
        public Node father;

        public Node() {
        }

        public Node(String str) {
            word = str;
        }

        public static void main(String[] args) {
            String beginWord = "hit";
            String endWord = "cog";
            List<String> wordlist = new ArrayList<>();
            wordlist.add("hot");
            wordlist.add("dot");
            wordlist.add("dog");
            wordlist.add("lot");
            wordlist.add("log");
            wordlist.add("cog");
            System.out.println(ladderLength(beginWord, endWord, wordlist));

        }

        public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Set<String> set = new HashSet<>(wordList);
            if (!set.contains(endWord)) {
                return 0;
            }
            Queue<Node> queue = new LinkedList<Node>();
            Node word = new Node(beginWord);
            word.deep = 1;
            word.father = null;
            queue.offer(word);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node n = queue.poll();
                    Iterator<String> it = set.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        if (compare(n.word, next)) {
                            it.remove();
                            if (next.equals(endWord)) {
                                return n.deep + 1;
                            } else {
                                Node nextnode = new Node(next);
                                nextnode.father = n;
                                nextnode.deep = n.deep + 1;
                                queue.offer(nextnode);
                            }
                        }
                    }
                }
            }
            return 0;
        }

        public static boolean compare(String str1, String str2) {
            int len = str1.length() - 1;
            int diff = 0;
            while (len >= 0) {
                if (str1.charAt(len) != str2.charAt(len)) {
                    diff++;
                    if (diff > 1) {
                        return false;
                    }
                }
                len--;
            }
            return true;
        }
    }


}