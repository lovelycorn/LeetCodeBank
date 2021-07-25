package com.leetcodebank;

public class LeetCode1582 {
    public static void main(String[] args) {
        int[][] sqr= {{1,2},{3,4}};
    }
    public int numSpecial(int[][] mat) {
        int ret = 0;
        int[] row = new int[mat.length];
        int[] col = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            int index = -1;
            for (int j = 0; j < mat[0].length; j++) {
                if(index == -1 && mat[i][j] == 1) {
                    index = j;
                }
                if(index != -1 && mat[i][j] == 1) {
                    index = -1;
                    break;
                }
            }
            row[i] = index;
        }
        for (int i = 0; i < mat[0].length; i++) {
            int index = -1;
            for (int j = 0; j < mat.length; j++) {
                if(index == -1 && mat[i][j] == 1) {
                    index = j;
                }
                if(index != -1 && mat[i][j] == 1) {
                    index = -1;
                    break;
                }
            }
            col[i] = index;
        }

        return ret;
    }
}
