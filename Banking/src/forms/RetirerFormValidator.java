package forms;

import dao.Database;
import presentation.models.*;

import java.util.HashMap;
import java.util.Map;

public class RetirerFormValidator {
    private static final String FIELD_MONTANT ="montant";

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

    private void verifierMontant(String montantS, Compte compte) throws FormException {
        Double montant = null;
        if(montantS.isEmpty()) throw new FormException("Vous n'avez donné aucun montant !");
        try{
            montant = Double.parseDouble(montantS);
        }
        catch (Exception e){
            throw new FormException("Le montant n'est pas valide !");
        }
        if(montant < 100) throw new FormException("Le minimum que vous pouvez retirer est de 100 DH !");
        if(compte.getSolde() - montant < 100) throw new FormException("Le maximum que vous pouvez retirer est de " + (compte.getSolde() - 100) + " DH !");
    }
    private void validerMontant(String montantS, Compte compte){

        try {
            verifierMontant(montantS, compte);

        } catch (FormException e) {
            setError(FIELD_MONTANT, e.getMessage());
        }
    }

    public Ticket validerRetrait(Compte compte, String montantS){

        errors.clear();

        validerMontant(montantS, compte);

        Log log = null;

        if(Errors().isEmpty()){

            Double montant = Double.parseDouble(montantS);
            Double soldeAJour = compte.getSolde() - montant;
            compte.setSolde(soldeAJour);
            Database.getCompteDAO().update(compte);

            log = new Log(TypeLog.RETRAIT, "Vous avez retiré un montant de " + montantS + " DH.", compte);
            Database.getLogDAO().save(log);
            setResultMsg("Retrait de " + montantS + " DH fait avec succès, voullez-vous sauvegarder le ticket ?");
            return new Ticket(compte, log);
        }

        return null;
    }
}