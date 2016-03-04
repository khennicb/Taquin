package Prog;

import Prog.IHM.Console;
import Prog.IHM.IHM;

public class MainNico {
    static IHM IHM;
    
    public static void main(String[] args) {
        /*
        int[][] tab = {
            {1, 2},
            {3, 4}
        };
        
        EtatPlateau test = new EtatPlateau("HBBHGD", tab);
        
        IHM = new Console();
        IHM.afficherEtat(test);
        */
        
        int[][] debut = {
            {4, 3, 8},
            {2, 1, 0},
            {7, 6, 5}
        };
        
        int[][] fin = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        };
        
        EtatPlateau init = new EtatPlateau("", debut, fin);
        EtatPlateau end = new EtatPlateau("", fin, fin);
        
        IHM IHM = new Console();
        
        SolveurSniper r = new SolveurSniper(init);
        
        EtatPlateau resultat = r.solve();
        if(resultat != null){
            char[] cl = resultat.getListeMouvements().toCharArray();
            EtatPlateau current = init;
            EtatPlateau suivant;
            for (int i = 0; i < cl.length; i++) {
                suivant = current.getEtatPlateauApresAction(Deplacement.fromChar(cl[i]));
                if(suivant != null) ((Console)IHM).animate(current, suivant);
                current = suivant;
            }
        }
        
    }
    
}
