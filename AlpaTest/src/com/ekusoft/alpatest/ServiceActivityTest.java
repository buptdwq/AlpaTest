package com.ekusoft.alpatest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ekusoft.alpacore.IAlpaSystemService;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceActivityTest extends Activity
{
	public final String LOG_TAG = "ServiceActivityTest";
	
	private ListView logView  = null;
	
	private IAlpaSystemService mAlpaSystemService = null;
	
	private ServiceConnection mConnection = new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			
			mAlpaSystemService = IAlpaSystemService.Stub.asInterface(service);			
			try
			{
				String str = mAlpaSystemService.getCanVersion();
				Log.v(LOG_TAG, "GetCanVersion"+str);
			}
			catch(RemoteException e){
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			mAlpaSystemService = null;
		}
		
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);

		logView = (ListView)findViewById(R.id.listView1);
		
        final Button buttonBind = (Button)findViewById(R.id.ButtonBind);
        buttonBind.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				Intent intent = new Intent();
				intent.setAction("com.ekusoft.alpacore.action.AlpaSystemService");
				bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
			}
		});
        
        final Button buttonUnBind = (Button)findViewById(R.id.ButtonUnBind);
        buttonUnBind.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				
			}
		});       
  
        final Button buttonGetApi = (Button)findViewById(R.id.ButtonGetApi);
        buttonGetApi.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{			
				try
				{
					String str = mAlpaSystemService.getMcuVersion();
					Log.v(LOG_TAG, "GetMcuVersion"+str);
				}
				catch(RemoteException e)
				{
					e.printStackTrace();
				}
			}
		});       
                
        
        
        
        final Button buttonList = (Button)findViewById(R.id.buttonList);
        buttonList.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				boolean serviceRunning = false;
				String serviceName = "";
				
				ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
				
				List<ActivityManager.RunningServiceInfo> l = am.getRunningServices(50);
				
				Iterator<ActivityManager.RunningServiceInfo> i = l.iterator();
				
				
				 List<String> data = new ArrayList<String>();
				 
				while (i.hasNext()) 
				{
				    ActivityManager.RunningServiceInfo runningServiceInfo = (ActivityManager.RunningServiceInfo) i.next();
				
				    Log.i("superContacts", "检查到的服务:"+runningServiceInfo.service.getClassName());
				    String str = "Service Activity 检查到的服务:"+runningServiceInfo.service.getClassName();
				    
				    data.add(str);
				    
				    if(runningServiceInfo.service.getClassName().equals(serviceName))
				    {
				
				    	serviceRunning = true;
				    }
				}
				
				logView.setAdapter(new ArrayAdapter<String>( getBaseContext(), android.R.layout.simple_expandable_list_item_1, data));
				
			}
		});  
        
        
	
	}

}
