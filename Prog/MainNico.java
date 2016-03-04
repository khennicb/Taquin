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
            {4, 8, 7},
            {6, 2, 3},
            {1, 0, 5}
        };
        
        int[][] fin = {
            {1, 2, 3},
            {3, 4, 5},
            {6, 7, 0}
        };
        
        EtatPlateau init = new EtatPlateau("", debut, fin);
        EtatPlateau end = new EtatPlateau("", fin, fin);
        
        IHM IHM = new Console();
        
        SolveurSniper r = new SolveurSniper(IHM);
        
        r.solve(init, end);
        
    }
    
}
