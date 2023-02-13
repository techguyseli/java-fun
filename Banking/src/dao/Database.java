package dao;

import dao.daoFiles.*;

public abstract class Database {
    private static final ILogDAO logDAO = new LogDAO();
    private static final ICompteDAO compteDAO = new CompteDAO();
    private static final IAgenceDAO agenceDAO = new AgenceDAO();
    private static final IClientDAO clientDAO = new ClientDAO();
    private static final IUtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    public static IAgenceDAO getAgenceDAO() {
        return agenceDAO;
    }

    public static IClientDAO getClientDAO() {
        return clientDAO;
    }

    public static ICompteDAO getCompteDAO() {
        return compteDAO;
    }

    public static ILogDAO getLogDAO() {
        return logDAO;
    }

    public static IUtilisateurDAO getUtilisateurDAO() {
        return utilisateurDAO;
    }
}
