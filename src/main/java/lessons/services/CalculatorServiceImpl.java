package lessons.services;

import org.springframework.stereotype.Service;
import ru.sbt.cacheproxy.CacheAnnotation;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @CacheAnnotation
    public int calc(int a, int b) {
        System.out.println("true invoke");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return a + b;
    }
}
