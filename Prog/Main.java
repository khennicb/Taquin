package Prog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {   
        final String pathData = "Data";
        ArrayList<String> modeJeux = new ArrayList();
        modeJeux.add("Jeux");
        modeJeux.add("Profondeur");
        //Ajouter Modification est modifier le if a la ligne 118
        
        EntreeSortieFichier fichier;
        int[][][] plateau;
        EtatPlateau etatPlateau;
        Solveur solveur;
        String map = null;
        String mode = null;
        Scanner input = new Scanner(System.in);
        
        int i;
        String choix;
        
        i=0;      
        while (i<args.length){
            if("-m".equals(args[i])){
                i++;
                if (i<args.length && args[i].length()>0 && args[i].charAt(0)!='-' && modeJeux.contains(args[i])){
                    mode = args[i];
                } else {
                    String chaine = "Syntaxe : -m {";
                    for(String s : modeJeux){
                        chaine+=s+"|";
                    }
                    chaine = chaine.substring(0, chaine.length()-1)+"}";
                    System.out.println(chaine);
                    return;
                }
            } else if("-p".equals(args[i])) {
                i++;
                if (i<args.length && args[i].length()>0 && args[i].charAt(0)!='-'){
                    map = args[i];
                } else {
                    System.out.println("Syntaxe : -m {PlateauDeJeu}");
                    return;
                }
            } else {
                String chaine = "Syntaxe : -m {";
                for(String s : modeJeux){
                    chaine+=s+"|";
                }
                chaine = chaine.substring(0, chaine.length()-1)+"}";
                System.out.println(chaine);
                System.out.println("          -p {PlateauDeJeu}");
                return;
            }
            i++;
        }
        
        if (mode==null){
            System.out.println("Que voulez-vous faire ? ");
            for (i=0;i<modeJeux.size();i++){
                System.out.println(i+" - "+modeJeux.get(i));
            }
            int choixNombre;
            do {
                choix = input.nextLine();
                try {
                    choixNombre = Integer.valueOf(choix);
                } catch (NumberFormatException e){
                    System.out.println("Choisir un nombre entre 0 et "+(modeJeux.size()-1));
                    choixNombre=-1;
                }
                if (choixNombre<0 || choixNombre>=modeJeux.size()){
                    System.out.println("Choisir un nombre entre 0 et "+(modeJeux.size()-1));
                }
            } while (choixNombre<0 || choixNombre>=modeJeux.size()); 
            mode = modeJeux.get(choixNombre);
        }
        if (map==null){
            File file = new File(pathData);
            File[] files = file.listFiles();
            System.out.println("Quel carte voulez-vous choisir : Taper un nombre ou le nom du fichier (dans le dossier "+pathData+") ? ");
            for (i=0;i<files.length;i++){
                System.out.println(i+" - "+files[i].getName());
            }
            choix = input.nextLine();
            try {
                map=files[Integer.valueOf(choix)].getName();
            } catch (NumberFormatException e){
                map=choix;
            } catch (ArrayIndexOutOfBoundsException e) {
                map=choix;
            }
        }
        
        fichier = new EntreeSortieFichier(pathData);
        try {
            plateau = fichier.readPlateau(map);
        } catch (FileNotFoundException ex) {
            System.out.println("Fichier non trouvé");
            return;
        } catch (ExceptionFormatFichier ex) {
            System.out.println("Fichier au mauvais format");
            return;
        }
        etatPlateau = new EtatPlateau("", plateau[0], plateau[1]);
        solveur = new Solveur(etatPlateau);
        if (solveur.estSolvable()) {
            if ("Jeux".equals(mode)){
                Jeu jeu = new Jeu(etatPlateau);
                jeu.lancerLeJeu();
            } else {
                //TO DO
                System.err.println("Rien !!!!!!!!!!!!!!!!!!!!!!!");
            }
        } else {
            System.out.println("Le jeu n'est pas réalisable");
            return;
        }
    }
    
}
