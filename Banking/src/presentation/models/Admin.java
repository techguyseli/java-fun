package presentation.models;

public class Admin  extends Utilisateur{

    private static final Admin ADMIN = new Admin();

    private Admin(){
        id          = 1L;
        login       = "admin";
        motDePasse  = "1234";
        nom         = "Admin";
        prenom      = "Omar";
        role = Role.ADMIN;

    }


    public static Admin getInstance(){

        return ADMIN;
    }

}
