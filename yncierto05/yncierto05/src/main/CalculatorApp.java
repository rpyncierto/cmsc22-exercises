/*********************************************************************************************************************************************************
 * This program is an implementation of different calculators namely (1) Scientific Calculator, (2) Statistics Calculator, and (3) Carpenter Calculator. *
 * Each calculator is capable of performing basic arithmetics and having their own operations. User can choose the type of calculator they want and what *
 * operation they want to use.                                                                                                                           *
 *                                                                                                                                                       *
 * @author Reymond P. Yncierto                                                                                                                           *
 * @created 2022-12-11 19:27                                                                                                                             *
 * @finished 2022-12-23 16:34                                                                                                                            *
 *********************************************************************************************************************************************************/

package main;

import calculators.*;
import java.util.Scanner;

public class CalculatorApp {

    public final static String SCICAL = "Scientific Calculator";
    public final static String STATCAL = "Statistics Calculator";
    public final static String CARPCAL = "Carpenter Calculator";

    public final static String SCICAL_DESC = "Basic arithmetic, exponentiation, and square root";
    public final static String STATCAL_DESC = "Basic arithmetic, average, and median";
    public final static String CARPCAL_DESC = "Basic arithmetic, convert inches to centimeters, convert yards to meters";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;
        int method;
        float input1;
        float input2;
        float input3;

        // TODO: Instantiate the different Calculators
        // instantiate the scientific calculator
        ScientificCalculator sciCal = new ScientificCalculator(SCICAL, SCICAL_DESC);
        // instantiate the statistic calculator
        StatisticCalculator statCal = new StatisticCalculator(STATCAL, STATCAL_DESC);
        // instantiate the carpenter calculator
        CarpenterCalculator carpCal = new CarpenterCalculator(CARPCAL, CARPCAL_DESC);

