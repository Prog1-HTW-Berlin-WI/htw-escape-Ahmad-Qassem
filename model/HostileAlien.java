package model;

/**
 * Feindliches Alien.
 * Hat einen zufälligen Angriff.
 *
 * @author Ahmad Qassem
 * @author Luca Jan Relic
 */
public class HostileAlien extends Alien {

    public HostileAlien() {
        super("Raumschiff Jaeger", 12, false, "Das Alien faucht und geht auf dich los.");
    }

    /**
     * Feindliches Alien macht zufälligen Schaden.
     *
     * @return Schaden 
     */
    @Override
    public int attack() {
        return 3 + (int) (Math.random() * 7);
    }
}
