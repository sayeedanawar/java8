package com.java8.practice.module6_exceptions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * MODULE 6: Exception Handling in Java 8 Lambdas
 * 
 * Exercise instructions:
 * 1. Implement a wrapping functional interface/wrapper method that lets you execute code throwing checked exceptions
 *    inside a standard stream pipeline cleanly.
 * 2. Complete the tasks below without introducing compiler errors.
 */
public class ExceptionHandling {

    // Define a custom functional interface that allows throwing checked exceptions
    @FunctionalInterface
    public interface ThrowingConsumer<T, E extends Exception> {
        void accept(T t) throws E;
    }

    @FunctionalInterface
    public interface ThrowingFunction<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    /**
     * Task 1: Complete the wrapper method to convert a ThrowingConsumer into a standard Consumer.
     * It should catch any checked exception and rethrow it as a RuntimeException.
     */
    public static <T> Consumer<T> handleConsumerException(ThrowingConsumer<T, Exception> throwingConsumer) {
        // TODO: Implement wrapper
        return t -> {
            // Your solution here
        };
    }

    /**
     * Task 2: Complete the wrapper method to convert a ThrowingFunction into a standard Function.
     * It should catch any checked exception and rethrow it as a RuntimeException.
     */
    public static <T, R> Function<T, R> handleFunctionException(ThrowingFunction<T, R, Exception> throwingFunction) {
        // TODO: Implement wrapper
        return t -> {
            // Your solution here
            return null;
        };
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("40", "50", "invalid", "60");

        // Example Task 3: Use handleConsumerException to parse elements and print them.
        // Thread.sleep(millis) throws InterruptedException (a checked exception).
        // Wrap a sleep delay inside the consumer.
        List<String> classNames = Arrays.asList("java.lang.String", "java.lang.Integer", "non.existent.Class");

        System.out.println("--- Loading Classes using handleFunctionException wrapper ---");
        // TODO: Use the handleFunctionException wrapper inside the map operation to load classes dynamically with Class.forName(name)
        // List<Class<?>> loadedClasses = classNames.stream()
        //      .map( ... )
        //      .collect(Collectors.toList());
    }
}
