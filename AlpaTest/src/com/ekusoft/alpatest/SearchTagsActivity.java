package com.ekusoft.alpatest;

import java.util.Arrays;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

public class SearchTagsActivity extends Activity {

	public SearchTagsActivity() {
		// TODO Auto-generated constructor stub
	}
	
	private SharedPreferences saveSearches;
	private TableLayout queryTableLayout;
	private EditText queryEditText;
	private EditText tagEditText;
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tags);
        
        saveSearches = getSharedPreferences("searches", MODE_PRIVATE);
        
        queryTableLayout = ( TableLayout)findViewById( R.id.queryTableLayout);
        
        //
        queryEditText = ( EditText)findViewById(R.id.queryEditText);
        tagEditText = ( EditText)findViewById(R.id.tagEditText);
        
        Button saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener( saveButtonListener);

        Button clearTagsButton = (Button)findViewById(R.id.clearTagsButton);
        clearTagsButton.setOnClickListener( clearTagsButtonListener);
        
        refreshButtons(null);
    }

    private void refreshButtons( String newTag)
    {
    	String[] tags= saveSearches.getAll().keySet().toArray( new String[0]);
    	
    	Arrays.sort( tags, String.CASE_INSENSITIVE_ORDER);
    	
    	if ( newTag != null)
		{
			makeTagGui( newTag, Arrays.binarySearch(tags, newTag));
		}
    	else 
    	{
    		for (int i = 0; i < tags.length; i++)
			{
				makeTagGui( tags[i], i);
			}
		}
    }

    private void makeTag( String query, String tag)
    {
    	String originalQuery= saveSearches.getString(tag, null);
    	
    	SharedPreferences.Editor preferencesEditor = saveSearches.edit();
    	preferencesEditor.putString(tag, query);
    	preferencesEditor.apply();
    	
    	if ( originalQuery == null)
		{
			refreshButtons( tag);
		}
    }
    
    private void makeTagGui( String tag, int index)
    {
    	LayoutInflater inflater = ( LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	View newTagView = inflater.inflate( R.layout.new_tag_view, null);
    	
    	Button newTagButton=
    			(Button)newTagView.findViewById( R.id.newTagButton);
    	
    	newTagButton.setText(tag);
    	newTagButton.setOnClickListener(queryButtonListener);
    	
    	Button newEditButton =
    			( Button)newTagView.findViewById( R.id.newEditButton);
    	
    	newEditButton.setOnClickListener( editButtonListener);
    	
    	queryTableLayout.addView( newTagView, index);
    }
    
    private void clearButtons()
    {
    	queryTableLayout.removeAllViews();
    }
    
    public OnClickListener saveButtonListener = new OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
			if ( queryEditText.getText().length() > 0 && 
				 tagEditText.getText().length() > 0)
			{
				makeTag( queryEditText.getText().toString(), tagEditText.getText().toString());
				queryEditText.setText("");
				tagEditText.setText("");
				
				InputMethodManager manager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				manager.hideSoftInputFromWindow( tagEditText.getWindowToken(), 0);
				
			}
			else
			{
				AlertDialog.Builder builder = new AlertDialog.Builder( SearchTagsActivity.this);
				
				builder.setTitle(R.string.missingTitle);
				builder.setPositiveButton(R.string.OK, null );
				builder.setMessage(R.string.missingMessage);
				
				AlertDialog errorDialog = builder.create();
				errorDialog.show();
			}
		}
    	
	};
	
	public OnClickListener clearTagsButtonListener = new OnClickListener()
	{
		
		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
			AlertDialog.Builder builder = new AlertDialog.Builder( SearchTagsActivity.this);
			
			builder.setTitle(R.string.confirmTitle);
			builder.setPositiveButton( R.string.erase, new DialogInterface.OnClickListener()
			{
				
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// TODO Auto-generated method stub
					clearButtons();
					
					SharedPreferences.Editor preEditor = saveSearches.edit();
					preEditor.clear();
					preEditor.apply();
					
				}
			});
			
			builder.setCancelable( true);
			builder.setNegativeButton(R.string.cancel, null);
			
			builder.setMessage( R.string.confirmMessage);
			AlertDialog confirmDialog = builder.create();
			confirmDialog.show();
			
		}
	};
    
	
	public OnClickListener queryButtonListener = new OnClickListener()
	{
		
		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
			String buttonText = ((Button)v).getText().toString();
			String query = saveSearches.getString( buttonText, null );
			
			String urlString = getString( R.string.searchURL) +query;
			Intent getUrl = new Intent( Intent.ACTION_VIEW, Uri.parse(urlString));
			
			startActivity(getUrl);
		}
	};
	
	public OnClickListener editButtonListener = new OnClickListener()
	{
		
		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
			TableRow buttonTableRow = (TableRow)v.getParent();
			Button searchButton = (Button)buttonTableRow.findViewById( R.id.newTagButton);
			
			String tag = searchButton.getText().toString();
			
			tagEditText.setText( tag);
			queryEditText.setText( saveSearches.getString(tag, null));
			
		}
	};
    
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


}
