/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import env3d.Env;
import java.io.IOException;
import org.lwjgl.input.Keyboard;
import java.util.ArrayList;
import org.xml.sax.SAXException;


/**
 *
 * @author gladen
 */
public abstract class Jeu {

    enum MENU_VAL {
        MENU_SORTIE, MENU_CONTINUE, MENU_JOUE
    }
    //attributs 
    private final Env env;
    private Tux tux;
    private final Room mainRoom;
    private final Room menuRoom;
    private Letter letter;
    private Profil profil;
    private final Dico dico;
    private final ArrayList<Letter> lettres;
    protected EnvTextMap menuText;   
    //text (affichage des texte du jeu)
    public ArrayList<Letter> getLettres() {
        return lettres;
    }
    //constructeur 
    public Jeu() throws SAXException, IOException {

        // Crée un nouvel environnement
        env = new Env();

        // Instancie une Room
        mainRoom = new Room();

        // Instancie une autre Room pour les menus
        menuRoom = new Room();
        menuRoom.setTextureEast("textures/marble.png");
        menuRoom.setTextureWest("textures/marble.png");
        menuRoom.setTextureNorth("textures/marble.png");
        menuRoom.setTextureBottom("textures/marble.png");

        // Règle la camera
        env.setCameraXYZ(50, 60, 175);
        env.setCameraPitch(-20);

        // Désactive les contrôles par défaut
        env.setDefaultControl(false);

        // Instancie un profil par défaut
        //profil = new Profil("src/xml/profil.xml");
        profil = new Profil();
       
        //Instancie list de lettres
        lettres= new ArrayList<Letter>();
        
        // Dictionnaire
        dico = new Dico("src/xml/dico.xml");
        //dico.lireDictionnaireDOM("src/xml","dico.xml");

        // instancie le menuText
        menuText = new EnvTextMap(env);
        
        // Textes affichés à l'écran
        menuText.addText("Voulez vous ?", "Question", 200, 300);
        menuText.addText("1. Commencer une nouvelle partie ?", "Jeu1", 250, 280);
        menuText.addText("2. Charger une partie existante ?", "Jeu2", 250, 260);
        menuText.addText("3. Sortir de ce jeu ?", "Jeu3", 250, 240);
        menuText.addText("4. Quitter le jeu ?", "Jeu4", 250, 220);
        menuText.addText("Choisissez un nom de joueur : ", "NomJoueur", 200, 300);
        menuText.addText("1. Charger un profil de joueur existant ?", "Principal1", 250, 280);
        menuText.addText("2. Créer un nouveau joueur ?", "Principal2", 250, 260);
        menuText.addText("3. Sortir du jeu ?", "Principal3", 250, 240);
 
    }

    /**
     * Gère le menu principal
     *
     */
    public void execute() {
        //joue(new Partie("2022-11-23","mot",1));

        MENU_VAL mainLoop;
        mainLoop = MENU_VAL.MENU_SORTIE;
        do {
            mainLoop = menuPrincipal();
        } while (mainLoop != MENU_VAL.MENU_SORTIE);
        this.env.setDisplayStr("Au revoir !", 300, 30);
        env.exit();
    }


    // fourni
    private String getNomJoueur() {
        String nomJoueur = "";
        menuText.getText("NomJoueur").display();
        nomJoueur = menuText.getText("NomJoueur").lire(true);
        menuText.getText("NomJoueur").clean();
        return nomJoueur;
    }

    
    // fourni, à compléter
    private MENU_VAL menuJeu() {

        MENU_VAL playTheGame;
        playTheGame = MENU_VAL.MENU_JOUE;
        Partie partie;
        do {
            // restaure la room du menu
            env.setRoom(menuRoom);
            // affiche menu
            menuText.getText("Question").display();
            menuText.getText("Jeu1").display();
            menuText.getText("Jeu2").display();
            menuText.getText("Jeu3").display();
            menuText.getText("Jeu4").display();
            
            // vérifie qu'une touche 1, 2, 3 ou 4 est pressée
            int touche = 0;
            while (!(touche == Keyboard.KEY_1 || touche == Keyboard.KEY_2 || touche == Keyboard.KEY_3 || touche == Keyboard.KEY_4)) {
                touche = env.getKey();
                env.advanceOneFrame();
            }

            // nettoie l'environnement du texte
            menuText.getText("Question").clean();
            menuText.getText("Jeu1").clean();
            menuText.getText("Jeu2").clean();
            menuText.getText("Jeu3").clean();
            menuText.getText("Jeu4").clean();

            // restaure la room du jeu
            env.setRoom(mainRoom);

            // et décide quoi faire en fonction de la touche pressée
            switch (touche) {
                // -----------------------------------------
                // Touche 1 : Commencer une nouvelle partie
                // -----------------------------------------                
                case Keyboard.KEY_1: // choisi un niveau et charge un mot depuis le dico
                    // .......... dico.******
                    // crée un nouvelle partie
                    partie = new Partie("2018-09-7", "test", 1);
                    // joue
                    joue(partie);
                    // enregistre la partie dans le profil --> enregistre le profil
                    //profil.ajouterPartie(partie);
                    playTheGame = MENU_VAL.MENU_JOUE;
                    break;

                // -----------------------------------------
                // Touche 2 : Charger une partie existante
                // -----------------------------------------                
                case Keyboard.KEY_2: // charge une partie existante
                    partie = new Partie("2018-09-7", "test", 1); //XXXXXXXXX
                    // Recupère le mot de la partie existante
                    //String mot2 = partie.getMot();
                    // joue
                    joue(partie);
                    // enregistre la partie dans le profil --> enregistre le profil
                    // .......... profil.******
                    playTheGame = MENU_VAL.MENU_JOUE;
                    break;

                // -----------------------------------------
                // Touche 3 : Sortie de ce jeu
                // -----------------------------------------                
                case Keyboard.KEY_3:
                    playTheGame = MENU_VAL.MENU_CONTINUE;
                    break;

                // -----------------------------------------
                // Touche 4 : Quitter le jeu
                // -----------------------------------------                
                case Keyboard.KEY_4:
                    playTheGame = MENU_VAL.MENU_SORTIE;
            }
        } while (playTheGame == MENU_VAL.MENU_JOUE);
        return playTheGame;
    }

