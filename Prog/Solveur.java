package Prog;

import Prog.IHM.Console;

public class Solveur {
    private int[][] plateauInitial;
    private int[][] plateauFinal;

    public Solveur(int[][] plateauInitial, int[][] plateauFinal) {
        this.plateauInitial = plateauInitial;
        this.plateauFinal = plateauFinal;
    }
            
    /**
     * Dit si le jeu est solvable ou non
     * @return true si le jeu est fesable
     */
    public boolean estSolvable(){
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
}