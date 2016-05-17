package fr.stage.neodoctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.webkit.WebView;
import android.widget.*;

public class Welcome extends Activity {
	
	//false > affichage mini | true > affichage complet
	private boolean toggleState;
	
	private ImageView nightlight;
	private ImageView micro;
	private ImageView speaker;
	
	private ProgressBar progBar;
	
	private TextView nightlighttext;
	
	private WebView stateMini;
	private WebView state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		
		//12/05/16 by BONNAVAUD
		//À faire: aller chercher les informations dans la base de données et les traiter
		//dans l'application afin de déterminer l'état à afficher.
		
		//Pour l'instant, on fait comme si le résultat était: le bébé dort et tout va bien.
		String etat = "sleepingBaby";
		
		toggleState = false;
		
		nightlight = (ImageView) findViewById(R.id.nightlight);
		micro = (ImageView) findViewById(R.id.micro);
		speaker = (ImageView) findViewById(R.id.speaker);
		
		progBar = (ProgressBar) findViewById(R.id.progressBar);
		
		nightlighttext = (TextView) findViewById(R.id.nightlighttext);
		
		stateMini = (WebView) findViewById(R.id.babystategifmini);
		stateMini.loadUrl("file:///android_asset/"+etat+"Mini.gif");
		
		state = (WebView) findViewById(R.id.babystategif);
		state.loadUrl("file:///android_asset/"+etat+".gif");
		
	}
	
	
	public void toggleStateView(View v){
		
		if(toggleState){//on remplace l'affichage mini par l'affichage
			
			stateMini.setVisibility(View.GONE);
			nightlight.setVisibility(View.GONE);
			progBar.setVisibility(View.GONE);
			nightlighttext.setVisibility(View.GONE);
			
			state.setVisibility(View.VISIBLE);
			micro.setVisibility(View.VISIBLE);
			speaker.setVisibility(View.VISIBLE);
			
			toggleState=false;
		}
		else{//on remplace l'affichage par l'affichage mini
			
			state.setVisibility(View.GONE);
			micro.setVisibility(View.GONE);
			speaker.setVisibility(View.GONE);
			
			nightlight.setVisibility(View.VISIBLE);
			progBar.setVisibility(View.VISIBLE);
			stateMini.setVisibility(View.VISIBLE);
			nightlighttext.setVisibility(View.VISIBLE);
			
			toggleState=true;
		}
	}
	
	public void listen(View v){
		
	}
	
	public void speak(View v){
		
	}
	
	public void nightlight(View v){		
		Intent in = new Intent(Welcome.this, NightLight.class);
		this.startActivity(in);
	}
	
	public void history(View v){
		
		Intent in = new Intent(this, History.class);
		
		this.startActivity(in);
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
