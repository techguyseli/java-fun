package forms;

public class FormException extends Exception{
    public FormException()
    {
        super("Erreur dans le formulaire !");
    }
    public FormException(String errorMsg)
    {
        super(errorMsg);
    }
}

