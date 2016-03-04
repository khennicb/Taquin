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
    private EtatPlateau etatInitial;
    private int [][] tabFinal;
    private Console out;
    
    public Jeu(EtatPlateau e){
        etat = e;
        etatInitial = e;
        tabFinal = etat.getEtatFinal();
        out = new Console();
        
    }
    
    public void lancerLeJeu(){
        Solveur s = new Solveur(etat);
        if (!s.estSolvable()) {
            out.afficheMessage("Cette grille n'est pas solvable.");
            return;
        }
        
        String wait = out.getExplications(etat);
        if (wait.equalsIgnoreCase("quit") || wait.equalsIgnoreCase("quitter") || wait.equalsIgnoreCase("exit")) {
            return;
        }
               
        while (!etat.estFinal()) {
            out.clean();
            out.afficheEtatFinal(etat);
            out.afficheMessage("");
            out.afficherEtat(etat);
            
            Deplacement deplacement = Deplacement.Bas;
            boolean depPossible;
            String d;
            
            do {                
                d = out.listen();
                
                if (d.equalsIgnoreCase("8") || d.equalsIgnoreCase("z")) {
                    deplacement = Deplacement.Haut;
                } else if (d.equalsIgnoreCase("2") || d.equalsIgnoreCase("s")) {
                    deplacement = Deplacement.Bas;
                } else if (d.equalsIgnoreCase("4") || d.equalsIgnoreCase("q")) {
                    deplacement = Deplacement.Gauche;
                } else if(d.equalsIgnoreCase("6") || d.equalsIgnoreCase("d")) {
                    deplacement = Deplacement.Droite;
                } else if (d.equalsIgnoreCase("quit") || d.equalsIgnoreCase("quitter") || d.equalsIgnoreCase("exit")){
                    return;
                }else{
                    wait = out.getExplications(etat);
                    if (wait.equalsIgnoreCase("quit") || wait.equalsIgnoreCase("quitter") || wait.equalsIgnoreCase("exit")) {
                        return;
                    }
                }
                
                depPossible = etat.deplacementPossible(deplacement);
                if (!depPossible && !d.equalsIgnoreCase("help") && !d.equalsIgnoreCase("aide")) {
                    out.afficheMessage("Ce déplacement est impossible.");
                }
            } while (!depPossible && !d.equalsIgnoreCase("help") && !d.equalsIgnoreCase("aide"));
            
            etat = etat.getEtatPlateauApresAction(deplacement);
        }
        
        out.felication(etat, etatInitial);
    }
}
