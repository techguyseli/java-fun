package dao.daoFiles;

import dao.ICompteDAO;
import presentation.models.Agence;
import presentation.models.Client;
import presentation.models.Compte;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompteDAO implements ICompteDAO {

    @Override
    public List<Compte> findAll() {
        List<Compte> comptes = new ArrayList<>();


        try {
            List<String> lines = Files.readAllLines(FileBasePaths.ACCOUNTS_TABLE, StandardCharsets.UTF_8);
            lines.remove(0);

            if(!lines.isEmpty())
                comptes=
                        lines
                                .stream()
                                .map(line->{
                                    Compte compte;
                                    String[] st = line.split("\t\t\t");
                                    String   id              =   st[0];
                                    Double        solde           =   Double.parseDouble(st[1]);
                                    LocalDateTime  date            =   LocalDateTime.parse(st[2]);
                                    Long clientID          =   Long.parseLong(st[3]);
                                    Long agencyID          =   Long.parseLong(st[4]);

                                    Client client = (new ClientDAO()).findById(clientID);
                                    Agence agence = (new AgenceDAO()).findById(agencyID);

                                    compte = new Compte(agence, id, solde, date, client);

                                    return compte;
                                })
                                .collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }

        return comptes;
    }

    @Override
    public Compte findById(String idCompte) {
        return findAll().stream()
                .filter(compte -> compte.getNumeroCompte().equals(idCompte))
                .findFirst()
                .orElse(null);

    }

    private String getNextID(){
        try {
            String idStr = Files.readString(FileBasePaths.ACCOUNTS_COUNTER, StandardCharsets.UTF_8);
            Long id = Long.parseLong(idStr);

            Files.writeString(FileBasePaths.ACCOUNTS_COUNTER, String.valueOf(id + 1L), StandardOpenOption.WRITE);

            return id.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public Compte save(Compte compte) {
        if(compte.getNumeroCompte() == null) compte.setNumeroCompte(Long.parseLong(getNextID()));

        try {
            Files.writeString(FileBasePaths.ACCOUNTS_TABLE, compte.toString(), StandardOpenOption.APPEND);
            System.out.println("Compte n ° "+ compte.getNumeroCompte() + " a été ajouté avec succès ^_^");
        }
        catch (IOException e) { e.printStackTrace();}

        return compte;
    }

    @Override
    public List<Compte> saveAll(List<Compte> listeComptes) {
        try {
            Files.deleteIfExists(FileBasePaths.ACCOUNTS_TABLE);
            FileBasePaths.createFileBase();
        } catch (IOException e) { e.printStackTrace(); }

        return
                listeComptes
                        .stream()
                        .map(this::save)
                        .collect(Collectors.toList());
    }

    @Override
    public Compte update(Compte newCompte) {

        List<Compte> comptesUpdated =
                findAll()
                        .stream()
                        .map(compte -> {
                            if(compte.equals(newCompte))
                                return newCompte;
                            else
                                return compte;
                        })
                        .collect(Collectors.toList());

        saveAll(comptesUpdated);

        return newCompte;
    }

    @Override
    public Boolean delete(Compte compteToDelete) {
        var comptes = findAll();

        (new LogDAO()).deleteByAccount(compteToDelete);

        boolean deleted  =  comptes.remove(compteToDelete);

        if(deleted) saveAll(comptes);

        return deleted;
    }

    @Override
    public Boolean deleteById(String idCompte) { return delete(findById(idCompte)); }

    @Override
    public Boolean deleteAll(List<Compte> items) {
        items.forEach(this::delete);
        return true;
    }

    @Override
    public List<Compte> findByKeywordLike(String keyWord){

        List<Compte> comptes = findAll();

        return
                comptes
                        .stream()
                        .filter(compte ->
                                compte.getNumeroCompte().equals(keyWord) ||
                                        compte.getSolde().toString().equals(keyWord)    ||
                                        compte.getDateCreation().toLocalDate().toString().equals(keyWord)    ||
                                        compte.getPropriétaire().getNomComplet().toLowerCase().contains(keyWord.toLowerCase())    ||
                                        compte.getAgence().getNomAgence().toLowerCase().contains(keyWord.toLowerCase())
                        )
                        .collect(Collectors.toList());

    }

    @Override
    public List<Compte> findAccountsByClient(Client client) {
        return findAll().stream()
                .filter(c -> c.getPropriétaire().equals(client))
                .toList();
    }

    @Override
    public List<Compte> findAccountsByAgence(Agence agence) {
        return findAll().stream()
                .filter(c -> c.getAgence().equals(agence))
                .toList();
    }

    @Override
    public List<Compte> deleteByAgence(Agence agence) {
        List<Compte> comptes = findAccountsByAgence(agence);
        deleteAll(comptes);
        return comptes;
    }

    @Override
    public List<Compte> deleteByClient(Client client) {
        List<Compte> comptes = findAccountsByClient(client);
        deleteAll(comptes);
        return comptes;
    }
}
