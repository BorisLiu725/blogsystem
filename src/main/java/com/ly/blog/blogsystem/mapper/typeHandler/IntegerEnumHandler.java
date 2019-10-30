package com.ly.blog.blogsystem.mapper.typeHandler;

import com.ly.blog.blogsystem.enumeration.BaseEnum;
import com.ly.blog.blogsystem.enumeration.CommentState;
import com.ly.blog.blogsystem.enumeration.Gender;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BorisLiu on 2019/10/7
 */
/**
 * 数据库插入和查询时候自动类型转换
 * */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes({CommentState.class,Gender.class})
public class IntegerEnumHandler <E extends BaseEnum> extends BaseTypeHandler<E> {

    private Logger logger = LoggerFactory.getLogger(IntegerEnumHandler.class);

    private Map<Integer,E> enumTypeMap = new HashMap<>();
    /**
     *
     * 一定要加这个无参构造器，不然错误报错报死你
     * */
    public IntegerEnumHandler(){

    }


    public IntegerEnumHandler(Class<E> type){
        logger.info("invoke construct method.  class<E>={} === Class<E> type={}",type.getEnumConstants(),type);
        if (type == null){
            throw new IllegalArgumentException("类型不能为null");
        }
        E[] enumConstants = type.getEnumConstants();
        if (enumConstants == null){
            throw new IllegalArgumentException("没有enum..");
        }
        for (E e:enumConstants){
            enumTypeMap.put(e.value(),e);
        }
    }


    /**
     * e :枚举类型
     * */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        logger.info("invoke==>setNonNullParameter:i={},e={},e.value={}",i,e,e.value());
        preparedStatement.setInt(i,e.value());
    }


    @Override
    public E getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        logger.info("invoke==>getNullableResult:columnName={}",columnName);
        return get(resultSet.getInt(columnName));
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        logger.info("invoke==>getNullableResult:columnIndex={}",columnIndex);
        return get(resultSet.getInt(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        logger.info("invoke==>getNullableResult:columnIndex={},callableStatement={}",columnIndex,callableStatement);
        return get(callableStatement.getInt(columnIndex));
    }
    private E get(Integer value){
        if (value == null){
            return null;
        }
        return enumTypeMap.get(value);
    }

}
