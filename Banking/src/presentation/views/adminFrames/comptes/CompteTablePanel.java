package presentation.views.adminFrames.comptes;

import dao.Database;
import presentation.models.Agence;
import presentation.models.Compte;
import presentation.views.palette.TableCrudPanel;
import presentation.views.palette.TablePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class CompteTablePanel extends TablePanel<CompteTableModel> {

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
            List<Compte> comptes;
            if(keyword.isEmpty()) comptes = Database.getCompteDAO().findAll();
            else comptes =  Database.getCompteDAO().findByKeywordLike(keyword);
            tableModel.initData(comptes);
        });
    }

    public TableCrudPanel getCrudPanel(){
        return searchPanel.getCrudPanel();
    }



    public CompteTablePanel(List<Compte> objects) {
        super(new CompteTableModel(objects));
    }
}