package com.yaoit.springcloud.springdemo.thread.scrapyJd.threadPool;

import com.yaoit.springcloud.springdemo.thread.scrapyJd.SysConstant;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class UserThreadFactory implements ThreadFactory {
    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);
    // 定义线程组名称，在 jstack 问题排查时，非常有帮助
   public  UserThreadFactory(String whatFeaturOfGroup) {
        namePrefix = "From UserThreadFactory's " + whatFeaturOfGroup + "-Worker-"; }
    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + nextId.getAndIncrement();
//        Thread thread = new Thread(null, task, name, 0, false);
//        java8的修改
        Thread thread=new Thread(task,name);
        System.out.println(thread.getName());
        return thread; } }
 //执行任务体
class Task implements Runnable{
    private final AtomicLong count=new AtomicLong(0L);
     @Override
     public void run() {
         System.out.println("running_"+count);

     }
 }