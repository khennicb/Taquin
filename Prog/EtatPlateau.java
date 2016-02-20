package Prog;

public class EtatPlateau {

    private int[][] etatFinal;

    private String listeMouvements;
    private int[][] listeTuiles;
    private int dimension;

    public EtatPlateau(String listeMouvements, int[][] listeTuiles, int dimension, int[][] etatFinal) {
        this.listeMouvements = listeMouvements;
        this.listeTuiles = listeTuiles;
        this.dimension = dimension;
        this.etatFinal = etatFinal;
    }

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

    /*
     * Compare les prochains états possibles au score proposer et renvoie celui qui dépasse le plus le score  
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
                scoreConcurent = concurent.getBorneMeilleureSolution();
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
        nouvelleListeTuiles = new int[dimension][dimension];

        // On cherche la case '0'
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (nouvelleListeTuiles[i][j] == 0) { // Si la case est bien '0'

                    switch (deplacement.toString().charAt(0)) {
                        case 'H':
                            if (j == 0) {
                                return null;
                            } else {
                                nouvelleListeTuiles[i][j] = nouvelleListeTuiles[i][j - 1];
                                nouvelleListeTuiles[i][j - 1] = 0;
                            }

                        case 'B':
                            if (j == dimension - 1) {
                                return null;
                            } else {
                                nouvelleListeTuiles[i][j] = nouvelleListeTuiles[i][j + 1];
                                nouvelleListeTuiles[i][j + 1] = 0;
                            }

                        case 'G':
                            if (i == 0) {
                                return null;
                            } else {
                                nouvelleListeTuiles[i - 1][j] = nouvelleListeTuiles[i - 1][j];
                                nouvelleListeTuiles[i - 1][j] = 0;
                            }

                        case 'D':
                            if (i == dimension - 1) {
                                return null;
                            } else {
                                nouvelleListeTuiles[i + 1][j] = nouvelleListeTuiles[i][j];
                                nouvelleListeTuiles[i + 1][j] = 0;
                            }

                        default:
                            return null;
                    }
                }
            }
        }
        return new EtatPlateau(listeMouvements + deplacement, nouvelleListeTuiles, dimension, etatFinal);

    }

    public int getBorneMeilleureSolution() { // A OPTIMISER

        int cout = 0; // t'as vu, je peux appeler ma variable 'cout' parce qu'on est pas en C++  :P

        //Pour chaque tuile dans la tuile dans la solution final
        for (int col = 0; col < dimension; col++) {
            for (int ligne = 0; ligne < dimension; ligne++) {

                // on cherche la même tuile dans l'état du plateau (this)
                for (int i = 0; i < dimension; i++) {
                    for (int j = 0; j < dimension; j++) {
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

        for (int j = 0; j < dimension; j++) {
            for (int i = 0; i < dimension; i++) {
                s = s + Integer.toString(listeTuiles[i][j]) +" ";
            }
        }
        return s;
    }

}
