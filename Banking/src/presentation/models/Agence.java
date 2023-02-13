package presentation.models;

import java.util.Objects;

public class Agence {

    // primary key
    private Long                 idAgence;

    // unique
    private String              nomAgence;

    // unique
    private String              adresseAgence;

    // unique
    private String              telAgence;

    // unique
    private String              emailAgence;

    public Agence() {
    }
    public Agence(String nom, String adresse, String tel, String mail)
    {
        nomAgence       = nom;
        telAgence       = tel;
        adresseAgence   = adresse;
        emailAgence     = mail;
    }

    public Agence(Long id, String nom, String adresse, String tel, String mail)
    {
        idAgence = id;
        nomAgence       = nom;
        telAgence       = tel;
        adresseAgence   = adresse;
        emailAgence     = mail;
    }

    public Long             getIdAgence() {
        return idAgence;
    }
    public String           getNomAgence() {
        return nomAgence;
    }
    public String           getEmailAgence() {
        return emailAgence;
    }
    public String           getTelAgence() {
        return telAgence;
    }
    public String           getAdresseAgence() {
        return adresseAgence;
    }

    public void             setIdAgence(Long idAgence) {
        this.idAgence = idAgence;
    }
    public void             setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }
    public void             setEmailAgence(String emailAgence) {
        this.emailAgence = emailAgence;
    }
    public void             setAdresseAgence(String adresseAgence) {
        this.adresseAgence = adresseAgence;
    }
    public void             setTelAgence(String telAgence) {
        this.telAgence = telAgence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agence agence = (Agence) o;
        return idAgence.equals(agence.idAgence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAgence);
    }

    @Override
    public String toString() {
        return idAgence + "\t\t\t" +
                nomAgence + "\t\t\t" +
                telAgence + "\t\t\t" +
                adresseAgence + "\t\t\t" +
                emailAgence + "\n";
    }
}

