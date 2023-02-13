package presentation.views.adminFrames.agences;

import dao.Database;
import presentation.models.Agence;
import presentation.views.adminFrames.AdminChoixCRUDFrame;
import presentation.views.palette.SimpleButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgencesFrame extends JFrame{

    private JLabel headerLabel;
    private JPanel header;

    private JTabbedPane tabbedPane;

    private AgenceTablePanel tablePanel;
    private AjouterAgencePanel ajouterAgencePanel;
    private ModifierAgencePanel modifierAgencePanel;

    private SimpleButton reloadButton;

    private JPanel footer;

    private void initComponents(){
        headerLabel = new JLabel("Session [ADMIN] - CRUD Agences");
        headerLabel.setFont(new Font("Optima", Font.BOLD, 20));
        headerLabel.setForeground(new Color(255, 255, 255));
        header = new JPanel(new FlowLayout());
        header.setBackground(new Color(208, 146, 45));
        header.add(headerLabel);

        tablePanel = new AgenceTablePanel(Database.getAgenceDAO().findAll());
        ajouterAgencePanel = new AjouterAgencePanel();
        modifierAgencePanel = new ModifierAgencePanel();

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Optima", Font.BOLD, 20));
        tabbedPane.addTab("Liste des Agences", tablePanel);
        tabbedPane.addTab("Ajouter une agence", ajouterAgencePanel);
        tabbedPane.addTab("Modifier une agence", modifierAgencePanel);

        reloadButton = new SimpleButton("Rafraîchir");
        footer = new JPanel(new BorderLayout());
        footer.add(reloadButton, BorderLayout.CENTER);

    }

    private void initActions(){
        tablePanel.getCrudPanel().addBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1);
            }
        });
        tablePanel.getCrudPanel().editBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tablePanel.getTable().getSelectedRow();
                if(index == -1){
                    JOptionPane.showMessageDialog(null, "S'il vous plait séléctioner une agence depuis le tableau.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Long agenceID = (Long) tablePanel.getTableModel().getValueAt(index, 0);
                Agence agence = Database.getAgenceDAO().findById(agenceID);
                modifierAgencePanel.refreshAndShow(agence);
                tabbedPane.setSelectedIndex(2);
            }
        });
        tablePanel.getCrudPanel().deleteBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int index = tablePanel.getTable().getSelectedRow();
                    if(index == -1){
                        JOptionPane.showMessageDialog(null, "S'il vous plait séléctioner une agence depuis le tableau.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int option  = JOptionPane.showConfirmDialog(null, "Voullez-vous vraiment supprimer l'agence "
                            + tablePanel.getTableModel().getValueAt(index, 1)
                            + " ?\nCette action va supprimer tout les comptes créer dans cette agence.",
                            "AVERTISSEMENT",
                            JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION){
                        Long agenceID = (Long) tablePanel.getTableModel().getValueAt(index, 0);
                        Database.getAgenceDAO().deleteById(agenceID);
                        tablePanel.getTableModel().initData(Database.getAgenceDAO().findAll());
                    }
            }
        });
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.getTableModel().initData(Database.getAgenceDAO().findAll());
            }
        });
    }

    private void initFrame(){
        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setResizable(false);
        setVisible(true);
    }

    public AgencesFrame(){
        initComponents();
        initActions();
        initFrame();
    }

}
