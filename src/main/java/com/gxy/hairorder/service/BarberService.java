package com.gxy.hairorder.service;

import com.gxy.hairorder.entity.Barber;
import com.gxy.hairorder.entity.BarberType;
import com.gxy.hairorder.entity.Hair;
import com.gxy.hairorder.exception.BusinessException;
import com.gxy.hairorder.exception.BusinessExceptionCode;
import com.gxy.hairorder.form.BarberForm;
import com.gxy.hairorder.repository.BarberRepository;
import com.gxy.hairorder.repository.BarberTypeRepository;
import com.gxy.hairorder.repository.HairRepository;
import com.gxy.hairorder.req.BarberReq;
import com.gxy.hairorder.resp.BarberResp;
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
 * @Classname BarberService
 * @Description TODO
 * @Date 2022/2/26 17:40
 */
@Slf4j
@Service
public class BarberService {
    @Autowired
    private BarberRepository barberRepository;
    @Autowired
    private BarberTypeRepository barberTypeRepository;
    @Autowired
    private HairRepository hairRepository;
    @Autowired
    private SnowFlake snowFlake;
    public PageResp<BarberResp> list(BarberReq req){
//        PageHelper.startPage(req.getPage(),req.getSize());
        //Page设置查询第几页,Size每页大小
        PageRequest pageRequest = PageRequest.of(req.getPage(), req.getSize());
        Page<Barber> barberPage = barberRepository.findAll(pageRequest);
//        PageInfo<User>pageInfo=new PageInfo<>(userList);
        log.info("总数:{}",barberPage.getTotalElements());
        log.info("总页数:{}",barberPage.getTotalPages());
        //拷贝工具类
        List<BarberResp> respList = CopyUtil.copyList(barberPage.getContent(), BarberResp.class);
        for (BarberResp barberResp:respList){
            BarberType barberType = barberTypeRepository.findByBarberTypeId(barberResp.getBarberTypeId());
            barberResp.setBarberTypeName(barberType.getBarberTypeName());
        }

        PageResp<BarberResp> pageResp = new PageResp<>();
        pageResp.setTotal(barberPage.getTotalElements());
        pageResp.setList(respList);
        return pageResp;
    }

    /**
     * 新增/更新 理发师
     * @param barberForm
     */
    public void save(BarberForm barberForm){
        Barber barber = CopyUtil.copy(barberForm, Barber.class);
        if (ObjectUtils.isEmpty(barberForm.getBarberId())){
            //新增
            barber.setBarberId(snowFlake.nextId());
            barber.setCreateTime(new Date(System.currentTimeMillis()));
            barber.setOrderCount(0);
            barber.setBarberStatus(true);
            barberRepository.save(barber);
        }else {
            //修改
            Barber repository = barberRepository.findByBarberId(barberForm.getBarberId());
            //解决不更新字段时不被设置为null;
            if (repository==null){
                throw new BusinessException(BusinessExceptionCode.BARBER_ERROR);
            }
            CopyUtil.copyNullProperties(repository,barber);
            barberRepository.save(barber);
        }
    }

    public BarberResp findById(Long barberId) {
        Barber barber = barberRepository.findByBarberId(barberId);
        if (!ObjectUtils.isEmpty(barber)){
            BarberType barberType = barberTypeRepository.findByBarberTypeId(barber.getBarberTypeId());
            BarberResp barberResp = CopyUtil.copy(barber, BarberResp.class);
            barberResp.setBarberTypeName(barberType.getBarberTypeName());
            return barberResp;
        }
        return null;
    }
    public List<Barber> findTypeId(Long barberTypeId) {
        List <Barber> barberList=   barberRepository.findByBarberTypeId(barberTypeId);
        return barberList;
    }

    public void del(Long barberId) {
        barberRepository.deleteById(barberId);
    }

    public List<Barber> findHairId(Long hairId) {
        Hair hair = hairRepository.findByHairId(hairId);
        Long barberTypeId = hair.getBarberTypeId();
        List<Barber> barberList = findTypeId(barberTypeId);
        return barberList;
    }

//    public List<Barber> all() {
//        List<Barber> barberList = barberRepository.findAll();
//        return barberList;
//    }
}
