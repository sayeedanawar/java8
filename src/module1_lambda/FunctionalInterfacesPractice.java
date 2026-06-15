package com.javatechie.lambda.demo.src.module1_lambda;



import com.javatechie.lambda.demo.src.models.DataStore;
import com.javatechie.lambda.demo.src.models.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * Practice template for built-in functional interfaces:
 * Predicate, Consumer, Supplier, Function, BiPredicate, BiConsumer, BiFunction.
 */
public class FunctionalInterfacesPractice {

    public static void main(String[] args) {
        List<Employee> employees = DataStore.getAllEmployees();

        System.out.println("=== 1. Predicate Practice ===");
        // TODO: Create a Predicate that checks if an employee's salary is greater than 80,000
        Predicate<Employee> highSalaryPredicate = emp -> false;

        // TODO: Create a Predicate that checks if an employee belongs to the "Development" department
        Predicate<Employee> devDeptPredicate = emp -> false;

        // TODO: Combine predicates (high salary AND development department) using .and()
        Predicate<Employee> highSalaryDevPredicate = emp -> false;

        System.out.println("Employees with high salary (>80k):");
        filterAndPrint(employees, highSalaryPredicate);
        
        System.out.println("\nEmployees in Development department with high salary:");
        filterAndPrint(employees, highSalaryDevPredicate);


        System.out.println("\n=== 2. Consumer Practice ===");
        // TODO: Create a Consumer that prints employee name and salary in the format "Name: [name], Salary: [salary]"
        Consumer<Employee> printFormatConsumer = emp -> {};

        // TODO: Create a Consumer that prints employee's assigned projects names
        Consumer<Employee> printProjectsConsumer = emp -> {};

        // TODO: Chain consumers using .andThen() to print format first, then projects
        Consumer<Employee> chainedConsumer = emp -> {};

        System.out.println("Executing chained consumer for first 3 employees:");
        employees.stream().limit(3).forEach(chainedConsumer);


        System.out.println("\n=== 3. Supplier Practice ===");
        // TODO: Create a Supplier that returns a default Employee (id: 0, name: "Default Guest", department: "Guest", salary: 0)
        Supplier<Employee> defaultEmployeeSupplier = () -> null;

        Employee guestEmployee = defaultEmployeeSupplier.get();
        System.out.println("Default Guest Employee: " + guestEmployee);


        System.out.println("\n=== 4. Function Practice ===");
        // TODO: Create a Function that extracts the name of an employee
        Function<Employee, String> nameExtractor = emp -> null;

        // TODO: Create a Function that converts a String to uppercase
        Function<String, String> toUpperCaseFn = str -> null;

        // TODO: Compose these functions using .andThen() to get the uppercase name of an Employee
        Function<Employee, String> upperCaseNameExtractor = emp -> null;

        System.out.println("Uppercase names of all employees:");
        for (Employee emp : employees) {
            System.out.println(upperCaseNameExtractor.apply(emp));
        }


        System.out.println("\n=== 5. Bi-Functional Interfaces Practice ===");
        // TODO: Create a BiPredicate to check if an employee's salary is greater than a given threshold
        BiPredicate<Employee, Double> isSalaryAboveThreshold = (emp, threshold) -> false;

        // TODO: Create a BiConsumer to print employee name and a custom message prefix
        BiConsumer<Employee, String> printWithMessage = (emp, prefix) -> {};

        // TODO: Create a BiFunction to calculate a new salary after applying a percentage raise (e.g. Employee, percentage -> double)
        BiFunction<Employee, Double, Double> calculateRaisedSalary = (emp, percent) -> 0.0;

        Employee sampleEmp = employees.get(0);
        System.out.println("Is " + sampleEmp.getName() + " salary above 75k? " + isSalaryAboveThreshold.test(sampleEmp, 75000.0));
        printWithMessage.accept(sampleEmp, "Processing Record: ");
        System.out.println("Salary after 10% raise: " + calculateRaisedSalary.apply(sampleEmp, 10.0));
    }

    /**
     * Helper method to filter list of employees using a Predicate and print them.
     */
    private static void filterAndPrint(List<Employee> list, Predicate<Employee> predicate) {
        for (Employee emp : list) {
            if (predicate.test(emp)) {
                System.out.println(emp);
            }
        }
    }
}
