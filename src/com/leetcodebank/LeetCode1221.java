package com.leetcodebank;

public class LeetCode1221 {
    public static void main(String[] args) {

    }

    public int balancedStringSplit(String s) {
        int ret = 0;
        int L = 0;
        int R = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                L++;
            } else if (s.charAt(i) == 'R') {
                R++;
            }
            if (L == R && L != 0) {
                ret++;
                L = 0;
                R = 0;
            }
        }
        return ret;
    }

    public int balancedStringSplit2(String s) {
        int ret = 0;
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                n++;
            } else if (s.charAt(i) == 'R') {
                n--;
            }
            if (n == 0) {
                ret++;
                n = 0;
            }
        }
        return ret;
    }

}
