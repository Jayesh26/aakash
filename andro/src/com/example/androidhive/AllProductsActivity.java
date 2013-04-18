package com.example.androidhive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class AllProductsActivity extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	
	// Creating JSON Parser object
	JSONParser jsonParser = new JSONParser();

	ArrayList<HashMap<String, String>> productsList;

	// url to get all products list
	private static String url_all_products = "http://10.0.2.2/android_connect/get_all_products.php";
	private static String url_create_product = "http://10.0.2.2/android_connect/create_product.php";


	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCTS = "products";
	private static final String TAG_BOOKID = "bookid";
	private static final String TAG_COPYS = "copys";
	private static final String TAG_BOOKNAME = "bookname";
	private static final String TAG_DESC = "description";
	// products JSONArray
	JSONArray products = null;

	String bookname;
	int success;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_products);

		//get the value of name
		Intent i = getIntent();
		bookname = i.getStringExtra(TAG_BOOKNAME);
		
		
		// Hashmap for ListView
		productsList = new ArrayList<HashMap<String, String>>();

		// Loading products in Background Thread
		new LoadAllProducts().execute();
	

		// Get listview
		ListView lv = getListView();

		// on seleting single product
		// launching Edit Product Screen
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
	//			String pid = ((TextView) view.findViewById(R.id.pid)).getText()
	//					.toString();

				// Starting new intent
				Intent in = new Intent(getApplicationContext(),
						EditProductActivity.class);
				// sending pid to next activity
			//	in.putExtra(TAG_PID, pid);
				
				// starting new activity and expecting some response back
				startActivityForResult(in, 100);
			}
		});

	}

	// Response from Edit Product Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// if result code 100
		if (resultCode == 100) {
			// if result code 100 is received 
			// means user edited/deleted product
			// reload this screen again
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}

	}

	
	
	class LoadAllProducts extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AllProductsActivity.this);
			pDialog.setMessage("Loading products. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			// getting JSON string from URL
			params.add(new BasicNameValuePair("bookname", bookname));
			
			JSONObject json = jsonParser.makeHttpRequest(url_create_product,
					"GET", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					products = json.getJSONArray(TAG_PRODUCTS);

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						String bookid = c.getString(TAG_BOOKID);
						String bookname = c.getString(TAG_BOOKNAME);
						String desc = c.getString(TAG_DESC);
						int p = c.getInt(TAG_COPYS);
						String copys = Integer.toString(p);
					
						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_BOOKID, bookid);
			     		map.put(TAG_BOOKNAME, bookname);
			     		map.put(TAG_DESC,desc);
			     		map.put(TAG_COPYS, copys);

						// adding HashList to ArrayList
						productsList.add(map);
					}
				} else {
				
					// no products found
					// Launch Add New product Activity
					Intent i = new Intent(getApplicationContext(),
							NewProductActivity.class);
					// Closing all previous activities
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
		
					ListAdapter adapter = new SimpleAdapter(
							AllProductsActivity.this, productsList,
				R.layout.list_item, new String[] { TAG_BOOKID,TAG_COPYS,
									TAG_BOOKNAME,TAG_DESC},
							new int[] { R.id.pid,R.id.copys, R.id.name,R.id.descr});
					// updating listview
					setListAdapter(adapter);
					
					Toast.makeText(AllProductsActivity.this,"done!",2000).show();
				}
			});

		}
			
			
	}
}
