package calculators;

public class ScientificCalculator extends BasicCalculator {

    // constructor
    public ScientificCalculator(String name, String description) {
        super(name, description);
    }

    // exponentiation of 2 numbers
    public float exponentiate(float x, float y) {
        // using the superclass's multiply method to multiply x by itself y times
        float result = 1;
        for (int i = 0; i < y; i++) {
            result = super.multiply(result, x);
        }
        return result;
    }

    // square root of a number
    public float squareRoot(float x) {
        return (float) Math.sqrt(x);
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
        System.out.println("[5] - Exponentiation of 2 numbers");
        System.out.println("[6] - Square root of a number");
    }
}

// x = 2, y = 3
// iteration 0;
    // result = 1;
    // result = 1 * 2 = 2
// iteration 1;
    // result = 2;
    // result = 2 * 2 = 4
// iteration 2;
    // result = 4;
    // result = 4 * 2 = 8
// return result


