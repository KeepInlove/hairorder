package com.gxy.hairorder.repository;

import com.gxy.hairorder.entity.Sys;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author GUO
 * @Classname SysRepository
 * @Description TODO
 * @Date 2022/2/7 21:55
 */
public interface SysRepository extends JpaRepository<Sys,Long> {
    Sys findByUsernameAndPassword(String username,String password);
}
