# java多线程 -- 自学笔记



## java线程的创建和实现

### 继承Thread类

```java
public class SampleThread {
    public static class MyExtendThread extends Thread {
        public void run() {
            System.out.println("MyExtendThread is running");
        }
    }

    public static void main(String[] args) {
        //启动 MyExtendThread
        MyExtendThread myExtendThread = new MyExtendThread();
        myExtendThread.start();
    }
}



```
### 实现Runnable接口

```java
public class SampleThread {
    public static class MyRunnableThread implements Runnable {
        @Override
        public void run() {
            System.out.println("MyRunnableThread is running");
        }
    }

    public void extendsThread() {
        //启动 MyRunnableThread
        MyRunnableThread myRunnableThread = new MyRunnableThread();
        Thread myThread = new Thread(myRunnableThread)
        myThread.start();
    }
}
```



### 







### 

























