package br.usp.ime.uspmobtools;

import br.usp.ime.uspmobtools.janusmob.JanusMobActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class USPMobToolsActivity extends Activity {
	private Context context;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context = this;
		setContentView(R.layout.main);

		Button buttonJanusMob = (Button) findViewById(R.id.button_janusmob);
		buttonJanusMob.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, JanusMobActivity.class);
				context.startActivity(intent);
			}
		});
	}

}