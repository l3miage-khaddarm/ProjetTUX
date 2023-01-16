/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author TOURE ALLASSANE
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class Partie {
    private String date;
    private String mot;
    private int niveau;
    private int trouve;
    private int temps;

    public Partie(String date, String mot, int niveau) {
        this.date = date;
        this.mot = mot;
        this.niveau = niveau;
    }

    public String getMot() {
        return mot;
    }
    public Partie(Element partieElt){
        this.date = partieElt.getElementsByTagName("date").item(0).getNodeValue();
        this.mot  = partieElt.getElementsByTagName("mot").item(1).getNodeValue();
        this.niveau = Integer.parseInt(partieElt.getElementsByTagName("niveau").item(3).getNodeValue()) ;
        
    }
    public Element getPartie(Document doc){
        Element partie = doc.createElement("partie");
        return partie;
    }

    public void setTrouve(int nbLettresRestantes) {
        int nbLettresTrouves;
        nbLettresTrouves = mot.length() - nbLettresRestantes;
        trouve = (100*nbLettresTrouves)/mot.length();
    }

    public int getNiveau() {
        return niveau;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }
    @Override
    public String toString(){
        String message="";
        message +="Voici les information sur votre partie:"+"\n Date:"+date;
        message +="\n Mot :"+mot+"\n Niveau:"+niveau+"\n Temps:"+temps/1000+ "sec";
        message +="\n Trouvé: "+trouve +"%";
        return message;
    }
     
}