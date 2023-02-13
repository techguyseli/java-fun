package presentation.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Log {
    // primary key
    private Long id;
    private LocalDateTime date;
    private TypeLog type;
    private String message;
    private Compte compte;

    public Long getId() { return id; }
    public LocalDateTime getDate() {
      return date;
    }
    public TypeLog getType() {
      return type;
    }
    public String getMessage() {
      return message;
    }
    public Compte getCompte() { return compte; }

    public void setId(Long id) { this.id = id; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public void setType(TypeLog type) { this.type = type; }
    public void setMessage(String message) { this.message = message; }
    public void setCompte(Compte compte) { this.compte = compte; }

    public Log(TypeLog type, String message, Compte compte) {
        this.type = type;
        this.message = message;
        this.compte = compte;
        date = LocalDateTime.now();
    }

    public Log(Long id, LocalDateTime date, TypeLog type, String message, Compte compte) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.message = message;
        this.compte = compte;
    }

    @Override
    public String toString() {
        String logStr = id + "\t\t\t" +
                compte.getNumeroCompte() + "\t\t\t" +
                date + "\t\t\t" +
                type + "\t\t\t" +
                message + "\n";

        return logStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return id.equals(log.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
