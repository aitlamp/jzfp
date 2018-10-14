package com.atlp.jzfp;

import com.atlp.jzfp.common.base.BaseRepositoryImpl;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Import(FdfsClientConfig.class) // 将 FastDFS 配置引入项目
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING) // 解决引入FastDFS后jmx重复注册bean的问题
@EnableTransactionManagement //开启注解事务管理
@ServletComponentScan
//自定义jpa实现类配置
@EnableJpaRepositories(basePackages = {"com.atlp.jzfp.repository"}, repositoryBaseClass = BaseRepositoryImpl.class)
public class JzfpApplication {
    public static void main(String[] args) {
        SpringApplication.run(JzfpApplication.class, args);
    }
}
