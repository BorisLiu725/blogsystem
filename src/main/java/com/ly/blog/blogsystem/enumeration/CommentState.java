package com.ly.blog.blogsystem.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by BorisLiu on 2019/10/7
 */
public enum  CommentState implements BaseEnum{

    PASS(1),NO_PASS(2);

    private final int value;

    CommentState(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        return value;
    }


    /**
     * json的时候会调用我们的转换工厂
     * */
    @JsonCreator
    public static CommentState fromValue(int value){
        for (CommentState state : values()){
            if (state.value() == value){
                return state;
            }
        }
        return null;
    }

}
