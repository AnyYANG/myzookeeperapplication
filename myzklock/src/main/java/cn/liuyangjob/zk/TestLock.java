package cn.liuyangjob.zk;

import cn.liuyangjob.zk.lock.ZooKeeperLocker;

/**
 * Created by  liuyang
 * 2019/8/29    14:24
 * cn.liuyangjob.zk
 * All Right Reserved by liuyang.
 **/

public class TestLock {
    public static void main(String args[]) throws InterruptedException {
        ZooKeeperLocker locker = new ZooKeeperLocker();
        locker.lock("liuyang");
        Thread.sleep(5000);
        locker.unlock("liuyang");
    }
}
