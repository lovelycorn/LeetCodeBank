package com.leetcodebank;

public class LeetCode725 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        ListNode[] res = splitListToParts(n1, 3);

        System.out.println("helloworld");
    }

    public static ListNode[] splitListToParts(ListNode root, int k) {
        if (k <= 0) {
            return new ListNode[0];
        }
        if(root == null){
            return new ListNode[k];
        }
        int len = 1;
        ListNode p = root;
        while (p.next != null) {
            p = p.next;
            len++;
        }
        ListNode[] ret = new ListNode[k];
        ret[0] = root;
        int count = 1;
        p = root;

        if (k > len) {
            for (int i = 0; i < len; i++) {
                p = root;
                root = root.next;
                p.next = null;
                ret[count] = root;
                count++;
            }
            return ret;
        }
        int n = len / k;
        int r = len % k;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < n + 1; j++) {
                p = root;
                root = root.next;
            }
            p.next = null;
            ret[count] = root;
            count++;
        }
        for (int i = 0; i < (k - r - 1); i++) {
            for (int j = 0; j < n; j++) {
                p = root;
                root = root.next;
            }
            p.next = null;
            ret[count] = root;
            count++;
        }
        return ret;
    }
}
