package presentation.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Compte {

    private Agence          agence;

    // primary key
    private String          numeroCompte;

    private Double          solde;

    private LocalDateTime   dateCreation;

    private Client          propriétaire;

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public void setDateCreation() { this.dateCreation = LocalDateTime.now(); }

    public void setPropriétaire(Client propriétaire) {
        this.propriétaire = propriétaire;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public Agence getAgence() {
        return agence;
    }

    public Client           getPropriétaire() {
        return propriétaire;
    }

    public Double           getSolde() {
        return solde;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    private String generateNumeroCompte(long id) { return "b-" + agence.getIdAgence() + "-" + id; }

    public void setNumeroCompte(long id){ numeroCompte = generateNumeroCompte(id); }

    public void setNumeroCompte(String numeroCompte) { this.numeroCompte = numeroCompte; }

    public LocalDateTime    getDateCreation() {
        return dateCreation;
    }

    public Compte(){}

    public Compte(Agence agence, Double solde, Client propriétaire) {
        this.agence = agence;
        this.solde = solde;
        this.dateCreation = LocalDateTime.now();
        this.propriétaire = propriétaire;
    }

    public Compte(Agence agence, String numeroCompte, Double solde, LocalDateTime dateCreation, Client propriétaire) {
        this.agence = agence;
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.propriétaire = propriétaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return numeroCompte.equals(compte.numeroCompte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCompte);
    }

    @Override
    public String toString() {
        return numeroCompte + "\t\t\t" +
                solde + "\t\t\t" +
                dateCreation + "\t\t\t" +
                propriétaire.getId() + "\t\t\t" +
                agence.getIdAgence() + "\n";
    }
}
