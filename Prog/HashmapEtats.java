package Prog;

import java.util.HashMap;


public class HashmapEtats extends StructureEtats {
    
    private final HashMap<String, EtatPlateau> liste;

    public HashmapEtats() {
        liste = new HashMap();
    }
    
    public HashMap getListe(){
        return liste;
    }
    
    /**
     * @param etat L'état à ajouter
     * @return Booléen : l'ajout s'est bien fait dans le tableau. Si ce n'est pas le cas, c'est que l'état avait déjà été trouvé de manière plus optimisée.
     */
    @Override
    public boolean add(EtatPlateau etat) {
        EtatPlateau existant = liste.get(etat.toHashKey());
        
        if(existant == null || existant.getHauteur() > etat.getHauteur()) {
            // Le put() fait un replace() si l'élement existait déjà
            liste.put(etat.toHashKey(), etat);
            return true;
        }else{
            return false;
        }
    }
    
}
