/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.io.IOException;
import org.xml.sax.SAXException;

/**
 *
 * @author Nour
 */
public class LanceurDeJeu {

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws SAXException, IOException{
       JeuDevineLeMotOrdre jeu;
       jeu = new JeuDevineLeMotOrdre();
       jeu.execute();
    }    
}