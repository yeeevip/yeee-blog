package vip.yeee.app.bootstrap;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableMethodCache(basePackages = "vip.yeee.app")
@EnableCreateCacheAnnotation
@SpringBootApplication
@MapperScan({"vip.yeee.app.sys.manage.domain.mysql.mapper"
        , "vip.yeee.app.blog.manage.domain.mysql.mapper"
        , "vip.yeee.app.blog.client.domain.mysql.mapper"})
@ComponentScan({"vip.yeee.app"})
public class YeeeBlogBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(YeeeBlogBootstrapApplication.class, args);
    }

}
