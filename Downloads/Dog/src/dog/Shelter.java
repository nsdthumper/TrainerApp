
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Implementation of Dog class, uses JOptionPane to give a menu, with Adopt, Surrender, donate, and close options.
 * @author labuser
 */
public class Shelter {

    private Dog[] Kennels = new Dog[12];
    private double take = 0;
    private double puppyPrice = 100;
    private double adultPrice = 65;
    private double seniorPrice = 40;
    private String filename = "/home/will/Downloads/Dog/src/dog/currentDogs.csv"; // **COPY AND PASTE THE LOCATION OF YOUR currentdogs.csv FILE IN HERE SO THE PROGRAM RUNS CORRECTLY**

/**
 * Basic constructor, loads the instances of dog with Commas separated values from the file given.
 */
    public Shelter() {

        String[] info = new String[5]; // this is where the dog info is stored, since the positions of info should always be known, other wise an exception will be thrown
        int num = 0;
        try {
            File file = new File(this.filename);
            file.getAbsolutePath();
        } catch (Exception ex) {
            System.out.println("Error in reading file" + ex.toString());
        }
        try {
            File infile = new File(this.filename);
            Scanner input = new Scanner(infile);

            //loop through the CSV lines, parse them for the comma's, assign them to the info array, then assign the slots to the instance of dog class in Kennels
            while (input.hasNext() != false) {
                String x = input.nextLine();
                info = x.split(",");

                Dog dog = new Dog();
                dog.setBreed(info[0]);
                dog.setSex(info[1]);
                dog.setAge(Double.parseDouble(info[2]));
                dog.setFixed(Boolean.parseBoolean(info[3]));
                dog.setHousebroken(Boolean.parseBoolean(info[4]));

                this.Kennels[num] = dog;
                num++;
            }

        } catch (Exception ex) {
            System.out.println("Error in the order of the comma seperated values:" + ex.toString());
        }
    }
/**
 * Used to test the Shelter() constructor by printing out at the contents of the array with a for loop. 
 */
    public void testKennelLoading() {
        for (int i = 0; i < this.Kennels.length; i++) {
            if (this.Kennels[i] != null) {
                System.out.println(this.Kennels[i].toString());
                System.out.println("------------------");
            }
        }
    }
/**
 * adopting a dog, prints out the dogs from the array, then uses JOptionPane to get their choice, based on numbers and their spots in the array, 
 * then sets the array spot to Null and adds the appropriate amount to the Take variable.
 */
    public void adoptADog() {
        System.out.println("{*}Here are the current dogs, Enter -1 if you change your mind:");
        System.out.println("-------------------");
        for (int i = 0; i < this.Kennels.length; i++) {
            if (this.Kennels[i] != null) {
                System.out.println("{" + i + "}" + this.Kennels[i].toString());
                System.out.println("-------------------");
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        //System.out.println("Which dog would you like to adopt?");
        String x = JOptionPane.showInputDialog("Which dog yould you like to adopt?");
        int choice = Integer.parseInt(x);
        if (choice == -1) {
            return;
        } else {
            System.out.println("You have chosen:");
            System.out.println(this.Kennels[choice]);
            System.out.println("-------------------");
            double dogAge = this.Kennels[choice].getAge();
            if (dogAge <= 1) {
                JOptionPane.showMessageDialog(null, "That will be $100");
                //System.out.println("That will be $100");
                this.take += 100;
            }
            if (dogAge > 1 && dogAge <= 10) {
                JOptionPane.showMessageDialog(null, "That will be $60");
                //System.out.println("That will be $60");
                this.take += 60;
            }
            if (dogAge > 10) {
                JOptionPane.showMessageDialog(null, "That will be $40");
                //System.out.println("That will be $40");
                this.take += 40;
            }
            this.Kennels[choice] = null;
        }
    }
/**
 * This is the old menu, before i implemented the JOptionPane :
 * loops through the text based menu until the user enter 4, which starts the closeForDay() function. 
 * 
 *  
 */
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcometo Fake Stuff inc. Dog shelter Management System(D.S.M.S.)! enter 1 - 3 for stuff to happen, or 4 to quit!");
        System.out.println("1. Adopt a dog");
        System.out.println("2. Surrender a dog");
        System.out.println("3. Make a donation");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        while (true) {
            if (choice == 1) {
                adoptADog();
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
            } else if (choice == 2) {
                surrenderDog();
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
            } else if (choice == 3) {
                makeDonation();
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
            } else if (choice == 4) {
                closeForDay();
                break;
            }
        }
    }
/**
 * The current menu; uses JOptionPane to get their choice, then launches the current function, all in a while loop until they choose "Close for a day".  
 */
    public void menu2() {
        String[] menuButtons = {"Adopt", "Surrender", "Donate", "Close for the day"};
        boolean x  = true; 
        while (x == true) {
            int choice = JOptionPane.showOptionDialog(null, "Welcome to Dog Shelter Managment System (D.S.M.S)! choose stuff from the things below", "Confirmation",
                    JOptionPane.WARNING_MESSAGE, 0, null, menuButtons, menuButtons[0]);
            switch (choice) {
                case 0:
                    adoptADog();
                    break;
                case 1:
                    surrenderDog();
                    break;
                case 2:
                    makeDonation();
                    break;
                case 3:
                    closeForDay();
                    x = false; 
                    break;
                     
            }
        }
    }
/**
 * uses a for loop and if statements to check for an empty (null) array spot, 
 * then uses Set values from the Dog class to fill that spot. 
 */
    public void surrenderDog() {
        for (int i = 0; i < this.Kennels.length; i++) {
            if (this.Kennels[i] == null) {
                JOptionPane.showMessageDialog(null, "Found empty Kennel in slot " + i);
                Dog dog = new Dog();
                dog.setValues();
                this.Kennels[i] = dog;
                break;
            }
        }
    }
/**
 * ends the program; calculates the profit for the day with Take and the number of dogs times 8.
 */
    public void closeForDay() {
        System.out.println("-----Closing for the day-----");
        System.out.println("Total take for the is:" + this.take);
        //calculate cost of dog upkeep 'n stuff
        int count = 0;
        for (int i = 0; i < this.Kennels.length; i++) {
            if (this.Kennels[i] != null) {
                count++;
            }
        }
        String output = "take for the day: "+take+"\nCost of feeding dogs: "+(count*8)+"\nProfit for the day was: "+(take - (count * 8)); 
        JOptionPane.showMessageDialog(null, output);
        //System.out.println("Cost of feeding dogs today - " + (count * 8));
        //System.out.println("Profit for today - " + (take - (count * 8)));
        writeToFile();

    }
/**
 * Part of the "Closing for the day" series, writes the contents of the instances of Dogs in the "Current dogs" array to a CSV file using the getters for each private variable.  
 */
    public void writeToFile() {
        String currentDogs = "/Users/labuser/NetBeansProjects/Dog/src/dog/currentDogs.csv";
        try {
            PrintWriter writer = new PrintWriter(filename);

            for (int i = 0; i < this.Kennels.length; i++) {
                if (this.Kennels[i] != null) {
                    writer.print(this.Kennels[i].getBreed() + ",");
                    writer.print(this.Kennels[i].getSex() + ",");
                    writer.print(this.Kennels[i].getAge() + ",");
                    writer.print(this.Kennels[i].getFixed() + ",");
                    writer.print(this.Kennels[i].getHousebroken() + ",");
                    writer.println("");
                }
            }
            writer.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
/**
 * Making a donation; just does take += x, where x is a parsed integer from a JOptionPane. 
 */
    public void makeDonation() {
        System.out.println("How much would you like to donate?");
        String x = JOptionPane.showInputDialog("How much would you like to donate?");
        this.take += Integer.parseInt(x);
         
    }

    public static void main(String[] args) {
        Shelter shelter = new Shelter();
        //shelter.testKennelLoading();
        shelter.menu2();

    }
}
