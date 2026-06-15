package com.javatechie.lambda.demo.src.module3_advanced_streams;

import com.javatechie.lambda.demo.src.models.DataStore;
import com.javatechie.lambda.demo.src.models.Employee;
import com.javatechie.lambda.demo.src.models.Student;

import java.util.*;

/**
 * Advanced Stream API Exercises targeting 5+ Years of Experience.
 * Implement the methods below using Java 8 streams, collectors, groupingBy, partitioningBy, and custom collectors.
 * DO NOT use external libraries.
 */
public class AdvancedStreams {

    public static void main(String[] args) {
        List<Employee> employees = DataStore.getAllEmployees();
        List<Student> students = DataStore.getAllStudents();

        System.out.println("1. Employees grouped by Dept then Gender: " + groupEmployeesByDeptThenGender(employees));
        System.out.println("2. Highest paid employee in each Dept: " + getHighestPaidEmployeeInEachDept(employees));
        System.out.println("3. Students partitioned by top rank (< 100): " + partitionStudentsByRank(students));
        System.out.println("4. Custom representation of names: " + mapNamesToCustomStringRepresentation(employees));
        System.out.println("5. Custom collector join strings: " + joinStringsWithCustomCollector(Arrays.asList("Java", "Python", "Go", "Rust")));
    }

    /**
     * Exercise 1: Group employees by Department, and then nested-group them by Gender.
     * Expected Output Map structure: Map<DepartmentName, Map<Gender, List<Employee>>>
     */
    public static Map<String, Map<String, List<Employee>>> groupEmployeesByDeptThenGender(List<Employee> employees) {
        // TODO: Implement groupingBy department and nested groupingBy gender
        return Collections.emptyMap();
    }

    /**
     * Exercise 2: Find the highest-paid employee in each department.
     * Avoid returning Optional inside the map values. Return the Employee object directly.
     * Hint: Use Collectors.collectingAndThen along with Collectors.maxBy.
     */
    public static Map<String, Employee> getHighestPaidEmployeeInEachDept(List<Employee> employees) {
        // TODO: Find the highest-paid employee in each department using downstream collectors
        return Collections.emptyMap();
    }

    /**
     * Exercise 3: Partition students into two groups:
     * - Top Rankers: rank < 100 (key: true)
     * - Others: rank >= 100 (key: false)
     */
    public static Map<Boolean, List<Student>> partitionStudentsByRank(List<Student> students) {
        // TODO: Partition students by rank (< 100)
        return Collections.emptyMap();
    }

    /**
     * Exercise 4: Convert the names of all employees into a custom formatted string:
     * "[Name1 | Name2 | Name3]"
     * Hint: Use Collectors.joining with delimiter, prefix, and suffix.
     */
    public static String mapNamesToCustomStringRepresentation(List<Employee> employees) {
        // TODO: Collect employee names and join them in the specified format
        return "";
    }

    /**
     * Exercise 5: Implement a custom Collector using Collector.of() that joins a list of strings
     * with a hyphen "-" delimiter, skipping empty/null strings.
     * Do not use the built-in Collectors.joining() directly.
     */
    public static String joinStringsWithCustomCollector(List<String> strings) {
        // TODO: Define a custom collector using Collector.of to join non-empty strings with a hyphen "-"
        return "";
    }
}
