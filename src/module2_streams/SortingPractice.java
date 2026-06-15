package com.javatechie.lambda.demo.src.module2_streams;


import com.javatechie.lambda.demo.src.models.DataStore;
import com.javatechie.lambda.demo.src.models.Employee;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Exercise class for sorting collections using Stream API, lambdas, and Comparator.
 * Practices sorting collections and maps with double comparisons, multi-key sorting, and reverse ordering.
 * 
 * Note: Do not write implementation code. The methods must return placeholder values (like null or empty collections)
 * so that they compile. Write comments explaining what the user needs to implement.
 */
public class SortingPractice {

    /**
     * Exercise 1: Basic sorting of a list of objects by a double attribute (Ascending)
     * Task: Sort the list of employees by their salary in ascending order.
     *       Return a list of sorted Employee objects.
     */
    public static List<Employee> sortEmployeesBySalaryAsc(List<Employee> employees) {
        // TODO: Implement this method using Stream API
        // Hint: sorted(Comparator.comparingDouble(Employee::getSalary))
        return Collections.emptyList();
    }

    /**
     * Exercise 2: Basic sorting of a list of objects by a double attribute (Descending / Reverse)
     * Task: Sort the list of employees by their salary in descending order.
     *       Return a list of sorted Employee objects.
     */
    public static List<Employee> sortEmployeesBySalaryDesc(List<Employee> employees) {
        // TODO: Implement this method using Stream API
        // Hint: sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
        return Collections.emptyList();
    }

    /**
     * Exercise 3: Multi-key sorting
     * Task: Sort employees first by their Department (dept) alphabetically (ascending),
     *       and then by their Salary in descending order if they are in the same department.
     *       Return a list of sorted Employee objects.
     */
    public static List<Employee> sortEmployeesByDeptThenSalaryDesc(List<Employee> employees) {
        // TODO: Implement this method using Stream API
        // Hint: Use Comparator.comparing(Employee::getDept).thenComparing(..., Comparator.reverseOrder())
        //       or chaining comparingDouble and reversed().
        return Collections.emptyList();
    }

    /**
     * Exercise 4: Sorting a Map by Key
     * Task: Given a Map of book title to rating (Integer), sort this Map by its Keys (alphabetically)
     *       and return a list of Map Entries sorted by Key.
     */
    public static List<Map.Entry<String, Integer>> sortBookRatingsByKey(Map<String, Integer> ratings) {
        // TODO: Implement this method using Stream API
        // Hint: ratings.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(...)
        return Collections.emptyList();
    }

    /**
     * Exercise 5: Sorting a Map by Value (Descending)
     * Task: Given a Map of book title to rating (Integer), sort this Map by its Values in descending order.
     *       Return a list of Map Entries sorted by value descending.
     */
    public static List<Map.Entry<String, Integer>> sortBookRatingsByValueDesc(Map<String, Integer> ratings) {
        // TODO: Implement this method using Stream API
        // Hint: sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        List<Employee> employees = DataStore.getAllEmployees();

        Map<String, Integer> bookRatings = Map.of(
            "Core Java", 5,
            "Hibernate", 3,
            "Spring Boot", 4,
            "WebService", 2
        );

        System.out.println("Sorting exercises loaded!");
        System.out.println("Employees sorted by salary ASC: " + sortEmployeesBySalaryAsc(employees));
        System.out.println("Employees sorted by salary DESC: " + sortEmployeesBySalaryDesc(employees));
        System.out.println("Employees sorted by Dept then Salary DESC: " + sortEmployeesByDeptThenSalaryDesc(employees));
        System.out.println("Book ratings sorted by Key: " + sortBookRatingsByKey(bookRatings));
        System.out.println("Book ratings sorted by Value DESC: " + sortBookRatingsByValueDesc(bookRatings));
    }
}
