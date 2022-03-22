package com.gxy.hairorder.repository;

import com.gxy.hairorder.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author GUO
 * @Classname OrderRepository
 * @Description TODO
 * @Date 2021/12/8 19:48
 */
public interface BarberRepository extends JpaRepository<Barber,Long> {
    int countByBarberTypeId(Long barberTypeId);
    Barber findByBarberId(Long barberId);
    List <Barber> findByBarberTypeId(Long barberTypeId);
}
