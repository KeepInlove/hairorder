package com.gxy.hairorder.repository;

import com.gxy.hairorder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author GUO
 * @Classname OrderRepsitory
 * @Description TODO
 * @Date 2021/12/8 19:48
 */
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long userId);
    Order findByOrderId(Long orderId);
    List<Order> findByPhone(String phone);
}
