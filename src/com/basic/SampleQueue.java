package com.basic;

import java.util.Deque;
import java.util.LinkedList;

public class SampleQueue {
    public static void main(String[] args) {
        //声明队列
        //Queue<Integer> queue = new LinkedList<>();
        Deque<Integer> queue = new LinkedList<>();
        //进队
        queue.offer(1);
        //取队列首个（不出队）
        queue.peek();
        //出队
        queue.poll();
    }
}
