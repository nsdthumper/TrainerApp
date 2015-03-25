package willsdev.will.trainerapp;

/**
 * Created by will on 3/12/15.
 */
public class TotalWorkoutPlan {
    private ResistanceWorkout resist;
    private FlexibilityWorkout flex;
    private String cardio;

    public TotalWorkoutPlan(ResistanceWorkout resist, FlexibilityWorkout flex, String cardio) {
        this.resist = resist;
        this.flex = flex;
        this.cardio = cardio;
    }

    public ResistanceWorkout getResist() {
        return resist;
    }

    public void setResist(ResistanceWorkout resist) {
        this.resist = resist;
    }

    public FlexibilityWorkout getFlex() {
        return flex;
    }

    public void setFlex(FlexibilityWorkout flex) {
        this.flex = flex;
    }

    public String getCardio() {
        return cardio;
    }

    public void setCardio(String cardio) {
        this.cardio = cardio;
    }

    @Override
    public String toString() {
        return resist.toString() + "\n" + flex.toString() + "\n" + cardio;
    }
}
