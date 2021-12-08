package com.gxy.hairorder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
@Slf4j
@SpringBootApplication
public class HairorderApplication {
    public static void main(String[] args) {
        SpringApplication app=new SpringApplication(HairorderApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("数据提供启动成功！！地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
