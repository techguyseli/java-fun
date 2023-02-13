package presentation.models;

import java.util.Objects;

public abstract class Utilisateur {
    // primary key
    protected Long id;
    protected String prenom;
    protected String nom;
    protected String login;
    protected String motDePasse;
    protected Role role;

    public Long         getId() {
        return id;
    }
    public void         setId(Long id) {
        this.id = id;
    }
    public String       getNomComplet() {
        return prenom + " " + nom;
    }
    public String       getNom() {
        return nom;
    }
    public String       getPrenom() {
        return prenom;
    }
    public void         setNom(String nom) {
        this.nom = nom;
    }
    public void         setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getLogin() {
        return login;
    }
    public String getMotDePasse() {
        return motDePasse;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public  Utilisateur(){}

    public  Utilisateur(String login, String pass, Role role){
        this.login          = login;
        this.motDePasse     = pass;
        this.role = role;
    }

    public Utilisateur(String prenom, String nom, String login, String motDePasse, Role role) {
        this.prenom = prenom;
        this.nom = nom;
        this.login = login;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    public Utilisateur(Long id, String prenom, String nom, String login, String motDePasse, Role role) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.login = login;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    @Override
    public String toString() {
        return id + "\t\t\t" + prenom + "\t\t\t" + nom + "\t\t\t" + login + "\t\t\t" + motDePasse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(this.id, that.id);
    }

    public boolean correctCredentials(String login, String mdp){ return getLogin().equals(login) && getMotDePasse().equals(mdp); }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
