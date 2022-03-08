package com.gxy.hairorder.repository;

import com.gxy.hairorder.entity.Hair;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author GUO
 * @Classname HairsRepository
 * @Description TODO
 * @Date 2021/12/8 19:48
 */
public interface HairRepository extends JpaRepository<Hair,Long> {
    int countByHairTypeId(Long hairTypeId);
    Hair findByHairId(Long hairId);
}
