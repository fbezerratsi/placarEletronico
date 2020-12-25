package br.com.bezerra.clienteplacar;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import classes.Conexao;

public class MainActivity extends Activity {
	Button btEnviar;
	Button btnCronometro;
	TextView txtEquipe1, txtEquipe2, txtFalta1, txtFalta2;
	EditText cadastroEquipe1, cadastroEquipe2;

	//Variável para verificar se a posição das equipes foi invertida...
	static boolean inverterPosicao = false;

	public static String endereco;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnCronometro = (Button) findViewById(R.id.btCronometro);
		txtEquipe1 = (TextView) findViewById(R.id.txtEquipe1);
		txtEquipe2 = (TextView) findViewById(R.id.txtEquipe2);

		txtFalta1 = (TextView) findViewById(R.id.btFalta1);
		txtFalta2 = (TextView) findViewById(R.id.btFalta2);
	}

	@Override
	public void onBackPressed() {

		AlertDialog.Builder builder4 = new AlertDialog.Builder(this);//Cria o gerador do AlertDialog
		builder4.setTitle("Sair");//define o titulo
		builder4.setMessage(R.string.finalizar_aplicacao);//define a mensagem

		//define um botão como positivo
		builder4.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				onDestroy();
				finish();
			}
		});
		//define um botão como negativo.
		builder4.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				//Toast.makeText(MainActivity.this, "Cancelar", Toast.LENGTH_SHORT).show();
			}
		});
		builder4.create();//cria o AlertDialog
		builder4.show();//Exibe
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.e("entrou", "entrokkkkkkkkkkkk");
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("entrou", "DESTROY##############");
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
					Socket soc = new Socket(endereco, 5560);
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
	public void fecharCronometro() {
		vibrar();
		//Toast.makeText(getApplicationContext(), "Felipe", Toast.LENGTH_LONG).show();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Socket soc = new Socket(endereco, 5560);
					PrintWriter writer = new PrintWriter(soc.getOutputStream());
					writer.write("-1");
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
		
		if (text.equals("Tempo 1 +")) {
			//Toast.makeText(getApplicationContext(), "Botão 1: " + text2, Toast.LENGTH_LONG).show();
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Socket soc = new Socket(endereco, 5560);
						PrintWriter writer = new PrintWriter(soc.getOutputStream());
						if (inverterPosicao == false) {
							writer.write("4");
						} else {
							writer.write("5");
						}
						//writer.write("4");
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
						Socket soc = new Socket(endereco, 5560);
						PrintWriter writer = new PrintWriter(soc.getOutputStream());
						if (inverterPosicao == false) {
							writer.write("5");
						} else {
							writer.write("4");
						}
						//writer.write("5");
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
		//trocarPosicao();
		
		if (text.equals("Faltas 1 +")) {

			//Toast.makeText(getApplicationContext(), "Botão 1: " + text2, Toast.LENGTH_LONG).show();

			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Socket soc = new Socket(endereco, 5560);
						PrintWriter writer = new PrintWriter(soc.getOutputStream());
						if (inverterPosicao == false) {
							writer.write("2");
						} else {
							writer.write("3");
						}

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
						Socket soc = new Socket(endereco, 5560);
						PrintWriter writer = new PrintWriter(soc.getOutputStream());
						if (inverterPosicao == false) {
							writer.write("3");
						} else {
							writer.write("2");
						}
						//writer.write("3");
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
		
		if (text.equals("Gol 1 +")) {
			//Toast.makeText(getApplicationContext(), "Botão 1: " + text2, Toast.LENGTH_LONG).show();
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Socket soc = new Socket(endereco, 5560);
						PrintWriter writer = new PrintWriter(soc.getOutputStream());
						if (inverterPosicao == false) {
							writer.write("6");
						} else {
							writer.write("7");
						}
						//writer.write("6");
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
						Socket soc = new Socket(endereco, 5560);
						PrintWriter writer = new PrintWriter(soc.getOutputStream());
						if (inverterPosicao == false) {
							writer.write("7");
						} else {
							writer.write("6");
						}
						//writer.write("7");
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
					Socket soc = new Socket(endereco, 5560);
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

	public void setarEquipe1(View view) {
		//Toast.makeText(getApplicationContext(), "Equipe1", Toast.LENGTH_LONG).show();
		LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.caixa_nome_equipe1, null);
		final EditText edtMemo = (EditText) v.findViewById(R.id.edtEquipe1);


		AlertDialog alerta;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);//Cria o gerador do AlertDialog
		builder.setTitle("Equipe 1");//define o titulo
		builder.setMessage("Digite o nome da EQUIPE 1!");//define a mensagem
		builder.setView(v);

		//define um botão como positivo
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				Conexao c = new Conexao();

				txtEquipe1.setText(edtMemo.getText());
				String equipe1 = edtMemo.getText().toString();
				String equipe2 = txtEquipe2.getText().toString();
				c.enviarNomeEquipes(equipe1, equipe2);
				//Toast.makeText(MainActivity.this, equipe1+"#"+equipe2, Toast.LENGTH_SHORT).show();
			}
		});
		//define um botão como negativo.
		builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				//Toast.makeText(MainActivity.this, "Você clicou no botão Cancelar", Toast.LENGTH_SHORT).show();
			}
		});
		alerta = builder.create();//cria o AlertDialog
		alerta.show();//Exibe
	}

	public void setarEquipe2(View view) {
		//Toast.makeText(getApplicationContext(), "Equipe2", Toast.LENGTH_LONG).show();
		LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.caixa_nome_equipe1, null);
		final EditText edtMemo = (EditText) v.findViewById(R.id.edtEquipe1);


		AlertDialog alerta;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);//Cria o gerador do AlertDialog
		builder.setTitle("Equipe 2");//define o titulo
		builder.setMessage("Digite o nome da Equipe 2!");//define a mensagem
		builder.setView(v);

		//define um botão como positivo
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				Conexao c = new Conexao();
				txtEquipe2.setText(edtMemo.getText());
				String equipe1 = txtEquipe1.getText().toString();

				c.enviarNomeEquipes(equipe1, edtMemo.getText().toString());
				//Toast.makeText(MainActivity.this, equipe1+"#"+edtMemo.getText(), Toast.LENGTH_SHORT).show();
			}
		});
		//define um botão como negativo.
		builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				//Toast.makeText(MainActivity.this, "Você clicou no botão Cancelar", Toast.LENGTH_SHORT).show();
			}
		});
		alerta = builder.create();//cria o AlertDialog
		alerta.show();//Exibe
	}


	public void zerarCronometro() {
		vibrar();
		txtEquipe1.setText("Equipe 1");
		txtEquipe2.setText("Equipe 2");
		//Toast.makeText(getApplicationContext(), "Felipe", Toast.LENGTH_LONG).show();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Socket soc = new Socket(endereco, 5560);
					PrintWriter writer = new PrintWriter(soc.getOutputStream());
					writer.write("0");
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


	public void modificarCronometro(String min, String seg) {
		final String valor = min+":"+seg;
		//String segundos = Integer.toString(seg);
		//Toast.makeText(MainActivity.this, valor, Toast.LENGTH_SHORT).show();
		vibrar();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Socket soc = new Socket(endereco, 5560);
					PrintWriter writer = new PrintWriter(soc.getOutputStream());
					writer.write(valor);
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


	/**
	 * Inverte as posições dos times...
	 */
	public void trocarPosicao() {

		String equipe1 = txtEquipe1.getText().toString();
		String equipe2 = txtEquipe2.getText().toString();

		String aux = equipe2;
		equipe2 = equipe1;
		equipe1 = aux;

		txtEquipe1.setText(equipe1);
		txtEquipe2.setText(equipe2);

		if (inverterPosicao == false) {
			inverterPosicao = true;
		} else {
			inverterPosicao = false;
		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Definição do objeto Inflater
		MenuInflater inflater = this.getMenuInflater();
		
		//Inflar um XML em um Menu vazio
		inflater.inflate(R.menu.menu_principal, menu);
		
		//Exibir o menu		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//Verificar o item do menu selecionado
		switch (item.getItemId()) {
			case R.id.menu_preferencias:
				Intent intent3 = new Intent(MainActivity.this, Configuracoes.class);
				startActivity(intent3);
				return false;
			case R.id.menu_equipes:
				Intent intent1 = new Intent(MainActivity.this, EquipesActivity.class);
				startActivity(intent1);
				return false;
			case R.id.menu_tela_menos:
				Intent intent4 = new Intent(MainActivity.this, TelaMenosActivity.class);
				startActivity(intent4);
				return false;
			case R.id.zerar_cronometro:

				AlertDialog alerta;
				AlertDialog.Builder builder = new AlertDialog.Builder(this);//Cria o gerador do AlertDialog
				builder.setTitle(R.string.string_zerar_cronometro);//define o titulo
				builder.setMessage(R.string.zerar_programa);//define a mensagem

				//define um botão como positivo
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						zerarCronometro();
						//Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
					}
				});
				//define um botão como negativo.
				builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						//Toast.makeText(MainActivity.this, "Cancelar", Toast.LENGTH_SHORT).show();
					}
				});
				alerta = builder.create();//cria o AlertDialog
				alerta.show();//Exibe

				return false;
			case R.id.setar_cronometro:



				//AlertDialog alerta;
				final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);//Cria o gerador do AlertDialog
				builder2.setTitle(R.string.cronometro);//define o titulo

				LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View v = inflater.inflate(R.layout.activity_cronometro, null);


				final EditText ed1 = (EditText) v.findViewById(R.id.editText1);
				final EditText ed2 = (EditText) v.findViewById(R.id.editText2);
				final TextView tv = (TextView) v.findViewById(R.id.txtCronometro);
				final Button bt1 = (Button) v.findViewById(R.id.button1);
				final Button bt2 = (Button) v.findViewById(R.id.button2);
				final Button bt3 = (Button) v.findViewById(R.id.button3);
				final Button bt4 = (Button) v.findViewById(R.id.button4);


				bt1.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						int valor = Integer.parseInt(ed1.getText().toString());
						if (valor < 20) {
							valor++;
							if (valor < 10) {
								ed1.setText("0"+Integer.toString(valor));
							} else {
								ed1.setText(Integer.toString(valor));
							}
							//tv.setText(Integer.toString(valor) + ":" + ed2.getText());
							//builder2.setTitle(Integer.toString(valor)+":"+ed2.getText());
						} else if (valor == 20) {
							ed1.setText("00");
						}
						tv.setText(ed1.getText() + ":" + ed2.getText());
					}
				});
				bt2.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						int valor = Integer.parseInt(ed1.getText().toString());
						if (valor > 0) {
							valor--;
							if (valor < 10) {
								ed1.setText("0"+Integer.toString(valor));
							} else {
								ed1.setText(Integer.toString(valor));
							}

						} else if (valor == 0) {
							ed1.setText("20");
						}
						tv.setText(ed1.getText() + ":" + ed2.getText());
					}
				});
				bt3.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						int valor = Integer.parseInt(ed2.getText().toString());
						if (valor < 59) {
							valor++;
							if (valor < 10) {
								ed2.setText("0"+Integer.toString(valor));
							} else {
								ed2.setText(Integer.toString(valor));
							}
						} else if (valor == 59) {
							ed2.setText("00");
						}
						tv.setText(ed1.getText() + ":" + ed2.getText());
					}
				});
				bt4.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						int valor = Integer.parseInt(ed2.getText().toString());
						if (valor > 0) {
							valor--;
							if (valor < 10) {
								ed2.setText("0" + Integer.toString(valor));
							} else {
								ed2.setText(Integer.toString(valor));
							}
						} else if (valor == 0) {
							ed2.setText("59");
						}
						tv.setText(ed1.getText() + ":" + ed2.getText());
					}
				});



				//builder2.setMessage("Altere o cronômetro");//define a mensagem
				builder2.setView(v);

				//define um botão como positivo
				builder2.setPositiveButton("Definir", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {

						if (ed1.getText().toString().equals("00") && ed2.getText().toString().equals("00")) {
							Toast.makeText(MainActivity.this, R.string.valor_invalido, Toast.LENGTH_SHORT).show();
						} else {
							modificarCronometro(ed1.getText().toString(), ed2.getText().toString());
						}
					}
				});
				//define um botão como negativo.
				builder2.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						//Toast.makeText(MainActivity.this, "Você clicou no botão Cancelar", Toast.LENGTH_SHORT).show();
					}
				});
				builder2.create();//cria o AlertDialog
				builder2.show();//Exibe


				return false;
			case R.id.fechar:

				AlertDialog alerta2;
				AlertDialog.Builder builder3 = new AlertDialog.Builder(this);//Cria o gerador do AlertDialog
				builder3.setTitle("Fechar Programa");//define o titulo
				builder3.setMessage(R.string.fechar_programa);//define a mensagem

				//define um botão como positivo
				builder3.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						fecharCronometro();
						Toast.makeText(MainActivity.this, "Comando enviado com SUCESSO!", Toast.LENGTH_SHORT).show();
					}
				});
				//define um botão como negativo.
				builder3.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						//Toast.makeText(MainActivity.this, "Cancelar", Toast.LENGTH_SHORT).show();
					}
				});
				builder3.create();//cria o AlertDialog
				builder3.show();//Exibe


				return false;
			case R.id.sair:

				AlertDialog.Builder builder4 = new AlertDialog.Builder(this);//Cria o gerador do AlertDialog
				builder4.setTitle("Sair");//define o titulo
				builder4.setMessage(R.string.finalizar_aplicacao);//define a mensagem

				//define um botão como positivo
				builder4.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						finish();
					}
				});
				//define um botão como negativo.
				builder4.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						//Toast.makeText(MainActivity.this, "Cancelar", Toast.LENGTH_SHORT).show();
					}
				});
				builder4.create();//cria o AlertDialog
				builder4.show();//Exibe

				return false;
			case R.id.inverter_posicao_equipe:
				trocarPosicao();
				return false;
			default:
				return super.onOptionsItemSelected(item);
		}
		
	}

	
}
