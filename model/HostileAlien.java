package model;

/**
 * Feindliches Alien mit einfachem Angriff.
 */
public class HostileAlien extends Alien {

    public HostileAlien() {
        super("Raumschiff Jaeger", 12, false, "Das Alien faucht und geht auf dich los.");
    }

    @Override
    public int attack() {
        return 3 + (int) (Math.random() * 7);
    }
}
