package exercises0009;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainClassForExercises0008 {

    public static void main(String[] args){
        //System.out.println("Test MainClassForExercises0008");

        //aaadddffffhhhhdddddaaagggg
        //output outputString a3d3f3h4d4a3g4

        String inputString = "aaadddffffhhhhdddddaaagggg";

        Map<Character, Integer> freqMap = inputString.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                )).entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry :: getKey,
                        entry -> entry.getValue().intValue()
                ));

        StringBuilder outputString = new StringBuilder();
        freqMap.forEach((c, frequency) -> {
            outputString.append(c).append(frequency);

        });
        System.out.print("inputString : "+inputString);
        System.out.println("\n outputString : "+outputString);

        /*

        this is my life -> siht si my efil

        */

        String sentence = "this is my life";

        List<String> stringList  = Arrays.stream(sentence.split(" "))
                .filter(s ->
                    !s.isEmpty()
                )
                .map(s -> {
                    if(s.contains("a") || s.contains("e") || s.contains("i") || s.contains("o") || s.contains("u")){
                        char[] tempArray = s.toCharArray();
                        //return reversed String
                        return s;
                    }else{
                        return s;
                    }
                })
                .toList();

        System.out.println(stringList);









    }

}
