package com.javatechie.lambda.demo.src.models;

import java.util.Arrays;
import java.util.List;

public class DataStore {

    public static List<Employee> getAllEmployees() {
        Project p1 = new Project("P001", "Alpha", "ABC Corp", "Alice");
        Project p2 = new Project("P002", "Beta", "XYZ Ltd", "Bob");
        Project p3 = new Project("P003", "Gamma", "ABC Corp", "Alice");
        Project p4 = new Project("P004", "Delta", "TechWorld", "Charlie");
        Project p5 = new Project("P005", "Epsilon", "MoneyMatters", "Daniel");
        Project p6 = new Project("P006", "Zeta", "SmartTech", "Eva");
        Project p7 = new Project("P007", "Eta", "BrandBoost", "George");
        Project p8 = new Project("P008", "Theta", "InnoSoft", "Hannah");
        Project p9 = new Project("P009", "Iota", "FastTrack", "Ian");
        Project p10 = new Project("P010", "Kappa", "DigitalWave", "Jessica");

        Employee e1 = new Employee(1, "John Doe", "Development", Arrays.asList(p1, p2), 80000, "Male");
        Employee e2 = new Employee(2, "Jane Smith", "Development", Arrays.asList(p3), 80000, "Female");
        Employee e3 = new Employee(3, "Robert Brown", "Sales", Arrays.asList(p4), 60000, "Male");
        Employee e4 = new Employee(4, "Lisa White", "HR", Arrays.asList(p1), 55000, "Female");
        Employee e5 = new Employee(5, "Michael Green", "Finance", Arrays.asList(p5), 90000, "Male");
        Employee e6 = new Employee(6, "Sophia Brown", "Development", Arrays.asList(p6), 85000, "Female");
        Employee e7 = new Employee(7, "James Wilson", "Marketing", Arrays.asList(p7), 72000, "Male");
        Employee e8 = new Employee(8, "Olivia Harris", "Development", Arrays.asList(p8), 88000, "Female");
        Employee e9 = new Employee(9, "William Lee", "Sales", Arrays.asList(p9), 78000, "Male");
        Employee e10 = new Employee(10, "Emily Clark", "Development", Arrays.asList(p10), 95000, "Female");

        return Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
    }

    public static List<Student> getAllStudents() {
        return Arrays.asList(
            new Student(1, "Rohit", 30, "Male", "Mechanical Engineering", "Mumbai", 122, Arrays.asList("+912632632782", "+1673434729929")),
            new Student(2, "Pulkit", 56, "Male", "Computer Engineering", "Delhi", 67, Arrays.asList("+912632632762", "+1673434723929")),
            new Student(3, "Ankit", 25, "Female", "Mechanical Engineering", "Kerala", 164, Arrays.asList("+912632633882", "+1673434709929")),
            new Student(4, "Satish Ray", 30, "Male", "Mechanical Engineering", "Kerala", 26, Arrays.asList("+9126325832782", "+1671434729929")),
            new Student(5, "Roshan", 23, "Male", "Biotech Engineering", "Mumbai", 12, Arrays.asList("+012632632782")),
            new Student(6, "Chetan", 24, "Male", "Mechanical Engineering", "Karnataka", 90, Arrays.asList("+9126254632782", "+16736784729929")),
            new Student(7, "Arun", 26, "Male", "Electronics Engineering", "Karnataka", 324, Arrays.asList("+912632632782", "+1671234729929")),
            new Student(8, "Nam", 31, "Male", "Computer Engineering", "Karnataka", 433, Arrays.asList("+9126326355782", "+1673434729929")),
            new Student(9, "Sonu", 27, "Female", "Computer Engineering", "Karnataka", 7, Arrays.asList("+9126398932782", "+16563434729929", "+5673434729929")),
            new Student(10, "Shubham", 26, "Male", "Instrumentation Engineering", "Mumbai", 98, Arrays.asList("+912632646482", "+16734323229929"))
        );
    }

    public static List<Customer> getAllCustomers() {
        return Arrays.asList(
            new Customer(101, "john", "john@gmail.com", Arrays.asList("397937955", "21654725")),
            new Customer(102, "smith", "smith@gmail.com", Arrays.asList("89563856", "24856389")),
            new Customer(103, "peter", "peter@gmail.com", Arrays.asList("38946326", "2389463")),
            new Customer(104, "kely", "kely@gmail.com", Arrays.asList("98763524", "3794628"))
        );
    }

    public static List<Book> getAllBooks() {
        return Arrays.asList(
            new Book(101, "Core Java", 400),
            new Book(363, "Hibernate", 180),
            new Book(275, "Spring Boot", 240),
            new Book(893, "WebService", 300)
        );
    }
}
