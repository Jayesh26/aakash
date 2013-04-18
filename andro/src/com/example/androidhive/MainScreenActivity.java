package com.example.androidhive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainScreenActivity extends Activity{
	
	Button btnViewProducts;
	Button btnNewProduct;
	Button btnEditProduct;
	Button btnSearchProduct;
	EditText p_name;
//	@Override
	private static final String TAG_NAME = "name";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		
		// Buttons
		btnViewProducts = (Button) findViewById(R.id.btnViewProducts);
		btnNewProduct = (Button) findViewById(R.id.btnCreateProduct);
		btnEditProduct = (Button) findViewById(R.id.btnEditProduct);
		btnSearchProduct = (Button) findViewById(R.id.btnSearchProduct);
		

		// Search products click event
				btnSearchProduct.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View view) {
						// Launching All products Activity
						
						String name = ((TextView) findViewById(R.id.product_name)).getText().toString();

						// Starting new intent
						Intent in = new Intent(getApplicationContext(),
								Get_product.class);
						// sending name to next activity
						in.putExtra(TAG_NAME, name);
						startActivity(in);
						
					}
				});
		
		
		
	
		// view products click event
		btnViewProducts.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching All products Activity
				Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
				startActivity(i);
				
			}
		});
		
		// Edit products click event
				btnEditProduct.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View view) {
						// Launching All products Activity
						Intent i = new Intent(getApplicationContext(), EditProductActivity.class);
						startActivity(i);
						
					}
				});
				
		// new products click event
		btnNewProduct.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching create new product activity
				Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
				startActivity(i);
				
			}
		});
	}
}
