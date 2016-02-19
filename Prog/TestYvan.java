package Prog;

import Prog.IHM.Console;
import java.io.FileNotFoundException;

public class TestYvan {
    
    public static void main(String[] args) throws FileNotFoundException, ExceptionFormatFichier {
        EntreeSortieFichier fichier = new EntreeSortieFichier("Data");
        
        int[][][] plateau = fichier.readPlateau("testFile.txt");
        
        EtatPlateau test1 = new EtatPlateau("", plateau[0]);
        EtatPlateau test2 = new EtatPlateau("", plateau[1]);
        
        Console OUT = new Console();
        OUT.afficherEtat(test1);
        OUT.afficherEtat(test2);
    }
    
}
