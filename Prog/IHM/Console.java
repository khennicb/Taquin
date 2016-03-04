package Prog.IHM;

import Prog.EtatPlateau;
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
    
    public void animate(EtatPlateau etat1, EtatPlateau etat2) {
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
                !d.equalsIgnoreCase("quit") && !d.equalsIgnoreCase("quitter") && !d.equalsIgnoreCase("exit") && 
                !d.equalsIgnoreCase("help") && !d.equalsIgnoreCase("aide")) {
            System.out.println("Déplacement impossible. Veuillez saisir une lettre parmi: 2, 4, 6, 8 et z, q, s, d");
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
        System.out.println("Bonjour. Voici l'état courant de votre grille : ");
        afficherEtat(etat);
        System.out.println("Le but du jeu est d'arriver à cette état final : ");
        afficheEtatFinal(etat);
        System.out.println("Pour cela, vous allez devoir déplacer les cases qui entoure la case vide pour qu'elles prennent la place de celle-ci.");
        System.out.println("Entrez \"8\" ou \"Z\" pour que la case sous la case vide monte.");
        System.out.println("Entrez \"2\" ou \"S\" pour que la case au dessus de la case vide descende.");
        System.out.println("Entrez \"4\" ou \"Q\" pour que la case à droite de la case vide se déplace vers la gauche.");
        System.out.println("Entrez \"6\" ou \"D\" pour que la case à gauche de la case vide se déplace vers la droite.");
        System.out.println("");
        System.out.println("Vous pouvez quitter à tout moment en tapant \"quitter\". Pour demander de l'aide, tapez \"help\".");
        System.out.println("");
        System.out.print("Vous êtes pret ? ");
        
        return waitForUser();
    }
    
    public void felication(EtatPlateau etat){
        clean();
        afficherEtat(etat);
        System.out.println("Bravo ! Vous avez gagné.e !");
    }
    
    public void afficheMessage(String txt){
        System.out.println(txt);
    }
    
    public void clean(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
