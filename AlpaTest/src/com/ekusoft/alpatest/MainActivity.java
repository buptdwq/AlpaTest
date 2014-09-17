package com.ekusoft.alpatest;




import android.support.v7.app.ActionBarActivity;

import android.support.v4.app.Fragment;

import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;



public class MainActivity extends ActionBarActivity implements View.OnClickListener
{
	public final String LOG_TAG ="AlpaTest MainActivity";
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		if (savedInstanceState == null)
//		{
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
		
		
        final Button buttonSetup = (Button)findViewById(R.id.buttonService); 
        buttonSetup.setOnClickListener(this);
        
        findViewById(R.id.buttonBaiduSch).setOnClickListener(this); 

        findViewById(R.id.buttonTableTest).setOnClickListener(this);
        findViewById(R.id.ButtonFragment).setOnClickListener(this);
        
   
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment
	{

		public PlaceholderFragment()
		{
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if ( v.getId() == R.id.buttonService) {
			startActivity( new Intent( MainActivity.this, ServiceActivityTest.class ));
		}
		else if ( v.getId() == R.id.buttonBaiduSch) {
			startActivity( new Intent( MainActivity.this, SearchTagsActivity.class ));
		}
		else if ( v.getId() == R.id.ButtonFragment) {
			startActivity( new Intent( MainActivity.this, FragmentTestActivity.class ));
		}
		
		SensorManager seManager = ( SensorManager)getSystemService("SENSOR_SERVICE");
		
	}



}
