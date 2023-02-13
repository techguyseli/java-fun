package presentation.views.adminFrames.logs;

import dao.Database;
import presentation.models.Log;
import presentation.views.palette.TablePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class LogsTablePanel extends TablePanel<LogsTableModel>{

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
            List<Log> logs;
            if(keyword.isEmpty()) logs = Database.getLogDAO().findAll()
                    .stream()
                    .sorted((o1, o2) -> {
                        if(o1.getDate().isAfter(o2.getDate())) return -1;
                        else if(o1.getDate().isBefore(o2.getDate())) return 1;
                        else return 0;
                    })
                    .toList();
            else logs =  Database.getLogDAO().findByKeywordLike(keyword)
                    .stream()
                    .sorted((o1, o2) -> {
                        if(o1.getDate().isAfter(o2.getDate())) return -1;
                        else if(o1.getDate().isBefore(o2.getDate())) return 1;
                        else return 0;
                    })
                    .toList();
            tableModel.initData(logs);
        });
    }



    public LogsTablePanel() {
        super(new LogsTableModel(Database.getLogDAO().findAll()
                .stream()
                .sorted((o1, o2) -> {
                    if(o1.getDate().isAfter(o2.getDate())) return -1;
                    else if(o1.getDate().isBefore(o2.getDate())) return 1;
                    else return 0;
                })
                .toList()));
        searchPanel.remove(searchPanel.getCrudPanel());
    }
}
