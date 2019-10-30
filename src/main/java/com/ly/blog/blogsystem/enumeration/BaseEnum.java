package com.ly.blog.blogsystem.enumeration;


import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * Created by BorisLiu on 2019/9/27
 */

public interface BaseEnum {
    @JsonValue
    int value();

    static <T extends BaseEnum> T fromValue(Class<T> enumType,int value){
        for (T object:enumType.getEnumConstants()){
            if (Objects.equals(value,object.value())){
                return object;
            }
        }
        return null;
    }
}
