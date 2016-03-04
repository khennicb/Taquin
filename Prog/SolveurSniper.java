package Prog;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SolveurSniper extends Solveur{

    public SolveurSniper(EtatPlateau etatInit) {
        super(etatInit);
        nomAlgo = "Meilleur d'abord";
    }

    public EtatPlateau solve(){
        long tdebut = System.nanoTime();
        nbSommetsVisite=0;
        tailleMax=0;
        
        EtatPlateau init = etatInit;
        EtatPlateau fin = new EtatPlateau("", etatInit.getEtatFinal(), etatInit.getEtatFinal());
        
        ManhattanComparator comp = new ManhattanComparator();
        // c'est domage, c'est un peu lourd à l'execution
        PriorityQueue<EtatPlateau> listeOuverte = new PriorityQueue<>(comp);
        
        listeOuverte.add(init);
        
        while(!listeOuverte.isEmpty()){
            EtatPlateau current = listeOuverte.poll();
            nbSommetsVisite++;
            
            if(current.toHashKey().equals(fin.toHashKey())){
                longueurSolution = current.getHauteur();
                Solution = current.getListeMouvements();
                long tfin = System.nanoTime();
                tempsCPUNS = tfin-tdebut;
                return current;
            }else{
                for(Deplacement d : Deplacement.values()){
                    EtatPlateau suivant = current.getEtatPlateauApresAction(d);
                    if(suivant != null){
                       listeOuverte.offer(suivant);
                       if(tailleMax < listeOuverte.size()) tailleMax=listeOuverte.size();
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
            }else 
                return 1;
                
        }else
            return 1;
    }
    
}
