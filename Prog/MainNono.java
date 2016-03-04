/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prog;

import Prog.IHM.Console;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author foreyn
 */
public class MainNono {
    public static void main(String[] args) throws FileNotFoundException, ExceptionFormatFichier {
        
        EntreeSortieFichier fichier = new EntreeSortieFichier("Data","Repport");
        
        int[][][] plateau = fichier.readPlateau("sp000.txt");
        
        EtatPlateau test1 = new EtatPlateau("", plateau[0], plateau[1]);
        
        
        /*
        SolveurSniper s = new SolveurSniper(test1);
        EtatPlateau solution = s.solve();
        Console c = new Console();
        c.afficherEtat(solution);*/
        
        //BDHHGBDDBGHHG
        
        Jeu jeu = new Jeu(test1);
        try {
            jeu.lancerLeJeu();
        } catch (ExceptionQuitter ex) {
            System.out.println("Merci d'avoir joué.e!");
            System.out.println("");
        }
    }
}
