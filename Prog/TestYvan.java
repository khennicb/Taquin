package Prog;

import Prog.IHM.Console;
import java.io.FileNotFoundException;

public class TestYvan {
    
    public static void main(String[] args) throws FileNotFoundException, ExceptionFormatFichier {
        EntreeSortieFichier fichier = new EntreeSortieFichier("Data");
        Console OUT = new Console();        
        int[][][] plateau = fichier.readPlateau("sp0401.txt");
        
        EtatPlateau test1 = new EtatPlateau("", plateau[0], plateau[1]);
        EtatPlateau test2 = new EtatPlateau("", plateau[1], plateau[1]);
              
        Solveur s = new Solveur(plateau[0], plateau[1]);
        System.out.println(s.estSolvable());
        
        OUT.afficherEtat(test1);
        OUT.afficherEtat(test2);
    }
    
}
