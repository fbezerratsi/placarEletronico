package br.com.bezerra.clienteplacar;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TelaMenosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_menos);
	}
	
	
	private void vibrar() {
        Vibrator rr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long milliseconds = 30;//'30' é o tempo em milissegundos, é basicamente o tempo de duração da vibração. portanto, quanto maior este numero, mais tempo de vibração você irá ter
        rr.vibrate(milliseconds); 
    }
	
	public void funcaoCronometro(View v) {
		vibrar();
		//Toast.makeText(getApplicationContext(), "Felipe", Toast.LENGTH_LONG).show();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Socket soc = new Socket(MainActivity.endereco, 5560);
					PrintWriter writer = new PrintWriter(soc.getOutputStream());
					writer.write("1");
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
	
	
	public void tempo(View v) {
		vibrar();
		String text = (String) ((Button) v).getText();
		
		if (text.equals("Tempo 1 -")) {
			//Toast.makeText(getApplicationContext(), "Botão 1: " + text2, Toast.LENGTH_LONG).show();
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Socket soc = new Socket(MainActivity.endereco, 5560);
						PrintWriter writer = new PrintWriter(soc.getOutputStream());
						writer.write("14");
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
		} else {
			//Toast.makeText(getApplicationContext(), "Botão 2", Toast.LENGTH_LONG).show();
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Socket soc = new Socket(MainActivity.endereco, 5560);
						PrintWriter writer = new PrintWriter(soc.getOutputStream());
						writer.write("15");
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
	
	
	public void faltas(View v) {
		vibrar();
		String text = (String) ((Button) v).getText();
		
		if (text.equals("Faltas 1 -")) {
			//Toast.makeText(getApplicationContext(), "Botão 1: " + text2, Toast.LENGTH_LONG).show();
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Socket soc = new Socket(MainActivity.endereco, 5560);
						PrintWriter writer = new PrintWriter(soc.getOutputStream());
						writer.write("12");
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
		} else {
			//Toast.makeText(getApplicationContext(), "Botão 2", Toast.LENGTH_LONG).show();
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Socket soc = new Socket(MainActivity.endereco, 5560);
						PrintWriter writer = new PrintWriter(soc.getOutputStream());
						writer.write("13");
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
	
	
	public void gol(View v) {
		vibrar();
		String text = (String) ((Button) v).getText();
		
		if (text.equals("Gol 1 -")) {
			//Toast.makeText(getApplicationContext(), "Botão 1: " + text2, Toast.LENGTH_LONG).show();
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Socket soc = new Socket(MainActivity.endereco, 5560);
						PrintWriter writer = new PrintWriter(soc.getOutputStream());
						writer.write("16");
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
		} else {
			//Toast.makeText(getApplicationContext(), "Botão 2", Toast.LENGTH_LONG).show();
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Socket soc = new Socket(MainActivity.endereco, 5560);
						PrintWriter writer = new PrintWriter(soc.getOutputStream());
						writer.write("17");
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
	
	public void cancelarTempo(View v) {
		vibrar();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Socket soc = new Socket(MainActivity.endereco, 5560);
					PrintWriter writer = new PrintWriter(soc.getOutputStream());
					writer.write("8");
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


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Definição do objeto Inflater
		MenuInflater inflater = this.getMenuInflater();

		//Inflar um XML em um Menu vazio
		inflater.inflate(R.menu.menu_tela_menos, menu);

		//Exibir o menu
		return true;
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//Verificar o item do menu selecionado
		switch (item.getItemId()) {
			case R.id.menu_tela_mais:
				//Intent intent4 = new Intent(TelaMenosActivity.this, MainActivity.class);
				this.finish();
				super.onBackPressed();
				//startActivity(intent4);
				return false;
			default:
				return super.onOptionsItemSelected(item);
		}

	}
}
