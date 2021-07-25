package com.leetcodebank;

public class Sword53 {
    public static void main(String[] args) {

    }

    public int search(int[] nums, int target) {
        int result = 0;
        for (int number : nums) {
            if (number == target) {
                result++;
            }
        }
        return result;
    }

}
