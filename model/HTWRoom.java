package model;
import java.io.Serializable;

/**
 * @author Qassem Ahmad
 */
public class HTWRoom implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 9065680017147292999L;
    private final String identifier;
    private final String description;
    private Lecturer lecturer;

    /**
     * Erstellt einen neuen Raum.
     *
     * @param identifier Raumnummer
     * @param description Beschreibung des Raums
     * @param lecturer Lecturer, der im Raum ist
     */
    public HTWRoom(String identifier, String description, Lecturer lecturer) {
        this.identifier = identifier;
        this.description = description;
        this.lecturer = lecturer;
    }

    /**
     * @return Raumnummer
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @return Beschreibung des Raums
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Der Lecturer, der im Raum ist oder null, wenn keiner
     */
    public Lecturer getLecturer() {
        return lecturer;
    }
    /**
     * Setzt den Lecturer in Raum.
     *
     * @param lecturer Der Lecturer, der im Raum ist
     */
    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
    /**
     * @return true, wenn ein Lecturer im Raum ist
     */
    public boolean hasLecturer() {
        return lecturer != null;
    }

}