package presentation.views.clientsFrames;

import dao.Database;
import presentation.models.Client;
import presentation.models.Compte;
import presentation.views.palette.VerticalMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClientChoixCompteFrame extends JFrame{
    private VerticalMenuPanel accountsMenu;
    private final int width = 300;
    private final int height = 600;
    private List<Compte> comptes;


    private void initActions(){
        accountsMenu.getButtons().forEach((title, button) -> {
            button.addActionListener(e -> {
                new ClientMainFrame(
                        comptes.stream()
                                .filter(compte ->
                                        compte.getNumeroCompte().equals(title))
                                .findFirst().orElse(null)
                );
                dispose();
            });
        });
    }


    private void initComponents(String...numsComptes){
        setLayout(new BorderLayout());
        accountsMenu = new VerticalMenuPanel(width, numsComptes);
        JLabel label = new JLabel("Choisir un compte:");
        label.setFont(new Font("Optima", Font.PLAIN, 20));
        add(accountsMenu, BorderLayout.CENTER);
        add(label, BorderLayout.NORTH);
        accountsMenu.setVisible(true);
    }


    private void initFrame(){
        setTitle("Choisir un compte");
        setSize(width, height);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - width)/2,(screenSize.height - height)/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }


    public ClientChoixCompteFrame(Client client) {
        comptes = Database.getCompteDAO().findAccountsByClient(client);
        String[] array = new String[comptes.size()];
        initComponents(comptes.stream().map(Compte::getNumeroCompte).toList().toArray(array));
        initActions();
        initFrame();

    }
}
