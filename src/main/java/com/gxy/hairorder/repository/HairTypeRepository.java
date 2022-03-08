package com.gxy.hairorder.repository;


import com.gxy.hairorder.entity.HairType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author GUO
 * @Classname HairsTypeRepository
 * @Description TODO
 * @Date 2022/3/5 16:23
 */
public interface HairTypeRepository extends JpaRepository<HairType,Long> {
    HairType findByHairTypeId(Long hairTypeId);
}
