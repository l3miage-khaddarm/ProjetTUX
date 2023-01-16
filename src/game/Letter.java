/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import env3d.advanced.EnvNode;
import env3d.Env;

/**
 *
 * @author Nour
 */
public class Letter extends EnvNode {
    private char letter;
    public Letter(char l ,double x,double y){
       letter = l;
       setScale(4.0);
       setX(x);// positionnement au milieu de la largeur de la room
       setY(getScale() * 1.1); // positionnement en hauteur basé sur la taille de Tux
       setZ(y); // positionnement au milieu de la profondeur de la room
       String lettre;
       if(letter==' '){
           lettre="cube.png";
       }else{
           lettre=l+".png";
       }
       setTexture("models/letter/"+l+".png");
        //setTexture("models/letter/cube.png");
       setModel("models/letter/cube.obj");
       
   }
    
    
}
