package dao.daoFiles;

import dao.IUtilisateurDAO;
import presentation.models.Admin;
import presentation.models.Utilisateur;

public class UtilisateurDAO implements IUtilisateurDAO {
    @Override
    public Utilisateur findByLoginAndPass(String login, String pass) {
        if(Admin.getInstance().correctCredentials(login, pass)) return Admin.getInstance();
        return (new ClientDAO()).findAll()
                .stream()
                .filter(client -> client.correctCredentials(login, pass))
                .findFirst()
                .orElse(null);
    }
}
