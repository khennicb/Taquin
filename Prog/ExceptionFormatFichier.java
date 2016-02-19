package Prog;

public class ExceptionFormatFichier extends Exception{
    
    public ExceptionFormatFichier(String message){
        super("Erreur dans le format du fichier: "+message);
    }
}
