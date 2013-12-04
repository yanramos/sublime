/*package com.example.sublime;

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
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

public class GetFromBD {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.);
		Intent intent = getIntent();
		if (intent.getExtras().getBoolean("inseriu")){
			adicionaDoBD();
		}
		//Preenche a lista
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.get_from_bd, menu);
		return true;
	}
	
	public void adicionaDoBD(){
		String result = "";
		//URL DO ARQUIVO GETINFOS.PHP
		String url = "";
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		Intent intent = getIntent();
		pairs.add(new BasicNameValuePair("code", intent.getExtras().getString("code")));
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			post.setEntity(new UrlEncodedFormEntity(pairs));
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			InputStream responseStream = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream),8);
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null){
				builder.append(line+"\n");
			}
			responseStream.close();
			result = builder.toString();
			JSONArray jArray = new JSONArray(result);
			JSONObject data = jArray.getJSONObject(0);
			//Obter dados do JSONObject (vou completar quando criar a tabela)
			String titulo = "TESTE";
			String subTitulo = "teste";
			//Insere os dados novos no arquivo texto
		} catch (Exception e){
			Toast.makeText(getApplicationContext(), "Erro na conexao com o banco de dados.", Toast.LENGTH_LONG).show();
		}
	}

}*/
