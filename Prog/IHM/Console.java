package Prog.IHM;

import Prog.EtatPlateau;

public class Console implements IHM {

    @Override
    public void afficherEtat(EtatPlateau etat) {
        System.out.println("- Etat après : " + etat.getListeMouvements());
        
        for(int[] ligne : etat.getListeTuiles()) {
            System.out.print("- (\t");
            for(int tuile : ligne) {
                System.out.print(tuile+"\t");
            }
            System.out.print(")\n");
        }
        
        System.out.println("------");
    }
    
}
