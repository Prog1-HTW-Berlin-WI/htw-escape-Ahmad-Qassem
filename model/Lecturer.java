package model;

import java.io.Serializable;

/**
 * @author Qassem Ahmad
 */
public class Lecturer implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 540082607047283589L;
    private final String name;
    private boolean hasSigned;

    /**
     * Erstellt einen neuen Lecturer
     *
     * @param name Name des Lecturers
     */
    public Lecturer(String name) {
        this.name = name;
        this.hasSigned = false;
    }

    /**
     * @return Name des Lecturers
     */
    public String getName() {
        return name;
    }

    /**
     * @return true, wenn bereits unterschrieben
     */
    public boolean hasSigned() {
        return hasSigned;
    }

    /**
     * Gibt an, ob der Lecturer bereit ist zu unterschreiben
     * bei uns bereit, solange noch nicht unterschrieben
     *
     * @return true, wenn unterschreiben möglich ist
     */
    public boolean isReadyToSign() {
        return !hasSigned;
    }

    /**
     * Markiert den Lecturer als unterschrieben.
     */
    public void sign() {
        this.hasSigned = true;
    }
}