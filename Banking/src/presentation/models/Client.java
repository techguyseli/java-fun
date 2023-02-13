package presentation.models;


public class Client extends Utilisateur {

    // null
    private String email;

    // unique
    private String cin;

    // null
    private String tel;

    private Sexe sexe;


    public String       getCin() {
        return cin;
    }
    public String       getTel() {
        return tel;
    }
    public String       getEmail() {
        return email;
    }
    public Sexe getSexe() {
        return sexe;
    }
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }
    public void         setEmail(String email) {
        this.email = email;
    }
    public void         setTel(String tel) {
        this.tel = tel;
    }
    public void         setCin(String cin) {
        this.cin = cin;
    }

    public Client(String login, String pass, String n, String p, String mail, String cin, String tel, Sexe sexe){
        super(p, n, login, pass, Role.CLIENT);
        setTel(tel);
        setEmail(mail);
        setCin(cin);
        setSexe(sexe);
    }
    public Client(Long id, String login, String pass, String n, String p, String mail, String cin, String tel, Sexe sexe){
        super(id, p, n, login, pass, Role.CLIENT);
        setTel(tel);
        setEmail(mail);
        setCin(cin);
        setSexe(sexe);
    }

    @Override
    public String toString() {
        return super.toString() + "\t\t\t" + email + "\t\t\t" + cin + "\t\t\t" + tel + "\t\t\t" + sexe + "\n";
    }

}
