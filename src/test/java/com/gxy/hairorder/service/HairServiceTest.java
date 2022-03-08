package com.gxy.hairorder.service;

import com.gxy.hairorder.entity.Hair;
import com.gxy.hairorder.form.HairForm;
import com.gxy.hairorder.req.BarberReq;
import com.gxy.hairorder.req.HairReq;
import com.gxy.hairorder.resp.BarberResp;
import com.gxy.hairorder.resp.HairResp;
import com.gxy.hairorder.resp.PageResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author GUO
 * @Classname HairsServiceTest
 * @Description TODO
 * @Date 2022/3/5 17:14
 */
@SpringBootTest
@Slf4j
class HairServiceTest {

    @Autowired
    private HairService hairService;
    @Test
   public void list() {
//        HairReq hairReq=new HairReq();
//        hairReq.setPage(0);
//        hairReq.setSize(3);
//        PageResp<HairResp> list = hairService.list(hairReq);
//        log.info(list.toString());
        List<HairResp> respList = hairService.wxAll();
        log.info(respList.toString());
    }

    @Test
   public void save() {
        HairForm hairForm=new HairForm();
        hairForm.setHairName("服务");
        hairForm.setHairTypeId(1l);
        hairForm.setHairDes("一条龙");
        hairForm.setBarberTypeId(7799412300465152l);
        hairForm.setHairPrice(new BigDecimal(55.50));
        hairService.save(hairForm);
    }

    @Test
    void findById() {
    }

    @Test
    void del() {
    }
}