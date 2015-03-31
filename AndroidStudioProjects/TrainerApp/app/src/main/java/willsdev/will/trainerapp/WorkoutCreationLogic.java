package willsdev.will.trainerapp;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Will Shriver & Hayden Flinner
 */
public class WorkoutCreationLogic {

    //in the 2D array's,   the postion  zero of every sub-array(?) is the muscle group

    static String resistance[][] = {
            {"CHEST", "dumbbell press", "Push up", "Chest press machine"},
            {"BACK", "Sit and Reach", "Rowing", "Pullups/Lat Pull down"},
            {"SHOULDERS", "Shoulder Press", "Upright Row", "Lateral Raise"},
            {"ARMS", "Curl", "Tricep Extension", "Wrist Curl"},
            {"ABS", "Crunches", "Diagonal Chops", "Bent Knee Sit up"},
            {"UPPER LEGS", "Squats", "Stair Step ups", "Lunges"},
            {"LOWER LEGS", "Calf Raise"}
    };
    static String cardio[] = {"Treadmill", "Eliptical"};

    static String[][] flexibility = {
            {"CHEST","Straight Arms Behind Back","T-stretch"},
            {"BACK","Child's Pose","Spinal Twist"},
            {"SHOULDERS","Arms Across","Straights arms behind back","Seated Lean Back"},
            {"ARMS","Overhead Tricep","Pillar"},
            {"ABS","Prone Trunk Flexor","Side bend with straight arms"},
            {"HIPS","Hip Thigh stretch","Supine","Forward lunge"},
            {"THIGHS","Standing quad stretch","Side quad stretch"},
            {"LEGS","Sitting toe touch","modified Hamstring stretch"},
            {"CALVES","Wall calf stretch","Step stretch"}
    };

    static Client[] clientList = new Client[100]; //i dont remember if arrays are expandable in Java or not, so im not sure how to do with a possibly infinite size. Sorry bro. :shrug:

    static public String getRandomExercise(String type, String muscleGroup) {

        Random rand = new Random();

        if (type.equals("resistance")) {
            for (int i = 0; i < resistance.length; i++) {
                for (int j = 0; j < resistance[1].length; j++) {
                    if(resistance[i][0] == muscleGroup){
                        return resistance[i][(int)rand.nextDouble() * resistance[i].length + 1]; // random excercise, +1 to get it above [0], the muscle group label.
                    }                                                                            //we also discussed having the first position ([1]) being the preffered or default, which could be changes via certain options.
                }
            }
        }
        if (type.equals("flexibility")) { //If you want a Flexibilty excercise...
            for (int i = 0; i < flexibility.length; i++) { // loop through stuff...
                for (int j = 0; j < flexibility[1].length; j++) {
                    if(flexibility[i][0] == muscleGroup){ //find the muscle group that matches...
                        return flexibility[i][(int)rand.nextDouble() * flexibility[i].length + 1]; //and present a random excercise.
                    }
                }
            }
        }
        if (type.equals("cardio")){
            return cardio[0];
        }
        return null;

    }

    static public ResistanceWorkout generateResistanceWorkout(Client client) {

        Scanner scan = new Scanner(System.in);

        ResistanceWorkout temp = new ResistanceWorkout();


        String nameOfChestExercise = getRandomExercise("resistance", "CHEST");
        System.out.println("Input the desired weight for the exercise " + nameOfChestExercise + " for client " + client.getName());
        int weight = Integer.parseInt((scan.nextLine()));
        temp.addExercise(new Exercise(nameOfChestExercise, "resistance", weight));



        String backName = getRandomExercise("resistance", "BACK");
        System.out.println("Input the desired weight for the exercise " + backName + " for client " + client.getName());
        weight = Integer.parseInt(scan.nextLine());
        temp.addExercise(new Exercise(backName, "resistance", weight));


        String shoulderName = getRandomExercise("resistance", "SHOULDERS");
        System.out.println("Input the desired weight for the exercise " + shoulderName + " for client " + client.getName());
        weight = Integer.parseInt(scan.nextLine());
        temp.addExercise(new Exercise(shoulderName, "resistance", weight));



        String armName = getRandomExercise("resistance", "ARMS");
        System.out.println("Input the desired weight for the exercise " + armName + " for client " + client.getName());
        weight = Integer.parseInt(scan.nextLine());
        temp.addExercise(new Exercise(armName, "resistance", weight));


        String absName = getRandomExercise("resistance", "ABS");
        System.out.println("Input the desired weight for the exercise " + absName + " for client " + client.getName());
        weight = Integer.parseInt(scan.nextLine());
        temp.addExercise(new Exercise(absName, "resistance", weight));


        String upperLegsName = getRandomExercise("resistance", "UPPER LEGS");
        System.out.println("Input the desired weight for the exercise " + upperLegsName + " for client " + client.getName());
        weight = Integer.parseInt(scan.nextLine());
        temp.addExercise(new Exercise(upperLegsName, "resistance", weight));


        String lowerLegsName = getRandomExercise("resistance", "LOWER LEGS");
        System.out.println("Input the desired weight for the exercise " + lowerLegsName + " for client " + client.getName());
        weight = Integer.parseInt(scan.nextLine());
        temp.addExercise(new Exercise(lowerLegsName, "resistance", weight));

        return temp;
    }

