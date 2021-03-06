package Prog;

public class Solveur {

    protected EtatPlateau etatInit;
    protected String Solution;
    protected int longueurSolution;
    protected long tempsCPUNS;
    protected int nbSommetsVisite;
    protected int tailleMax;
    protected String nomAlgo;
    
    public Solveur(EtatPlateau etatInit) {
        this.etatInit = etatInit;
        nomAlgo = "Générale";
    }
            
    /**
     * Dit si le jeu est solvable ou non
     * @return true si le jeu est fesable
     */
    public boolean estSolvable(){
        int[][] plateauInitial = etatInit.getListeTuiles();
        int[][] plateauFinal = etatInit.getEtatFinal();
        
        int[][] plateau;
        int deplacementCaseBlacne, nbPermutation;
        int i1,i2,j1,j2, valeurTmp; 
        
        plateau = new int[plateauInitial.length][plateauInitial.length];
        for (int i=0;i<plateauInitial.length;i++){
            plateau[i] = plateauInitial[i].clone();
        }

        //On cercher le nombre de deplacemment de la case vide
        i1=0;
        j1=0;
        while (plateau[i1][j1]!=0){
            if (j1==plateau[i1].length-1){
                j1=0;
                i1++;
            } else {
                j1++;
            }
        } 
        i2=0;
        j2=0;
        while (plateauFinal[i2][j2]!=0){
            if (j2==plateauFinal[i2].length-1){
                j2=0;
                i2++;
            } else {
                j2++;
            }
        } 
        deplacementCaseBlacne = Math.abs(i1-i2)+Math.abs(j1-j2);
        
        //On cherche le nb de permutation
        nbPermutation=0;
        i1=0;
        j1=0;
        while (i1<plateauFinal.length){
            i2=0;
            j2=0;
            while (i2<plateau.length && plateau[i2][j2]!=plateauFinal[i1][j1]){
                if (j2==plateau[i2].length-1){
                    j2=0;
                    i2++;
                } else {
                    j2++;
                }
            } 
            if (i2==plateau.length){
                return false;
            } else if (i1!=i2 || j1!=j2){
                valeurTmp = plateau[i2][j2];
                plateau[i2][j2] = plateau[i1][j1];
                plateau[i1][j1] = valeurTmp;
                nbPermutation++;
            }
            
            if (j1==plateauFinal[i1].length-1){
                j1=0;
                i1++;
            } else {
                j1++;
            }
        } 
        
        return deplacementCaseBlacne%2==nbPermutation%2;
    }
    
    public EtatPlateau solve() {
        return null;
    }
    
    public String getSolution() {
        return Solution;
    }

    public int getLongueurSolution() {
        return longueurSolution;
    }

    public long getTempsCPUNS() {
        return tempsCPUNS;
    }

    public int getNbSommetsVisite() {
        return nbSommetsVisite;
    }

    public int getTailleMax() {
        return tailleMax;
    }

    public String getNomAlgo() {
        return nomAlgo;
    }
}
