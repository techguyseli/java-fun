package presentation.views.adminFrames.clients;

import dao.daoFiles.ClientDAO;
import presentation.models.Client;
import presentation.views.palette.TablePanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class ClientTablePanel extends TablePanel<ClientTableModel>{

    @Override
    protected void initActions() {
        searchPanel.getTxt_search().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    searchPanel.getBtn_search().doClick();
                }
            }
        });
        searchPanel.getBtn_search().addActionListener(e->{
            String keyword = searchPanel.getTxt_search().getText();
    //uncomment
            //var clients =  new ClientDao().findByKeywordLike(keyword);

            //tableModel.initClientsData(clients);

        });
    }



    public ClientTablePanel(List<Client> objects) {
        super(new ClientTableModel(objects));
    }
}
