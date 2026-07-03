package exercises0010;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MainClassForExercises0010 {

    public static void main(String[] args){

        //System.out.println("TEST MainClassForExercises0010");

        //Welcome To Programming
/*
        String inputString = "Welcome To Programming";

        Map<Character, Long> freqMap = inputString.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> Character.isLetterOrDigit(c))
                .collect(Collectors.groupingBy(
                        c -> c,
                        LinkedHashMap:: new,
                        Collectors.counting()
                )).entrySet().stream()
                .sorted(Comparator.comparingLong(Map.Entry :: getValue))
                        .collect(Collectors.groupingBy(
                                        Map.Entry :: getKey,
                                        Map.Entry :: getValue

                                ));




        freqMap.forEach((character, freq) -> {
          System.out.println(character+" repeated "+freq+" times");
        });

*/



    }

}
