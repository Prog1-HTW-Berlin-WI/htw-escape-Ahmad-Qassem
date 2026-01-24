package model;
import java.io.Serializable;
/**
 * Basisklasse fuer alle Aliens.
 * Ein Alien hat Namen, Lebenspunkte, einen Gruss und ist entweder friedlich oder feindlich.
 * @author Qassem Ahmad
 */

public abstract class Alien implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 1729389822767173584L;
    private final String name;
    private int lifePoints;
    private final boolean friendly;
    private final String greeting;

    public Alien(String name, int lifePoints, boolean friendly, String greeting) {
        this.name = name;
        this.lifePoints = Math.max(0, lifePoints);
        this.friendly = friendly;
        this.greeting = greeting;
    }

    /**
     * Zieht Lebenspunkte ab.
     * Unter 0 geht nicht.
     *
     * @param amount Schaden
     */
    public void takeDamage(int amount) {
        int damage = Math.max(0, amount);
        lifePoints = Math.max(0, lifePoints - damage);
        System.out.println(name + " erleidet " + damage + " Schaden. Restliche LP: " + lifePoints);
    }

    /**
     * @return true, wenn das Alien keine Lebenspunkte mehr hat
     */
    public boolean isDefeated() {
        return lifePoints <= 0;
    }

    public abstract int attack();

    public String getName() {
        return name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public boolean isFriendly() {
        return friendly;
    }

    public String getGreeting() {
        return greeting;
    }
}
