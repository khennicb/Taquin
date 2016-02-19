package Prog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EntreeSortieFichier {
    private String dossier;
    
    public EntreeSortieFichier(String dossier){
        this.dossier=dossier;
    }
    
    /**
     * 
     * Lit un fichier et rend les tableau associer
     * @param filename nom du fichier dans Data
     * @return int[2][k][k] : Le 1er tableau est la configuration initial, le 2eme est la configuration final. 
     * @throws FileNotFoundException Est lever si le fichier est inexistant
     */
    public int[][][] readPlateau(String filename) throws FileNotFoundException, ExceptionFormatFichier{
        int[][][] retour;
        String[] valeurLigne;
        String ligne = null;
        int size;
        BufferedReader br = new BufferedReader(new FileReader(dossier+File.separator+filename));    
        
        try {
            ligne = br.readLine();
            size = Integer.valueOf(ligne);
            retour = new int[2][size][size];
            for (int i=0;i<size;i++){
                ligne = br.readLine();
                valeurLigne = ligne.split(" ");
                for (int j=0;j<size;j++) {
                    retour[0][i][j] = Integer.valueOf(valeurLigne[j]);
                }
            }
            for (int i=0;i<size;i++){
                ligne = br.readLine();
                valeurLigne = ligne.split(" ");
                for (int j=0;j<size;j++) {
                    retour[1][i][j] = Integer.valueOf(valeurLigne[j]);
                }
            }
        } catch (IOException e) {
            throw new ExceptionFormatFichier("Fichier trop court");
        } catch (NumberFormatException e) {
            throw new ExceptionFormatFichier("Nombre attendu, Caractere trouvé : "+ligne);
        } catch (ArrayIndexOutOfBoundsException e){
            throw new ExceptionFormatFichier("Il manque des données sur la ligne : "+ligne);
        }    
        return retour;
    }
    
}
