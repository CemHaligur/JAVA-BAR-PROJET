package com.cytech.ingrédients;

abstract class BoissonMere {

    private String nom;
    protected double contenance;

    //Constructeur

    protected BoissonMere(String nom, double contenance){
        this.nom = nom;
        this.contenance = contenance;
    }

    //Getters

    public String getNom(){
        return this.nom;
    }

    public double getContenance(){
        return this.contenance;
    }


    //Autres Methodes

}

