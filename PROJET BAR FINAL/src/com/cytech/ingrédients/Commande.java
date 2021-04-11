package com.cytech.ingrédients;

import java.util.*;

public class Commande {
    private HashMap<Boisson, Integer> commandeBoisson = new HashMap<Boisson,Integer>();
    private HashMap<Cocktail, Integer> commandeCocktail = new HashMap<Cocktail,Integer>();;
    private List<String> isBoisson = Arrays.asList(new String[] {"boissonAlcoolisee", "boissonNonAlcoolisee", "Boisson"});
    private int numeroCommande;

    public Commande(){
        numeroCommande = genererInt(0,1000);
    }

    public Set<Boisson> getListBoisson(){
        return this.commandeBoisson.keySet();
    }

    public Set<Cocktail> getListCocktail(){
        return this.commandeCocktail.keySet();
    }

    public int getTotalBoisson(){
        return this.commandeBoisson.size();
    }

    public int getTotalCocktail(){
        return this.commandeCocktail.size();
    }

    public int getStockBoisson(Boisson b) {
        if(commandeBoisson.containsKey(b)){
            return commandeBoisson.get(b);
        }
        return 0;
    }

    public int getStockCocktail(Cocktail c) {
        if(commandeCocktail.containsKey(c)){
            return commandeCocktail.get(c);
        }
        return 0;
    }

    public void AjouterBoisson(Object o, int quantite){
        String dechiffrer = nomClass(o);
        if(isBoisson.contains(dechiffrer)) {
            Boisson b = (Boisson) o;
            if (this.commandeBoisson.containsKey(b)) {
                int quantite2 = this.commandeBoisson.get(b);
                quantite2 += quantite;
                this.commandeBoisson.put(b, quantite2);
            } else {
                this.commandeBoisson.put(b, quantite);
            }
            ajoutBoissonEffectue(b,quantite);
        }
        else if(dechiffrer.compareTo("Cocktail") == 0) {
            Cocktail c = (Cocktail) o;
            if (this.commandeCocktail.containsKey(c)) {
                int quantite2 = this.commandeCocktail.get(c);
                quantite2 += quantite;
                this.commandeCocktail.put(c , quantite2);
            } else {
                this.commandeCocktail.put(c, quantite);
            }
            ajoutCocktailEffectue(c,quantite);
        }
    }

    public String nomClass(Object o){
        return o.getClass().getSimpleName();
    }

    int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb = borneInf+random.nextInt(borneSup-borneInf);
        return nb;
    }

    public void ajoutBoissonEffectue(Boisson b, Integer quantite){
        System.out.println("La boisson : " + b.getNom() + " a bien été ajouté " + quantite + " fois !");
    }

    public void ajoutCocktailEffectue(Cocktail c, Integer quantite){
        System.out.println("Le cocktail : " + c.getNom() + " a bien été ajouté " + quantite + " fois !");
    }

    public boolean estVide() {
        return ((this.commandeBoisson.size() + this.commandeCocktail.size()) == 0);
    }

    public double calculerPrixTotal(){
        int quantite;
        double calc = 0;
        for(Boisson b : this.commandeBoisson.keySet()){
            quantite = this.commandeBoisson.get(b);
            calc += b.getPrix()*quantite;
        }

        for(Cocktail c : this.commandeCocktail.keySet()){
            quantite = this.commandeCocktail.get(c);
            calc += c.calculerPrix()*quantite;
        }

        return calc;
    }

    public void afficherCommande(){
        System.out.println("************************************************ COMMANDE n°" + numeroCommande + " *********************************************************");
        System.out.println("\n \uD83C\uDF7B [Boisson]");
        for(Boisson b : commandeBoisson.keySet()){
            System.out.print(   "   \n   ↪" + b + "(x" + this.commandeBoisson.get(b) + ")");
        }
        System.out.println("\n\n \uD83E\uDD42 [Cocktail]");
        for(Cocktail c : commandeCocktail.keySet()){
            System.out.print(   "   \n   ↪" + c + "(x" + this.commandeCocktail.get(c) + ")");
        }

        System.out.println("\n\n****************************************** PRIX TOTAL :"  + this.calculerPrixTotal() + " € *********************************************\n");
    }

    public void supprimerCommande(){
        this.commandeCocktail.clear();
        this.commandeBoisson.clear();
    }

}




