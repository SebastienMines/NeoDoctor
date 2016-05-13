package fr.stage.neodoctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class FirstConnect extends Activity implements OnClickListener,ContextMenu{
	private EditText surname;
	private EditText idDevice;
	private char login;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // /!\ supprime la barre titre /!\
		setContentView(R.layout.activity_firstconnect);
		 Intent intent = getIntent();
		 char c = 'O';
		 this.login = intent.getCharExtra("login",c);
		 Log.i("LOGIN : ",String.valueOf(this.login));
	}

	@Override
	public void deserialiser() {
		// TODO Auto-generated method stub
		this.surname = (EditText) findViewById(R.id.editText1);
		this.idDevice = (EditText) findViewById(R.id.editText2);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
