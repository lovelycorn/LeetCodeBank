package com.basic;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{3,6,6,8,9,12,20};
        System.out.println(binarySearch(arr, 11));

    }

    public static int binarySearch(int[] arr, int k) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            //int mid = (l + r) / 2;
            int mid = l + (r - l) / 2;
            if (arr[mid] == k) {
                return mid;
            } else if (arr[mid] > k) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
}
