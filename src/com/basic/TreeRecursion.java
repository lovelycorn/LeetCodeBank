package com.basic;

/*
* 递归解决二叉树问题，伪代码思路
* */
public class TreeRecursion {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //自顶向下解决方案，把上层数值通过参数传递给下层，使用全局变量存储答案
    public int topDown(TreeNode root, int level) {
        int ret = 0;
        if (root == null) {
            return ret;
        }
        int left = topDown(root.left, level); // 左子树找答案
        int right = topDown(root.right, level); // 右子树找答案
        return left + right;
    }

    //树深度demo
    int maxDepth;
    public void maxDepth(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if(root.left == null && root.right == null) {
            maxDepth = Math.max(maxDepth, depth + 1);
        }
        maxDepth(root.left, depth + 1);
        maxDepth(root.right, depth + 1);
    }

    //自底向上解决方案，上层数值依赖下层数值，基于左子树和右子树的结果获得当前层的数值，返回值
    public int bottomUp(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = bottomUp(root.left);
        int right = bottomUp(root.right);
        return Math.max(left, right) + 1;
    }

    //树深度demo
    public int maxDep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDep(root.left);
        int right = maxDep(root.right);
        return Math.max(left, right) + 1;
    }

}
