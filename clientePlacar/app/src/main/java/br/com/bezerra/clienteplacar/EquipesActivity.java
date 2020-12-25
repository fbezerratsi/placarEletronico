package br.com.bezerra.clienteplacar;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EquipesActivity extends Activity {

	Button btEnviarEquipes;
	EditText equipe1;
	EditText equipe2;
	
	String eq1;
	String eq2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_equipes);
		
		
		equipe1 = (EditText) findViewById(R.id.edtEquipe1);
		equipe2 = (EditText) findViewById(R.id.edtEquipe2);
	}
	
	public void enviarNomeEquipe(View v) {
		eq1 = equipe1.getText().toString();
		eq2 = equipe2.getText().toString();

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
