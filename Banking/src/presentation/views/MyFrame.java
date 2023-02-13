package presentation.views;


import dao.IClientDAO;
import presentation.views.adminFrames.clients.ClientTablePanel;
import presentation.views.palette.HeaderPanel;
import presentation.views.palette.VerticalMenuPanel;
import presentation.views.palette.TablePanel;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame{

    ClassLoader cl = getClass().getClassLoader();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Container  container;
    TablePanel tablePanel;
    HeaderPanel header;
    VerticalMenuPanel menuPanel;
    IClientDAO clients;

    private void initPanels(){
        tablePanel = new ClientTablePanel(clients.findAll());
        menuPanel = new VerticalMenuPanel("Ajouter","Modifier","Supprimer","Chercher");

        header = new HeaderPanel(new ImageIcon(cl.getResource("images/icons/menu.png")),
                new ImageIcon(cl.getResource("images/icons/bankIcon64.png")),
                Color.WHITE);

        initActions();
    }

    private void initContainer(){
        initPanels();
        container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(tablePanel, BorderLayout.CENTER);
        container.add(menuPanel, BorderLayout.WEST);
        container.add(header, BorderLayout.NORTH);
    }

    private void initActions(){

       var buttons = menuPanel.getButtons();

       buttons.get("Ajouter")
               .addActionListener(click -> System.out.println("btn Ajouter cliqué"));

        buttons.get("Modifier")
                .addActionListener(click -> System.out.println("btn Modifier cliqué"));

        buttons.get("Supprimer")
                .addActionListener(click -> System.out.println("btn Supprimé cliqué"));

        buttons.get("Chercher")
                .addActionListener(click -> System.out.println("btn Chercher cliqué"));

        header.getBtn_west().addActionListener(e->{

            if(menuPanel.isVisible()) menuPanel.setVisible(false);
            else menuPanel.setVisible(true);
        });

    }


    public MyFrame(String title, IClientDAO clients){
        this.clients = clients;
        initContainer();
        setTitle(title);
        setLocation(0,0);
        setSize(screenSize.width,screenSize.height-90);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
