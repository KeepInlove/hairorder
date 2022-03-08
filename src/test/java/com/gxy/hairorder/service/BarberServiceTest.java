package com.gxy.hairorder.service;

import com.gxy.hairorder.form.BarberForm;
import com.gxy.hairorder.req.BarberReq;
import com.gxy.hairorder.req.UserReq;
import com.gxy.hairorder.resp.BarberResp;
import com.gxy.hairorder.resp.PageResp;
import com.gxy.hairorder.resp.UserResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GUO
 * @Classname BarberServiceTest
 * @Description TODO
 * @Date 2022/2/26 17:54
 */
@SpringBootTest
@Slf4j
public class BarberServiceTest {
    @Autowired
    private BarberService barberService;
    @Test
   public void list() {
        BarberReq barberReq=new BarberReq();
        barberReq.setPage(0);
        barberReq.setSize(3);
        PageResp<BarberResp> list = barberService.list(barberReq);
        log.info(list.toString());
    }
    @Test
    public void save(){
        BarberForm barberForm=new BarberForm();
        barberForm.setBarberDes("123");
        barberForm.setBarberImg("http://127.0.0.1:8089/513E5F6D5DCF4B95915D29358B4EC4D61.png");
        barberForm.setBarberName("电磁场");
        barberForm.setBarberPrice(new BigDecimal(18.50));
        barberForm.setBarberTypeId(9543931251934208l);
//        barberForm.setBarberStatus(true);
        barberService.save(barberForm);
    }
    @Test
    public void find(){
        BarberResp barberResp = barberService.findById(2l);
        log.info(barberResp.toString());
    }
}