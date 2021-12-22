package com.leetcodebank;

import java.util.Stack;

public class StackSample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        //进栈
        stack.push(1);
        //取栈顶（不出栈）
        stack.peek();
        //出栈
        stack.pop();
        //判断为空
        stack.isEmpty();
        //判断为空，栈特有
        stack.empty();
    }
}
