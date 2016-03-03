package Prog;

public class Solveur {
    private int[][] plateauInitial;
    private int[][] plateauFinal;

    public Solveur(int[][] plateauInitial, int[][] plateauFinal) {
        this.plateauInitial = plateauInitial;
        this.plateauFinal = plateauFinal;
    }
            
    /**
     * Dit si un jeu est solvable ou non
     * @param plateauInitial Plateau de départ
     * @param plateauFinal Plateau d'arriver souhaiter
     * @return true si le jeu est fesable
     */
    public boolean estSolvable(int[][] plateauInitial, int[][] plateauFinal){
       /* int deplacementCaseBlacne, nbPermutation;
        int i1,i2,j1,j2;
        
        //On cercher le nombre de deplacemment de la case vide
        i1=0;
        j1=0;
        while (plateauInitial[i1][j1]!=0){
            if (j1==plateauInitial[i1].length-1){
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
        j2=0;
        
        
        return deplacementCaseBlacne%2==nbPermutation%2;*/
        return true;
    }
}
