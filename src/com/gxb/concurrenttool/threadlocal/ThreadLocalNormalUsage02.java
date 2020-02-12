package com.gxb.concurrenttool.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1000个打印日期的任务，用线程池来执行
 */
public class ThreadLocalNormalUsage02 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public String date(int seconds) {
        //参数的单位是毫秒
        Date date = new Date(1000 * seconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 1000; i++) {
            final int finalI = i;
            threadPool.submit(() -> {
                new Thread(() -> {
                    System.out.println(new ThreadLocalNormalUsage02().date(finalI));
                }).start();
            });
        }
    }
}