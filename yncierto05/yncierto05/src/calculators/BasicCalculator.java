package calculators;

public class BasicCalculator {
  
  public String name;
  public String description;

  public BasicCalculator(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public float add(float x, float y) {
    return x + y;
  }

  public float subtract(float x, float y) {
    return x - y;
  }

  public float multiply(float x, float y) {
    return x * y;
  }

  public float divide(float x, float y) {
    return x / y;
  }

  public void printMenu() {
    System.out.println("===== " + this.name + " MENU =====");
    System.out.println("[1] - Add 2 numbers");
    System.out.println("[2] - Subtract 2 numbers");
    System.out.println("[3] - Multiply 2 numbers");
    System.out.println("[4] - Divide 2 numbers");
  }
  
}
