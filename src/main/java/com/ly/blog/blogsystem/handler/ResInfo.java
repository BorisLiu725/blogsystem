package com.ly.blog.blogsystem.handler;


import com.ly.blog.blogsystem.common.ResponseCodeConstant;
import org.springframework.http.HttpStatus;

/**
 * Created by BorisLiu on 2019/9/11
 */

public class ResInfo<T> {
   private int code;
   private String msg;
   private T result;


   public ResInfo() {
   }

   public ResInfo(int code, String msg){
      this.code =  code;
      this.msg = msg;
   }

   public int getCode() {
      return code;
   }

   public void setCode(int code) {
      this.code = code;
   }

   public String getMsg() {
      return msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public T getResult() {
      return result;
   }

   public void setResult(T result) {
      this.result = result;
   }

   /**
    * 无返回值，请求成功
    * */
   public static ResInfo<Void> ok(String message){
      ResInfo<Void> resInfo = new ResInfo<Void>();
      resInfo.setMsg(message);
      resInfo.setCode(ResponseCodeConstant.SERVICE_SUCCESS_CODE);
      return resInfo;
   }

   public static ResInfo<Void> ok(){
      String message = HttpStatus.OK.name();
      return ok(message);
   }

   /**
    * 返回结果集
    * */
   public static <T> ResInfo<T> result(T resultBody){
      ResInfo<T> resInfo = new ResInfo<>();
      resInfo.setResult(resultBody);
      resInfo.setMsg(HttpStatus.OK.name());
      resInfo.setCode(HttpStatus.OK.value());
      return resInfo;
   }

   /**
    * 参数错误
    * */
    public static ResInfo badRequest(){
      return badRequest(HttpStatus.BAD_REQUEST.name());
    }

   public static ResInfo badRequest(String message){
       ResInfo resInfo = new ResInfo();
       resInfo.setMsg(message);
       resInfo.setCode(HttpStatus.BAD_REQUEST.value());
       return resInfo;
   }

   /**
    * 服务器内部错误
    * */
   public static ResInfo innerServiceError(){
       return innerServiceError(HttpStatus.INTERNAL_SERVER_ERROR.name());
   }

   public static ResInfo innerServiceError(String message){
       ResInfo resInfo = new ResInfo();
       resInfo.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
       resInfo.setMsg(message);
       return resInfo;
   }

   /**
    * 自定义异常出错
    * */

   public static ResInfo myError(String message){
       ResInfo resInfo = new ResInfo();
       resInfo.setCode(ResponseCodeConstant.SERVICE_ERROR_CODE);
       resInfo.setMsg(message);
       return resInfo;
   }


}
