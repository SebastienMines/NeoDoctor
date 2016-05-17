package fr.stage.neodoctor;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class FirstConnect extends Activity implements OnClickListener,ContextMenu{
	private EditText surname;
	private EditText name;
	private Button skip;
	private String login;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // /!\ supprime la barre titre /!\
		setContentView(R.layout.activity_firstconnect);
		 Intent intent = getIntent();
		 this.login = intent.getStringExtra("login");
		 deserialiser();
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	     StrictMode.setThreadPolicy(policy);
		 
		 this.skip.setOnClickListener(this);
	}

	@Override
	public void deserialiser() {
		// TODO Auto-generated method stub
		this.surname = (EditText) findViewById(R.id.surname);
		this.skip = (Button) findViewById(R.id.skip);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.skip){
			String surname = this.surname.getText().toString();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("login",this.login));
			params.add(new BasicNameValuePair("surname",surname));
			CustomHttpClient.makeHttpRequest("http://miniplus.alwaysdata.net/insert_first_connect.php","POST", params);
			Intent intent = new Intent(FirstConnect.this, Welcome.class);
			startActivity(intent);
		}
		
	}

}
