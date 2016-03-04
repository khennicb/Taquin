package Prog;

import java.util.Stack;

public class SolveurBazooka extends Solveur {

    HashmapEtats map;
    Stack<EtatPlateau> pile;
    EtatPlateau solution;
    boolean algoEnCours;

    int profondeurLimite;

    public SolveurBazooka(EtatPlateau etatInit) {
        super(etatInit);
        solution = null;
        longueurSolution = 0;
        nbSommetsVisite = 0;
        tailleMax = 0;
    }

    public EtatPlateau solve() {

        if (!estSolvable()) {
            System.out.println("Insolvable");
            return null;
        }

        EtatPlateau etatCourant;
        profondeurLimite = 5;

        while (solution == null) {
            //initialisation du solveur
            algoEnCours = true;
            map = new HashmapEtats();
            map.add(etatInit);
            pile = new Stack<>();
            pile.push(etatInit);

            while (algoEnCours) {
                etatCourant = pile.pop();

                generation(etatCourant);

                testSolution(etatCourant);
                //if (solution != null) { 
                if (pile.isEmpty()) { // on vérifie que ce n'est pas fini
                    algoEnCours = false;
                }
            }
            //on retente avec un hauteur limite de 10 de plus
            profondeurLimite += 10;
        }

        return solution;
    }

    /* Ajoute dans la pile les fils de e qui sont admissibles
     *  admissible = peut devenir une meilleure solution
     *
     */
    private int generation(EtatPlateau e) {
        // si cette etat ne peut pas devenir une meilleure solution
        if (solution != null && e.getScoreManatthan() + 1 >= solution.getScoreManatthan()) {
            return 0;
        } else if (e.getScoreManatthan() + 1 >= profondeurLimite) {
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
                return true;
            }
        } else {
            return false;
        }
    }
}
