package com.hujy.demo.service.impl;

import com.hujy.demo.entity.Config;
import com.hujy.demo.entity.Order;
import com.hujy.demo.entity.OrderItem;
import com.hujy.demo.mapper.ConfigMapper;
import com.hujy.demo.mapper.OrderItemMapper;
import com.hujy.demo.mapper.OrderMapper;
import com.hujy.demo.service.OrderService;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description
 *
 * @author hujy
 * @version 1.0
 * @date 2019-09-18 10:47
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public Integer saveOrder(Order order) {
        return orderMapper.save(order);
    }

    @Override
    public Integer saveOrderItem(OrderItem orderItem, Integer userId) {
        try (HintManager hintManager = HintManager.getInstance()) {
            hintManager.addDatabaseShardingValue("t_order_item", userId);
            return orderItemMapper.save(orderItem);
        }
    }

    @Override
    public Order selectBySharding(Integer userId, Integer orderId) {
        return orderMapper.selectBySharding(userId, orderId);
    }

    @Override
    public List<Order> selectOrderJoinOrderItem(Integer userId, Integer orderId) {
        try (HintManager hintManager = HintManager.getInstance()) {
            hintManager.addDatabaseShardingValue("t_order_item", userId);
            return orderMapper.selectOrderJoinOrderItem(userId, orderId);
        }
    }

    @Override
    public List<Order> selectOrderJoinOrderItemNoSharding(Integer userId, Integer orderId) {
        return orderMapper.selectOrderJoinOrderItem(userId, orderId);
    }

    @Override
    public List<Order> selectOrderJoinConfig(Integer userId, Integer orderId) {
        return orderMapper.selectOrderJoinConfig(userId, orderId);
    }

    @Override
    public Integer saveConfig(Config config) {
        return configMapper.save(config);
    }

    @Override
    public Config selectConfig(Integer id) {
        return configMapper.selectById(id);
    }
}
