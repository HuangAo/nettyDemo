package com.nettydemo;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author Real
 * @Data 2021/4/29 9:28
 */
@Log4j2
@SpringBootApplication
public class NettyDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(NettyDemoApplication.class, args);
    }
}
