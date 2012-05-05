package br.usp.ime.uspmobtools.janusmob;

import br.usp.ime.uspmobtools.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class JanusMobActivity extends Activity {
	
	private Context context;

	private JanusDatabaseHelper janusdb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context = this;
		setContentView(R.layout.janusmob_main);
		
		janusdb = new JanusDatabaseHelper(this);
		
		Button botaoEntrar = (Button) findViewById(R.id.janusmob_button_entrar);
		botaoEntrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String nusp = ((TextView)findViewById(R.id.janusmob_field_usuario)).getText().toString();
				String senha = ((TextView) findViewById(R.id.janusmob_field_senha)).getText().toString();
//				Intent intent = new Intent(context, DadosAlunoActivity.class);
				Intent intent = new Intent(context, ExibeJanusActivity.class);
				intent.putExtra("nusp", nusp).putExtra("senha", senha);
				context.startActivity(intent);
			}
		});
	}

}
