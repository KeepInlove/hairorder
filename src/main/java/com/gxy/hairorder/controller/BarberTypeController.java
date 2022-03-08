package com.gxy.hairorder.controller;


import com.gxy.hairorder.req.BarberTypeReq;
import com.gxy.hairorder.resp.BarberTypeResp;
import com.gxy.hairorder.resp.CommonResp;
import com.gxy.hairorder.service.BarberTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author GUO
 * @Classname BarberTypeController
 * @Description 理发师类型分类
 * @Date 2021/12/13 21:18
 */
@RestController
@RequestMapping("/barberType")
public class BarberTypeController {

    @Autowired
    private BarberTypeService barberTypeService;

    @GetMapping("/list")
    public CommonResp barberTypeList(){
        CommonResp resp=new CommonResp();
        List<BarberTypeResp> typeRespList = barberTypeService.list();
        resp.setContent(typeRespList);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp barberTypeSave(@Valid @RequestBody BarberTypeReq barberTypeReq){
        CommonResp resp=new CommonResp();
        resp.setMessage("新增或修改理发师类型");
        barberTypeService.save(barberTypeReq);
        return resp;
    }
    @DeleteMapping("/delete/{barberTypeId}")
    public CommonResp barberTypeDel(@PathVariable Long barberTypeId){
        CommonResp resp=new CommonResp();
        resp.setMessage("删除理发师类型");
        barberTypeService.del(barberTypeId);
        return resp;
    }
}
