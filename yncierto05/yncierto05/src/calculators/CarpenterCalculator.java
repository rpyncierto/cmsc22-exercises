package calculators;

public class CarpenterCalculator extends BasicCalculator {

    // constructor
    public CarpenterCalculator(String name, String description) {
        super(name, description);
    }

    // methods
    // convert inches to centimeters
    public float inchesToCentimeters(float inches) {
        return super.multiply(inches, 2.54f);
    }

    // convert yard to meters
    public float yardsToMeters(float yards) {
        return super.multiply(yards, 0.9144f);
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
        System.out.println("[5] - Convert Inches to Centimeters");
        System.out.println("[6] - Convert Yards to Meters");
    }
}
