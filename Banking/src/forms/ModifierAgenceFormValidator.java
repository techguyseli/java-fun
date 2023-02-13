package forms;

import dao.Database;
import presentation.models.Agence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModifierAgenceFormValidator {
    private static final String FIELD_NOM = "nom", FIELD_ADRESSE = "adresse", FIELD_TEL = "tél", FIELD_EMAIL = "email";

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

    private void verifierNom(Agence a, String nom) throws FormException{
        if(nom != null && !nom.isBlank()){
            List<Agence> agences = Database.getAgenceDAO().findAll()
                            .stream()
                            .filter(agence -> agence.getNomAgence().toLowerCase().equals(nom.toLowerCase())
                                    && !agence.equals(a))
                            .toList();
            if(agences.size() > 0) throw new FormException("Le nom d'agence existe déja !");

        }
        else throw new FormException("Le nom de l'agence est obligatoire !");
    }
    private void verifierAdresse(Agence a, String adresse) throws FormException{
        if(adresse != null && !adresse.isBlank()){
            List<Agence> agences = Database.getAgenceDAO().findAll()
                    .stream()
                    .filter(agence -> agence.getAdresseAgence().toLowerCase().equals(adresse.toLowerCase())
                            && !agence.equals(a))
                    .toList();
            if(agences.size() > 0) throw new FormException("L'adresse de l'agence existe déja !");

        }
        else throw new FormException("L'adresse de l'agence est obligatoire !");
    }
    private void verifierTel(Agence a, String tel) throws FormException{
        if(tel != null && !tel.isBlank()){
            List<Agence> agences = Database.getAgenceDAO().findAll()
                    .stream()
                    .filter(agence -> agence.getTelAgence().equals(tel)
                            && !agence.equals(a))
                    .toList();
            if(agences.size() > 0) throw new FormException("Le numéro de téléphone de l'agence existe déja !");
            if(!tel.matches("^0[0-9]{9}$")) throw new FormException("Le numéro de téléphone n'est pas dans le bon format !");
        }
        else throw new FormException("Le numéro de téléphone de l'agence est obligatoire !");
    }
    private void verifierEmail(Agence a, String email) throws FormException{
        if(email != null && !email.isBlank()){
            List<Agence> agences = Database.getAgenceDAO().findAll()
                    .stream()
                    .filter(agence -> agence.getEmailAgence().toLowerCase().equals(email.toLowerCase())
                    && !agence.equals(a))
                    .toList();
            if(agences.size() > 0) throw new FormException("L'email de l'agence existe déja !");
            if(!email.matches("^[a-zA-Z.1-9]+@[a-zA-Z.1-9]+\\.[a-zA-Z]{2,4}$")) throw new FormException("L'email n'est pas dans le bon format !");
        }
        else throw new FormException("L'email de l'agence est obligatoire !");
    }

    private void validerNom(Agence agence, String nom){

        try {
            verifierNom(agence, nom);

        } catch (FormException e) {
            setError(FIELD_NOM, e.getMessage());
        }
    }
    private void validerAdresse(Agence agence, String adresse){

        try {
            verifierAdresse(agence, adresse);

        } catch (FormException e) {
            setError(FIELD_ADRESSE, e.getMessage());
        }
    }
    private void validerTel(Agence agence, String tel){

        try {
            verifierTel(agence, tel);

        } catch (FormException e) {
            setError(FIELD_TEL, e.getMessage());
        }
    }
    private void validerEmail(Agence agence, String email){

        try {
            verifierEmail(agence, email);

        } catch (FormException e) {
            setError(FIELD_EMAIL, e.getMessage());
        }
    }

    public void validerModification(Agence agence, String nom, String adresse, String tel, String email){
        errors.clear();

        validerNom(agence, nom);
        validerAdresse(agence, adresse);
        validerTel(agence, tel);
        validerEmail(agence, email);

        if(Errors().isEmpty()){
            agence.setNomAgence(nom);
            agence.setAdresseAgence(adresse);
            agence.setTelAgence(tel);
            agence.setEmailAgence(email);
            Database.getAgenceDAO().update(agence);
            setResultMsg("Agence modifié avec succès.");
        }
    }

}
