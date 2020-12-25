package classes;

import android.view.View;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import br.com.bezerra.clienteplacar.MainActivity;

/**
 * Created by Felipe Bezerra on 01/08/2015.
 */
public class Conexao {

    public void enviarNomeEquipes(final String eq1, final String eq2) {
        //eq1 = equipe1.getText().toString();
        //eq2 = equipe2.getText().toString();

        //eq1 = "felipe";
        //byte[] umByteArray = eq1.getBytes();
        //texto = "f";
        //String[] array = "Felipe";

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Socket soc = new Socket(MainActivity.endereco, 5560);
                    PrintWriter writer = new PrintWriter(soc.getOutputStream(), true);
                    //DataOutputStream writer = new DataOutputStream(soc.getOutputStream());
                    //writer.writeBytes("\n" + eq1 + "\n");

                    //writer.writeByte(num);
                    writer.write(eq1+"#"+eq2);

                    writer.flush();
                    writer.close();
                } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        t.start();

    }
}
