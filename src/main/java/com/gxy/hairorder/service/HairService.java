package com.gxy.hairorder.service;

import com.gxy.hairorder.entity.BarberType;
import com.gxy.hairorder.entity.Hair;
import com.gxy.hairorder.entity.HairType;
import com.gxy.hairorder.exception.BusinessException;
import com.gxy.hairorder.exception.BusinessExceptionCode;
import com.gxy.hairorder.form.HairForm;
import com.gxy.hairorder.repository.BarberTypeRepository;
import com.gxy.hairorder.repository.HairRepository;
import com.gxy.hairorder.repository.HairTypeRepository;
import com.gxy.hairorder.req.HairReq;
import com.gxy.hairorder.resp.HairResp;
import com.gxy.hairorder.resp.PageResp;
import com.gxy.hairorder.utils.CopyUtil;
import com.gxy.hairorder.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Date;
import java.util.List;

/**
 * @author GUO
 * @Classname HairsService
 * @Description TODO
 * @Date 2022/2/26 17:40
 */
@Slf4j
@Service
public class HairService {
    @Autowired
    private HairRepository hairRepository;
    @Autowired
    private HairTypeRepository hairTypeRepository;
    @Autowired
    private BarberTypeRepository barberTypeRepository;
    @Autowired
    private SnowFlake snowFlake;

    //微信小程序
//    public List<HairResp> wxAll(){
//        List<Hair> hairList = hairRepository.findAll();
//        //拷贝工具类
//        List<HairResp> respList = CopyUtil.copyList(hairList, HairResp.class);
//        for (HairResp hairResp:respList){
//            HairType hairType = hairTypeRepository.findByHairTypeId(hairResp.getHairTypeId());
//            BarberType barberType=barberTypeRepository.findByBarberTypeId(hairResp.getBarberTypeId());
//            hairResp.setBarberTypeName(barberType.getBarberTypeName());
//            hairResp.setHairTypeName(hairType.getHairTypeName());
//        }
//        Collections.reverse(respList);
//        return respList;
//    }

    public PageResp<HairResp> list(HairReq req){
//        PageHelper.startPage(req.getPage(),req.getSize());
        //Page设置查询第几页,Size每页大小
        PageRequest pageRequest = PageRequest.of(req.getPage(), req.getSize());
        Page<Hair> hairPage = hairRepository.findAll(pageRequest);
//        PageInfo<User>pageInfo=new PageInfo<>(userList);
        log.info("总数:{}",hairPage.getTotalElements());
        log.info("总页数:{}",hairPage.getTotalPages());
        //拷贝工具类
        List<HairResp> respList = CopyUtil.copyList(hairPage.getContent(), HairResp.class);
        for (HairResp hairResp:respList){
            HairType hairType = hairTypeRepository.findByHairTypeId(hairResp.getHairTypeId());
            BarberType barberType=barberTypeRepository.findByBarberTypeId(hairResp.getBarberTypeId());
            hairResp.setBarberTypeName(barberType.getBarberTypeName());
            hairResp.setHairTypeName(hairType.getHairTypeName());
        }
        PageResp<HairResp> pageResp = new PageResp<>();
        pageResp.setTotal(hairPage.getTotalElements());
        pageResp.setList(respList);
        return pageResp;
    }

    /**
     * 新增/更新 项目
     * @param hairForm
     */
    public void save(HairForm hairForm){
        Hair hair = CopyUtil.copy(hairForm, Hair.class);
        if (ObjectUtils.isEmpty(hairForm.getHairId())){
            hair.setHairId(snowFlake.nextId());
            hair.setCreateTime(new Date(System.currentTimeMillis()));
            hair.setOrderCount(0);
            hair.setHairStatus(true);
            hairRepository.save(hair);
        }else {
            Hair repository = hairRepository.findByHairId(hairForm.getHairId());
            //解决不更新字段时不被设置为null;
            if (repository==null){
                throw new BusinessException(BusinessExceptionCode.HAIR_ERROR);
            }
            CopyUtil.copyNullProperties(repository,hair);
            hairRepository.save(hair);
        }
    }

    public HairResp findById(Long hairId) {
        Hair hair = hairRepository.findByHairId(hairId);
        if (!ObjectUtils.isEmpty(hair)){
            HairType hairType = hairTypeRepository.findByHairTypeId(hair.getHairTypeId());
            BarberType barberType=barberTypeRepository.findByBarberTypeId(hair.getBarberTypeId());
            HairResp hairResp = CopyUtil.copy(hair, HairResp.class);
            hairResp.setHairTypeName(hairType.getHairTypeName());
            hairResp.setBarberTypeName(barberType.getBarberTypeName());
            return hairResp;
        }else {
        return null;
        }
    }

    public void del(Long hairId) {
        hairRepository.deleteById(hairId);
    }

    public List<HairResp> hairListByHairType(Long hairTypeId) {
        List<Hair> hairList = hairRepository.findByHairTypeId(hairTypeId);
        List<HairResp> hairRespList = CopyUtil.copyList(hairList, HairResp.class);
        for (HairResp hairResp:hairRespList){
            HairType hairType = hairTypeRepository.findByHairTypeId(hairResp.getHairTypeId());
            BarberType barberType=barberTypeRepository.findByBarberTypeId(hairResp.getBarberTypeId());
            hairResp.setBarberTypeName(barberType.getBarberTypeName());
            hairResp.setHairTypeName(hairType.getHairTypeName());
        }
        return hairRespList;
    }

//    public List<HairResp> index() {
//        List<HairType> typeList = hairTypeRepository.findAll();
//        HairType hairType = typeList.get(0);
//        List<HairResp> hairRespList = hairListByHairType(hairType.getHairTypeId());
//        return hairRespList;
//    }
}
