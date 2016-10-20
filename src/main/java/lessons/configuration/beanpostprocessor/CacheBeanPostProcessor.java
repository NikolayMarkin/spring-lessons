package lessons.configuration.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;
import ru.sbt.cacheproxy.CacheAnnotation;
import ru.sbt.cacheproxy.CacheProxy;

import java.lang.reflect.Method;

@Service
public class CacheBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Before " + beanName);

        Method[] declaredMethods = bean.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(CacheAnnotation.class)) {
                bean = CacheProxy.cache(bean);
                break;
            }
        }

        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("After " + beanName);
        return bean;
    }
}
