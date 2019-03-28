package cn.com.flaginfo.platform.littleProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableAsync
@EnableScheduling
@ComponentScan("cn.com.flaginfo.platform")
@SpringBootApplication
@EnableAutoConfiguration
@ImportResource(locations = { "classpath*:spring/*.xml"})
public class LittleApplication {

    public static void main(String[] args){
        SpringApplication springApplication = new SpringApplication(LittleApplication.class);
        springApplication.run(args);
    }
}


