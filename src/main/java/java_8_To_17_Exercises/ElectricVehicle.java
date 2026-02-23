package java_8_To_17_Exercises;

//non-sealed class - Can be extended further
public non-sealed class ElectricVehicle extends Vehicle{
    @Override
    public void start(){
        System.out.println("Electric vehicle starts...");
    }
}
