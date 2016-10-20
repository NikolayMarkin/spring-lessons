package lessons.configuration;

import lessons.configuration.AnotherConfiguration;
import lessons.services.GreetingService;
import lessons.services.GreetingServiceImpl;
import org.springframework.context.annotation.*;

/**
 * Конфигурационный класс Spring IoC контейнера
 */
@Configuration
@ComponentScan
@Import(AnotherConfiguration.class)
public class LessonsConfiguration {

    @Bean
    @Description("Текстовое описание бина greetingService")
    GreetingService greetingService() {
        return new GreetingServiceImpl();
    }

}