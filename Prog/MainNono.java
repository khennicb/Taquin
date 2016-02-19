/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prog;

import Prog.IHM.Fenetre;
import javax.swing.JFrame;

/**
 *
 * @author foreyn
 */
public class MainNono {
    public static void main(String[] args) {
        Fenetre frame = new Fenetre();
        
        int[][] tab = {
            {1, 2},
            {3, 4}
        };
        
        EtatPlateau test = new EtatPlateau("HBBHGD", tab);
        
        frame.afficherEtat(test);
    }
}
