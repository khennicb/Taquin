/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prog;

import Prog.IHM.Console;
import java.io.FileNotFoundException;

/**
 *
 * @author foreyn
 */
public class MainNono {
    public static void main(String[] args) throws FileNotFoundException, ExceptionFormatFichier {
        
        EntreeSortieFichier fichier = new EntreeSortieFichier("Data");
        
        int[][][] plateau = fichier.readPlateau("sp000.txt");
        
        EtatPlateau test1 = new EtatPlateau("", plateau[0], plateau[1]);
        
        /*EtatPlateau fin = new EtatPlateau("", plateau[1], plateau[1]);
        
        SolveurSniper s = new SolveurSniper();
        EtatPlateau solution = s.solve(test1, fin);
        Console c = new Console();
        c.afficherEtat(solution);*/
        
        //BDHHGBDDBGHHG
        
        Jeu jeu = new Jeu(test1);
        jeu.lancerLeJeu();
        
        System.out.println("Merci d'avoir joué.e!");
        System.out.println("");
    }
}
