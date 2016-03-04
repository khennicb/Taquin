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
        out.getExplications();
        
        out.afficheMessage("Etat Initial : ");
        out.afficherEtat(etat);
        
        
        out.afficheEtatFinal(etat);
        
        Solveur s = new Solveur(etat);
        if (!s.estSolvable()) {
            out.afficheMessage("Cette grille n'est pas solvable.");
            return;
        }
        
        out.waitForUser();
                        
        while (!etat.estFinal()) {
            out.clean();
            
            out.afficherEtat(etat);
            Deplacement deplacement;
            
            do {                
                String d = out.listen();
                
                if (d.equalsIgnoreCase("z")) {
                    deplacement = Deplacement.Haut;
                } else if (d.equalsIgnoreCase("s")) {
                    deplacement = Deplacement.Bas;
                } else if (d.equalsIgnoreCase("q")) {
                    deplacement = Deplacement.Gauche;
                } else {
                    deplacement = Deplacement.Droite;
                }
                
                out.afficheMessage("Ce déplacement est impossible.");
            } while (!etat.deplacementPossible(deplacement));
            
            etat = etat.getEtatPlateauApresAction(deplacement);
            
        }
        
        out.felication(etat);
    }
}
