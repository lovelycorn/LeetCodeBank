package com.leetcodebank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LeetCode5376 {
    public static void main(String[] args) {
        System.out.println(5376);
        int[] testcase2 = {8, 8};
        List<Integer> retlist = minSubsequence(testcase2);
    }

    public static List<Integer> minSubsequence(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        if (Objects.isNull(nums) || nums.length < 1) {
            return ret;
        }
        if(nums.length ==1 ) {
            ret.add(nums[0]);
            return ret;
        }
        int len = nums.length;
        Arrays.sort(nums);
        int[] sumLeft = new int[len];
        sumLeft[0] = 0;
        int[] sumRight = new int[len];
        sumRight[len - 1] = nums[len - 1];
        // cal sum Left and right
        for (int i = 1; i < len; i++) {
            sumLeft[i] = sumLeft[i - 1] + nums[i-1];
            sumRight[len - i - 1] = sumRight[len - i] + nums[len - i - 1];
        }
        for (int i = len - 1; i >= 0; i--) {
            ret.add(nums[i]);
            if (sumRight[i] > sumLeft[i]) {
                return ret;
            }
            System.out.println();
        }
        return ret;
    }
}
