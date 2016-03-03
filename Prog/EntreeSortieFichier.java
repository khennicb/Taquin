package Prog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EntreeSortieFichier {
    private String dossierCarteInitial;
    
    public EntreeSortieFichier(String dossierCarteInitial){
        this.dossierCarteInitial=dossierCarteInitial;
    }
    
    /**
     * 
     * Lit un fichier et rend les tableau associer. Il garantis qu'il y a UNE SEUL case vide (0)
     * @param filename nom du fichier dans Data
     * @return int[2][k][k] : Le 1er tableau est la configuration initial, le 2eme est la configuration final. 
     * @throws FileNotFoundException Est lever si le fichier est inexistant
     */
    public int[][][] readPlateau(String filename) throws FileNotFoundException, ExceptionFormatFichier{
        int[][][] retour;
        String[] valeurLigne;
        String ligne = null;
        int size;
        int nbZero=0;
        int j,j2;
        BufferedReader br = new BufferedReader(new FileReader(dossierCarteInitial+File.separator+filename));    
        
        try {
            ligne = br.readLine().split(" ")[0];
            size = Integer.valueOf(ligne);
            retour = new int[2][size][size];
            for (int i=0;i<size;i++){
                ligne = br.readLine();
                valeurLigne = ligne.split(" ");
                j=0;
                j2=0;
                while(j2<size) {
                    if(!valeurLigne[j].equals("")){
                        retour[0][i][j2] = Integer.valueOf(valeurLigne[j]);
                        if (Integer.valueOf(valeurLigne[j])==0){
                            nbZero++;
                        }
                        j2++;
                    }
                    j++;
                }
            }
            if (nbZero<1){
                throw new ExceptionFormatFichier("Pas de case vide (0) trouvé sur le plateau initial");
            } else if (nbZero>1){
                throw new ExceptionFormatFichier("Trop de case vide (0) trouvé sur le plateau initial");
            }
            nbZero=0;
            for (int i=0;i<size;i++){
                ligne = br.readLine();
                valeurLigne = ligne.split(" ");
                j=0;
                j2=0;
                while (j2<size) {
                    if(!valeurLigne[j].equals("")){
                        retour[1][i][j2] = Integer.valueOf(valeurLigne[j]);
                        if (Integer.valueOf(valeurLigne[j])==0){
                            nbZero++;
                        }
                        j2++;
                    }
                    j++;
                }
            }
            if (nbZero<1){
                throw new ExceptionFormatFichier("Pas de case vide (0) trouvé sur le plateau initial");
            } else if (nbZero>1){
                throw new ExceptionFormatFichier("Trop de case vide (0) trouvé sur le plateau initial");
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
