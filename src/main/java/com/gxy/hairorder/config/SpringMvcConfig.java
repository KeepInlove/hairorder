package com.gxy.hairorder.config;

import com.gxy.hairorder.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author GUO
 * @Classname SpringMvcConfig
 * @Description TODO
 * @Date 2022/2/24 0:33
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
//    //注入拦截器
    @Autowired
    LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                //过滤不需要拦截的路径
                .excludePathPatterns(
                        "/login/sys"
                );
    }

}

