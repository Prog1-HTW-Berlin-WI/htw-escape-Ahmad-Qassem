package app;
import model.HTWRoom;
import model.Hero;
/**
 * Spezifikation der relevanten Objekten für das Escape Spiel.
 * Diese Klasse steuert das Spiel und kontrolliert den Zustand des Spiels.
 * @author Qassem Ahmad
 */

public class EscapeGame {
    private final Hero hero;
    private final HTWRoom[] rooms = new HTWRoom[3];
    private boolean gameRunning = true;
    private boolean gameFinished = false;

    /**
     * erstellt ein neues Held.
     */
    public EscapeGame() {
        this.hero = new Hero();
    }
/**
 * lässt von außerhalb der Klasse feststellen, ob das Spiel läuft.
 * @return Spielzustand
 */
    public boolean isGameRunning() {
        return gameRunning;
    }
/**
 * lässt Spielzustand von außerhalb der Klasse ändern
 * @param gameRunning, gibt den neuen Spielzustand zurück.
 */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }
/**
 * lässt von außerhalb der Klasse feststellen, ob das Spiel beendet ist.
 * @return gibt den Spielendestatus zurück.
 */
    public boolean isGameFinished() {
        return gameFinished;
    }
/**
 * lässt Spielendestatus von außerhalb der Klasse ändern.
 * @param gameFinished, aktualiesiert den Spielendestatus.
 */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
/**
 * gibt den Beginnstatus auf der Konsole aus.
 */
    public void run() {
        System.out.println("The game has started. Or not?");
    }
/**
 * liest den Wert von Hero.
 * @return gibt den Wert zurück.
 */
    public Hero getHero() {
        return hero;
    }
}
