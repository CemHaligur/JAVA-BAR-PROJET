package com.cytech.ingrédients;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;
import java.util.HashMap;
import java.nio.*;

public class boissonAlcoolisee extends Boisson {

    private double degreAlcool;

    public boissonAlcoolisee(String nom, double prix, double contenance,double degreAlcool) {
        super(nom,prix,contenance);
        this.degreAlcool = degreAlcool;
    }

    // Getter

    public double getDegreAlcool() {
        return degreAlcool;

    }

    public String toString() {
        return  this.getNom().toUpperCase() +
                " ⇒ Contenance : " + this.getContenance() + " ml " + "[DEGRÉ D'ALCOOL : " + getDegreAlcool() + "°] à " + this.getPrix() +
                "€ ";
    }


}

