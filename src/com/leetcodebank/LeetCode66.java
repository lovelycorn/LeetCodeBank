package com.leetcodebank;

public class LeetCode66 {
    public static void main(String[] args) {
        System.out.println(66);
        int[] case1 = {9,1,9,9};
        plusOne(case1);
    }

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length-1; i >= 0 ; i--) {
            digits[i] = digits[i] + 1;
            if(digits[i] == 10) {
                digits[i] = 0;
            } else {
                break;
            }
        }
        if(digits[0] == 0){
            int[] retspecial = new int[digits.length+1];
            retspecial[0] = 1;
            return retspecial;
        }
        return digits;
    }
}
