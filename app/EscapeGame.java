package app;
import model.FriendlyAlien;
import model.HTWRoom;
import model.Hero;
import model.HostileAlien;
import model.Lecturer;

/**
 * Spezifikation der relevanten Objekten für das Escape Spiel.
 * Diese Klasse steuert das Spiel und kontrolliert den Zustand des Spiels.
 * @author Qassem Ahmad
 */

public class EscapeGame {
    private final Hero hero;
    private Lecturer[] lecturer = new Lecturer[6];
    private final HTWRoom[] rooms = new HTWRoom[20];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    private FriendlyAlien friendly = new FriendlyAlien();
    private HostileAlien hostile = new HostileAlien();
  

    /**
     * erstellt ein neues Held.
     */
    public EscapeGame(Hero hero) {
        this.hero = hero;
        this.lecturer[0] = new Lecturer("Fr.");
        this.lecturer[1] = new Lecturer("Fr.");
        this.lecturer[2] = new Lecturer("Herr.");
        this.lecturer[3] = new Lecturer("Herr.");
        this.lecturer[4] = new Lecturer("Fr.");
        this.lecturer[5] = new Lecturer("Fr. Dr. Majunke");
        this.rooms[0] = new HTWRoom("A000","dies und das",lecturer[0]);
        this.rooms[1] = new HTWRoom("A000","dies und das",lecturer[1]);
        this.rooms[2] = new HTWRoom("A000","dies und das",lecturer[2]);
        this.rooms[3] = new HTWRoom("A000","dies und das",lecturer[3]);
        this.rooms[4] = new HTWRoom("A000","dies und das",lecturer[4]);
        this.rooms[5] = new HTWRoom("A000","dies und das",lecturer[5]);
        this.rooms[6] = new HTWRoom("A000","dies und das",null);
        this.rooms[7] = new HTWRoom("A000","dies und das",null);
        this.rooms[8] = new HTWRoom("A000","dies und das",null);
        this.rooms[9] = new HTWRoom("A000","dies und das",null);
        this.rooms[10] = new HTWRoom("A000","dies und das",null);
        this.rooms[11] = new HTWRoom("A000","dies und das",null);
        this.rooms[12] = new HTWRoom("A000","dies und das",null);
        this.rooms[13] = new HTWRoom("A000","dies und das",null);
        this.rooms[14] = new HTWRoom("A000","dies und das",null);
        this.rooms[15] = new HTWRoom("A000","dies und das",null);
        this.rooms[16] = new HTWRoom("A000","dies und das",null);
        this.rooms[17] = new HTWRoom("A000","dies und das",null);
        this.rooms[18] = new HTWRoom("A000","dies und das",null);
        this.rooms[19] = new HTWRoom("A000","dies und das",null);
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
        System.out.print("Bitte Zahl zwischen 1 und 5 eingeben: ");
    }

    private void handleGameMenuInput(String input) {
        switch (input) {
            case "1":
                htwErkunden();
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

    private void htwErkunden() {

        double chance = Math.random();
        double alienTyp = Math.random();
 
        if (chance < 0.2) {
            for (int i = 6; i < rooms.length; i++){
                if (rooms[i].getVisited() == false) {
                    System.out.println("Du betritts " + rooms[i].getIdentifier()+ ", " + rooms[i].getDescription());
                    System.out.println();
                    System.out.println("Der Raum ist leer.");
                    System.out.println();
                    System.out.println("Die Ruhe macht dich unruhig, aber du muss weiter erkunden...");
                    System.out.println();
                    rooms[i].beenVisited();
                    hero.increaseGameRound();
                    return;
                } 
            }
           
        }
        else if (chance < 0.52) {
            for (int i = 6; i < rooms.length; i++){

                if (rooms[i].getVisited() == false) {
                    System.out.println();
                    System.out.println("Du betritts " + rooms[i].getIdentifier()+ ", " + rooms[i].getDescription() + ", aber...");
                    System.out.println();
                    System.out.println("Ein lebendes Wesen!");
                    System.out.println();

                    if (alienTyp < 0.4) {       // Wahrscheinlichkeit beliebig festsetzen.
                        
                        System.out.println("Es scheint friedlich zu sein ");
                        System.out.println();
                        System.out.println(friendly.getName() + ": " + friendly.getGreeting());
                    }
                      
                    else {    
                        
                        System.out.println("VORSICHT!! Es scheint aggresive zu sein!! ");
                        System.out.println();
                        System.out.println(hostile.getName() + ": " + hostile.getGreeting());
                        System.out.println(hostile.getName() + " has " + hostile.getLifePoints() + " HP");
                        System.out.println();
                    }

                rooms[i].beenVisited();
                hero.increaseGameRound();
                return;
                } 
            }
        }
        else {  for (int i = 4; i >= 0; i--){

                if (!rooms[i].lecturerHasSigned()) { 
                    System.out.println();
                    System.out.println("Du betritts " + rooms[i].getIdentifier()+ ", " + rooms[i].getDescription());
                    System.out.println();
                    System.out.println(rooms[i].getLecturer() + " ist bereit zu unterschreiben");
                    System.out.println();
                    hero.increaseGameRound();
                    rooms[i].makeLecturerSign();
                    return;
                }
            }
            System.out.println();
            System.out.println("### Alle DozentenInnen haben bereits unterschrieben. Du kannst jetzt Dr. Majunke besuchen ###");
            System.out.println();
            System.out.println("Ein Alien sieht dich und kommt auf dich zu!");
            System.out.println();
            if (alienTyp < 0.4) {       // Wahrscheinlichkeit beliebig festsetzen.
                        System.out.println("Es scheint friedlich zu sein ");
                        System.out.println();
                        System.out.println(friendly.getName() + ": " + friendly.getGreeting());
                    }
                      
                    else {    
                        System.out.println("VORSICHT!! Es scheint aggresive zu sein!! ");
                        System.out.println();
                        System.out.println(hostile.getName() + ": " + hostile.getGreeting());
                        System.out.println();
                        System.out.println(hostile.getName() + " has " + hostile.getLifePoints() + " HP");
                    }
        }
    }
}
