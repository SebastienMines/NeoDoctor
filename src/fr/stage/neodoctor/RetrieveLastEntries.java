package fr.stage.neodoctor;

import java.io.*;
import java.util.ArrayList;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;

import android.os.AsyncTask;
import android.util.Log;

class RetrieveLastEntries extends AsyncTask<String, Void, String> {
@Override
    protected String doInBackground(String... params) {
         try{
                 String result = "";
                 // L'année à envoyer
                 ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                 nameValuePairs.add(new BasicNameValuePair("id","3"));
                 
                // Envoi de la requête avec HTTPPost
                 InputStream is = null;
                try{
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httppost = new HttpPost("http://miniplus.alwaysdata.net/valeur.php");
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        HttpResponse response = httpclient.execute(httppost);
                        HttpEntity entity = response.getEntity();
                        is = entity.getContent();
                }catch(Exception e){
                        Log.e("RLE Error in http connection", e.toString());
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
                }catch(Exception e){
                        Log.e("RLE Error converting result", e.toString());
                }
                 
                // Parsing des données JSON
                try{
                    
                        JSONArray jArray = new JSONArray(result);
                        int length = jArray.length();
                        
                        String retour = jArray.getJSONObject(length-1).getString("temperature")+";";
                        retour += jArray.getJSONObject(length-2).getString("date")+";";
                        
                        for(int i=length-2;i>length-11;i=i-2){
                            
                            if(i==length-1){
                              retour+=jArray.getJSONObject(i).getString("temperature")+";"+jArray.getJSONObject(i++).getString("date");
                            }
                            else{
                              retour+=jArray.getJSONObject(i).getString("temperature")+";"+jArray.getJSONObject(i++).getString("date")+";";
                            }
                            
                        }
                        //Log.i("Dernières valeurs", retour);
                        return retour;
                        
                }
                catch(JSONException e){
                        Log.e("RLE Erreur parsing", e.toString());
                }
         }
         catch(Exception e){
             Log.e("RLE", e.toString());
         }
        return null;
    }
}