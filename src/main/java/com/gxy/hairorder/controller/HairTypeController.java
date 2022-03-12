package com.gxy.hairorder.controller;


import com.gxy.hairorder.req.HairTypeReq;
import com.gxy.hairorder.resp.HairTypeResp;
import com.gxy.hairorder.resp.CommonResp;
import com.gxy.hairorder.service.HairTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author GUO
 * @Classname HairTypeController
 * @Description 理发师类型分类
 * @Date 2021/12/13 21:18
 */
@RestController
@RequestMapping("/hairType")
public class HairTypeController {

    @Autowired
    private HairTypeService hairTypeService;


    @GetMapping("/list")
    public CommonResp hairTypeList(){
        CommonResp resp=new CommonResp();
        List<HairTypeResp> typeRespList = hairTypeService.list();
        resp.setContent(typeRespList);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp hairTypeSave(@Valid @RequestBody HairTypeReq hairTypeReq){
        CommonResp resp=new CommonResp();
        resp.setMessage("新增或修改理项目类型");
        hairTypeService.save(hairTypeReq);
        return resp;
    }
    @DeleteMapping("/delete/{hairTypeId}")
    public CommonResp hairTypeDel(@PathVariable Long hairTypeId){
        CommonResp resp=new CommonResp();
        resp.setMessage("删除项目类型");
        hairTypeService.del(hairTypeId);
        return resp;
    }
}
