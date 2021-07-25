package com.leetcodebank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeetCode56 {
    public static void main(String[] args) {
        LeetCode56 solution = new LeetCode56();
        int[][] test = {{1, 2}, {3, 4}};
        //System.out.println(solution.merge());
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> list = new ArrayList<>(intervals.length);
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] >= intervals[i + 1][0]) {
                intervals[i + 1][0] = intervals[i][0];
                intervals[i + 1][1] = Math.max(intervals[i + 1][1], intervals[i][1]);
            } else list.add(intervals[i]);
        }
        list.add(intervals[intervals.length - 1]);
        return list.toArray(new int[list.size()][2]);
    }
}
