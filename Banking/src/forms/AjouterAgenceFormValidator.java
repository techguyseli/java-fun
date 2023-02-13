package forms;

import dao.Database;
import presentation.models.Agence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AjouterAgenceFormValidator {
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

    private void verifierNom(String nom) throws FormException{
        if(nom != null && !nom.isBlank()){
            List<Agence> agences = Database.getAgenceDAO().findAll()
                            .stream()
                            .filter(agence -> agence.getNomAgence().toLowerCase().equals(nom.toLowerCase()))
                            .toList();
            if(agences.size() > 0) throw new FormException("Le nom d'agence existe déja !");

        }
        else throw new FormException("Le nom de l'agence est obligatoire !");
    }
    private void verifierAdresse(String adresse) throws FormException{
        if(adresse != null && !adresse.isBlank()){
            List<Agence> agences = Database.getAgenceDAO().findAll()
                    .stream()
                    .filter(agence -> agence.getAdresseAgence().toLowerCase().equals(adresse.toLowerCase()))
                    .toList();
            if(agences.size() > 0) throw new FormException("L'adresse de l'agence existe déja !");

        }
        else throw new FormException("L'adresse de l'agence est obligatoire !");
    }
    private void verifierTel(String tel) throws FormException{
        if(tel != null && !tel.isBlank()){
            List<Agence> agences = Database.getAgenceDAO().findAll()
                    .stream()
                    .filter(agence -> agence.getTelAgence().equals(tel))
                    .toList();
            if(agences.size() > 0) throw new FormException("Le numéro de téléphone de l'agence existe déja !");
            if(!tel.matches("^0[0-9]{9}$")) throw new FormException("Le numéro de téléphone n'est pas dans le bon format !");
        }
        else throw new FormException("Le numéro de téléphone de l'agence est obligatoire !");
    }
    private void verifierEmail(String email) throws FormException{
        if(email != null && !email.isBlank()){
            List<Agence> agences = Database.getAgenceDAO().findAll()
                    .stream()
                    .filter(agence -> agence.getEmailAgence().toLowerCase().equals(email.toLowerCase()))
                    .toList();
            if(agences.size() > 0) throw new FormException("L'email de l'agence existe déja !");
            if(!email.matches("^[a-zA-Z.1-9]+@[a-zA-Z.1-9]+\\.[a-zA-Z]{2,4}$")) throw new FormException("L'email n'est pas dans le bon format !");
        }
        else throw new FormException("L'email de l'agence est obligatoire !");
    }

    private void validerNom(String nom){

        try {
            verifierNom(nom);

        } catch (FormException e) {
            setError(FIELD_NOM, e.getMessage());
        }
    }
    private void validerAdresse(String adresse){

        try {
            verifierAdresse(adresse);

        } catch (FormException e) {
            setError(FIELD_ADRESSE, e.getMessage());
        }
    }
    private void validerTel(String tel){

        try {
            verifierTel(tel);

        } catch (FormException e) {
            setError(FIELD_TEL, e.getMessage());
        }
    }
    private void validerEmail(String email){

        try {
            verifierEmail(email);

        } catch (FormException e) {
            setError(FIELD_EMAIL, e.getMessage());
        }
    }

    public void validerAjout(String nom, String adresse, String tel, String email){
        errors.clear();

        validerNom(nom);
        validerAdresse(adresse);
        validerTel(tel);
        validerEmail(email);

        if(Errors().isEmpty()){
            Agence agence = new Agence(nom, adresse, tel, email);
            Database.getAgenceDAO().save(agence);
            setResultMsg("Agence ajouté avec succès.");
        }
    }

}
