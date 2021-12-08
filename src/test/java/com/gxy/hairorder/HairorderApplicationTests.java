package com.gxy.hairorder;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class HairorderApplicationTests {

    @Test
    void contextLoads() {
        log.debug("debug...");
        log.info("info...");
        String name="guo";
        String password="123456";
        log.info("name:"+name+"password:"+password);
        log.info("-----");
        log.info("name:{},password:{}",name,password);
        log.error("error...");
    }

}
