package forms;



import dao.Database;
import presentation.models.Admin;
import presentation.models.Client;
import presentation.models.Utilisateur;

import java.util.HashMap;
import java.util.Map;

public class LoginFormValidator {

    private static final String FIELD_LOGIN ="login", FIELD_PASS = "password";


    private Map<String , String> errors = new HashMap<>();
    private String resultMsg;

    public Map<String, String> Errors() {
        return errors;
    }
    public void setError(String fieldName, String errorMsg) {
        Errors().put(fieldName, errorMsg);
    }

    public String ResultMsg() {
        return resultMsg;
    }
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }


    private void verifierLogin(String login) throws FormException {
        if(login!= null && login.trim().length()!=0){
            if(login.trim().length()<4)
                throw new FormException("Login must have moe than 4 chars !");
        }else{
            throw new FormException("Login is Required !");
        }
    }
    private void verifierPass(String pass) throws FormException {
        if(pass!= null && pass.trim().length()!=0){
            if(pass.trim().length()<4)
                throw new FormException("Password must have more than 4 chars !");
        }else{
            throw new FormException("Password is Required !");
        }
    }


    private void validerLogin(String login){

        try {
            verifierLogin(login);

        } catch (FormException e) {
           setError(FIELD_LOGIN, e.getMessage());
        }
    }
    private void validerPass(String pass){

        try {
            verifierPass(pass);


        } catch (FormException e) {
            setError(FIELD_PASS, e.getMessage());
        }
    }


    public Utilisateur validerSession(String login, String password){

        errors.clear();
        Utilisateur session = null;


        validerLogin(login);
        validerPass(password);

        if(Errors().isEmpty()){

            session = Database.getUtilisateurDAO().findByLoginAndPass(login, password);
            if(session!= null){
                if(session instanceof Admin)
                    setResultMsg("Vous étes connectés avec succés [ADMIN] " + session.getNomComplet());
                else if (session instanceof Client) {
                    setResultMsg("Vous étes connectés avec succés [CLIENT] " + session.getNomComplet());
                }
            }

            else {
                setResultMsg("La combinaison du login et password que vous avez tapé est non valid !");
            }
        }


        return session;
    }


}
