package presentation.views.adminFrames.comptes;

import presentation.models.Agence;
import presentation.models.Compte;
import presentation.views.palette.TableModel;

import java.util.List;

public class CompteTableModel extends TableModel<Compte>{
    @Override
    public void initData(List<Compte> objects) {
        data = new Object[objects.size()][columnsNames.length];

        int i = 0;
        for(Compte compte : objects){

            data[i][0] =  compte.getNumeroCompte();
            data[i][1] =  compte.getPropriétaire().getId() + " : " + compte.getPropriétaire().getNomComplet();
            data[i][2] =  compte.getAgence().getNomAgence();
            data[i][3] =  compte.getSolde() + " DH";
            data[i][4] =  compte.getDateCreation();

            i++;
        }

        this.fireTableDataChanged();
    }

    public CompteTableModel(List<Compte> objects) {
        super(objects, "Numéro de compte", "ID / Nom complet de client", "Nom d'agence", "Solde", "Date et temps de création");
    }
}

