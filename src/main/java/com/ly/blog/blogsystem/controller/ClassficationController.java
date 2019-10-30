package com.ly.blog.blogsystem.controller;

import com.ly.blog.blogsystem.bean.Classfication;
import com.ly.blog.blogsystem.common.ResponseCodeConstant;
import com.ly.blog.blogsystem.handler.ResInfo;
import com.ly.blog.blogsystem.exception.ClassficationException;
import com.ly.blog.blogsystem.service.ClassficationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by BorisLiu on 2019/10/6
 */
@RequestMapping("/classfication")
@RestController
public class ClassficationController {

    @Autowired
    private ClassficationService classficationService;

    @ApiOperation(value = "查询所有分类列表")
    @GetMapping(value = "/find/list")
    public List<Classfication> findArticles(){
        return classficationService.findAll();
    }

    @ApiOperation(value = "添加分类")
    @ApiImplicitParam(name = "classfication",value = "文章分类信息",required = true)
    @PostMapping(value = "/publish")
    public void addClassfication(@RequestBody Classfication classfication){
        classficationService.createClassfication(classfication);
    }


    @ApiOperation(value = "修改分类")
    @ApiImplicitParam(name = "Classfication",value = "文章分类信息",required = true)
    @PutMapping(value = "/update")
    public void updateClassfication(@RequestBody Classfication classfication) {
        classficationService.updateClassfication(classfication);
    }

    @ApiOperation(value = "删除分类")
    @ApiImplicitParam(name = "classficationId",value = "分类id",required = true)
    @DeleteMapping(value = "/delete/{classficationId}")
    public void deleteClassfication(@PathVariable(value = "classficationId")Integer classficationId){
        classficationService.deleteClassfication(classficationId);
    }


}
