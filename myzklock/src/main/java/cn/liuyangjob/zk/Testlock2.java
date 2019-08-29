package cn.liuyangjob.zk;

import cn.liuyangjob.zk.lock.ZooKeeperLocker;

/**
 * Created by  liuyang
 * 2019/8/29    15:21
 * cn.liuyangjob.zk
 * All Right Reserved by liuyang.
 **/

public class Testlock2 {
    static int i = 0;
    public static void main(String args[]) throws InterruptedException {
        ZooKeeperLocker locker = new ZooKeeperLocker();
        Runnable run = ()-> {
            for(int m=0 ;m<1000; m++){
                locker.lock("sys");
                i=i+1;
              //  System.out.print("  "+i);
              locker.unlock("sys");
            }
        };
        Thread thread =new Thread(run);
        Thread thread2 =new Thread(run);
        thread2.start();
        thread.start();
        thread.join();
        thread2.join();
        System.out.println(i);
    }
    public void add(){

    }
}
