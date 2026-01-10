package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


/**
 * Einstiegspunkt der Konsolenanwendung für das Escape Spiel.
 * Diese Klasse zeigt das Hauptmenü an, liest Eingaben ein und startet Aktionen wie Spielstart oder Laden und Speichern.
 * @author Luca Relic
 */
public class EscapeApp {

    public static final String SAVE_FILE_NAME = "save";
    private EscapeGame game;
    private static boolean gameRunning = true;

/**
 * Startet die Anwendung und führt die Menü aus.
 * 
 * @param args args Kommandozeilenargumente, werden nicht verwendet
 */
    public static void main(String[] args) {
        System.out.println("Welcome to the HTW escape");
        System.out.println("========================================\n");

        EscapeApp app = new EscapeApp();
        app.runApp();

        System.out.println("Thank you for playing. Goodbye!");
    }

    private void runApp() {
        while (gameRunning) {
            showMainMenu();
            String choice = readUserInput();
            handleUserInput(choice);
            System.out.println("====================");
        }
    }

    /**
     * Gibt das Hauptmenü in der Konsole aus.
     */
    private void showMainMenu() {
        System.out.println("You're in the main menu");
        System.out.println("What do you want to do next?");
        System.out.println("(1) Start new game");

        if (game != null && !game.isGameFinished()) {
            System.out.println("(2) Spiel fortsetzen");
            System.out.println("(4) Spiel speichern");
        }

        if (hasSavedGame()) {
            System.out.println("(3) Spiel laden");
            System.out.println("(5) Spiel löschen");
        }

        System.out.println("(6) Quit");
        System.out.println("");
        System.out.println("Please choose a number between 1 and 6: ");
    }

    /**
     * Liest eine Zeile von der Konsole ein.
     *
     * @return die vom Nutzereingegebene Zeichenkette
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        // TBD
        return userInput;
    }

    /**
     * Verarbeitet die Nutzereingabe aus dem Menü und führt die passende Aktion aus.
     * 
     * @param input die Menüauswahl als Zeichenkette
     */
    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                this.startGame();
                break;
            case "2":
                if (game != null && !game.isGameFinished()) {
                    resumeGame();
                } else {
                    System.out.println("Invalid input. Please choose a correct number between 1 and 6");
                }
                break;
            case "3":
                if (hasSavedGame()) {
                    loadGame();
                } else {
                    System.out.println("Invalid input. Please choose a correct number between 1 and 6");
                }
                break;
            
            case "4":
                if (game != null && !game.isGameFinished()) {
                    saveGame();
                } else {
                    System.out.println("Invalid input.");
                }
                break;


            case "5":
                if (hasSavedGame()) {
                    deleteGame();
                } else {
                    System.out.println("Invalid input.");
                }
                break;

            case "6":
                gameRunning = false;
                break;
            default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 6");
                break;
        }
    }

    /**
     * Erstellt ein neues Spielobjekt und startet anschließend das Spiel.
     */
    private void startGame() {
        this.game = new EscapeGame();
        resumeGame();
    }

    /**
     * Setzt das aktuelle Spiel auf den Status laufend und ruft anschließend die Spiellogik auf.
     * Diese Methode setzt voraus, dass ein Spielobjekt existiert.
     */
    private void resumeGame() {
        this.game.setGameRunning(true);
        this.game.run();
    }

    /**
     * Löscht die Speicherdatei, falls sie existiert.
     */
    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            System.out.println("Game deleted!");
        }
    }

    /**
     * Speichert das aktuelle Spiel mittels Serialisierung in eine Datei.
     * Bei Fehlern wird eine Fehlermeldung ausgegeben.
     */
    private void saveGame() {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
            oos.flush();
        } catch (Exception ex) {
            System.err.println("Something went wrong while saving the game: " + ex.getMessage());
            return;
        }
        System.out.println("Game saved!");
    }

    /**
     * Lädt einen gespeicherten Spielstand aus der Speicherdatei und setzt ihn als aktuelles Spiel.
     * Bei Fehlern wird eine Fehlermeldung ausgegeben.
     */
    private void loadGame() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.game = (EscapeGame) ois.readObject();
            System.out.println("Game loaded!");
        } catch (Exception ex) {
            System.err.println("Something went wrong while loading the game: " + ex.getMessage());
        }
    }

    /**
     * Prüft, ob aktuell ein Spielobjekt vorhanden ist.
     *
     * @return true, wenn ein Spielobjekt existiert, sonst false
     */
    private boolean isGameRunning() {
        return game != null;
    }

    /**
     * Prüft, ob das aktuelle Spiel beendet ist.
     * 
     * @return true, wenn ein Spiel existiert und als beendet markiert ist, sonst false
     */
    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }

    /**
     * Prüft, ob eine Speicherdatei für ein gespeichertes Spiel existiert.
     * 
     * @return true, wenn die Speicherdatei existiert, sonst false
     */
    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }

}
