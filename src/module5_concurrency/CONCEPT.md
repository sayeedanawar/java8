# Module 5: Concurrency - CompletableFuture and Parallel Streams

This module covers Java 8's powerful asynchronous programming capabilities using `CompletableFuture`, along with parallel streams and thread synchronization techniques.

---

## 1. Asynchronous Programming with CompletableFuture

Prior to Java 8, asynchronous programming relied on `java.util.concurrent.Future`. While `Future` represented the result of an asynchronous computation, it had severe limitations:
- **Blocking `get()`:** You could not obtain the result without blocking the calling thread until the result was available.
- **No Callback Support:** You could not attach callbacks to run automatically when the task completed.
- **No Chaining/Pipelines:** You could not easily chain multiple asynchronous tasks together (e.g., output of Task A becomes input of Task B).
- **No Manual Completion:** You could not manually complete a future.

`CompletableFuture<T>` implements both `Future<T>` and `CompletionStage<T>`. It allows you to build **non-blocking asynchronous pipelines**, registering callbacks that execute as stages complete.

### Core Creation Methods
*   **`CompletableFuture.supplyAsync(Supplier<U> supplier)`**: Runs a task asynchronously that returns a result. By default, it runs in the `ForkJoinPool.commonPool()`.
*   **`CompletableFuture.runAsync(Runnable runnable)`**: Runs a task asynchronously that does *not* return a result.

### Non-blocking Callbacks and Chaining
*   **`thenApply(Function<T, U> fn)`**: Transforms the result of the previous stage. Equivalent to map. It runs synchronously on the thread that completed the previous stage, or on the main thread if the previous stage was already complete.
*   **`thenAccept(Consumer<T> action)`**: Consumes the result of the previous stage without returning a new result (returns `CompletableFuture<Void>`).
*   **`thenRun(Runnable action)`**: Executes a task after the previous stage completes, ignoring its result.

### Chaining vs. Combining Futures
*   **`thenCompose(Function<T, CompletionStage<U>> fn)`**: Used to chain two dependent futures. If Task B depends on the result of Task A and itself returns a `CompletableFuture`, `thenCompose` flattens the nested futures (`CompletableFuture<CompletableFuture<U>>` becomes `CompletableFuture<U>`). This is equivalent to flatMap.
*   **`thenCombine(CompletionStage<U> other, BiFunction<T, U, V> fn)`**: Used to run two independent futures in parallel and combine their results when both complete.

---

## 2. Thread Pools and Executors

### Common ForkJoinPool vs. Custom ExecutorService
By default, the `*Async` methods without an explicit `Executor` parameter run their tasks in `ForkJoinPool.commonPool()`.
*   **ForkJoinPool.commonPool()**: 
    *   Designed for CPU-bound computations.
    *   The size of the pool defaults to `Runtime.getRuntime().availableProcessors() - 1`.
    *   **Anti-pattern:** Running blocking I/O tasks (database queries, external API calls) in the common pool. If the common pool threads get blocked, it starves other parts of the application (like parallel streams) that rely on the same common pool.
*   **Custom ExecutorService**:
    *   Always supply a custom executor (e.g., `ThreadPoolExecutor` or cached/fixed thread pool) for I/O-intensive tasks to keep application-wide CPU tasks unblocked.
    *   Usage: `CompletableFuture.supplyAsync(supplier, customExecutor)`.

---

## 3. Parallel Streams and ForkJoinPool

Parallel streams simplify parallel processing by dividing a stream into substreams and execution across multiple threads using a `ForkJoinPool`.

### How Parallel Streams Work
1.  **Splitting:** The stream is split into parts using a `Spliterator`.
2.  **Execution:** Substreams are processed by worker threads using the *work-stealing algorithm* inside `ForkJoinPool.commonPool()`.
3.  **Combining:** The results of the substreams are combined (e.g., using `reduce` or `collect`).

### Pitfalls of Parallel Streams
*   **Sharing the Common Pool:** As mentioned, all parallel streams in a JVM share the same `ForkJoinPool.commonPool()`. If one stream blocks on I/O, it can degrade the performance of all other parallel streams.
*   **Ordering Costs:** Operations like `limit()`, `skip()`, and `findFirst()` can be very expensive on parallel streams because they must preserve document/encounter order.
*   **Stateful/Side-effecting Lambdas:** Modifying shared state inside parallel stream operations leads to race conditions.
*   **Custom ForkJoinPool Trick:** Although not officially part of the standard Stream API design, you can run a parallel stream inside a custom `ForkJoinPool` to isolate it from the common pool:
    ```java
    ForkJoinPool customPool = new ForkJoinPool(4);
    customPool.submit(() -> list.parallelStream().map(x -> ...).collect(...)).get();
    ```

---

## 4. Coordination & Synchronization (Classic Wait/Notify)

For coordinate execution between threads (like an Even/Odd printing handshake), classic synchronization primitives are used:
*   **`synchronized` block:** Obtains a monitor lock on a shared object.
*   **`wait()`**: Tells the current thread to give up the monitor and go to sleep until another thread invokes `notify()` or `notifyAll()` on the same monitor. Must be called from a synchronized context.
*   **`notify()` / `notifyAll()`**: Wakes up one or all threads waiting on this object's monitor. Must be called from a synchronized context.
*   **Condition Loops:** Always invoke `wait()` inside a loop testing the condition being waited for:
    ```java
    synchronized (lock) {
        while (!condition) {
            lock.wait();
        }
        // perform action
        lock.notifyAll();
    }
    ```

---

## 5. Advanced Interview Cheat Sheet (5+ YoE)

1.  **What is the difference between `thenApply` and `thenApplyAsync`?**
    *   `thenApply` runs on the thread that completes the previous stage (or on the calling thread if already completed).
    *   `thenApplyAsync` always submits the callback task to an executor (the default `ForkJoinPool.commonPool()` or a custom one if supplied) to run on a separate thread.
2.  **How do you handle exceptions in CompletableFuture?**
    *   `exceptionally(Function<Throwable, T> fn)`: Recovers from an exception by providing a fallback value.
    *   `handle(BiFunction<T, Throwable, U> fn)`: Allows you to transform both the successful result or the exception.
    *   `whenComplete(BiConsumer<T, Throwable> action)`: Executes a callback upon completion without transforming the result.
3.  **Java 8 Timeout Handling Limitations:**
    *   Java 8 does *not* support `.orTimeout()` or `.completeOnTimeout()`. These were introduced in Java 9.
    *   In Java 8, timeout handling must be done by calling `future.get(timeout, unit)` which blocks, or by using a scheduled executor to manually complete the future exceptionally after a duration.
