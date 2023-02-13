package forms;

import dao.Database;
import presentation.models.Agence;
import presentation.models.Client;
import presentation.models.Compte;
import presentation.models.TypeLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AjouterCompteFormValidator {
    private static final String FIELD_NOMAGENCE = "nom d'agence", FIELD_SOLDE = "solde", FIELD_CIN = "CIN";

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

    private void verifierNomAgence(String nom) throws FormException{
        if(nom != null && !nom.isBlank()){
            Agence agence = Database.getAgenceDAO().findByNom(nom);
            if(agence == null) throw new FormException("L'agence avec le nom " + nom + " n'existe pas !");

        }
        else throw new FormException("Le nom de l'agence est obligatoire !");
    }
    private void verifierSolde(String montantS) throws FormException {
        Double montant = null;
        if(montantS.isBlank()) throw new FormException("Vous n'avez donné aucun montant !");
        try{
            montant = Double.parseDouble(montantS);
        }
        catch (Exception e){
            throw new FormException("Le montant n'est pas valide !");
        }
        if(montant < 100) throw new FormException("Le montant est inférieure à 100.00 DH !");
    }
    private void verifierCIN(String cin) throws FormException{
        if(cin != null && !cin.isBlank()){
            Client client = Database.getClientDAO().findByCIN(cin);
            if(client == null) throw new FormException("Le client avec le CIN " + cin + " n'existe pas !");
        }
        else throw new FormException("Le CIN est obligatoire !");
    }


    private void validerNomAgence(String nom){

        try {
            verifierNomAgence(nom);

        } catch (FormException e) {
            setError(FIELD_NOMAGENCE, e.getMessage());
        }
    }
    private void validerSolde(String solde){

        try {
            verifierSolde(solde);

        } catch (FormException e) {
            setError(FIELD_SOLDE, e.getMessage());
        }
    }
    private void validerCIN(String cin){

        try {
            verifierCIN(cin);

        } catch (FormException e) {
            setError(FIELD_CIN, e.getMessage());
        }
    }

    public void validerAjout(String nomAgence, String soldeStr, String cinClient){
        errors.clear();

        validerNomAgence(nomAgence);
        validerSolde(soldeStr);
        validerCIN(cinClient);

        if(Errors().isEmpty()){
            Client client = Database.getClientDAO().findByCIN(cinClient);
            Agence agence = Database.getAgenceDAO().findByNom(nomAgence);
            Double solde = Double.parseDouble(soldeStr);
            Compte compte = Database.getCompteDAO().save(new Compte(agence, solde, client));
            Database.getLogDAO().saveLogForAccount(compte, TypeLog.CREATION, "Compte " + compte.getNumeroCompte()
            + " a été créer, appartenant au client avec le CIN " + cinClient);
            setResultMsg("Compte ajouté avec succès.");
        }
    }

}
