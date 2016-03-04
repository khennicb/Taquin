package Prog;

import Prog.IHM.IHM;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SolveurSniper {

    public EtatPlateau solve(EtatPlateau init, EtatPlateau fin){
        
        ManhattanComparator comp = new ManhattanComparator();
        PriorityQueue<EtatPlateau> listeOuverte = new PriorityQueue<>(comp);
        
        listeOuverte.add(init);
        
        while(!listeOuverte.isEmpty()){
            EtatPlateau current = listeOuverte.poll();
            
            if(current.toHashKey().equals(fin.toHashKey())){
                return current;
            }else{
                for(Deplacement d : Deplacement.values()){
                    EtatPlateau suivant = current.getEtatPlateauApresAction(d);
                    if(suivant != null){
                       listeOuverte.offer(suivant);
                   }
               }
           }
        }
        return null;
    }
}

class ManhattanComparator implements Comparator<EtatPlateau> {
    
    @Override
    public int compare(EtatPlateau etat1, EtatPlateau etat2) {
        // -1 = etat2 sera moins prioritaire que etat 1, 0 = même niveau, 1 = plus prio
        if(etat1.getF() < etat2.getF())
            return -1;
        else if(etat1.getF() == etat2.getF()){
            
            if(etat1.getH() < etat2.getH())
                return -1;
            else if (etat1.getH() == etat2.getH()){
                return 0;
                /*
                if(etat1.getF() < etat2.getF())
                    return -1;
                else if (etat1.getF() == etat2.getF())
                    return 0;
                else 
                    return 1;
                */
            }else 
                return 1;
                
        }else
            return 1;
    }
    
}