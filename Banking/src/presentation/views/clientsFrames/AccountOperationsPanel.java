package presentation.views.clientsFrames;

import forms.RetirerFormValidator;
import forms.VerserFormValidator;
import forms.VirementFormValidator;
import presentation.models.Compte;
import presentation.models.Ticket;
import presentation.views.palette.SimpleButton;
import presentation.views.palette.SimpleTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class AccountOperationsPanel extends JPanel {
    private Compte compteActuel;

    private JLabel verserLabel;
    private SimpleTextField verserText;
    private SimpleButton verserBtn;

    private JLabel retirerLabel;
    private SimpleTextField retirerText;
    private SimpleButton retirerBtn;

    private JLabel numCompteLabel;
    private SimpleTextField numCompteText;
    private  JLabel virementMontantLabel;
    private SimpleTextField virementMontantText;
    private SimpleButton virementBtn;

    private void initComponents(){
        Font font = new Font("Optima", Font.PLAIN, 18);

        verserLabel = new JLabel("Montant à vérser");
        verserLabel.setFont(font);
        verserText = new SimpleTextField();
        verserBtn = new SimpleButton("Verser");

        retirerLabel = new JLabel("Montant à retirer");
        retirerLabel.setFont(font);
        retirerText = new SimpleTextField();
        retirerBtn = new SimpleButton("Retirer");

        numCompteLabel = new JLabel("Numéro de compte");
        numCompteLabel.setFont(font);
        numCompteText = new SimpleTextField();
        virementMontantLabel = new JLabel("Montant de virement");
        virementMontantLabel.setFont(font);
        virementMontantText = new SimpleTextField();
        virementBtn = new SimpleButton("Effectuer");

    }

    private void afficherLesErreursEtImprimerLeTiquet(Map<String , String> errors, Ticket ticket, String resultMessage){
        if(!errors.isEmpty()){
            String errorMessage = "";
            int size = errors.keySet().size();
            String[] errorKeys = errors.keySet().toArray(new String[0]);
            for (int i = 0; i < size; i++) errorMessage += errorKeys[i] + " > " + errors.get(errorKeys[i]) + "\n";
            JOptionPane.showMessageDialog(null, errorMessage, "ERRORS", JOptionPane.ERROR_MESSAGE);
        }
        else {
            compteActuel = ticket.getCompte();
            int option = JOptionPane.showConfirmDialog(null, resultMessage, "Sauvegarder Le Ticket", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null, "S'il vous plait choisir un fichier pour sauvegarder le ticket.", "", JOptionPane.INFORMATION_MESSAGE);
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int fileChooserOption = fileChooser.showSaveDialog(null);

                if (fileChooserOption != JFileChooser.APPROVE_OPTION) return;

                String selectedFolderStrPath = fileChooser.getSelectedFile().getAbsolutePath();
                if(selectedFolderStrPath.equals("")) return;

                Path selectedFolder = Paths.get(selectedFolderStrPath);
                String message = ticket.save(selectedFolder);
                JOptionPane.showMessageDialog(null, message, "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void initActions(){
        verserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String montantS = verserText.getText();
                VerserFormValidator form = new VerserFormValidator();

                Ticket ticket  = form.validerVersement(compteActuel, montantS);

                afficherLesErreursEtImprimerLeTiquet(form.Errors(), ticket, form.ResultMsg());
            }
        });

        retirerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String montantS = retirerText.getText();
                RetirerFormValidator form = new RetirerFormValidator();

                Ticket ticket  = form.validerRetrait(compteActuel, montantS);

                afficherLesErreursEtImprimerLeTiquet(form.Errors(), ticket, form.ResultMsg());
            }
        });

        virementBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String montantS = virementMontantText.getText();
                String numCompte = numCompteText.getText();

                VirementFormValidator form = new VirementFormValidator();

                Ticket ticket  = form.validerVirement(compteActuel, montantS, numCompte);

                afficherLesErreursEtImprimerLeTiquet(form.Errors(), ticket, form.ResultMsg());
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
        add(verserLabel, gbc);
        gbc.gridy++;
        add(verserText, gbc);
        gbc.insets = new Insets(0, 0, 45, 0);
        gbc.gridy++;
        add(verserBtn, gbc);

        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.gridy++;
        add(retirerLabel, gbc);
        gbc.gridy++;
        add(retirerText, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 45, 0);
        add(retirerBtn, gbc);

        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.gridy++;
        add(numCompteLabel, gbc);
        gbc.gridy++;
        add(numCompteText, gbc);
        gbc.gridy++;
        add(virementMontantLabel, gbc);
        gbc.gridy++;
        add(virementMontantText, gbc);
        gbc.gridy++;
        add(virementBtn, gbc);
    }

    public AccountOperationsPanel(Compte compteActuel) {
        this.compteActuel = compteActuel;
        initComponents();
        initActions();
        initPanel();
    }
}
