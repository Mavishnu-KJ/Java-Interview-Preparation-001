package java_8_To_17_Exercises;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Java_Coding_Exercises_001 {

    public static void main(String[] args) {

        List<String> stringList = Arrays.asList(
                "Sachin", "Shewag", "Gambhir", "Virat", "", "Yuvraj", null, "Dhoni", "Raina"
        );

        List<Integer> integerList = Arrays.asList(10, 44, 18, 12, 7, 3);

        String nonNullValueString = "Funkynshot";
        String nullValueString = null;

        //Use lambda with forEach to print a list of strings with "Hello " prefix
        stringList.forEach(s -> {
            if (s != null && !s.isBlank()) {
                System.out.println("Hello " + s);
            }
        });

        //Sort a list of integers in descending order using lambda Comparator.
        List<Integer> integerListDesc = integerList.stream()
                .filter(Objects::nonNull)
                .sorted((a, b) -> b.compareTo(a))
                .toList();
        System.out.println("Sort a list of integers in descending order using lambda Comparator : " + integerListDesc);

        //Filter even numbers from a list using Predicate and lambda.
        List<Integer> evenIntegerList = integerList.stream()
                .filter(n -> Objects.nonNull(n) && n % 2 == 0)
                .toList();

        System.out.println("Filter even numbers from a list using Predicate and lambda : " + evenIntegerList);

        //Use Consumer to print each element of a list with uppercase.
        //NOTE : map is the FUNCTION, forEach is the Consumer
        System.out.println("Use Consumer to print each element of a list with uppercase :");
        stringList.forEach(s -> {
            if (Objects.nonNull(s) && !s.isBlank()) {
                System.out.println(s.toUpperCase());
            }
        });

        //Create a Supplier that generates random numbers (2–100)
        Random random = new Random();
        Supplier<Integer> supplier = () -> random.nextInt(100 - 2 + 1) + 2;
        //NOTE : formula is random.nextInt(max-min+1)+min

        //If Interviewer wants modern way random.ints
        Supplier<Integer> supplier1 = () -> random.ints(2, 101).findFirst().orElse(2);

        //Thread-safe version (if interviewer asks about concurrency)Java
        Supplier<Integer> supplier2 = () -> ThreadLocalRandom.current().nextInt(2, 101);

        System.out.println("Supplier that generated random numbers (2–100), random number : " + supplier.get());
        System.out.println("Supplier that generated random numbers (2–100), random number : " + supplier1.get());
        System.out.println("Supplier that generated random numbers (2–100), random number : " + supplier2.get());

        //Use Function to convert a list of strings to uppercase
        //NOTE : map is a FUNCTION, forEach is a CONSUMER
        List<String> upperCaseStringList = stringList.stream()
                .filter(Objects::nonNull)
                .filter(s -> !s.isBlank())
                .map(String::toUpperCase)
                .toList(); // Use collect(Collectors.toList()) if Java 8-15 compatibility needed

        System.out.println("Use Function to convert a list of strings to uppercase : " + upperCaseStringList);

        //Chain Function: Convert string to uppercase, then to length.
        List<Integer> lengthOfStringsInStringList = stringList.stream()
                .filter(s -> s != null && !s.isBlank())
                .map(s -> s.toUpperCase().length())
                .toList();
        System.out.println("Chain Function: Convert string to uppercase, then to length : " + lengthOfStringsInStringList);

        //Use Predicate to filter names starting with "S" from a list
        List<String> stringStartsWithS = stringList.stream()
                .filter(s -> Objects.nonNull(s) && !s.isBlank() && s.startsWith("S"))
                .toList();
        System.out.println("Use Predicate to filter names starting with \"S\" from a list : " + stringStartsWithS);

        //Implement a custom functional interface "Calculator" with add and subtract methods using lambda.
        //NOTE : Functional Interface is also known as Single Abstract Method (SAM) interface
        Calculator add = Integer::sum; // We can use (a,b) -> a+b as well
        Calculator subtract = (a, b) -> a - b;

        System.out.println("Using custom functional Interface Calculator, addition of 6,4 is " + add.calc(6, 4));
        System.out.println("Using custom functional Interface Calculator, subtraction of 6,4 is " + subtract.calc(6, 4));

        //Use lambda with Runnable to print "Hello from thread"
        Runnable runnable = () -> System.out.println("Hello From Thread " + Thread.currentThread().getName());

        //Thread t1 = new Thread(runnable); //If we do not mention the thread name, by default it will be Thread-0
        Thread t1 = new Thread(runnable, "Thread_01"); //Defining thread name as Thread 01
        t1.start(); //Calling runnable from thread t1
        runnable.run(); //Calling runnable from main thread

        System.out.println("Printing Hello from main"); //Just to show the thread running orders may vary because threads are non-deterministic

        //Sort a list of employees by salary using Comparator lambda.
        List<Employee> employeeList = Arrays.asList(
                new Employee(10L, "Sachin", 88888),
                new Employee(18L, "Virat Kohli", 55555),
                new Employee(7L, "Dhoni", 66666)
        );

        List<Employee> employeeListSortedBySalary = employeeList.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(Employee::getEmployeeSalary))
                .toList();

        System.out.println("Sort a list of employees by salary using Comparator lambda : "+employeeListSortedBySalary);

        //Find highest paid employee
        Employee highestPaidEmployee = employeeList.stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingInt(Employee::getEmployeeSalary))
                .orElse(null);
        System.out.println("Highest paid employee : "+highestPaidEmployee);

        //Find second highest paid employee
        Employee secondHighestPaidEmployee = employeeList.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(Employee::getEmployeeSalary).reversed())
                .limit(2)
                .skip(1)
                .findFirst()
                .orElse(null);
        System.out.println("Second Highest paid employee : "+secondHighestPaidEmployee);

        //Assuming the Employee can be null inside EmployeeList, So use null-check Comparator and sort the employeeListBySalary descending
        List<Employee> employeeListSortedBySalaryDesc = employeeList.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.nullsLast(Comparator.comparingInt(Employee::getEmployeeSalary).reversed()))
                .toList();
        System.out.println("Sort a list of employees by salary descending : "+employeeListSortedBySalaryDesc);

        //Group a list of strings by length descending using Collectors.groupingBy with lambda
        Map<Integer, List<String>> stringListGroupingByLengthDesc = stringList.stream()
                .filter(s-> s!=null && !s.isBlank())
                //.map(s -> s.length())
                //.sorted(Comparator.reverseOrder())
                .collect(Collectors.groupingBy(
                        String::length,
                        ()->new TreeMap<>((a,b)->b.compareTo(a)),
                        Collectors.toList()
                ));

        System.out.println("Group a list of strings by length descending using Collectors.groupingBy with lambda : "+stringListGroupingByLengthDesc);

        //Group a list of strings by length ascending using Collectors.groupingBy with lambda
        Map<Integer, List<String>> stringListGroupingByLengthAsc = stringList.stream()
                .filter(s-> Objects.nonNull(s) && !s.isBlank())
                //.map(s -> s.length())
                //.sorted(Comparator.naturalOrder())
                .collect(Collectors.groupingBy(
                            String::length,
                            TreeMap::new, //Equivalent to () -> new TreeMap<>()
                            Collectors.toList()
                            ));

        System.out.println("Group a list of strings by length ascending using Collectors.groupingBy with lambda : "+stringListGroupingByLengthAsc);

        //Use Optional with lambda: If value present, print it; else print default
        Optional<String> optionalWithNonNullValueString = Optional.ofNullable(nonNullValueString);
        Optional<String> optionalWithNullValueString = Optional.ofNullable(nullValueString);

        //Case 1: If value is present
        optionalWithNonNullValueString.ifPresentOrElse(
                (value)->System.out.println(value), // Can use System.out::println instead
                () -> System.out.println("default")
        );

        //Case 2: If value is not present
        optionalWithNullValueString.ifPresentOrElse(
                System.out::println,
                ()->System.out.println("default")
        );

        //Use Optional with lambda: If value present, print it; else print default, NOTE : Don't use ifPresentOrElse
        //Case 1: Value is present
        System.out.println(optionalWithNonNullValueString.orElse("default"));
        //Case 2: Value is not present
        System.out.println(optionalWithNullValueString.orElse("default"));

        //Create a Predicate that checks if a number is prime (using lambda)
        //Prime number is a number which is divided by 1 and itself
        //1 is not a prime number by the prime number rule
        //2 is the only even prime number
        //Numbers divided by 2 are not the prime numbers
        Predicate<Integer> isPrime = n -> {
            if(n<2)
                return false;
            if(n==2)
                return true;
            if(n%2==0)
                return false;
            for(int i=3; i<=Math.sqrt(n); i=i+2){
                if(n%i == 0)
                    return false;
            }
            return true;
        };

        System.out.println("Is 1 prime number ? "+isPrime.test(1));
        System.out.println("Is 2 prime number ? "+isPrime.test(2));
        System.out.println("Is 3 prime number ? "+isPrime.test(3));
        System.out.println("Is 8 prime number ? "+isPrime.test(8));
        System.out.println("Is 33 prime number ? "+isPrime.test(33));

        //Use lambda with Stream: Filter names starting with "S", map to uppercase, collect to list.
        List<String> upperCaseStringList1 = stringList.stream()
                .filter(s->s!=null && !s.isBlank() && s.startsWith("S"))
                .map(String::toUpperCase)
                .toList();

        System.out.println("Use lambda with Stream: Filter names starting with \"S\", map to uppercase, collect to list : "+upperCaseStringList1);

        //Implement a method reference for static method (e.g., String::toUpperCase, Integer::parseInt, Math::abs)
        Function<String, String> toUpperCase = String::toUpperCase;
        Function<String, Integer> parseInt = Integer::parseInt;
        Function<Integer, Integer> mathAbs = Math::abs;

        System.out.println("Method Reference using Function, toUpperCase(\"Funkynshot\") "+toUpperCase.apply("Funkynshot")); //Output: FUNKYNSHOT
        System.out.println("Method Reference using Function, parseInt(\"500\") "+parseInt.apply("500")); //Output: 500
        System.out.println("Method Reference using Function, mathAbs(-12) "+mathAbs.apply(-12)); //Output: 12








    }

}
