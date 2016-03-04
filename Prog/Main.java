package Prog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {         
        EntreeSortieFichier fichier;
        int[][][] plateau;
        Solveur solveur;
        String map = null;
        Boolean jeux = null;
        final String pathData = "Data";
        Scanner input = new Scanner(System.in);
        
        int i;
        String choix;
        
        i=0;      
        while (i<args.length){
            if("-p".equals(args[i])){
                jeux = true;
            } else if ("-a".equals(args[i])){
                jeux = false;
            } else {
                map = args[i];
            }
            i++;
        }
        
        if (jeux==null){
            System.out.println("Que voulez vous faire ? ");
            System.out.println("1 - Jouer");
            System.out.println("2 - Résoudre");  
            do {
                choix = input.nextLine();
            } while (!"1".equals(choix) && !"2".equals(choix)); 
            if ("1".equals(choix)){
                jeux =true;
            } else {
                jeux = false;
            }
        }
        if (map==null){
            File file = new File(pathData);
            File[] files = file.listFiles();
            System.out.println("Quel carte voulez-vous choisir : un nombre ou le nom (dans le dossier "+pathData+") ? ");
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
        EtatPlateau e = new EtatPlateau("", plateau[0], plateau[1]);
        solveur = new Solveur(e);
        if (solveur.estSolvable()) {
            if (jeux==true){
                EtatPlateau ep = new EtatPlateau("", plateau[0], plateau[1]);
                Jeu jeu = new Jeu(ep);
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
