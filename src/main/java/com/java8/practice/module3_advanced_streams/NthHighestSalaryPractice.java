package com.java8.practice.module3_advanced_streams;

import com.java8.practice.models.DataStore;
import com.java8.practice.models.Employee;

import java.util.*;
import java.util.stream.*;

/**
 * Exercises to find the N-th highest salary (dynamic value) using Streams.
 * This covers grouping, sorting, and dense ranking scenarios.
 */
public class NthHighestSalaryPractice {

    public static void main(String[] args) {
        List<Employee> employees = DataStore.getAllEmployees();

        System.out.println("2nd Highest Unique Salary: " + getNthHighestUniqueSalary(employees, 2));
        System.out.println("Employees with 2nd Highest Salary: " + getEmployeesWithNthHighestSalary(employees, 2));
        System.out.println("2nd Highest Salary by Dept: " + getNthHighestSalaryByDepartment(employees, 2));
    }

    /**
     * Exercise 1: Find the N-th highest unique salary from the list of employees.
     * For example, given salaries: [95k, 90k, 88k, 88k, 85k]
     * - 1st highest: 95k
     * - 2nd highest: 90k
     * - 3rd highest: 88k (duplicates should be treated as one rank, i.e. dense ranking)
     * - 4th highest: 85k
     *
     * If N is invalid or out of bounds, return Optional.empty().
     */
    public static Optional<Double> getNthHighestUniqueSalary(List<Employee> employees, int n) {
        // TODO: Implement dense ranking to find the N-th highest unique salary
        return Optional.empty();
    }

    /**
     * Exercise 2: Find all employees who earn the N-th highest salary.
     * Note: Multiple employees can have the same salary, so return a List of those Employees.
     * If no such salary exists, return an empty list.
     */
    public static List<Employee> getEmployeesWithNthHighestSalary(List<Employee> employees, int n) {
        // TODO: Find the N-th highest salary, and then filter employees matching that salary
        return Collections.emptyList();
    }

    /**
     * Exercise 3: Find the N-th highest salary in each department.
     * Return a Map where the key is the department name and the value is the N-th highest salary in that department.
     * If a department has fewer than N unique salaries, omit it from the result map or set the value to 0.0.
     */
    public static Map<String, Double> getNthHighestSalaryByDepartment(List<Employee> employees, int n) {
        // TODO: Group by department and find the N-th highest salary in each department
        return Collections.emptyMap();
    }
}
