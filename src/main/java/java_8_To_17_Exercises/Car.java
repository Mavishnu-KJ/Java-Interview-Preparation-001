package java_8_To_17_Exercises;

//final class - Cannot be extended further
public final class Car extends Vehicle{

    @Override
    public void start(){
        System.out.println("Car starts...");
    }

}
