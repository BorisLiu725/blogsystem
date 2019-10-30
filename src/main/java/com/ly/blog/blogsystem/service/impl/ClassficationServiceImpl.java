package com.ly.blog.blogsystem.service.impl;

import com.ly.blog.blogsystem.bean.Classfication;
import com.ly.blog.blogsystem.dto.PageResult;
import com.ly.blog.blogsystem.mapper.ClassficationMapper;
import com.ly.blog.blogsystem.service.ClassficationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BorisLiu on 2019/10/6
 */
@Service
public class ClassficationServiceImpl implements ClassficationService {

    @Autowired
    private ClassficationMapper classficationMapper;

    @Override
    public void createClassfication(Classfication classfication) {
        classficationMapper.insert(classfication);
    }

    @Override
    public void updateClassfication(Classfication classfication) {
        classficationMapper.update(classfication);
    }

    @Override
    public void deleteClassfication(Integer classficationId) {
        classficationMapper.delete(classficationId);
    }

    @Override
    public List<Classfication> findAll() {
        List<Classfication> classficationList =  classficationMapper.findAll();
        return classficationList;
    }
}
