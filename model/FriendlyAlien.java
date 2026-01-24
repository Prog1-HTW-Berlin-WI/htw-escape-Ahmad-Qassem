package model;

/**
 * Friedliches Alien.
 * Greift nicht an und macht 0 Schaden.
 *
 * @author Ahmad Qassem
 * @author Luca Jan Relic
 */
public class FriendlyAlien extends Alien {

    public FriendlyAlien() {
        super("Sternen Schnecke", 8, true, "Das Alien winkt dir freundlich zu.");
    }

    @Override
    public int attack() {
        return 0;
    }
}

