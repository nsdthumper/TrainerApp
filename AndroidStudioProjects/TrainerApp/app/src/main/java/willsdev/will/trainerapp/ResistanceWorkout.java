package willsdev.will.trainerapp;

/**
 * Created by will on 3/12/15.
 */
public class ResistanceWorkout {
    private Exercise[] exercises = new Exercise[25];
    int i = 0;

    public ResistanceWorkout() {
    }


    public Exercise[] getExercises(){
        return exercises;
    }

    public void addExercise(Exercise temp){
        exercises[i] = temp;
        i++;
    }

    @Override
    public String toString() {
        String temp = "";
        for (int j = 0; j < i; j++){
            temp +=  exercises[j] + "\n";
        }
        return temp;
    }
}
