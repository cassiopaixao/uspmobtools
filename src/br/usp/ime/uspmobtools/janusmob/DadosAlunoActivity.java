package br.usp.ime.uspmobtools.janusmob;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import br.usp.ime.uspmobtools.R;

public class DadosAlunoActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.janusmob_dados_aluno);
		
		new AtualizaDadosAluno("779", "asd", this).start();

	}

	void exibeMensagem(String titulo, String mensagem) {
		new AlertDialog.Builder(this).setTitle(titulo).setMessage(mensagem)
				.show();
	}
}
