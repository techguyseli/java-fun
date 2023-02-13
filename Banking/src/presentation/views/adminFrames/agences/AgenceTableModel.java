package presentation.views.adminFrames.agences;

import presentation.models.Agence;
import presentation.views.palette.TableModel;

import java.util.List;

public class AgenceTableModel extends TableModel<Agence>{
    @Override
    public void initData(List<Agence> objects) {
        data = new Object[objects.size()][columnsNames.length];

        int i = 0;
        for(Agence agence : objects){

            data[i][0] =  agence.getIdAgence();
            data[i][1] =  agence.getNomAgence();
            data[i][2] =  agence.getAdresseAgence();
            data[i][3] =  agence.getTelAgence();
            data[i][4] =  agence.getEmailAgence();

            i++;
        }

        this.fireTableDataChanged();
    }

    public AgenceTableModel(List<Agence> objects) {
        super(objects, "ID", "Nom", "Adresse", "Tél", "Email");
    }
}

