# Module 3: Advanced Streams

## 1. Grouping and Partitioning

Grouping and partitioning allow you to classify elements of a stream into categories, resulting in a `Map`. 

### `Collectors.groupingBy`
The `groupingBy` collector is used to group elements by a classifier function. There are three overloaded variants of `groupingBy`:

1. **One-parameter**: `groupingBy(classifier)`
   - Groups elements by the classifier. The map values are `List<T>`.
   - Example: `groupingBy(Employee::getDept)` -> `Map<String, List<Employee>>`
2. **Two-parameter**: `groupingBy(classifier, downstreamCollector)`
   - Groups elements by the classifier and processes the values of each group using a downstream collector.
   - Example: `groupingBy(Employee::getDept, Collectors.counting())` -> `Map<String, Long>`
3. **Three-parameter**: `groupingBy(classifier, mapFactory, downstreamCollector)`
   - Allows you to specify a custom `Map` implementation (e.g., `TreeMap::new` for sorted keys).
   - Example: `groupingBy(Employee::getDept, TreeMap::new, Collectors.toList())` -> `TreeMap<String, List<Employee>>`

### `Collectors.partitioningBy`
Partitioning is a special case of grouping where the classifier is a `Predicate`. The resulting map always contains two keys: `true` and `false`, with values representing elements that match or do not match the predicate.

1. **One-parameter**: `partitioningBy(predicate)` -> `Map<Boolean, List<T>>`
2. **Two-parameter**: `partitioningBy(predicate, downstreamCollector)` -> `Map<Boolean, D>`

---

## 2. Downstream Collectors

Downstream collectors are crucial for nesting and aggregating grouped data. Some of the most powerful downstream collectors include:

- **`mapping(mapper, downstream)`**: Transforms elements before passing them to the downstream collector.
  ```java
  Map<String, Set<String>> deptEmployeeNames = employees.stream()
      .collect(groupingBy(Employee::getDept, mapping(Employee::getName, toSet())));
  ```
- **`flatMapping(mapper, downstream)`**: Flattens nested collections before collecting them (Java 9+).
- **`filtering(predicate, downstream)`**: Filters elements within each group before collection (Java 9+).
- **`reducing(identity, mapper, op)`**: Performs a reduction on the elements of each group.
- **`maxBy(comparator)` / `minBy(comparator)`**: Finds the maximum or minimum element in each group. Returns a `Map<K, Optional<T>>`.
- **`collectingAndThen(downstream, finisher)`**: Adapts a collector to perform an additional finishing transformation.
  ```java
  // Find highest-paid employee in each department (without Optional wrapper)
  Map<String, Employee> topPaidInDept = employees.stream()
      .collect(groupingBy(
          Employee::getDept,
          collectingAndThen(maxBy(Comparator.comparingDouble(Employee::getSalary)), Optional::get)
      ));
  ```

---

## 3. Custom Collectors

When built-in collectors are insufficient, you can implement your own by implementing the `java.util.stream.Collector<T, A, R>` interface, which defines five functions:

1. **`Supplier<A> supplier()`**: Creates and returns a new mutable result container (e.g., `ArrayList::new`).
2. **`BiConsumer<A, T> accumulator()`**: Folds a new element into the mutable result container.
3. **`BinaryOperator<A> combiner()`**: Combines two partial results into one (used in parallel stream processing).
4. **`Function<A, R> finisher()`**: Transforms the intermediate container into the final result type. If `Characteristics.IDENTITY_FINISH` is set, this function can just be `Function.identity()`.
5. **`Set<Characteristics> characteristics()`**: Returns a set of characteristics specifying the collector's capabilities (e.g., `CONCURRENT`, `UNORDERED`, `IDENTITY_FINISH`).

### Example: Custom Collector for String Joins
```java
Collector<String, StringBuilder, String> commaJoiner = Collector.of(
    StringBuilder::new,                           // supplier
    (sb, s) -> { if (sb.length() > 0) sb.append(","); sb.append(s); }, // accumulator
    (sb1, sb2) -> {                               // combiner
        if (sb1.length() > 0 && sb2.length() > 0) sb1.append(",");
        return sb1.append(sb2);
    },
    StringBuilder::toString                       // finisher
);
```

---

## 4. Reducing: Stream.reduce vs Collectors.reducing

- **`Stream.reduce()`** is a terminal operation on a Stream. It is designed for immutable reductions (e.g., summing integers, combining strings).
- **`Collectors.reducing()`** is designed to be used as a downstream collector inside `groupingBy` or `partitioningBy`.
- **Mutable vs Immutable Reduction**: Do not use `reduce` for mutable operations like adding elements to an `ArrayList`. That is what `Stream.collect` (mutable reduction) is optimized for. Using `reduce` for mutable structures in parallel streams can lead to thread-safety issues or terrible performance because of copying.

---

## 5. Advanced Interview Patterns (5+ YOE)

### Q1: How do you achieve Dense Ranking in Java Streams?
Dense ranking means ranking items by a value where items with equal values receive the same rank, and the next rank is consecutive (e.g., salaries 100k (1), 100k (1), 90k (2)).
To implement this cleanly, you can:
1. Sort the unique values in descending order.
2. Store their rank in a map or look up the index in the sorted unique list.
3. Or group by value, sort the entry keys, and assign rank based on position.

### Q2: What is Stream Performance Optimization & Characteristics?
Streams utilize metadata flag characteristics defined in `Spliterator` (e.g., `ORDERED`, `DISTINCT`, `SORTED`, `SIZED`).
- If a stream knows it is `DISTINCT`, calling `distinct()` becomes a no-op.
- If a stream is `SORTED` and you call `sorted()`, it skips sorting.
- If a stream is `SIZED`, operations like `toArray` can allocate the exact size, avoiding resizing.

**Performance Tips**:
- Avoid **boxing/unboxing overhead**: Use `IntStream`, `LongStream`, and `DoubleStream` instead of `Stream<Integer>`, `Stream<Long>`, and `Stream<Double>`.
- **Filter early**: Place filter operations as close to the source as possible to reduce the number of elements processed downstream.
- **Limit stateful operations**: Operations like `distinct()`, `sorted()`, `limit()`, and `skip()` are stateful and require buffering all elements before producing output, which can hurt performance, especially in parallel streams.
- **Parallel stream overhead**: Only use parallel streams when:
  1. The data size is large (e.g., N > 10,000).
  2. The computation per element is heavy (high Q-value).
  3. The source splits easily (e.g., `ArrayList` is excellent, `LinkedList` or `BufferedReader.lines` splits poorly).
