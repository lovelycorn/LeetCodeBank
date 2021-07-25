package com.leetcodebank;

public class Student implements Comparable<Student>{
    int id;
    int age;
    public Student(int id, int age){
        this.id = id;
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    @Override
    public int compareTo(Student o) {
        if(this.age == o.getAge())
            return 0;
        else
            return this.age - o.getAge();
    }
}
