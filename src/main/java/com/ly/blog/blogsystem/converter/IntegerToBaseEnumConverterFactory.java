package com.ly.blog.blogsystem.converter;

import com.ly.blog.blogsystem.enumeration.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * Created by BorisLiu on 2019/10/8
 */
public class IntegerToBaseEnumConverterFactory implements ConverterFactory<Integer,BaseEnum> {


    @Override
    public <T extends BaseEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        return new IntegerToEnum<>(targetType);
    }

    private class IntegerToEnum<T extends  BaseEnum> implements Converter<Integer,T>{

        private final Class<T> enumType;

        IntegerToEnum(Class<T> enumType){
            this.enumType = enumType;
        }

        @Override
        public T convert(Integer integer) {
            return BaseEnum.fromValue(enumType,integer);
        }
    }


}
