package lessons.starter;

import lessons.configuration.AppConfiguration;
import lessons.configuration.LessonsConfiguration;
import lessons.services.CalculatorService;
import lessons.services.GreetingService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


public class Starter {

    private static final Logger logger = LogManager.getLogger(Starter.class);

    public static void main(String[] args) {
        logger.info("Starting configuration...");

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        GreetingService greetingService = context.getBean(GreetingService.class);
        logger.info(greetingService.sayGreeting());


        CalculatorService service = context.getBean(CalculatorService.class);

        System.out.println(service.calc(1, 2));

        System.out.println(service.calc(1, 2));
    }
}