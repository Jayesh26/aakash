package com.example.androidhive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Get_product extends Activity {

		String name;
		int success;

	private ProgressDialog pDialog;


	JSONParser jsonParser = new JSONParser();

	private static final String url_product_details = "http://10.0.2.2/android_connect/get_product_details.php";
//	private static String url_all_products = "http://10.0.2.2/android_connect/get_all_products.php";


	private static final String TAG_NAME = "name";
	private static final String TAG_SUCCESS = "success";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_product);

		
		Intent i = getIntent();
		name = i.getStringExtra(TAG_NAME);
	//	int success;
	/*	try {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", name));

			// getting product details by making HTTP request
			// Note that product details url will use GET request
			JSONObject json = jsonParser.makeHttpRequest(
					url_product_details, "GET", params);

			// check your log for json response
			Log.d("Single Product Details", json.toString());
			
			// json success tag
			success = json.getInt(TAG_SUCCESS);
			if (success == 1) {
					Toast.makeText(Get_product.this, "success!!!", 2000).show();
			}
			else
				Toast.makeText(Get_product.this, "failure", 2000).show();
			
		}catch (JSONException e) {
				e.printStackTrace();
			} */
		new GetProductDetails().execute();
		
	}

		
	class GetProductDetails extends AsyncTask<String, String, String> {

	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(Get_product.this);
		pDialog.setMessage("Loading product details. Please wait...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
	}
	
	protected String doInBackground(String... params) {

		// updating UI from Background Thread
		runOnUiThread(new Runnable() {
			public void run() {
				// Check for success tag
				
			
					// Building Parameters
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("name", name));
					System.out.println("I am here");
					// getting product details by making HTTP request
					// Note that product details url will use GET request
					JSONObject json = jsonParser.makeHttpRequest(url_product_details, "GET", params);
					
					// check your log for json response
					Log.d("Single Product Details", json.toString());
					
					try{
					// json success tag
					success = json.getInt(TAG_SUCCESS);
					
				}catch (JSONException e) {
						e.printStackTrace();
					}
			}
	
		});
		return null;
	}
}

protected void onPostExecute(String file_url) {
	// dismiss the dialog once got all details
	
	pDialog.dismiss();
	runOnUiThread(new Runnable() {
		public void run() {
		
			if (success == 1) 
				Toast.makeText(Get_product.this, "success!!!", 2000).show();
			
		
	}
	});

	

}
}
