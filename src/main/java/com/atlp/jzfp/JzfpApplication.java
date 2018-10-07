package com.atlp.jzfp;

import com.atlp.jzfp.common.base.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启注解事务管理
@ServletComponentScan
@EnableJpaRepositories(basePackages = {"com.atlp.jzfp.repository"}, repositoryBaseClass = BaseRepositoryImpl.class)
public class JzfpApplication {
    public static void main(String[] args) {
        SpringApplication.run(JzfpApplication.class, args);
    }
}
