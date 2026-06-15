package com.javatechie.lambda.demo.src.module5_concurrency;


import com.javatechie.lambda.demo.src.models.Employee;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exercise: CompletableFuture asynchronous pipelines.
 * 
 * Objectives:
 * 1. Run asynchronous tasks using custom ExecutorService.
 * 2. Combine results from two independent futures using thenCombine.
 * 3. Handle task timeout in a Java 8 compliant way (using future.get(timeout, unit)).
 * 4. Apply exception recovery logic using exceptionally() or handle().
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        try {
            System.out.println("--- Task 1: Load Employees Async ---");
            CompletableFuture<List<Employee>> empFuture = loadEmployeesAsync(executor);
            if (empFuture != null) {
                empFuture.thenAccept(employees -> {
                    if (employees != null) {
                        System.out.println("Loaded " + employees.size() + " employees asynchronously.");
                    }
                }).join(); // join to wait for printout
            }

            System.out.println("\n--- Task 2: Combine and Transform ---");
            CompletableFuture<Integer> countFuture = combineAndTransform(executor);
            if (countFuture != null) {
                System.out.println("Combined total contacts count: " + countFuture.join());
            }

            System.out.println("\n--- Task 3: Handle Timeout ---");
            String timeoutResult = handleTimeout();
            System.out.println("Timeout task result: " + timeoutResult);

            System.out.println("\n--- Task 4: Exception Recovery ---");
            CompletableFuture<String> recoveryFuture = exceptionRecovery();
            if (recoveryFuture != null) {
                System.out.println("Exception recovery result: " + recoveryFuture.join());
            }

        } finally {
            executor.shutdown();
        }
    }

    /**
     * Task 1: Load employees asynchronously.
     * Use CompletableFuture.supplyAsync and pass a custom executor service.
     * 
     * @param executor the custom thread pool executor
     * @return CompletableFuture containing list of employees
     */
    public static CompletableFuture<List<Employee>> loadEmployeesAsync(ExecutorService executor) {
        // TODO: Implement loading employees from DataStore.getAllEmployees() asynchronously.
        // Return a CompletableFuture containing the employee list.
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Task 2: Fetch and combine two independent datasets.
     * 1. Get the list of all employee names asynchronously.
     * 2. Get the list of all customer names asynchronously.
     * 3. Combine both lists using thenCombine to produce a single count of total contacts (employees + customers).
     * 
     * @param executor the custom thread pool executor
     * @return CompletableFuture containing total count of contacts
     */
    public static CompletableFuture<Integer> combineAndTransform(ExecutorService executor) {
        // TODO:
        // Future A: supplyAsync to get employee names list: DataStore.getAllEmployees() mapped to Employee::getName
        // Future B: supplyAsync to get customer names list: DataStore.getAllCustomers() mapped to Customer::getName
        // Combine them using thenCombine, summing their sizes.
        return CompletableFuture.completedFuture(0);
    }

    /**
     * Task 3: Simulate a slow operation and handle timeout.
     * In Java 8, we handle timeout using future.get(timeout, unit) since .orTimeout() was introduced in Java 9.
     * Implement a method that executes a task taking 3 seconds, and attempts to get the result with a 1-second timeout.
     * 
     * @return string output indicating success or timeout recovery message
     */
    public static String handleTimeout() {
        // TODO:
        // 1. Create a CompletableFuture that sleeps for 3 seconds (simulating delay), then returns "Success".
        // 2. Try to retrieve the result using future.get(1, TimeUnit.SECONDS).
        // 3. Catch TimeoutException (and other exception types), print a message, and return "Fallback / Timeout occurred".
        return "Not implemented";
    }

    /**
     * Task 4: Exception Recovery.
     * Use CompletableFuture.supplyAsync to execute a failing task.
     * Recover from the failure using exceptionally() or handle(), returning a fallback message.
     * 
     * @return CompletableFuture containing the final message (either successful completion or recovered value)
     */
    public static CompletableFuture<String> exceptionRecovery() {
        // TODO:
        // 1. supplyAsync a task that throws a RuntimeException("Database error!").
        // 2. Chain exceptionally() to catch the error and return a fallback string: "Recovered: Default Data".
        return CompletableFuture.completedFuture(null);
    }
}
