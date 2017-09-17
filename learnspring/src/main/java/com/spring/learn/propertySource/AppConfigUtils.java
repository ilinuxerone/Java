package com.spring.learn.propertySource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by Administrator on 2017/9/13.
 */
@Configuration
@PropertySource(value = "classpath:conf/*.properties", ignoreResourceNotFound = true)
public class AppConfigUtils {
    public  AppConfigUtils(){
        System.out.println("context start: AppConfigUtils");
    }

    @Autowired
    public Environment env;

    @Bean(name="dataSource")
    public SimpleDataSource dataSource(){
        SimpleDataSource proxoolDataSource = new SimpleDataSource();
        proxoolDataSource.setDriver(env.getProperty("testdriver"));
        proxoolDataSource.setDriverUrl(env.getProperty("testdriverUrl"));
        proxoolDataSource.setUser(env.getProperty("testuserName"));
        proxoolDataSource.setPassword(env.getProperty("testpassword"));
        System.out.println(proxoolDataSource.toString());
        return proxoolDataSource;
    }
}
