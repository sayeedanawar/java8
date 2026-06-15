# Module 6: Exception Handling in Java 8

In Java 8, dealing with checked exceptions inside lambda expressions and Stream pipelines is a common pain point. This module covers the rules, patterns, and elegant wrapper functions to handle exceptions in a clean, functional style.

---

## 1. The Core Problem with Checked Exceptions in Lambdas

Java 8 functional interfaces (such as `Function`, `Consumer`, `Predicate`, etc.) do **not** declare any throws clauses in their abstract methods. For example, `java.util.function.Function` is defined as:
```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t); // Does NOT throw any checked exception
}
```

If you try to call a method that throws a checked exception (e.g., `Class.forName()`, `Thread.sleep()`, or database operations) inside a lambda that matches this interface, the compiler will complain:
```java
// Compilation Error: Unhandled exception type ClassNotFoundException
List<Class<?>> classes = classNames.stream()
    .map(name -> Class.forName(name)) 
    .collect(Collectors.toList());
```

---

## 2. Solution Patterns

### Pattern A: Standard Try-Catch Block (The Naive Approach)
The simplest solution is to wrap the body in a try-catch block.
```java
List<Class<?>> classes = classNames.stream()
    .map(name -> {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    })
    .collect(Collectors.toList());
```
*Downside*: This makes functional pipelines verbose, hard to read, and defeats the purpose of clean, declarative streams.

### Pattern B: Creating a Wrapper Utility (The Senior Developer Approach)
You can define a generic wrapper method that takes a functional interface throwing checked exceptions and converts it to a standard Java functional interface by catching the checked exception and rethrowing it as an unchecked exception (`RuntimeException`).

For example, define a functional interface that allows throwing checked exceptions:
```java
@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Exception> {
    R apply(T t) throws E;
}
```

Then write a generic wrapper static method:
```java
public static <T, R> Function<T, R> handleException(ThrowingFunction<T, R, Exception> throwingFunction) {
    return val -> {
        try {
            return throwingFunction.apply(val);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };
}
```

This lets you write clean stream pipelines:
```java
List<Class<?>> classes = classNames.stream()
    .map(handleException(Class::forName))
    .collect(Collectors.toList());
```

---

## 3. 5+ YOE Interview Focus Points

*   **SneakyThrows**: How does Lombok's `@SneakyThrows` work? It uses bytecode manipulation to deceive the compiler into throwing checked exceptions without declaring them in the method signature, bypassing the Java checked exception rules.
*   **Custom Functional Interfaces**: When designing custom API libraries, prefer declaring functional interfaces with generic throws bounds (e.g., `<E extends Exception>`) if the library operations might throw checked exceptions.
*   **Performance of Wrapper Lambdas**: Every wrapping adds a small indirection layer. In highly throughput-sensitive batch processing pipelines, handling exceptions via try-catch inside a custom helper method might be slightly more efficient than creating multiple layers of generic function wrappers.
