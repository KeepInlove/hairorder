package com.gxy.hairorder.service;


import com.gxy.hairorder.req.BarberTypeReq;
import com.gxy.hairorder.resp.BarberTypeResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author GUO
 * @Classname BarberTypeServiceTest
 * @Description TODO
 * @Date 2021/12/11 15:50
 */
@SpringBootTest
@Slf4j
public class BarberTypeServiceTest {

    @Autowired
    private BarberTypeService barberTypeService;
    @Test
    public void save() {
        BarberTypeReq barberTypeReq=new BarberTypeReq();
        barberTypeReq.setBarberTypeId(850090465346560l);
        BigDecimal price = BigDecimal.valueOf(30.00);
        barberTypeReq.setBarberPrice(price);
        barberTypeReq.setBarberTypeName("私人理发师");
        barberTypeService.save(barberTypeReq);
    }
    @Test
    public void list() {
        List<BarberTypeResp> typeRespList = barberTypeService.list();
        log.info(typeRespList.toString());
    }
}