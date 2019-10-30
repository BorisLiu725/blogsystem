package com.ly.blog.blogsystem.mapper;

import com.ly.blog.blogsystem.bean.Classfication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by BorisLiu on 2019/10/6
 */
@Mapper
public interface ClassficationMapper {

    void insert(Classfication classfication);

    List<Classfication> findAll();

    void delete(@Param("id")Integer id);

    void update(Classfication classfication);


    Classfication findById(@Param("id")Integer id);
}
