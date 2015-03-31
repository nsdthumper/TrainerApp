
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author labuser
 */
/**
 * 
 * The class with the info, setters and getters for all the pertinent information about "the dog"
 */
public class Dog {

    private String breed = "Unknown";
    private String sex = "Unknown";
    private double age = 0.1;
    private boolean fixed = false;
    private boolean housebroken = false;
    
/**
 * Standard constructor, sets the variables to their stock values.
 */
    public Dog() {
        breed = "Unknown";
        sex = "Unknown";
        age = 0.1;
        fixed = false;
        housebroken = false;
    }
/**
 * Constructor with parameters, checks for their relevancy, and sets them to 
 * default if they are incorrect 
 * @param breed String
 * @param sex String
 * @param age Double
 * @param fixed Boolean
 * @param housebroken Boolean
 */
    public Dog(String breed, String sex, double age, boolean fixed, boolean housebroken){
        this.breed = breed;
        this.sex = sex;
        this.age = age;
        this.fixed = fixed; 
        this.housebroken = housebroken; 
        if(!sex.equals("F") || !sex.equals("M")){
            this.sex = "Undefined";
            System.out.println("{*}Error in sex entry, set to default.");
        }
        if(age <= 0 || age >=30){
           this.age = 0;
            System.out.println("{*}Error in age entry, set to defualt.");
        }
        if(fixed != true && fixed != false){
            this.fixed = false; 
            System.out.println("{*}Error in fixed entry, set to defualt.");
        }
        if(housebroken != true && housebroken != false){
            System.out.println("{*} Error in housebroken entry, set to default");
        }
    } 

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public void setHousebroken(boolean housebroken) {
        this.housebroken = housebroken;
    }

    public String getBreed() {
        return breed;
    }

    public String getSex() {
        return sex;
    }

    public double getAge() {
        return age;
    }

    public boolean getFixed() {
        return fixed;
    }

    public boolean getHousebroken() {
        return housebroken;
    }
/**
 * Function to set the values of the instance of Dog, it uses JOptionPane to ensure they are correct values. 
 */
    public void setValues() {
        Scanner scanner = new Scanner(System.in);
        JOptionPane pane = new JOptionPane();
        
        //System.out.print("Breed:");
        //breed = scanner.nextLine();
        breed = JOptionPane.showInputDialog("Please input breed").toUpperCase();
        
       //System.out.print("Sex:");
       String[] buttons = {"F", "M"}; 
         int genderInput = JOptionPane.showOptionDialog(null, "Male or Female", "Confirmation",
        JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[0]);
         switch (genderInput){
             case 0: sex = "F";
                 break;
             case 1: sex = "M";
                 break;
         }
         
        
        String ageIn = JOptionPane.showInputDialog("Input age");
        try{
        age = Double.parseDouble(ageIn);
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }
        String[] TrueFalseButtons = {"True","False"};
        int houseBrokenIn = JOptionPane.showOptionDialog(null, "Housebroken?", "Confirmation",
        JOptionPane.WARNING_MESSAGE, 0, null, TrueFalseButtons, buttons[0]);        
        switch (houseBrokenIn){
            case 0: housebroken = true; 
                break;
            case 1: housebroken = false;
                break;
        }        
        int isfixed = JOptionPane.showOptionDialog(null, "Fixed?", "Confirmation",
        JOptionPane.WARNING_MESSAGE, 0, null, TrueFalseButtons, TrueFalseButtons[0]);
        switch (isfixed){
            case 0: fixed = true;
                break;
            case 1: fixed = false;
                break;
        }
        
    }
    /**
     * Prints out the contents of Dog, not the class value.
     * @return string 
     */
    public String toString() {
        String string = new String();
        string = string + "Breed: " + breed + "\nSex: " + sex + "\nAge: " + age + "\nNeutere/Spayed: " + fixed + "\nHousebroken: " + housebroken;
        return string;
    }
}
