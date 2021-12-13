package com.gxy.hairorder.repository;


import com.gxy.hairorder.entity.BarberType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author GUO
 * @Classname BarberTypeRepositoryTest
 * @Description TODO
 * @Date 2021/12/11 15:42
 */
@SpringBootTest
@Slf4j
public class BarberTypeRepositoryTest {

    @Autowired
    private BarberTypeRepository barberTypeRepository;
    @Test
    public void findByBarberTypeId() {
        Long id=123456l;
        BarberType byBarberType = barberTypeRepository.findByBarberTypeId(id);
        log.info(byBarberType.toString());
    }
}
