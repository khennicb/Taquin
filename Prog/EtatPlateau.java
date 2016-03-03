package Prog;

public class EtatPlateau {

    private int[][] etatFinal;
    private String  listeMouvements;
    private int[][] listeTuiles;
    private int[]   coordZero = new int[2];
    

    public EtatPlateau(String listeMouvements, int[][] listeTuiles, int[][] etatFinal) {
        this.listeMouvements = listeMouvements;
        this.listeTuiles = listeTuiles;
        this.etatFinal = etatFinal;
        
        for (int i = 0; i < listeTuiles.length; i++) { // définit CoordZero
            for (int j = 0; j < listeTuiles.length; j++) {
                if(listeTuiles[i][j] == 0) {
                    coordZero[0] = i;
                    coordZero[1] = j;
                }
            }
        }
    }
    
    public int getHauteur() {
        return listeMouvements.length();
    }
    
    public String toHashKey() {
        String tmp = "";
        
        for(int[] ligne : listeTuiles)
            for(int tuile : ligne)
                tmp += tuile;
        
        return tmp;
    }
    
    public boolean estFinal(){
        for (int i = 0; i < listeTuiles.length; i++) {
            for (int j = 0; j < listeTuiles.length; j++) {
                if (listeTuiles[i][j] != etatFinal[i][j])
                    return false;
            }
        }
        return true;
        
    }
    
    public boolean deplacementPossible(Deplacement d) {
        
        if (d == Deplacement.Bas && coordZero[0] == 0 )
            return false;
        if (d == Deplacement.Haut && coordZero[0] == listeTuiles.length-1 )
            return false;
        if (d == Deplacement.Droite && coordZero[1] == 0 )
            return false;
        if (d == Deplacement.Gauche && coordZero[1] == listeTuiles.length-1 )
            return false;
        
        return true;
    }
    //--- Getters et setters
    
    public String getListeMouvements() {
        return listeMouvements;
    }

    public void setListeMouvements(String listeMouvements) {
        this.listeMouvements = listeMouvements;
    }

    public int[][] getListeTuiles() {
        return listeTuiles;
    }

    public void setListeTuiles(int[][] listeTuiles) {
        this.listeTuiles = listeTuiles;
    }

    public int[][] getEtatFinal() { // A IMPLEMENTER
        return etatFinal;
    }

    
    
    public int getScoreManatthan(){
        return getBorneMeilleureSolution() + getListeMouvements().length();        
    }
    
    
    /*
     * Compare les prochains états possibles au score proposé et renvoie celui qui dépasse le plus le score  
     * Si aucun ne dépasse le score proposé, renvoie null.
     */
    public EtatPlateau getMeilleurProchainEtat(int meilleurScore) {
        int[][] etatFinal = getEtatFinal();
        EtatPlateau meilleurSolution = null;

        EtatPlateau concurent;
        int scoreConcurent;
        for (Deplacement mouv : Deplacement.values()) { // pour chaque mouvement possible
            concurent = getEtatPlateauApresAction(mouv);
            if (concurent != null) {
                scoreConcurent = concurent.getScoreManatthan();
                if (scoreConcurent > meilleurScore) {
                    meilleurScore = scoreConcurent;
                    meilleurSolution = concurent;
                }
            }
        }
        return meilleurSolution;
    }

    /*
     * Compare les prochains états possibles et renvoie le plus proche de la solution
     */
    public EtatPlateau getMeilleurProchainEtat() {
        return getMeilleurProchainEtat(Integer.MAX_VALUE);
    }

    /*
    *   Renvoie l'état du plateau après le déplacement d'une tuile dans la case vide.
    *   Si le déplacement est impossible, renvoie null.
    *
     */
    public EtatPlateau getEtatPlateauApresAction(Deplacement deplacement) {
        int[][] nouvelleListeTuiles;
        nouvelleListeTuiles = new int[listeTuiles.length][listeTuiles.length];

        // On cherche la case '0'
        int i, j;
        i = coordZero[0];
        j = coordZero[1];
                if (nouvelleListeTuiles[i][j] == 0 && deplacementPossible(deplacement)) {
                    switch (deplacement.toString().charAt(0)) {
                        case 'D':
                                nouvelleListeTuiles[i][j] = nouvelleListeTuiles[i][j - 1];
                                nouvelleListeTuiles[i][j - 1] = 0;
                            break;

                        case 'G':
                                nouvelleListeTuiles[i][j] = nouvelleListeTuiles[i][j + 1];
                                nouvelleListeTuiles[i][j + 1] = 0;
                            break;

                        case 'H':
                                nouvelleListeTuiles[i][j] = nouvelleListeTuiles[i + 1][j];
                                nouvelleListeTuiles[i + 1][j] = 0;
                            break;

                        case 'B':
                                nouvelleListeTuiles[i][j] = nouvelleListeTuiles[i-1][j];
                                nouvelleListeTuiles[i - 1][j] = 0;
                            break;

                        default:
                            return null;
                    }
                } else
                    return null;
                
        return new EtatPlateau(listeMouvements + deplacement, nouvelleListeTuiles, etatFinal);

    }

    public int getBorneMeilleureSolution() { // A OPTIMISER

        int cout = 0; // t'as vu, je peux appeler ma variable 'cout' parce qu'on est pas en C++  :P

        //Pour chaque tuile dans la tuile dans la solution final
        for (int col = 0; col < listeTuiles.length; col++) {
            for (int ligne = 0; ligne < listeTuiles.length; ligne++) {

                // on cherche la même tuile dans l'état du plateau (this)
                for (int i = 0; i < listeTuiles.length; i++) {
                    for (int j = 0; j < listeTuiles.length; j++) {
                        // une fois trouvé, on ajoute au cout sa distance par rapport à sa place final.
                        if (etatFinal[col][ligne] == listeTuiles[i][j]) {
                            cout = cout + Math.abs(j - i);
                        }

                    }
                }

            }
        }
        return cout;
    }

    
    /*
    *   Renvoie une chaine représentant la lecture des tuiles du plateau de gauche à droite et de bas en haut.
    *   Les numéros des tuiles sont séparés par des espaces.
    */
    public String toString() {
        String s = "";

        for (int j = 0; j < listeTuiles.length; j++) {
            for (int i = 0; i < listeTuiles.length; i++) {
                s = s + Integer.toString(listeTuiles[i][j]);
            }
        }
        return s;
    }

}
