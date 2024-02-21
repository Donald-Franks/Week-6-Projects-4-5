package franks_csc6301_m5a1p5;

import java.util.*;

/**
 * This program prompts users for a string of integers to be placed into a Stack.
 * Users can opt to have a list auto-generated with the number of how many integers 
 * they choose, or manually enter their own list of integers to be sorted from least 
 * to greatest. User can then choose if they want to push or pop numbers from the 
 * Stack. Error handling is in place for all inputs. The Stack is printed after each 
 * user input to display the indexes of the values. 
 * 
 * @author Donald B. Franks II 
 * @version 1.0.0 (modified Franks_CSC6301_M4A1P4)
 * @since Week 5 of CSC6301
 */
public class Franks_CSC6301_M5A1P5 {
    /**
     * Default constructor for Franks_CSC6301_M5A1P5 class.
     */
    public Franks_CSC6301_M5A1P5() {
    }

     /**
     * The main method serves as the entry point for the program. It interacts with 
     * the user to generate a list of integers and provides options to push or 
     * pop integers from the list.
     * 
     * @param args the command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create two Stack objects to store the numbers
        Stack<Integer> unsortedNumbers = new Stack<>();
        Stack<Integer> sortedNumbers = new Stack<>();
        
        // Ask the user if they want to generate a random string of numbers
        System.out.println("\nDo you want to generate a random string of numbers? (yes/no): ");
        // Opens and closes the scanner 
        try (Scanner scanner = new Scanner(System.in)) {
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                int count = 0;
                while (true) {
                    System.out.println("\nHow many numbers do you want to generate?: ");
                    try {
                        count = Integer.parseInt(scanner.nextLine());
                        break;  
                    } catch (NumberFormatException e) {
                        System.out.println("\nThat's not a valid integer. Please try again.");
                    }
                }
                unsortedNumbers = generateRandomNumbers(count); 
                sortedNumbers = new Stack<>();
                for (Integer num : unsortedNumbers) {
                    sortedNumbers.push(num);
                }
                Collections.sort(sortedNumbers);
                System.out.println("\nGenerated random numbers: ");
                // Outputs randomly generated numbers twenty values on each line at a time 
                for (int i = 0; i < unsortedNumbers.size(); i++) {
                    if (i % 20 == 0) {
                        System.out.print("[");
                    }
                    System.out.printf("%d", unsortedNumbers.get(i));
                    if ((i + 1) % 20 == 0 || i == unsortedNumbers.size() - 1) {
                        System.out.println("]");
                    } else {
                        System.out.print(", ");
                    }
                }
                System.out.println(""); 
            } else {
                System.out.println("\nEnter a list of numbers, separated by spaces: ");
                String line = scanner.nextLine();
                // Split the line into individual strings
                String[] strings = line.split(" ");
                System.out.println(" ");
                // Try to parse each string as an integer and add it to the list
                for (String s : strings) {
                    try {
                        int number = Integer.parseInt(s);
                        unsortedNumbers.push(number);
                        sortedNumbers.push(number);
                    } catch (NumberFormatException e) {
                        System.out.println("\"" + s + "\" is not a valid integer. Skipping.");
                    }
                }
            }

            // Sort the list of numbers
            Collections.sort(sortedNumbers);

            // Print the unsorted stack of numbers
            printUnsortedTable(unsortedNumbers);
            // Print the sorted stack of numbers
            printTable(sortedNumbers);
        
            // Ask the user if they want to push or pop integers from the stack 
            while (true) {
                System.out.println("\nDo you want to push or pop integers from the stack? (push/pop/no(ends program)): ");
                response = scanner.nextLine();
                if (response.equalsIgnoreCase("no")) {
                    System.out.println("\nHave a great day!\n");
                    printSeparator(); 
                    break;
                } else if (response.equalsIgnoreCase("push")) {
                    System.out.println("\nEnter the number you want to push:");
                    try {
                        int number = Integer.parseInt(scanner.nextLine());
                        unsortedNumbers.push(number);
                        // Add the number to the stack at the correct position
                        sortedNumbers.push(number);
                        Collections.sort(sortedNumbers);
                        System.out.println("\nNumber " + number + " pushed successfully!");
                        printUnsortedTable(unsortedNumbers);
                        printTable(sortedNumbers);
                    } catch (NumberFormatException e) {
                        System.out.println("\nThat's not a valid integer. Please try again.");
                    }
                } else if (response.equalsIgnoreCase("pop")) {
                    System.out.println("\nHow many numbers do you want to pop off?: ");
                    try {
                        int count = Integer.parseInt(scanner.nextLine());
                        for (int i = 0; i < count; i++) {
                            if (unsortedNumbers.isEmpty()) {
                                break;
                            }
                            int number = unsortedNumbers.pop();
                            sortedNumbers.remove((Integer) number);
                            Collections.sort(sortedNumbers);
                            System.out.println("\nNumber " + number + " popped off successfully!");
                        }
                        printUnsortedTable(unsortedNumbers);
                        printTable(sortedNumbers);
                    } catch (NumberFormatException e) {
                        System.out.println("\nThat's not a valid integer. Please try again.");
                    }
                } else {
                    System.out.println("\nInvalid option. Please enter 'push', 'pop', or 'no'.");
                }
            }
        }
    }

    /**
     * This method generates a specified number of random integers between 0 and 99.
     * 
     * @param count the number of random integers to generate
     * @return a Stack of the generated random integers
     */
    public static Stack<Integer> generateRandomNumbers(int count) {
        Random rand = new Random();
        Stack<Integer> numbers = new Stack<>();
        for (int i = 0; i < count; i++) {
            numbers.push(rand.nextInt(100));  // Generate random numbers between 0 and 99
        }
        return numbers;
    }

