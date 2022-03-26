package com.gxy.hairorder.service;


import com.gxy.hairorder.entity.User;
import com.gxy.hairorder.exception.BusinessException;
import com.gxy.hairorder.exception.BusinessExceptionCode;
import com.gxy.hairorder.form.UserForm;
import com.gxy.hairorder.repository.UserRepository;
import com.gxy.hairorder.req.LoginReq;
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
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
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
@Transactional
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

    public UserResp login(LoginReq loginReq){
        User user = userRepository.findByPhoneAndPassword(loginReq.getPhone(), loginReq.getPassword());
        if (ObjectUtils.isEmpty(user)){
            return null;
        }else {
            UserResp userResp = CopyUtil.copy(user, UserResp.class);
            return userResp;
        }

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

    public void save(UserForm userForm) {

        User user = CopyUtil.copy(userForm, User.class);
        user.setCreateTime(new Date(System.currentTimeMillis()));
        if (ObjectUtils.isEmpty(userForm.getId())){
            user.setId(snowFlake.nextId());
            userRepository.save(user);
        }else {
            User repository = userRepository.findByPhone(userForm.getPhone());
            //解决不更新字段时不被设置为null;
            if (repository==null){
                throw new BusinessException(BusinessExceptionCode.USER_ERROR);
            }
            if (ObjectUtils.isEmpty(userForm.getPassword())){
                user.setPassword(repository.getPassword());
            }else {
            String password = DigestUtils.md5DigestAsHex(userForm.getPassword().getBytes());
            user.setPassword(password);
            }
            CopyUtil.copyNullProperties(repository,user);
            userRepository.save(user);
        }

    }

    public void userDel(Long userId) {
        userRepository.deleteById(userId);
    }
}
