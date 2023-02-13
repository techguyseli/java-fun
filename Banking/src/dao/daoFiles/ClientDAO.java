package dao.daoFiles;

import dao.IClientDAO;
import presentation.models.Client;
import presentation.models.Compte;
import presentation.models.Sexe;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientDAO implements IClientDAO {

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();


        try {
           List<String> lines = Files.readAllLines(FileBasePaths.CLIENT_TABLE, StandardCharsets.UTF_8);
            lines.remove(0);

            if(!lines.isEmpty())
                       clients=
                       lines
                               .stream()
                               .map(line->{
                                   Client cl;
                                   String[] st = line.split("\t\t\t");
                                   long     id              =   Long.parseLong(st[0]);
                                   String   prenom          =   st[1];
                                   String   nom             =   st[2];
                                   String   login           =   st[3];
                                   String   pass            =   st[4];
                                   String   email           =   st[5];
                                   String   cin             =   st[6];
                                   String   tel             =   st[7];
                                   String   sexe            =   st[8];

                                   Sexe sex = null;

                                   if(email.equalsIgnoreCase("null")) email =null;
                                   if(tel.equalsIgnoreCase("null")) tel =null;
                                   if(!sexe.equalsIgnoreCase("null")) {
                                       if(sexe.equalsIgnoreCase("HOMME")) sex = Sexe.HOMME;
                                       else sex = Sexe.FEMME;

                                   }

                                   cl = new Client(id, login, pass, nom, prenom, email, cin, tel, sex);
                                   return cl;
                               })
                               .collect(Collectors.toList());


        } catch (IOException e) {
           e.printStackTrace();
        }

        return clients;
    }

    @Override
    public Client findById(Long idClient) {
        return findAll().stream()
                .filter(client -> client.getId() == idClient)
                .findFirst()
                .orElse(null);

    }

    private Long getNextID(){
        try {
            String idStr = Files.readString(FileBasePaths.CLIENT_COUNTER, StandardCharsets.UTF_8);
            Long id = Long.parseLong(idStr);

            Files.writeString(FileBasePaths.CLIENT_COUNTER, String.valueOf(id + 1L), StandardOpenOption.WRITE);

            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public Client save(Client client) {
        if(client.getId() == null) client.setId(getNextID());

        try {
            Files.writeString(FileBasePaths.CLIENT_TABLE, client.toString(), StandardOpenOption.APPEND);
            System.out.println("Client n ° "+ client.getId() + " a été ajouté avec succès ^_^");
        }
        catch (IOException e) { e.printStackTrace();}

        return client;
    }

    @Override
    public List<Client> saveAll(List<Client> listeClients) {
        try {
            Files.deleteIfExists(FileBasePaths.CLIENT_TABLE);
            FileBasePaths.createFileBase();
        }
        catch (IOException e) {e.printStackTrace();}
        return
                listeClients
                        .stream()
                        .map(this::save)
                        .collect(Collectors.toList());
    }

    @Override
    public Client update(Client newClient) {

        List<Client> clientsUpdated =
                findAll()
                        .stream()
                        .map(client -> {
                            if(client.equals(newClient))
                                return newClient;
                            else
                                return client;
                        })
                        .collect(Collectors.toList());

        saveAll(clientsUpdated);

        return newClient;
    }

    @Override
    public Boolean delete(Client clientToDelete) {
        var clients = findAll();

        (new CompteDAO()).deleteByClient(clientToDelete);

        boolean deleted  =  clients.remove(clientToDelete);

        if(deleted) saveAll(clients);

        return deleted;
    }

    @Override
    public Boolean deleteById(Long idClient) { return delete(findById(idClient)); }

    @Override
    public Boolean deleteAll(List<Client> items) {
        items.forEach(this::delete);
        return true;
    }

    @Override
    public List<Client> findByKeywordLike(String keyWord){

        List<Client> clients = findAll();

        return
                clients
                        .stream()
                        .filter(client ->
                                client.getId().toString().equals(keyWord) ||
                                        client.getNom().toLowerCase().contains(keyWord.toLowerCase())    ||
                                        client.getPrenom().toLowerCase().contains(keyWord.toLowerCase())    ||
                                        client.getLogin().equals(keyWord)    ||
                                        client.getMotDePasse().equals(keyWord)    ||
                                        client.getCin().equalsIgnoreCase(keyWord)    ||
                                        (client.getEmail() != null && client.getEmail().equalsIgnoreCase(keyWord))    ||
                                        (client.getTel() != null && client.getTel().equals(keyWord))    ||
                                        client.getSexe().toString().toLowerCase().equalsIgnoreCase(keyWord)
                        )
                        .collect(Collectors.toList());

    }

    @Override
    public Client findClientByAccountID(String accountID) {
        Compte c = (new CompteDAO()).findById(accountID);
        if(c == null) return null;
        return c.getPropriétaire();
    }

    @Override
    public Client findByCIN(String cin) {
        return findAll()
                .stream()
                .filter(client -> client.getCin().toLowerCase().equals(cin.toLowerCase()))
                .findFirst().orElse(null);
    }
}
