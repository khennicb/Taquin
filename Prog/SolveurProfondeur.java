package Prog;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SolveurProfondeur {
    
    public EtatPlateau solve(EtatPlateau init, EtatPlateau fin){
        //Stack<EtatPlateau> listeFermee = new Stack<>();
        
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
                       listeOuverte.add(suivant);
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
        int manE1 = etat1.getMeilleurScoreManatthan();
        int manE2 = etat2.getMeilleurScoreManatthan();
        
        if(manE1 < manE2){
            return -1;
        }else if(manE1 == manE2){
            return 0; // ou retourner seulement le g de f=g+h
        }else{
            return 1;
        }
    }
    
}