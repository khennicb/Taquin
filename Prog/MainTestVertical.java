
package Prog;

import java.io.FileNotFoundException;

public class MainTestVertical {
    
    public static void main(String[] args) throws FileNotFoundException, ExceptionFormatFichier {
        
        String filename = "sp000_1.txt";
        EntreeSortieFichier esF = new EntreeSortieFichier("Data");
        
        int[][][] plateau = esF.readPlateau(filename);
        
        int[][] eFinal, eInit;
        eInit = plateau[0];
        eFinal = plateau[1];
        
        
        EtatPlateau etatInitial= new EtatPlateau("", eInit, eFinal);
        
        SolveurVertical solveur = new SolveurVertical(etatInitial);
        EtatPlateau sol = solveur.solve();
        
        System.out.println("la solution est : " + sol.getListeMouvements());
        
        
    }
    
}
