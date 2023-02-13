package presentation.views.clientsFrames;

import dao.Database;
import presentation.models.Compte;
import presentation.models.Log;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class InfoComptePanel extends JPanel {

    private JLabel infoCompteLabel;
    private JLabel dernierOpsLabel;

    private JLabel agenceLabel;
    private JLabel agenceValueLabel;
    private JLabel numCompteLabel;
    private JLabel numCompteValueLabel;
    private JLabel soldeLabel;
    private JLabel soldeValueLabel;
    private JLabel dateCreationLabel;
    private JLabel dateCreationValueLabel;

    private MiniLogsTablePanel logsTablePanel;

    private Compte compteActuel;

    public void refresh(){
        soldeValueLabel.setText(compteActuel.getSolde() + " DH");
        List<Log> logs = Database.getLogDAO().findAccountLogs(compteActuel).stream()
                .sorted(new Comparator<Log>() {
                    @Override
                    public int compare(Log o1, Log o2) {
                        if(o1.getDate().isAfter(o2.getDate())) return -1;
                        else if(o1.getDate().isBefore(o2.getDate())) return 1;
                        else return 0;
                    }
                })
                .limit(10)
                .toList();
        logsTablePanel.getTableModel().initData(logs);
    }

    private void initComponents(){
        Font fontTitle = new Font("Optima", Font.BOLD, 19);
        Font fontValue = new Font("Optima", Font.PLAIN, 19);
        Color colorTitle  = new Color(197, 152, 37);
        agenceLabel =            new JLabel("Nom d'agence du compte : ");
        agenceLabel.setFont(fontTitle);
        agenceLabel.setForeground(colorTitle);
        agenceValueLabel =       new JLabel(compteActuel.getAgence().getNomAgence());
        agenceValueLabel.setFont(fontValue);
        numCompteLabel =         new JLabel("Numero de compte : ");
        numCompteLabel.setForeground(colorTitle);
        numCompteLabel.setFont(fontTitle);
        numCompteValueLabel =    new JLabel(compteActuel.getNumeroCompte());
        numCompteValueLabel.setFont(fontValue);
        soldeLabel =             new JLabel("Solde : ");
        soldeLabel.setForeground(colorTitle);
        soldeLabel.setFont(fontTitle);
        soldeValueLabel =        new JLabel(compteActuel.getSolde().toString() + " DH");
        soldeValueLabel.setFont(fontValue);
        dateCreationLabel =      new JLabel("Date de création : ");
        dateCreationLabel.setForeground(colorTitle);
        dateCreationLabel.setFont(fontTitle);
        dateCreationValueLabel = new JLabel(compteActuel.getDateCreation().toString());
        dateCreationValueLabel.setFont(fontValue);

        List<Log> logs = Database.getLogDAO().findAccountLogs(compteActuel).stream()
                .sorted(new Comparator<Log>() {
                    @Override
                    public int compare(Log o1, Log o2) {
                        if(o1.getDate().isAfter(o2.getDate())) return -1;
                        else if(o1.getDate().isBefore(o2.getDate())) return 1;
                        else return 0;
                    }
                })
                .limit(10)
                .toList();
        logsTablePanel = new MiniLogsTablePanel(logs);

        infoCompteLabel = new JLabel("Info Du Compte :");
        infoCompteLabel.setForeground(Color.GRAY);
        infoCompteLabel.setFont(new Font("Optima", Font.BOLD, 22));

        dernierOpsLabel = new JLabel("Dérnieres Opérations :");
        dernierOpsLabel.setForeground(Color.GRAY);
        dernierOpsLabel.setFont(new Font("Optima", Font.BOLD, 22));

    }

    private void initPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        setMinimumSize(new Dimension(1000, 600));
        setMaximumSize(new Dimension(1000, 600));
        setPreferredSize(new Dimension(1000, 600));

        gbc.gridx = 0;
        gbc.gridy = 0;
        //gbc.anchor = GridBagConstraints.BOTH;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.gridwidth = 2;
        add(infoCompteLabel, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 15, 15);
        add(agenceLabel, gbc);
        gbc.gridy++;
        add(numCompteLabel, gbc);
        gbc.gridy++;
        add(soldeLabel, gbc);
        gbc.gridy++;
        add(dateCreationLabel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 15, 0);
        add(agenceValueLabel, gbc);
        gbc.gridy++;
        add(numCompteValueLabel, gbc);
        gbc.gridy++;
        add(soldeValueLabel, gbc);
        gbc.gridy++;
        add(dateCreationValueLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(dernierOpsLabel, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        add(logsTablePanel, gbc);
    }

    public InfoComptePanel(Compte compte){
        compteActuel = compte;
        initComponents();
        initPanel();
    }

}
