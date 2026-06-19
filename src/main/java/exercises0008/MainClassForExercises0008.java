package exercises0008;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainClassForExercises0008 {

    public static record Employee(
            int id,
            String name,
            String department,
            Double salary
    ){

    }

    public static record Item(
            String itemName
    ){

    }

    public static record Order(
            List<Item> itemList
    ){

    }

    public static record Transaction(
            String transactionId,
            Double amount
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


        //Second highest paid employee



        /*
        Frequency Count: Given a string,
        find the frequency of each character, and filter out the top 3 most frequent elements using Streams.
        */
        String str1 = "aaabbbbbbcccdddd";

        /*
        Frequency Count: Given a list of words,
        find the frequency of each word, and filter out the top 3 most frequent elements using Streams.
        */

        List<String> wordList = Arrays.asList(
                "banana", "banana", "apple", "orange", "banana",
                "banana", "orange", "banana", "apple", "orange"
        );

        /*
        Flattening Nested Collections: Given a list of Order objects, where each order contains a list of Item objects,
        extract a distinct, sorted list of all item names across all orders using flatMap.
        */

        List<Order> orderList = List.of(
                new Order(List.of(new Item("Keyboard"), new Item("Mouse"))),
                new Order(List.of(new Item("Laptop"), new Item("Bag"))),
                new Order(List.of(new Item("Keyboard"), new Item("Mobile"))),
                new Order(List.of(new Item("Tab"), new Item("Mouse"))),
                new Order(List.of(new Item("Pen")))
        );



        /*
        Partitioning Data: Given a list of transactions,
        partition them into two groups (e.g., transactions above ₹50,000 and below) and
        calculate the average transaction amount for each group simultaneously.
        */

        List<Transaction> transactionList = List.of(
                new Transaction("10", 80000.0),
                new Transaction("18", 60000.0),
                new Transaction("7", 70000.0),
                new Transaction("3", 40000.0),
                new Transaction("1", 45000.0),
                new Transaction("15", 20000.0)
        );


        /*
        Custom String Joining: Given a list of strings, filter out empty strings,
        convert them to uppercase, and join them with a comma separator enclosed in square brackets [A, B, C].
        */

        List<String> stringList = Arrays.asList(
                "Sachin", "Shewag", "Gambhir", "Virat", "", "Yuvraj", null, "Dhoni", "   ", "Raina"
        );

        /*
        Data Aggregation: Given a collection of complex Employee,
        use Streams to sort them dynamically by department name first, and then by salary descending.
        */


        /*
        Find the number which is repeated the same number of times
        Given List : [12,13,2,2,3,5,4,4,4,4], Can you guess the output?
        Output {2,4}
        */

        List<Integer> integerList = List.of(12,13,2,2,3,5,4,4,4,4);


        /*
        Return the consecutive two numbers 2 peer. Output will be List<List>
        Given list [1,2,4,5,7,8,9], Output {{1,2}, {4,5}, {7,8}, {8,9}}
        */

        List<Integer> integerList1 = List.of(1,2,4,5,7,8,9);


        /*
        Print non-repeating first character. Consider any String. your wish.
        */

        String s1 = "aabbcddxxyyza";

        /*
        Binary Grouping: Take a number in binary form and count the groups of isolated 0s individually before a 1 appears
        (e.g., Input: 0011000 → Output: groups of size 2 and 3).
        */

        /*
        Time Complexity Explanation:
        O(n) — String split and stream operations process each character linearly.
        Space Complexity Explanation:
        O(n) — For storing the resulting list of group sizes.
        */

        String binaryNumber = "0011000";


        /*
        Merge Overlapping Intervals: Given a collection of intervals (e.g., [1,3], [2,6], [8,10]),
        merge all overlapping intervals to output [1,6], [8,10]. (Tests sorting and custom collection logic).
        */

        int[][] intervals = {{1,3}, {2,6}, {8,10}, {15,18}, {9,11}};





    }

}
