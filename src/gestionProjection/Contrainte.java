/***********************************************************************
 * Module:  Contrainte.java
 * Author:  p1502985
 * Purpose: Defines the Class Contrainte
 ***********************************************************************/

package gestionProjection;

import java.util.*;

/** @pdOid 319ece08-c13b-4bfd-a566-8c2bf8a77628 */
public class Contrainte {
   /** @pdOid 8bfa3aa8-a220-4ae3-93f4-b3aee61c0979 */
   private boolean ok;
   
   /** @pdRoleInfo migr=no name=Projection assc=association3 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Projection> projection;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Projection> getProjection() {
      if (projection == null)
         projection = new java.util.HashSet<Projection>();
      return projection;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorProjection() {
      if (projection == null)
         projection = new java.util.HashSet<Projection>();
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
      if (this.projection == null)
         this.projection = new java.util.HashSet<Projection>();
      if (!this.projection.contains(newProjection))
      {
         this.projection.add(newProjection);
         newProjection.setContrainte(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldProjection */
   public void removeProjection(Projection oldProjection) {
      if (oldProjection == null)
         return;
      if (this.projection != null)
         if (this.projection.contains(oldProjection))
         {
            this.projection.remove(oldProjection);
            oldProjection.setContrainte((Contrainte)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllProjection() {
      if (projection != null)
      {
         Projection oldProjection;
         for (java.util.Iterator iter = getIteratorProjection(); iter.hasNext();)
         {
            oldProjection = (Projection)iter.next();
            iter.remove();
            oldProjection.setContrainte((Contrainte)null);
         }
      }
   }

}