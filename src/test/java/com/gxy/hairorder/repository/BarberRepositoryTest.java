package com.gxy.hairorder.repository;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author GUO
 * @Classname BarberRepositoryTest
 * @Description TODO
 * @Date 2022/2/26 16:11
 */
@SpringBootTest
@Slf4j
public class BarberRepositoryTest {
    @Autowired
    private BarberRepository barberRepository;
    @Test
    public void countByBarberTypeId() {
        int count = barberRepository.countByBarberTypeId(7799343901590528l);
        log.info(String.valueOf(count));
    }
}
