package Prog;

import java.io.FileNotFoundException;

public class MainTestVertical {
    
    public static void main(String[] args) throws FileNotFoundException, ExceptionFormatFichier {
        
        String filename = "sp003.txt";
        EntreeSortieFichier esF = new EntreeSortieFichier("Data");
        
        int[][][] plateau = esF.readPlateau(filename);
        
        int[][] eFinal, eInit;
        eInit = plateau[0];
        eFinal = plateau[1];
        
        
        EtatPlateau etatInitial= new EtatPlateau("", plateau[0], plateau[1]);
        
        SolveurVertical solveur = new SolveurVertical(etatInitial);
        solveur.solve();
        
        
    }
    
}
