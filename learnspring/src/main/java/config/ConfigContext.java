package config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/9/13.
 */
@Configuration
public class ConfigContext {
    public ConfigContext(){
        System.out.println("spring容器启动初始化。。。");
    }
}
