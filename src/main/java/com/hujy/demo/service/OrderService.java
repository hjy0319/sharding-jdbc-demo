package com.hujy.demo.service;

import com.hujy.demo.entity.Config;
import com.hujy.demo.entity.Order;
import com.hujy.demo.entity.OrderItem;

import java.util.List;

/**
 * Description
 *
 * @author hujy
 * @version 1.0
 * @date 2019-09-18 10:47
 */
public interface OrderService {
    Integer saveOrder(Order order);

    Integer saveOrderItem(OrderItem orderItem, Integer userId);

    Order selectBySharding(Integer userId, Integer orderId);

    List<Order> selectOrderJoinOrderItem(Integer userId, Integer orderId);

    List<Order> selectOrderJoinOrderItemNoSharding(Integer userId, Integer orderId);

    List<Order> selectOrderJoinConfig(Integer userId, Integer orderId);

    Integer saveConfig(Config config);

    Config selectConfig(Integer id);
}
