package com.leetcodebank;

import java.util.Stack;

public class LeetCode856 {
    public static void main(String[] args) {
        System.out.println(856);
    }

    // (()(()))
    public static int scoreOfParentheses(String S) {
        int ret = 0;
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if(S.charAt(i)=='(') {
                stk.push('(');
            }
            // when ')'
            else {
                
            }
        }


        return ret;
    }

}
