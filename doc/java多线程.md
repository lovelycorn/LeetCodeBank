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



省略一些无用的章节...

## 线程的基本方法

### 线程等待 wait

调用该方法的线程进入WAITING状态，只有等待另外线程的通知或被中断才会返回，需要注意的是调用wait()方法后，会释放对象的锁。因此，wait方法一般用在同步方法或同步代码块中

### 线程睡眠 sleep

sleep导致当前线程休眠，与wait方法不同的是sleep不会释放当前占有的锁，sleep(long)会导致线程进入TIMED-WAITING状态，而wait()方法会导致当前线程进入WAITNG状态

### 线程让步 yield

yield会使当前线程让出CPU执行时间片，与其他线程一起重新竞争CPU时间片。一般情况下，优先级高的线程有更大的可能性成功竞争得到CPU时间片，但这又不是绝对的，有的操作系统对线程优先级并不敏感

### 线程中断 interrupt

中断一个线程，其本意是给这个线程一个通知信号，会影响这个线程内部的一个中断标识位。这个线程本身并不会因此而改变状态（如阻塞，终止等）

### Join - 等待其他线程终止

join()方法，等待其他线程终止，在当前线程中调用一个线程的join()方法，则当前线程转为阻塞状态，回到另一个线程结束，当前线程再由阻塞状态变为就绪状态，等待cpu的宠幸

为什么要用join()方法

很多情况下，主线程生成并启动了子线程，需要用到子线程返回的结果，也就是需要主线程需要在子线程结束后再结束，这时候就要用到join()方法。

### 线程唤醒 notify

Object类中的notify()方法，唤醒在此对象监视器上等待的单个线程，如果所有线程都在此对象上等待，则会选择唤醒其中一个线程，选择是任意的，并在对实现做出决定时发生，线程通过调用其中一个wait()方法，在对象的监视器上等待，直到当前的线程放弃此对象的锁定，才能继续执行被唤醒的线程，被唤醒的线程将以常规方式与在该对象上主动同步的其他所有线程进行竞争。类似的方法还有notifyAll()，唤醒再次监视器上等待的所有线程

### 基本方法小汇总

sleep(): 强迫一个线程睡眠N毫秒

isAlive(): 判断一个线程是否存活

join(): 等待线程终止

activeCount(): 程序中活跃的线程数

enumerate(): 枚举程序中的线程

currentThread(): 得到当前线程

isDaemon(): 一个线程是否为守护线程

setDaemon(): 设置一个线程为守护线程。（用户线程和守护线程的区别在于，是否等待主线程依赖于主线程结束而结束）

setName(): 为线程设置一个名称

wait(): 强迫一个线程等待

notify(): 通知一个线程继续运行

setPriority(): 设置一个线程的优先级

getPriority(): 获得一个线程的优先级



又跳了...

## 线程池

线程复用是基础

### 线程池的组成 -- 4部分

**线程池管理器**：用于创建并管理线程池

**工作线程**：线程池中的线程

**任务接口**：每个任务必须实现的接口，用于工作线程调度其运行

**任务队列**：用于存放待处理的任务，提供一种缓冲机制

Java中的线程池是通过Executor框架实现的，该框架有

Executor, Executors, ExecutorService, ThreadPoolExecutor, Callable和Future, FutureTask这几个类

### ThreadPoolExecutor构造方法参数解析

**corePoolSize**：指定了线程池中的线程数量

**maximumPoolSize**：指定了线程池中的最大线程数量

**keepAliveTime**：当前线程池数量超过corePoolSize时，多余的空闲线程的存活时间，即多次时间内会被销毁

**unit**：keepAliveTime的单位

**workQueue**：任务队列，被提交但尚未执行的任务

**threadFactory**：线程工厂，用于创建线程，一般用默认的即可

**handler**：拒绝策略，当任务太多来不及处理，如何拒绝任务

### 拒绝策略

1、线程池中的线程已经用完了；2、等待队列已经排满

