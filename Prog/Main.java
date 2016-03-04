package Prog;

import Prog.IHM.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {   
        final String pathData = "Data";
        final String pathReport = "Report";

        HashMap<String,String> modeJeux = new HashMap();
        modeJeux.put("Jouer","jeux");
        modeJeux.put("Résoudre en Largueur","largueur");
        modeJeux.put("Résoudre en Profondeur","profondeur");
        modeJeux.put("Résoudre avec Meilleur d'abord","meilleur");
        modeJeux.put("Générer un Rapport","rapport");
        //Ajouter Modification est modifier le if a la ligne 118
        
        EntreeSortieFichier fichier;
        int[][][] plateau;
        EtatPlateau init;
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
                if (i<args.length && args[i].length()>0 && args[i].charAt(0)!='-' && modeJeux.containsValue(args[i])){
                    mode = args[i];
                } else {
                    String chaine = "Syntaxe : -m {";
                    for(String s : modeJeux.values()){
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
                for(String s : modeJeux.values()){
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
            ArrayList<String> list = new ArrayList();
            i=0;
            for (String s : modeJeux.keySet()){
                System.out.println(i+" - "+s);
                list.add(modeJeux.get(s));
                i++;
            }
            int choixNombre;
            do {
                choix = input.nextLine();
                try {
                    choixNombre = Integer.valueOf(choix);
                } catch (NumberFormatException e){
                    System.out.println("Choisir un nombre entre 0 et "+(list.size()-1));
                    choixNombre=-1;
                }
                if (choixNombre<0 || choixNombre>=list.size()){
                    System.out.println("Choisir un nombre entre 0 et "+(list.size()-1));
                }
            } while (choixNombre<0 || choixNombre>=list.size()); 
            mode = list.get(choixNombre);
        }
        if (map==null){
            File file = new File(pathData);
            File[] files = file.listFiles();
            System.out.println("Quel carte voulez-vous choisir ? Tapez un nombre ou le nom du fichier (dans le dossier "+pathData+")");
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
        
        fichier = new EntreeSortieFichier(pathData,pathReport);
        try {
            plateau = fichier.readPlateau(map);
        } catch (FileNotFoundException ex) {
            System.out.println("Fichier non trouvé");
            return;
        } catch (ExceptionFormatFichier ex) {
            System.out.println("Fichier au mauvais format");
            return;
        }
        init = new EtatPlateau("", plateau[0], plateau[1]);
        solveur = new Solveur(init);
        Console c = new Console();
        
        if (solveur.estSolvable()) {
            if ("jeux".equals(mode)){
                Jeu jeu = new Jeu(init);
                try {
                    jeu.lancerLeJeu();
                } catch (ExceptionQuitter ex) {}
            } else if ("largueur".equals(mode)){
                SolveurMitrailleur s = new SolveurMitrailleur(init);
                EtatPlateau sol = s.solve();
                c.replayEtat(sol, init);
                //System.out.println("la solution est : " + sol.getListeMouvements());
            } else if ("profondeur".equals(mode)){
                SolveurBazooka s = new SolveurBazooka(init);
                EtatPlateau sol = s.solve();
                c.replayEtat(sol, init);
                //System.out.println("la solution est : " + sol.getListeMouvements());
            } else if ("meilleur".equals(mode)){
                SolveurSniper s = new SolveurSniper(init);
                EtatPlateau sol = s.solve();
                c.replayEtat(sol, init);
                //System.out.println("la solution est : " + sol.getListeMouvements());
            } else if ("rapport".equals(mode)){
                Solveur[] solveurs = {new SolveurSniper(init), new SolveurBazooka(init), new SolveurMitrailleur(init)};
                for (Solveur s : solveurs){
                    s.solve();
                }
                try {
                    fichier.writeResult(solveurs, map, map);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            System.out.println("Le jeu n'est pas réalisable");
            return;
        }
    }
    
}
