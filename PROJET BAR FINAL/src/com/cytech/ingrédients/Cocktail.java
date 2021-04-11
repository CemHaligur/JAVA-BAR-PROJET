package com.cytech.ingrédients;

import java.util.*;

public class Cocktail extends BoissonMere{

    private List<Boisson> boisson;
    private boolean disponibilite = false;
    private List<String> isBoisson = Arrays.asList(new String[] {"Boisson"});
    private List<String> isBoissonAlco = Arrays.asList(new String[] {"boissonAlcoolisee"});
    private List<String> isBoissonNonAlco = Arrays.asList(new String[] {"boissonNonAlcoolisee"});




    // Constructeur

    public Cocktail(String nom, double contenance, List<Boisson> boisson) {
        super(nom,contenance);
        this.boisson = boisson;
    }

    // Getter

    public List<Boisson> getBoisson() {
        return boisson;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    // Autres Methodes

    @Override
    public String toString() {
        String cocktail;
        String compo;
        cocktail = getNom().toUpperCase() + " ⇒ Contenance : " + getContenance() + " ml"+ " [DEGRÉ D'ALCOOL : " + calculerDegreAlcool() + "°] [DEGRÉ DE SUCRE : " + calculerDegreSucre() +"°] Prix : " + calculerPrix() + " €";
        compo = cocktail + "\n                ↪ Composition : [|";
        for(Boisson b : this.boisson){
            compo += b.getNom().toUpperCase() + "|";
        }
        compo += "]";
        return compo;
    }

    public double calculerPrix(){
        double calc = 0;
        for(Boisson b : this.boisson){
            calc += b.getPrix();
        }
        calc *= 1.1;
        return calc;
    }

    public double calculerDegreAlcool(){
        double calc = 0;
        for(Boisson b : this.boisson){
            String dechiffrer = b.getClass().getSimpleName();
            if(isBoissonAlco.contains(dechiffrer)){
                calc += ((boissonAlcoolisee) b).getDegreAlcool();
            }
        }
        return calc;
    }

    public double calculerDegreSucre(){
        double calc = 0;
        for(Boisson b : this.boisson){
            String dechiffrer = b.getClass().getSimpleName();
            if(isBoissonNonAlco.contains(dechiffrer)){
                calc += ((boissonNonAlcoolisee) b).getDegreSucre();
            }
        }
        return calc;
    }

    public void Disponibilite(HashMap<Boisson, Integer> stockBoisson) {
        boolean valider = true;
        for(Boisson b : this.boisson){
            if(!stockBoisson.containsKey(b))
            {
                valider = false;
            }
            else if(stockBoisson.get(b) <= 0 )
            {
                valider = false;
            }
        }
        this.disponibilite = valider;
    }
}

