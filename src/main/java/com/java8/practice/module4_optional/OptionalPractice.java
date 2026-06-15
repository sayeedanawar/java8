package com.java8.practice.module4_optional;

import com.java8.practice.models.*;
import java.util.*;

/**
 * Practice exercises for Java 8 Optional class.
 * Learn to write code avoiding NullPointerExceptions and refactor legacy null checks.
 */
public class OptionalPractice {

    // Helper classes for nested Optional exercises (Computer -> Soundcard -> USB)
    public static class Computer {
        private final Soundcard soundcard;
        public Computer(Soundcard soundcard) { this.soundcard = soundcard; }
        public Optional<Soundcard> getSoundcard() { return Optional.ofNullable(soundcard); }
    }

    public static class Soundcard {
        private final USB usb;
        public Soundcard(USB usb) { this.usb = usb; }
        public Optional<USB> getUsb() { return Optional.ofNullable(usb); }
    }

    public static class USB {
        private final String version;
        public USB(String version) { this.version = version; }
        public String getVersion() { return version; }
    }

    // Helper method that simulates an expensive db lookup or creation
    private static String getExpensiveDefault() {
        System.out.println(">>> getExpensiveDefault() was executed!");
        return "System Default Project";
    }

    /**
     * Task 1: Safe Mapping & flatMapping with Optional
     * Get the USB version of the Computer. If the computer, soundcard, or USB is missing, return "UNKNOWN".
     * Hint: Use flatMap when the method returns an Optional, and map when it returns a raw object.
     */
    public static String getUsbVersion(Optional<Computer> computer) {
        // TODO: Implement using flatMap and map on the Optional, defaulting to "UNKNOWN"
        return "UNKNOWN";
    }

    /**
     * Task 2: findAny/findFirst with Optional
     * Find an Employee from the list by their ID.
     * Return an Optional containing the Employee, or Optional.empty() if not found.
     */
    public static Optional<Employee> findEmployeeById(List<Employee> employees, int id) {
        // TODO: Implement using Stream API and findFirst or findAny
        return Optional.empty();
    }

    /**
     * Task 3: orElseThrow Exception
     * Find an Employee by ID. If found, return their name. If not, throw an IllegalArgumentException with message "Employee not found".
     * Hint: Use Optional.orElseThrow(...)
     */
    public static String getEmployeeNameOrThrow(List<Employee> employees, int id) {
        // TODO: Find the employee and map to name, or throw IllegalArgumentException
        return "";
    }

    /**
     * Task 4: orElse vs orElseGet (Evaluation differences)
     * Demonstrates execution of default values.
     * Use orElse and orElseGet with 'getExpensiveDefault()' on a PRESENT optional and an EMPTY optional.
     */
    public static void runOrElseDemo() {
        Optional<String> presentOptional = Optional.of("Alpha Project");
        Optional<String> emptyOptional = Optional.empty();

        System.out.println("--- Testing orElse with PRESENT Optional ---");
        // TODO: Retrieve value or use orElse(getExpensiveDefault())
        String val1 = ""; 
        System.out.println("Result: " + val1);

        System.out.println("\n--- Testing orElseGet with PRESENT Optional ---");
        // TODO: Retrieve value or use orElseGet(() -> getExpensiveDefault())
        String val2 = "";
        System.out.println("Result: " + val2);

        System.out.println("\n--- Testing orElse with EMPTY Optional ---");
        // TODO: Retrieve value or use orElse(getExpensiveDefault())
        String val3 = "";
        System.out.println("Result: " + val3);

        System.out.println("\n--- Testing orElseGet with EMPTY Optional ---");
        // TODO: Retrieve value or use orElseGet(() -> getExpensiveDefault())
        String val4 = "";
        System.out.println("Result: " + val4);
    }

    /**
     * Task 5: Refactoring Legacy Null Checks
     * Here is a typical legacy nested null check method. Refactor it using Optional.
     */
    public static String getProjectClientLegacy(Employee employee) {
        if (employee != null) {
            List<Project> projects = employee.getProjects();
            if (projects != null && !projects.isEmpty()) {
                Project firstProject = projects.get(0);
                if (firstProject != null) {
                    String client = firstProject.getClient();
                    if (client != null) {
                        return client.toUpperCase();
                    }
                }
            }
        }
        return "UNKNOWN CLIENT";
    }

    /**
     * Task 5 (Refactored): Refactor the above method to use Optional pipelines.
     * Do NOT use any 'if' checks.
     */
    public static String getProjectClientOptional(Employee employee) {
        // TODO: Refactor using Optional.ofNullable, map, filter, etc.
        return "UNKNOWN CLIENT";
    }

    public static void main(String[] args) {
        List<Employee> employees = DataStore.getAllEmployees();

        System.out.println("=== Task 1: USB Version Lookup ===");
        USB usb = new USB("3.1");
        Soundcard soundcard = new Soundcard(usb);
        Computer computer = new Computer(soundcard);

        System.out.println("USB Version (Valid Computer): " + getUsbVersion(Optional.of(computer)));
        System.out.println("USB Version (No USB): " + getUsbVersion(Optional.of(new Computer(new Soundcard(null)))));
        System.out.println("USB Version (No Soundcard): " + getUsbVersion(Optional.of(new Computer(null))));
        System.out.println("USB Version (Empty): " + getUsbVersion(Optional.empty()));


        System.out.println("\n=== Task 2 & 3: Finding Elements & Throwing Exceptions ===");
        Optional<Employee> empOpt = findEmployeeById(employees, 5);
        empOpt.ifPresent(e -> System.out.println("Found Employee 5: " + e.getName()));
        
        try {
            System.out.println("Employee 2 Name: " + getEmployeeNameOrThrow(employees, 2));
            System.out.println("Employee 99 Name: " + getEmployeeNameOrThrow(employees, 99));
        } catch (Exception e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }


        System.out.println("\n=== Task 4: orElse vs orElseGet Demo ===");
        runOrElseDemo();


        System.out.println("\n=== Task 5: Refactoring Null Checks ===");
        Employee legacyEmp = employees.get(0); // Has projects with client
        Employee noProjEmp = new Employee(11, "No Project Dev", "Development", null, 60000, "Male");

        System.out.println("Legacy (Normal): " + getProjectClientLegacy(legacyEmp));
        System.out.println("Legacy (Null Projects): " + getProjectClientLegacy(noProjEmp));
        System.out.println("Legacy (Null Employee): " + getProjectClientLegacy(null));

        System.out.println("Optional (Normal): " + getProjectClientOptional(legacyEmp));
        System.out.println("Optional (Null Projects): " + getProjectClientOptional(noProjEmp));
        System.out.println("Optional (Null Employee): " + getProjectClientOptional(null));
    }
}
