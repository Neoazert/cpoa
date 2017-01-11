/***********************************************************************
 * Module:  Salle.java
 * Author:  p1502985
 * Purpose: Defines the Class Salle
 ***********************************************************************/

package gestionProjection;

import java.util.*;

/** @pdOid c4bf6666-d885-410a-81b0-6a31ce8d4f3f */
public class Salle {
   /** @pdOid daada224-8a31-423f-87a1-cab191f886f8 */
   private String nomSalle;

    public Salle(int numeroSalle, String nomSalle) {
        this.nomSalle = nomSalle;
        this.numeroSalle = numeroSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public void setNumeroSalle(int numeroSalle) {
        this.numeroSalle = numeroSalle;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public int getNumeroSalle() {
        return numeroSalle;
    }
   /** @pdOid 0d185158-5023-4c51-86f8-aa09df341224 */
   private int numeroSalle;
 
}