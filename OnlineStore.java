/* Tudor Chiribes

Advanced Programming CA 2

NCI 2018 */

import javax.swing.*;
import java.io.*;

// Create interface for Clothing that will define an abstract method for price
interface Clothing {
    double price();
}

// Main class for the GUI
public class OnlineStore {

    public static void main(String[] args) {

        // Declare and instantiate new clothing factory
        ClothingFactory myFactory = new ClothingFactory();

        // Declare a new object reference variable for a file writer
        FileWriter writeToFile;

        // Declare variables for user option and cart items/price
        int userOption;
        String userChoice = "";
        double subTotal = 0;
        int order[] = new int[3];

        // Provide options for GUI
        String[] options = new String[] {"Add Hoodie", "Remove Hoodie", "Add Jacket",
                "Remove Jacket", "Add T-Shirt", "Remove T-Shirt"};

        do {

            // Get user option using JOptionDialog
            userOption = JOptionPane.showOptionDialog(null,
                    "Please select the items you wish to purchase: ", "Chiribes Online Store",
                    0, JOptionPane.INFORMATION_MESSAGE, null, options, null);

            // Print user option for debugging
            //System.out.println("User selection: " + userOption);

            // Create string from userOption and add item to cart
            if (userOption == 0) {
                userChoice = "Hoodie";
                order[0]++;
            }
            else if (userOption == 2) {
                userChoice = "Jacket";
                order[1]++;
            }
            else if (userOption == 4) {
                userChoice = "TShirt";
                order[2]++;
            }

            // Create temporary instance of chosen item and add it to cart
            if (userOption == 0 || userOption == 2 || userOption == 4) {
                Clothing addingToCart = myFactory.getClothing(userChoice);
                subTotal += addingToCart.price();
            }

            // Remove item from cart and deduct price from subtotal
            if (userOption == 1 && order[0] > 0) {
                subTotal -= 12D;
                order[0]--;     // Check if cart contains item
            }
            else if (userOption == 3 && order[1] > 0) {
                subTotal -= 22.5D;
                order[1]--;     // Check if cart contains item
            }
            else if (userOption == 5 && order[2] > 0) {
                subTotal -= 7.5D;
                order[2]--;     // Check if cart contains item
            }

            // Display subtotal and cart contents to user
            switch (userOption) {
                case 0:
                    JOptionPane.showMessageDialog(null,
                            "Hoodie added to cart!\n\nItems:\n\nHoodies: " +
                            order[0] + "\nJackets: " + order[1] + "\nT-Shirts: " + order[2] +
                            "\n\nSubtotal: " + subTotal);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null,
                            "Hoodie removed from cart!\n\nItems:\n\nHoodies: " +
                                    order[0] + "\nJackets: " + order[1] + "\nT-Shirts: " + order[2] +
                                    "\n\nSubtotal: " + subTotal);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null,
                            "Jacket added to cart!\n\nItems:\n\nHoodies: " +
                                    order[0] + "\nJackets: " + order[1] + "\nT-Shirts: " + order[2] +
                                    "\n\nSubtotal: " + subTotal);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null,
                            "Jacket removed from cart!\n\nItems:\n\nHoodies: " +
                                    order[0] + "\nJackets: " + order[1] + "\nT-Shirts: " + order[2] +
                                    "\n\nSubtotal: " + subTotal);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null,
                            "T-Shirt added to cart!\n\nItems:\n\nHoodies: " +
                                    order[0] + "\nJackets: " + order[1] + "\nT-Shirts: " + order[2] +
                                    "\n\nSubtotal: " + subTotal);
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null,
                            "T-Shirt removed from cart!\n\nItems:\n\nHoodies: " +
                                    order[0] + "\nJackets: " + order[1] + "\nT-Shirts: " + order[2] +
                                    "\n\nSubtotal: " + subTotal);
                    break;
                default:
                    //System.out.println("Terminating application!");
                    break;
            }

        } while (userOption != -1);

        // Display final order details
        JOptionPane.showMessageDialog(null, "Order placed!\n\nItems:\n\nHoodies: " +
                order[0] + "\nJackets: " + order[1] + "\nT-Shirts: " + order[2] +
                "\n\nSubtotal: " + subTotal);

        // Print correct output to console
        System.out.printf("You have purchased %d hoodies %d jackets and %d tshirts\n", order[0], order[1], order[2]);

        // Write order to file
        try {
            // Instantiate file writer
            writeToFile = new FileWriter(new File("order.txt"));

            // Specify string to be written to file
            writeToFile.write(String.format("Items: \n\nHoodies: %d \nJackets: %d \nT-Shirts: %d \n\nSubtotal: %.2f",
                    order[0], order[1], order[2], subTotal));

            // Close file writer
            writeToFile.close();
        }

        catch (IOException writeException) {

            System.out.println("Exception writing to file...");
        }

        System.out.println("Order successfully stored");

        JOptionPane.showMessageDialog(null, "Thank you, come again!");
    }
}

// Class which contains the factory method
class ClothingFactory {

    // Define factory method
    public Clothing getClothing(String userInput) {

        // Check for null or empty input
        if (userInput == null || userInput.equals("")) {

            return null;
        }

        if (userInput.equalsIgnoreCase("hoodie")) {

            return new Hoodie();
        }

        if (userInput.equalsIgnoreCase("jacket")) {

            return new Jacket();
        }

        if (userInput.equalsIgnoreCase("tshirt")) {

            return new TShirt();
        }

        // Return null for no match
        return null;
    }
}

// Concrete class for Hoodie which implements the abstract method for price
class Hoodie implements Clothing {

    // Implement abstract method
    @Override
    public double price() {
        return 12D;
    }
}

// Concrete class for Jacket which implements the abstract method for price
class Jacket implements Clothing {

    // Implement abstract method
    @Override
    public double price() {
        return 22.5D;
    }
}

// Concrete class for T-Shirt which implements the abstract method for price
class TShirt implements Clothing {

    // Implement abstract method
    @Override
    public double price() {
        return 7.5D;
    }
}
