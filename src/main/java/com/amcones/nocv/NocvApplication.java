package com.amcones.nocv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.amcones.nocv.dao")
@EnableScheduling
public class NocvApplication {

    public static void main(String[] args) {
        SpringApplication.run(NocvApplication.class, args);
    }

}
