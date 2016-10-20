package ru.sbt.cacheproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static java.lang.ClassLoader.getSystemClassLoader;

public class CacheProxy implements InvocationHandler {
    private final Map<String, Object> resultByArg = new HashMap<>();
    private final Object delegate;

    public static <T> T cache(Object delegate) {
        return (T) Proxy.newProxyInstance(getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CacheProxy(delegate)
        );
    }

    public CacheProxy(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(CacheAnnotation.class)) {
            return invoke(method, args);
        }

        String key = key(method, args);

        if (!resultByArg.containsKey(key)) {
            Object result = getResult(method, args);
            resultByArg.put(key, result);
        }

        return resultByArg.get(key);
    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(delegate, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible");
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private Object getResult(Method method, Object[] args) throws Throwable {
        Object result = invoke(method, args);

        return result;
    }

    private String key(Method method, Object[] args) {
        StringBuilder builder = new StringBuilder();

        builder.append(method.getName());

        for (Object arg : args) {
            builder.append("-" + arg);
        }

        return builder.toString();
    }
}
