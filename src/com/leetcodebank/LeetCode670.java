package com.leetcodebank;

import java.util.Arrays;

public class LeetCode670 {
    public static void main(String[] args) {
        int result = maximumSwap(98368);
        System.out.println();
    }

    public static int maximumSwap(int num) {
        if (num >= 100000000) {
            return 0;
        }
        int ret = 0;
        int[] numRecord = new int[8];
        int r = 0;
        int flag = -1;
        for (int i = 7; i >= 0; i--) {
            r = num / (int) Math.pow(10, i);
            if (r > 0 && flag == -1) {
                flag = i;
            }
            if (r <= 0 && flag == -1) {
                numRecord[i] = -1;
            }

            if (r > 0 && flag != -1) {
                numRecord[i] = r;
                num = num - r * (int) Math.pow(10, i);
            }
        }

        int[] numList = new int[flag + 1];
        for (int i = 0; i < numList.length; i++) {
            numList[i] = numRecord[i];
        }

        int[] sortList= numList.clone();
        Arrays.sort(sortList);

        for (int i = numList.length-1; i >=0 ; i--) {
            if(numList[i] != sortList[i]){
                //find sortList[i] from numlist 0 -> 8
                swap(i,sortList[i],numList);
                break;
            }
        }

        for (int i = 0; i < numList.length; i++) {
            ret = ret + numList[i] * (int)Math.pow(10,i);
        }

        return ret;
    }

    // n1 is 最高位 n2 is
    public static void swap(int base, int n2, int[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == n2) {
                int temp = list[base];
                list[base] = list[i];
                list[i] = temp;
                break;
            }
        }
    }


}
