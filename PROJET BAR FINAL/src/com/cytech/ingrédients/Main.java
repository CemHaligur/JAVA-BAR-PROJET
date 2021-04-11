package com.cytech.ingrédients;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;
import java.util.HashMap;
import java.nio.*;

public class Main {

    public static void main(String[] args) {

        Boisson r = new boissonNonAlcoolisee("Cola",72,5,5.0);
        Barman.ajouterBoissonStock(r,10);
        Boisson c = new boissonAlcoolisee("Vodka",100,5,12.5);
        Barman.ajouterBoissonStock(c,2);
        Boisson s = new boissonAlcoolisee("Bière",250,5,5);
        Barman.ajouterBoissonStock(s,5);
        Cocktail coco1 = new Cocktail("la cefor",15,new ArrayList<Boisson>(Arrays.asList(c,s,r)));
        Barman.ajouterCocktailStock(coco1,1);
        Cocktail coco2 = new Cocktail("instant",10,new ArrayList<Boisson>(Arrays.asList(c,s)));
        Barman.ajouterCocktailStock(coco2,1);
        //Barman.chargerMapBoisson("Boisson.txt");
        //Barman.chargerMapCocktail("Cocktail.txt");
        Barman.MENUPRINCIPALE();
        //Barman.sauvegarderMapBoisson("Boisson.txt");
        //Barman.sauvegarderMapCocktail("Cocktail.txt");

    }
}

