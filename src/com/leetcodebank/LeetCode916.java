package com.leetcodebank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode916 {
    public static void main(String[] args) {
        System.out.println(916);

    }

    public static List<String> wordSubsets(String[] A, String[] B) {
        int[] bmax = count("");
        for (String b: B) {
            int[] bCount = count(b);
            for (int i = 0; i < 26; i++) {
                bmax[i] = Math.max(bmax[i], bCount[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        search: for (String a: A) {
            int[] aCount = count(a);
            for (int i = 0; i < 26; i++) {
                if(aCount[i] < bmax[i])
                    continue search;
            ans.add(a);
            }
        }
        return ans;
    }

    public static int[] count(String S) {
        int[] ans = new int[26];
        for(char c: S.toCharArray()){
            ans[c-'a']++;
        }
        return ans;
    }

}
