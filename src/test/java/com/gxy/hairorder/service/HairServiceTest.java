package com.gxy.hairorder.service;

import com.gxy.hairorder.controller.HairController;
import com.gxy.hairorder.form.HairForm;
import com.gxy.hairorder.resp.HairResp;
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
    @Autowired
    private HairController hairController;
    @Test
   public void list() {
//        HairReq hairReq=new HairReq();
//        hairReq.setPage(0);
//        hairReq.setSize(3);
//        PageResp<HairResp> list = hairService.list(hairReq);
//        log.info(list.toString());
//        List<HairResp> respList = hairService.wxAll();
//        log.info(respList.toString());
    }

    @Test
   public void save() {
        HairForm hairForm=new HairForm();
        hairForm.setHairName("测试5");
        hairForm.setHairTypeId(9836171155751936l);
        hairForm.setHairDes("测试5");
        hairForm.setBarberTypeId(9797083173766144l);
        hairForm.setHairPrice(new BigDecimal(55.50));
        hairService.save(hairForm);
    }

    @Test
    void findById() {
        hairController.findHairId(9837053321167872l);

    }

    @Test
    void hairListByHairType() {
        List<HairResp> hairRespList = hairService.hairListByHairType(9836171155751936l);
        log.info(hairRespList.toString());
    }
    @Test
    void hairListByBarberType(){
        List<HairResp> hairRespList = hairService.hairListByBarberType(9797083173766144l);
        log.info(hairRespList.toString());
    }
}