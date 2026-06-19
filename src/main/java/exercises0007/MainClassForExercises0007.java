package exercises0007;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainClassForExercises0007 {

    public static void main(String[] args){

        //System.out.println("test");
        //LTIMindtree
        //Java, Spring Boot, Hibernate
        // arrayformat, Hibernate, Spring Boot, Java
        List<String> stringList = Arrays.asList("Java", "Spring Boot", "Hibernate");

        String[] stringArray = new String[stringList.size()];
        int k = stringList.size();

        for(int i = stringList.size() -1, j=0; i>=0 && j<k; i--, j++){

            if(j<k) {
                stringArray[j] = stringList.get(i);

            }

        }
        System.out.println("String List : "+stringList);
        System.out.println("Reverse order : "+Arrays.toString(stringArray));





    }

}
