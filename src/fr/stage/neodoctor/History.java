package fr.stage.neodoctor;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class History extends ListActivity {
	
	public ListView history;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_history);
		
		//String[] mHistory = new String[15];
		//int taille = mHistory.length;
	    //setListAdapter(new SimpleAdapter(null, null, taille, mHistory, null));
	}
	
	@Override
    public void onDestroy()
    {
        super.onDestroy();
        this.finish();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
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
