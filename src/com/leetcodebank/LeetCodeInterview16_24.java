package com.leetcodebank;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class LeetCodeInterview16_24 {
    public static void main(String[] args) {

        boolean b;
        int[] test = {-2, -1, 0, 3, 5, 6, 7, 9, 13, 14};
        pairSums(test, 12);
    }

    public static List<List<Integer>> pairSums(int[] nums, int target) {
        Map<Integer, Integer> countMap = new HashMap<>();
        List<List<Integer>> ret = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!countMap.containsKey(nums[i])) {
                countMap.put(nums[i], 1);
            } else {
                countMap.replace(nums[i], countMap.get(nums[i]) + 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int src = nums[i];
            int pair = target - src;
            if (src == pair) {
                if (countMap.get(src) > 1) {
                    List<Integer> single = new ArrayList<>();
                    single.add(src);
                    single.add(pair);
                    countMap.replace(src, countMap.get(src) - 1);
                    countMap.replace(pair, countMap.get(pair) - 1);
                    ret.add(single);
                }
                continue;
            }

            if (countMap.get(src) > 0 && countMap.containsKey(pair) && countMap.get(pair) > 0) {
                List<Integer> single = new ArrayList<>();
                single.add(src);
                single.add(pair);
                countMap.replace(src, countMap.get(src) - 1);
                countMap.replace(pair, countMap.get(pair) - 1);
                ret.add(single);
            }
        }
        return ret;
    }


/*    public static List<List<Integer>> pairSums(int[] nums, int target) {
        boolean[] visited = new boolean[nums.length];
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (visited[i]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (visited[j]) {
                    continue;
                }
                if (nums[i] + nums[j] == target) {
                    List<Integer> single = new ArrayList<>();
                    visited[i] = true;
                    visited[j] = true;
                    single.add(nums[i]);
                    single.add(nums[j]);
                    ret.add(single);
                    break;
                }
            }
        }
        System.out.println();
        return ret;
    }*/
}
