package com.leetcodebank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LeetCode565 {
    public static void main(String[] args) {
        System.out.println(791);
    }


    public static int arrayNesting(int[] nums) {
        if(Objects.isNull(nums) || nums.length<=0){
            return 0;
        }
        int ret = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = i;
            list.clear();
            while(!list.contains(nums[index])){
                list.add(nums[index]);
                index = nums[index];
            }
            ret = Math.max(ret, list.size());
        }
        return ret;
    }

    public static int arrayNesting2(int[] nums) {
        if(Objects.isNull(nums) || nums.length<=0){
            return 0;
        }
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            int flag = nums[i];
            int count = 0;
            int index = nums[i];
            do{
                index = nums[index];
                count++;
            }
            while(index!=flag);
            ret = Math.max(ret, count);
        }
        return ret;
    }

}
