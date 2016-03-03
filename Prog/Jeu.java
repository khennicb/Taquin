/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prog;

import Prog.IHM.Console;

/**
 *
 * @author foreyn
 */
public class Jeu {
    private EtatPlateau etat;
    private int [][] tabFinal;
    private Console out;
    
    public Jeu(EtatPlateau e){
        etat = e;
        tabFinal = etat.getEtatFinal();
        out = new Console();
        
    }
    
    public void lancerLeJeu(){
        int[][] tabCourrant = etat.getListeTuiles();
        Solveur s = new Solveur(tabFinal, tabCourrant);
        if (!s.estSolvable()) {
            System.out.println("Cette grille n'est pas solvable.");
            return;
        }
                
        out.afficherEtat(etat);
        
        while (true/*!etat.finie()*/) {            
            String deplacement = out.listen();
            
        }
    }
}
