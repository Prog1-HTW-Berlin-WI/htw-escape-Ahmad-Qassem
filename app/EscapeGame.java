package app;
import java.io.Serializable;
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

public class EscapeGame implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Hero hero;
    private Lecturer[] lecturer = new Lecturer[6];
    private final HTWRoom[] rooms = new HTWRoom[24];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    private FriendlyAlien friendly = new FriendlyAlien();
    private HostileAlien hostile;
    private boolean smallRestUsedThisRound;
    private final String[] majuntkeQuestions = new String[3];
    private final String[][] majuntkeAnswers = new String[3][4];
    private final int[] majuntkeCorrectAnswer = new int[3];
  

    /**
     * erstellt ein neues Held.
     */
    public EscapeGame(Hero hero) {
        this.hero = hero;
        this.lecturer[0] = new Lecturer("Fr. Safitri");
        this.lecturer[1] = new Lecturer("Fr. Vaseva");
        this.lecturer[2] = new Lecturer("Herr. Poeser");
        this.lecturer[3] = new Lecturer("Herr. Gnaoui");
        this.lecturer[4] = new Lecturer("Fr. Gärtner");
        this.lecturer[5] = new Lecturer("Fr. Dr. Majunke");
        this.rooms[0] = new HTWRoom("A002","Seminarraum",lecturer[0]);
        this.rooms[1] = new HTWRoom("A003","Seminarraum",lecturer[1]);
        this.rooms[2] = new HTWRoom("A008","Büroraum",lecturer[2]);
        this.rooms[3] = new HTWRoom("A009","Büroraum",lecturer[3]);
        this.rooms[4] = new HTWRoom("A013","Medienunt.Unterrichtsraum",lecturer[4]);
        this.rooms[5] = new HTWRoom("A014","Büroraum",lecturer[5]);
        this.rooms[6] = new HTWRoom("A015","Seminarraum",null);
        this.rooms[7] = new HTWRoom("A114","Mehrzweckunterrichtsraum",null);
        this.rooms[8] = new HTWRoom("A115","Mehrzweckunterrichtsraum",null);
        this.rooms[9] = new HTWRoom("A118","Seminarraum",null);
        this.rooms[10] = new HTWRoom("A119","Seminarraum",null);
        this.rooms[11] = new HTWRoom("A126","Technisches Labor",null);
        this.rooms[12] = new HTWRoom("A127","Seminarraum",null);
        this.rooms[13] = new HTWRoom("A128","Seminarraum",null);
        this.rooms[14] = new HTWRoom("A208","Hör-/Lehrsaal",null);
        this.rooms[15] = new HTWRoom("A211","Büroraum",null);
        this.rooms[16] = new HTWRoom("A212","Büroraum",null);
        this.rooms[17] = new HTWRoom("A215","Übungsraum",null);
        this.rooms[18] = new HTWRoom("A226","Mehrzweckunterrichtsraum",null);
        this.rooms[19] = new HTWRoom("A238","Hör-/Lehrsaal ansteigend",null);
        this.rooms[20] = new HTWRoom("A239","Seminarraum",null);
        this.rooms[21] = new HTWRoom("A240","Medienunt. Unterrichtsr.",null);
        this.rooms[22] = new HTWRoom("A234","Mehrzwecksunterrichtsraum",null);
        this.rooms[23] = new HTWRoom("A231","Mehrzwecksunterrichtsraum",null);

        this.smallRestUsedThisRound = false;
        initMajuntkeQuestions();
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
    * setzt gameRunning auf true und startet das Mainmenu
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
        System.out.println("The game has started."); //kann raus
    }
    /**
     * zeigt das Mainmenu auf der Konsole
     */
    private void showGameMenu() {
        System.out.println("Spielmenu");
        System.out.println("(1) Hochschule erkunden");
        System.out.println("(2) Hero Status anzeigen");
        System.out.println("(3) Laufzettel anzeigen");
        System.out.println("(4) Verschnaufpause machen");
        System.out.println("(5) Spiel verlassen");
        System.out.print("Bitte Zahl zwischen 1 und 5 eingeben: ");
        System.out.println();
    }
    /**
     * verarbeitet die Auswahl des Spielers
     * @param input die Eingabe des Spielers im Scanner
     * jenach Eingabe werden entsprechende Methoden und Ausgaben ausgeführt
     */
    private void handleGameMenuInput(String input) {
        switch (input) {
            case "1":
                htwErkunden();
                System.out.println();
                break;
            case "2":
                System.out.println("Name: " + hero.getName());
                System.out.println("Lebenspunkte: " + hero.getHealthPoints());
                System.out.println("Erfahrungspunkte: " + hero.getExperiencePoints());
                System.out.println("Basicdamage: " + hero.getDamage());
                System.out.println("Gameround: " + hero.getGameRound() + "/24");
                break;
            case "3":
                System.out.println("Laufzettel:");
                System.out.println();
                hero.getLaufzettel();
                break;
            case "4":
                doRest();
                System.out.println();
                break;
            case "5":
                System.out.println("Zurueck ins Hauptmenu.");
                gameRunning = false;
                break;
            default:
                System.out.println("Unzulaessige Eingabe. Bitte 1 bis 5 eingeben.");
                System.out.println();
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
    /**
     * Auswahl der Verschnaufpausen werden aufgelistet
     * Scanner wird aufgerufen
     * Eingabe des Scanners unter der Variable choice wird verarbeitet
     * jenach Eingabe werden entsprechende Methoden und Ausgaben ausgeführt
     */
    private void doRest() {
    System.out.println();
    System.out.println("Verschnaufpause");
    System.out.println("(1) Kleine Verschnaufpause (+3 LP, einmal pro Runde)");
    System.out.println("(2) Grosse Verschnaufpause (+10 LP, kostet eine Runde)");
    System.out.println("(3) Abbrechen");
    System.out.print("Bitte Zahl zwischen 1 und 3 eingeben: ");
    System.out.println();

    String choice = EscapeApp.readUserInput();

    switch (choice) {
        case "1":
            if (smallRestUsedThisRound) {
                System.out.println("Du hast in dieser Runde bereits eine kleine Verschnaufpause genutzt.");
                return;
            }
            int beforeSmall = hero.getHealthPoints();
            hero.regenerate(false);
            int afterSmall = hero.getHealthPoints();
            smallRestUsedThisRound = true;
            System.out.println("Lebenspunkte: " + beforeSmall + " -> " + afterSmall);
            return;

        case "2":
            int beforeBig = hero.getHealthPoints();
            hero.regenerate(true);
            smallRestUsedThisRound = false; //weil in regenerate neue runde gezählt wird.
            int afterBig = hero.getHealthPoints();
            System.out.println("Lebenspunkte: " + beforeBig + " -> " + afterBig);
            //hero.increaseGameRound(); die Methode regenerate() macht das schon.
            return;

        case "3":
            System.out.println("Keine Verschnaufpause gemacht.");
            return;

        default:
            System.out.println("Unzulaessige Eingabe. Bitte eine Zahl zwischen 1 - 3 eingeben");
            System.out.println();
            doRest();
            
        }
    }



    /**
     * führt beliebige Ereignisse wenn Spieler das Campus erkunden will.
     * wenn das Maximum Gameround 24 nicht überschritten ist und die Lebenspunkte des Hero über 0 sind. 
     * 20% der Fälle passiert nichts. 
     * 52% der Fälle trifft der Hero ein Alien (40% freundlich, 60% feindlich). 
     * wenn feindlich kann der Hero zwischen Kämpfen oder Fliehen entscheiden. 
     * 28% der Fälle trifft der Hero ein lecturer und sammelt dessen Unterschrift. 
     * Wenn die Lebenspunkte bei 0, muss der Hero eine Verschnaufpause machen. 
     * erreicht Hero die letzte Runde mit allen Unterschriften, wird ausnahweise die Methode visitMajunke ausgeführt. 
     * ansonsten wird das Spiel beendet.
     */
    private void htwErkunden() {
        hostile = new HostileAlien();
        if(hero.getGameRound() < 24 && hero.isOperational()){
            hero.increaseGameRound(); //hier einmal anstelle unten 3-4mal.
            smallRestUsedThisRound = false;
            double chance = Math.random();
            double alienTyp = Math.random();
        
        
 
            if (chance < 0.2) {
                for (int i = 6; i < rooms.length; i++){
                    if (rooms[i].gotVisited() == false) {
                        System.out.println("Du betritts " + rooms[i].getIdentifier()+ ", " + rooms[i].getDescription());
                        System.out.println();
                        System.out.println("Der Raum ist leer.");
                        System.out.println();
                        System.out.println("Die Ruhe macht dich unruhig, aber du muss weiter erkunden...");
                        System.out.println();
                        rooms[i].beenVisited();
                        return;
                    } 
                }
           
            }
            else if (chance < 0.72) {
                for (int i = 6; i < rooms.length; i++){

                    if (rooms[i].gotVisited() == false) {
                        System.out.println();
                        System.out.println("Du betritts " + rooms[i].getIdentifier()+ ", " + rooms[i].getDescription() + ", aber...");
                        System.out.println();   

                        if (alienTyp < 0.4) {       
                            System.out.println("Ein lebendes Wesen!");
                            System.out.println();
                            System.out.println("Es scheint friedlich zu sein ");
                            System.out.println();
                            System.out.println(friendly.getName() + ": " + friendly.getGreeting());
                        }
                      
                        else if (!hostile.isDefeated()) {    //die Bedingung kann weg?
                            System.out.println("Ein lebendes Wesen!");
                            System.out.println();
                            System.out.println("VORSICHT!! Es scheint aggresive zu sein!! ");
                            System.out.println();
                            System.out.println(hostile.getName() + ": " + hostile.getGreeting());
                            System.out.println(hostile.getName() + " has " + hostile.getLifePoints() + " HP");
                            System.out.println();
                            fightOrFlee();
                        }
                        rooms[i].beenVisited();
                        return;
                    } 
                }
            }
            else {  for (int i = 4; i >= 0; i--){
                    if (!rooms[i].lecturerHasSigned()) { 
                        System.out.println();
                        System.out.println("Du betritts " + rooms[i].getIdentifier()+ ", " + rooms[i].getDescription());
                        System.out.println();
                        System.out.println(rooms[i].getLecturerName() + " ist bereit zu unterschreiben");
                        System.out.println();
                        //rooms[i].makeLecturerSign(); //Methode existierte schon innerhalb Hero.java;
                        Lecturer roomLecturer = rooms[i].getLecturer();
                        hero.signExerciseLeader(roomLecturer);
                        return;
                    }
                }
                    System.out.println();
                    System.out.println("### Alle DozentenInnen haben bereits unterschrieben. Du kannst jetzt Prof. Majunke besuchen ###");
                    System.out.println();
                    visitMajunke();
            }
        }
        else if(!hero.isOperational()){
            System.out.println("Dein Hero braucht Verschnaufpause");
        }
        else if(hero.getGameRound() == 24 && hero.hasAllSignatures() == true) { //Ausnahmefall (selten)
            System.out.println();
            System.out.println("### Alle DozentenInnen haben bereits unterschrieben. Du kannst jetzt Prof. Majunke besuchen ###");
            System.out.println();
            visitMajunke();
        }
        else {
            System.out.println("GAME OVER!"); 
            loseBecauseTimeIsUp(); 
        }
    }

    private void fightOrFlee() {
        System.out.println();
        System.out.println("Du muss entscheiden. Kämpfen oder Fliehen?");
        System.out.println("Your HP: " + hero.getHealthPoints() + " - Basicdamage: " + hero.getDamage());
        System.out.println();
        System.out.println(" 1) Kämpfen");
        System.out.println(" 2) Fliehen");

        String choice = EscapeApp.readUserInput();
        
        switch (choice) {
            case "1":
                while (hero.isOperational() && !hostile.isDefeated()) {
                    int heroDamage = hero.attack();
                    int alienDamage = hostile.attack();
                    hostile.takeDamage(heroDamage);
                    System.out.println();
                    hero.takeDamage(alienDamage);
                    System.out.println();
                    System.out.println();
                    }
                if (hostile.isDefeated()) {
                        hero.addExperiencePoints(5);
                        System.out.println("## GLÜCKWUNSCH! Du hast den Alien besiegt! Du bekommst: 5 XP" );
                        System.out.println();
                        System.out.println("Your HP: " + hero.getHealthPoints() + " - Basicdamage: " + hero.getDamage());
                }   else if (!hero.isOperational()) { hero.addExperiencePoints(1); //soweit keine Konsequenzen für healthpoints=0
                        System.out.println("## Der Alien hat dich zwar überwältigt, stärker bist du trotzdem geworden! Du bekommst: 1 XP" );
                        System.out.println();
                        System.out.println("Your HP: " + hero.getHealthPoints() + " - Basicdamage: " + hero.getDamage());
                    }
                        return;
            case "2":
                if (hero.flee()) {
                    System.out.println(" Der Flucht ist gelungen! ");
                    System.out.println();
                }   else { System.out.println(" Der Alien ist zu schnell! ");
                        while (hero.isOperational() && !hostile.isDefeated()) {
                            int heroDamage = hero.attack();
                            int alienDamage = hostile.attack();
                            hero.takeDamage(alienDamage);
                            System.out.println();
                            hostile.takeDamage(heroDamage);
                            System.out.println();
                            System.out.println();
                        }
                    }
                if (hostile.isDefeated()) {
                        hero.addExperiencePoints(5);
                        System.out.println("## GLÜCKWUNSCH! Du hast den Alien besiegt! Du bekommst: 5 XP" );
                        System.out.println();
                        System.out.println("Your HP: " + hero.getHealthPoints() + " - Basicdamage: " + hero.getDamage());
                }   else if (!hero.isOperational()) { hero.addExperiencePoints(1); //soweit keine Konsequenzen für healthpoints=0
                        System.out.println("## Der Alien hat dich zwar überwältigt, stärker bist du trotzdem geworden! Du bekommst: 1 XP" );
                        System.out.println();
                        System.out.println("Your HP: " + hero.getHealthPoints() + " - Basicdamage: " + hero.getDamage());
                    }
                        return;
            default:
                System.out.println("Unzulässige Eingabe, bitte 1 oder 2 eingeben");
                System.out.println();
                fightOrFlee();

                
                
        }

    }

    private void visitMajunke() {
        
        System.out.println("Du betrittst " + rooms[5].getIdentifier()+ ", " + rooms[5].getDescription());
        System.out.println();
        System.out.println("Professorin Dr. Majuntke ist hier, um deine Fortschritte zu überprüfen.");
        System.out.println("Sie schaut sich deinen Laufzettel an und überprüft, ob du alle Unterschriften hast.");
        System.out.println();
        System.out.println("Professorin Majuntke sieht deinen Laufzettel an... und nickt zufrieden.");
        System.out.println();
        System.out.println("Professorin Majuntke: 'Herzlichen Glückwunsch! Nun musst du mir nur noch eine Frage beantworten, um zu entkommen.'");
        System.out.println("Professorin Majuntke stellt dir eine Frage");
        System.out.println();

        boolean firstTryCorrect = askRandomMajuntkeQuestion();
        if (firstTryCorrect) {
            winGame();
            return;
        }


        System.out.println();
        System.out.println("Falsch! du bekommst noch eine Chance:");
        System.out.println();

        boolean secondTryCorrect = askRandomMajuntkeQuestion();
        if (secondTryCorrect) {
            winGame();
            return;
        }

        System.out.println();
        System.out.println("Auch die zweite Antwort ist falsch! Leider hast du keine weiteren Chancen mehr.");
        System.out.print("Professorin Majuntke fliegt davon und sagt: \"Bis zum nächsten Semester.\"");
        gameFinished = true;
        gameRunning = false;
    }

    private boolean askRandomMajuntkeQuestion() {
        int index = (int) (Math.random() * 3);
        return askMajuntkeQuestion(index);
    }

    private boolean askMajuntkeQuestion(int index) {
        System.out.println(majuntkeQuestions[index]);
        System.out.println();

        for (int i = 0; i < 4; i++) {
            System.out.println((i + 1) + ") " + majuntkeAnswers[index][i]);
        }

        int choice = readChoice1To4();
        if (choice == majuntkeCorrectAnswer[index]) {
            System.out.println();
            System.out.println("Korrekt!");
            return true;
        }

        System.out.println();
        System.out.println("Falsch!");
        return false;
    }

    private int readChoice1To4() {
    while (true) {
        System.out.print("Deine Antwort (1-4): ");
        String input = EscapeApp.readUserInput();

            switch (input) {
                case "1": return 1;
                case "2": return 2;
                case "3": return 3;
                case "4": return 4;
                default:
                System.out.println("Bitte gib 1, 2, 3 oder 4 ein.");
            }
        }
    }

    private void initMajuntkeQuestions() {
        majuntkeQuestions[0] = "Welche Kontrollstruktur nutzt man, um Code mehrfach auszuführen?";
        majuntkeAnswers[0][0] = "if";
        majuntkeAnswers[0][1] = "for";
        majuntkeAnswers[0][2] = "class";
        majuntkeAnswers[0][3] = "import";
        majuntkeCorrectAnswer[0] = 2;

        majuntkeQuestions[1] = "Welcher Datentyp speichert Wahr oder Falsch?";
        majuntkeAnswers[1][0] = "boolean";
        majuntkeAnswers[1][1] = "int";
        majuntkeAnswers[1][2] = "char";
        majuntkeAnswers[1][3] = "double";
        majuntkeCorrectAnswer[1] = 1;

        majuntkeQuestions[2] = "Welcher Operator vergleicht zwei int Werte auf Gleichheit?";
        majuntkeAnswers[2][0] = "=";
        majuntkeAnswers[2][1] = "==";
        majuntkeAnswers[2][2] = "equals()";
        majuntkeAnswers[2][3] = "===";
        majuntkeCorrectAnswer[2] = 2;
    }


    private void winGame() {
        System.out.println("Herzlichen Glückwunsch! Du hast es geschafft, rechtzeitig zu entkommen.");
        System.out.println("Professorin Majuntke ist beeindruckt von deinem Mut und deiner Entschlossenheit.");
        System.out.println("Du hast das Escape Game erfolgreich abgeschlossen!");
        gameFinished = true;
        gameRunning = false;
    }

    private void loseBecauseTimeIsUp() {
        System.out.println("Die Zeit ist abgelaufen! Du hast es nicht geschafft, rechtzeitig zu entkommen.");
        System.out.println("Professorin Majuntke fliegt davon. Was mit der HTW passiert, bleibt ungewiss...");
        System.out.println("GAME OVER!");
        gameFinished = true;
        gameRunning = false;
    }
}
