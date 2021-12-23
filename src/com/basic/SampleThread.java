package com.basic;

public class SampleThread {
    public static class MyExtendThread extends Thread {
        public void run() {
            System.out.println("MyExtendThread is running");
        }
    }

    public static class MyRunnableThread implements Runnable {
        @Override
        public void run() {
            System.out.println("MyRunnableThread is running");
        }
    }

    public static void main(String[] args) {
        //启动 MyExtendThread
        MyExtendThread myExtendThread = new MyExtendThread();
        myExtendThread.start();

        //启动 MyRunnableThread
        MyRunnableThread myRunnableThread = new MyRunnableThread();
        Thread myThread = new Thread(myRunnableThread);
        myThread.start();
    }
}
