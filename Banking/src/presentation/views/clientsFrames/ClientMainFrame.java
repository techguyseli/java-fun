package presentation.views.clientsFrames;

import dao.Database;
import presentation.models.Compte;
import presentation.models.Log;
import presentation.models.Sexe;
import presentation.views.palette.SimpleButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class ClientMainFrame extends JFrame{
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Compte compteActuel;

    private InfoComptePanel infoComptePanel;
    private AccountOperationsPanel operationsPanel;
    private LogsTablePanel logsTablePanel;
    private ModifierInfoPanel modifierInfoPanel;
    private SimpleButton choisirCompte;
    private SimpleButton reloadButton;

    private JTabbedPane tabbedMenuPane;

    private JPanel header;
    private JPanel footer;

    private void initComponents(){
        header = new JPanel(new FlowLayout());
        header.setBackground(new Color(208, 146, 45));
        String sexe = compteActuel.getPropriétaire().getSexe().equals(Sexe.HOMME) ? "Mr" : "Mme";
        JLabel headerLabel = new JLabel("Bonjour " + sexe + "." + compteActuel.getPropriétaire().getNom() + ", vous étes connectez au compte " + compteActuel.getNumeroCompte());
        headerLabel.setFont(new Font("Optima", Font.BOLD, 20));
        headerLabel.setForeground(new Color(255, 255, 255));
        header.add(headerLabel);

        infoComptePanel = new InfoComptePanel(compteActuel);
        operationsPanel = new AccountOperationsPanel(compteActuel);
        logsTablePanel = new LogsTablePanel(Database.getLogDAO().findAccountLogs(compteActuel)
                .stream()
                .sorted(new Comparator<Log>() {
                    @Override
                    public int compare(Log o1, Log o2) {
                        if(o1.getDate().isAfter(o2.getDate())) return -1;
                        else if(o1.getDate().isBefore(o2.getDate())) return 1;
                        else return 0;
                    }
                })
                .toList(),
                compteActuel);
        modifierInfoPanel = new ModifierInfoPanel(compteActuel.getPropriétaire());

        tabbedMenuPane = new JTabbedPane();
        tabbedMenuPane.setFont(new Font("Optima", Font.BOLD, 20));
        tabbedMenuPane.addTab("Details de compte", infoComptePanel);
        tabbedMenuPane.addTab("Faire des transactions", operationsPanel);
        tabbedMenuPane.addTab("Histoire des transactions", logsTablePanel);
        tabbedMenuPane.addTab("Changer le mot de passe", modifierInfoPanel);

        footer = new JPanel();
        choisirCompte = new SimpleButton("Choisir un autre compte", Color.WHITE, Color.RED);
        reloadButton = new SimpleButton("Rafraichir");
        footer.setLayout(new BorderLayout());
        footer.add(reloadButton, BorderLayout.CENTER);
        footer.add(choisirCompte, BorderLayout.WEST);
    }


    private void initActions(){
        choisirCompte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientChoixCompteFrame(compteActuel.getPropriétaire());
                dispose();
            }
        });

        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoComptePanel.refresh();
                logsTablePanel.refresh();
            }
        });
    }


    private void initFrame(){
        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(footer, BorderLayout.SOUTH);
        add(tabbedMenuPane, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(screenSize);
        setResizable(false);
        setVisible(true);
    }


    public ClientMainFrame(Compte compteActuel) {
        this.compteActuel = compteActuel;
        initComponents();
        initActions();
        initFrame();
    }
}
