/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prog;

import Prog.IHM.Console;
import Prog.ExceptionQuitter;

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
    
    public void lancerLeJeu() throws ExceptionQuitter{
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
            
            Deplacement deplacement = null;
            boolean depPossible = false;
            String d;
            boolean erreur;
            
            do {   
                erreur = true;
                d = out.listen();
                
                if (d.equalsIgnoreCase("8") || d.equalsIgnoreCase("z") || d.equalsIgnoreCase("h")) {
                    deplacement = Deplacement.Haut;
                } else if (d.equalsIgnoreCase("2") || d.equalsIgnoreCase("s") || d.equalsIgnoreCase("b")) {
                    deplacement = Deplacement.Bas;
                } else if (d.equalsIgnoreCase("4") || d.equalsIgnoreCase("q") || d.equalsIgnoreCase("g")) {
                    deplacement = Deplacement.Gauche;
                } else if(d.equalsIgnoreCase("6") || d.equalsIgnoreCase("d")) {
                    deplacement = Deplacement.Droite;
                } else if(d.equalsIgnoreCase("help") || d.equalsIgnoreCase("aide")){
                    erreur = false;
                    d = out.getExplications(etat);
                }else if(d.equalsIgnoreCase("solution") || d.equalsIgnoreCase("solve")){
                    erreur = false;
                    EtatPlateau solution = solve();
                    out.afficheSolution(solution);
                    d = out.waitForUser();
                }/*else if(d.equalsIgnoreCase("next") || d.equalsIgnoreCase("suivant")){
                    EtatPlateau solution = solve();
                    
                }*/
                
                checkForQuit(d);
                
                if (deplacement != null) {
                    depPossible = etat.deplacementPossible(deplacement);
                    if (!depPossible && erreur) {
                        out.afficheMessage("Ce déplacement est impossible.");
                    } 
                }
            } while (!depPossible && erreur);
            
            if (deplacement != null) {
                etat = etat.getEtatPlateauApresAction(deplacement);
            }
        }
        
        out.felication(etat, etatInitial);
    }
    
    private EtatPlateau solve(){
        int[][] listeTuiles = etat.getListeTuiles();
        int[][] nouvelleListeTuiles = new int[listeTuiles.length][listeTuiles.length];
        
        for (int i = 0; i < listeTuiles.length; i++) {
            nouvelleListeTuiles[i] = listeTuiles[i].clone();
        }

        EtatPlateau plat = new EtatPlateau("", nouvelleListeTuiles, etat.getEtatFinal());
        SolveurSniper s = new SolveurSniper(plat);
        return s.solve();
    }
    
    private void checkForQuit(String d) throws ExceptionQuitter{
        if (d.equalsIgnoreCase("quit") || d.equalsIgnoreCase("quitter") || d.equalsIgnoreCase("exit")) {
            throw new ExceptionQuitter();
        }
    }
}
