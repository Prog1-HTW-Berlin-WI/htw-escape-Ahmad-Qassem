package model;
import java.io.Serializable;

/**
 * @author Qassem Ahmad
 */
public class Hero implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 3578735620108186013L;

    private String name;
    private int healthPoints = 50;
    private int experiencePoints = 0;
    private final Lecturer[] signedExerciseLeaders = new Lecturer[5];

    public Hero() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void takeDamage(int amount) {
        if (healthPoints > 0) {
            healthPoints -= amount;
            if (healthPoints < 0) {
                healthPoints = 0;
            }
        }
    }

    public boolean isOperational() {
        return healthPoints > 0;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public int addExperiencePoints(int points) {
        this.experiencePoints += points;
        return this.experiencePoints;
    }

    

}