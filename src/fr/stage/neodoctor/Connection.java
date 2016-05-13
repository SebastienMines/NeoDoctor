package fr.stage.neodoctor;

import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Connection extends Activity implements OnClickListener,ContextMenu {
	
	private EditText email;
	private EditText mdp;
	private TextView creerCompte;
	private TextView mdpOublie;
	private Button seConnecter;
	private boolean firstConnect;
	final AsyncTask<String, Void, String> textv = new RetrieveConnect().execute(null, null, null);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // /!\ supprime la barre titre /!\
		setContentView(R.layout.activity_connection);
		
		deserialiser();
		
		this.email.setOnClickListener(this);
		this.mdp.setOnClickListener(this);
		this.seConnecter.setOnClickListener(this);
		this.creerCompte.setOnClickListener(this);
		this.mdpOublie.setOnClickListener(this);
	}
	
	public void deserialiser(){
		this.email = (EditText) findViewById(R.id.login);
		this.mdp = (EditText) findViewById(R.id.mdp);
		this.creerCompte = (TextView) findViewById(R.id.creerCompte);
		this.seConnecter = (Button) findViewById(R.id.seConnecter);
		this.mdpOublie = (TextView) findViewById(R.id.mdpoublie);
	}
	
	public void onClick(View v){
		if(v.getId()==R.id.login){
			this.email.setText("");
		}
		if(v.getId()==R.id.mdp){
			this.mdp.setText("");
		}
		if(v.getId()==R.id.seConnecter){
			try {
				if(this.isNetworkAvailable()){
					this.seConnecter(this.email.getText().toString(), this.mdp.getText().toString());
				}
				else{
					Toast.makeText(Connection.this,"Erreur : Aucune connexion internet", Toast.LENGTH_SHORT).show();
				}
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(v.getId()==R.id.creerCompte){
			Uri uri = Uri.parse("https://www.google.fr");
			Intent intent = new Intent(Connection.this, Creation.class);
			startActivity(intent);
		}
		if(v.getId()==R.id.mdpoublie){
			Uri uri = Uri.parse("https://www.google.fr");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}
	}
	
	public void seConnecter (String unLogin, String unMdp) throws InterruptedException, ExecutionException{
		boolean test = this.connect(this.textv.get(), unLogin, unMdp);
		Log.i("BOOLEAN", ""+test);
		if(test == true && this.firstConnect==false){
			Intent intent = new Intent(Connection.this, Welcome.class);
			startActivity(intent);
		}
		else if(test == true && this.firstConnect==true){
			Intent intent = new Intent(Connection.this, FirstConnect.class);
			startActivity(intent);
		}
		else{
			Toast.makeText(Connection.this,"Erreur de login ou de mot de passe", Toast.LENGTH_SHORT).show();
		}
	}
	
	public boolean connect(String result, String unLogin, String unMdp){
		try{
			Log.i("log_infos", result);
 	        JSONArray jArray = new JSONArray(result);
 	        for(int i=0;i<jArray.length();i++){
 	                JSONObject json_data = jArray.getJSONObject(i);
 	                Log.i("log_tag","login: "+json_data.getString("login")+
 	                        ", mdp: "+json_data.getString("mdp")
 	                );
 	                if(json_data.getString("login").equals(unLogin) && json_data.getString("mdp").equals(unMdp) && json_data.getString("prenom").equals("")){
 	                	this.firstConnect = true;
 	                	return true;
 	                }
 	                if(json_data.getString("login").equals(unLogin) && json_data.getString("mdp").equals(unMdp)){
 	                	this.firstConnect = false;
 	                	return true;
 	                }
 	        }
 	        return false;
		}
		catch(JSONException e){
			Log.e("log_tag", "Error parsing data "+e.toString());
			return false;
		}
	}
	
	private boolean isNetworkAvailable() {
	        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	        return activeNetworkInfo != null;
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connection, menu);
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
