package com.gxy.hairorder.repository;

import com.gxy.hairorder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author GUO
 * @Classname UserRepository
 * @Description TODO
 * @Date 2021/12/8 12:46
 */

public interface UserRepository extends JpaRepository<User,Long> {
    User findByPhone(String phone);
}
