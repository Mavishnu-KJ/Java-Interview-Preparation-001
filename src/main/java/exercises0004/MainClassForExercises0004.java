package exercises0004;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainClassForExercises0004 {
    public static void main(String[] args){

        /*
         Take an array containing mixed integers, Objects, and nested arrays
         Example : {{1}, 2, {3, 4}, 5, {6, {7, 8}}}
         and flatten it into a single-level stream.
        */

        Object[] inputArrayWithMixedObjectsArraysIntegers = {
            new int[] {1},
            2,
            new int[] {3,4},
            5,
            new Object[]{6, new int[] {7,8}}
        };

        System.out.println("inputArrayWithMixedObjectsArraysIntegers : "+Arrays.toString(inputArrayWithMixedObjectsArraysIntegers));

        int[] flattenedArray = flatten(inputArrayWithMixedObjectsArraysIntegers);
        System.out.println("flattenedArray : "+Arrays.toString(flattenedArray));




    }

    public static int[] flatten(Object[] nested){

        return Arrays.stream(nested)
                .flatMapToInt(item ->{
                    if(item instanceof int[] arr){
                        return Arrays.stream(arr);
                    }else if(item instanceof Integer integer){
                        return IntStream.of(integer);
                    }else if(item instanceof Object[] objectArray){
                        // Convert int[] returned by recursion into IntStream
                        return Arrays.stream(flatten(objectArray));
                    }
                    return IntStream.empty();
                })
                .toArray();

    }

}
