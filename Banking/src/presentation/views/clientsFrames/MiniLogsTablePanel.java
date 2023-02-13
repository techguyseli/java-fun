package presentation.views.clientsFrames;

import presentation.models.Log;
import presentation.views.palette.TablePanel;

import java.awt.*;
import java.util.List;

public class MiniLogsTablePanel extends TablePanel<MiniLogsTableModel>{

    @Override
    protected void initActions() {
    }

    public MiniLogsTablePanel(List<Log> objects) {
        super(new MiniLogsTableModel(objects));
        table.setFont(new Font("Optima", Font.BOLD, 14));
        remove(searchPanel);
    }
}
