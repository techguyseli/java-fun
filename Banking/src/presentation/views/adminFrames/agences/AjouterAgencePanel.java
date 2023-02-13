package presentation.views.adminFrames.agences;

import forms.AjouterAgenceFormValidator;
import presentation.views.palette.SimpleButton;
import presentation.views.palette.SimpleTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterAgencePanel extends JPanel {

    private JLabel nomLabel;
    private JLabel adresseLabel;
    private JLabel telLabel;
    private JLabel emailLabel;

    private SimpleTextField nomText;
    private SimpleTextField adresseText;
    private SimpleTextField telText;
    private SimpleTextField emailText;

    private SimpleButton addBtn;

    private void initComponents(){
        Font f = new Font("Optima", Font.PLAIN, 18);
        nomLabel = new JLabel("Nom de l'agence");
        nomLabel.setFont(f);
        nomText = new SimpleTextField();
        adresseLabel = new JLabel("Adresse de l'agence");
        adresseLabel.setFont(f);
        adresseText = new SimpleTextField();
        telLabel = new JLabel("Téléphone de l'agence");
        telLabel.setFont(f);
        telText = new SimpleTextField();
        emailLabel = new JLabel("Email de l'agence");
        emailLabel.setFont(f);
        emailText = new SimpleTextField();
        addBtn = new SimpleButton("Ajouter");
    }

    private void initActions(){
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomText.getText();
                String adresse = adresseText.getText();
                String tel = telText.getText();
                String email = emailText.getText();

                AjouterAgenceFormValidator form = new AjouterAgenceFormValidator();

                form.validerAjout(nom, adresse, tel, email);

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
        add(nomLabel, c);

        c.gridy++;
        add(nomText, c);

        c.gridy++;
        add(adresseLabel, c);

        c.gridy++;
        add(adresseText, c);

        c.gridy++;
        add(telLabel, c);

        c.gridy++;
        add(telText, c);

        c.gridy++;
        add(emailLabel, c);

        c.gridy++;
        add(emailText, c);

        c.gridy++;
        add(addBtn, c);
    }

    public AjouterAgencePanel(){
        initComponents();
        initActions();
        initPanel();
    }
}
