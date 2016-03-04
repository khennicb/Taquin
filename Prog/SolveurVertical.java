/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prog;

import java.util.Stack;

/**
 *
 * @author ben
 */
public class SolveurVertical extends Solveur {

    HashmapEtats map;
    Stack<EtatPlateau> pile;
    EtatPlateau solution;
    boolean algoEnCours;

    public SolveurVertical(EtatPlateau etatInit) {
        super(etatInit);
        map = new HashmapEtats();
        map.add(etatInit);
        pile = new Stack<>();
        solution = null;
        nomAlgo = "Profondeur";
    }

    public EtatPlateau solve() {
        long time = System.nanoTime();
        Solution = null;
        longueurSolution = 0;
        nbSommetsVisite = 0;
        tailleMax = 0;
        
        if (!estSolvable()){
            System.out.println("Insolvable");
            return null;
        }
        
        algoEnCours = true;
        pile.push(etatInit);
        EtatPlateau etatCourant;
        
        while (algoEnCours) {
            etatCourant = pile.pop();
            
            //etatCourant.affiche();

            generation(etatCourant);

            testSolution(etatCourant);
            //if (solution != null) { 
            if (pile.isEmpty()) { // on vérifie que ce n'est pas fini
                algoEnCours = false;
            }

        }
        
        tempsCPUNS = System.nanoTime()-time;
        return solution;
    }

    /* Ajoute dans la pile les fils de e qui sont admissibles
     *  admissible = peut devenir une meilleure solution
     *
     */
    private int generation(EtatPlateau e) {
        // si cette etat ne peut pas devenir une meilleure solution
        if (solution != null && e.getScoreManatthan()+1 >= solution.getScoreManatthan()) {
            return 0;
        }else if (e.getScoreManatthan()+1 >= 50) {
            return 0;
        }
        int count = 0;

        EtatPlateau etatGenere;
        for (Deplacement d : Deplacement.values()) {
            etatGenere = e.getEtatPlateauApresAction(d);

            // si le déplacement est possible et l'état généré est admissible
            if (etatGenere != null && map.add(etatGenere)) {
                pile.push(etatGenere);
                count++;
            }
        }

        return 0;
    }

    private boolean testSolution(EtatPlateau e) {
        if (e.estFinal()) {
            if (solution != null && e.getScoreManatthan() > solution.getScoreManatthan()) {
                System.err.println("SolveurVertical : Erreur au test d'une solution.  Une solution non admissible est parvenu à la methode testSolution");
                return false;
            } else {
                solution = e;
                super.Solution=e.getListeMouvements();
                return true;
            }
        } else {
            return false;
        }
    }
}
