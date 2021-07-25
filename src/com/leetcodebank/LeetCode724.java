package com.leetcodebank;

import java.util.Objects;

public class LeetCode724 {
    public static void main(String[] args) {
        System.out.println(724);
        int[] test1 = {1,7,3,6,5,6};
        int[] test2 = {1,2,3};
        int[] test3 = {-1,-1,-1,-1,-1,-1};
        System.out.println(pivotIndex(test1));
        System.out.println(pivotIndex(test2));
        System.out.println(pivotIndex(test3));
    }

    public static int pivotIndex(int[] nums) {
        if (Objects.isNull(nums) || nums.length <= 0) {
            return -1;
        }

        if (nums.length ==1){
            return 0;
        }
        int ret = -1;
        int[] leftsum = new int[nums.length];
        int[] rightsum = new int[nums.length];
        leftsum[0] = nums[0];
        rightsum[nums.length-1] = nums[nums.length-1];
        for (int i = 1; i < nums.length; i++) {
            leftsum[i] = leftsum[i - 1] + nums[i];
            rightsum[nums.length-i-1] = rightsum[nums.length-i] + nums[nums.length-1-i];
        }
        System.out.println();
        for (int i = 0; i < nums.length; i++) {
            if(leftsum[i] == rightsum[i]){
                return i;
            }
        }
        return ret;
    }
}
