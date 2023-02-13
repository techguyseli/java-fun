package forms;

import dao.Database;
import presentation.models.Client;

import java.util.HashMap;
import java.util.Map;

public class ChangerMDPFormValidator {

    private static final String FIELD_PASS1 = "ancien password", FIELD_PASS2 = "nouveau password", FIELD_CONFIRMATION = "confirmation";


    private Map<String, String> errors = new HashMap<>();
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

    private void verifierAncienPass(Client clt, String pass) throws FormException {
        if (pass != null && pass.trim().length() != 0) {
            if (!pass.equals(clt.getMotDePasse()))
                throw new FormException("Mot de passe éroné !");
        } else {
            throw new FormException("Mot de passe requis !");
        }
    }

    private void verifierNouveauPass(Client clt, String pass) throws FormException {
        if (pass != null && pass.trim().length() != 0) {
            if (pass.equals(clt.getMotDePasse()))
                throw new FormException("Le nouveau mot de passe est le même que l'ancien !");
            if (pass.trim().length() < 4)
                throw new FormException("Le mot de passe doit avoir au moins 4 caractères !");
        } else {
            throw new FormException("Nouveau mot de passe requis !");
        }
    }

    private void verifierConfirmation(String pass, String confirmation) throws FormException {
        if (confirmation != null && confirmation.trim().length() != 0) {
            if (!pass.equals(confirmation))
                throw new FormException("Le nouveau mot de passe et la confirmation se differt !");
        } else {
            throw new FormException("Confirmation de mot de passe requis !");
        }
    }


    private void validerAncienPass(Client clt, String pass) {

        try {
            verifierAncienPass(clt, pass);

        } catch (FormException e) {
            setError(FIELD_PASS1, e.getMessage());
        }
    }

    private void validerNouveauPass(Client clt, String pass) {

        try {
            verifierNouveauPass(clt, pass);


        } catch (FormException e) {
            setError(FIELD_PASS2, e.getMessage());
        }
    }

    private void validerConfirmation(String pass, String confirmation) {

        try {
            verifierConfirmation(pass, confirmation);


        } catch (FormException e) {
            setError(FIELD_CONFIRMATION, e.getMessage());
        }
    }


    public Client validerMdp(Client client, String mdp, String nouveau, String confirmation) {

        errors.clear();

        validerAncienPass(client, mdp);
        validerNouveauPass(client, nouveau);
        validerConfirmation(nouveau, confirmation);

        if (Errors().isEmpty()) {
            client.setMotDePasse(nouveau);
            Database.getClientDAO().update(client);
            resultMsg = "Mot de passe changé avec succès.";
        }


        return client;
    }
}
