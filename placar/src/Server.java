//import com.sun.glass.ui.Screen;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;

public class Server {
    
    public Server() {

    }

    public static void main(String args[]) throws AWTException, SocketException{
        Cronometro cronometro = new Cronometro();
        cronometro.setVisible(true);
        try {
            ServerSocket Servidor = new ServerSocket(5560);

            while(true){
                Socket C = Servidor.accept();
                Scanner s = new Scanner(C.getInputStream());
                String texto = s.nextLine();
                if (texto.indexOf("#") > 0) {
                    String[] res =  texto.split("#");
                    cronometro.modificarEquipe1(2, res[0]);
                    cronometro.modificarEquipe2(2, res[1]);
                } else if (texto.indexOf(":") > 0) {
                    //String[] res =  texto.split(":");
                    cronometro.modificarCronometro(texto);
                    //JOptionPane.showMessageDialog(null, res[0] + "\n" + res[1]);
                } else {
                    //System.out.println(s.nextLine());
                    switch (texto) {
                        case "1":
                            cronometro.funcaoCronometro();
                            break;
                        case "2":
                            cronometro.faltaEquipe1();
                            break;
                        case "3":
                            cronometro.faltaEquipe2();
                            break;
                        case "4":
                            cronometro.tempoEquipe1();
                            break;
                        case "5":
                            cronometro.tempoEquipe2();
                            break;
                        case "6":
                            cronometro.golEquipe1();
                            break;
                        case "7":
                            cronometro.golEquipe2();
                            break;
                        case "8":
                            //System.out.println("felipe");
                            Robot robot = new Robot();
                            robot.keyPress(KeyEvent.VK_SPACE);
                            robot.keyRelease(KeyEvent.VK_SPACE);
                            break;
                        case "12":
                            cronometro.faltaEquipe1Menos();
                            break;
                        case "13":
                            cronometro.faltaEquipe2Menos();
                            break;
                        case "14":
                            cronometro.tempoEquipe1Menos();
                            break;
                        case "15":
                            cronometro.tempoEquipe2Menos();
                            break;
                        case "16":
                            cronometro.golEquipe1Menos();
                            break;
                        case "17":
                            cronometro.golEquipe2Menos();
                            break;
                        case "0":
                            cronometro.zerar();
                            break;
                        case "-1":
                            int dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja SAIR?");
                            if(dialogResult == JOptionPane.YES_OPTION){
                                Servidor.close();
                                cronometro.sair();
                            }
                            
                            break;
                        default:
                            System.out.println("Comando não encontrado!");
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}