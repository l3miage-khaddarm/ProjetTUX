/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import env3d.Env;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Nour
 */
public class Dico {
    //attributs 
    private ArrayList<String> listeNiveau1;
    private ArrayList<String> listeNiveau2;
    private ArrayList<String> listeNiveau3;
    private ArrayList<String> listeNiveau4;
    private ArrayList<String> listeNiveau5;
    private String chemienFichierDico;
    
    
    //constructeur 
    public Dico(String cheminFichierDico){
        this.chemienFichierDico=cheminFichierDico;
        listeNiveau1 = new ArrayList<String>();
        listeNiveau2 = new ArrayList<String>();
        listeNiveau3 = new ArrayList<String>();
        listeNiveau4 = new ArrayList<String>();
        listeNiveau5 = new ArrayList<String>();
      
    }
    
    //méthode getMotDepuisListeNiveaux
    public String getMotDepuisListeNiveaux(int niveau){
        String mot="";
        switch (vérifieNiveau(niveau)) {
            case 1:
                mot=getMotDepuisListe(listeNiveau1);
                break;
            case 2:
                mot=getMotDepuisListe(listeNiveau2);
                break;
            case 3:
                mot=getMotDepuisListe(listeNiveau3);
                break;
            case 4:
                mot=getMotDepuisListe(listeNiveau4);
                break;
            case 5:
                mot=getMotDepuisListe(listeNiveau5);
                break;
                
        }
        return mot;
    }
    //méthode ajouteMotDico
    public void ajouteMotDico(int niveau,String mot){
         switch (vérifieNiveau(niveau)) {
            case 1:
                listeNiveau1.add(mot);
                break;
            case 2:
                 listeNiveau2.add(mot);
                break;
            case 3:
                 listeNiveau3.add(mot);
                break;
            case 4:
                 listeNiveau4.add(mot);
                break;
            case 5:
                 listeNiveau5.add(mot);
                break;
            default:
                break;
         }
    }
    //méthode getCheminFichieDico
    public String getCheminFichieDico(){
        return chemienFichierDico;
    }
    //méthode lireDictionnaireDom qui charge les mots du dictionnaire dans la liste de mots 
//    public void lireDictionnaireDOM(String path,String filename) throws SAXException , IOException{
//        DOMParser parser = new DOMParser();
//        parser.parse(path + "/" +filename);
//        Document doc = parser.getDocument();
//        NodeList listedesmots;
//        listedesmots = doc.getElementsByTagName("tux:mot");
//        for(int i=0;i<listedesmots.getLength()-1;i++){
//            Element mot= (Element) listedesmots.item(i);
//            
//            int niveauMot =Integer.parseInt(mot.getAttribute("niveau"));
//            
//            String LettresMot = mot.getTextContent();
//            ajouteMotDico(niveauMot,LettresMot);
//        }
//    
//    }
    //méthode vérifieNiveau
    private int vérifieNiveau(int niveau){
        int niveauR;
        if(niveau>=1 && niveau<=5){
            niveauR=niveau;
        }else{
            niveauR=1;
        }
        return niveauR;
    }
    //méthode getMotDepuisListe
    private String getMotDepuisListe(ArrayList<String> list){
        int random=(int)(Math.random() * (list.size()-1));
        return list.get(random);
    }
}
