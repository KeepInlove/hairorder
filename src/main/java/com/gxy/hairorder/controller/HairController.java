package com.gxy.hairorder.controller;

import com.gxy.hairorder.form.HairForm;
import com.gxy.hairorder.req.HairReq;
import com.gxy.hairorder.resp.CommonResp;
import com.gxy.hairorder.resp.HairResp;
import com.gxy.hairorder.resp.PageResp;
import com.gxy.hairorder.service.HairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/11 14:51
 * @Classname HairController
 * @Description TODO
 */
@RestController
@RequestMapping("/hair")
public class HairController {

    @Autowired
    private HairService hairService;
//    @GetMapping("/wxAll")
//    public CommonResp hairList(){
//        CommonResp resp=new CommonResp();
//        List<HairResp> respList = hairService.wxAll();
//        resp.setContent(respList);
//        return resp;
//    }
    @GetMapping("/list")
    public CommonResp hairPage(HairReq hairReq){
        CommonResp resp=new CommonResp();
        PageResp<HairResp> pageResp = hairService.list(hairReq);
        resp.setContent(pageResp);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp hairSave(@Valid @RequestBody HairForm hairForm){
        CommonResp resp=new CommonResp();
        resp.setMessage("新增或修改理项目类型");
        hairService.save(hairForm);
        return resp;
    }
    @DeleteMapping("/delete/{hairId}")
    public CommonResp hairDel(@PathVariable Long hairId){
        CommonResp resp=new CommonResp();
        resp.setMessage("删除理发师类型");
        hairService.del(hairId);
        return resp;
    }
    //按id查询
    @GetMapping("/find/{hairId}")
    public CommonResp findHairId(@PathVariable Long hairId){
        CommonResp resp=new CommonResp();
        HairResp hairResp = hairService.findById(hairId);
        if(!ObjectUtils.isEmpty(hairResp)){
            resp.setContent(hairResp);
            resp.setMessage("查询成功");
            return resp;
        }else {
            resp.setMessage("未查询到数据,请检查请求数据");
            resp.setSuccess(false);
            return resp;
        }
    }
    //按类型查询
    @GetMapping("/byHairType/{hairTypeId}")
    public CommonResp hairListByHairType(@PathVariable Long hairTypeId){
        CommonResp resp=new CommonResp();
       List<HairResp>  hairRespList = hairService.hairListByHairType(hairTypeId);
       resp.setContent(hairRespList);
       resp.setMessage("查询成功");
       return resp;
    }

    //按类型查询
    @GetMapping("/byBarberType/{barberTypeId}")
    public CommonResp hairListByBarberType(@PathVariable Long barberTypeId){
        CommonResp resp=new CommonResp();
        List<HairResp>  hairRespList = hairService.hairListByBarberType(barberTypeId);
        resp.setContent(hairRespList);
        resp.setMessage("查询成功");
        return resp;
    }
//    //按首页查询
//    @GetMapping("/index")
//    public CommonResp index(){
//        CommonResp resp=new CommonResp();
//        List<HairResp>  hairRespList = hairService.index();
//        resp.setContent(hairRespList);
//        resp.setMessage("查询成功");
//        return resp;
//    }
}
