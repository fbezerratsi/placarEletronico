
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Felipe Bezerra
 */
public class Tempo extends javax.swing.JFrame {

    private javax.swing.Timer timer;  
    private int segundos = 0;
    private int minutos = 01;
    private int velocidade = 1000;
    
    /**
     * Creates new form Tempo
     */
    public Tempo() {
        initComponents();
        this.setLocationRelativeTo(null);
        iniciarCintagem();//Aqui inicia a contagem
        stopTime(); // Aqui para o tempo para que o nosso cronômetro inicie parado
        timer.start();
    }

    
    private void iniciarCintagem() {
        ActionListener action = new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
                
                if (segundos == 0) {
                    minutos--;
                    segundos = 59;
                } else {
                    segundos--;
                }
                if (minutos == 0 && segundos == 0) {
                    timer.stop();
//                    try {
//                        apitar();
//                    } catch (IOException ex) {
//                        Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    //JOptionPane.showMessageDialog(rootPane, "Tempo Terminado");
                    fecharAplicação();
                    
                }
                String min = minutos <= 9? "0"+minutos:minutos+"";
                String seg = segundos <= 9? "0"+segundos:segundos+"";
                
                txtCronometroTempo.setText(min+":"+seg);
            }  
        };  
        this.timer = new javax.swing.Timer(velocidade, action);  
        this.timer.start();
    }
    
    public void fecharAplicação() {
        this.setVisible(false);
        this.dispose();
    }
    
    public void apitar() throws IOException {
        // STRING COM O CAMINHO DO ARQUIVO MP3 A SER TOCADO
        String path = new File("../src/som/termino_tempo.mp3").getCanonicalPath();
		//String path = "C:\\Users\\Felipe Bezerra\\Documents\\NetBeansProjects\\placar\\src\\som\\apitodefutebol.mp3";

        // INSTANCIAÇÃO DO OBJETO FILE COM O ARQUIVO MP3
        File mp3File = new File(path);

        // INSTANCIAÇÃO DO OBJETO MP3MUSICA DA CLASS INTERNA
        MP3Musica musica = new MP3Musica();
        musica.tocar(mp3File);

        // CHAMA O METODO QUE TOCA A MUSICA
        musica.start();
    }
    
    private void stopTime() {
        timer.stop();

        if (this.minutos == 0 && this.segundos == 0) {
            txtCronometroTempo.setText("0"+this.minutos + ":0" + this.segundos);
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

        jPanel1 = new javax.swing.JPanel();
        txtCronometroTempo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

        txtCronometroTempo.setFont(new java.awt.Font("Tahoma", 1, 90)); // NOI18N
        txtCronometroTempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCronometroTempo.setText("01:00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCronometroTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtCronometroTempo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jButton1.setText("Pause");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jButton2.setText("Fechar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        cancelarTempo();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        timer.stop();
    }//GEN-LAST:event_jButton1ActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Tempo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Tempo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Tempo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Tempo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Tempo().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtCronometroTempo;
    // End of variables declaration//GEN-END:variables

    public void cancelarTempo() {
        timer.stop();
        this.minutos = 0;
        this.segundos = 0;
//        try {
//            apitar();
//        } catch (IOException ex) {
//            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
//        }
        this.setVisible(false);
        this.dispose();
    }
}
