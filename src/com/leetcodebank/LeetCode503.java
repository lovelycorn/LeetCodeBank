package com.leetcodebank;

public class LeetCode503 {
    public static void main(String[] args) {
        int[] n1 = {1, 2, 1};
        int[] n2 = {};
        int[] result = nextGreaterElements(n1);
        for (int i : result
        ) {
            System.out.println(i);
        }
    }
    public static int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] ret = new int[len];
        int val;
        int j;
        for (int i = 0; i < len; i++) {
            val = nums[i];
            j = (i + 1) % len;
            ret[i] = -1;
            while (j != i) {
                if (nums[j] > nums[i]) {
                    ret[i] = nums[j];
                    //j = i-1;
                    break;
                }
                j = (j + 1) % len;
            }
        }
        return ret;
    }
}
