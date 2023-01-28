/*******************************************************************************************************************************************
 * This is a simple program that outputs the minimum/maximum, and even/odd count of N user integer inputs which are all stored in an array *
 *																																		   *
 * @author Reymond P. Yncierto																											   *
 * @created_date 2022-09-24 16:00																										   *
 *																																		   *
 *******************************************************************************************************************************************/

package mypackage;
import java.util.Scanner; // import scanner class

public class Yncierto_Exer1 {
	public static void main(String[] args) {
		int choice; // declare choice variable
		final int N = 5; // declare constant N variable initialized to 5
		System.out.println("Please enter 5 numbers.");
	    System.out.println(); // outputs empty space
	    int[] inputs = new int[N]; // creates an array of N integers
	    Scanner input = new Scanner(System.in);
	    for (int i = 0; i < inputs.length; i++) { // iterate the array
	        try {
	            System.out.print("Enter a number: ");
	            inputs[i] = input.nextInt(); // read user input and store it in the array
	        } catch (Exception e) { // catch the exception if the input is not an integer
	            System.out.println("Oops! That's not an integer! :(");
	            input.next(); // clear the input
	            i--; // decrement i to repeat the iteration
	        }
	    }
	    System.out.println();
	    System.out.println("Inputs are:");
	    for (int i = 0; i < inputs.length; i++) { // iterate through the array
	        System.out.print(inputs[i]+"\t"); // output the array separated by tab
	    }
	    System.out.println();
	    do { // to ensure that the menu will run atleast once
	        System.out.println();
	        // menu
	        System.out.println("Menu-------------------");
	        System.out.println("[1] Get Minimum Value \n[2] Get Maximum Value \n[3] Count Even and Odd \n[0] Exit");
	        System.out.print("Choice: ");
	        choice = input.nextInt(); // read user input and store it in choice variable
	        switch(choice) {
	            case 1: // get the minimum value from the array
	                int min = inputs[0]; // declare min variable and assign the first element of the array to it
	                for (int i = 1; i < inputs.length; i++) { // iterate through the array
	                    if (inputs[i] < min) { // compare each element to get the minimum
	                        min = inputs[i]; // assign the current element to min
	                    }
	                }
	                System.out.println("Minimum value is " + min + "."); // outputs the minimum element in the array
	                break;
	            case 2: // get the maximum value from the array
	                int max = inputs[0]; // declare max variable and assign the first element of the array to it
	                for (int i = 1; i < inputs.length; i++) { // iterate through the array
	                    if (inputs[i] > max) { // compare each element to get the minimum
	                        max = inputs[i]; // assign the current element to max
	                    }
	                }
	                System.out.println("Maximum value is " + max + ".") ; // outputs the maximum element in the array
	                break;
	            case 3: // count the number of even and odd values in the array
	                int even = 0, odd = 0; // declare even and odd variable and initialized them to 0
	                for (int i = 0; i < inputs.length; i++) { // iterate through the array
	                    if (inputs[i] % 2 == 0) { // check if the current element is even
	                        even++; // increment even variable
	                    } else {
	                        odd++; // increment odd variable
	                    }
	                }
	                System.out.println("Odd numbers: " + odd); // outputs the number of odd numbers in the array
	                System.out.println("Even numbers: " + even); // outputs the number of even numbers in the array
	                break;
	            case 0: // exit the program
	                System.out.println("\n\nProgram Exits...Bye!");
	                break;
	            default:
	                System.out.println("Please enter a valid choice.");
	        }
	    } while (choice != 0); // repeat the menu until user enters 0
	}
}
