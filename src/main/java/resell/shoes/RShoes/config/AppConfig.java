package resell.shoes.RShoes.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "resell.shoes.RShoes")
@MapperScan(basePackages = "resell.shoes.RShoes.repository")
public class AppConfig implements WebMvcConfigurer {


}
