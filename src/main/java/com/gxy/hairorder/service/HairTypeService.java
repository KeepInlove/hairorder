package com.gxy.hairorder.service;

import com.gxy.hairorder.entity.HairType;
import com.gxy.hairorder.exception.BusinessException;
import com.gxy.hairorder.exception.BusinessExceptionCode;
import com.gxy.hairorder.repository.HairRepository;
import com.gxy.hairorder.repository.HairTypeRepository;
import com.gxy.hairorder.req.HairTypeReq;
import com.gxy.hairorder.resp.HairTypeResp;
import com.gxy.hairorder.utils.CopyUtil;
import com.gxy.hairorder.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Date;
import java.util.List;

/**
 * @author GUO
 * @Classname HairTypeService
 * @Description TODO
 * @Date 2021/12/11 15:21
 */
@Slf4j
@Service
public class HairTypeService {
    @Autowired
    private HairTypeRepository hairTypeRepository;
    @Autowired
    private HairRepository hairRepository;
    @Autowired
    private SnowFlake snowFlake;

    public List<HairTypeResp> list(){
        List<HairType> typeList = hairTypeRepository.findAll();
        List<HairTypeResp> hairTypeList = CopyUtil.copyList(typeList, HairTypeResp.class);
        //查询不同类型理发师成员
        for (HairTypeResp typeResp:hairTypeList){
            int count = hairRepository.countByHairTypeId(typeResp.getHairTypeId());
            typeResp.setCount(count);
        }
        return hairTypeList;
    }
    /**
     * 新增/更新 理发师类型
     * @param typeReq
     */
    public void save(HairTypeReq typeReq){
        HairType hairType = CopyUtil.copy(typeReq, HairType.class);
        if (ObjectUtils.isEmpty(typeReq.getHairTypeId())){
            hairType.setHairTypeId(snowFlake.nextId());
            hairType.setCreateTime(new Date(System.currentTimeMillis()));
//            hairType.setCreateTime(DateUtil.DateToSimple());
            hairTypeRepository.save(hairType);
        }else {
            HairType repository = hairTypeRepository.findByHairTypeId(typeReq.getHairTypeId());
            //解决不更新字段时不被设置为null;
            if (repository==null){
                throw new BusinessException(BusinessExceptionCode.BARBER_TYPE_ERROR);
            }
            CopyUtil.copyNullProperties(repository,hairType);
            hairTypeRepository.save(hairType);
        }
    }
    public void del(Long hairTypeId){
        hairTypeRepository.deleteById(hairTypeId);
    }

}
