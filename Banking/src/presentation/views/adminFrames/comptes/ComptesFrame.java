package presentation.views.adminFrames.comptes;

import dao.Database;
import presentation.views.adminFrames.AdminChoixCRUDFrame;
import presentation.views.palette.SimpleButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComptesFrame extends JFrame{

    private JLabel headerLabel;
    private JPanel header;

    private JTabbedPane tabbedPane;

    private CompteTablePanel tablePanel;
    private AjouterComptePanel ajouterComptePanel;

    private SimpleButton reloadButton;

    private JPanel footer;

    private void initComponents(){
        headerLabel = new JLabel("Session [ADMIN] - CRUD Comptes");
        headerLabel.setFont(new Font("Optima", Font.BOLD, 20));
        headerLabel.setForeground(new Color(255, 255, 255));
        header = new JPanel(new FlowLayout());
        header.setBackground(new Color(208, 146, 45));
        header.add(headerLabel);

        tablePanel = new CompteTablePanel(Database.getCompteDAO().findAll());
        ajouterComptePanel = new AjouterComptePanel();

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Optima", Font.BOLD, 20));
        tabbedPane.addTab("Liste des comptes", tablePanel);
        tabbedPane.addTab("Ajouter une compte", ajouterComptePanel);

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
        tablePanel.getCrudPanel().remove(tablePanel.getCrudPanel().editBtn());
        tablePanel.getCrudPanel().deleteBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int index = tablePanel.getTable().getSelectedRow();
                    if(index == -1){
                        JOptionPane.showMessageDialog(null, "S'il vous plait séléctioner un compte depuis le tableau.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int option  = JOptionPane.showConfirmDialog(null, "Voullez-vous vraiment supprimer le compte "
                            + tablePanel.getTableModel().getValueAt(index, 0)
                            + " ?\nCette action va supprimer tout ces logs.",
                            "AVERTISSEMENT",
                            JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION){
                        String compteID = (String) tablePanel.getTableModel().getValueAt(index, 0);
                        Database.getCompteDAO().deleteById(compteID);
                        tablePanel.getTableModel().initData(Database.getCompteDAO().findAll());
                    }
            }
        });
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.getTableModel().initData(Database.getCompteDAO().findAll());
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

    public ComptesFrame(){
        initComponents();
        initActions();
        initFrame();
    }

}