    static public ResistanceWorkout generateResistanceWorkoutWithoutInput(Client client) {

        ResistanceWorkout temp = new ResistanceWorkout();

        String nameOfChestExercise = getRandomExercise("resistance", "CHEST");
        int weight = 0;
        temp.addExercise(new Exercise(nameOfChestExercise, "resistance", weight));



        String backName = getRandomExercise("resistance", "BACK");

        temp.addExercise(new Exercise(backName, "resistance", weight));


        String shoulderName = getRandomExercise("resistance", "SHOULDERS");

        temp.addExercise(new Exercise(shoulderName, "resistance", weight));



        String armName = getRandomExercise("resistance", "ARMS");

        temp.addExercise(new Exercise(armName, "resistance", weight));


        String absName = getRandomExercise("resistance", "ABS");

        temp.addExercise(new Exercise(absName, "resistance", weight));


        String upperLegsName = getRandomExercise("resistance", "UPPER LEGS");

        temp.addExercise(new Exercise(upperLegsName, "resistance", weight));


        String lowerLegsName = getRandomExercise("resistance", "LOWER LEGS");

        temp.addExercise(new Exercise(lowerLegsName, "resistance", weight));

        return temp;
    }


    static public FlexibilityWorkout generateFlexibilityWorkout(Client client){

        FlexibilityWorkout temp = new FlexibilityWorkout();

        String nameOfChestExercise = getRandomExercise("flexibility", "CHEST");
        System.out.println(nameOfChestExercise);
        temp.addExercise(new Exercise(nameOfChestExercise, "flexibility", 0));
        System.out.println(temp.getExercises()[0].getName());


        String nameOfBackExercise = getRandomExercise("flexibility", "BACK");
        temp.addExercise(new Exercise(nameOfBackExercise, "flexibility", 0));


        String nameOfShouldersExercise = getRandomExercise("flexibility", "SHOULDERS");
        temp.addExercise(new Exercise(nameOfShouldersExercise, "flexibility", 0));

        String nameOfArmsExercise = getRandomExercise("flexibility", "ARMS");
        temp.addExercise(new Exercise(nameOfArmsExercise, "flexibility", 0));

        String nameOfAbsExercise = getRandomExercise("flexibility", "ABS");
        temp.addExercise(new Exercise(nameOfAbsExercise, "flexibility", 1));


        String nameOfHipExercise = getRandomExercise("flexibility", "HIPS");
        temp.addExercise(new Exercise(nameOfHipExercise, "flexibility", 0));


        String nameOfThighExercise = getRandomExercise("flexibility", "THIGHS");
        temp.addExercise(new Exercise(nameOfThighExercise, "flexibility", 0));

        String nameOfLegExercise = getRandomExercise("flexibility", "LEGS");
        temp.addExercise(new Exercise(nameOfLegExercise, "flexibility", 0));

        String nameOfCalvesExercise = getRandomExercise("flexibility", "CALVES");
        temp.addExercise(new Exercise(nameOfCalvesExercise, "flexibility", 0));

        return temp;
    }

    static public TotalWorkoutPlan generateMegaExercise(Client client) {
        ResistanceWorkout resistance = generateResistanceWorkoutWithoutInput(client);
        FlexibilityWorkout flexible = generateFlexibilityWorkout(client);
        String cardio = "Elliptical"; // I don't like treadmills so they will be added later.

        TotalWorkoutPlan temp = new TotalWorkoutPlan(resistance, flexible, cardio);
        System.out.println(temp);
        return temp;
    }

    public void addClient(String name, int age){
        if (age < 0) {
            return;
        }
        Client newClient = new Client(name, age);

        for (int i = 0; i < clientList.length ; i++) {
            if (clientList[i] == null){
                clientList[i] = newClient;
            }
        }
    }

//    public static void main(String[] args) {
//        TraineeApp work = new TraineeApp();
//        System.out.println(work.generateMegaExercise(new Client("Hayden", 13)));
//
//    }

}


