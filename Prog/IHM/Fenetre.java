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
        setSize(200, 200);
        setVisible(true);
    }

    @Override
    public void afficherEtat(EtatPlateau etat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
