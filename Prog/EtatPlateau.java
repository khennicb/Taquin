package Prog;

public class EtatPlateau {

    private String listeMouvements;
    private int[][] listeTuiles;
    private int dimension;

    public EtatPlateau(String listeMouvements, int[][] listeTuiles, int dimension) {
        this.listeMouvements = listeMouvements;
        this.listeTuiles = listeTuiles;
        this.dimension = dimension;
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

    public EtatPlateau getEtatFinal() { // A IMPLEMENTER
        return null;
    }

    /*
     * Compare les prochains états possibles au score proposer et renvoie celui qui dépasse le plus le score  
     * Si aucun ne dépasse le score proposé, renvoie null.
    */
    public EtatPlateau getMeilleurProchainEtat(int meilleurScore) {
        EtatPlateau etatFinal = getEtatFinal();
        EtatPlateau meilleurSolution = null;
        char[] mouvements = {'H', 'B', 'G', 'D'};

        EtatPlateau concurent;
        int scoreConcurent;
        for (char mouv : mouvements) { // pour chaque mouvement possible
            concurent = getEtatPlateauApresAction(mouv);
            if (concurent != null) {
                scoreConcurent = concurent.getBorneMeilleureSolution(etatFinal);
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
    public EtatPlateau getEtatPlateauApresAction(char deplacement) { // A MODIFIER avec un type énuméré pour les déplacement.
        int[][] nouvelleListeTuiles;
        nouvelleListeTuiles = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                // On cherche la case '0'

                switch (deplacement) {
                    case 'H':
                        if (nouvelleListeTuiles[i][j] == 0) {
                            if (j == 0) {
                                return null;
                            } else {
                                nouvelleListeTuiles[i][j] = nouvelleListeTuiles[i][j - 1];
                                nouvelleListeTuiles[i][j - 1] = 0;
                                return new EtatPlateau(listeMouvements + "H", nouvelleListeTuiles, dimension);
                            }
                        }
                        break;
                    case 'B':
                        if (nouvelleListeTuiles[i][j] == 0) {
                            if (j == dimension - 1) {
                                return null;
                            } else {
                                nouvelleListeTuiles[i][j] = nouvelleListeTuiles[i][j + 1];
                                nouvelleListeTuiles[i][j + 1] = 0;
                                return new EtatPlateau(listeMouvements + "B", nouvelleListeTuiles, dimension);
                            }
                        }

                        break;
                    case 'G':
                        if (nouvelleListeTuiles[i][j] == 0) {
                            if (i == 0) {
                                return null;
                            } else {
                                nouvelleListeTuiles[i - 1][j] = nouvelleListeTuiles[i - 1][j];
                                nouvelleListeTuiles[i - 1][j] = 0;
                                return new EtatPlateau(listeMouvements + "G", nouvelleListeTuiles, dimension);
                            }
                        }

                        break;
                    case 'D':
                        if (nouvelleListeTuiles[i][j] == 0) {
                            if (i == dimension - 1) {
                                return null;
                            } else {
                                nouvelleListeTuiles[i + 1][j] = nouvelleListeTuiles[i][j];
                                nouvelleListeTuiles[i + 1][j] = 0;
                                return new EtatPlateau(listeMouvements + "D", nouvelleListeTuiles, dimension);
                            }
                        }

                        break;
                    default:
                        return null;
                }

            }
        }
        return null; //cas impossible

    }

    public int getBorneMeilleureSolution(EtatPlateau solution) { // A OPTIMISER

        int cout = 0;

        int[][] sol = solution.getListeTuiles();
        for (int col = 0; col < dimension; col++) {
            for (int ligne = 0; ligne < dimension; ligne++) {
                //on a séléctionné la tuile dans la solution final
                for (int i = 0; i < dimension; i++) {
                    for (int j = 0; j < dimension; j++) {
                        // on cherche la même tuile dans l'état du plateau
                        if (sol[col][ligne] == listeTuiles[i][j]) {
                            cout = cout + Math.abs(j - i);
                        }
                    }
                }
            }
        }
        return cout;
    }
}
