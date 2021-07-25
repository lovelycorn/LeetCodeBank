package com.leetcodebank;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCode781 {
    public static void main(String[] args) {
        int[] case1 = {1, 3, 3, 3, 3, 3, 3, 7, 3, 0, 8};
        int[] case2 = {4, 0, 0, 2, 4};
        System.out.println(2/5);
        //System.out.println(numRabbits(case1));
        System.out.println(numRabbits(case2));
    }

    public static int numRabbits(int[] answers) {
        int ret = 0;
        int N = 0;
        int count = 0;
        int tmp = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < answers.length; i++) {
            if (map.containsKey(answers[i])) {
                map.replace(answers[i], map.get(answers[i]) + 1);
            } else {
                map.put(answers[i], 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            N = entry.getKey();
            count = entry.getValue();
            tmp = (count/(N+1))*(N+1);
            ret = ret + tmp;
            if((count%(N+1)) != 0) {
                ret = ret + (N+1);
            }
        }
        return ret;
    }

}
