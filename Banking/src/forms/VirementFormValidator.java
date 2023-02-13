package forms;

import dao.Database;
import presentation.models.*;

import java.util.HashMap;
import java.util.Map;

public class VirementFormValidator {
    private static final String FIELD_MONTANT ="montant";
    private static final String FIELD_NUM_COMPTE = "numéro de compte";

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
        if(montant < 100) throw new FormException("Le montant minimum que vous pouvez faire un virement avec est de 100 DH !");
        if(compte.getSolde() - montant < 100) throw new FormException("Le montant maximum que vous pouvez faire un virement avec est de " + (compte.getSolde() - 100) + " DH !");
    }
    private void validerMontant(String montantS, Compte compte){

        try {
            verifierMontant(montantS, compte);

        } catch (FormException e) {
            setError(FIELD_MONTANT, e.getMessage());
        }
    }

    private void verifierNumCompte(String numCompte, Compte compte) throws FormException {
        if(numCompte.isEmpty()) throw new FormException("Vous n'avez donné aucun numéro de compte !");
        if(numCompte.equals(compte.getNumeroCompte())) throw new FormException("Vous avez tapé le numéro de compte de votre compte courant !");
        if(Database.getCompteDAO().findById(numCompte) == null) throw new FormException("Il n'existe aucun compte avec le numéro suivant > " + numCompte + " !");
    }
    private void validerNumCompte(String numCompte, Compte compte){

        try {
            verifierNumCompte(numCompte, compte);

        } catch (FormException e) {
            setError(FIELD_NUM_COMPTE, e.getMessage());
        }
    }

    public Ticket validerVirement(Compte compteActuel, String montantS, String numCompte){

        errors.clear();

        validerMontant(montantS, compteActuel);
        validerNumCompte(numCompte, compteActuel);

        Log log = null;

        if(Errors().isEmpty()){

            Double montant = Double.parseDouble(montantS);

            Double soldeAJourComptActuel = compteActuel.getSolde() - montant;
            compteActuel.setSolde(soldeAJourComptActuel);
            Database.getCompteDAO().update(compteActuel);

            Compte compteVirement = Database.getCompteDAO().findById(numCompte);
            Double soldeAJourCompteVirement = compteVirement.getSolde() + montant;
            compteVirement.setSolde(soldeAJourCompteVirement);
            Database.getCompteDAO().update(compteVirement);

            log = new Log(TypeLog.VIREMENT, "Vous avez envoyé un montant de " + montantS + " DH au compte " + compteVirement.getNumeroCompte() + ".", compteActuel);
            Database.getLogDAO().save(log);

            Log logOther = new Log(TypeLog.VIREMENT, "Vous avez reçu un montant de " + montantS + " DH envoyé du compte " + compteActuel.getNumeroCompte() + ".", compteVirement);
            Database.getLogDAO().save(logOther);

            setResultMsg("Virement de " + montantS + " DH au compte " + compteVirement.getNumeroCompte() + ", fait avec succès, voullez vous sauvegarder le ticket ?");
            return new Ticket(compteActuel, log);
        }

        return null;
    }
}