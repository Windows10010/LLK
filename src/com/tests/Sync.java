package com.tests;

import java.lang.reflect.Array;
import java.util.*;

public class Sync implements Runnable{
    Integer P = new Integer(1);
    @Override
    public void run(){
            for (int i = 0; i < 10; i++) {
                synchronized (this){
                    P++;
                    System.out.println(Thread.currentThread().getName() + " : " + P);
                }try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }

    public static void main(String[] args) {
        Sync hop = new Sync();
        new Thread(hop,"1").start();
        new Thread(hop,"2").start();
    }
}
