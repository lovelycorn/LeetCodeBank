package com.basic;

public class StaticTest
{
    static
    {
        System.out.println("Hi there");
    }

    public void print()
    {
        System.out.println("Hello");
    }

    public static void main(String args[])
    {
        StaticTest st1 = new StaticTest();
        st1.print();
        StaticTest st2 = new StaticTest();
        st2.print();
    }
}
