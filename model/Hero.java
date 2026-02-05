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

    /**
     * Erstellt einen neuen Hero.
     *
     * @param name Startname des Heros
     */
    public Hero(String name) {
        this.name = name;
        this.healthPoints = MAX_HP;
        this.experiencePoints = 0;
        this.signedExerciseLeaders = new Lecturer[5];
        this.gameRound = 0;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getDamage() {
        double damage = experiencePoints * 2.3 + 1;
        return (int) damage;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
    /**
     * erhöht den Gameround +1 solange er nicht 24 ist.
     * Math.min wählt den kleineren int aus den Klammern.
     */
    public void increaseGameRound() {
        this.gameRound = Math.min(24, gameRound + 1);  
    }

    public int getGameRound() {
        return gameRound;
    }
    /**
     * verringert die Lebenspunkte.
     * Math.max wählt den grßeren int aus den Klammern.
     * @param amount Wert der empfangenen Schaden
     */
    public void takeDamage(int amount) {
        if (healthPoints > 0) {
            this.healthPoints = Math.max(0, healthPoints - amount);
            System.out.println(name + " erleidet " + amount + " Schaden. Restliche LP: " + healthPoints);
            }
    }
    /**
     * @return true, wenn der Hero noch Lebenspunkte hat
     */
    public boolean isOperational() {
        return healthPoints > 0;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }
    /**
     * erhöht die Erfahrungspunkte 
     * @param points Wert der aufzusummierende Punkte 
     */
    public void addExperiencePoints(int points) {
        this.experiencePoints += points;
    }
    
    /**
     * @param  longRest soll angeben um welcher Verschnaufpause handelt es. bei false werden die Lebenspunkte
     * um +3 erhöht.
     * bei true um +10 und Gameround wird mit der Methode um +1 erhöht.
     * Math.min stellt sicher dass der int 50 nicht überschritten wird.
     */
    public void regenerate (boolean longRest) { 
        if (!longRest) {
            this.healthPoints = Math.min(50, healthPoints + 3);
        }
        else { this.healthPoints = Math.min(50, healthPoints + 10);
        increaseGameRound();
        }
    }
    /**
     * prüft den Erfolg des Fluchtsversuchs des Heros.
     * @return gibt wahr bei eine wahrscheinlichkeit von 42% zurück und somit gilt der Flucht gelungen.
     * bei false ist der Flucht nicht gelungen 58%.
     */
    public boolean flee() {
        return Math.random() < 0.42;
    }
    /**
     * rechnet den Angriffschaden des Heros.
     * die Formel der Grundschaden ist der Wert des Doubles.
     * bei einer wahrscheinlichkeit von 13% liegt der Schaden bei 0.
     * bei einer Wahrscheinlichkeit von 12% doppelt sich der Schaden.
     * @return gibt den umgewandelten int wert der Grundschaden zurück
     */
    public int attack() {
        double grundSchaden = experiencePoints * 2.3 + 1;
        double wahrscheinlichkeit = Math.random(); 
        if (wahrscheinlichkeit < 0.13 ) {
            grundSchaden *= 0;
        } else if (wahrscheinlichkeit < 0.25) { 
            grundSchaden *= 2;
        }
        return (int) grundSchaden;
    }
    /**
     * listet lecturer ,die unterschrieben haben, in  einem Array auf
     * prüft wo die nächste freie Stelle im Array ist
     * wenn lecturer noch nicht unterschrieben hat, wird lecturer eingetragen
     * ändert den boolean von hasSigned auf true um
     */
    public void signExerciseLeader (Lecturer lecturer) {
            for (int i = 0; i < 5; i++) {
                if(signedExerciseLeaders[i] == null && lecturer.isReadyToSign()) {  //isReadyToSign kann eig weg?
                    signedExerciseLeaders[i] = lecturer;
                    lecturer.sign();
                    return;
                }
            //}
        }
    }
    /**
     * ruft die list der lecturer, die unterschrieben haben, auf
     * druckt den auf der Konsole aus
     * rechnet die fehlenden Unterschriften
     * druckt gesonderte Nachricht wenn alle Unterschriften gesammelt wurden
     */
    public void getLaufzettel() {
     int fehlendeUnterschriften = 5;
        for (Lecturer i : signedExerciseLeaders) {
            if(i != null) {  
                    System.out.println("# " + i.getName());
                    fehlendeUnterschriften -= 1;
            }   
                
        }
     System.out.println();
     System.out.println("Um Professorin Majuntke finden zu können, brauchst du noch: " + fehlendeUnterschriften + " Unterschriften");
     if (fehlendeUnterschriften == 0) { 
        System.out.println("### Alle DozentenInnen haben bereits unterschrieben. Du kannst jetzt Dr. Majunke besuchen ###");
     }
    }
    /**
     * prüft ob alle lecturer unterschrieben haben
     * sucht nach einem Element mit einem null Wert
     * @return false wenn nicht alle lecturer unterschriebn haben. Ansonsten true.
     */
    public boolean hasAllSignatures() {
    for (int i = 0; i < signedExerciseLeaders.length; i++) {
        if (signedExerciseLeaders[i] == null) {
            return false;
        }
        }
    return true;
    }

}