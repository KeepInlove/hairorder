package com.gxy.hairorder.controller;

import com.gxy.hairorder.entity.Barber;
import com.gxy.hairorder.form.BarberForm;
import com.gxy.hairorder.req.BarberReq;
import com.gxy.hairorder.resp.*;
import com.gxy.hairorder.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author GUO
 * @Classname BarberController
 * @Description TODO
 * @Date 2022/2/26 18:02
 */
@RestController
@RequestMapping("/barber")
public class BarberController {

    @Autowired
    private BarberService barberService;

//    @GetMapping("/index")
//    public CommonResp index() {
//        CommonResp<PageResp<BarberResp>> resp = new CommonResp<>();
//        PageResp<BarberResp> pageResp = barberService.list();
//        resp.setContent(pageResp);
//        return resp;
//    }

    @GetMapping("/list")
    public CommonResp userList(@Valid BarberReq barberReq) {
        CommonResp<PageResp<BarberResp>> resp = new CommonResp<>();
        PageResp<BarberResp> pageResp = barberService.list(barberReq);
        resp.setContent(pageResp);
        return resp;
    }
    @PostMapping("/save")
    public CommonResp barberTypeSave(@Valid @RequestBody BarberForm barberForm) {
        CommonResp resp = new CommonResp();
        barberService.save(barberForm);
        return resp;
    }

    @DeleteMapping("/delete/{barberId}")
    public CommonResp barberDel(@PathVariable Long barberId) {
        CommonResp resp = new CommonResp();
        resp.setMessage("删除理发师类型");
        barberService.del(barberId);
        return resp;
    }

    @GetMapping("/find/{barberId}")
    public CommonResp findById(@PathVariable Long barberId) {

        CommonResp resp = new CommonResp();
        BarberResp barberResp = barberService.findById(barberId);
        if (!ObjectUtils.isEmpty(barberResp)) {
            resp.setContent(barberResp);
            resp.setMessage("查询成功");
            return resp;
        } else {
            resp.setMessage("未查询到数据,请检查请求数据");
            resp.setSuccess(false);
            return resp;
        }
    }
    @GetMapping("/findHairId/{hairId}")
    public CommonResp findHairId(@PathVariable Long hairId) {
        CommonResp resp = new CommonResp();
        List<Barber> barberList = barberService.findHairId(hairId);
        resp.setContent(barberList);
        return resp;
    }
    @GetMapping("/findTypeId/{barberTypeId}")
    public CommonResp findTypeId(@PathVariable Long barberTypeId) {
        CommonResp resp = new CommonResp();
        List<Barber> barberList = barberService.findTypeId(barberTypeId);
        resp.setContent(barberList);
        return resp;
    }

}
