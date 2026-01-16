package app;
import model.Hero;
import model.Lecturer;
import model.HTWRoom;

/**
 * Spezifikation der relevanten Objekten für das Escape Spiel.
 * Diese Klasse steuert das Spiel und kontrolliert den Zustand des Spiels.
 * @author Qassem Ahmad
 */

public class EscapeGame {
    private final Hero hero;
    private final Lecturer[] lecturers = new Lecturer[5];
    private final HTWRoom[] rooms = new HTWRoom[3];
    private boolean gameRunning = true;
    private boolean gameFinished = false;

    /**
     * erstellt ein neues Held.
     */
    public EscapeGame(Hero hero) {
        this.hero = hero;
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
        gameRunning = true;

        while (gameRunning && !gameFinished) {
            showGameMenu();
            String choice = EscapeApp.readUserInput();
            handleGameMenuInput(choice);
            
            System.out.println("===================="); 

        }
        System.out.println("The game has started. Or not?");
    }

    private void showGameMenu() {
        System.out.println("Spielmenu");
        System.out.println("(1) Hochschule erkunden");
        System.out.println("(2) Hero Status anzeigen");
        System.out.println("(3) Laufzettel anzeigen");
        System.out.println("(4) Verschnaufpause machen");
        System.out.println("(5) Spiel verlassen");
        System.out.print("Bitte Zahl zwischen 1 und 5eingeben: ");
    }

    private void handleGameMenuInput(String input) {
        switch (input) {
            case "1":
                System.out.println("XXX");
                break;
            case "2":
                System.out.println("Name: " + hero.getName());
                System.out.println("Lebenspunkte: " + hero.getHealthPoints());
                System.out.println("Erfahrungspunkte: " + hero.getExperiencePoints());
                break;
            case "3":
                System.out.println("XXX");
                break;
            case "4":
                System.out.println("XXX");
                break;
            case "5":
                System.out.println("Zurueck ins Hauptmenu.");
                gameRunning = false;
                break;
            default:
                System.out.println("Unzulaessige Eingabe. Bitte 1 bis 5 eingeben.");
                break;
        }
    }
/**
 * liest den Wert von Hero.
 * @return gibt den Wert zurück.
 */
    public Hero getHero() {
        return hero;
    }
}
