package lessons.starter;

import java.util.logging.Logger;

public class Starter {

    private static final Logger logger = Logger.getLogger(Starter.class);

    public static void main(String[] args) {
        logger.info("Starting configuration...");

        ApplicationContext context = new AnnotationConfigApplicationContext(LessonsConfiguration.class);
    }
}