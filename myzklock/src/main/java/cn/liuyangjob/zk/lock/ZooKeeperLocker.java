package cn.liuyangjob.zk.lock;

import cn.liuyangjob.zk.register.ZookeeperRegister;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by  liuyang
 * 2019/8/28    18:18
 * cn.liuyangjob.zk.lock
 * All Right Reserved by liuyang.
 **/
public class ZooKeeperLocker {
    ZookeeperRegister register = new ZookeeperRegister();
    public ZooKeeper zooKeeper;

    public ZooKeeperLocker() {
        getRegister();
    }

    public ZooKeeper getRegister() {
        zooKeeper = register.getConnection("192.168.1.212:2181", null, 6000);
        return zooKeeper;
    }

    public void lock(String path) {
        try {
            Stat root = zooKeeper.exists("/lock", false);
            if (root == null) {
                zooKeeper.create("/lock", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            Stat stat = zooKeeper.exists("/lock/" + path, false);
            if (stat == null) {
            //    System.out.println("加锁成功");
                try {
                    zooKeeper.create("/lock/" + path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                } catch (Exception e) {
                    try {
                        System.out.println("加锁失败,等待重新申请");
                        Thread.sleep(2500);
                        lock(path);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                }


            } else {
                try {
               //     System.out.println("加锁失败,等待重新申请");
                    Thread.sleep(2500);
                    lock(path);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void unlock(String path) {
        try {
            Stat stat = zooKeeper.exists("/lock/" + path, false);
            if (stat == null) {
            } else {
                zooKeeper.delete("/lock/" + path, -1);
            }
        //    System.out.println("解锁成功");
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
