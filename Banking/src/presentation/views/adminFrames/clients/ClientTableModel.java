package presentation.views.adminFrames.clients;

import presentation.models.Client;
import presentation.views.palette.TableModel;

import java.util.List;

public class ClientTableModel extends TableModel<Client>{
    @Override
    public void initData(List<Client> objects) {
        data = new Object[objects.size()][columnsNames.length];

        int i = 0;
        for(Client client : objects){

            data[i][0] =  client.getId();
            data[i][1] =  client.getNom();
            data[i][2] =  client.getPrenom();
            data[i][3] =  client.getLogin();
            data[i][4] =  client.getEmail();
            data[i][5] =  client.getCin();
            data[i][6] =  client.getTel();
            data[i][7] =  client.getSexe();

            i++;
        }

        this.fireTableDataChanged();
    }

    public ClientTableModel(List<Client> objects) {
        super(objects, "ID", "PRENOM", "NOM", "LOGIN", "EMAIL", "CIN", "TEL", "SEXE");
    }
}
