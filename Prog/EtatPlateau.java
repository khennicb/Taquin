package Prog;

public class EtatPlateau {
    private String listeMouvements;
    private int[][] listeTuiles;

    public EtatPlateau(String listeMouvements, int[][] listeTuiles) {
        this.listeMouvements = listeMouvements;
        this.listeTuiles = listeTuiles;
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
    
}
