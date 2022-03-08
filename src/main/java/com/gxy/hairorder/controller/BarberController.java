package com.gxy.hairorder.controller;

import com.gxy.hairorder.form.BarberForm;
import com.gxy.hairorder.req.BarberReq;
import com.gxy.hairorder.resp.*;
import com.gxy.hairorder.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


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

    @GetMapping("/list")
    public CommonResp userList(@Valid BarberReq barberReq){
        CommonResp<PageResp<BarberResp>> resp=new CommonResp<>();
        PageResp<BarberResp> pageResp = barberService.list(barberReq);
        resp.setContent(pageResp);
        return resp;
    }
    @PostMapping("/save")
    public CommonResp barberTypeSave(@Valid @RequestBody BarberForm barberForm){
        CommonResp resp=new CommonResp();
        barberService.save(barberForm);
        return resp;
    }
    @DeleteMapping("/delete/{barberId}")
    public CommonResp barberDel(@PathVariable Long barberId){
        CommonResp resp=new CommonResp();
        resp.setMessage("删除理发师类型");
        barberService.del(barberId);
        return resp;
    }
    @GetMapping("/find/{barberId}")
    public CommonResp barberTypeDel(@PathVariable Long barberId){

        CommonResp resp=new CommonResp();
        BarberResp barberResp = barberService.findById(barberId);
        if(!ObjectUtils.isEmpty(barberResp)){
            resp.setContent(barberResp);
            resp.setMessage("查询成功");
            return resp;
        }else {
            resp.setMessage("未查询到数据,请检查请求数据");
            resp.setSuccess(false);
            return resp;
        }
    }
}
