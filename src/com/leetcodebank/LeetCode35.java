package com.leetcodebank;

import java.util.Objects;

public class LeetCode35 {
    public static void main(String[] args) {
        LeetCode35 solution = new LeetCode35();
        int[] nums = {1, 3, 5, 6};
        int target = 0;
        System.out.println(solution.searchInsert(nums, target));
    }

    public int searchInsert(int[] nums, int target) {
        if (Objects.isNull(nums) || nums.length <= 0) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }
}
