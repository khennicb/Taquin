package Prog;

import Prog.IHM.Console;

public class MainNico {
   
    public static void main(String[] args) {
        int[][] tab = {
            {1, 2},
            {3, 4}
        };
        
        EtatPlateau test = new EtatPlateau("HBBHGD", tab);
        
        Console OUT = new Console();
        OUT.afficherEtat(test);
    }
    
}
