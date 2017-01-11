/***********************************************************************
 * Module:  Projection.java
 * Author:  p1502985
 * Purpose: Defines the Class Projection
 ***********************************************************************/

package gestionProjection;

import java.util.*;

/** @pdOid 85e67722-0178-4503-a6c5-17a021e71a53 */
public class Projection {
   /** @pdOid 6462b975-214b-4219-9748-6c7669ccbcd0 */
   private Date date;
   /** @pdOid 88f083d6-5fde-4d18-9777-d4f6c9f4382c */
   private String heure;

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
   
   /** @pdRoleInfo migr=no name=Film assc=association1 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Aggregation */
   public Film film;
   /** @pdRoleInfo migr=no name=Contrainte assc=association3 mult=0..1 side=A */
   public Contrainte contrainte;
   /** @pdRoleInfo migr=no name=Salle assc=association4 mult=1 side=A */
   public Salle salle;

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
   
    public Date getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public Film getFilm() {
        return film;
    }

    public Salle getSalle() {
        return salle;
    }

   
   
   
   
   
  
   

   /** @pdGenerated default parent getter */
   public Contrainte getContrainte() {
      return contrainte;
   }
   
   /** @pdGenerated default parent setter
     * @param newContrainte */
   public void setContrainte(Contrainte newContrainte) {
      if (this.contrainte == null || !this.contrainte.equals(newContrainte))
      {
         if (this.contrainte != null)
         {
            Contrainte oldContrainte = this.contrainte;
            this.contrainte = null;
            oldContrainte.removeProjection(this);
         }
         if (newContrainte != null)
         {
            this.contrainte = newContrainte;
            this.contrainte.addProjection(this);
         }
      }
   }

}