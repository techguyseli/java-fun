package dao.daoFiles;

import dao.Database;
import dao.ILogDAO;
import presentation.models.Compte;
import presentation.models.Log;
import presentation.models.TypeLog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogDAO implements ILogDAO {

    @Override
    public List<Log> findAll() {
        List<Log> logs = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(FileBasePaths.ACCOUNTS_LOG_TABLE, StandardCharsets.UTF_8);
            lines.remove(0);

            if(!lines.isEmpty())
                logs=
                        lines
                                .stream()
                                .map(line->{
                                    Log log;
                                    String[] st = line.split("\t\t\t");
                                    Long   id =  Long.parseLong(st[0]);
                                    String accountID  =   st[1];
                                    LocalDateTime  date            =   LocalDateTime.parse(st[2]);
                                    TypeLog typeLog          =   TypeLog.getTypeLog(st[3]);
                                    String message          =   st[4];

                                    Compte compte = Database.getCompteDAO().findById(accountID);

                                    log = new Log(id, date, typeLog, message, compte);

                                    return log;
                                })
                                .collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }

        return logs;
    }

    @Override
    public Log findById(Long idLog) {
        return findAll().stream()
                .filter(log -> log.getId().equals(idLog))
                .findFirst()
                .orElse(null);

    }

    private Long getNextID(){
        try {
            String idStr = Files.readString(FileBasePaths.ACCOUNTS_LOG_COUNTER, StandardCharsets.UTF_8);
            Long id = Long.parseLong(idStr);

            Files.writeString(FileBasePaths.ACCOUNTS_LOG_COUNTER, String.valueOf(id + 1L), StandardOpenOption.WRITE);

            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public Log save(Log log) {
        if(log.getId() == null) log.setId(getNextID());

        try {
            Files.writeString(FileBasePaths.ACCOUNTS_LOG_TABLE, log.toString(), StandardOpenOption.APPEND);
            System.out.println("Log n ° "+ log.getId() + " a été ajouté avec succès ^_^");
        }
        catch (IOException e) { e.printStackTrace();}

        return log;
    }

    @Override
    public List<Log> saveAll(List<Log> listeLogs) {
        try {
            Files.deleteIfExists(FileBasePaths.ACCOUNTS_LOG_TABLE);
            FileBasePaths.createFileBase();
        } catch (IOException e) { e.printStackTrace(); }

        return
                listeLogs
                        .stream()
                        .map(this::save)
                        .collect(Collectors.toList());
    }

    @Override
    public Log update(Log newLog) {

        List<Log> logsUpdated =
                findAll()
                        .stream()
                        .map(log -> {
                            if(log.equals(newLog))
                                return newLog;
                            else
                                return log;
                        })
                        .collect(Collectors.toList());

        saveAll(logsUpdated);

        return newLog;
    }

    @Override
    public Boolean delete(Log logToDelete) {
        var logs = findAll();

        boolean deleted  =  logs.remove(logToDelete);

        if(deleted) saveAll(logs);

        return deleted;
    }

    @Override
    public Boolean deleteById(Long idLog) { return delete(findById(idLog)); }

    @Override
    public Boolean deleteAll(List<Log> items) {
        items.forEach(this::delete);
        return true;
    }

    @Override
    public List<Log> findByKeywordLike(String keyWord){

        List<Log> logs = findAll();

        return
                logs
                        .stream()
                        .filter(log ->
                                log.getId().toString().equals(keyWord) ||
                                        log.getType().toString().toLowerCase().equals(keyWord.toLowerCase())    ||
                                        log.getDate().toLocalDate().toString().equals(keyWord)    ||
                                        log.getDate().toLocalTime().toString().split("\\.")[0].substring(0, 5).equals(keyWord.substring(0, 5))    ||
                                        log.getMessage().toLowerCase().contains(keyWord.toLowerCase())    ||
                                        log.getCompte().getNumeroCompte().equals(keyWord)
                        )
                        .collect(Collectors.toList());

    }

    @Override
    public Log saveLogForAccount(Compte compte, TypeLog type, String message) {
        Log log = new Log(type, message, compte);
        save(log);
        return log;
    }

    @Override
    public List<Log> findAccountLogs(Compte compte) {
        return findAll().stream()
                .filter(log -> log.getCompte().equals(compte))
                .toList();
    }

    @Override
    public List<Log> deleteByAccount(Compte compte) {
        List<Log> logs = findAccountLogs(compte);
        deleteAll(logs);
        return logs;
    }
}
