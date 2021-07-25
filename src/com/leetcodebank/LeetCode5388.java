package com.leetcodebank;

import java.util.*;

public class LeetCode5388 {
    public static void main(String[] args) {
        System.out.println(reformat("a0b1c2"));
        System.out.println(reformat("covid2019"));
        System.out.println(reformat("leetcode"));
        System.out.println(reformat("ab123"));
    }

    public static String reformat(String s) {
        if (s.length() <= 0 || Objects.isNull(s)) {
            return "";
        }
        Queue<Character> numQue = new LinkedList<>();
        Queue<Character> charQue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                numQue.offer(c);
            }
            if (c >= 'a' && c <= 'z') {
                charQue.offer(c);
            }
        }
        if (Math.abs(numQue.size() - charQue.size()) <= 1) {
            StringBuilder builder = new StringBuilder();
            int size = Math.max(numQue.size(), charQue.size());


            if (numQue.size() >= charQue.size()) {
                for (int i = 0; i < size; i++) {
                    if (!numQue.isEmpty()) {
                        builder.append(numQue.poll());
                    }
                    if (!charQue.isEmpty()) {
                        builder.append(charQue.poll());
                    }
                }
            } else {
                for (int i = 0; i < size; i++) {
                    if (!charQue.isEmpty()) {
                        builder.append(charQue.poll());
                    }
                    if (!numQue.isEmpty()) {
                        builder.append(numQue.poll());
                    }
                }
            }
            return builder.toString();
        }
        return "";
    }
}
