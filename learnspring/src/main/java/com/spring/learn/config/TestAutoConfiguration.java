package com.spring.learn.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/9/13.
 */
@Configuration
@ComponentScan(basePackages = "com.spring.learn.config")
public class TestAutoConfiguration {
    public TestAutoConfiguration(){
        System.out.println("spring容器 TestAutoConfiguration 启动初始化。。。");
    }
}
