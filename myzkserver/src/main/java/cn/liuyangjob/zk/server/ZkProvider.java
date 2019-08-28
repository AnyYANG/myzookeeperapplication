package cn.liuyangjob.zk.server;

import cn.liuyangjob.zk.bean.Node;
import cn.liuyangjob.zk.register.ZookeeperRegister;
import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  liuyang
 * 2019/8/28    10:05
 * cn.liuyangjob.zk.server
 * All Right Reserved by liuyang.
 **/

public class ZkProvider  implements Watcher{
    ZooKeeper zooKeeper;
    static final String  ROOT = "/ly";
    static final String connectString = "113.142.70.112:2181";
    static final int timeout = 6000;
    public void getConnection(){
        ZookeeperRegister register = new ZookeeperRegister();
        zooKeeper = register.getConnection(connectString,this,timeout);
        Node node = new Node();
        node.setClientName("");
        node.setIp("127.0.0.1");
        node.setPort("8080");
        node.setServerName("zkProvicer");
        node.setStatus("ONLINE");
        String data= JSON.toJSONString(node);
        System.out.println(data);
        try {
            zooKeeper.create(ROOT+"/server",data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String args[]) throws IOException {
       ZkProvider  provider = new ZkProvider();
       provider.getConnection();
       System.in.read();
    }


    @Override
    public void process(WatchedEvent event) {

    }
}
