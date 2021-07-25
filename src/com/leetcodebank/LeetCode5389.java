package com.leetcodebank;

import java.util.*;

public class LeetCode5389 {
    public static void main(String[] args) {
        System.out.println(5389);
        String str = "4";
        int i = Integer.parseInt(str);
        System.out.println(i);


    }

    public static List<List<String>> displayTable(List<List<String>> orders) {
        final String beef = "Beef Burrito";
        final String cevich = "Ceviche";
        final String fc = "Fried Chicken";
        final String water = "Water";
        List<List<String>> displayTable = new ArrayList<>();
        List<String> firstLine = new ArrayList<>();
        firstLine.add("Table");
        firstLine.add(beef);
        firstLine.add(cevich);
        firstLine.add(fc);
        firstLine.add(water);
        displayTable.add(firstLine);
        List<Integer> tableCount = new ArrayList<>();

        for (List<String> list : orders) {
            int tableNum = Integer.parseInt(list.get(1));
            if (!tableCount.contains(tableNum)) {
                tableCount.add(tableNum);
            }
        }
        tableCount.sort(new myCompare());
        HashMap<Integer, List<String>> map = new HashMap<>();
        for (int table : tableCount) {
            List<String> singleLine = new ArrayList<>(5);
            singleLine.add("" + table);
            singleLine.add("0");
            singleLine.add("0");
            singleLine.add("0");
            singleLine.add("0");
            map.put(table, singleLine);
        }

        for (List<String> list : orders) {
            int tableNum = Integer.parseInt(list.get(1));
            String foodItem = list.get(2);
            List<String> doList = map.get(tableNum);
            switch (foodItem) {
                case beef:
                    doList.set(1, Integer.parseInt(doList.get(1)) + 1 + "");
                    map.put(tableNum,doList);
                    break;
                case cevich:
                    doList.set(1, Integer.parseInt(doList.get(2)) + 1 + "");
                    map.put(tableNum,doList);
                    break;
                case fc:
                    doList.set(1, Integer.parseInt(doList.get(3)) + 1 + "");
                    map.put(tableNum,doList);
                    break;
                case water:
                    doList.set(1, Integer.parseInt(doList.get(4)) + 1 + "");
                    map.put(tableNum,doList);
                    break;
            }
        }

        for (Map.Entry<Integer,List<String>> entry: map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            displayTable.add(entry.getValue());
        }



        return displayTable;
    }

    public static class myCompare implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

}
