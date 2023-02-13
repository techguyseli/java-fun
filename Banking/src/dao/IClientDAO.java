package dao;


import presentation.models.Client;

public interface IClientDAO extends IDAO<Client, Long>{
    Client findClientByAccountID(String accountID);
    Client findByCIN(String cin);
}
