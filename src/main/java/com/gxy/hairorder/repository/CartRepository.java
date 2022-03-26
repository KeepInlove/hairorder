package com.gxy.hairorder.repository;

import com.gxy.hairorder.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/22 19:14
 * @Classname CartRepository
 * @Description TODO
 */
public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findByUserId(Long userId);
    Integer countByUserId(Long userId);
}
