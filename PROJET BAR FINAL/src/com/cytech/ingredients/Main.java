package com.cytech.ingredients;

import org.json.simple.parser.ParseException;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;
import java.util.HashMap;
import java.nio.*;


public class Main {

    public static void main(String[] args) throws IOException, ParseException{

        /*Boisson r = new boissonNonAlcoolisee("Cola",72,5,5.0);
        Barman.ajouterBoissonStock(r,10);
        Boisson c = new boissonAlcoolisee("Vodka",100,5,12.5);
        Barman.ajouterBoissonStock(c,2);
        Boisson s = new boissonAlcoolisee("Bière",250,5,5);
        Barman.ajouterBoissonStock(s,5);
        Cocktail coco1 = new Cocktail("la cefor",15,new ArrayList<Boisson>(Arrays.asList(c,s,r)));
        Barman.ajouterCocktailStock(coco1);
        Cocktail coco2 = new Cocktail("instant",10,new ArrayList<Boisson>(Arrays.asList(c,s)));
        Barman.ajouterCocktailStock(coco2);*/
        //Barman.chargerMapBoisson("Boisson.txt");
        //Barman.chargerMapCocktail("Cocktail.txt");*/
        Barman.initBoissonsJSON();
        Barman.initCocktailsJSON();
        Barman.MENUPRINCIPALE();
        //Barman.sauvegarderMapBoisson("Boisson.txt");
        //Barman.sauvegarderMapCocktail("Cocktail.txt");

    }
}

