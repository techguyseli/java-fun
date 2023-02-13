package dao;

import presentation.models.Agence;
import presentation.models.Client;
import presentation.models.Compte;

import java.util.List;

public interface ICompteDAO extends IDAO<Compte, String> {
    List<Compte> findAccountsByClient(Client client);
    List<Compte> findAccountsByAgence(Agence agence);
    List<Compte> deleteByAgence(Agence agance);
    List<Compte> deleteByClient(Client client);
}
