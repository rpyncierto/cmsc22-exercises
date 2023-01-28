/*******************************************************************************************************************************************
 * This is a simple program that uses the concepts of arrays, Strings, and methods to print the array, search for keywords in the array,   *
 * and replace a line or element in the array from a set of arrays.                                                                        *
 *                                                                                                                                         *
 *                                                                                                                                         *
 * @author Reymond P. Yncierto																											   *
 * @created_date 2022-10-6 19:30																										   *
 *																																		   *
 *******************************************************************************************************************************************/

import java.util.Scanner;

public class Yncierto_Exer2 {
	public static void main(String[] args){
        int choice = 0;
        String[] openingLines= { //array of strings
            "It was a dark and stormy night",
			"In a hole in the ground there lived a hobbit",
			"Call me Ishmael",
			"It was a pleasure to burn",
			"I was running away"
        };

        do {
            printMenu(); //print the menu
            Scanner in = new Scanner(System.in); //scanner object
            try {
                choice = in.nextInt(); // read input
            } catch (Exception e) { // catch error (if input is not an integer)
                choice = -1; // assign -1 to choice variable to proceed with the default in switch statement where user will be asked again to input
            };
            in.nextLine();


            switch(choice) { // switch for the choice of the user
                case 1:
                    printOpeningLines(openingLines); // print the array of strings
                    System.out.println();
                    break;
                case 2:
                    String keyword; // declaration
                    do { // repeat until the user input only 1 word
                        System.out.println("\n>> Enter 1 keyword to search");
                        keyword = in.nextLine(); // read input
                    } while (keyword.contains(" ") || keyword.length() == 0); // terminate if input is correct (input does not have a space and input is not empty)
                    int index = searchKeyword(openingLines, keyword); // search the keyword among the array and return the index where the keyword is present
                    if (index == -1) { // means, keyword is not found on the array
                        System.out.println("\n>> No item found with that keyword\n");
                    } else { // keyword is found in the array
                        System.out.println("\n>> Found one item:");
                        System.out.println("(" + (index+1) + ") " + openingLines[index] + "\n"); // print the string where the keyword is present
                        }
                    break;
                case 3:
                    System.out.println("\n>> Replace which line?");
                    printOpeningLines(openingLines); // print the array
                    System.out.println();
                    int line = in.nextInt(); // read input
                    if (line > openingLines.length || line < 1) { // check if line is valid (in the range of the length of the array)
                        do { // if not
                            System.out.println("\n>> Select a line from the list [1-5]");
                            line = in.nextInt(); // read input
                        } while (line > openingLines.length || line < 1); // terminate if input is correct
                    }
                    System.out.println("\n>> What do you want to replace it with?");
                    Scanner string = new Scanner(System.in); // scanner object
                    String replace = string.nextLine(); // read input
                    if (replace.split(" ").length < 3) { // check id replace is composed of 3 words
                        do { // if not
                            System.out.println("\n>> That's not an opening line! Use at least 3 words.");
                            replace = string.nextLine(); // read input
                        } while(replace.split(" ").length < 3); // terminate if input is correct (replace is composed of at least 3 words)
                    }
                    openingLines = replaceLine(openingLines, line, replace); // replace the array with the new string
                    System.out.println("\n>> Replaced line #" + line + "\n");
                    break;
                case 0: // exit
                    System.out.println("\n>> Goodbye!");
                    break;
                default: // if choice is not 1, 2, 3, or 0
                    System.out.println("\n>> Please select a number from the menu\n");
            }
        } while (choice != 0);
    }

    public static void printMenu() { // prints the menu
        System.out.println(
            "===== FAMOUS OPENING LINES =====" +
            "\n[1] Print Opening Lines" +
            "\n[2] Keyword search" +
            "\n[3] Replace a line" +
            "\n[0] Exit" +
            "\n================================\n" +
            "\n>> What do you want to do?"
            );
    }

    public static void printOpeningLines(String[] openingLines) { // prints the array
        System.out.println("\n");
        for (int i=0; i<openingLines.length; i++) { // iterate the array
            System.out.println("(" + (i+1) + ") " + openingLines[i]); // print each element
        }
    }

    public static int searchKeyword(String[] openingLines, String searchKeyword) { // search if the keyword is present in the array
        for (int i=0; i<openingLines.length; i++) { // iterate the array
            if (openingLines[i].toLowerCase().contains(searchKeyword.toLowerCase())) { // check if an element contains the keyword
                return i;
            }
        }
        return -1;
    }
    public static String[] replaceLine(String [] openingLines, int indexToReplace, String newOpeningLine) { // replace the array with the new string
        openingLines[indexToReplace-1] = newOpeningLine; // access the element then assign it to the new string
        return openingLines;
    }
}
