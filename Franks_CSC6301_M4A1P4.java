import java.util.*;

/**
 * This program prompts users for a string of integers to be placed into a linked list.
 * Users can opt to have a list auto-generated with the number of how many integers 
 * they choose, or manually enter their own list of integers to be sorted from least 
 * to greatest. User can then choose if they want to insert or remove numbers from 
 * the linked list. Error handling is in place for all inputs. The linked list is 
 * printed after each user input to display the indexes of the values. 
 * 
 * @author Donald B. Franks II 
 * @version 1.0.0
 * @since Week 4 of CSC6301
 */
public class Franks_CSC6301_M4A1P4 {
    /**
     * Default constructor for Franks_CSC6301_M4A1P4 class.
     */
    public Franks_CSC6301_M4A1P4() {
    }

     /**
     * The main method serves as the entry point for the program. It interacts with 
     * the user to generate a list of integers and provides options to insert or 
     * remove integers from the list.
     * 
     * @param args the command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a LinkedList object to store the numbers
        LinkedList<Integer> numbers = new LinkedList<>();

        // Ask the user if they want to generate a random string of numbers
        System.out.println("\nDo you want to generate a random string of numbers? (yes/no)");
        // Opens and closes the scanner 
        try (Scanner scanner = new Scanner(System.in)) {
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                int count = 0;
                while (true) {
                    System.out.println("\nHow many numbers do you want to generate?");
                    try {
                        count = Integer.parseInt(scanner.nextLine());
                        break;  
                    } catch (NumberFormatException e) {
                        System.out.println("\nThat's not a valid integer. Please try again.");
                    }
                }
                numbers = generateRandomNumbers(count);
                System.out.println("\nGenerated random numbers: ");
                // Outputs randomly generated numbers twenty values on each line at a time 
                for (int i = 0; i < numbers.size(); i++) {
                    if (i % 20 == 0) {
                        System.out.print("[");
                    }
                    System.out.printf("%d", numbers.get(i));
                    if ((i + 1) % 20 == 0 || i == numbers.size() - 1) {
                        System.out.println("]");
                    } else {
                        System.out.print(", ");
                    }
                }
            } else {
                System.out.println("\nEnter a list of numbers, separated by spaces:");
                String line = scanner.nextLine();
                // Split the line into individual strings
                String[] strings = line.split(" ");
                System.out.println(" ");
                // Try to parse each string as an integer and add it to the list
                for (String s : strings) {
                    try {
                        int number = Integer.parseInt(s);
                        numbers.add(number);
                    } catch (NumberFormatException e) {
                        System.out.println("\"" + s + "\" is not a valid integer. Skipping.");
                    }
                }
            }

            // Sort the list of numbers
            Collections.sort(numbers);

            // Print the sorted list of numbers on one line, separated by commas
            System.out.println("\n\nSorted numbers in a linked list: \n");
            printTable(numbers);
        
            // Ask the user if they want to insert or remove integers from the list
            while (true) {
                System.out.println("\nDo you want to insert or remove integers from the linked list? (insert/remove/no(ends program))");
                response = scanner.nextLine();
                if (response.equalsIgnoreCase("no")) {
                    System.out.println("\nHave a great day!\n");
                    break;
                } else if (response.equalsIgnoreCase("insert")) {
                    System.out.println("\nEnter the number you want to insert:");
                    try {
                        int number = Integer.parseInt(scanner.nextLine());
                        // Find the index where the number should be inserted
                        int index = 0;
                        for (Integer i : numbers) {
                            if (i > number) {
                                break;
                            }
                            index++;
                        }
                        // Add the number to the list at the correct position
                        numbers.add(index, number);
                        System.out.println("\nNumber " + number + " at index " + index + " inserted successfully.");
                        Collections.sort(numbers);
                        System.out.println("\nUpdated sorted numbers in a linked list: \n");
                        printTable(numbers);
                    } catch (NumberFormatException e) {
                        System.out.println("\nThat's not a valid integer. Please try again.");
                    }
                } else if (response.equalsIgnoreCase("remove")) {
                    System.out.println("\nEnter the number you want to remove:");
                    try {
                        int number = Integer.parseInt(scanner.nextLine());
                        int index = numbers.indexOf(number);
                        if (numbers.remove((Integer) number)) {
                            System.out.println("\nNumber " + number + " at index " + index + " removed successfully.");
                        } else {
                            System.out.println("\nNumber " + number + " not found in the linked list.");
                        }
                        System.out.println("\nUpdated sorted numbers in a linked list: \n");
                        printTable(numbers);
                    } catch (NumberFormatException e) {
                        System.out.println("\nThat's not a valid integer. Please try again.");
                    }
                } else {
                    System.out.println("\nInvalid option. Please enter 'insert', 'remove', or 'no'.");
                }
            }
        }
    }

    /**
     * This method generates a specified number of random integers between 0 and 99.
     * 
     * @param count the number of random integers to generate
     * @return a LinkedList of the generated random integers
     */
    public static LinkedList<Integer> generateRandomNumbers(int count) {
        Random rand = new Random();
        LinkedList<Integer> numbers = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(rand.nextInt(100));  // Generate random numbers between 0 and 99
        }
        return numbers;
    }

    /**
     * This method prints the indexes and values of a list in a tabular format.
     * 
     * @param list the list to print
     */
    public static void printTable(List<Integer> list) {
        for (int i = 0; i < list.size(); i += 10) {
            System.out.print("Index: |");
            for (int j = i; j < Math.min(i + 10, list.size()); j++) {
                System.out.printf("%5d |", j);
            }
            System.out.println();
            // Print a line of dashes to separate indexes and values
            System.out.println("-------".repeat(Math.min(10, list.size() - i) + 1));  
            System.out.print("Value: |");
            for (int j = i; j < Math.min(i + 10, list.size()); j++) {
                System.out.printf("%5d |", list.get(j));
            }
            System.out.println("\n");
            System.out.println("\n");
        }
    }
}
