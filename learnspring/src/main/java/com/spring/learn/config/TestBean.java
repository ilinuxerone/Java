package com.spring.learn.config;

/**
 * Created by Administrator on 2017/9/13.
 */
public class TestBean {
    private String username;

    private String password;

    private String url;

    public TestBean() {
    }

    public void sayHello(){
        System.out.println("TestBean sayHello...");
    }

    public String toString(){
        return "username:"+this.username+",url:"+this.url+",password:"+this.password;
    }

    public void start(){
        System.out.println("TestBean 初始化。。。");
    }

    public void cleanUp(){
        System.out.println("TestBean 销毁。。。");
    }
}
