/***********************************************************************
 * Module:  Film.java
 * Author:  p1502985
 * Purpose: Defines the Class Film
 ***********************************************************************/

package gestionProjection;

import java.util.*;

/** @pdOid 5f050592-d5db-45d4-96b3-23a266e46675 */
public class Film {
    
    private int id;
   /** @pdOid f26ec516-150b-4b33-a3f5-7a6f0a3d5349 */
   private String nomFilm;
   /** @pdOid d6d74c12-33d3-47e7-a62e-beacaa350107 */
   private String auteur;
   /** @pdOid 4c391b16-22ce-4824-8fe7-787b2a9b6729 */
   private int duree;
   
   private String concours;
   
   
   public Film(int id, String nomFilm, String concours, String auteur, int duree){
       this.id = id;
       this.nomFilm = nomFilm;
       this.auteur = auteur;
       this.duree = duree;
       this.concours = concours;
   }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomFilm(String nomFilm) {
        this.nomFilm = nomFilm;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setConcours(String concours) {
        this.concours = concours;
    }

    public String getNomFilm() {
        return nomFilm;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getDuree() {
        return duree;
    }

    public String getConcours() {
        return concours;
    }
   
   public int getId(){
       return id;
   }

}