package presentation.views.adminFrames.agences;

import dao.Database;
import presentation.models.Agence;
import presentation.models.Compte;
import presentation.models.Log;
import presentation.views.palette.TableCrudPanel;
import presentation.views.palette.TablePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class AgenceTablePanel extends TablePanel<AgenceTableModel> {

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
            List<Agence> agences;
            if(keyword.isEmpty()) agences = Database.getAgenceDAO().findAll();
            else agences =  Database.getAgenceDAO().findByKeywordLike(keyword);
            tableModel.initData(agences);
        });
    }

    public TableCrudPanel getCrudPanel(){
        return searchPanel.getCrudPanel();
    }



    public AgenceTablePanel(List<Agence> objects) {
        super(new AgenceTableModel(objects));
    }
}