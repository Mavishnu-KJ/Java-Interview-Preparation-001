package java_8_To_17_Exercises;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java_Coding_Exercises_001 {

    public static void main(String[] args) {

        List<String> stringList = Arrays.asList(
                "Sachin", "Shewag", "Gambhir", "Virat", "", "Yuvraj", null, "Dhoni", "Raina"
        );

        List<String> stringListWithDuplicates = Arrays.asList(
            "Sachin Tendulkar", "Virat Kohli", "Dhoni", "Raina", null, "", "Jadeja", "   ", "Dhoni", "Raina"
        );

        List<Integer> integerList = Arrays.asList(10, 44, 18, 12, 7, 3);
        List<Integer> emptyList = List.of();

        List<List<Integer>> nestedIntegerList = List.of(
                Arrays.asList(1, 3, 5, 7),
                Arrays.asList(2, 4, 6, 8)
        );
        //System.out.println("nestedIntegerList is "+nestedIntegerList);

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

        //Use constructor reference to create a new ArrayList.
        Supplier<ArrayList<Integer>> arrayListSupplier = ArrayList::new ;

        ArrayList<Integer> arrayList1FromSupplier = arrayListSupplier.get();
        arrayList1FromSupplier.add(10);
        arrayList1FromSupplier.add(7);

        ArrayList<Integer> arrayList2FromSupplier = arrayListSupplier.get();
        arrayList2FromSupplier.add(18);
        arrayList2FromSupplier.add(1);

        System.out.println("Using constructor reference, created arrayList arrayList1FromSupplier is "+arrayList1FromSupplier);
        System.out.println("Using constructor reference, created arrayList arrayList2FromSupplier is "+arrayList2FromSupplier);

        //Handle checked exception in lambda (wrap in try-catch or custom functional interface).

        //1. Wrap in try-catch
        //NOTE : NullPointerException, RunTimeException are unchecked exceptions
        //NOTE : IOException, ParseException are checked exceptions
        System.out.println("\nHandling checked exception in lambda by wrap in try-catch : ");
        stringList.forEach(s->{
                if(s!=null && !s.isBlank()){
                    try {
                        if (s.startsWith("S")) {
                            throw new IOException("String starts with S");
                        }
                        System.out.println(s);
                    }catch(IOException e){
                        System.out.println("Caught checked exception : "+e.getMessage());
                    }
                }
            }
        );

        //Using custom functional interface
        System.out.println("\nHandling checked exception in lambda using custom functional interface : ");
        ThrowIOException<String> throwIOExceptionInterface = (s)->{
                if (s.startsWith("S")) {
                    throw new IOException("String starts with S");
                }
        };

        stringList.forEach(s->{
                    if(s!=null && !s.isBlank()){
                        try {
                            throwIOExceptionInterface.throwIOException(s);
                            System.out.println(s);
                        }catch (IOException e){
                            System.out.println("Caught checked exception : "+e.getMessage());
                        }
                    }
                }
        );


        //Use BiFunction to add two numbers with lambda
        BiFunction<Integer, Integer, Integer> additionUsingBiFunction = (a,b) -> a+b; //We can use Integer::sum instead
        System.out.println("Addition of 6, 4 using BiFunction, additionUsingBiFunction.apply(6,4) is "+additionUsingBiFunction.apply(6,4));

        //Combine Predicate and Consumer: Filter and print only matching elements.
        System.out.println("Combined Predicate and Consumer, Printing only the matching elements :");
        stringList.stream()
                .filter(s->s!=null && !s.isBlank() && s.startsWith("S")) //Predicate
                .forEach(System.out::println); //Consumer

        //Create Stream from list and print all elements using forEach
        System.out.println("Created stream from list and printing all elements using forEach : ");
        integerList.stream()
                .filter(Objects::nonNull)
                .forEach(System.out::println);

        //Filter even numbers from list and print using forEach
        System.out.println("Even numbers from integerList : ");
        integerList.stream()
                .filter(n->Objects.nonNull(n) && n%2 ==0)
                .forEach(System.out::println);

        //Map list of strings to uppercase and collect to List
        List<String> upperCaseStringList2 = stringList.stream()
                .filter(s-> Objects.nonNull(s) && !s.isBlank())
                .map(String::toUpperCase)
                .toList();

        System.out.println("Map list of strings to uppercase and collect to List, upperCaseStringList2 is "+upperCaseStringList2);

        //Sort list of integers ascending using sorted
        List<Integer> integerListSortedAsc = integerList.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.naturalOrder()) // We can use just sorted() instead for ascending order
                .toList();

        System.out.println("Sort list of integers ascending using sorted, integerListSortedAsc is "+integerListSortedAsc);

        //Sort descending using sorted(Comparator.reverseOrder()).
        List<Integer> integerListSortedDesc = integerList.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.reverseOrder())
                .toList();

        System.out.println("Sort descending using sorted, integerListSortedDesc is "+integerListSortedDesc);

        //Count elements in list using count
        long numberOfElementsInStringList = stringList.stream()
                //.filter(Objects::nonNull)
                .count();

        System.out.print("number of elements in stringList is : "+numberOfElementsInStringList);

        //Find first element using findFirst (or Optional).
        String firstElement = stringList.stream()
                .filter(s->s!=null && !s.isBlank())
                .findFirst()
                .orElse(null);

        System.out.println("Find first element using findFirst (or Optional), firstElement is "+firstElement);

        //Check if any number is even using anyMatch
        boolean isIntegerListHasEven = integerList.stream()
                .filter(Objects::nonNull)
                .anyMatch(n->n%2==0);

        System.out.println("integerList "+integerList+" has even number ? "+isIntegerListHasEven);

        //Using anyMatch with emptyList
        boolean isEmptyListHasEven = emptyList.stream()
                .filter(Objects::nonNull)
                .anyMatch(n->n%2==0);

        System.out.println("emptyList "+emptyList+" has even number ? "+isEmptyListHasEven);

        //Check if all numbers are positive using allMatch
        boolean isAllNumbersInIntegerListPositive = integerList.stream()
                .filter(Objects::nonNull)
                .allMatch(n->n>0);

        System.out.println("integerList "+integerList+" has all numbers positive ? "+isAllNumbersInIntegerListPositive);

        //Check if no number is negative using noneMatch.
        boolean isNoNegativeNumberInIntegerList = integerList.stream()
                .filter(Objects::nonNull)
                .noneMatch(n->n<0);

        System.out.println("integerList "+integerList+" has no negative numbers ? "+isNoNegativeNumberInIntegerList);

        //Reduce to sum of list using reduce

        //Method 1 : Using orElse
        Integer sumOfIntegersInTheIntegerList = integerList.stream()
                .filter(Objects::nonNull)
                .reduce((a,b)->a+b)// We can use Integer::sum instead
                .orElse(null);
        System.out.println("Reduce to sum of list using reduce, using Optional, sumOfIntegersInTheIntegerList is "+sumOfIntegersInTheIntegerList);

        //Method 2 : Using Optional<Integer>
        Optional<Integer> sumOfIntegersInTheEmptyList = emptyList.stream()
                .filter(Objects::nonNull)
                .reduce(Integer::sum); // It will return Optional.empty if the list is empty
        System.out.println("Reduce to sum of list using reduce, using Optional, sumOfIntegersInTheEmptyList is "+sumOfIntegersInTheEmptyList);

        //Method 3 : Use int instead of Integer
        int sumOfIntegersInTheIntegerList1 = integerList.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                //.reduce(0,Integer::sum);
                .sum();

        System.out.println("Reduce to sum of list using int instead of Integer, sumOfIntegersInTheIntegerList1 is "+sumOfIntegersInTheIntegerList1);

        /* NOTE : prefer using int instead of Integer, reduce(0, Integer::sum) instead of reduce(Integer:;sum) if not Optional
            reduce(Integer::sum) will give Optional.empty incase of the list is empty
            reduce(0, Integer::sum) will give 0 in case of the list is empty - This is the expected output,
            So, prefer reduce(0, Integer::sum) over reduce(Integer::sum)
         */

        //Reduce to product of list using reduce
        long productOfIntegersInTheIntegerList = integerList.stream()
                .filter(Objects::nonNull)
                .mapToLong(Integer::longValue)
                .reduce(1L, (a,b)->a*b); // We can use Integer::multiplyExact instead

        //NOTE : We can use Math::multiplyExact to throw ArithmeticException on overflow, RECOMMENDED

        System.out.println("Reduce to product of list using reduce, productOfIntegersInTheIntegerList is "+productOfIntegersInTheIntegerList);

        //FlatMap: Flatten List<List<Integer>> to List<Integer>
        List<Integer> flattenedList = nestedIntegerList.stream()
                .filter(Objects::nonNull)
                //.flatMap(innerList->innerList.stream()) //For each inner List<Integer>, it calls .stream() → returns Stream<Integer>
                .flatMap(innerList->innerList==null?Stream.empty():innerList.stream())
                //.flatMap(List::stream) // We can use List::stream instead but only if innerList is not null
                .toList();

        System.out.println("Flattened the nestedIntegerList "+nestedIntegerList+ " to "+flattenedList);

        //Remove duplicates using distinct.
        List<String> stringListWithoutDuplicates = stringListWithDuplicates.stream()
                .filter(s->s!=null && !s.isBlank())
                .distinct()
                .toList();

        System.out.println("Removed duplicates using distinct, stringListWithoutDuplicates is "+stringListWithoutDuplicates);

        /* NOTE : If interviewer asks "does distinct remove based on equals?"
        Answer: "Yes — distinct() uses Object.equals() and hashCode().
        For custom objects, override them if needed."
         */

        //Limit to first 5 elements
        List<Integer> first5ElementsInIntegerList = integerList.stream()
                .filter(Objects::nonNull)
                .limit(5)
                .toList();

        System.out.println("Limit to first 5 elements, first5ElementsInIntegerList is "+first5ElementsInIntegerList);

        //Skip first 3 elements
        List<Integer> skippedFirst3ElementsInIntegerList = integerList.stream()
                .filter(Objects::nonNull)
                .skip(3) //skip first 3 elements
                .toList();

        System.out.print("Skipped first 3 elements , skippedFirst3ElementsInIntegerList is "+skippedFirst3ElementsInIntegerList);

        //Group strings by length using Collectors.groupingBy.
        Map<Integer, List<String>> groupingStringsByLength = stringList.stream()
                .filter(s-> Objects.nonNull(s) && !s.isBlank())
                .collect(Collectors.groupingBy(
                   String::length,
                   ()->new TreeMap<>(), //NOTE : use Linked LinkedHashMap::new to preserve insertion order
                   Collectors.toList()
                ));

        System.out.println("Group strings by length using Collectors.groupingBy, groupingStringsByLength is "+groupingStringsByLength);



    }

}
