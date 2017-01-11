/***********************************************************************
 * Module:  Planning.java
 * Author:  p1502985
 * Purpose: Defines the Class Planning
 ***********************************************************************/

package gestionProjection;

import connexion.ConnexionMariaDB;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** @pdOid 59d7409c-750a-4f84-a23e-aa07f64e071d */
public class Planning {
   /** @pdRoleInfo migr=no name=Projection assc=association5 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public ArrayList<Projection> projection;
   private java.sql.Connection connexion;
   private ArrayList<Film> tabFilms;
   private ArrayList<Salle> tabSalles;
   
   public Planning(){
       
       loadPlanning();
   }
   
  
   
   /** @pdGenerated default getter */
   public ArrayList<Projection> getProjection() {

      return projection;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorProjection() {

      return projection.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newProjection */
   public void setProjection(java.util.Collection<Projection> newProjection) {
      removeAllProjection();
      for (java.util.Iterator iter = newProjection.iterator(); iter.hasNext();)
         addProjection((Projection)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newProjection */
   public void addProjection(Projection newProjection) {
      if (newProjection == null)
         return;

      if (!this.projection.contains(newProjection))
         this.projection.add(newProjection);
   }
   
   /** @pdGenerated default remove
     * @param oldProjection */
   public void removeProjection(Projection oldProjection) {
      if (oldProjection == null)
         return;
      if (this.projection != null)
         if (this.projection.contains(oldProjection))
            this.projection.remove(oldProjection);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllProjection() {
      if (projection != null)
         projection.clear();
   }
   
   
    public void openConnection() {
        connexion = ConnexionMariaDB.creerConnexion();
        if (connexion == null) {
            System.out.println("Probleme de connection.");
            System.exit(1);
        }
        else System.out.println("La connexion a été établie !");
    }
    
    
    
    public void closeConnection() {
        try {
            connexion.close();	// Fermeture de la connexion
        } catch (java.sql.SQLException e) {
            System.out.println("ERREUR ORACLE" + e.getMessage());
        }
    }

    private void loadPlanning() {
       openConnection();
       Projection proj = new Projection();
       tabFilms = new ArrayList<>();
       tabSalles = new ArrayList<>();
       projection =new ArrayList<>(); 
       
       
       boolean continuer = true;
       try {
           java.sql.Statement requete;
           requete = connexion.createStatement();
           java.sql.ResultSet ensresul;
           ensresul = requete.executeQuery(
                   "select * from films");
                      
           while (ensresul.next()){

               tabFilms.add(new Film(ensresul.getInt(1), ensresul.getString(2), ensresul.getString(3), ensresul.getString(4), ensresul.getInt(5))); 
          
           }
           
           
           Iterator<Film> filmIterator = tabFilms.iterator();
           
            ensresul = requete.executeQuery(
                   "select * from salles");
            
           while (ensresul.next()){
               
         
               tabSalles.add(new Salle(ensresul.getInt(1), ensresul.getString(2))); 
          
           }
           Iterator<Salle> salleIterator = tabSalles.iterator();
           
           requete = connexion.createStatement();
           ensresul = requete.executeQuery(
                   "select * from projections");
           
           
           while (ensresul.next()) {
               System.out.println(ensresul.getInt(1));
               int i=0;
               continuer = true;
               
               while(continuer && i<tabFilms.size()){
     
                   if (tabFilms.get(i).getId() == ensresul.getInt(1)){
                       continuer = false;
                       proj.setFilm(tabFilms.get(i));
                   } 
                   i++;
                   
               }
               
               continuer = true;
               while(continuer && i<tabSalles.size()){
                   if (tabSalles.get(i).getNumeroSalle() == ensresul.getInt(4)){
                       continuer = false;
                       proj.setSalle(tabSalles.get(i));
                   }
                   i++;
                   
               }
                                 
               proj.setDate(ensresul.getDate(2));
               proj.setHeure(ensresul.getString(3));
               
               System.out.println(proj);
               
               projection.add(proj);     
               
               proj = new Projection();
               
               
               //System.out.println(date + heure + idSalle);
               
               
           }
           ensresul.close();
           requete.close();
           
       } catch (SQLException ex) {
           Logger.getLogger(Planning.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       closeConnection();
       
  
    }
    
    
    
    public ArrayList<Film> getFilmsDisponible(String concours){
        
        ArrayList<Film> filmsDisponible = new ArrayList();
        
        for (int i =0; i< tabFilms.size();i++){
            if(tabFilms.get(i).getConcours().equals(concours) )
            {
                filmsDisponible.add(tabFilms.get(i));
            }
        }
        
        return filmsDisponible;
    }
    
    
    public ArrayList<Salle> getSalles(){
        return tabSalles;
    }
    /*
    public ArrayList<String> getSalleDisponible(Film film){
        int nbCase = film.getDuree()/30;
        
    }*/
    
    
    

}