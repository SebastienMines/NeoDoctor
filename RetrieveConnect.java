	package fr.stage.neodoctor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.view.TextureView;
import android.widget.TextView;

class RetrieveConnect extends AsyncTask<String, Void, String> implements Retrieve{
@Override
    public String doInBackground(String... params) {
         try{
	        	 String result = "";
	         	// L'année à envoyer
	         	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	         	nameValuePairs.add(new BasicNameValuePair("id","3"));
	         	 
	         	// Envoi de la requête avec HTTPPost
	             InputStream is = null;
	         	try{
	         	        HttpClient httpclient = new DefaultHttpClient();
	         	        HttpPost httppost = new HttpPost("http://miniplus.alwaysdata.net/connect_with_identifiants.php");
	         	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	         	        HttpResponse response = httpclient.execute(httppost);
	         	        HttpEntity entity = response.getEntity();
	         	        is = entity.getContent();
	         	}catch(Exception e){
	         	        Log.e("log_tag", "Error in http connection "+e.toString());
	         	}
	
	         	//Conversion de la réponse en chaine
	         	try{
	         	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	         	        StringBuilder sb = new StringBuilder();
	         	        String line = null;
	         	        while ((line = reader.readLine()) != null) {
	         	                sb.append(line + "\n");
	         	        }
	         	        is.close();
	         	 
	         	        result=sb.toString();
	         	        return result;
	         	}catch(Exception e){
	         	        Log.e("log_tag", "Error converting result "+e.toString());
	         	}
         }
         catch(Exception e){
        	 
         }
        return null;
    }
}
