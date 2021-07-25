package com.leetcodebank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MonoStack {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        int[] ret = nextGreaterElements(nums);
        int a = 0;

    }

    public static int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * nums.length - 1; i >= 0; --i) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }
}
