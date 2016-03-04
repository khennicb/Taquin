package Prog;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestYvan {
    
    public static void main(String[] args) throws FileNotFoundException, ExceptionFormatFichier, IOException, InterruptedException {
        EntreeSortieFichier fichier = new EntreeSortieFichier("Data","Repport");
//        Console OUT = new Console();        
        int[][][] plateau = fichier.readPlateau("sp0401.txt");
        
        EtatPlateau test1 = new EtatPlateau("", plateau[0], plateau[1]);
        EtatPlateau test2 = new EtatPlateau("", plateau[1], plateau[1]);
        
        Solveur s = new Solveur(test1);
        System.out.println(s.estSolvable());
        
//        OUT.afficherEtat(test1);
 //       OUT.afficherEtat(test2);
        
        System.out.print("\033[H\033[2J");  
        System.out.flush();

    }
    
}
