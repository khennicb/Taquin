package Prog.IHM;

import Prog.EtatPlateau;
import java.util.Scanner;

public class Console implements IHM {
       

    @Override
    public void afficherEtat(EtatPlateau etat) {
        System.out.println("- Etat : " + etat.getListeMouvements());
        
        for(int[] ligne : etat.getListeTuiles()) {
            System.out.print("- (\t");
            for(int tuile : ligne) {
                System.out.print(tuile+"\t");
            }
            System.out.print(")\n");
        }
        
        System.out.println("------");
    }
    
    
    public String listen(){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        String d = input.nextLine();
        while(!d.equalsIgnoreCase("z") && !d.equalsIgnoreCase("q") && !d.equalsIgnoreCase("s") && !d.equalsIgnoreCase("d")) {
            System.out.println("Déplacement impossible. Veuillez saisir une lettre parmi: z, q, s, d");
            d = input.nextLine();
        }
        
        //System.out.println("Vous avez saisi: " + d);
        return d;
    }
}
