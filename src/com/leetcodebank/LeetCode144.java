package com.leetcodebank;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LeetCode144 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    List<Integer> result = new ArrayList<>();

    //递归解题思路
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }
        result.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
        return null;
    }

    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    //迭代解题思路
    public List<Integer> preorderTraversalwithStack(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                ret.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return ret;
    }
}