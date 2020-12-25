package br.com.bezerra.clienteplacar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Configuracoes extends Activity {

	EditText endereco;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configuracoes);
		
	}
	
	public void setarEndereco(View v) {
		
		endereco = (EditText) findViewById(R.id.edtEndereco);
		MainActivity.endereco = endereco.getText().toString();
		this.finish();
	}
}
