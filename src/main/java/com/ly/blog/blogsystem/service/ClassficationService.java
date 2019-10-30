package com.ly.blog.blogsystem.service;

import com.ly.blog.blogsystem.bean.Classfication;
import com.ly.blog.blogsystem.dto.PageResult;

import java.util.List;

/**
 * Created by BorisLiu on 2019/10/6
 */

public interface ClassficationService {
    void createClassfication(Classfication classfication);

    void updateClassfication(Classfication classfication);

    void deleteClassfication(Integer classficationId);

    List<Classfication> findAll();
}
