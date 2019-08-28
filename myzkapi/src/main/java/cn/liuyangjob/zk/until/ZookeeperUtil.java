package cn.liuyangjob.zk.until;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by  liuyang
 * 2019/8/27    17:44
 * cn.liuyangjob.zk.util
 * All Right Reserved by liuyang.
 **/
public class ZookeeperUtil {
    /**
     * zookeeper服务器地址
     */
    public static final String connectString = "113.142.70.112:2181";
    /**
     * 定义session失效时间
     */
    public static final int sessionTimeout = 5000;
    /**
     *  定义path
     */
    public static final String path = "/root";


    /**
     * 返回一个zookeeper链接
     * @param watcher
     * @return
     */
    public static ZooKeeper getZookeeperConnection(Watcher watcher) {
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }
}
