package com.ekusoft.alpatest;

import com.ekusoft.alpatest.DataLoaderFragment.ProgressListener;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class FragmentTestActivity extends Activity implements ProgressListener 
{

	private static final String TAG_DATA_LOADER = "dataLoader";
	private static final String TAG_SPLASH_SCREEN = "spalshScreen";
	private DataLoaderFragment mDataLoaderFragment;
	private SplashScreenFragment mSplashScreenFragment;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);
        
        final FragmentManager fm = getFragmentManager();
        mDataLoaderFragment = (DataLoaderFragment)fm.findFragmentByTag(TAG_DATA_LOADER);
        
        if ( mDataLoaderFragment == null) 
        {
			mDataLoaderFragment = new DataLoaderFragment();
			mDataLoaderFragment.setProgressListener(this);
			mDataLoaderFragment.startLoading();
			
			fm.beginTransaction().add(mDataLoaderFragment, TAG_DATA_LOADER).commit();
		
		}
        else 
        {
        	if ( checkCompletionStatus()) 
        	{
				return;
			}
		}
        
        mSplashScreenFragment = (SplashScreenFragment)fm.findFragmentByTag(TAG_SPLASH_SCREEN);
        if ( mSplashScreenFragment == null) 
        {
        	mSplashScreenFragment = new SplashScreenFragment();
        	fm.beginTransaction().add(android.R.id.content ,mSplashScreenFragment, TAG_SPLASH_SCREEN).commit();
		}
        
        
    }
    
    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
    	if ( mDataLoaderFragment != null) 
    	{
    		checkCompletionStatus();
		}
    }
    
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();
    	if ( mDataLoaderFragment != null) 
    	{
			mDataLoaderFragment.removeProgressListener();
		}
    }
    
    private boolean checkCompletionStatus() 
    {
    	if ( mDataLoaderFragment.hasResult()) 
    	{
			onCompletion(mDataLoaderFragment.getResult());
			FragmentManager fm = getFragmentManager();
			mSplashScreenFragment = (SplashScreenFragment)fm.findFragmentByTag(TAG_SPLASH_SCREEN);
			if (mSplashScreenFragment != null) 
			{
				fm.beginTransaction().remove(mSplashScreenFragment).commit();
			}
			
			return true;
		}
    	
    	mDataLoaderFragment.setProgressListener(this);
    	return false;
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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


	@Override
	public void onCompletion(Double result) 
	{
		// TODO Auto-generated method stub
		TextView textView = new TextView(this);
		textView.setText(String.valueOf(result));
		setContentView(textView);
	
		mDataLoaderFragment = null;
	}


	@Override
	public void onProgressUpdate( int progress) 
	{
		// TODO Auto-generated method stub
		
		mSplashScreenFragment.setProgress( progress);
		
		
	}
}
