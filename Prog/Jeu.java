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
        System.out.println("Etat Initial : ");
        out.afficherEtat(etat);
        
        System.out.println("Etat Final :");
        out.afficheEtatFinal(etat);
        System.out.println("");
        
        Solveur s = new Solveur(tabFinal, etat.getListeTuiles());
        if (!s.estSolvable()) {
            System.out.println("Cette grille n'est pas solvable.");
            return;
        }
                        
        while (etat.estFinal()) {
            out.afficherEtat(etat);
            
            String d = out.listen();
            Deplacement deplacement;
            
            if (d.equalsIgnoreCase("z")) { deplacement = Deplacement.Haut; }
            else if (d.equalsIgnoreCase("s")) { deplacement = Deplacement.Bas; }
            else if (d.equalsIgnoreCase("q")) { deplacement = Deplacement.Gauche; }
            else { deplacement = Deplacement.Droite; }
            
            if (etat.deplacementPossible(deplacement)) {
                etat = etat.getEtatPlateauApresAction(deplacement); 
            }else{
                System.out.println("Ce déplacement est impossible.");
            }
        }
        
        System.out.println("Bravo ! Vous avez gagné.e !");
    }
}