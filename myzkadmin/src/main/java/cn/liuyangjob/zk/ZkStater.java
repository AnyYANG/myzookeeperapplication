package cn.liuyangjob.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by  liuyang
 * 2019/8/28    18:22
 * PACKAGE_NAME
 * All Right Reserved by liuyang.
 **/
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class ZkStater {
    public static void main(String args[]) {
        SpringApplication.run(ZkStater.class,args);
    }
}
