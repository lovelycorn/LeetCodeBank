package com.leetcodebank;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode1222 {
    public static void main(String[] args) {

    }
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> res = new LinkedList<>();
        //标志数组
        boolean[][] flag = new boolean[8][8];
        for(int[] queen : queens)
            flag[queen[0]][queen[1]] = true;
        //8个方向                         右     左    上    下    右上   右下   左上    左下
        int[][] direction = new int[][]{{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
        //从第一个方向开始到第八个方向
        for(int i = 0;i < direction.length;i++){
            //起始位置为king的坐标,找到第一个皇后停止这个方向的查找，或者直到出界
            for(int x = king[0],y = king[1];x >= 0 && x < 8 && y >= 0 && y < 8;x += direction[i][0],y += direction[i][1]){
                if(flag[x][y]){
                    res.add(Arrays.asList(x,y));
                    break;
                }
            }
        }

        return res;

    }
}