两种情况满足时，触发拒绝策略，JDK提供了内置的拒绝策略

策略一、AbortPolicy：直接抛出异常，阻止系统正常运行

策略二、CallerRunsPolicy：只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。显然这样做不会真的丢弃任务，但是，任务提交线程的性能极有可能会急剧下降

策略三、DiscardOldestPolicy：丢弃最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务

策略四、DiscardPolicy：该策略默默地丢弃无法处理的任务，不予任何处理。如果允许任务丢失，这是最好的一种方案

以上策略均实现RejectedExecutionHandler接口，若以上策略仍无法满足实际需要，完全可以自己扩展RejectedExecutionHandler接口

### java线程池工作过程

1、线程池刚创建时，里面没有一个线程。任务队列是作为参数传进来的。不过，就算队列里面有任务，线程池也不会马上执行它们

2、当调用execute()方法添加一个任务时，线程池会做如下判断：

如果正在运行的线程数量小于corePoolSize，那么马上创建线程运行这个任务

如果正在运行的线程数量大于或等于corePoolSize，那么将这个任务放入队列

如果这时候队列满了，而且正在运行的线程数量小于maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务

如果队列满了，而且正在运行的线程数量大于或等于maximumPoolSize，那么线程池会抛出异常RejectExecutionException

3、当一个线程完成任务时，它会从队列中取下一个任务来执行

4、当一个线程无事可做，超过一定的时间(keepAliveTime)时，线程池会判断，如果当前运行的线程数大于corePoolSize，那么这个线程就被停掉。所以线程池的所有任务完成后，它最终会收缩到corePoolSize的大小

![image-20211226140402831](D:\ideaProject\LeetCodeBank\doc\threadPool_running.png)

CountDownLatch、CyclicBarrier、Semaphore用法

CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同；CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的

Semaphore其实和锁有些类似，它一般用于控制对某组资源的访问权限



volatile关键字作用

可见性，把标记的变量放在可读区



Java中用到的线程调度

抢占式调度

由系统控制，在某种运行机制下，每条线程都分同样的执行时间片，也可能是某些线程执行的时间片较长，总之是在控制线程的时间片，使得一个线程的堵塞不会导致整个进程的崩溃

协同式调度

串行调度，一个线程执行完后通知系统，切换到下一个线程，这样的情况线程不会存在多线程同步的问题，但是某个线程的堵塞可以导致整个进程崩溃

JVM的线程调度实现（抢占式调度）

java使用的线程调度为抢占式调度，Java中线程会按优先级分配CPU时间片运行，且优先级越高越优先执行，但优先级高并不代表能独自占用执行时间片，可能是优先级高得到越多得执行时间片，反之，优先级低得分到的执行时间少但不会分配不到执行时间

线程让出CPU的情况

当前运行线程主动放弃CPU，JVM暂时放弃CPU操作（基于时间片轮转调度的JVM操作系统不会让线程永久放弃CPU，或者说放弃本次时间片的执行权），例如调用yield()方法

当前运行线程因为某些原因进入阻塞状态，例如阻塞在I/O上

当前运行线程结束，即运行完run()方法里面的任务

CAS

CAS Compare And Swap/Set 比较并交换，CAS包含三个参数，V,E,N 

V 表示要更新的变量（内存值）

E 表示预期值（旧的）

N 表示新值

当且仅当V = E，才会将V的值设为N

如果 V ≠ E，说明已经有其他线程做了更新，则当前线程什么都不做

最后CAS返回当前V的真实值

CAS操作是乐观锁，它总是认为自己可以成功完成操作。当多个线程同时使用CAS操作一个变量时，只有一个会胜出，并成功更新，其余均会失败。失败的线程不会被挂起，仅是被告知失败，并且允许再次尝试，当然也允许失败的线程放弃操作。

基于这样的原理，CAS操作即使没有锁，也可以发现其他线程对当前线程的干扰，并进行恰当的处理
