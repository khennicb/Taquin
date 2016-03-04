/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prog;

/**
 *
 * @author ben
 */
public enum Deplacement {
    Haut("H"), Bas("B"), Gauche("G"), Droite("D");
    
    private String lettre = "";
    
    Deplacement(String lettre) {
        this.lettre = lettre;
    }
    
    public String toString(){
        return lettre;
    }
    
    public static Deplacement fromChar(char c){
        if(c=='H')
            return Haut;
        if(c=='B')
            return Bas;
        if(c=='G')
            return Gauche;
        return Droite;
    }
    
}
