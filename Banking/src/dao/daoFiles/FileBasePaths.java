package dao.daoFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public interface FileBasePaths {

  Path FILEBASEFOLDER = Paths.get("myFileBase");
  Path BANK_AGENCIES_TABLE = Paths.get(FILEBASEFOLDER.toString(), "agencies.txt");
  Path BANK_AGENCIES_COUNTER = Paths.get(FILEBASEFOLDER.toString(), "agencies_counter.txt");
  Path CLIENT_TABLE = Paths.get(FILEBASEFOLDER.toString(), "clients.txt");
  Path CLIENT_COUNTER = Paths.get(FILEBASEFOLDER.toString(), "clients_counter.txt");
  Path ACCOUNTS_TABLE = Paths.get(FILEBASEFOLDER.toString(), "accounts.txt");
  Path ACCOUNTS_COUNTER = Paths.get(FILEBASEFOLDER.toString(), "accounts_counter.txt");
  Path ACCOUNTS_LOG_TABLE = Paths.get(FILEBASEFOLDER.toString(), "logs.txt");
  Path ACCOUNTS_LOG_COUNTER = Paths.get(FILEBASEFOLDER.toString(), "logs_counter.txt");

  String BANK_AGENCIES_TABLE_HEADER = "ID\t\t\tNAME\t\t\tTEL\t\t\tADRESS\t\t\tEMAIL\n";
  String CLIENT_TABLE_HEADER = "ID\t\t\tPRENOM\t\t\tNOM\t\t\tLOGIN\t\t\tMOT DE PASSE\t\t\tEMAIL\t\t\tCIN\t\t\tTEL\t\t\tSEXE\n";
  String ACCOUNTS_TABLE_HEADER = "ID\t\t\tSOLDE\t\t\tDATE CREATION\t\t\tCLIENT ID\t\t\tAGENCY ID\n";
  String ACCOUNTS_LOG_TABLE_HEADER = "ID\t\t\tACCOUNT ID\t\t\tDATETIME\t\t\tTYPE\t\t\tMESSAGE\n";


  static void createFile(Path path, String header){
    if(! path.toFile().exists()){
      try{
        path.toFile().createNewFile();
        Files.writeString(path, header, StandardOpenOption.WRITE);
        System.out.println("le fichier " + path.getFileName() + " a été créé.");
      } catch (IOException ex){
        ex.printStackTrace();
      }
    } else {
      System.out.println("le fichier existe déja!!!");
    }
  }

  private static void createDirectory(Path path){
    if (Files.notExists(path)) {
      try {
        Files.createDirectory(path);
        System.out.println("le dossier " + path.getFileName() + " a été créé.");
      }
      catch (Exception e ) {
        e.printStackTrace();
      }
    } else {
      System.out.println("le dossier existe déja!!!");
    }
  }

  static void createFileBase(){
    createDirectory(FILEBASEFOLDER);
    createFile(BANK_AGENCIES_TABLE, BANK_AGENCIES_TABLE_HEADER);
    createFile(BANK_AGENCIES_COUNTER, "1");
    createFile(CLIENT_TABLE, CLIENT_TABLE_HEADER);
    createFile(CLIENT_COUNTER, "2");
    createFile(ACCOUNTS_TABLE, ACCOUNTS_TABLE_HEADER);
    createFile(ACCOUNTS_COUNTER, "1");
    createFile(ACCOUNTS_LOG_TABLE, ACCOUNTS_LOG_TABLE_HEADER);
    createFile(ACCOUNTS_LOG_COUNTER, "1");
  }
}
