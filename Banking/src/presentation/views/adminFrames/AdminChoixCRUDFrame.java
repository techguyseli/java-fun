package presentation.views.adminFrames;

import presentation.views.adminFrames.agences.AgencesFrame;
import presentation.views.adminFrames.clients.ClientsFrame;
import presentation.views.adminFrames.comptes.ComptesFrame;
import presentation.views.adminFrames.logs.LogsFrame;
import presentation.views.palette.VerticalMenuPanel;

import javax.swing.*;
import java.awt.*;

public class AdminChoixCRUDFrame extends JFrame{

    private VerticalMenuPanel crudMenu;

    private final int width = 300;
    private final int height = 600;


    private void initActions(){
        crudMenu.getButtons().forEach((title, button) -> {
            if(title.equals("Agences"))
                button.addActionListener(e -> {
                    new AgencesFrame();
                });
            else if (title.equals("Clients"))
                button.addActionListener(e -> {
                    new ClientsFrame();
                });
            else if (title.equals("Comptes"))
                button.addActionListener(e -> {
                    new ComptesFrame();
                });
            else button.addActionListener(e -> {
                    new LogsFrame();
                });
        });
    }


    private void initComponents(){
        setLayout(new BorderLayout());
        crudMenu = new VerticalMenuPanel(width, "Agences", "Clients", "Comptes", "Logs");
        JLabel label = new JLabel("Choisir une page de CRUD:");
        label.setFont(new Font("Optima", Font.PLAIN, 20));
        add(crudMenu, BorderLayout.CENTER);
        add(label, BorderLayout.NORTH);
        crudMenu.setVisible(true);
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


    public AdminChoixCRUDFrame() {
        initComponents();
        initActions();
        initFrame();

    }
}
