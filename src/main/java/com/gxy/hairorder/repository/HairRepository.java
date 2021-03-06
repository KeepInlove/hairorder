package com.gxy.hairorder.repository;

import com.gxy.hairorder.entity.Hair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author GUO
 * @Classname HairsRepository
 * @Description TODO
 * @Date 2021/12/8 19:48
 */
public interface HairRepository extends JpaRepository<Hair,Long> {

    int countByHairTypeId(Long hairTypeId);

    List<Hair> findByHairTypeId(Long hairTypeId);

    List<Hair> findByBarberTypeId(Long barberTypeId);

    List<Hair> findByHairNameLike(String hairName);
    Hair findByHairId(Long hairId);
}
