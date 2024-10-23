package edu.t1;

import edu.t1.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public class TestRunner {

    public static void runTests(Class<?> testClass) throws Exception {
        Object testClassInstance = testClass.getDeclaredConstructor().newInstance();

        Optional<Method> beforeSuite = findSingleAnnotatedMethod(testClass, BeforeSuite.class);
        beforeSuite.ifPresent(TestRunner::checkMethodIsStatic);

        Optional<Method> afterSuite = findSingleAnnotatedMethod(testClass, AfterSuite.class);
        afterSuite.ifPresent(TestRunner::checkMethodIsStatic);

        Optional<Method> beforeEachTest = findSingleAnnotatedMethod(testClass, BeforeTest.class);
        Optional<Method> afterEachTest = findSingleAnnotatedMethod(testClass, AfterTest.class);

        List<Method> testMethods = getTestMethodsSortedByPriority(testClassInstance);

        invokeIfPresent(beforeSuite, testClassInstance);

        for (Method testMethod : testMethods) {
            invokeIfPresent(beforeEachTest, testClassInstance);
            invokeTestMethod(testMethod, testClassInstance);
            invokeIfPresent(afterEachTest, testClassInstance);
        }

        invokeIfPresent(afterSuite, testClassInstance);
    }

    private static void invokeIfPresent(Optional<Method> method, Object instance)
            throws IllegalAccessException, InvocationTargetException {
        if (method.isPresent()) {
            invokeTestMethod(method.get(), instance);
        }
    }

    private static void invokeTestMethod(Method method, Object instance) throws IllegalAccessException, InvocationTargetException {
        Object targetInstance = isNull(instance) && Modifier.isStatic(method.getModifiers()) ? null : instance;

        if (method.isAnnotationPresent(CsvSource.class)) {
            CsvSource csvSource = method.getAnnotation(CsvSource.class);
            Object[] parameters = Arrays.stream(csvSource.value().split(","))
                    .map(String::trim)
                    .map(TestRunner::convertStringToParameter)
                    .toArray();
            method.invoke(targetInstance, parameters);
        } else {
            method.invoke(targetInstance);
        }
    }

    private static Object convertStringToParameter(String value) {
        if (value.matches("\\d+")) {
            return Integer.parseInt(value);
        }
        if ("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value)) {
            return Boolean.parseBoolean(value);
        }
        return value;
    }

    private static List<Method> getTestMethodsSortedByPriority(Object testClassInstance) {
        Class<?> clazz = testClassInstance.getClass();

        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .sorted(Comparator.comparingInt((Method m) -> m.getAnnotation(Test.class).priority()).reversed())
                .toList();
    }

    private static Optional<Method> findSingleAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotation) {
        List<Method> annotatedMethods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .toList();

        if (annotatedMethods.size() > 1) {
            throw new IllegalArgumentException("Аннотация %s использована больше чем один раз"
                    .formatted(annotation.getSimpleName()));
        }
        return annotatedMethods.isEmpty() ? Optional.empty() : Optional.of(annotatedMethods.getFirst());
    }

    private static void checkMethodIsStatic(Method method) {
        requireNonNull(method);
        if (!Modifier.isStatic(method.getModifiers())) {
            throw new IllegalArgumentException("Метод %s должен быть статичным"
                    .formatted(method.getName()));
        }
    }
}