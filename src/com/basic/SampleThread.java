package com.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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

        //启动有返回值的线程
        //创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);
        //创建任务，带返回值
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < 3; i++) {
            Callable c = new Callable() {
                @Override
                public Object call() throws Exception {
                    return "call call call";
                }
            };
            Future f = pool.submit(c);
            list.add(f);
        }
        //关闭线程池
        pool.shutdown();
        //获取所有并发任务的运行结果
        for (Future f: list) {
            try {
                System.out.println("res " + f.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
