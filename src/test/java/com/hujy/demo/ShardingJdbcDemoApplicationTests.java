package com.hujy.demo;

import com.hujy.demo.entity.Config;
import com.hujy.demo.entity.Order;
import com.hujy.demo.entity.OrderItem;
import com.hujy.demo.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcDemoApplicationTests {

    @Autowired
    private OrderService orderService;

    /**
     * 路由保存
     *
     * @param
     * @return void
     * @author hujy
     * @date 2019-09-20 11:36
     */
    @Test
    public void testSaveOrder() {
        for (int i = 0; i < 10; i++) {
            Integer orderId = 1000 + i;
            Integer userId = 10 + i;

            Order o = new Order();
            o.setOrderId(orderId);
            o.setUserId(userId);
            o.setConfigId(i);
            o.setRemark("save.order");
            orderService.saveOrder(o);

            OrderItem oi = new OrderItem();
            oi.setOrderId(orderId);
            oi.setRemark("save.orderItem");
            orderService.saveOrderItem(oi, userId);
        }
    }

    /**
     * 根据分片键查询
     *
     * @param
     * @return void
     * @author hujy
     * @date 2019-09-20 11:26
     */
    @Test
    public void testSelectByUserId() {
        Integer userId = 12;
        Integer orderId = 1002;
        Order o1 = orderService.selectBySharding(userId, orderId);
        System.out.println(o1);

        userId = 17;
        orderId = 1007;
        Order o2 = orderService.selectBySharding(userId, orderId);
        System.out.println(o2);

    }

    /**
     * 与分片子表关联
     *
     * @param
     * @return void
     * @author hujy
     * @date 2019-09-20 11:24
     */
    @Test
    public void testSelectOrderJoinOrderItem() {
        // 指定了子表分片规则
        List<Order> o1 = orderService.selectOrderJoinOrderItem(12, 1002);
        System.out.println(o1);
        // 未指定子表分片规则：导致子表的全路由
        List<Order> o2 = orderService.selectOrderJoinOrderItemNoSharding(12, 1002);
        System.out.println(o2);
    }

    /**
     * 与广播表关联
     *
     * @param
     * @return void
     * @author hujy
     * @date 2019-09-20 11:24
     */
    @Test
    public void testSelectOrderJoinConfig() {
        List<Order> o1 = orderService.selectOrderJoinConfig(12, 1002);
        System.out.println(o1);
        List<Order> o2 = orderService.selectOrderJoinConfig(17, 1007);
        System.out.println(o2);
    }

    /**
     * 广播表保存
     * 对所有数据源进行广播
     *
     * @param
     * @return void
     * @author hujy
     * @date 2019-09-20 11:23
     */
    @Test
    public void testSaveConfig() {
        for (int i = 0; i < 10; i++) {
            Config config = new Config();
            config.setId(i);
            config.setRemark("config " + i);
            orderService.saveConfig(config);
        }
    }

    /**
     * 广播表查询
     * 随机选择数据源
     *
     * @param
     * @return void
     * @author hujy
     * @date 2019-09-20 11:23
     */
    @Test
    public void testSelectConfig() {
        Config config1 = orderService.selectConfig(5);
        System.out.println(config1);

        Config config2 = orderService.selectConfig(7);
        System.out.println(config2);
    }

}
