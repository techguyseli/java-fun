package presentation.views.adminFrames.clients;

import dao.Database;
import presentation.models.Client;
import presentation.views.adminFrames.AdminChoixCRUDFrame;
import presentation.views.palette.SimpleButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientsFrame extends JFrame{

    private JLabel headerLabel;
    private JPanel header;

    private JTabbedPane tabbedPane;

    private ClientTablePanel tablePanel;
    private AjouterClientPanel ajouterClientPanel;
    private ModifierClientPanel modifierClientPanel;

    private SimpleButton reloadButton;

    private JPanel footer;

    private void initComponents(){
        headerLabel = new JLabel("Session [ADMIN] - CRUD Clients");
        headerLabel.setFont(new Font("Optima", Font.BOLD, 20));
        headerLabel.setForeground(new Color(255, 255, 255));
        header = new JPanel(new FlowLayout());
        header.setBackground(new Color(208, 146, 45));
        header.add(headerLabel);

        tablePanel = new ClientTablePanel(Database.getClientDAO().findAll());
        ajouterClientPanel = new AjouterClientPanel();
        JScrollPane addFormScrollPane = new JScrollPane(ajouterClientPanel);
        modifierClientPanel = new ModifierClientPanel();
        JScrollPane modifyFormScrollPane = new JScrollPane(modifierClientPanel);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Optima", Font.BOLD, 20));
        tabbedPane.addTab("Liste des clients", tablePanel);
        tabbedPane.addTab("Ajouter un client", addFormScrollPane);
        tabbedPane.addTab("Modifier un client", modifyFormScrollPane);

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
                    JOptionPane.showMessageDialog(null, "S'il vous plait séléctioner un client depuis le tableau.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Long clientID = (Long) tablePanel.getTableModel().getValueAt(index, 0);
                Client client = Database.getClientDAO().findById(clientID);
                modifierClientPanel.refreshAndShow(client);
                tabbedPane.setSelectedIndex(2);
            }
        });
        tablePanel.getCrudPanel().deleteBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tablePanel.getTable().getSelectedRow();
                if(index == -1){
                    JOptionPane.showMessageDialog(null, "S'il vous plait séléctioner un client depuis le tableau.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int option  = JOptionPane.showConfirmDialog(null, "Voullez-vous vraiment supprimer le client "
                                + tablePanel.getTableModel().getValueAt(index, 1) + " "
                                + tablePanel.getTableModel().getValueAt(index, 2) + " "
                                + " ?\nCette action va supprimer tout les comptes appartenant à ce client et leurs logs.",
                        "AVERTISSEMENT",
                        JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION){
                    Long clientID = (Long) tablePanel.getTableModel().getValueAt(index, 0);
                    Database.getClientDAO().deleteById(clientID);
                    tablePanel.getTableModel().initData(Database.getClientDAO().findAll());
                }
            }
        });
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.getTableModel().initData(Database.getClientDAO().findAll());
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

    public ClientsFrame(){
        initComponents();
        initActions();
        initFrame();
    }

}