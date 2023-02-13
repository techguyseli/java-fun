package presentation.views.clientsFrames;

import presentation.models.Log;
import presentation.views.palette.TableModel;

import java.util.List;

public class LogsTableModel extends TableModel<Log>{
    @Override
    public void initData(List<Log> objects) {
        data = new Object[objects.size()][columnsNames.length];

        int i = 0;
        for(Log log : objects){

            data[i][0] =  log.getId();
            data[i][1] =  log.getType();
            data[i][2] =  log.getMessage();
            data[i][3] =  log.getDate();
            data[i][4] =  log.getCompte().getNumeroCompte();

            i++;
        }

        this.fireTableDataChanged();
    }

    public LogsTableModel(List<Log> objects) {
        super(objects, "ID Log", "Type", "Message", "Date and Time", "ID Compte");
    }
}
