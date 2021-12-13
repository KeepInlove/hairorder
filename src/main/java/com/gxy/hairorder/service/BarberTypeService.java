package com.gxy.hairorder.service;

import com.gxy.hairorder.entity.BarberType;
import com.gxy.hairorder.exception.BusinessException;
import com.gxy.hairorder.exception.BusinessExceptionCode;
import com.gxy.hairorder.repository.BarberTypeRepository;
import com.gxy.hairorder.req.BarberTypeReq;
import com.gxy.hairorder.resp.BarberTypeResp;
import com.gxy.hairorder.utils.CopyUtil;
import com.gxy.hairorder.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author GUO
 * @Classname BarberTypeService
 * @Description TODO
 * @Date 2021/12/11 15:21
 */
@Slf4j
@Service
public class BarberTypeService {
    @Autowired
    private BarberTypeRepository barberTypeRepository;
    @Autowired
    private SnowFlake snowFlake;

    public List<BarberTypeResp> list(){
        List<BarberType> typeList = barberTypeRepository.findAll();
        List<BarberTypeResp> barberTypeList = CopyUtil.copyList(typeList, BarberTypeResp.class);
        return barberTypeList;
    }
    /**
     * 新增/更新 理发师类型
     * @param typeReq
     */
    public void save(BarberTypeReq typeReq){
        BarberType barberType = CopyUtil.copy(typeReq, BarberType.class);
        if (ObjectUtils.isEmpty(typeReq.getBarberTypeId())){
            barberType.setBarberTypeId(snowFlake.nextId());
            barberType.setCreateTime(new Date());
            barberTypeRepository.save(barberType);
        }else {
            BarberType repository = barberTypeRepository.findByBarberTypeId(typeReq.getBarberTypeId());
            //解决不更新字段时不被设置为null;
            if (repository==null){
                throw new BusinessException(BusinessExceptionCode.BARBER_TYPE_ERROR);
            }
            CopyUtil.copyNullProperties(repository,barberType);
            barberTypeRepository.save(barberType);
        }
    }

}