    /**
     * This method prints an 80 character line of dashes (-) as segment separators. 
     */
    public static void printSeparator() {
        System.out.println("-".repeat(80));
    }

    /**
     * This method prints the indexes and values of the unsorted stack in a tabular format.
     * 
     * @param list the list to print
     */
    public static void printUnsortedTable(Stack<Integer> list) {
        printSeparator(); 
        if (list.isEmpty()) {
            System.out.println("\nThe stack is empty.");
        } else {
            System.out.println("\nCurrent state of the unsorted stack:\n");
            for (int i = 0; i < list.size(); i += 10) {
                System.out.print("Index: |");
                for (int j = i; j < Math.min(i + 10, list.size()); j++) {
                    System.out.printf("%5d |", j);
                }
                System.out.println();
                System.out.println("-------".repeat(Math.min(10, list.size() - i) + 1));  
                System.out.print("Value: |");
                for (int j = i; j < Math.min(i + 10, list.size()); j++) {
                    System.out.printf("%5d |", list.get(j));
                    }
                    System.out.println("\n");
            
                }
            
            }
        }

    /**
     * This method prints the indexes and values of the sorted stack in a tabular format.
     * 
     * @param list the list to print
     */
    public static void printTable(Stack<Integer> list) {
        // If either stack is empty, both are empty. This check is so the index/values don't
        // show up and to not repeat the message in the printUnsortedTable method above. 
        if (list.isEmpty()) {
            System.out.println(" ");
            printSeparator();
        } else {    
            // Print the sorted stack
            System.out.println("\nCurrent state of the sorted stack:\n");
            for (int i = 0; i < list.size(); i += 10) {
                System.out.print("Index: |");
                for (int j = i; j < Math.min(i + 10, list.size()); j++) {
                    System.out.printf("%5d |", j);
                }
                System.out.println();
                System.out.println("-------".repeat(Math.min(10, list.size() - i) + 1));  
                System.out.print("Value: |");
                for (int j = i; j < Math.min(i + 10, list.size()); j++) {
                    System.out.printf("%5d |", list.get(j));
                    }
                    System.out.println("\n");
            }
            printSeparator();
        }
    }
}