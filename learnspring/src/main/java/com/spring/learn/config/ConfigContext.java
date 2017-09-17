package com.spring.learn.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Administrator on 2017/9/13.
 */
@Configuration
public class ConfigContext {
    public ConfigContext(){
        System.out.println("spring容器 ConfigContext 启动初始化。。。");
    }

    @Bean(name="testBean")
    @Scope("prototype")
    TestBean testBean(){
        return new TestBean();
    }
}
