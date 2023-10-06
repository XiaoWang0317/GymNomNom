package com.gymnomnom.gymnomnom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//response code, 1 success; 0 fail
    private String msg;  //response info
    private Object data; //returned data

    //cwrd success
    public static Result success(){
        return new Result(1,"success",null);
    }
    //searching success
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    //fail
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}
