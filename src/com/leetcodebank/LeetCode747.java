package com.leetcodebank;

import java.util.Arrays;

public class LeetCode747 {
    public static void main(String[] args) {
        System.out.println(747);
    }

    public static int dominantIndex(int[] nums) {
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i != maxIndex && nums[i] * 2 > nums[maxIndex]) {
                return -1;
            }
        }
        return maxIndex;
    }
}
