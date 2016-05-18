package fr.stage.neodoctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class Welcome extends Activity {
	
	//false > affichage mini | true > affichage complet
	private boolean toggleState;
	
	private RelativeLayout layoutMini;
	private RelativeLayout layoutMaxi;
	private RelativeLayout layoutNightLight;
	private RelativeLayout layoutMisc;

	private ImageView babyHeadMini;
	private ImageView babyHead;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		
		//--------- d�serialisation ----------
		
		layoutMini = (RelativeLayout) findViewById(R.id.layoutMini);
		layoutMaxi = (RelativeLayout) findViewById(R.id.layoutMaxi);
		layoutNightLight = (RelativeLayout) findViewById(R.id.layoutNightLight);
		layoutMisc = (RelativeLayout) findViewById(R.id.layoutMisc);

		babyHeadMini = (ImageView) findViewById(R.id.babyHeadMini);
		babyHead = (ImageView) findViewById(R.id.babyHead);

		toggleState = false;
		
		//------------ traitements ------------
		
		//12/05/16 by BONNAVAUD
		//� faire: aller chercher les informations dans la base de donn�es et les traiter
		//dans l'application afin de d�terminer l'�tat � afficher.
		
		//Pour l'instant, on fait comme si le r�sultat �tait: le b�b� dort et tout va bien.
		int etat = 1;
		
		switch(etat){//afficher l'�tat selon le r�sultat du traitement
		
			case(0):{//bracelet �teint ou aucune mesures
				babyHeadMini.setImageResource(R.drawable.flavor);
				babyHead.setImageResource(R.drawable.flavor);
				break;
			}
			case(1):{//b�b� dort
				babyHeadMini.setImageResource(R.drawable.headasleep);
				babyHead.setImageResource(R.drawable.headasleep);
				layoutMini.setBackgroundResource(R.drawable.fondsleeping);
				layoutMaxi.setBackgroundResource(R.drawable.fondsleeping);
				break;
			}
			case(2):{//b�b� est r�veill�
				babyHeadMini.setImageResource(R.drawable.headawake);
				babyHead.setImageResource(R.drawable.headawake);
				layoutMini.setBackgroundResource(R.drawable.fondawake);
				layoutMaxi.setBackgroundResource(R.drawable.fondawake);
				break;
			}
			case(3):{//b�b� est r�veill� anxieux
				babyHeadMini.setImageResource(R.drawable.headanxious);
				babyHead.setImageResource(R.drawable.headanxious);
				break;
			}
			default:{//etat ne correspond � aucune valeur attendue, probablement une erreur de traitement
				Toast.makeText(this, "Woops, something went wrong!", Toast.LENGTH_LONG).show();
			}
		}
		
	}
	
	
	public void toggleStateView(View v){
		
		if(toggleState){//on remplace l'affichage mini par l'affichage
			
			layoutMini.setVisibility(View.GONE);
			layoutMaxi.setVisibility(View.VISIBLE);
			layoutNightLight.setVisibility(View.GONE);
			layoutMisc.setVisibility(View.VISIBLE);
			
			//on indique qu'on a d�sormais l'affichage
			toggleState=false;
		}
		else{//on remplace l'affichage par l'affichage mini
			
			layoutMini.setVisibility(View.VISIBLE);
			layoutMaxi.setVisibility(View.GONE);
			layoutNightLight.setVisibility(View.VISIBLE);
			layoutMisc.setVisibility(View.GONE);

			//on indique qu'on a d�sormais l'affichage mini
			toggleState=true;
		}
	}
	
	public void listen(View v){
		
	}
	
	public void speak(View v){
		
	}
	
	public void smell(View v){
		
	}
	
	public void nightlight(View v){
		
		Intent intentNightLight = new Intent(Welcome.this, NightLight.class);
		
		this.startActivity(intentNightLight);
	}
	
	public void history(View v){
		
		Intent intentHistory = new Intent(this, History.class);
		
		this.startActivity(intentHistory);
		
	}
	
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {

	  // Save UI state changes to the savedInstanceState.   
	  // This bundle will be passed to onCreate if the process is  
	  // killed and restarted.

	  savedInstanceState.putBoolean("toggleState", toggleState);
	  super.onSaveInstanceState(savedInstanceState);  
	}  
	//onRestoreInstanceState  
	    @Override  
	public void onRestoreInstanceState(Bundle savedInstanceState) {  
	  super.onRestoreInstanceState(savedInstanceState);  
	  // Restore UI state from the savedInstanceState.  
	  // This bundle has also been passed to onCreate.  
	  toggleState = savedInstanceState.getBoolean("toggleState");
	  if(toggleState)
		  toggleState = false;
	  else
		  toggleState = true;
	  this.toggleStateView(null);
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
