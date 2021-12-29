package com.basic;

import java.util.LinkedList;
import java.util.Queue;

public class SampleBFS {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //BFS迭代四步法
    //1. 判空
    //2. 创建队列，添加根节点
    //3. 非空队列执行循环 {
    //      取队首的若干个节点
    //      添加需要进队的节点
    //    }
    //4. 对空，结束遍历
    public int bfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }
}
