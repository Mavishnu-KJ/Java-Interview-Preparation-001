package exercises0008;

import exercises0008.MainClassForExercises0008;

import java.util.*;
import java.util.stream.Collectors;

public class MainClassForExercises0008 {

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

        Map<String, Employee> highestPaidEmployeeMapByDepartmentWise = employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.maxBy(Comparator.comparingDouble(Employee :: salary))
                )).entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry :: getKey,
                        entry -> entry.getValue().orElse(null)
                ));

        System.out.println("=".repeat(20));
        System.out.println("Highest paid employee department wise : ");
        highestPaidEmployeeMapByDepartmentWise.forEach((dept, emp) -> {
           System.out.println(dept+"-> "+emp.name+"( Salary : "+ emp.salary()+" )");
        });

        Map<String, Employee> secondHighestPaidEmployeeMapByDepartmentWise = employeeList.stream()
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
                        entry -> entry.getValue()
                ));

        System.out.println("=".repeat(20));
        System.out.println("Second highest paid employee department wise : ");
        secondHighestPaidEmployeeMapByDepartmentWise.forEach((dept, emp) -> {
                System.out.println(dept + "-> " + emp.name + "( Salary : " + emp.salary() + " )");
        });


         /*
        Frequency Count: Given a string,
        find the frequency of each character, and filter out the top 3 most frequent elements using Streams.
        */

        String str = "aaabbbbbbcccdddd";
        Map<Character, Integer> top3MostFrequentElements = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                )).entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry :: getKey,
                        entry -> entry.getValue().intValue(),
                        (existing, duplicate) -> existing,
                        LinkedHashMap :: new
                ));

        System.out.println("=".repeat(20));
        System.out.println("top 3 most frequent elements using Streams : ");
        top3MostFrequentElements.forEach((character, frequency) -> {
            System.out.println(character + "-> "+frequency+" times repeated");
        });

        /*
        Frequency Count: Given a list of words,
        find the frequency of each word, and filter out the top 3 most frequent elements using Streams.
        */

        List<String> wordList = Arrays.asList(
                "banana", "banana", "apple", "orange", "banana",
                "banana", "orange", "banana", "apple", "orange"
        );

        Map<String, Integer> top3MostFrequentElements1 = wordList.stream()
                .collect(Collectors.groupingBy(
                        s -> s,
                        Collectors.counting()
                )).entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry :: getKey,
                        entry -> entry.getValue().intValue(),
                        (existing, duplicate) -> existing,
                        LinkedHashMap :: new
                ));

        System.out.println("=".repeat(20));
        System.out.println("top 3 most frequent elements using Streams : ");
        top3MostFrequentElements1.forEach((word, freq) -> {
            System.out.println(word + "-> "+freq+" times repeated");
        });

    }

}
