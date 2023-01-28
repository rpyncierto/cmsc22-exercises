package calculators;

import java.util.Arrays;

public class StatisticCalculator extends BasicCalculator {

    // constructor
    public StatisticCalculator(String name, String description) {
        super(name, description);
    }

    // methods
    // average of 3 numbers
    public float average(float x, float y, float z) {
        // use the superclass's add method to add x, y, and z together using loop
        // use the superclass's divide method to divide the sum by 3
        return super.divide(super.add(super.add(x, y), z), 3);
    }

    // median of 3 numbers
    public float median(float x, float y, float z) {
        // store x,y,z in an array
        float[] numbers = {x, y, z};
        // sort the array
        Arrays.sort(numbers);
        // return the middle number
        return numbers[1];
    }

    // print the menu, override the superclass's print menu method
    @Override
    public void printMenu() {
        System.out.println("===== " + this.name.toUpperCase() + " =====");
        System.out.println("[0] - Back to Main Menu");
        System.out.println("[1] - Add 2 numbers");
        System.out.println("[2] - Subtract 2 numbers");
        System.out.println("[3] - Multiply 2 numbers");
        System.out.println("[4] - Divide 2 numbers");
        System.out.println("[5] - Average of 3 numbers");
        System.out.println("[6] - Median of 3 numbers");
    }
}
