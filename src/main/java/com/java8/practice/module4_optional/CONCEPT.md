# Module 4: Java 8 Optional

## 1. Design Goal of `Optional`
`java.util.Optional<T>` was introduced in Java 8 as a container object which may or may not contain a non-null value. 

### Core Design Philosophy
The primary goal of `Optional` is to **provide a clear, type-level representation of an optional return value** to prevent `NullPointerException` (NPE) and encourage developers to explicitly handle the absence of a value rather than relying on `null` references.

> [!IMPORTANT]
> **What Optional is NOT:**
> * **Not a replacement for every null reference:** It is not designed to replace all nulls in Java.
> * **Not for parameters:** Passing `Optional` as a method parameter adds boilerplate to the caller (who must wrap values) and violates clean coding principles. Use method overloading or nullable parameters instead.
> * **Not for class fields / serialization:** `Optional` does not implement `java.io.Serializable`. Storing it in class fields will break standard Java serialization and increase the memory footprint of objects.
> * **Not for collection elements:** Never use `List<Optional<T>>` or `Map<Key, Optional<Value>>`. Instead, represent the absence of a value in collections by omitting the key/value or using an empty collection/null check at the boundary.

---

## 2. Creating Optionals
There are three static factory methods to instantiate an `Optional`:

* **`Optional.empty()`**: Returns an empty `Optional` instance.
* **`Optional.of(value)`**: Returns an `Optional` containing the specified non-null value. If the value is `null`, it immediately throws a `NullPointerException`.
* **`Optional.ofNullable(value)`**: Returns an `Optional` containing the value if non-null, otherwise returns an empty `Optional`.

```java
String name = getNullableName();

// Safe creation:
Optional<String> optName = Optional.ofNullable(name);

// Unsafe creation (will throw NPE if name is null):
Optional<String> optNameUnsafe = Optional.of(name);
```

---

## 3. Extracting and Handling Values

### `orElse` vs `orElseGet` (Lazy vs Eager)
These two methods provide a default value when the `Optional` is empty, but they evaluate their parameters differently.

| Method | Signature | Parameter Evaluation | Recommended Use Case |
| :--- | :--- | :--- | :--- |
| **`orElse(T other)`** | `public T orElse(T other)` | **Eager**: The parameter is evaluated *regardless* of whether the Optional contains a value or not. | Constant, pre-instantiated default values or primitives. |
| **`orElseGet(Supplier<? extends T> other)`** | `public T orElseGet(Supplier<? extends T> other)` | **Lazy**: The `Supplier` is executed *only* if the Optional is empty. | Database calls, heavy object instantiations, web service calls. |

#### Example of Evaluation Difference:
```java
// Suppose we have a method that logs and creates a default item:
public static String getDefault() {
    System.out.println("getDefault() invoked!");
    return "Default Value";
}

Optional<String> optional = Optional.of("Present Value");

// 1. orElse: getDefault() IS executed even though the Optional has a value!
String val1 = optional.orElse(getDefault()); 
// Prints: "getDefault() invoked!"

// 2. orElseGet: getDefault() is NOT executed because the Optional has a value.
String val2 = optional.orElseGet(() -> getDefault());
// Prints nothing.
```

### `orElseThrow`
Used when the absence of a value is an exceptional condition. In Java 8, it takes a supplier generating the exception:
```java
User user = optUser.orElseThrow(() -> new UserNotFoundException("User not found"));
```

---

## 4. Transforming and Filtering Optionals

### `map` vs `flatMap`
Both methods are used to transform values inside an `Optional`, but they handle functions returning other `Optional` instances differently.

#### `map`
If the mapper function returns a raw value `U`, `map` automatically wraps the result into an `Optional<U>`.
* **Signature**: `public <U> Optional<U> map(Function<? super T, ? extends U> mapper)`
```java
Optional<User> user = getUser();
Optional<String> username = user.map(User::getName); // Wraps String into Optional<String>
```

#### `flatMap`
If the mapper function itself returns an `Optional<U>`, using `map` would result in a nested `Optional<Optional<U>>`. `flatMap` flattens the result into a single `Optional<U>`.
* **Signature**: `public <U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper)`
```java
public class User {
    public Optional<Address> getAddress() { ... }
}

Optional<User> user = getUser();
// Use flatMap to avoid Optional<Optional<Address>>
Optional<Address> address = user.flatMap(User::getAddress); 
```

### `filter`
Allows you to conditionally keep or discard the value inside the `Optional`. If the value matches the predicate, it is retained; otherwise, an empty `Optional` is returned.
```java
Optional<User> activeUser = optUser.filter(User::isActive);
```

---

## 5. Senior-Level Interview Tips (5+ YOE)

### Q1: What is the overhead of using `Optional`? When should we avoid it for performance-critical code?
`Optional` is a reference type. Wrapping a value in an `Optional` creates an extra object allocation on the heap, adding memory footprint and garbage collection pressure.
* **Avoid in tight loops or high-frequency methods**: If a method is called millions of times per second, returning `Optional` can significantly degrade performance. Use `null` or sentinel values instead, and document the behavior.
* **Avoid wrapping primitives**: Use specialized primitive optional classes (`OptionalInt`, `OptionalLong`, `OptionalDouble`) instead of `Optional<Integer>`, etc., to avoid the dual overhead of wrapper class allocation and boxing.

### Q2: Why shouldn't `Optional` be used in Domain Model Fields?
1. **Serialization**: `Optional` does not implement `Serializable`. If your domain models are sent over RMI, saved via some legacy caching engines, or serialized directly, it will throw `NotSerializableException`.
2. **JPA / Hibernate Mapping**: Hibernate does not naturally map `Optional` fields to database columns out-of-the-box without custom AttributeConverters, making code unnecessarily complex.
* **Alternative pattern**: Define the class field as nullable, and expose the getter returning an `Optional`:
```java
public class Employee {
    private String middleName; // Nullable database field

    public Optional<String> getMiddleName() {
        return Optional.ofNullable(middleName); // Safe Optional API exposed to clients
    }
}
```

### Q3: What is the `Optional` API anti-pattern "Coding style: Imperative in Functional wrapper"?
A common anti-pattern is using `Optional` like a structured null check using `isPresent()` and `get()`:
```java
// Anti-pattern (essentially identical to if-null-check):
Optional<User> user = findUser();
if (user.isPresent()) {
    System.out.println(user.get().getName());
}

// Recommended Functional Approach:
findUser().map(User::getName)
          .ifPresent(System.out::println);
```
Avoid calling `get()` without first calling `isPresent()`. In Java 8, `orElseThrow(Supplier)` is preferred over `get()` to make the potential failure explicit and prevent standard NoSuchElementException without a useful message.
