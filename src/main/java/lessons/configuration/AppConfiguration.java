package lessons.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"lessons.services", "lessons.configuration.beanpostprocessor"})
public class AppConfiguration {
}
