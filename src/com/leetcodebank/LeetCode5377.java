package com.leetcodebank;

import java.util.Arrays;

public class LeetCode5377 {
    public static void main(String[] args) {
        System.out.println(5377);
        String test1 = "1111";
        System.out.println(plus(test1));
        String test2 = "11010";
        System.out.println(division(test2));
        String test3 = "1101";
        System.out.println(numSteps(test3));
    }

    public static int numSteps(String s) {
        int count = 0;
        while (!s.equals("1")) {
            if (s.charAt(s.length() - 1) == '0') {
                s = division(s);
            } else {
                s = plus(s);
            }
            count++;
        }
        return count;
    }

    public static String plus(String s) {
        char[] charArray = s.toCharArray();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (charArray[i] == '0') {
                charArray[i] = '1';
                return String.valueOf(charArray);
            }
            charArray[i] = '0';
        }
        char[] ret = new char[s.length() + 1];
        Arrays.fill(ret, '0');
        ret[0] = '1';
        return String.valueOf(ret);
    }

    public static String division(String s) {
        return s.substring(0, s.length() - 1);
    }
}
