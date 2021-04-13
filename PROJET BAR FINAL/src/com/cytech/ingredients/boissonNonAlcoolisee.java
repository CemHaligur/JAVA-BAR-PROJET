package com.cytech.ingredients;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;
import java.util.HashMap;
import java.nio.*;

public class boissonNonAlcoolisee extends Boisson {

    private double degreSucre;

    public boissonNonAlcoolisee(String nom, double prix, double contenance, double degreSucre) {
        super(nom,prix,contenance);
        this.degreSucre = degreSucre;
    }

    // Getter

    public double getDegreSucre() {
        return degreSucre;
    }

    // Autres Methodes

    public String toString() {
        return   this.getNom().toUpperCase() +
                " ⇒ Contenance : " + this.getContenance() + " ml " + "[DEGRÉ DE SUCRE : " + getDegreSucre() + "°] à " + this.getPrix() +
                "€ ";
    }


}

