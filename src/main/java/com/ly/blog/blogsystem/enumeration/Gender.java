package com.ly.blog.blogsystem.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

/**
 * Created by BorisLiu on 2019/10/7
 */
public enum  Gender implements BaseEnum {
    MALE(1),FAMALE(2);

    private final int value;

    Gender(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        return value;
    }

    @JsonCreator
    public static Gender fromValue(int value){
        for (Gender gender:Gender.values()){
            if (gender.value == value){
                return gender;
            }
        }
        return null;
    }
}
