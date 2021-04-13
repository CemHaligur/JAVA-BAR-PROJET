package com.cytech.ingredients;

import java.util.Objects;

public class Boisson extends BoissonMere{

    private double prix;


    public Boisson(String nom, double prix, double contenance){
        super(nom,contenance);
        this.prix = prix;
    }

    // Getter

    public double getPrix() {
        return prix;
    }

    // Autre Methode

    public String toString() {
        return  this.getNom().toUpperCase() +
                " ⇒ Contenance : " + this.getContenance() + " ml à " + this.getPrix() +
                "€ ";
    }

}


