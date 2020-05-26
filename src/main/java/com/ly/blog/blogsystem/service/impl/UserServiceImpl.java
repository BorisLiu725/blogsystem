package com.ly.blog.blogsystem.service.impl;

import com.ly.blog.blogsystem.bean.User;
import com.ly.blog.blogsystem.dto.RegisterDTO;
import com.ly.blog.blogsystem.enumeration.UserCheckEnum;
import com.ly.blog.blogsystem.exception.UserException;
import com.ly.blog.blogsystem.mapper.UserMapper;
import com.ly.blog.blogsystem.service.UserService;
import com.ly.blog.blogsystem.utils.UuidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by BorisLiu on 2019/9/26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User findByUsernameAndPassword(String userName, String password) {
        return userMapper.findByUsernameAndPassword(userName,password);
    }

    @Override
    public void registerUser(RegisterDTO registerDTO) {
        String userId = UuidUtils.getUUid();
        User user = new User(userId,registerDTO.getUserName(),registerDTO.getPassword(),registerDTO.getEmail(),null,null,null,null,null);
        if (!check(user)){
            throw new UserException("用户名或邮箱重复！");
        }
        user.setCreateTime(new Date());
        user.setUpdateTime(user.getCreateTime());
        logger.info("插入数据:"+user);
        userMapper.insert(user);
    }

    /**
     * 根据类型检查是否出现重复
     * */
    @Override
    public boolean check(UserCheckEnum userCheckEnum,Object target) {
        User user = null;
        if (UserCheckEnum.USER_NAME.equals(userCheckEnum)){
            user =  userMapper.findByName(target);
        }else if (UserCheckEnum.EMAIL.equals(userCheckEnum)){
            user = userMapper.findByEmail(target);
        }
        logger.info(userCheckEnum+"==>"+target+"==>check==>user:"+user);
        // 说明没有重复，校验通过
        if (Objects.isNull(user)){
            return true;
        }
        return false;
    }

    @Override
    public User findByToken(String userId) {
        return userMapper.findById(userId);
    }

    @Override
    public User findById(String userId) {
        return userMapper.findById(userId);
    }

    @Override
    public boolean updateUserById(User user) {
       Integer res =  userMapper.updateUserById(user);
        return res > 0;
    }

    @Override
    public boolean updateUserStateById(String userId, Integer state) {
        Integer res = userMapper.updateUserStateById(userId, state);
        return res > 0;
    }

    @Override
    public boolean updatePasswordById(String userId, String oldPassword,String newPassword) {
        User user = userMapper.findById(userId);
        if (Objects.isNull(user)){
            return false;
        }

        if (!oldPassword.equals(user.getPassword())){
            return false;
        }

        Integer res = userMapper.updatePasswordById(userId, newPassword);

        return res > 0;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }


    public boolean check(User user) {
        User result = userMapper.findByUserNameAndEmail(user);
        return Objects.isNull(result);
    }




//    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void addUser(RegisterDTO registerDTO){

    }




}
