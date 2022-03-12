package com.gxy.hairorder.service;


import com.gxy.hairorder.entity.User;
import com.gxy.hairorder.repository.UserRepository;
import com.gxy.hairorder.req.UserReq;
import com.gxy.hairorder.resp.PageResp;
import com.gxy.hairorder.resp.UserResp;
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
 * @Classname UserServce
 * @Description TODO
 * @Date 2021/12/8 12:48
 */
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SnowFlake snowFlake;

    /**
     * 分页查询所有用户
     * @param req
     * @return
     */
    public PageResp<UserResp> list(UserReq req){
//        PageHelper.startPage(req.getPage(),req.getSize());
        //Page设置查询第几页,Size每页大小
        PageRequest pageRequest = PageRequest.of(req.getPage(), req.getSize());
        Page<User> userPage= userRepository.findAll(pageRequest);
//        PageInfo<User>pageInfo=new PageInfo<>(userList);
        log.info("总数:{}",userPage.getTotalElements());
        log.info("总页数:{}",userPage.getTotalPages());
        //拷贝工具类
        List<UserResp> respList = CopyUtil.copyList(userPage.getContent(), UserResp.class);
        PageResp<UserResp> pageResp = new PageResp<>();
        pageResp.setTotal(userPage.getTotalElements());
        pageResp.setList(respList);
        return pageResp;
    }

   public UserResp findByPhone(String phone){
       User user = userRepository.findByPhone(phone);
       if (ObjectUtils.isEmpty(user)){
          return null;
       }else {
           UserResp userResp = CopyUtil.copy(user, UserResp.class);
           return userResp;
       }
   }
   public void saveUser(String phone){
        User user=new User();
        user.setId(snowFlake.nextId());
        user.setPhone(phone);
        user.setIntegral(0);
        user.setSex("女");
        user.setCreateTime(new Date(System.currentTimeMillis()));
        user.setName(phone);
        userRepository.save(user);
   }
}
