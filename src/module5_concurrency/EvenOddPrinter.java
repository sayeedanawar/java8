package com.javatechie.lambda.demo.src.module5_concurrency;

/**
 * Exercise: Coordinate Even/Odd printing using two threads with synchronization/handshake logic.
 * 
 * Objectives:
 * 1. Implement synchronization between two threads using a shared monitor lock.
 * 2. Use wait() and notifyAll() to coordinate the handshake so that numbers are printed
 *    sequentially (e.g., 1, 2, 3, 4, ..., limit).
 * 3. One thread must print odd numbers, and the other thread must print even numbers.
 * 4. Ensure there is no busy-spinning (use wait/notify to block threads when it's not their turn).
 */
public class EvenOddPrinter {

    private final int limit;
    private int counter = 1;
    private final Object lock = new Object();

    public EvenOddPrinter(int limit) {
        this.limit = limit;
    }

    /**
     * Prints odd numbers up to the limit.
     */
    public void printOdd() {
        // TODO: Implement the loop and wait/notify logic to print odd numbers.
        // Tips:
        // - Synchronize on the shared 'lock' object.
        // - Loop until counter exceeds limit.
        // - While the counter is even, wait on the lock.
        // - Print the thread name and current counter.
        // - Increment the counter.
        // - Notify the other thread.
    }

    /**
     * Prints even numbers up to the limit.
     */
    public void printEven() {
        // TODO: Implement the loop and wait/notify logic to print even numbers.
        // Tips:
        // - Synchronize on the shared 'lock' object.
        // - Loop until counter exceeds limit.
        // - While the counter is odd, wait on the lock.
        // - Print the thread name and current counter.
        // - Increment the counter.
        // - Notify the other thread.
    }

    public static void main(String[] args) {
        EvenOddPrinter printer = new EvenOddPrinter(20);

        // TODO: Create Thread t1 for printOdd() and Thread t2 for printEven().
        // Tip: Set meaningful thread names (e.g., "Odd-Thread", "Even-Thread") to verify correctness in console logs.

        // TODO: Start both threads.

        // TODO: Join both threads to wait for completion.
    }
}
