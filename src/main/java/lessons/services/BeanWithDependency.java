package lessons.services;

public class BeanWithDependency {

    private final GreetingService greetingService;

    public BeanWithDependency(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
}
