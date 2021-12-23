package com.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StringSort {
    public static void main(String[] args) {
        System.out.println("String sort");
        List<String> list1 = new ArrayList<String>();
        list1.add("a");
        list1.add("i");
        list1.add("d");
        list1.add("c");
        System.out.println(list1);
        Collections.sort(list1);
        System.out.println(list1);
        List<String> list2 = new ArrayList<>();
        list2.add("caa");
        list2.add("cab");
        list2.add("daa");
        list2.add("add");
        System.out.println(list2);
        Collections.sort(list2);
        System.out.println(list2);
    }
}
