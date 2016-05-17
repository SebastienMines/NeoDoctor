package fr.stage.neodoctor;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MdpForgot extends Activity implements OnClickListener,ContextMenu{
	private EditText mail;
	private Button submit;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // /!\ supprime la barre titre /!\
		setContentView(R.layout.activity_mdpforgot);
		deserialiser();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		 
		this.submit.setOnClickListener(this);
	}

	@Override
	public void deserialiser() {
		// TODO Auto-generated method stub
		this.mail = (EditText) findViewById(R.id.mail);
		this.submit = (Button) findViewById(R.id.send);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.send){
			String mail = this.mail.getText().toString();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("mail",mail));
			CustomHttpClient.makeHttpRequest("http://miniplus.alwaysdata.net/envoi_mdp.php","POST", params);
		}
		
	}

}

