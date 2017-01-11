/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mariadb.jdbc.MariaDbDataSource;

/**
 *
 * @author Mooneswar.Ramburrun
 */

public class ConnexionMariaDB {

@SuppressWarnings("UseSpecificCatch")
    public static Connection creerConnexion() {
       
        try {
            Connection conn = null;

            String userID = "p1502985";
            String pwd = "240956";
            String URL = "jdbc:mariadb://iutdoua-web.univ-lyon1.fr/p1502985";
            conn = (Connection) DriverManager.getConnection(URL, userID, pwd);
            return (conn);
         } catch (SQLException ex) {
            Logger.getLogger(ConnexionMariaDB.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;   
   }
    
}// fin ConnexionOracleFactory
