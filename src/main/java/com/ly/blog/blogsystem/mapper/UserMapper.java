package com.ly.blog.blogsystem.mapper;

import com.ly.blog.blogsystem.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by BorisLiu on 2019/9/11
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where user_name=#{username} and password=#{password}")
    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


    public void insert(User user);

    User findByName(@Param("target")Object target);

    User findByEmail(@Param("target")Object target);

    User findByUserNameAndEmail(User user);

    User findById(@Param("userId") String userId);

    String findNameById(@Param("userId")String userId);

    Integer updateUserById(User user);

    Integer updateUserStateById(@Param("userId") String userId,@Param("state")Integer state);

    Integer updatePasswordById(@Param("userId") String userId,@Param("password")String password);

    @Select("select * from user")
    List<User> findAll();
}
