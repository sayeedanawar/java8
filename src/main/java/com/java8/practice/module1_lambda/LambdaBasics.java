package com.java8.practice.module1_lambda;

/**
 * Practice template for custom functional interfaces using Lambda expressions.
 */
public class LambdaBasics {

    // 1. Custom Math Operation Interface
    @FunctionalInterface
    public interface MathOperation {
        int operate(int a, int b);
    }

    // 2. Custom String Formatter Interface
    @FunctionalInterface
    public interface StringFormatter {
        String format(String input);
    }

    // Helper method that uses MathOperation
    public static int executeOperation(int a, int b, MathOperation op) {
        return op.operate(a, b);
    }

    // Helper method that uses StringFormatter
    public static String formatString(String input, StringFormatter formatter) {
        return formatter.format(input);
    }

    public static void main(String[] args) {
        System.out.println("=== 1. Custom Math Operations ===");

        // TODO: Implement MathOperation for Addition using Lambda
        MathOperation addition = (a, b) -> 0; // Replace with lambda expression

        // TODO: Implement MathOperation for Multiplication using Lambda
        MathOperation multiplication = (a, b) -> 0; // Replace with lambda expression

        // TODO: Implement MathOperation for Division using Lambda (ensure you handle division by zero or assume b != 0 for now)
        MathOperation division = (a, b) -> 0; // Replace with lambda expression

        System.out.println("Addition (10 + 5): " + executeOperation(10, 5, addition));
        System.out.println("Multiplication (10 * 5): " + executeOperation(10, 5, multiplication));
        System.out.println("Division (10 / 5): " + executeOperation(10, 5, division));


        System.out.println("\n=== 2. Custom String Formatters ===");

        // TODO: Implement StringFormatter to convert input to uppercase
        StringFormatter toUpperCase = input -> ""; // Replace with lambda expression

        // TODO: Implement StringFormatter to reverse the input string
        StringFormatter reverse = input -> ""; // Replace with lambda expression

        // TODO: Implement StringFormatter to wrap input with brackets, e.g. "[input]"
        StringFormatter bracketWrap = input -> ""; // Replace with lambda expression

        String rawText = "LambdaPractice";
        System.out.println("Uppercase: " + formatString(rawText, toUpperCase));
        System.out.println("Reversed: " + formatString(rawText, reverse));
        System.out.println("Bracket Wrapped: " + formatString(rawText, bracketWrap));


        System.out.println("\n=== 3. Scoping Rules and Variable Capture ===");
        
        int multiplier = 5;
        // TODO: Create a MathOperation using Lambda that multiplies 'a' and 'b' and then multiplies the result by the local variable 'multiplier'
        // Check if you can modify the variable 'multiplier' after creating/executing the lambda.
        MathOperation scaledMultiply = (a, b) -> 0; // Replace with lambda expression
        
        System.out.println("Scaled Multiplication (2 * 3 * 5): " + executeOperation(2, 3, scaledMultiply));
    }
}
