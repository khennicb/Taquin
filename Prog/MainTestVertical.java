package Prog;

import java.io.FileNotFoundException;

public class MainTestVertical {
    
    public static void main(String[] args) throws FileNotFoundException, ExceptionFormatFichier {
        
        String filename = "sp003.txt";
        EntreeSortieFichier esF = new EntreeSortieFichier("");
        
        int[][][] plateau = esF.readPlateau(filename);
        
        int[][] eFinal, eInit;
        eInit = plateau[0];
        eFinal = plateau[1];
        
        
        EtatPlateau test1 = new EtatPlateau("", plateau[0], plateau[1]);
        
        
        
        
    }
    
}
