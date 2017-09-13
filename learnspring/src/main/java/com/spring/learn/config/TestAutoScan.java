package com.spring.learn.config;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/9/13.
 */
//添加注册bean的注解
@Component("testAutoScan")
public class TestAutoScan {

    private  String username;

    private  String url;

    private  String password;

    public void sayHello(){
        System.out.println("TestBean sayHello...");
    }

    public String toString(){
        return "username:"+this.username+",url:"+this.url+",password:"+this.password;
    }
}