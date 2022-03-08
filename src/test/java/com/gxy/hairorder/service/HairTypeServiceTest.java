package com.gxy.hairorder.service;

import com.gxy.hairorder.resp.HairTypeResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GUO
 * @Classname HairTypeServiceTest
 * @Description TODO
 * @Date 2022/3/5 17:24
 */
@SpringBootTest
@Slf4j
public class HairTypeServiceTest {

    @Autowired
    private HairTypeService hairTypeService;
    @Test
    public void list() {
        List<HairTypeResp> list = hairTypeService.list();
        log.info(list.toString());
    }

    @Test
    void save() {
    }

    @Test
    void del() {
    }
}