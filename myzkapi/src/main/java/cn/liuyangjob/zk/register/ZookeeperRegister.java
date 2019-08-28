package cn.liuyangjob.zk.register;


import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ZookeeperRegister {

    ZooKeeper zooKeeper;
    public  ZooKeeper getConnection(String connectString,Watcher watcher,int timeout) {
        try {
            zooKeeper=new ZooKeeper(connectString,timeout,watcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  zooKeeper;
    }
}
