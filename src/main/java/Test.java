import org.apache.zookeeper.*;

/**
 * Created by tanght on 16/4/21.
 */
public class Test {
    public static void main(String[] args) throws  Exception{

        // 创建ZK对象
        ZooKeeper zk = new ZooKeeper("127.0.0.1:2119",
                3000,
                new Watcher() { // 出发的事件监控
                    public void process(WatchedEvent event) {
                        System.out.println(event.getType() + "  事件被触发.");
                    }
                }
        );

        // 创建目录节点/ 参数 TODO 1.路径 2.数据 3. 4.
        zk.create("/testRootPath", "testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 创建子目录
        zk.create("/testRootPath/testChildPathOne", "testCihldDataOne".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/testRootPath", false, null))); // 获取子目录数据

        // 获取目录子节点列表
        System.out.println(zk.getChildren("/testRootPath", true));

        // 修改子目录节点数据/ 参数 TODO
        zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);
        System.out.println(new String(zk.getData("/testRootPath/testChildPathOne", false, null)));

        // 目录节点状态/ TODO
        System.out.println("目录节点状态:  " + zk.exists("/testRootPath", true));

        // 创建另一个子节点目录
        zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo", true, null))); // 获取新子节点数据

     /*   // 删除子节点目录/ 参数 TODO
        zk.delete("/testRootPath/testChildPathOne", -1);
        zk.delete("/testRootPath/testChildPathTwo", -1);
        // 删除父目录节点
        zk.delete("/testRootPath", -1)*/;


        // 关闭连接
        zk.close();

    }
}
