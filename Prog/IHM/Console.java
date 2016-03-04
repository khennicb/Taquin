package Prog.IHM;

import Prog.EtatPlateau;
import java.util.Scanner;

public class Console implements IHM {
    Scanner input;
    
    public Console(){
        input = new Scanner(System.in);
    }
       

    @Override
    public void afficherEtat(EtatPlateau etat) {
        System.out.println("- Etat " + etat.getHauteur() + " : " + etat.getListeMouvements());
        
        for(int[] ligne : etat.getListeTuiles()) {
            System.out.print("- (\t");
            for(int tuile : ligne) {
                System.out.print(tuile+"\t");
            }
            System.out.print(")\n");
        }
        
        System.out.println("------");
        System.out.println("");
    }
    
    public void afficheEtatFinal(EtatPlateau etat){
//        System.out.println("");
        System.out.println("- Etat Finale : " + etat.getListeMouvements());
        
        for(int[] ligne : etat.getEtatFinal()) {
            System.out.print("- (\t");
            for(int tuile : ligne) {
                System.out.print(tuile+"\t");
            }
            System.out.print(")\n");
        }
        
        System.out.println("------");
        System.out.println("");
    }
    
    
    public String listen(){
        System.out.println("");
        
        String d = input.nextLine();
        while(!d.equalsIgnoreCase("8") && !d.equalsIgnoreCase("6") && !d.equalsIgnoreCase("2") && !d.equalsIgnoreCase("4") && !d.equalsIgnoreCase("z") && !d.equalsIgnoreCase("q") && !d.equalsIgnoreCase("s") && !d.equalsIgnoreCase("d")) {
            System.out.println("Déplacement impossible. Veuillez saisir une lettre parmi: 2, 4, 6, 8 et z, q, s, d");
            d = input.nextLine();
        }
        
        return d;
    }
    
    public void waitForUser(){
        System.out.println("Cliquez sur entrée pour lancer le jeu : ");
        input.nextLine();
    }
    
    public void getExplications(EtatPlateau etat){
        System.out.println("Bonjour. Voici l'état courant de votre grille : ");
        afficherEtat(etat);
        System.out.println("Le but du jeu est d'arriver à cette état final : ");
        afficheEtatFinal(etat);
        System.out.println("Pour cela, vous allez devoir déplacer les cases qui entoure le 0 pour qu'elles prennent la place de celui-ci.");
        System.out.println("Entrez \"8\" ou \"Z\" pour que la case sous le 0 monte et remplace le 0.");
        System.out.println("Entrez \"2\" ou \"S\" pour que la case au dessus du 0 descende et remplace le 0.");
        System.out.println("Entrez \"4\" ou \"Q\" pour que la case à droite du 0 se déplace vers la gauche et remplace le 0.");
        System.out.println("Entrez \"6\" ou \"D\" pour que la case à gauche du 0 se déplace vers la droite et remplace le 0.");
        System.out.println("");
        System.out.print("Vous êtes pret ? ");
    }
    
    public void felication(EtatPlateau etat){
        clean();
        afficheEtatFinal(etat);
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
