package dao.daoFiles;

import dao.Database;
import dao.IAgenceDAO;
import presentation.models.Agence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgenceDAO implements IAgenceDAO {

    @Override
    public List<Agence> findAll() {
        List<Agence> agences = new ArrayList<>();


        try {
            List<String> lines = Files.readAllLines(FileBasePaths.BANK_AGENCIES_TABLE, StandardCharsets.UTF_8);
            lines.remove(0);

            if(!lines.isEmpty())
                agences=
                        lines
                                .stream()
                                .map(line->{
                                    Agence agence;
                                    String[] st = line.split("\t\t\t");
                                    long     id              =   Long.parseLong(st[0]);
                                    String   nom             =   st[1];
                                    String   tel             =   st[2];
                                    String   adresse         =   st[3];
                                    String   email           =   st[4];

                                    if(email.equalsIgnoreCase("null")) email =null;
                                    if(tel.equalsIgnoreCase("null")) tel =null;

                                    agence = new Agence(id, nom, adresse, tel, email);
                                    return agence;
                                })
                                .collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }

        return agences;
    }

    @Override
    public Agence findById(Long idAgence) {
        return findAll().stream()
                .filter(agence -> agence.getIdAgence() == idAgence)
                .findFirst()
                .orElse(null);

    }

    private Long getNextID(){
        try {
            String idStr = Files.readString(FileBasePaths.BANK_AGENCIES_COUNTER, StandardCharsets.UTF_8);
            Long id = Long.parseLong(idStr);

            Files.writeString(FileBasePaths.BANK_AGENCIES_COUNTER, String.valueOf(id + 1L), StandardOpenOption.WRITE);

            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public Agence save(Agence agence) {
        if(agence.getIdAgence() == null) agence.setIdAgence(getNextID());

        try {
            Files.writeString(FileBasePaths.BANK_AGENCIES_TABLE, agence.toString(), StandardOpenOption.APPEND);
            System.out.println("Agence n ° "+ agence.getIdAgence() + " a été ajouté avec succès ^_^");
        }
        catch (IOException e) { e.printStackTrace();}

        return agence;
    }

    @Override
    public List<Agence> saveAll(List<Agence> listeAgences) {
        try {
            Files.deleteIfExists(FileBasePaths.BANK_AGENCIES_TABLE);
            FileBasePaths.createFileBase();
        }
        catch (IOException e) {e.printStackTrace();}
        return
                listeAgences
                        .stream()
                        .map(this::save)
                        .collect(Collectors.toList());
    }

    @Override
    public Agence update(Agence newAgence) {

        List<Agence> agencesUpdated =
                findAll()
                        .stream()
                        .map(agence -> {
                            if(agence.equals(newAgence))
                                return newAgence;
                            else
                                return agence;
                        })
                        .collect(Collectors.toList());

        saveAll(agencesUpdated);

        return newAgence;
    }

    @Override
    public Boolean delete(Agence agenceToDelete) {
        var agences = findAll();

        (new CompteDAO()).deleteByAgence(agenceToDelete);
        boolean deleted  =  agences.remove(agenceToDelete);

        if(deleted) saveAll(agences);

        return deleted;
    }

    @Override
    public Boolean deleteById(Long idAgence) {
        return delete(findById(idAgence));
    }

    @Override
    public Boolean deleteAll(List<Agence> items) {
        items.forEach(this::delete);
        return true;
    }

    @Override
    public List<Agence> findByKeywordLike(String keyWord){

        List<Agence> agences = findAll();

        return
                agences
                        .stream()
                        .filter(agence ->
                                agence.getIdAgence().toString().equals(keyWord) ||
                                        agence.getNomAgence().toLowerCase().contains(keyWord.toLowerCase())    ||
                                        agence.getTelAgence().equals(keyWord)    ||
                                        agence.getAdresseAgence().toLowerCase().contains(keyWord.toLowerCase())    ||
                                        agence.getEmailAgence().equalsIgnoreCase(keyWord)
                        )
                        .collect(Collectors.toList());

    }

    @Override
    public Agence findByNom(String nom) {
        return findAll()
                .stream()
                .filter(agence -> agence.getNomAgence().toLowerCase().equals(nom.toLowerCase()))
                .findFirst().orElse(null);
    }
}