    private MENU_VAL menuPrincipal() {

        MENU_VAL choix = MENU_VAL.MENU_CONTINUE;
        String nomJoueur;

        // restaure la room du menu
        env.setRoom(menuRoom);

        menuText.getText("Question").display();
        menuText.getText("Principal1").display();
        menuText.getText("Principal2").display();
        menuText.getText("Principal3").display();
               
        // vérifie qu'une touche 1, 2 ou 3 est pressée
        int touche = 0;
        while (!(touche == Keyboard.KEY_1 || touche == Keyboard.KEY_2 || touche == Keyboard.KEY_3)) {
            touche = env.getKey();
            env.advanceOneFrame();
        }

        menuText.getText("Question").clean();
        menuText.getText("Principal1").clean();
        menuText.getText("Principal2").clean();
        menuText.getText("Principal3").clean();

        // et décide quoi faire en fonction de la touche pressée
        switch (touche) {
            // -------------------------------------
            // Touche 1 : Charger un profil existant
            // -------------------------------------
            case Keyboard.KEY_1:
                // demande le nom du joueur existant
                nomJoueur = getNomJoueur();
                // charge le profil de ce joueur si possible
//                if (profil.charge(nomJoueur)) {
//                    choix = menuJeu();
//                } else {
//                    choix = MENU_VAL.MENU_SORTIE;//CONTINUE;
//                }
                break;

            // -------------------------------------
            // Touche 2 : Créer un nouveau joueur
            // -------------------------------------
            case Keyboard.KEY_2:
                // demande le nom du nouveau joueur
                nomJoueur = getNomJoueur();
                // crée un profil avec le nom d'un nouveau joueur
                profil = new Profil();
                choix = menuJeu();
                break;

            // -------------------------------------
            // Touche 3 : Sortir du jeu
            // -------------------------------------
            case Keyboard.KEY_3:
                choix = MENU_VAL.MENU_SORTIE;
        }
        return choix;
    }

    public void joue(Partie partie) {

        // Instancie un Tux
        tux = new Tux(env, mainRoom);
        env.addObject(tux);
        letter = new Letter('a', 10, 10);
        for(Letter e :lettres){
            env.addObject(e);
        }
          
    

        // Ici, on peut initialiser des valeurs pour une nouvelle partie
        démarrePartie(partie);

        // Boucle de jeu
        Boolean finished;
        finished = false;
        while (!finished) {

            // Contrôles globaux du jeu (sortie, ...)
            //1 is for escape key
            if (env.getKey() == 1) {
                finished = true;
            }

            // Contrôles des déplacements de Tux (gauche, droite, ...)
            tux.déplace();

            // Ici, on applique les regles
            appliqueRegles(partie);

            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }

        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminePartie(partie);

    }

    protected abstract void démarrePartie(Partie partie);

    protected abstract void appliqueRegles(Partie partie);

    protected abstract void terminePartie(Partie partie);
     //  retourne la distance entre le personnage tux et la lettre qu'il trouve en face de lui.
   protected double distance(Letter letter){
        return Math.sqrt((letter.getX()-tux.getX())*(letter.getX()-tux.getX()) + (letter.getZ()-tux.getZ())*(letter.getZ()-tux.getZ()));
    }                       
   protected boolean collision(Letter letter){
       return (distance(letter)<1);
   }
       

}
