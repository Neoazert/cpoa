/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import gestionProjection.Film;
import gestionProjection.Planning;
import gestionProjection.Projection;
import gestionProjection.Salle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1503046
 */
public class AfficherProjection extends javax.swing.JFrame {
    
    Date actualDate ;
    /**
     * Creates new form AfficherProjection
     */
    public AfficherProjection( Planning planning, Film film) {
        initComponents();
        this.planning = planning;
        this.film = film;
        ArrayList<Salle> salles = planning.getSalles();
        
        for(int i=0; i<salles.size(); i++){
            jComboBox1.addItem(salles.get(i).getNomSalle());
        }
        actualDate = getFirstProjDate();
        jLabel1.setText(actualDate.toString());
        
                
        showProj();
        

        
        //jTable1.getModel().setValueAt("******", 0, 0);
        
        
        
    }
    
    public Date getFirstProjDate(){  
        
         SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy/hh");
        try {
            return dateformat3.parse("18/09/2017/01");
        } catch (ParseException ex) {
            Logger.getLogger(AfficherProjection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public Date getLastProjDate(){
        Date d = null;
        
        Date temp = new Date();
        
        temp.setHours(0);
        temp.setMinutes(0);
        temp.setSeconds(0);
        
        if( (planning.getProjection().get(0).getDate()) != null){
            temp.setDate(planning.getProjection().get(0).getDate().getDate());
            temp.setMonth(planning.getProjection().get(0).getDate().getMonth());
            temp.setYear(planning.getProjection().get(0).getDate().getYear());
        }
        d=temp;
        
        for (int i =0;i<planning.getProjection().size();i++){
            if (d.before(planning.getProjection().get(i).getDate())){
                
                d.setDate(planning.getProjection().get(i).getDate().getDate());
                d.setMonth(planning.getProjection().get(i).getDate().getMonth());
                d.setYear(planning.getProjection().get(i).getDate().getYear());
            }
        }
        
        return d;
    }
    
    
    public void showProj(){
        
        Date max =  new Date();
        max.setMonth(actualDate.getMonth());
        max.setYear(actualDate.getYear());
        max.setDate(actualDate.getDate()+6);
        
        
     
        for(int i=1;i<jTable1.getRowCount();i++){
            for(int j=1;j<jTable1.getColumnCount();j++){
                    jTable1.getModel().setValueAt(null, i, j);
                 }
        }
        
      /*  System.out.println("-----------------");
        
        System.out.println(acutalDate);
        System.out.println(planning.getProjection().get(0).getDate().toString());
        System.out.println(max);
        System.out.println(planning.getProjection().get(0).getDate().compareTo(acutalDate) >= 0 && planning.getProjection().get(0).getDate().compareTo(max) <=0);
        System.out.println("-----------------");*/
        
        for(int i=0; i<planning.getProjection().size();i++){
            
            
            
            
            
            
            if(jComboBox1.getSelectedItem()== planning.getProjection().get(i).getSalle().getNomSalle() //sale
                    && planning.getProjection().get(i).getDate().compareTo(actualDate) >= 0 && planning.getProjection().get(i).getDate().compareTo(max) <=0 /*semaine*/ ){
                
                    String str = Character.toString(planning.getProjection().get(i).getHeure().charAt(0)) + Character.toString(planning.getProjection().get(i).getHeure().charAt(1));
                    int heure = Integer.parseInt(str); 
                    
                    str = Character.toString(planning.getProjection().get(i).getHeure().charAt(3)) + Character.toString(planning.getProjection().get(i).getHeure().charAt(4));
                    int minutes = Integer.parseInt(str); 
                    
                    int indice_debut = (heure -8)*2;
                    if(minutes == 30)
                         indice_debut++;
                        
                    
                    //System.out.println(planning.getProjection().get(i).getFilm().getNomFilm() + " : " + minutes);
                    
                    int a = planning.getProjection().get(i).getDate().getDay();
                    if(a==0)
                        a=7;
                    
                    System.out.println(a);
                    //System.out.println(planning.getProjection().get(i).getDate().getDay());
                    
                    int nbCase = planning.getProjection().get(i).getFilm().getDuree()/30;
                    
                    if(planning.getProjection().get(i).getFilm().getDuree()%30 != 0){
                        nbCase++;
                    }
                        
                    
                    
                    jTable1.getModel().setValueAt(planning.getProjection().get(i).getFilm().getNomFilm(), indice_debut, a);
                    for(int j=0;j<nbCase; j++){
                        jTable1.getModel().setValueAt(planning.getProjection().get(i).getFilm().getNomFilm(), indice_debut+j, a);
                    }
                
            }
            
        }
        
        if(film != null)
           caseDispo();
    }
    
    
private void caseDispo(){
                
        int nbCase = film.getDuree()/30;
        if(film.getDuree()%30 != 0) 
            nbCase ++;
        
        //System.out.println(film.getDuree() + "          " + nbCase);
        
        
        for(int i=1; i<jTable1.getColumnCount(); i++){
            for(int j=1; j<jTable1.getRowCount(); j++){
                
                if(jTable1.getModel().getValueAt(j, i) == null){
                    
                    if(j+nbCase > jTable1.getRowCount()){
                        for(int a = j; a < jTable1.getRowCount(); a++){
                            jTable1.getModel().setValueAt("************", a, i);
                        }
                        j=jTable1.getRowCount()-1;                   
                    }
                    
                    else{
                        
                        int a = j+1;
                        boolean continuer = true;
                        
                        while(a < j+nbCase && continuer){
                            if(jTable1.getModel().getValueAt(a, i) != null)
                                continuer = false;
                            a++;
                        }
                        
                        if (continuer == false){
                            for(int b = j; b<a-1; b++){
                                 jTable1.getModel().setValueAt("************", b, i);
                            }
                            j = a-1;
                        }
                        
                    }
                }
                
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"8h00", null, null, null, null, null, null, null},
                {"", null, null, null, null, null, null, null},
                {"9h00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"10h00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"11h", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"12h", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"13h", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"14h", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"15h", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"16h", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"17h", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"18h", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"19h", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"20h", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"21h", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"22h", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"
            }
        ){public boolean isCellEditable(int row, int column){return false;}}
    );
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable1MouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            jTable1MouseEntered(evt);
        }
    });
    jScrollPane2.setViewportView(jTable1);

