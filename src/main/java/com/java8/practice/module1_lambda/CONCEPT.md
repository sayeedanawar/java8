# Module 1: Lambda Expressions and Functional Interfaces

## 1. Introduction to Lambda Expressions
A lambda expression in Java is a concise way to represent an anonymous function (a function without a name). It allows you to pass behavior as an argument to a method, treating code as data. This is the cornerstone of functional programming in Java.

Syntax of Lambda Expressions:
```java
(parameters) -> expression
// or
(parameters) -> { statements; }
```
Example:
```java
// Traditional Anonymous Class
Runnable r1 = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running...");
    }
};

// Lambda Expression equivalent
Runnable r2 = () -> System.out.println("Running...");
```

---

## 2. Functional Interfaces and `@FunctionalInterface`
A **Functional Interface** is an interface that contains exactly **one abstract method** (also known as the Single Abstract Method or SAM).
* It can have any number of default and static methods.
* The `@FunctionalInterface` annotation is informative but highly recommended. It instructs the compiler to generate an error if the interface does not satisfy the SAM contract.
* Methods inherited from `java.lang.Object` (like `toString()`, `equals(Object)`, etc.) do not count toward the single abstract method limit.

Example:
```java
@FunctionalInterface
public interface SimpleCalculator {
    int operate(int a, int b); // Single Abstract Method
    
    default void printInfo() {
        System.out.println("Calculator operational");
    }
    
    static boolean isNonZero(int a) {
        return a != 0;
    }
}
```

---

## 3. Built-in Functional Interfaces
Java 8 introduced the `java.util.function` package containing pre-defined functional interfaces. Here are the core categories:

| Interface | Method Signature | Input | Output | Primary Use Case |
| :--- | :--- | :--- | :--- | :--- |
| **`Predicate<T>`** | `boolean test(T t)` | Single Object `T` | `boolean` | Filtering, validation, checks |
| **`Consumer<T>`** | `void accept(T t)` | Single Object `T` | `void` | Side effects, printing, logging |
| **`Supplier<T>`** | `T get()` | None | Object `T` | Factory patterns, lazy generation |
| **`Function<T, R>`** | `R apply(T t)` | Single Object `T` | Object `R` | Transformation, mapping |
| **`BiPredicate<T, U>`** | `boolean test(T t, U u)` | Two Objects `T, U` | `boolean` | Comparison, two-input validation |
| **`BiConsumer<T, U>`** | `void accept(T t, U u)` | Two Objects `T, U` | `void` | Multi-parameter actions, Map iteration |
| **`BiFunction<T, U, R>`**| `R apply(T t, U u)` | Two Objects `T, U` | Object `R` | Math operations, combining two items |

### Primitive Variations
To prevent automatic box/unbox overhead, Java 8 provides specialized interfaces for primitives:
* Predicates: `IntPredicate`, `LongPredicate`, `DoublePredicate`
* Consumers: `IntConsumer`, `LongConsumer`, `DoubleConsumer`
* Functions: `IntFunction<R>`, `ToIntFunction<T>`, `IntToLongFunction`, etc.

---

## 4. Target Typing
Lambda expressions do not contain information about which functional interface they implement. Instead, their type is inferred from the context they are used in. This is called **Target Typing**.

The compiler checks:
1. The target type context (assignment, method arguments, return statements).
2. If the target type is indeed a functional interface.
3. If the lambda parameters and return type match the signature of the Single Abstract Method.

Example:
```java
// Same lambda expression resolved to different functional interfaces
Callable<String> c = () -> "Hello";
Supplier<String> s = () -> "Hello";
```

---

## 5. Scoping Rules and Variable Capture
Lambda expressions look like anonymous inner classes, but they have distinct differences in how they handle scope:

### Variable Capture / Effectively Final
A lambda expression can access local variables from its enclosing block. However, the captured variable must be **final** or **effectively final** (meaning its value is never modified after initialization).
* This restriction exists because lambda instances may run asynchronously or in another thread, and permitting changes would introduce thread-safety issues.
* Instance and static variables are NOT subject to this restriction.

### `this` Reference
* **Anonymous Class**: `this` refers to the anonymous class instance itself.
* **Lambda Expression**: `this` refers to the enclosing class instance where the lambda is defined. Lambdas do not introduce a new scope level.

```java
public class ScopeDemo {
    private String name = "Enclosing Class";
    
    public void execute() {
        Runnable rAnon = new Runnable() {
            private String name = "Anonymous Class";
            @Override
            public void run() {
                System.out.println(this.name); // Prints: "Anonymous Class"
            }
        };
        
        Runnable rLambda = () -> {
            // System.out.println(this.name); 
            // In lambda, 'this' refers to ScopeDemo. This prints: "Enclosing Class"
            System.out.println(this.name); 
        };
        
        rAnon.run();
        rLambda.run();
    }
}
```

---

## 6. Senior-Level Interview Tips (5+ YOE)

### Q1: How do lambdas compile under the hood? (Anonymous Classes vs invokedynamic)
Unlike anonymous inner classes, which generate a separate `.class` file (e.g., `MyClass$1.class`) and are instantiated via `new` (involving standard class loading and allocation costs), lambda expressions are compiled using the `invokedynamic` (indy) instruction (introduced in Java 7).
* At compile time, the compiler places the lambda body into a synthetic private static (or instance) method in the same class.
* At runtime, the first time the `invokedynamic` call site is hit, it bootstraps a recipe to link the lambda to a functional interface call site using `LambdaMetafactory.metafactory`.
* Subsequent calls bypass translation and run as fast as direct method calls, avoiding the overhead of creating dedicated classes and loading them.

### Q2: Can Lambda expressions cause memory leaks?
Yes. If a lambda captures an instance variable or calls an instance method, it implicitly captures the `this` reference of the enclosing class. If that lambda is stored or passed to a long-lived object (e.g., a static cache or global registry), the enclosing object cannot be garbage collected, leading to a memory leak. To avoid this, capture local variables or use static helper methods whenever possible.

### Q3: Why is there no `TripleFunction<T, U, V, R>` or custom functions in the JDK?
The JDK designers limited functional interfaces to 1 or 2 parameters for simplicity and readability. If a function requires three or more parameters, it usually indicates that the parameters should be grouped into a domain object or configuration record. If truly necessary, you can define your own `@FunctionalInterface` with three arguments:
```java
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
```
