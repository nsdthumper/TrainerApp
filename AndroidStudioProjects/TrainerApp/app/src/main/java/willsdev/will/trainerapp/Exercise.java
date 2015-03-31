package willsdev.will.trainerapp;

/**
 * Created by will on 3/12/15.
 */
public class Exercise {
    private String type;
    private String name;
    private int weight = -1; // You entered an incorrect weight. We will find a better way to handle this down the line.
    private int reps = 12;
    private int sets = 2;
    private double breakTime;

    public Exercise(String name, String type, int weight) {
        setName(name);
        setType(type);
        setWeight(weight);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equals("resistance") || type.equals("flexibility") || type.equals("cardio")) {
            this.type = type;
        }
        else {
            System.out.println("something's wrong");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if (weight >= 0) {
            this.weight = weight;
        }
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public double getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(double breakTime) {
        this.breakTime = breakTime;
    }

    @Override
    public String toString() {
        if (type.equals("resistance")){
            return name + ", " + type + ", " + weight + "lbs., " + "Sets: " + sets + ", Reps: " + reps;
        }
        if (type.equals("flexibility")) {
            return name;
        }
        else {
            return "type is wrong;";
        }
    }
}

