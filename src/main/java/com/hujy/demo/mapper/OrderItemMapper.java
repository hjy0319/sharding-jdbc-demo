package com.hujy.demo.mapper;

import com.hujy.demo.entity.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description
 *
 * @author hujy
 * @version 1.0
 * @date 2019-09-19 16:46
 */
@Mapper
public interface OrderItemMapper {
    @Insert("insert into t_order_item(order_id,remark) values(#{orderId},#{remark})")
    Integer save(OrderItem orderItem);
}
