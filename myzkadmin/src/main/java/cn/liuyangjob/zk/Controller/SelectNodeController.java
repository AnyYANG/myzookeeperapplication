package cn.liuyangjob.zk.Controller;

import cn.liuyangjob.zk.register.ZookeeperRegister;
import cn.liuyangjob.zk.watcher.MyzkWatcher;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  liuyang
 * 2019/8/28    18:28
 * cn.liuyangjob.zk.Controller
 * All Right Reserved by liuyang.
 **/
@Controller
public class SelectNodeController {

    ZookeeperRegister register = new ZookeeperRegister();
    ZooKeeper zooKeeper;

    @ResponseBody
    @RequestMapping("/all")
    public Object getAllNode() {
        MyzkWatcher watcher = new MyzkWatcher();
        List<String> resultList = new ArrayList<String>();
        zooKeeper = register.getConnection("113.142.70.112:2181", watcher, 5000);
        try {
            List<String> lists = zooKeeper.getChildren("/", watcher);
            for (String s : lists) {
                List<String> lists2 = zooKeeper.getChildren("/" + s, watcher);
                for (String s2 : lists2) {
                    byte[] b = zooKeeper.getData("/" + s + "/" + s2, watcher, null);
                    System.out.println(s+s2+new String(b));
                    resultList.add(new String(b));
                }
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultList;
    }


}
