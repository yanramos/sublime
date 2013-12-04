package com.example.sublime;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.example.sublime.layout.PromoListItem;

public class LoadingActivity extends Activity {

	String title;
	String subtitle;
	String icon;
	String address;
	String status = "true";
	boolean repeated = false;
	ArrayList<PromoListItem> items = new ArrayList<PromoListItem>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		AsyncLoading loading = new AsyncLoading();
		loading.execute();
	}

	private class AsyncLoading extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... arg0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			Intent intent = getIntent();
			boolean updating = intent.getExtras().getBoolean("updating");
			String result = "";
			String url = "http://sublimeapp.comlu.com/getInfo.php";
			ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("id", intent.getExtras()
					.getString("id")));
			String stringId = intent.getExtras().getString("id");
			FileInputStream fIn = null;
			FileOutputStream fOut = null;
			if (updating){
				try {
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(url);
					post.setEntity(new UrlEncodedFormEntity(pairs));
					HttpResponse response = client.execute(post);
					HttpEntity entity = response.getEntity();
					InputStream responseStream = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(responseStream), 8);
					StringBuilder builder = new StringBuilder();
					String line = null;
					while ((line = reader.readLine()) != null) {
						builder.append(line + "\n");
					}
					responseStream.close();
					result = builder.toString();
					result = result.substring(0, result.indexOf("<"));

					JSONArray jArray = new JSONArray(result);
					JSONObject data = jArray.getJSONObject(0);
					title = data.getString("title");
					subtitle = data.getString("subtitle");
					icon = data.getString("icon");
					address = data.getString("url");
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							TextView label = (TextView) findViewById(R.id.textView1);
							label.setText("Dados obtidos com sucesso!");
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							TextView label = (TextView) findViewById(R.id.textView1);
							label.setText("Atualizando dados...");
						}
					});
					
					fIn = openFileInput("sublime_if.txt");
					InputStreamReader isr = new InputStreamReader(fIn);
					fOut = openFileOutput("sublime_if_copy.txt", Context.MODE_PRIVATE);
					OutputStreamWriter osw = new OutputStreamWriter(fOut);
					
					int readInt;
					int readChar;
					String readId = "";
					String goalKey = intent.getExtras().getString("key");
					String rest = "";
					int restPos = 0;
					boolean copyingRest = false;
					while ((readInt = isr.read())!= -1){
						readChar = (char) readInt;
						if (!copyingRest && readChar != '\n'){
							readId += readChar;
							osw.write(readChar);
						} else if (!copyingRest){
							osw.write(readChar);
							if (goalKey.equalsIgnoreCase(readId)){
								copyingRest = true;
								rest = goalKey + "\n" + title + "\n" + subtitle + "\n" + icon +
										"\n" + address + "\n" + status + "\n";
								restPos = 0;
							}
						} else {
							osw.write(rest.charAt(restPos));
							restPos++;
							if (restPos >= rest.length()){
								copyingRest = false;
							}
						}
					}
					
					isr.close();
					osw.close();
					
					fIn = openFileInput("sublime_if_copy.txt");
					isr = new InputStreamReader(fIn);
					fOut = openFileOutput("sublime_if.txt", Context.MODE_PRIVATE);
					osw = new OutputStreamWriter(fOut);
					
					while ((readInt = isr.read())!= -1){
						readChar = (char) readInt;
						osw.write(readChar);
					}
					
					isr.close();
					osw.close();

				} catch (Exception e) {
					e.printStackTrace();
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							TextView label = (TextView) findViewById(R.id.textView1);
							label.setText("Não foi possível conectar ao servidor.");
						}
					});
				}
				
			} else {
				try {
					fIn = openFileInput("sublime_kf.txt");
					InputStreamReader isr = new InputStreamReader(fIn);
					stringId = intent.getExtras().getString("id");
					char c;
					int i;
					String id = "";
					while ((i = isr.read()) != -1 && !repeated) {
						c = (char) i;
						if (c != '\n') {
							id += c;
						} else {
							if (stringId.equalsIgnoreCase(id)) {
								repeated = true;
							}
						}
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} finally {
					if (fIn != null) {
						try {
							fIn.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if (!repeated) {
					try {
						HttpClient client = new DefaultHttpClient();
						HttpPost post = new HttpPost(url);
						post.setEntity(new UrlEncodedFormEntity(pairs));
						HttpResponse response = client.execute(post);
						HttpEntity entity = response.getEntity();
						InputStream responseStream = entity.getContent();
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(responseStream), 8);
						StringBuilder builder = new StringBuilder();
						String line = null;
						while ((line = reader.readLine()) != null) {
							builder.append(line + "\n");
						}
						responseStream.close();
						result = builder.toString();
						result = result.substring(0, result.indexOf("<"));

						JSONArray jArray = new JSONArray(result);
						JSONObject data = jArray.getJSONObject(0);
						title = data.getString("title");
						subtitle = data.getString("subtitle");
						icon = data.getString("icon");
						address = data.getString("url");

						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								TextView label = (TextView) findViewById(R.id.textView1);
								label.setText("Dados obtidos com sucesso!");
							}
						});
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}

					} catch (Exception e) {
						e.printStackTrace();
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								TextView label = (TextView) findViewById(R.id.textView1);
								label.setText("Não foi possível conectar ao servidor.");
							}
						});
						title = "Desconhecido";
						subtitle = "Clique aqui para identificar seu cupom!";
						icon = "none";
						address = "none";
						status = "false";
					}

					finally {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								TextView label = (TextView) findViewById(R.id.textView1);
								label.setText("Verificando dados...");
							}
						});
						try {
								fOut = openFileOutput("sublime_kf.txt",
										Context.MODE_APPEND);
								OutputStreamWriter osw = new OutputStreamWriter(fOut);

								osw.write(stringId);
								osw.write('\n');
								osw.flush();
								osw.close();

								fOut = openFileOutput("sublime_if.txt",
										Context.MODE_APPEND);
								osw = new OutputStreamWriter(fOut);

								osw.write(stringId);
								osw.write('\n');
								osw.write(title);
								osw.write('\n');
								osw.write(subtitle);
								osw.write('\n');
								osw.write(icon);
								osw.write('\n');
								osw.write(address);
								osw.write('\n');
								osw.write(status);
								osw.write('\n');
								osw.flush();
								osw.close();
														
						} catch (IOException ioe) {
							ioe.printStackTrace();
						}
					}

				}
			}

			return null;
		}

		@Override
		protected void onPreExecute() {
			TextView label = (TextView) findViewById(R.id.textView1);
			label.setText("Obtendo dados...");
		}

		@Override
		protected void onPostExecute(String result) {
			Intent i = new Intent(LoadingActivity.this, PromoListActivity.class);
			i.putExtra("repeated", repeated);
			startActivity(i);
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loading, menu);
		return true;
	}

}
