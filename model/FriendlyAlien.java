package model;

/**
 * Friedliches Alien.
 * Greift nicht an und macht 0 Schaden.
 *
 * @author Ahmad Qassem
 * @author Luca Jan Relic
 */
public class FriendlyAlien extends Alien {
    /**
     * Erstellt ein festes friedliches Alien.
     * Dieses Alien greift nicht an.
     */
    public FriendlyAlien() {
        super("Sternen Schnecke", 8, true, "Das Alien winkt dir freundlich zu.");
    }
    /**
     * Friedliches Alien macht keinen Schaden.
     *
     * @return 0
     */
    @Override
    public int attack() {
        return 0;
    }
}

