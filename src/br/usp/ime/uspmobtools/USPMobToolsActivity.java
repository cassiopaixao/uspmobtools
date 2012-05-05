package br.usp.ime.uspmobtools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.usp.ime.uspmobtools.bandeco.AppBandecoActivity;
import br.usp.ime.uspmobtools.janusmob.JanusMobActivity;
import br.usp.ime.uspmobtools.rucard.RuCardActivity;
import br.usp.ime.uspmobtools.uspmap.USPMapActivity;

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
		
		Button buttonRuCard = (Button) findViewById(R.id.button_rucard);
		buttonRuCard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, RuCardActivity.class);
				context.startActivity(intent);
			}
		});
		
		Button buttonUspMap = (Button) findViewById(R.id.button_uspmap);
		buttonUspMap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, USPMapActivity.class);
				context.startActivity(intent);
			}
		});
		
		Button buttonBandeco = (Button) findViewById(R.id.button_bandeco);
		buttonBandeco.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, AppBandecoActivity.class);
				context.startActivity(intent);
			}
		});
		
	}

}