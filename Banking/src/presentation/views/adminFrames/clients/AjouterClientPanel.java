package presentation.views.adminFrames.clients;

import forms.AjouterClientFormValidator;
import presentation.models.Sexe;
import presentation.views.palette.SimpleButton;
import presentation.views.palette.SimplePasswordField;
import presentation.views.palette.SimpleTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterClientPanel extends JPanel {

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

    private SimpleButton addBtn;

    private void initComponents(){
        Font f = new Font("Optima", Font.PLAIN, 18);
        nomLabel = new JLabel("Nom du client *");
        nomLabel.setFont(f);
        prenomLabel = new JLabel("Prénom du client *");
        prenomLabel.setFont(f);
        loginLabel = new JLabel("Login du client *");
        loginLabel.setFont(f);
        mdpLabel = new JLabel("Mot de passe du client *");
        mdpLabel.setFont(f);
        mdpConfirmationLabel = new JLabel("Confirmation de mot de passe *");
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

        addBtn = new SimpleButton("Ajouter");
    }

    private void initActions(){
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomText.getText();
                String prenom = prenomText.getText();
                String login = loginText.getText();
                String mdp = String.valueOf(mdpPass.getPassword());
                String mdpConfirmation = String.valueOf(mdpConfirmationPass.getPassword());
                String email = emailText.getText();
                String cin = cinText.getText();
                String tel = telText.getText();
                Sexe sexe = (Sexe) sexeCombo.getSelectedItem();

                AjouterClientFormValidator form = new AjouterClientFormValidator();

                form.validerAjout(nom, prenom, login, mdp, mdpConfirmation,
                        email, cin, tel, sexe);

                if(!form.Errors().isEmpty()){
                    String errorMessage = "";
                    int size = form.Errors().keySet().size();
                    String[] errorKeys = form.Errors().keySet().toArray(new String[0]);
                    for (int i = 0; i < size; i++) errorMessage += errorKeys[i] + " > " + form.Errors().get(errorKeys[i]) + "\n";
                    JOptionPane.showMessageDialog(null, errorMessage, "ERRORS", JOptionPane.ERROR_MESSAGE);
                }
                else JOptionPane.showMessageDialog(null, form.ResultMsg(), "", JOptionPane.INFORMATION_MESSAGE);
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
        add(addBtn, c);
    }

    public AjouterClientPanel(){
        initComponents();
        initActions();
        initPanel();
    }
}
