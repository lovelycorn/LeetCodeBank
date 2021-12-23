# 算法复杂度分析 -- 自学笔记

算法复杂度是用来估算算法好坏的指标，分为

时间复杂度 time complexity : 估算程序指令的执行次数（执行时间）

空间复杂度 space complexity : 估算所需占用的存储空间

## 时间复杂度

### 常数阶O(1)

无论代码执行了多少行，只要是没有循环等复杂结构，那这个代码的时间复杂度就是O(1)

### 线性阶O(n)

```java
for(i=1; i<=n; ++i)
{
   j = i;
   j++;
}
```

**for循环里面的代码会执行n遍**，因此它消耗的时间是随着n的变化而变化的，因此这类代码都可以用O(n)来表示它的时间复杂度

### 对数阶O(logN)

```java
int i = 1;
while(i<n)
{
    i = i * 2;
}
```

在while循环里面，每次都将i乘以2，乘完之后，**i距离n就越来越近了**

循环x次之后，结束循环，此时  **2^x = n**

x = log2^n  x次就是我们预期的时间复杂度

### 线性对数阶O(nlogN)

将上面对数阶O(logn)的代码循环N遍之后，他的时间复杂度就是O(nlogN)

```java
for(m=1; m<n; m++)
{
    i = 1;
    while(i<n)
    {
        i = i * 2;
    }
}
```

### 平方阶O(n^2)

把O(n)的代码再嵌套循环一遍，他的时间复杂度就是O(n^2)

```java
for(x=1; i<=n; x++)
{
   for(i=1; i<=n; i++)
    {
       j = i;
       j++;
    }
}
```

2层n循环，它的时间复杂度就是O(n*n)，即O(n^2)

当其中一层循环变成m

```java
for(x=1; i<=m; x++)
{
   for(i=1; i<=n; i++)
    {
       j = i;
       j++;
    }
}
```

它的时间复杂度为O(m*n)

### 立方阶O(n^3)、K次方阶O(n^k)

参考平方阶推导

## 空间复杂度

空间复杂度是对一个算法在运行过程中临时占用存储空间大小的一个量度，反映的是一个趋势，用S(n)来定义

常见的空间复杂度 O(1) O(n) O(n^2)

### 空间复杂度O(1)

如果算法执行所需要的临时空间不随着某个变量n的大小而变化，即此算法空间复杂度为一个常量，可表示为O(1)

```java
int i = 1;
int j = 2;
++i;
j++;
int m = i + j;
```

代码中的i, j, m所分配的空间都不随着处理数据量变化，因此它的空间复杂度 S(n) = O(1)

### 空间复杂度O(n)

```java
int[] m = new int[n]
for(i=1; i<=n; ++i)
{
   j = i;
   j++;
}
```

第一行new了一个数组出来，数组占用大小为n，后续没有分配新的空间，所以整段空间复杂度为S(n) = O(n)

按照规律，后面以此类推




