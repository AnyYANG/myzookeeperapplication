package cn.liuyangjob.zk.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Created by  liuyang
 * 2019/8/28    18:35
 * cn.liuyangjob.zk.watcher
 * All Right Reserved by liuyang.
 **/

public class MyzkWatcher implements Watcher {

    public void process(WatchedEvent event) {
     Event.KeeperState  state = event.getState();
        System.out.println("state"+state+"Path|"+event.getPath()+"Type|"+event.getType()+"Wrapper|"+event.getWrapper());
    }
}
