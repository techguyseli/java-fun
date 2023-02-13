package presentation.views.clientsFrames;

import forms.ChangerMDPFormValidator;
import presentation.models.Client;
import presentation.views.palette.SimpleButton;
import presentation.views.palette.SimplePasswordField;
import presentation.views.palette.SimpleTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierInfoPanel extends JPanel {
    private Client clientActuel;

    private JLabel mdpActuelLabel;
    private SimplePasswordField mdpActuelText;
    private JLabel nouveauMdpLabel;
    private SimplePasswordField nouveauMdpText;
    private JLabel mdpConfirmationLabel;
    private SimplePasswordField mdpConfirmationText;
    private SimpleButton validerBtn;

    private void initComponents(){

        Font font = new Font("Optima", Font.PLAIN, 18);

        mdpActuelLabel = new JLabel("Mot de passe actuel");
        mdpActuelLabel.setFont(font);
        mdpActuelText = new SimplePasswordField();
        nouveauMdpLabel = new JLabel("Nouveau mot de passe");
        nouveauMdpLabel.setFont(font);
        nouveauMdpText = new SimplePasswordField();
        mdpConfirmationLabel = new JLabel("Confirmer le mot de passe");
        mdpConfirmationLabel.setFont(font);
        mdpConfirmationText = new SimplePasswordField();
        validerBtn = new SimpleButton("Changer");

    }

    private void initActions(){

        validerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mdpActuel = String.valueOf(mdpActuelText.getPassword());
                String nouveauMdp = String.valueOf(nouveauMdpText.getPassword());
                String confirmationMdp = String.valueOf(mdpConfirmationText.getPassword());

                ChangerMDPFormValidator form = new ChangerMDPFormValidator();

                clientActuel = form.validerMdp(clientActuel, mdpActuel, nouveauMdp, confirmationMdp);

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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 15, 0);

        add(mdpActuelLabel, gbc);
        gbc.gridy++;
        add(mdpActuelText, gbc);
        gbc.gridy++;
        add(nouveauMdpLabel, gbc);
        gbc.gridy++;
        add(nouveauMdpText, gbc);
        gbc.gridy++;
        add(mdpConfirmationLabel, gbc);
        gbc.gridy++;
        add(mdpConfirmationText, gbc);
        gbc.gridy++;
        add(validerBtn, gbc);
    }

    public ModifierInfoPanel(Client clientActuel) {
        this.clientActuel = clientActuel;
        initComponents();
        initActions();
        initPanel();
    }
}