    jComboBox1.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jComboBox1ItemStateChanged(evt);
        }
    });
    jComboBox1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox1ActionPerformed(evt);
        }
    });

    jButton2.setText("<<");
    jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton2MouseClicked(evt);
        }
    });
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    jButton3.setText(">>");
    jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton3MouseClicked(evt);
        }
    });

    jLabel1.setText("-");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 961, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(53, 53, 53)
                    .addComponent(jButton2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButton3)
                    .addGap(61, 61, 61)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(74, 74, 74))))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton1)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton2)
                .addComponent(jButton3)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(40, 40, 40))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if(actualDate != null)
            showProj();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
            actualDate.setDate(actualDate.getDate()-7);
            
            jLabel1.setText(actualDate.toString());
            showProj();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked

        
            actualDate.setDate(actualDate.getDate()+7);
            
            jLabel1.setText(actualDate.toString());
            showProj();
            
    }//GEN-LAST:event_jButton3MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if(film != null && jTable1.getModel().getValueAt(jTable1.getSelectedRow(),jTable1.getSelectedColumn()) == null)
        {    
            int h = (8+(jTable1.getSelectedRow())/2);
            String heureDebut = "";
            if (h < 10)
                heureDebut += "0";
            
            heureDebut += Integer.toString(h) + "h";

            if(jTable1.getSelectedRow()%2 != 0)
                heureDebut += "30";
            else
                heureDebut += "00";
            
            Date date = new Date(actualDate.getYear(), actualDate.getMonth(), actualDate.getDate(), actualDate.getHours(), actualDate.getMinutes(), actualDate.getSeconds());
            date.setDate(date.getDate()+jTable1.getSelectedColumn()-1);
            
            //System.out.println(date.getDay());
            
            
            planning.addProjection(new Projection(date, heureDebut, film, planning.getSalleNamed(jComboBox1.getSelectedItem().toString())));
               
            showProj();       
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(Planning planning, Film film) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AfficherProjection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AfficherProjection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AfficherProjection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AfficherProjection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AfficherProjection(planning, film).setVisible(true);
            }
        });
    }
    
    private Film film;
    private Planning planning;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}




/*
3 films en compétition / jours
films dnas les dates du festival
2 films de la compétition officielle différent projeté 2 fois / jours
certaines compétitions dans certaines salles uniquement
*/