package com.leetcodebank;

import java.util.*;

public class OrderTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static List<Integer> ret = new ArrayList<>();
    static Stack<TreeNode> stk = new Stack<TreeNode>();

    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = null;
        node3.right = null;
        node4.left = null;
        node4.right = null;
        node5.left = null;
        node5.right = null;
        levelOrder(node1);
        System.out.println();
    }

    //二叉树的先序遍历
    public static List<Integer> preorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return ret;
        }
        if (root == null) {
            return ret;
        }
        ret.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
        return ret;
    }

    public static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        ret.add(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    //二叉树的中序遍历
    public static List<Integer> inorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return ret;
        }
        if (root == null) {
            return ret;
        }
        inOrder(root.left);
        ret.add(root.val);
        inOrder(root.right);
        return ret;
    }

    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        ret.add(node.val);
        inOrder(node.right);
    }

    //二叉树的后序遍历
    public static List<Integer> postorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return ret;
        }
        if (root == null) {
            return ret;
        }
        postOrder(root.left);
        postOrder(root.right);
        ret.add(root.val);
        return ret;
    }

    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        ret.add(node.val);
    }

    //二叉树的层序遍历，BFS
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (Objects.isNull(root)) {
            return new ArrayList<>(0);
        }
        if (root == null) {
            return new ArrayList<>(0);
        }
        Map<TreeNode, Integer> nodeMap = new HashMap<>();
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        //init
        nodeMap.put(root, 0);
        que.offer(root);
        tmp.add(root.val);
        ret.add(tmp);

        while (!que.isEmpty()) {
            TreeNode cur = que.poll();
            int level = nodeMap.get(cur);
            if (cur.left != null) {
                if (!nodeMap.containsValue(level + 1)) {
                    List<Integer> listnew = new ArrayList<>();
                    ret.add(listnew);
                }
                nodeMap.put(cur.left, level + 1);
                ret.get(level + 1).add(cur.left.val);
                que.offer(cur.left);
            }
            if (cur.right != null) {
                if (!nodeMap.containsValue(level + 1)) {
                    List<Integer> listnew = new ArrayList<>();
                    ret.add(listnew);
                }
                nodeMap.put(cur.right, level + 1);
                ret.get(level + 1).add(cur.right.val);
                que.offer(cur.right);
            }
        }

        return ret;
    }
}
