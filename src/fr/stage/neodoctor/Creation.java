package fr.stage.neodoctor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.*;

public class Creation extends Activity implements OnClickListener,ContextMenu {
	
	private EditText email;
	private EditText mdp;
	private Button creerCompte;
	private Button retourneConnection;
	private LinearLayout creation;
	private LinearLayout felicitation;
	private static final String EMAIL_PATTERN ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+
												"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PASSWORD_PATTERN ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"; //à utiliser pour la version finale

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // /!\ supprime la barre titre /!\
		setContentView(R.layout.activity_creation);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
		
		deserialiser();
		
		this.email.setOnClickListener(this);
		this.mdp.setOnClickListener(this);
		this.creerCompte.setOnClickListener(this);
		this.retourneConnection.setOnClickListener(this);
	}
	
	public void deserialiser(){
		this.email = (EditText) findViewById(R.id.login);
		this.mdp = (EditText) findViewById(R.id.mdp);
		this.creerCompte = (Button) findViewById(R.id.creerCompte);
		this.retourneConnection = (Button) findViewById(R.id.retourneConnexion);
		this.creation = (LinearLayout) findViewById(R.id.Ll1);
		this.felicitation = (LinearLayout) findViewById(R.id.Ll2);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.login){
			this.email.setText("");
		}
		if(v.getId()==R.id.mdp){
			this.mdp.setText("");
		}
		if(v.getId()==R.id.creerCompte){
			String login = this.email.getText().toString();
			String mdp = this.mdp.getText().toString();
			if(regexemail(login)){
				if(highPassword(mdp)){
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("login",login));
					params.add(new BasicNameValuePair("mdp",mdp));
					CustomHttpClient.makeHttpRequest("http://miniplus.alwaysdata.net/insert_members.php","POST", params);
					this.creation.setVisibility(View.GONE);
					this.felicitation.setVisibility(View.VISIBLE);
				}
				else{
					Toast.makeText(Creation.this,"Le mot de passe doit faire 8 charactères ou plus.", Toast.LENGTH_SHORT).show();
				}
			}
			else{
				Toast.makeText(Creation.this,"L'e-mail est invalide.", Toast.LENGTH_SHORT).show();
			}
		}
		if(v.getId()==R.id.retourneConnexion){
			Intent intent = new Intent(Creation.this, Connection.class);
			startActivity(intent);
		}
	}
	
	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null;
	}
	
	public boolean regexemail(String aEmail){
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(aEmail);
		return matcher.matches();
	}
	
	public boolean highPassword(String aPassword){
		if(aPassword.length()>=8){
			return true;
		}
		else{
			return false;
		}
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
