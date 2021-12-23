# java多线程 -- 自学笔记



## java线程的创建和实现

### 继承Thread类

Thread本质也是**实现了Runnable**接口，是一个线程的实例

启动线程的唯一方法是Thread类的**start()**方法，该方法是一个**native**方法，它将启动一个新线程去执行run()方法

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

对于有些类已经**extends其他类**这种情况，没办法直接继承extends Thread，这个时候可以选择实现Runnable接口

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

### ExecutorService 、Callable<Class> 、Future有返回值线程

有返回值的任务必须要实现Callable接口，无返回值的必须实现Runnable，是一一对应的

执行Callable任务后，可以获取一个Future对象，在该对象上调用get就可以获取到Callable任务返回的Object，再结合线程池接口ExecutorService，可以实现有返回结果的多线程

```java
public static void main(String[] args) {
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
```

### 基于线程池的实现方式

线程和数据库连接资源非常宝贵，每次创建和销毁会浪费

使用缓存策略 -- 线程池

```java
ExecutorService threadPool = Executors.newFixedThreadPool(10);
while(true) {
    threadPool.execute(new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "is running..");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
}
```

### 

## 线程池 -- todo

Java里线程池的顶级接口为Executor，Executor严格来说只是一个执行线程的工具，真正线程池接口为ExecutorService

![image-20211223203448549](D:\ideaProject\LeetCodeBank\doc\thread.png)

### newCachedThreadPool

创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。

### newFixedThreadPool

创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程。

### newScheduledThreadPool

创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行

### newSingleThreadExecutor

创建一个线程池（这个线程池只有一个线程），这个线程池可以在线程死后（或发生异常时）重新启动一个线程来替代原来的线程继续执行下去



## 线程生命周期（状态）

线程创建后，不是已启动就进入执行状态，也不会一直在执行状态，有如下几个状态

new新建、runnable就绪、running运行、blocked阻塞、dead死亡

线程启动后，CPU不会被一直霸占，所以CPU需要在多条线程之间切换，于是线程状态也会多次在运行、阻塞之间切换

### 新建状态 new

当程序使用**new关键字创建**了一个线程之后，该线程就处于新建状态，此时仅有JVM为其分配内存，并初始化其成员变量的值

### 就绪状态 runnable

当线程对象调用了**start()**方法之后，该线程处于就绪状态。Java虚拟机会为起创建方法调用栈和程序计数器，等待调度运行

### 运行状态 running

如果处于就绪状态的线程获得了CPU，开始**执行run()方法**的线程执行体，则该线程处于运行状态

### 阻塞状态 blocked

阻塞状态是指线程因为某种原因**放弃了cpu使用权**，也即让出了cpu timeslice，暂时停止运行。直到线程进入可运行(runnable)状态，才有机会再次获得cpu timeslice转到运行(running)状态。阻塞的情况分三种

**等待阻塞**（o.wait -> 等待队列）：运行(running)的线程**执行o.wait()**方法，JVM会把该线程放入等待队列(waitting queue)中

**同步阻塞**（lock->锁池）：运行(running)的线程在**获取对象的同步锁**时，若该同步**锁被别的线程占用**，则JVM会把该线程放入锁池(lock pool)中

**其他阻塞**（sleep/join）：运行(running)的线程**执行Thread.sleep(long ms)**或**t.join()方法**，或者**发出I/O请求**时，JVM会把该线程置为阻塞状态。当sleep()状态超时，join()等待线程终止或者超时、或则I/O处理完毕时，线程重新转入可运行(runnable)状态

### 线程死亡dead

线程会以下面三种方式结束，结束后就是死亡状态

**正常结束**：run()或call()方法执行完成，线程正常结束

**异常结束**：线程抛出一个未捕获的Exception或Error

**调用stop**：直接调用该线程的stop()方法来结束该线程，此方法通常容易导致死锁，不推荐使用

![image-20211223210611675](D:\ideaProject\LeetCodeBank\doc\thread_lifecycle.png)



## 终止线程的4种方式

正常运行结束

使用退出标志退出线程

interrupt方法结束线程

stop方法终止线程（线程不安全）

## sleep与wait区别

todo

## start与run区别

todo

## Java后台线程 -- 守护线程

todo

## Java锁

### 乐观锁

乐观思想，认为读多写少，并发写的可能性较低，所以我先读了再说

先读数据，默认其他人不会对此数据修改，在自己更新的时候，去判断其他人有没有更新这个数。

写之前读取，加锁，进行和之前拿的数据比较，如果一样证明无人修改，直接写入更新，如果比较失败，重复读-比较-写

todo CAS操作是啥

### 悲观锁

悲观思想，认为写多，遇到并发的可能性高

每次读写数据的时候都会上锁，别人像读写这个数据时会被block，直到拿到锁

Like Synchronized and RetreenLock

### 自旋锁

如果持有锁的线程能在很短时间内释放锁资源，那么那些等待竞争锁的线程就不需要做内核态和用户态之间的切换进入阻塞挂起状态，它们只需要等一等（自旋），等持有锁的线程释放锁后即可立即获取锁，这样就避免用户线程和内核的切换和消耗。

自旋可以理解为，线程占用着cpu放空等待，所以需要设定一个自旋等待的最大时间

适用于竞争不激烈的场景，不然就会导致线程占着茅坑不拉屎

**如何选择自旋锁的时间阈值：**

jdk1.5自选周期写死

jdk1.6引入了适应性自旋锁，由前一次在同一个锁上的自旋时间以及锁的拥有者的状态来决定

1.6使用JVM配置开启参数，1.7以后jvm控制









