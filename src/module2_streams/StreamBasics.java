package com.javatechie.lambda.demo.src.module2_streams;

import com.javatechie.lambda.demo.src.models.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Exercise class for basic Stream operations: map, filter, flatMap, collect, distinct, limit, skip.
 * Use the DataStore models to practice these operations.
 * 
 * Note: Do not write implementation code. The methods must return placeholder values (like null or empty collections)
 * so that they compile. Write comments explaining what the user needs to implement.
 */
public class StreamBasics {

    /**
     * Exercise 1: Filter and Map
     * Task: Filter employees who belong to the "Development" department and have a salary greater than 80000.
     *       Map their names and collect them into a List of Strings.
     */
    public static List<String> getHighPaidDevNames(List<Employee> employees) {
        // TODO: Implement this method using Stream API
        // Hint: filter -> map -> collect
        return Collections.emptyList();
    }

    /**
     * Exercise 2: FlatMap and Distinct (Objects)
     * Task: Retrieve a list of all unique Projects associated with all employees.
     *       Since each Employee has a List of Projects, you need to use flatMap to flatten the stream,
     *       then use distinct to filter duplicates, and collect them to a List.
     */
    public static List<Project> getUniqueProjects(List<Employee> employees) {
        // TODO: Implement this method using Stream API
        // Hint: flatMap(e -> e.getProjects().stream()) -> distinct() -> collect
        return Collections.emptyList();
    }

    /**
     * Exercise 3: FlatMap (Strings)
     * Task: Retrieve a list of all phone numbers from all customers.
     *       Flatten the list of phone numbers for each customer into a single list of Strings.
     */
    public static List<String> getAllCustomerPhoneNumbers(List<Customer> customers) {
        // TODO: Implement this method using Stream API
        return Collections.emptyList();
    }

    /**
     * Exercise 4: Limit and Sort
     * Task: Get the names of the top N students based on their rank (smaller rank is better, e.g., rank 1 is top).
     *       Sort the stream by student rank, limit to N, map to their first names, and collect to a List.
     */
    public static List<String> getTopNStudentNames(List<Student> students, int n) {
        // TODO: Implement this method using Stream API
        // Hint: sorted(Comparator.comparingInt(Student::getRank)) -> limit(n) -> map(Student::getFirstName) -> collect
        return Collections.emptyList();
    }

    /**
     * Exercise 5: Skip and Limit pagination simulation
     * Task: Skip the first 'skipCount' students and then limit the result to 'pageSize' students.
     *       Collect them to a List of Students.
     */
    public static List<Student> getStudentsPaged(List<Student> students, int skipCount, int pageSize) {
        // TODO: Implement this method using Stream API
        return Collections.emptyList();
    }

    /**
     * Exercise 6: Distinct (Strings)
     * Task: Get the set of all unique cities where students reside.
     *       Collect the result into a Set.
     */
    public static Set<String> getDistinctStudentCities(List<Student> students) {
        // TODO: Implement this method using Stream API
        return Collections.emptySet();
    }

    /**
     * Exercise 7: Count Unique Contacts
     * Task: Filter students who belong to "Mechanical Engineering" department.
     *       Extract their contacts (which is a list of Strings).
     *       Count the total number of unique contacts across all mechanical engineering students.
     */
    public static long countUniqueMechanicalStudentContacts(List<Student> students) {
        // TODO: Implement this method using Stream API
        return 0L;
    }

    public static void main(String[] args) {
        List<Employee> employees = DataStore.getAllEmployees();
        List<Customer> customers = DataStore.getAllCustomers();
        List<Student> students = DataStore.getAllStudents();

        System.out.println("Exercises loaded. Try implementing the solutions!");
        System.out.println("High Paid Devs Names: " + getHighPaidDevNames(employees));
        System.out.println("Unique Projects count: " + getUniqueProjects(employees).size());
        System.out.println("All Customer Phones: " + getAllCustomerPhoneNumbers(customers));
        System.out.println("Top 3 Students: " + getTopNStudentNames(students, 3));
        System.out.println("Paged Students (skip 2, limit 3): " + getStudentsPaged(students, 2, 3));
        System.out.println("Distinct Student Cities: " + getDistinctStudentCities(students));
        System.out.println("Unique Mechanical Contacts Count: " + countUniqueMechanicalStudentContacts(students));
    }
}
