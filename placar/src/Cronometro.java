
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sun.rmi.runtime.Log;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Felipe Bezerra
 */
public class Cronometro extends javax.swing.JFrame {

    private javax.swing.Timer timer;  
    private int segundos = 0;
    private int minutos = 20;
    private int velocidade = 1000;
    private boolean statusCronometro = false;
    private int falta1 = 0;
    private int falta2 = 0;
    private int placar1 = 0;
    private int placar2 = 0;
    private int _tempo = 1;

    
    int largura = 0;
    int altura = 0;
    
    /**
     * Creates new form NewJFrame
     */
    public Cronometro() throws SocketException {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.BLACK); 
        iniciarCintagem();//Aqui inicia a contagem
        stopTime(); // Aqui para o tempo para que o nosso cronômetro inicie parado
        
        txtIp.setText(getLocalAddress().toString());
        //txtIp.setText(Inet4Address.getLocalHost().getAddress());
    }
    
    
    //Mostrar IP no canto inferior esquerdo...
    public static InetAddress getLocalAddress() throws SocketException
  {
    Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
    while( ifaces.hasMoreElements() )
    {
      NetworkInterface iface = ifaces.nextElement();
      Enumeration<InetAddress> addresses = iface.getInetAddresses();

      while( addresses.hasMoreElements() )
      {
        InetAddress addr = addresses.nextElement();
        if( addr instanceof Inet4Address && !addr.isLoopbackAddress() )
        {
          return addr;
        }
      }
    }

    return null;
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
                    try {
                        apitar();
                    } catch (IOException ex) {
                        Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //JOptionPane.showMessageDialog(rootPane, "Tempo Terminado");
                    
                    if (_tempo == 1) {
                        txtTempo.setText("2 st");
                        statusCronometro = false;
                        minutos = 20;
                        segundos = 0;
                        txtCronometro.setText("20:00");
                        txtFalta1.setText("Faltas");
                        txtFalta2.setText("Faltas");
                        txtTempo1.setText("Tempo");
                        txtTempo2.setText("Tempo");
                        falta1 = 0;
                        falta2 = 0;
                        _tempo = 2;
                    } else {
                        //txtTempo.setText("1 st");
                        statusCronometro = false;
                        minutos = 20;
                        segundos = 0;
                        txtCronometro.setText("20:00");
                        txtFalta1.setText("Faltas");
                        txtFalta2.setText("Faltas");
                        txtTempo1.setText("Tempo");
                        txtTempo2.setText("Tempo");
                        falta1 = 0;
                        falta2 = 0;
                        _tempo = 1;
                    }
                }
                
                String min = minutos <= 9? "0"+minutos:minutos+"";
                String seg = segundos <= 9? "0"+segundos:segundos+"";
                
                txtCronometro.setText(min+":"+seg);  
            }  
        };  
        this.timer = new javax.swing.Timer(velocidade, action);  
        this.timer.start();
    }

    public void apitar() throws IOException {
        // STRING COM O CAMINHO DO ARQUIVO MP3 A SER TOCADO
        File path = new File("../src/som/termino_jogo.mp3");
		//String path = "C:\\Users\\Felipe Bezerra\\Documents\\NetBeansProjects\\placar\\src\\som\\apitodefutebol.mp3";

        // INSTANCIAÇÃO DO OBJETO FILE COM O ARQUIVO MP3
        File mp3File = new File(path.getAbsolutePath());

        // INSTANCIAÇÃO DO OBJETO MP3MUSICA DA CLASS INTERNA
        MP3Musica musica = new MP3Musica();
        musica.tocar(mp3File);

        // CHAMA O METODO QUE TOCA A MUSICA
        musica.start();
    }
    
    public void beep() throws IOException {
        // STRING COM O CAMINHO DO ARQUIVO MP3 A SER TOCADO
        File path = new File("../src/som/beep-23.mp3");
		//String path = "C:\\Users\\Felipe Bezerra\\Documents\\NetBeansProjects\\placar\\src\\som\\apitodefutebol.mp3";

        // INSTANCIAÇÃO DO OBJETO FILE COM O ARQUIVO MP3
        File mp3File = new File(path.getAbsolutePath());

        // INSTANCIAÇÃO DO OBJETO MP3MUSICA DA CLASS INTERNA
        MP3Musica musica = new MP3Musica();
        musica.tocar(mp3File);

        // CHAMA O METODO QUE TOCA A MUSICA
        musica.start();
    }
    
    public void beepGol() throws IOException {
        // STRING COM O CAMINHO DO ARQUIVO MP3 A SER TOCADO
        File path = new File("../src/som/beep-22.mp3");
		//String path = "C:\\Users\\Felipe Bezerra\\Documents\\NetBeansProjects\\placar\\src\\som\\apitodefutebol.mp3";

        // INSTANCIAÇÃO DO OBJETO FILE COM O ARQUIVO MP3
        File mp3File = new File(path.getAbsolutePath());

        // INSTANCIAÇÃO DO OBJETO MP3MUSICA DA CLASS INTERNA
        MP3Musica musica = new MP3Musica();
        musica.tocar(mp3File);

        // CHAMA O METODO QUE TOCA A MUSICA
        musica.start();
    }
    
    public void apitarBeep() throws IOException {
        // STRING COM O CAMINHO DO ARQUIVO MP3 A SER TOCADO
        
        //String path = new File("src/som/07.mp3").getCanonicalPath();
        File path = new File("../src/som/07.mp3");
		//String path = "C:\\Users\\Felipe Bezerra\\Documents\\NetBeansProjects\\placar\\src\\som\\apitodefutebol.mp3";

        // INSTANCIAÇÃO DO OBJETO FILE COM O ARQUIVO MP3
        File mp3File = new File(path.getAbsolutePath());

        // INSTANCIAÇÃO DO OBJETO MP3MUSICA DA CLASS INTERNA
        MP3Musica musica = new MP3Musica();
        musica.tocar(mp3File);

        // CHAMA O METODO QUE TOCA A MUSICA
        musica.start();
    }
    
    private void stopTime() {
        timer.stop();

        if (this.minutos == 0 && this.segundos == 0) {
            txtCronometro.setText("0"+this.minutos + ":0" + this.segundos);
        }
        
    }

    public void fechar() {
        this.dispose();
    }
    public void destruir() {
        this.dispose();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCronometro = new javax.swing.JLabel();
        txtDireitoMenos = new javax.swing.JLabel();
        txtDireitoMais = new javax.swing.JLabel();
        txtEsquerdoMais = new javax.swing.JLabel();
        txtEsquerdoMenos = new javax.swing.JLabel();
        txtTime2 = new javax.swing.JLabel();
        txtTime1 = new javax.swing.JLabel();
        txtFalta2 = new javax.swing.JLabel();
        txtFalta1 = new javax.swing.JLabel();
        txtTempo1 = new javax.swing.JLabel();
        txtTempo2 = new javax.swing.JLabel();
        txtTempo = new javax.swing.JLabel();
        txtPlacarEsquerdoMais = new javax.swing.JLabel();
        txtPlacarEsquerdoMenos = new javax.swing.JLabel();
        txtPlacarDireitoMais = new javax.swing.JLabel();
        txtPlacarDireitoMenos = new javax.swing.JLabel();
        txtPlacar2 = new javax.swing.JLabel();
        txtPlacar1 = new javax.swing.JLabel();
        txtIp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setModalExclusionType(null);
        setUndecorated(true);

        txtCronometro.setFont(new java.awt.Font("Tahoma", 1, 240)); // NOI18N
        txtCronometro.setForeground(new java.awt.Color(255, 0, 0));
        txtCronometro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCronometro.setText("20:00");
        txtCronometro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCronometroMouseClicked(evt);
            }
        });

        txtDireitoMenos.setFont(new java.awt.Font("Tahoma", 0, 60)); // NOI18N
        txtDireitoMenos.setForeground(new java.awt.Color(255, 0, 0));
        txtDireitoMenos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDireitoMenos.setText("-");
        txtDireitoMenos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        txtDireitoMenos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDireitoMenosMouseClicked(evt);
            }
        });

        txtDireitoMais.setFont(new java.awt.Font("Tahoma", 0, 60)); // NOI18N
        txtDireitoMais.setForeground(new java.awt.Color(255, 0, 0));
        txtDireitoMais.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDireitoMais.setText("+");
        txtDireitoMais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        txtDireitoMais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDireitoMaisMouseClicked(evt);
            }
        });

        txtEsquerdoMais.setFont(new java.awt.Font("Tahoma", 0, 60)); // NOI18N
        txtEsquerdoMais.setForeground(new java.awt.Color(255, 0, 0));
        txtEsquerdoMais.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtEsquerdoMais.setText("+");
        txtEsquerdoMais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        txtEsquerdoMais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEsquerdoMaisMouseClicked(evt);
            }
        });

        txtEsquerdoMenos.setFont(new java.awt.Font("Tahoma", 0, 60)); // NOI18N
        txtEsquerdoMenos.setForeground(new java.awt.Color(255, 0, 0));
        txtEsquerdoMenos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtEsquerdoMenos.setText("-");
        txtEsquerdoMenos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        txtEsquerdoMenos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEsquerdoMenosMouseClicked(evt);
            }
        });

        txtTime2.setFont(new java.awt.Font("Tahoma", 1, 50)); // NOI18N
        txtTime2.setForeground(new java.awt.Color(255, 255, 255));
        txtTime2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTime2.setText("Equipe 02");
        txtTime2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        txtTime2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTime2MouseClicked(evt);
            }
        });

        txtTime1.setFont(new java.awt.Font("Tahoma", 1, 50)); // NOI18N
        txtTime1.setForeground(new java.awt.Color(255, 255, 255));
        txtTime1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTime1.setText("Equipe 01");
        txtTime1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        txtTime1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTime1MouseClicked(evt);
            }
        });

        txtFalta2.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        txtFalta2.setForeground(new java.awt.Color(255, 255, 0));
        txtFalta2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFalta2.setText("Faltas");
        txtFalta2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        txtFalta2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFalta2MouseClicked(evt);
            }
        });

        txtFalta1.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        txtFalta1.setForeground(new java.awt.Color(255, 255, 0));
        txtFalta1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFalta1.setText("Faltas");
        txtFalta1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        txtFalta1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFalta1MouseClicked(evt);
            }
        });

        txtTempo1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        txtTempo1.setForeground(new java.awt.Color(255, 255, 0));
        txtTempo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTempo1.setText("Tempo");
        txtTempo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        txtTempo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTempo1MouseClicked(evt);
            }
        });

        txtTempo2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        txtTempo2.setForeground(new java.awt.Color(255, 255, 0));
        txtTempo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTempo2.setText("Tempo");
        txtTempo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        txtTempo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTempo2MouseClicked(evt);
            }
        });

        txtTempo.setFont(new java.awt.Font("Arial", 1, 50)); // NOI18N
        txtTempo.setForeground(new java.awt.Color(255, 0, 0));
        txtTempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTempo.setText("1 st");

        txtPlacarEsquerdoMais.setFont(new java.awt.Font("Tahoma", 1, 50)); // NOI18N
        txtPlacarEsquerdoMais.setForeground(new java.awt.Color(255, 255, 0));
        txtPlacarEsquerdoMais.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPlacarEsquerdoMais.setText("+");
        txtPlacarEsquerdoMais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        txtPlacarEsquerdoMais.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                txtPlacarEsquerdoMaisMouseDragged(evt);
            }
        });
        txtPlacarEsquerdoMais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPlacarEsquerdoMaisMouseClicked(evt);
            }
        });

        txtPlacarEsquerdoMenos.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        txtPlacarEsquerdoMenos.setForeground(new java.awt.Color(255, 255, 0));
        txtPlacarEsquerdoMenos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPlacarEsquerdoMenos.setText("-");
        txtPlacarEsquerdoMenos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        txtPlacarEsquerdoMenos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPlacarEsquerdoMenosMouseClicked(evt);
            }
        });

        txtPlacarDireitoMais.setFont(new java.awt.Font("Tahoma", 1, 50)); // NOI18N
        txtPlacarDireitoMais.setForeground(new java.awt.Color(255, 255, 0));
        txtPlacarDireitoMais.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPlacarDireitoMais.setText("+");
        txtPlacarDireitoMais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        txtPlacarDireitoMais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPlacarDireitoMaisMouseClicked(evt);
            }
        });

        txtPlacarDireitoMenos.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        txtPlacarDireitoMenos.setForeground(new java.awt.Color(255, 255, 0));
        txtPlacarDireitoMenos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPlacarDireitoMenos.setText("-");
        txtPlacarDireitoMenos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        txtPlacarDireitoMenos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPlacarDireitoMenosMouseClicked(evt);
            }
        });

        txtPlacar2.setBackground(new java.awt.Color(0, 0, 0));
        txtPlacar2.setFont(new java.awt.Font("Tahoma", 1, 250)); // NOI18N
        txtPlacar2.setForeground(new java.awt.Color(255, 255, 0));
        txtPlacar2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPlacar2.setText("00");

        txtPlacar1.setBackground(new java.awt.Color(0, 0, 0));
        txtPlacar1.setFont(new java.awt.Font("Tahoma", 1, 250)); // NOI18N
        txtPlacar1.setForeground(new java.awt.Color(255, 255, 0));
        txtPlacar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPlacar1.setText("00");

        txtIp.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEsquerdoMais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEsquerdoMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCronometro, javax.swing.GroupLayout.DEFAULT_SIZE, 1319, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDireitoMais, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireitoMenos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFalta1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTempo1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTempo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTempo2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFalta2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPlacarEsquerdoMais, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPlacarEsquerdoMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtPlacar1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPlacar2, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPlacarDireitoMais, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPlacarDireitoMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtIp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtDireitoMais, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireitoMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtEsquerdoMais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEsquerdoMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFalta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTempo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTempo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFalta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPlacarDireitoMais, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPlacarDireitoMenos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtPlacar2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtPlacarEsquerdoMais, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPlacarEsquerdoMenos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtPlacar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(txtIp))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCronometroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCronometroMouseClicked
        funcaoCronometro();
    }//GEN-LAST:event_txtCronometroMouseClicked

    private void txtEsquerdoMaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEsquerdoMaisMouseClicked
        try {
            beep();
        } catch (IOException ex) {
            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
        }
        String txtCron = txtCronometro.getText();
        
        String[] array = txtCron.split(":");
        this.minutos = Integer.parseInt(array[0]);
        this.segundos = Integer.parseInt(array[1]);
        this.minutos++;
        if (this.minutos <= 9) {
            if (this.segundos <= 9) {
                txtCronometro.setText("0" + this.minutos + ":0" + this.segundos);
            } else {
                txtCronometro.setText("0" + this.minutos + ":" + this.segundos);
            }
        } else {
            if (this.segundos <= 9) {
                txtCronometro.setText(this.minutos + ":0" + this.segundos);
            } else {
                txtCronometro.setText(this.minutos + ":" + this.segundos);
            }
        }
        
    }//GEN-LAST:event_txtEsquerdoMaisMouseClicked

    private void txtEsquerdoMenosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEsquerdoMenosMouseClicked
        try {
            beep();
        } catch (IOException ex) {
            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
        }
        String txtCron = txtCronometro.getText();
        
        String[] array = txtCron.split(":");
        this.minutos = Integer.parseInt(array[0]);
        this.segundos = Integer.parseInt(array[1]);
        this.minutos--;
        if (this.minutos < 0) {
            this.minutos = 0;
        }
        if (this.minutos <= 9) {
            if (this.segundos <= 9) {
                txtCronometro.setText("0" + this.minutos + ":0" + this.segundos);
            } else {
                txtCronometro.setText("0" + this.minutos + ":" + this.segundos);
            }
        } else {
            if (this.segundos <= 9) {
                txtCronometro.setText(this.minutos + ":0" + this.segundos);
            } else {
                txtCronometro.setText(this.minutos + ":" + this.segundos);
            }
        }
        
    }//GEN-LAST:event_txtEsquerdoMenosMouseClicked

    private void txtDireitoMaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDireitoMaisMouseClicked
        try {
            beep();
        } catch (IOException ex) {
            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
        }
        String txtCron = txtCronometro.getText();
        
        String[] array = txtCron.split(":");
        this.minutos = Integer.parseInt(array[0]);
        this.segundos = Integer.parseInt(array[1]);
        this.segundos++;
        
        if (this.minutos <= 9) {
            if (this.segundos <= 9) {
                txtCronometro.setText("0" + this.minutos + ":0" + this.segundos);
            } else {
                txtCronometro.setText("0" + this.minutos + ":" + this.segundos);
            }
        } else {
            if (this.segundos <= 9) {
                txtCronometro.setText(this.minutos + ":0" + this.segundos);
            } else {
                txtCronometro.setText(this.minutos + ":" + this.segundos);
            }
        }
        
    }//GEN-LAST:event_txtDireitoMaisMouseClicked

    private void txtDireitoMenosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDireitoMenosMouseClicked
        try {
            beep();
        } catch (IOException ex) {
            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
        }
        String txtCron = txtCronometro.getText();
        
        String[] array = txtCron.split(":");
        this.minutos = Integer.parseInt(array[0]);
        this.segundos = Integer.parseInt(array[1]);
        this.segundos--;
        if (this.segundos < 0) {
            this.segundos = 0;
        }
        if (this.minutos <= 9) {
            if (this.segundos <= 9) {
                txtCronometro.setText("0" + this.minutos + ":0" + this.segundos);
            } else {
                txtCronometro.setText("0" + this.minutos + ":" + this.segundos);
            }
        } else {
            if (this.segundos <= 9) {
                txtCronometro.setText(this.minutos + ":0" + this.segundos);
            } else {
                txtCronometro.setText(this.minutos + ":" + this.segundos);
            }
        }
        
    }//GEN-LAST:event_txtDireitoMenosMouseClicked

    private void txtTime1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTime1MouseClicked
        
        modificarEquipe1(1, "");
        
    }//GEN-LAST:event_txtTime1MouseClicked

    private void txtTime2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTime2MouseClicked
        
        modificarEquipe2(1, "");
        
    }//GEN-LAST:event_txtTime2MouseClicked

    private void txtFalta1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFalta1MouseClicked
        
        faltaEquipe1();
        
    }//GEN-LAST:event_txtFalta1MouseClicked

    private void txtFalta2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFalta2MouseClicked
        faltaEquipe2();
    }//GEN-LAST:event_txtFalta2MouseClicked

    private void txtTempo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTempo1MouseClicked
        
        tempoEquipe1();
        
    }//GEN-LAST:event_txtTempo1MouseClicked

    private void txtTempo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTempo2MouseClicked
        
        tempoEquipe2();
        
    }//GEN-LAST:event_txtTempo2MouseClicked

    private void txtPlacarEsquerdoMaisMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPlacarEsquerdoMaisMouseDragged
 
    }//GEN-LAST:event_txtPlacarEsquerdoMaisMouseDragged

    private void txtPlacarDireitoMaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPlacarDireitoMaisMouseClicked
        
        golEquipe2();
        
    }//GEN-LAST:event_txtPlacarDireitoMaisMouseClicked

    private void txtPlacarEsquerdoMaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPlacarEsquerdoMaisMouseClicked
        
        golEquipe1();
        
    }//GEN-LAST:event_txtPlacarEsquerdoMaisMouseClicked

    private void txtPlacarEsquerdoMenosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPlacarEsquerdoMenosMouseClicked
        try {
            beep();
        } catch (IOException ex) {
            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.placar1--;
        if (this.placar1 < 0) {
            this.placar1 = 0;
        } else {
            if (this.placar1 <= 9) {
                txtPlacar1.setText("0" +Integer.toString(this.placar1));
            } else {
                txtPlacar1.setText(Integer.toString(this.placar1));
            }
        }
        
    }//GEN-LAST:event_txtPlacarEsquerdoMenosMouseClicked

    private void txtPlacarDireitoMenosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPlacarDireitoMenosMouseClicked
        try {
            beep();
        } catch (IOException ex) {
            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.placar2--;
        if (this.placar2 < 0) {
            this.placar2 = 0;
        } else {
            if (this.placar2 <= 9) {
                txtPlacar2.setText("0" +Integer.toString(this.placar2));
            } else {
                txtPlacar2.setText(Integer.toString(this.placar2));
            }
        }
        
    }//GEN-LAST:event_txtPlacarDireitoMenosMouseClicked

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
//            java.util.logging.Logger.getLogger(Cronometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Cronometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Cronometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Cronometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Cronometro().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel txtCronometro;
    private javax.swing.JLabel txtDireitoMais;
    private javax.swing.JLabel txtDireitoMenos;
    private javax.swing.JLabel txtEsquerdoMais;
    private javax.swing.JLabel txtEsquerdoMenos;
    private javax.swing.JLabel txtFalta1;
    private javax.swing.JLabel txtFalta2;
    private javax.swing.JLabel txtIp;
    private javax.swing.JLabel txtPlacar1;
    private javax.swing.JLabel txtPlacar2;
    private javax.swing.JLabel txtPlacarDireitoMais;
    private javax.swing.JLabel txtPlacarDireitoMenos;
    private javax.swing.JLabel txtPlacarEsquerdoMais;
    private javax.swing.JLabel txtPlacarEsquerdoMenos;
    private javax.swing.JLabel txtTempo;
    private javax.swing.JLabel txtTempo1;
    private javax.swing.JLabel txtTempo2;
    private javax.swing.JLabel txtTime1;
    private javax.swing.JLabel txtTime2;
    // End of variables declaration//GEN-END:variables

    public void modificarCronometro(String valor) {
        txtCronometro.setText(valor);
        String[] res =  valor.split(":");
        this.minutos = Integer.parseInt(res[0]);
        this.segundos = Integer.parseInt(res[1]);
    }
    
    public void sair() {
        this.setVisible(false);
        this.dispose();
               
    }
    
    public void funcaoCronometro() {
//        try {
//            apitarBeep();
//        } catch (IOException ex) {
//            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
//        }
        if (this.statusCronometro == false) {
            this.statusCronometro = true;
            timer.start();
//            txtEsquerdoMais.setEnabled(false);
//            txtEsquerdoMenos.setEnabled(false);
//            txtDireitoMais.setEnabled(false);
//            txtDireitoMenos.setEnabled(false);
        } else {
            this.statusCronometro = false;
            timer.stop();
        }
    }

    public void faltaEquipe1() {
        this.falta1++;
        txtFalta1.setText(Integer.toString(this.falta1));
    }
    public void faltaEquipe1Menos() {
        if (this.falta1 == 0) {
            txtFalta1.setText("Faltas");
        } else {
            this.falta1--;
            txtFalta1.setText(Integer.toString(this.falta1));
        }
        
    }
    public void faltaEquipe2() {
        this.falta2++;
        txtFalta2.setText(Integer.toString(this.falta2));
    }
    public void faltaEquipe2Menos() {
        if (this.falta2 == 0) {
            txtFalta2.setText("Faltas");
        } else {
            this.falta2--;
            txtFalta2.setText(Integer.toString(this.falta2));
        }
    }
    
    public void tempoEquipe1() {
        String tempo1 = txtTempo1.getText();
        if (tempo1.equals("Tempo")) {
            statusCronometro = false;
            Tempo tempo = new Tempo();
            
            tempo.setVisible(true);
            timer.stop();
            txtTempo1.setText("1");
        } else if (tempo1.equals("1")) {
            //JOptionPane.showMessageDialog(rootPane, "A EQUIPE JÁ SOLICITOU O TEMPO");
        }
    }
    public void tempoEquipe1Menos() {
        txtTempo1.setText("Tempo");
    }
    public void tempoEquipe2Menos() {
        txtTempo2.setText("Tempo");
    }
    public void tempoEquipe2() {
        String tempo2 = txtTempo2.getText();
        if (tempo2.equals("Tempo")) {
            statusCronometro = false;
            Tempo tempo = new Tempo();
            tempo.setVisible(true);
            timer.stop();
            txtTempo2.setText("1");
        } else if (tempo2.equals("1")) {
            //JOptionPane.showMessageDialog(rootPane, "A EQUIPE JÁ SOLICITOU O TEMPO");
        }
    }

    public void golEquipe1() {
        try {
            beepGol();
        } catch (IOException ex) {
            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.placar1++;
        if (this.placar1 <= 9) {
            txtPlacar1.setText("0" +Integer.toString(this.placar1));
        } else {
            txtPlacar1.setText(Integer.toString(this.placar1));
        }
    }
    
    public void golEquipe1Menos() {
        try {
            beepGol();
        } catch (IOException ex) {
            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (this.placar1 == 0) {
            this.placar1 = 0;
            txtPlacar1.setText("00");
        } else {
            this.placar1--;
            if (this.placar1 <= 9) {
                txtPlacar1.setText("0" +Integer.toString(this.placar1));
            } else {
                txtPlacar1.setText(Integer.toString(this.placar1));
            }
        }
        
    }

    public void golEquipe2() {
        try {
            beepGol();
        } catch (IOException ex) {
            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.placar2++;
        if (this.placar2 <= 9) {
            txtPlacar2.setText("0" +Integer.toString(this.placar2));
        } else {
            txtPlacar2.setText(Integer.toString(this.placar2));
        }
    }
    public void golEquipe2Menos() {
        try {
            beepGol();
        } catch (IOException ex) {
            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (this.placar2 == 0) {
            this.placar2 = 0;
            txtPlacar2.setText("00");
        } else {
            this.placar2--;
            if (this.placar2 <= 9) {
                txtPlacar2.setText("0" +Integer.toString(this.placar2));
            } else {
                txtPlacar2.setText(Integer.toString(this.placar2));
            }
        }
        
    }

    public void modificarEquipe1(int forma, String nome) {
        if (forma == 1) {
            String time1 = JOptionPane.showInputDialog("Digite a EQUIPE 01: ");
            if (time1.length() > 10) {
                JOptionPane.showMessageDialog(rootPane, "Nome muito grande!");
            } else {
                txtTime1.setText(time1);
            }
        } else {
            txtTime1.setText(nome);
        }
        
    }
    public void modificarEquipe2(int forma, String nome) {
        if (forma == 1) {
            String time2 = JOptionPane.showInputDialog("Digite a EQUIPE 02: ");
            if (time2.length() > 10) {
                JOptionPane.showMessageDialog(rootPane, "Nome muito grande!");
            } else {
                txtTime2.setText(time2);
            }
        } else {
            txtTime2.setText(nome);
        }
        
        
    }
    
    
    public void zerar() {
        txtFalta1.setText("Faltas");
        txtFalta2.setText("Faltas");
        this.falta1 = 0;
        this.falta2 = 0;
        
        txtTempo1.setText("Tempo");
        txtTempo2.setText("Tempo");
        txtTime1.setText("Equipe 01");
        txtTime2.setText("Equipe 02");
        txtTempo.setText("1 st");
        
        txtPlacar1.setText("00");
        txtPlacar2.setText("00");
        this.placar1 = 0;
        this.placar2 = 0;
        
        txtCronometro.setText("20:00");
        this.minutos = 20;
        this.segundos = 00;
    }
}

