package exercises0006;

import java.util.*;
import java.util.stream.Collectors;

public class MainClassForExercises0006 {

    public static record Employee(
            int id,
            String name,
            String department,
            Double salary
    ){

    }

    public static void main(String[] args){

        /*
        Group and Aggregate Data: Given a list of Employee objects (with fields: id, name, department, salary),
        write a stream pipeline to find the highest-paid employee in each department.
        */

        List<Employee> employeeList = Arrays.asList(
                new Employee(7, "Dhoni", "WK Plus Batting", 777777.0),
                new Employee(10, "Sachin", "Cricket", 888888.0),
                new Employee(18, "Virat Kohli", "Batting", 666666.0),
                new Employee(7, "Dhawan", "Batting", 444444.0),
                new Employee(7, "Yuvraj", "All Rounder", 777777.0),
                new Employee(7, "KL Rahul", "WK Plus Batting", 555555.0),
                new Employee(7, "Jadeja", "All Rounder", 444444.0)
        );

        //Highest paid employee
        Map<String, Employee> employeeMap = employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee :: department, // grouped by department
                        Collectors.maxBy(Comparator.comparingDouble(Employee :: salary)) // Employee sorted by salary descending, one max employee only
                )// HashMap : Key : department, Value -> Optional<Employee>
                ).entrySet().stream() //converting Map into stream for further processing
                .collect(Collectors.toMap( //Iterating the map
                        Map.Entry :: getKey, // Department as key
                        entry -> entry.getValue().orElse(null) // Unwrap Optional<Employee>
                ));

        System.out.println("Highest paid employee department wise : ");
        employeeMap.forEach((dept, emp) -> {
           System.out.println(dept+"  - Highest Paid Employee : "+emp.name+" (Salary : Rs. "+emp.salary+" )");
        });

        //Highest paid employee using Collectors.reducing
        Map<String, Employee> employeeMap1 = employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee :: department,
                        Collectors.reducing((e2, e1) -> e1.salary() > e2.salary() ? e1 : e2)
                )).entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry :: getKey,
                        entry -> entry.getValue().orElse(null)
                ));

        System.out.println("=".repeat(20));
        System.out.println("Highest paid employee department wise using Collectors.reducing : ");
        employeeMap1.forEach((dept, emp) -> {
            System.out.println(dept+"  - Highest Paid Employee : "+emp.name+" (Salary : Rs. "+emp.salary+" )");
        });

        //Second highest paid employee
        Map<String, Employee> employeeMap2 = employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee :: department,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    if(list.size() < 2) return null;

                                    list.sort((e1, e2) -> Double.compare(e2.salary(), e1.salary()));
                                    return list.get(1);
                                }
                        )
                )).entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .collect(Collectors.toMap(
                        Map.Entry :: getKey,
                        Map.Entry :: getValue
                ));

        System.out.println("=".repeat(20));
        System.out.println("Second highest paid employee department wise using Collectors.reducing : ");
        employeeMap2.forEach((dept, emp) -> {
            if(emp != null) {
                System.out.println(dept + " - Second highest Paid Employee : " + emp.name + " (Salary : Rs. " + emp.salary + " )");
            }
        });





    }

}
