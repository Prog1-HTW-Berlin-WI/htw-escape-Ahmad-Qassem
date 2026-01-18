package model;
import java.io.Serializable;

/**
 * @author Qassem Ahmad
 */
public class Hero implements Serializable {

    //private static final int Lecturer = 5;
    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 3578735620108186013L;
    private static final int MAX_HP = 50;
    private String name;
    private int healthPoints;
    private int experiencePoints;
    private Lecturer[] signedExerciseLeaders;
    private int gameRound;
    
    public Hero(String name) {
        this.name = name;
        this.healthPoints = MAX_HP;
        this.experiencePoints = 0;
        this.signedExerciseLeaders = new Lecturer[5];
        this.gameRound = 1;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void increaseGameRound() {
        this.gameRound = Math.min(24, gameRound + 1);  
    }

    public int getGameRound() {
        return gameRound;
    }

    public void takeDamage(int amount) {
        if (healthPoints > 0) {
            this.healthPoints = Math.max(0, healthPoints - amount);
            }
    }

    public boolean isOperational() {
        return healthPoints > 0;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void addExperiencePoints(int points) {
        this.experiencePoints += points;
    }
    

    public void regenerate (boolean longRest) { //longrest ist noch nicht implementiert
        if (!longRest) {
            this.healthPoints = Math.min(50, healthPoints + 3);
        }
        else { this.healthPoints = Math.min(50, healthPoints + 10);
        increaseGameRound();
        }
    }

    public boolean flee() {
        return Math.random() < 0.42;
    }

    public int attack() {
        double grundSchaden = experiencePoints * 2.3 + 1;
        double wahrscheinlichkeit = Math.random(); // damit die Wahrscheinlichkeiten sich nicht überlappen.
        if (wahrscheinlichkeit < 0.13 ) {
            grundSchaden *= 0;
        } else if (wahrscheinlichkeit < 0.12) {
            grundSchaden *= 2;
        }
        return (int) grundSchaden;
    }
    
    public void signExerciseLeader (Lecturer lecturer) {
        if (lecturer.hasSigned() == false) {
            for (int i = 0; i < 5; i++) {
                if(signedExerciseLeaders[i] == null) {  
                    signedExerciseLeaders[i] = lecturer;
                    lecturer.sign();
                    return;
                }
            }
        }
    }

}