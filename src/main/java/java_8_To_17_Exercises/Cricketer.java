package java_8_To_17_Exercises;

public record Cricketer(String name, int centuries) {

    //static field (constant)
    public static final int HIGHEST_CENTURIES = 100;

    //Compact constructor for validation
    public Cricketer{
        if(centuries < 0){
            throw new IllegalArgumentException("Centuries cannot be negative");
        }
    }

    //instance method
    public boolean isHighestCenturiesScorer(){
        return centuries == HIGHEST_CENTURIES;
    }

    //1. Can't we use instance method as setter method ?
    /*
    => We cannot use instance method as setter method
    => Records are immutable — their components (fields) are final and cannot be changed after creation.
    => So, Records can have getter methods only, cannot have setter methods
    public void setSalary(int newSalary){
        this.centuries = newSalary; // Compilation error : Cannot assign a value to final variable 'centuries'
    }
    */

    //2. Can't we pass input parameters to the instance method?
    /*
    => We can pass input parameters to the instance method
    => Instance methods in records can have any number of parameters, just like in traditional classes.
    */
    public boolean isLowestCenturiesScorer(int thresold){
        return centuries < thresold;
    }

    //3. Can't we use instance method as normal method that we use in traditional class?
    /*

    Instance methods in records work almost identically to instance methods in normal classes:

    => They have access to this
    => They can access the record components (name(), centuries())
    => They can have parameters
    => They can return any type
    => They can throw exceptions
    => if the record implements an interface with default methods, those can be overridden in the record itself
    => Methods from Object (toString, equals, hashCode) can also be overridden in the record.

    */

    //static method (factory)
    public static Cricketer unCappedCricketer(String name){
        return new Cricketer(name, 0);
    }

    //Pattern matching instanceof with String, Integer, Employee.


}
