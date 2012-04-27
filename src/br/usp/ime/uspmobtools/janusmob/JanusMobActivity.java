package br.usp.ime.uspmobtools.janusmob;

import br.usp.ime.uspmobtools.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class JanusMobActivity extends Activity {
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context = this;
		setContentView(R.layout.janusmob_main);
		
		Button botaoEntrar = (Button) findViewById(R.id.janusmob_button_entrar);
		botaoEntrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, DadosAlunoActivity.class);
				context.startActivity(intent);
			}
		});
	}

}
