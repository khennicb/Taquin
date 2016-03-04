/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prog;

import java.util.Stack;

/**
 *
 * @author ben
 */
public class SolveurVertical {
    
    Stack<EtatPlateau> pile;
    EtatPlateau solution;
    boolean algoEnCours;
    
    public SolveurVertical() {
        pile = new Stack<>();
        solution = null;
    }
    
    public EtatPlateau solve(EtatPlateau etatInit){
        
        algoEnCours = true;
        
        while (algoEnCours){
         






            
            if (pile.isEmpty())
                algoEnCours = false;

        }
            
        return null;
    }
    
    
    
}
