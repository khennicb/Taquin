/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prog.IHM;

import Prog.EtatPlateau;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 *
 * @author foreyn
 */
public class Fenetre extends JFrame implements IHM{
    
    public Fenetre(){
        super("Taquin");
        
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        };
        
        addWindowListener(l);
        setSize(800, 600);
        setVisible(true);
    }

    @Override
    public void afficherEtat(EtatPlateau etat) {
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                // La taille des cases doivent etre adaptées à la largeur de la fenetre
                // Les cases doivent etre des boutons non clicable
                // Seuls les max(4) cases voisines de la case zero doivent etre clicable
                // La case zero est elle un bouton ?
            }
        }
    }
    
}
