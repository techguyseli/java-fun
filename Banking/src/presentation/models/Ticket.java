package presentation.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Ticket {
    private Compte compte;
    private Log log;
    private String ticketFileName;
    private String ticketContent;

    public Compte getCompte() {
        return compte;
    }

    private String setTicketContent(){
        String borderChar = "*";
        String spliter = "*******************************************************************************";
        int lineSize = spliter.length();

        String content = spliter + "\n";
        String ticketTitle = "Ticket N° " + log.getId();

        String strHolder = null;
        int numHolder;

        content += borderChar;
        for(int i = 0; i < ((lineSize - 1) - ticketTitle.length())/2; i++) content += " ";
        content += ticketTitle;
        for(int i = 0; i < ((lineSize - 1) - ticketTitle.length())/2; i++) content += " ";
        content += borderChar + "\n";
        content += spliter + "\n";

        strHolder = borderChar + "=> Compte : " + compte.getNumeroCompte();
        content += strHolder;
        numHolder = lineSize - strHolder.length() - 1;
        for(int i = 0; i < numHolder; i++) content += " ";
        content += borderChar + "\n";

        strHolder = borderChar + "=> Type D'operation : " + log.getType();
        content += strHolder;
        numHolder = lineSize - strHolder.length() - 1;
        for(int i = 0; i < numHolder; i++) content += " ";
        content += borderChar + "\n";

        strHolder = borderChar + "=> Date : " + log.getDate().toString();
        content += strHolder;
        numHolder = lineSize - strHolder.length() - 1;
        for(int i = 0; i < numHolder; i++) content += " ";
        content += borderChar + "\n";

        strHolder = borderChar + "=> Message : " + log.getMessage();
        content += strHolder;
        numHolder = lineSize - strHolder.length() - 1;
        for(int i = 0; i < numHolder; i++) content += " ";
        content += borderChar + "\n";

        content += spliter;

        return content;
    }

    public Ticket(Compte compte, Log log){
        this.compte = compte;
        this.log = log;
        ticketFileName = "TICKET_" + compte.getNumeroCompte() + "_" + log.getId() + ".txt";
        ticketContent = setTicketContent();
    }

    public String save(Path folder){
        String message = "le ticket " + ticketFileName + " existe déja !";

        Path file = Paths.get(folder.toString(), ticketFileName);

        if(! file.toFile().exists()){
            try{
                file.toFile().createNewFile();
                Files.writeString(file, ticketContent, StandardOpenOption.WRITE);
                message = "Le ticket " + ticketFileName + " a été sauvegardé.";
            } catch (IOException ex){
                message = ex.getMessage();
            }
        }

        return message;
    }
}
