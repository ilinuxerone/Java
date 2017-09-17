package com.spring.learn.propertySource;

import com.spring.learn.config.TestAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/9/13.
 */
public class TestPropSource {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigUtils.class);
        SimpleDataSource dataSource = (SimpleDataSource)context.getBean("dataSource");
        dataSource.toString();

    }
}