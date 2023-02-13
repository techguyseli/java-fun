package presentation.views.clientsFrames;

import presentation.models.Log;
import presentation.views.palette.TableModel;

import java.util.List;

public class MiniLogsTableModel extends TableModel<Log>{
    @Override
    public void initData(List<Log> objects) {
        data = new Object[objects.size()][columnsNames.length];

        int i = 0;
        for(Log log : objects){

            data[i][0] =  log.getType().toString();
            data[i][1] =  log.getMessage();
            data[i][2] =  log.getDate().toString().substring(0, 10);

            i++;
        }

        this.fireTableDataChanged();
    }

    public MiniLogsTableModel(List<Log> objects) {
        super(objects, "Type", "Message", "Date");
    }
}
