package com.leetcodebank;

import java.util.*;

public class LeetCode791 {
    public static void main(String[] args) {
        String S = "cba";
        System.out.println(customSortString("kqep","pekeq"));
    }

    public static String customSortString(String S, String T) {
        if(Objects.isNull(S) || Objects.isNull(T)){
            return "";
        }

        Map<Character, Integer> charRank = new HashMap<>();
        List<Character> charList = new ArrayList<>();
        StringBuilder sbRank = new StringBuilder();
        StringBuilder sbLeave = new StringBuilder();
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            charRank.put(S.charAt(i),i);
            charList.add(S.charAt(i));
        }
        for (int i = 0; i < T.length(); i++) {
            if(charRank.containsKey(T.charAt(i))){
                sbRank.append(T.charAt(i));
                continue;
            }
            sbLeave.append(T.charAt(i));
        }
        T = sbRank.toString();
        for (int i = 0; i < charList.size(); i++) {
            for (int j = 0; j < T.length(); j++) {
                if(T.charAt(j) == charList.get(i)){
                    ret.append(charList.get(i));
                }
            }
        }
        return ret.toString() + sbLeave.toString();
    }
}
