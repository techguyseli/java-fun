package presentation.views.adminFrames.clients;

import forms.ModifierClientFormValidator;
import presentation.models.Client;
import presentation.models.Sexe;
import presentation.views.palette.SimpleButton;
import presentation.views.palette.SimplePasswordField;
import presentation.views.palette.SimpleTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierClientPanel extends JPanel {

    private Client client;

    private JLabel nomLabel;
    private JLabel prenomLabel;
    private JLabel loginLabel;
    private JLabel mdpLabel;
    private JLabel mdpConfirmationLabel;
    private JLabel emailLabel;
    private JLabel cinLabel;
    private JLabel telLabel;
    private JLabel sexeLabel;

    private SimpleTextField nomText;
    private SimpleTextField prenomText;
    private SimpleTextField loginText;
    private SimplePasswordField mdpPass;
    private SimplePasswordField mdpConfirmationPass;
    private SimpleTextField cinText;
    private SimpleTextField telText;
    private SimpleTextField emailText;
    private JComboBox<Sexe> sexeCombo;

    private SimpleButton modBtn;
    private SimpleButton clearBtn;

    private void initComponents(){
        Font f = new Font("Optima", Font.PLAIN, 18);
        nomLabel = new JLabel("Nom du client *");
        nomLabel.setFont(f);
        prenomLabel = new JLabel("Prénom du client *");
        prenomLabel.setFont(f);
        loginLabel = new JLabel("Login du client *");
        loginLabel.setFont(f);
        mdpLabel = new JLabel("Mot de passe du client");
        mdpLabel.setFont(f);
        mdpConfirmationLabel = new JLabel("Confirmation de mot de passe");
        mdpConfirmationLabel.setFont(f);
        emailLabel = new JLabel("Email du client");
        emailLabel.setFont(f);
        cinLabel = new JLabel("CIN du client *");
        cinLabel.setFont(f);
        telLabel = new JLabel("Numéro de téléphone du client");
        telLabel.setFont(f);
        sexeLabel = new JLabel("Sexe *");
        sexeLabel.setFont(f);

        nomText = new SimpleTextField();
        prenomText = new SimpleTextField();
        loginText = new SimpleTextField();
        mdpPass = new SimplePasswordField();
        mdpConfirmationPass = new SimplePasswordField();
        cinText = new SimpleTextField();
        telText = new SimpleTextField();
        emailText = new SimpleTextField();
        sexeCombo = new JComboBox<>(Sexe.values());
        sexeCombo.setFont(f);

        modBtn = new SimpleButton("Modifier");
        clearBtn = new SimpleButton("Annuler");
    }

    public void refreshAndShow(Client client){
        this.client = client;
        nomText.setText(client.getNom());
        prenomText.setText(client.getPrenom());
        loginText.setText(client.getLogin());
        cinText.setText(client.getCin());
        telText.setText((client.getTel() == null ? "" : client.getTel()));
        emailText.setText((client.getEmail() == null ? "" : client.getEmail()));
        sexeCombo.setSelectedItem(client.getSexe());
        mdpPass.setText("");
        mdpConfirmationPass.setText("");
    }

    private void clearAndHide(){
        this.client = null;
        nomText.setText("");
        prenomText.setText("");
        loginText.setText("");
        cinText.setText("");
        telText.setText("");
        emailText.setText("");
        mdpPass.setText("");
        mdpConfirmationPass.setText("");
    }

    private void initActions(){
        modBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(client == null){
                    JOptionPane.showMessageDialog(null, "S'il vous plait séléctioner d'abord un client depuis le tableau !", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String nom = nomText.getText();
                String prenom = prenomText.getText();
                String login = loginText.getText();
                String mdp = String.valueOf(mdpPass.getPassword());
                String mdpConfirmation = String.valueOf(mdpConfirmationPass.getPassword());
                String email = emailText.getText();
                String cin = cinText.getText();
                String tel = telText.getText();
                Sexe sexe = (Sexe) sexeCombo.getSelectedItem();

                ModifierClientFormValidator form = new ModifierClientFormValidator();

                form.validerModification(client, nom, prenom, login, mdp, mdpConfirmation,
                        email, cin, tel, sexe);

                if(!form.Errors().isEmpty()){
                    String errorMessage = "";
                    int size = form.Errors().keySet().size();
                    String[] errorKeys = form.Errors().keySet().toArray(new String[0]);
                    for (int i = 0; i < size; i++) errorMessage += errorKeys[i] + " > " + form.Errors().get(errorKeys[i]) + "\n";
                    JOptionPane.showMessageDialog(null, errorMessage, "ERRORS", JOptionPane.ERROR_MESSAGE);
                }
                else JOptionPane.showMessageDialog(null, form.ResultMsg(), "", JOptionPane.INFORMATION_MESSAGE);
                clearAndHide();
            }
        });
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAndHide();
            }
        });
    }

    private void initPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.gridx = 0;
        c.insets = new Insets(0, 0, 15, 0);


        add(cinLabel, c);
        c.gridy++;
        add(cinText, c);

        c.gridy++;
        add(nomLabel, c);
        c.gridy++;
        add(nomText, c);

        c.gridy++;
        add(prenomLabel, c);
        c.gridy++;
        add(prenomText, c);

        c.gridy++;
        add(loginLabel, c);
        c.gridy++;
        add(loginText, c);

        c.gridy++;
        add(mdpLabel, c);
        c.gridy++;
        add(mdpPass, c);

        c.gridy++;
        add(mdpConfirmationLabel, c);
        c.gridy++;
        add(mdpConfirmationPass, c);

        c.gridy++;
        add(telLabel, c);
        c.gridy++;
        add(telText, c);

        c.gridy++;
        add(emailLabel, c);
        c.gridy++;
        add(emailText, c);

        c.gridy++;
        add(sexeLabel, c);
        c.gridy++;
        add(sexeCombo, c);

        c.gridy++;
        add(modBtn, c);
        c.gridy++;
        add(clearBtn, c);
    }

    public ModifierClientPanel(){
        initComponents();
        initActions();
        initPanel();
    }
}
