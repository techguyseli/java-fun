package dao;

import presentation.models.Utilisateur;

public interface IUtilisateurDAO {

    Utilisateur findByLoginAndPass(String login, String pass);
}
