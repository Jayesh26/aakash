package com.example.androidhive;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewProductActivity extends Activity {

	// Progress Dialog
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	EditText inputName;
	EditText inputPrice;
	EditText inputDesc;
	int success;
	// url to create new product
	private static String url_create_product = "http://10.0.2.2/android_connect/create_product.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_BOOKNAME = "bookname";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_product);

		// Edit Text
		inputName = (EditText) findViewById(R.id.inputName);

		// Create button
		Button btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);

		// button click event
		btnCreateProduct.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// creating new product in background thread
				new CreateNewProduct().execute();
			}
		});
	}

	/**
	 * Background Async Task to Create new product
	 * */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NewProductActivity.this);
			pDialog.setMessage("Searching Product..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			String bookname = inputName.getText().toString();
			
			

			// Starting new intent
			Intent in = new Intent(getApplicationContext(),
					AllProductsActivity.class);
			// sending name to next activity
			in.putExtra(TAG_BOOKNAME, bookname);
			startActivity(in);
			finish();
			// Building Parameters
		/*	List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", name));
	
			JSONObject json = jsonParser.makeHttpRequest(url_create_product,
					"POST", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				success = json.getInt(TAG_SUCCESS);

			} catch (JSONException e) {
				e.printStackTrace();
			}
*/
			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		/*	runOnUiThread(new Runnable() {
				public void run() {
				
					if (success == 1) 
						Toast.makeText(NewProductActivity.this, "success!!!", 2000).show();
					
				
		}

	});*/
}
}
}