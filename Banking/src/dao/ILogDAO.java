package dao;

import presentation.models.Compte;
import presentation.models.Log;
import presentation.models.TypeLog;

import java.util.List;

public interface ILogDAO extends IDAO<Log, Long> {
    Log saveLogForAccount(Compte compte, TypeLog type, String Message);
    List<Log> findAccountLogs(Compte compte);
    List<Log> deleteByAccount(Compte compte);
}