        // TODO: Create a menu for the user to select one of the calculators
        // print the menu until choice = 0;
        do {
            System.out.println();
            printCalculatorMenu();
            choice = getPrefferedMethod(scanner, "menu");
            /*  TODO:
                Print the submenu of operations for a calculator. Do this by overriding the BasicCalculator print method.
                Ask the user for the appropriate number of inputs
                Pass the inputs to the appropriate Calculator method.
                Print the returned result.
            */
            switch (choice) {
                case 0: // exit is chosen
                    System.out.println("Thank you for using this program!");
                    scanner.close();
                    break;
                case 1: // scientific calculator is chosen
                    sciCal.printMenu();
                    method = getPrefferedMethod(scanner, "subMenu");
                    if (method == 0) { // back to main menu is chosen
                        break;
                    }
                    input1 = getInput(scanner, 1); // get user input
                    switch(method) {
                        case 1: // addition
                            System.out.println();
                            input2 = getInput(scanner, 2); // get user input
                            System.out.println(input1 + " + " + input2 + " = " + sciCal.add(input1, input2));
                            break;
                        case 2: // subtraction
                            System.out.println();
                            input2 = getInput(scanner, 2); // get user input
                            System.out.println(input1 + " - " + input2 + " = " + sciCal.subtract(input1, input2));
                            break;
                        case 3: // multiplication
                            System.out.println();
                            input2 = getInput(scanner, 2); // get user input
                            System.out.println(input1 + " * " + input2 + " = " + sciCal.multiply(input1, input2));
                            break;
                        case 4: // division
                            System.out.println();
                            input2 = getInput(scanner, 2); // get user input
                            System.out.println(input1 + " / " + input2 + " = " + sciCal.divide(input1, input2));
                            break;
                        case 5: // exponentiation
                            System.out.println();
                            input2 = getInput(scanner, 2); // get user input
                            System.out.println(input1 + " raised to " + input2 + " = " + sciCal.exponentiate(input1, input2));
                            break;
                        case 6: // square root
                            System.out.println("Square root of " + input1 + " = " + sciCal.squareRoot(input1));
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }

                    break;
                case 2: // statistics calculator is chosen
                    statCal.printMenu();
                    method = getPrefferedMethod(scanner, "subMenu");
                    if (method == 0) { // back to main menu is chosen
                        break;
                    }
                    input1 = getInput(scanner, 1); // get user input
                    System.out.println();
                    input2 = getInput(scanner, 2); // get user input
                    switch(method) {
                        case 1: // addition
                            System.out.println(input1 + " + " + input2 + " = " + statCal.add(input1, input2));
                            break;
                        case 2: // subtraction
                            System.out.println(input1 + " - " + input2 + " = " + statCal.subtract(input1, input2));
                            break;
                        case 3: // multiplication
                            System.out.println(input1 + " * " + input2 + " = " + statCal.multiply(input1, input2));
                            break;
                        case 4: // division
                            System.out.println(input1 + " / " + input2 + " = " + statCal.divide(input1, input2));
                            break;
                        case 5: // average of 3 numbers
                            System.out.println();
                            input3 = getInput(scanner, 3); // get user input
                            System.out.println("Average of " + input1 + ", " + input2 + ", and " + input3 + " is " + statCal.average(input1, input2, input3));
                            break;
                        case 6: // median of 3 numbers
                            System.out.println();
                            input3 = getInput(scanner, 3); // get user input
                            System.out.println("Median of " + input1 + ", " + input2 + ", and " + input3 + " is " + statCal.median(input1, input2, input3));
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;
                case 3: // carpenter calculator is chosen
                    carpCal.printMenu();
                    method = getPrefferedMethod(scanner, "subMenu");
                    if (method == 0) { // back to main menu is chosen
                        break;
                    }
                    input1 = getInput(scanner, 1); // get user input
                    switch(method) {
                        case 1: // addition
                            System.out.println();
                            input2 = getInput(scanner, 2); // get user input
                            System.out.println(input1 + " + " + input2 + " = " + carpCal.add(input1, input2));
                            break;
                        case 2: // subtraction
                            System.out.println();
                            input2 = getInput(scanner, 2); // get user input
                            System.out.println(input1 + " - " + input2 + " = " + carpCal.subtract(input1, input2));
                            break;
                        case 3: // multiplication
                            System.out.println();
                            input2 = getInput(scanner, 2); // get user input
                            System.out.println(input1 + " * " + input2 + " = " + carpCal.multiply(input1, input2));
                            break;
                        case 4: // division
                            System.out.println();
                            input2 = getInput(scanner, 2); // get user input
                            System.out.println(input1 + " / " + input2 + " = " + carpCal.divide(input1, input2));
                            break;
                        case 5: // inches to centimeters
                            System.out.println(input1 + " inches is " + carpCal.inchesToCentimeters(input1) + " centimeters");
                            break;
                        case 6: // yards to meters
                            System.out.println(input1 + " yards is " + carpCal.yardsToMeters(input1) + " meters");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
    }

    // print menu method
    public static void printCalculatorMenu() {
        System.out.println("======= MENU =======");
        System.out.println("[0] - Exit");
        System.out.println("[1] - " + SCICAL + ": " + SCICAL_DESC);
        System.out.println("[2] - " + STATCAL + ": " + STATCAL_DESC);
        System.out.println("[3] - " + CARPCAL + ": " + CARPCAL_DESC);
    }

    // method to get the preffered calculator/method of the user
    public static int getPrefferedMethod(Scanner scanner, String type) {
        // loops until user input is valid
        while (true) {
            switch(type) {
                case "menu": // get calculator type
                    try {
                        System.out.print("\n>> What do you want to do?\n");
                        int choice = scanner.nextInt();
                        // check if choice is in the range of 0-3
                        if (choice >= 0 && choice <= 3) {
                            System.out.println();
                            return choice;
                        } else {
                            System.out.println(">> Please input a valid choice");
                        }
                    } catch (Exception e) {
                        System.out.println(">> Please input a valid choice");
                        scanner.nextLine();
                    }
                    break;
                case "subMenu": // get method type
                    try {
                        System.out.print("\n>> What do you want to do?\n");
                        int choice = scanner.nextInt();
                        // check if choice is in the range of 0-6
                        if (choice >= 0 && choice <= 6) {
                            System.out.println();
                            return choice;
                        } else {
                            System.out.println(">> Please input a valid choice");
                        }
                    } catch (Exception e) {
                        System.out.println(">> Please input a valid choice");
                        scanner.nextLine();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    // get the input from the user
    public static float getInput(Scanner scanner, int i) {
        // loop until the user inputs a valid input
        while (true) {
            try {
                System.out.print(">> Please enter input # " + i + "\n");
                float input = scanner.nextFloat();
                return input;
            } catch (Exception e) {
                System.out.println();
                scanner.next();
            }
        }
    }
}
