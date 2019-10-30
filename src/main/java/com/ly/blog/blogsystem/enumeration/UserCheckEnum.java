package com.ly.blog.blogsystem.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by BorisLiu on 2019/10/7
 */
public enum  UserCheckEnum implements BaseEnum{

    USER_NAME(1),
    EMAIL(2);

    private final int type;

    UserCheckEnum(int type){
        this.type = type;
    }

    @Override
    public int value() {
        return type;
    }
    /**
     * 将传进来的int值转成对应的枚举类型
     * */
    @JsonCreator
    public static UserCheckEnum fromValue(int value){
        for (UserCheckEnum userCheckType : UserCheckEnum.values()){
            if (userCheckType.type == value){
                return userCheckType;
            }
        }
        return null;
    }

}
