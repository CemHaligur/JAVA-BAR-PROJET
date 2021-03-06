package com.cytech.ingredients;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;
import java.util.HashMap;
import java.nio.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Barman implements Serializable{


    private static HashMap<Boisson, Integer> stockBoisson = new HashMap<Boisson, Integer>();
    private static ArrayList<Cocktail> stockCocktail = new ArrayList<Cocktail>();
    //private static HashMap<Cocktail, Integer> stockCocktail = new HashMap<Cocktail, Integer>();
    private static List<Cocktail> NouveauCocktail = new ArrayList<Cocktail>();
    private static List<String> isBoissonAlco = Arrays.asList(new String[] {"boissonAlcoolisee"});
    private static List<String> isBoissonNonAlco = Arrays.asList(new String[] {"boissonNonAlcoolisee"});
    private static int nombreBoisson;
    private static int nombreCocktail;

    // Getter

    public static HashMap<Boisson, Integer> getStockB(){
        return stockBoisson;
    }

    public static ArrayList<Cocktail> getStockC(){
        return stockCocktail;
    }

    public static int getStockBoisson(Boisson b) {
        if(stockBoisson.containsKey(b)){
            return stockBoisson.get(b);
        }
        return 0;
    }


    // Autres Methodes

    public static void initBoissonsJSON() throws IOException, ParseException {
        try {
            stockBoisson.clear();
            stockBoisson = readJSONBoissonsStock(new FileReader("boisson.json"));
        }catch (Exception e){
            System.out.println("Le fichier n'a pas pu s'ouvrir !");
        }
    }

    public static void initCocktailsJSON() throws IOException, ParseException {
        try {
            stockCocktail.clear();
            stockCocktail = readJSONCocktails(new FileReader("cocktail.json"));
        }catch (Exception e){
            System.out.println("Le fichier n'a pas pu s'ouvrir !");
        }
    }

    public static void MENUPRINCIPALE() throws IOException, ParseException {
        System.out.println( "                      ??????    ???????????????\n" +
                "                     ????????????   ???????????????\n" +
                "                    ??????????????????  ???????????????\n" +
                "                  ??????????????????????????? ???????????????\n" +
                "                 ????????????????????????????????????????????????\n" +
                "               ??????????????????????????????????????????????????????\n" +
                "            ??????????????????????????????????????????????????????????????????\n" +
                "         ????????????????????????\uD835\uDD39\uD835\uDD40\uD835\uDD3C???\uD835\uDD4D\uD835\uDD3C???\uD835\uDD4C\uD835\uDD3C ???????????????????????????\n" +
                "       ??????????????????????????????????????????????????????????????????????????????????????????\n" +
                "     ?????????????????????\uD835\uDFD9. \uD835\uDD3C???\uD835\uDD4B???\uD835\uDD3C??? \uD835\uDD3B\uD835\uDD38???\uD835\uDD4A \uD835\uDD43\uD835\uDD3C \uD835\uDD39\uD835\uDD38??????????????????\n" +
                "      ??????????????????????????????????????????????????????????????????????????????????????????????????????\n" +
                "       ????????????????????????????????????????????????????????????????????????????????????????????????\n" +
                "        ??????????????????????????????????????????????????????????????????????????????????????????\n" +
                "         ????????????????????????????????????????????????????????????????????????????????????\n" +
                "         ????????????????????????????????????????????????????????????????????????????????????\n" +
                "         ????????????????????????????????????????????????????????????????????????????????????\n" +
                "         ????????????????????????????????????????????????????????????????????????????????????\n" +
                "?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\n" +
                "?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\n" +
                "?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\n  ");


        int choix = lireInt("");
        MenuRetour();
    }

    public static void ajouterBoissonStock(Boisson b, int quantite){
        int quantite2;
        if(stockBoisson.containsKey(b)){
            quantite2 = stockBoisson.get(b);
            quantite2 += quantite;
            stockBoisson.put(b,quantite2);
        }else{
            stockBoisson.put(b,quantite);
        }
    }

    public static void ajouterCocktailStock(Cocktail c){
        stockCocktail.add(c);
    }

    private static String lireString(String s1) throws IOError {
        String lecture;
        System.out.println(s1);
        Scanner s = new Scanner(System.in);
        lecture = s.nextLine();
        return lecture;
    }

    private static int lireInt(String s1) throws IOError {
        System.out.println(s1);
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }

    private static double lireDouble(String s1) throws IOError {
        System.out.println(s1);
        Scanner sc = new Scanner(System.in);
        return Double.parseDouble(sc.nextLine());
    }

    private static void ComposerCocktail(Commande maCommande) throws IOException, ParseException {
        Map Carte = afficherCarteSans(maCommande);
        int choix = -2;
        int choixBoisson = -2;
        boolean encore = true;
        List<Boisson> listeIngredient = new ArrayList<>();
        while((choix != 2) && (choixBoisson != 0)) {
            do {
                System.out.print("**************************************************************************************************************************\n");
                choixBoisson = lireInt("   [??? : NUMERO DE LA BOISSON??]         [????? : ANNULER??] ");
                if(listeIngredient.contains((Boisson) Carte.get(choixBoisson)))
                {
                    System.out.print("Cette boisson a d??j?? ??t?? ajout?? au cocktail !\n");
                }
            } while(listeIngredient.contains((Boisson) Carte.get(choixBoisson)));
            if (choix != 0) {
                if(choixBoisson != 0) {
                    listeIngredient.add((Boisson) Carte.get(choixBoisson));
                }
                if(listeIngredient.size() >= 1) {
                    System.out.println(" COMPOSITION ACTUELLE : " + listeIngredient + "\n");
                }
                if(listeIngredient.size() > 1)  {
                    if(!(listeIngredient.size() == nombreBoisson)){
                        System.out.print("************************************************************************************************************************\n");
                        choix = lireInt("   [????? : CONTINUER L'AJOUT??]               [????? : CREER LE COCKTAIL??]               [????? : ANNULER LA SAISIE??]  ");
                    }
                    else {
                        System.out.print("**************************************************************************************************************************\n");
                        choix = lireInt("   [????? : CREER LE COCKTAIL??]                        [????? : ANNULER LA SAISIE??]  ");
                    }
                    clearScreen();
                    if (choix == 0){
                        MenuRetour();
                    }
                    else if (choix == 2) {
                        String nom = lireString("VEUILLEZ SAISIR LE NOM DE VOTRE COCKTAIL : ");
                        int contenance = listeIngredient.size()*5;
                        Cocktail c = new Cocktail(nom, contenance, listeIngredient);
                        ajouterCocktailStock(c);
                    }
                }
            } else {
                MenuRetour();
            }
        }
        MenuRetour();
    }

    public static void MenuRetour() throws IOException, ParseException {
        Commande maCommande = new Commande();
        afficherCarte(maCommande);
        System.out.print("**************************************************************************************************************************\n");
        int choix = lireInt("   [????? : COMMANDER??]         [????? : CREER UN COCKTAIL??]         [????? : BARMAN MANAGER??]         [????? : QUITTER LE BAR??] ");
        clearScreen();
        if(choix == 1){
            Barman.SelectionnerBoisson(maCommande);
        }
        else if(choix == 2){
            Barman.ComposerCocktail(maCommande);
        }
        else if(choix == 3){
            Barman.ajouterBoissonManager();
        }
        else if(choix == 0){
            sauvegarderBoisson();
            sauvegarderCocktail();
            System.out.println("???????????????????????????????????????????????????        ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????      ?????????");
            System.out.println("???????????????????????????????????????????????????        ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????      ?????????");
            System.out.println("???????????????????????????????????????????????????        ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????      ?????????");
            System.out.println("???????????????????????????????????????????????????        ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????      ?????????");
            System.out.println("???????????????????????????????????????????????????        ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????      ?????????");
            System.out.println("???????????????????????????????????????????????????        ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????      ?????????");
        }
    }

    public static void ajouterBoissonManager() throws IOException, ParseException{
        System.out.println("**************************************************************************************************************************");
        System.out.println("BIENVENUE AU MENU MANAGER RESERV?? AU BARMAN !");
        System.out.println("**************************************************************************************************************************");

        int choix = lireInt("[????? : AJOUTER UNE BOISSON??]   [????? : MODIFIER UNE BOISSON??]   [????? : SUPPRIMER UNE BOISSON??]   [????? : REVENIR EN ARRIERE??]");
        if(choix == 0){
            MenuRetour();

        }
        else if(choix == 1){
            int choix2 = lireInt("[????? : ALCOOLISEE??]                        [????? : NON ALCOOLISEE??]");
            if(choix2 == 1){
                String nomBoisson = lireString("VEUILLEZ SAISIR UN NOM DE BOISSON : ");
                double degreAlcool = lireDouble("VEUILLEZ SAISIR UN DEGR?? D'ALCOOL : ");
                double contenance = 5.0;
                double prix = lireDouble("VEUILLEZ SAISIR LE PRIX : ");
                int quantit?? = lireInt("VEUILLEZ SAISIR LA QUANTIT?? A AJOUTER : ");
                Boisson R = new boissonAlcoolisee(nomBoisson,prix,contenance,degreAlcool);
                Barman.ajouterBoissonStock(R,quantit??);
                Barman.ajouterBoissonManager();
            }
            else if(choix2 == 2){
                String nomBoisson = lireString("VEUILLEZ SAISIR UN NOM DE BOISSON : ");
                double degreSucre = lireDouble("VEUILLEZ SAISIR UN DEGR?? DE SUCRE : ");
                double contenance = 5.0;
                double prix = lireDouble("VEUILLEZ SAISIR LE PRIX : ");
                int quantit?? = lireInt("VEUILLEZ SAISIR LA QUANTIT?? A AJOUTER : ");
                Boisson R = new boissonNonAlcoolisee(nomBoisson,prix,contenance,degreSucre);
                Barman.ajouterBoissonStock(R,quantit??);
                Barman.ajouterBoissonManager();

            }
        }
        else if(choix == 2){
            System.out.println("**************************************************************************************************************************");
            Commande maCommande = new Commande();
            Map Carte = afficherCarteSans(maCommande);
            System.out.println("**************************************************************************************************************************");
            int choix3 = lireInt("   [??? : NUMERO DE LA BOISSON A MODIFIER??]         [????? : ANNULER??] ");
            if(choix3 == 0){
                Barman.MenuRetour();
            }
            else
            {
                Object o = Carte.get(choix3);
                String dechiffrer = o.getClass().getSimpleName();
                if (isBoissonAlco.contains(dechiffrer)) {
                    System.out.println("**************************************************************************************************************************");
                    System.out.println((boissonAlcoolisee) Carte.get(choix3));
                    System.out.println("**************************************************************************************************************************");
                    int valeur = lireInt("[????? : LE NOM??]              [????? : DEGR?? D'ALCOOL??]              [????? : QUANTIT????]              [????? : PRIX??]");
                    if (valeur == 1) {
                        String nouveaunom = lireString("VEUILLEZ SAISIR LE NOUVEAU NOM : ");
                        boissonAlcoolisee b = (boissonAlcoolisee) Carte.get(choix3);
                        int quantite = stockBoisson.get(b);
                        double contenance = 5.0;
                        double degreAlcool = b.getDegreAlcool();
                        double prix = b.getPrix();
                        boissonAlcoolisee nouveauB = new boissonAlcoolisee(nouveaunom, prix, contenance, degreAlcool);
                        stockBoisson.put(nouveauB, quantite);
                        stockBoisson.remove(b);
                        ajouterBoissonManager();
                    }  else if (valeur == 2) {
                        double nouveaudegrealcool = lireDouble("VEUILLEZ SAISIR LE NOUVEAU DEGRE D'ALCOOL : ");
                        boissonAlcoolisee b = (boissonAlcoolisee) Carte.get(choix3);
                        int quantite = stockBoisson.get(b);
                        String nom = b.getNom();
                        double contenance = 5.0;
                        double prix = b.getPrix();
                        boissonAlcoolisee nouveauB = new boissonAlcoolisee(nom, prix, contenance, nouveaudegrealcool);
                        stockBoisson.put(nouveauB, quantite);
                        stockBoisson.remove(b);
                        ajouterBoissonManager();

                    } else if (valeur == 4) {
                        double nouveauprix = lireDouble("VEUILLEZ SAISIR LE NOUVEAU PRIX : ");
                        boissonAlcoolisee b = (boissonAlcoolisee) Carte.get(choix3);
                        int quantite = stockBoisson.get(b);
                        String nom = b.getNom();
                        double contenance = 5.0;
                        double degreAlcool = b.getDegreAlcool();
                        boissonAlcoolisee nouveauB = new boissonAlcoolisee(nom, nouveauprix, contenance, degreAlcool);
                        stockBoisson.put(nouveauB, quantite);
                        stockBoisson.remove(b);
                        ajouterBoissonManager();

                    } else if (valeur == 3){
                        int quantite = lireInt("VEUILLEZ SAISIR LA NOUVELLE QUANTIT?? : ");
                        boissonAlcoolisee b = (boissonAlcoolisee) Carte.get(choix3);
                        double prix = b.getPrix();
                        String nom = b.getNom();
                        double contenance = 5.0;
                        double degreAlcool = b.getDegreAlcool();
                        boissonAlcoolisee nouveauB = new boissonAlcoolisee(nom, prix, contenance, degreAlcool);
                        stockBoisson.put(nouveauB, quantite);
                        stockBoisson.remove(b);
                        ajouterBoissonManager();

                    }

                } else if (isBoissonNonAlco.contains(dechiffrer)) {
                    System.out.println("**************************************************************************************************************************");
                    System.out.println((boissonNonAlcoolisee) Carte.get(choix3));
                    System.out.println("**************************************************************************************************************************");
                    int valeur = lireInt("[????? : LE NOM??]              [????? : DEGR?? DE SUCRE??]              [????? : QUANTIT????]              [????? : PRIX??]");
                    if (valeur == 1) {
                        String nouveaunom = lireString("VEUILLEZ SAISIR LE NOUVEAU NOM : ");
                        boissonNonAlcoolisee b = (boissonNonAlcoolisee) Carte.get(choix3);
                        int quantite = stockBoisson.get(b);
                        double contenance = 5.0;
                        double degreSucre = b.getDegreSucre();
                        double prix = b.getPrix();
                        boissonNonAlcoolisee nouveauB = new boissonNonAlcoolisee(nouveaunom, prix, contenance, degreSucre);
                        stockBoisson.put(nouveauB, quantite);
                        stockBoisson.remove(b);
                        ajouterBoissonManager();

                    }  else if (valeur == 2) {
                        double nouveaudegresucre = lireDouble("VEUILLEZ SAISIR LE NOUVEAU DEGRE DE SUCRE : ");
                        boissonNonAlcoolisee b = (boissonNonAlcoolisee) Carte.get(choix3);
                        int quantite = stockBoisson.get(b);
                        String nom = b.getNom();
                        double contenance = 5.0;
                        double prix = b.getPrix();
                        boissonNonAlcoolisee nouveauB = new boissonNonAlcoolisee(nom, prix, contenance, nouveaudegresucre);
                        stockBoisson.put(nouveauB, quantite);
                        stockBoisson.remove(b);
                        ajouterBoissonManager();

                    } else if (valeur == 4) {
                        double nouveauprix = lireDouble("VEUILLEZ SAISIR LE NOUVEAU PRIX : ");
                        boissonNonAlcoolisee b = (boissonNonAlcoolisee) Carte.get(choix3);
                        int quantite = stockBoisson.get(b);
                        String nom = b.getNom();
                        double contenance = 5.0;
                        double degreSucre = b.getDegreSucre();
                        boissonNonAlcoolisee nouveauB = new boissonNonAlcoolisee(nom, nouveauprix, contenance, degreSucre);
                        stockBoisson.put(nouveauB, quantite);
                        stockBoisson.remove(b);
                        ajouterBoissonManager();

                    } else if (valeur == 3){
                        int quantite = lireInt("VEUILLEZ SAISIR LA NOUVELLE QUANTIT?? : ");
                        boissonAlcoolisee b = (boissonAlcoolisee) Carte.get(choix3);
                        double prix = b.getPrix();
                        String nom = b.getNom();
                        double contenance = 5.0;
                        double degreAlcool = b.getDegreAlcool();
                        boissonAlcoolisee nouveauB = new boissonAlcoolisee(nom, prix, contenance, degreAlcool);
                        stockBoisson.put(nouveauB, quantite);
                        stockBoisson.remove(b);
                        ajouterBoissonManager();

                    }
                }
            }
        }
        else if(choix == 3){
            Commande maCommande = new Commande();
            Map Carte = afficherCarteSans(maCommande);
            System.out.println("**************************************************************************************************************************");
            int choix4 = lireInt("   [??? : NUMERO DE LA BOISSON A SUPPRIMER??]         [????? : ANNULER??] ");
            if(choix4 == 0){
                MenuRetour();
            }
            else {
                int quantite = lireInt("VEUILLEZ SAISIR LA QUANTIT?? A RETIRER : ");
                if (choix > nombreCocktail) {
                    Boisson b = (Boisson) Carte.get(choix4);
                    RetirerBoissonStock(b, quantite);
                    Barman.ajouterBoissonManager();
                }
            }
        }
    }

    /*public static void choixBoisson(int choix){
        System.out.print("***************************************************************************************************************\n");
        choix = lireInt("   [??? : NUMERO DE LA BOISSON??]         [????? : ANNULER??] ");
        clearScreen();
    }

    public static void voirCommande(int choix, Commande maCommande){
        System.out.print("**************************************************************************************************************\n");
        choix = lireInt(" [??? : NUMERO DE LA BOISSON??]          [??0 : VOIR LA COMMANDE ??]" + "          [" + maCommande.calculerPrixTotal() + " ???]" );
        clearScreen();
    }

    public static void choixBoissonContinue(int choix, Commande maCommande){
        maCommande.afficherCommande();
        choix = lireInt("   [????? : CONTINUER L'AJOUT??]   [????? : VALIDER LA COMMANDE??]    ");
        clearScreen();
    }*/

    public static void SelectionnerBoisson(Commande maCommande) throws IOException, ParseException{
        Map Carte = afficherCarte(maCommande);
        int choix = -2;
        int choixBoisson = -2;
        int combien;

        if(maCommande.estVide()){
            System.out.print("**************************************************************************************************************************\n");
            choix = lireInt("   [??? : NUMERO DE LA BOISSON??]               [????? : ANNULER??] ");
            clearScreen();
        }
        else {
            System.out.print("*************************************************************************************************************************\n");
            choix = lireInt(" [??? : NUMERO DE LA BOISSON??]                    [??0 : VOIR LA COMMANDE ??]" + "                    [" + maCommande.calculerPrixTotal() + " ???]" );
            clearScreen();
        }
        if (choix != 0)
        {
            System.out.println(" \uD83C\uDF77 " + Carte.get(choix));
            System.out.print("\n             ??? Quantit?? : ");
            int max = 10;
            int max2 = 1000;
            if (choix > nombreCocktail) {
                max = getStockBoisson((Boisson) Carte.get(choix));
                max -= maCommande.getStockBoisson((Boisson) Carte.get(choix));
            }
            else{
                for(Boisson b : ((Cocktail) Carte.get(choix)).getBoisson())
                {
                    max = getStockBoisson(b);
                    max -= maCommande.getStockBoisson(b);
                    if(max < max2){
                        max2 = max;
                    }
                }
                max = max2;
            }
            System.out.println(max + "\n");
            if(max > 0) {
                if (choix > nombreCocktail) {
                    do {
                        combien = lireInt("??????NE PAS DEPASSER LE STOCK  ??????");
                    } while ((combien < 0) || (combien > max));
                }
                else{
                    do {
                        combien = lireInt("??????NE PAS DEPASSER LE STOCK  ??????");
                    } while ((combien < 0) || (combien > max));
                }
                maCommande.AjouterBoisson(Carte.get(choix), combien);
            } else {
                System.out.println("Il y a plus de stock");
            }

            SelectionnerBoisson(maCommande);
        }
        else {
            if (maCommande.estVide()) {
                MENUPRINCIPALE();
            } else {
                maCommande.afficherCommande();
                choix = lireInt("   [????? : CONTINUER L'AJOUT??]               [????? : VALIDER LA COMMANDE??]    ");
                clearScreen();
                switch (choix) {
                    case 0 :
                        SelectionnerBoisson(maCommande);
                        break;
                    case 1 :
                        System.out.println("[COMMANDE VALIDEE]");
                        if(maCommande.getTotalBoisson() > 0)
                        {
                            for(Boisson b : maCommande.getListBoisson())
                            {
                                RetirerBoissonStock(b,maCommande.getStockBoisson(b));
                            }
                        }
                        if(maCommande.getTotalCocktail() > 0)
                        {
                            for(Cocktail c : maCommande.getListCocktail())
                            {
                                for(Boisson b : c.getBoisson())
                                {
                                    RetirerBoissonStock(b,maCommande.getStockCocktail(c));
                                }
                            }
                        }
                        isDisponible();
                        MenuRetour();
                        break;
                    case 2 :
                        maCommande.supprimerCommande();
                        MenuRetour();
                        break;
                }

            }
        }
    }

    public static Map afficherCarteSans(Commande maCommande) throws IOException, ParseException {
        isDisponible();
        Map CarteSelec = new HashMap();
        int i = 1;
        if(nombreBoisson + nombreCocktail > 0) {
            Quota(maCommande, CarteSelec, i);
        } else {
            System.out.println("\nLE BAR EST VIDE...\n");
            MENUPRINCIPALE();
        }
        return CarteSelec;
    }

    private static void Quota(Commande maCommande, Map carteSelec, int i) {
        System.out.println(" \n \uD83E\uDD42 Les Boissons  " + "[" + nombreBoisson + "/" + stockBoisson.size() + "]");
        System.out.println(" \n    \uD83E\uDD42 Les Boissons Alcoolisee");
        for (Object o : stockBoisson.keySet()) {
            String dechiffrer = o.getClass().getSimpleName();
            if (isBoissonAlco.contains(dechiffrer)) {
                o = (boissonAlcoolisee) o;
                int fois = stockBoisson.get(o) - maCommande.getStockBoisson((boissonAlcoolisee) o);
                if (fois > 0) {
                    System.out.println("    \uD83E\uDD72" + i + "\uD83E\uDD72 : ?????? " + o + "(x" + fois + ")");
                    carteSelec.put(i, o);
                    i++;
                } else {
                    System.out.println("    VIDE");
                }
            }
        }
        System.out.println(" \n    \uD83E\uDD42 Les Boissons Non Alcoolisee");
        for (Object o : stockBoisson.keySet()) {
            String dechiffrer = o.getClass().getSimpleName();
            if(isBoissonNonAlco.contains(dechiffrer)){
                o = (boissonNonAlcoolisee) o;
                int fois = stockBoisson.get(o) - maCommande.getStockBoisson((boissonNonAlcoolisee) o);
                if (fois > 0) {
                    System.out.println("    \uD83E\uDD72" + i + "\uD83E\uDD72 : ?????? " + o + "(x" + fois + ")");
                    carteSelec.put(i, o);
                    i++;
                } else {
                    System.out.println("    VIDE");
                }
            }
        }
    }

    public static Map afficherCarte(Commande maCommande) {
        isDisponible();
        Map CarteSelec = new HashMap();
        int i = 1;
        System.out.println("**************************************************************************************************************************");
        if(nombreBoisson + nombreCocktail > 0) {
            System.out.println(" \uD83C\uDF7B Les Cocktails  " + "[" + nombreCocktail + "/" + stockCocktail.size() + "]");
            for (Cocktail c : stockCocktail) {
                if (c.isDisponibilite()) {
                    System.out.println("    \uD83E\uDD72" + i + "\uD83E\uDD72 : ?????? " + c);
                    CarteSelec.put(i, c);
                    i++;
                }
            }
            Quota(maCommande, CarteSelec, i);
        }
        return CarteSelec;
    }

    public static void isDisponible() {
        nombreCocktail = 0;
        for(Cocktail c : stockCocktail) {
            c.Disponibilite(stockBoisson);
            if(c.isDisponibilite()){
                nombreCocktail++;
            }
        }
        nombreBoisson = 0;
        for(Boisson b : stockBoisson.keySet()) {
            if(stockBoisson.get(b) > 0){
                nombreBoisson++;
            }
        }
    }

    public static void RetirerBoissonStock(Boisson b, int quantite){
        if(stockBoisson.containsKey(b)){
            int quantite2 = stockBoisson.get(b);
            if(quantite2 > 0){
                quantite2 -= quantite;
            }
            stockBoisson.put(b,quantite2);
        }
        else
        {
            stockBoisson.put(b,0);
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static  ArrayList<Cocktail> readJSONCocktails(FileReader file) throws IOException, ParseException {
        ArrayList<Cocktail> COCKTAILS = new ArrayList<Cocktail>();

        JSONParser jsonparser = new JSONParser();

        Object obj = jsonparser.parse(file);

        JSONObject jsonobject = (JSONObject) obj;

        JSONArray array = (JSONArray) jsonobject.get("Cocktails");
        // Pour chaque Cocktails
        for(int i=0;i<array.size();i++){
            ArrayList<Boisson> recetteC = new ArrayList<Boisson>();
            JSONObject cockt=(JSONObject) array.get(i);

            String nomC = (String)  cockt.get("nom");
            JSONArray arrayIngredient = (JSONArray) cockt.get("ingredients");
            for(int k = 0; k < arrayIngredient.size(); k++) {
                JSONObject boiss = (JSONObject) arrayIngredient.get(k);
                String nomB = (String) boiss.get("nom");
                Integer contenanceB = Integer.parseInt(boiss.get("contenance").toString());
                double prixMlB = (double) boiss.get("prixMl");
                if(null == boiss.get("degreAlcool"))
                {
                    double degre = (double) boiss.get("degreAlcool");
                    boissonAlcoolisee x = new boissonAlcoolisee(nomB,prixMlB,contenanceB,degre);
                    recetteC.add(x);
                } else {
                    double degre = (double) boiss.get("degreSucre");
                    boissonNonAlcoolisee x = new boissonNonAlcoolisee(nomB,prixMlB,contenanceB,degre);
                    recetteC.add(x);
                }
            }

            Cocktail coco = new Cocktail(nomC,2*recetteC.size(),recetteC);
            COCKTAILS.add(coco);

        }

        return COCKTAILS;
    }

    public static void sauvegarderBoisson() throws IOException, ParseException{
        writeJSONBoissonsStock(stockBoisson);
    }

    public static void sauvegarderCocktail() throws IOException, ParseException{
        writeJSONCocktails(stockCocktail);
    }

    public static void writeJSONCocktails(ArrayList<Cocktail> COCKTAILS) throws IOException {
        JSONObject obj=new JSONObject();


        JSONArray liste_Cocktails=new JSONArray(); // JSON liste
        for(Cocktail c: COCKTAILS){
            JSONObject obj_Cock=new JSONObject();
            obj_Cock.put("nom",c.getNom());
            JSONArray l_ingredient=new JSONArray();
            for(Boisson b : c.getBoisson()) {
                JSONObject obj_interne=new JSONObject();
                obj_interne.put("nom",b.getNom());
                obj_interne.put("prixMl",(double) b.getPrix());
                obj_interne.put("contenance",5.0);
                String cla = b.getClass().getSimpleName();
                if(cla.equals("boissonAlcoolisee")) {
                    obj_interne.put("degreAlcool",(double) ((boissonAlcoolisee) b).getDegreAlcool());

                } else if(cla.equals("boissonNonAlcoolisee")) {
                    obj_interne.put("degreSucre",(double) ((boissonNonAlcoolisee) b).getDegreSucre());
                }

                l_ingredient.add(obj_interne);

            } // end for ingredients

            obj_Cock.put("ingredients",l_ingredient);
            liste_Cocktails.add(obj_Cock);
        } // end for cocktail c
        obj.put("Cocktails",liste_Cocktails);


        try(FileWriter file=new FileWriter("cocktail.json")){
            file.write(obj.toString());
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }


    public static  HashMap<Boisson,Integer> readJSONBoissonsStock(FileReader file) throws IOException, ParseException {
        HashMap<Boisson,Integer> StockBoisson = new HashMap<Boisson,Integer>();
        JSONParser jsonparser = new JSONParser();
        Object obj= jsonparser.parse(file);
        JSONObject jsonobject=(JSONObject)obj;

        JSONArray array = (JSONArray) jsonobject.get("boissonAlcoolisee");
        for(int i=0;i<array.size();i++){
            JSONObject boisson=(JSONObject) array.get(i);
            String nom = (String) boisson.get("nom");
            Double prix = (Double) boisson.get("prixMl");
            Double degre = (Double) boisson.get("degreAlcool");
            Integer q = Integer.parseInt(boisson.get("quantite").toString());

            boissonAlcoolisee a = new boissonAlcoolisee(nom,prix,5.0,degre);
            StockBoisson.put(a,q);

        }
        JSONArray array2 = (JSONArray) jsonobject.get("boissonNonAlcoolisee");
        for(int i=0;i<array.size();i++){

            JSONObject boisson=(JSONObject) array2.get(i);
            String nom = (String) boisson.get("nom");

            String couleur = (String) boisson.get("couleur");
            Double prix = (Double) boisson.get("prixMl");
            Double degre = (Double) boisson.get("degreSucre");
            Integer q = Integer.parseInt(boisson.get("quantite").toString());

            boissonNonAlcoolisee a = new boissonNonAlcoolisee(nom,prix,5.0,degre);
            StockBoisson.put(a,q);

        }

        return StockBoisson;
    }

    public static void writeJSONBoissonsStock(HashMap<Boisson,Integer> StockStock) throws IOException {
        JSONObject obj=new JSONObject();

        //** S??par?? les alccol et les sucr??
        ArrayList<boissonAlcoolisee> Ba = new ArrayList<boissonAlcoolisee>();
        ArrayList<boissonNonAlcoolisee> Bs = new ArrayList<boissonNonAlcoolisee>();
        for(Boisson b: StockStock.keySet()){
            String cla = b.getClass().getSimpleName();
            if(cla.equals("boissonAlcoolisee")) {
                Ba.add((boissonAlcoolisee) b);
            } else if(cla.equals("boissonNonAlcoolisee")) {
                Bs.add((boissonNonAlcoolisee) b);
            }
        }

        JSONArray liste_BoissonsAlcoolisee=new JSONArray();
        for(boissonAlcoolisee b: Ba){
            JSONObject obj_interne=new JSONObject();
            obj_interne.put("nom",b.getNom());
            obj_interne.put("prixMl",(double) b.getPrix());
            obj_interne.put("degreAlcool",(double) b.getDegreAlcool());
            obj_interne.put("quantite",StockStock.get(b));
            liste_BoissonsAlcoolisee.add(obj_interne);
        }
        obj.put("boissonAlcoolisee",liste_BoissonsAlcoolisee);

        JSONArray liste_BoissonsNonAlcoolisee=new JSONArray();
        for(boissonNonAlcoolisee b : Bs){
            JSONObject obj_interne=new JSONObject();
            obj_interne.put("nom",b.getNom());
            obj_interne.put("prixMl",(double) b.getPrix());
            obj_interne.put("degreSucre",(double) b.getDegreSucre());
            obj_interne.put("quantite",StockStock.get(b));
            liste_BoissonsNonAlcoolisee.add(obj_interne);
        }
        obj.put("boissonNonAlcoolisee",liste_BoissonsNonAlcoolisee);

        // Go
        try(FileWriter file=new FileWriter("boisson.json")){
            file.write(obj.toString());
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

}

