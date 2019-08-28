package cn.liuyangjob.zk.client;

import cn.liuyangjob.zk.bean.Node;
import cn.liuyangjob.zk.register.ZookeeperRegister;
import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by  liuyang
 * 2019/8/28    16:14
 * cn.liuyangjob.zk.client
 * All Right Reserved by liuyang.
 **/

public class ZkCustomer implements Watcher {
    ZooKeeper zooKeeper;
    List<String> list;

    public void connectZooKeeper() {
        ZookeeperRegister register = new ZookeeperRegister();
        zooKeeper = register.getConnection("113.142.70.112:2181", this, 6000);
        try {
            list = zooKeeper.getChildren("/ly", true);
            for(String i : list){
                System.out.println(i);
                byte[] b =   zooKeeper.getData("/ly/"+i,true, null);
                Node node= JSON.parseObject(new String(b),Node.class);
                System.out.println(node.getServerName());
                System.out.println(node.getIp());
                System.out.println(node.getPort());
                System.out.println(node.getStatus());
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        ZkCustomer customer = new ZkCustomer() ;
        customer.connectZooKeeper();
    }


    public void process(WatchedEvent watchedEvent) {

    }
}
