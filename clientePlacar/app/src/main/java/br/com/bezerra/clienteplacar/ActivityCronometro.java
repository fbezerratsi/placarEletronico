package br.com.bezerra.clienteplacar;

/**
 * Created by Felipe Bezerra on 05/08/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityCronometro extends Activity {

    EditText ed1, ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        ed1 = (EditText) findViewById(R.id.editText1);
        ed1 = (EditText) findViewById(R.id.editText2);

    }

    public void mais1(View v) {
        int valor = Integer.parseInt(ed1.getText().toString());
        valor++;
    }
    public void menos1(View v) {
        int valor = Integer.parseInt(ed1.getText().toString());
        valor--;
    }
    public void mais2(View v) {
        int valor = Integer.parseInt(ed2.getText().toString());
        valor++;
    }
    public void menos2(View v) {
        int valor = Integer.parseInt(ed2.getText().toString());
        valor--;
    }
}
