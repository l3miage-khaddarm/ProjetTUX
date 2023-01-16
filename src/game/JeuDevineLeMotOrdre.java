/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import env3d.Env;
import java.io.IOException;
import org.xml.sax.SAXException;

/**
 *
 * @author admin
 */
public class JeuDevineLeMotOrdre extends Jeu {
    //Attributs 
    private int nbLettresRestantes;
    private Chronometre chrono;
    
    public JeuDevineLeMotOrdre() throws SAXException, IOException{
        super();
      
    }

    @Override
    protected void démarrePartie(Partie partie) {
        
    }

    @Override
    protected void appliqueRegles(Partie partie) {
    }

    @Override
    protected void terminePartie(Partie partie){
        
    }
   private Boolean tuxTrouveLettre(){  
       boolean find=false;
       if (collision(getLettres().get(0))){
           find=true;
       }
       return find;
   }
    private int getNbLettresRestantes(){
        return nbLettresRestantes;
    }
    private int getTemps(){
        return 0;
    }
    
}
