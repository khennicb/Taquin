package Prog.IHM;

import Prog.Deplacement;
import Prog.EtatPlateau;
import Prog.SolveurSniper;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Console implements IHM {
    Scanner input;
    
    public Console(){
        input = new Scanner(System.in);
    }
       

    @Override
    public void afficherEtat(EtatPlateau etat) {
        System.out.println("- Etat " + etat.getHauteur() + " : " + etat.getListeMouvements());
        afficheGrille(etat.getListeTuiles());
    }
    
    public void afficheEtatFinal(EtatPlateau etat){
        System.out.println("- Etat Finale : ");
        afficheGrille(etat.getEtatFinal());
    }
    
    private void afficheGrille(int[][] grille){
        System.out.print("╔════");
        for(int[] ligne : grille) {
            System.out.print("══════");
        }
        System.out.print("╗\n");
        
        for(int[] ligne : grille) {
            System.out.print("║    ");
            for(int tuile : ligne) {
                if(tuile <= 9)
                    System.out.print(" "+(tuile==0 ? " " : tuile)+"    ");
                else
                    System.out.print((tuile==0 ? "  " : tuile)+"    ");
            }
            System.out.print("║\n");
        }
        
        System.out.print("╚════");
        for(int[] ligne : grille) {
            System.out.print("══════");
        }
        System.out.println("╝");
    }
    
    public void replayEtat(EtatPlateau etat, EtatPlateau init){
        if(etat != null){
            char[] cl = etat.getListeMouvements().toCharArray();
            EtatPlateau current = init;
            EtatPlateau suivant;
            for (int i = 0; i < cl.length; i++) {
                suivant = current.getEtatPlateauApresAction(Deplacement.fromChar(cl[i]));
                if(suivant != null) animate(current, suivant);
                current = suivant;
            }
        }
    }
    
    
    public void animate(EtatPlateau etat1, EtatPlateau etat2) {
        // Devrait animer un déplacement entre l'état1 et l'état2
        int delai = 200;
        
        try {
            clean();
            afficherEtat(etat1);
            Thread.sleep(delai);
            clean();
            afficherEtat(etat2);
            Thread.sleep(delai);
        } catch (InterruptedException ex) {}
    }
    
    public String listen(){
        System.out.println("");
        
        String d = input.nextLine();
        while(!d.equalsIgnoreCase("8") && !d.equalsIgnoreCase("6") && !d.equalsIgnoreCase("2") && !d.equalsIgnoreCase("4") &&
                !d.equalsIgnoreCase("z") && !d.equalsIgnoreCase("q") && !d.equalsIgnoreCase("s") && !d.equalsIgnoreCase("d") &&
                !d.equalsIgnoreCase("h") && !d.equalsIgnoreCase("g") && !d.equalsIgnoreCase("b") &&
                !d.equalsIgnoreCase("quit") && !d.equalsIgnoreCase("quitter") && !d.equalsIgnoreCase("exit") && 
                !d.equalsIgnoreCase("help") && !d.equalsIgnoreCase("aide") &&
                !d.equalsIgnoreCase("solution") && !d.equalsIgnoreCase("solve")) {
            System.out.println("Déplacement impossible. Veuillez saisir une lettre parmi: 2, 4, 6, 8 ou z, q, s, d ou H, B, D, G");
            d = input.nextLine();
        }
        
        return d;
    }
    
    public String waitForUser(){
        System.out.println("Cliquez sur entrée pour continuer : ");
        String d = input.nextLine();
        return d;
    }
    
    public String getExplications(EtatPlateau etat){
        System.out.println("Le but du jeu est d'arriver à cette état final : ");
        afficheEtatFinal(etat);
        System.out.println("Bonjour. Voici l'état courant de votre grille : ");
        afficherEtat(etat);
        System.out.println("Pour cela, vous allez devoir déplacer les cases qui entoure la case vide pour qu'elles prennent la place de celle-ci.");
        System.out.println("Entrez \"8\" ou \"Z\" pour que la case sous la case vide monte.");
        System.out.println("Entrez \"2\" ou \"S\" pour que la case au dessus de la case vide descende.");
        System.out.println("Entrez \"4\" ou \"Q\" pour que la case à droite de la case vide se déplace vers la gauche.");
        System.out.println("Entrez \"6\" ou \"D\" pour que la case à gauche de la case vide se déplace vers la droite.");
        System.out.println("");
        System.out.println("Vous pouvez quitter à tout moment en tapant \"quitter\". Pour demander de l'aide, tapez \"help\". Pour voir la solution complète, tapez \"solve\"");
        System.out.println("");
        System.out.print("Vous êtes pret ? ");
        
        return waitForUser();
    }
    
    public void felication(EtatPlateau etat, EtatPlateau etatInit){
        clean();
        afficherEtat(etat);
        System.out.println("Bravo ! Vous avez gagné.e !");
        System.out.println("");
        System.out.println("Solution optimal : ");
        SolveurSniper solv = new SolveurSniper(etatInit);
        EtatPlateau etatOptimal = solv.solve();
        System.out.println("nombre de coups : " + etatOptimal.getHauteur() + ", chemin : " + etatOptimal.getListeMouvements());
    }
    
    public void afficheSolution(EtatPlateau etatOptimal){
        System.out.println("nombre de coups : " + etatOptimal.getHauteur() + ", chemin : " + etatOptimal.getListeMouvements());
    }
    
    public void afficheMessage(String txt){
        System.out.println(txt);
    }
    
    public void clean(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
