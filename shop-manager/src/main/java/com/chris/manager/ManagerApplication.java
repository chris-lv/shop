package com.chris.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName:ManagerApplication
 * Package:com.chris.manager
 * Description:
 *
 * @Date:2021/3/9 9:13
 * @Author:Chris
 * @E-mail:gzshs@vip.qq.com
 */
@SpringBootApplication
@MapperScan("com.chris.manager.mapper")
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }
}
