package presentation.views.adminFrames.comptes;

import dao.Database;
import forms.AjouterCompteFormValidator;
import presentation.models.Agence;
import presentation.views.palette.SimpleButton;
import presentation.views.palette.SimpleTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterComptePanel extends JPanel {

    private JLabel agenceLabel;
    private JLabel soldeLabel;
    private JLabel proprietaireLabel;

    private JComboBox<String> agenceCombo;
    private SimpleTextField soldeText;
    private SimpleTextField proprietaireText;

    private SimpleButton addBtn;

    private void initComponents(){
        Font f = new Font("Optima", Font.PLAIN, 18);
        agenceLabel = new JLabel("Nom de l'agence *");
        agenceLabel.setFont(f);
        String[] agences = Database.getAgenceDAO().findAll()
                .stream()
                .map(Agence::getNomAgence)
                .toList().toArray(new String[0]);
        agenceCombo = new JComboBox<>(agences);
        soldeLabel = new JLabel("Solde de compte *");
        soldeLabel.setFont(f);
        soldeText = new SimpleTextField();
        proprietaireLabel = new JLabel("CIN du client *");
        proprietaireLabel.setFont(f);
        proprietaireText = new SimpleTextField();

        addBtn = new SimpleButton("Ajouter");
    }

    private void initActions(){
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String agenceNom = (String) agenceCombo.getSelectedItem();
                String solde = soldeText.getText();
                String cin = proprietaireText.getText();

                AjouterCompteFormValidator form = new AjouterCompteFormValidator();

                form.validerAjout(agenceNom, solde, cin);

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
        add(agenceLabel, c);

        c.gridy++;
        add(agenceCombo, c);

        c.gridy++;
        add(proprietaireLabel, c);

        c.gridy++;
        add(proprietaireText, c);

        c.gridy++;
        add(soldeLabel, c);

        c.gridy++;
        add(soldeText, c);

        c.gridy++;
        add(addBtn, c);
    }

    public AjouterComptePanel(){
        initComponents();
        initActions();
        initPanel();
    }
}
