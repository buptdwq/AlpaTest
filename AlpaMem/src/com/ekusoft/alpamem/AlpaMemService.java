package com.ekusoft.alpamem;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AlpaMemService extends Service {
	
	private final static String TAG = "AlpaMemService";  
	
	public AlpaMemService() {
		// TODO Auto-generated constructor stub
	}
	
	private IAlpaMemService.Stub binder = new IAlpaMemService.Stub() {

		@Override
		public void setMcuCmd(int nCmdIndex, BytesCmd cmd, int nLen)
				throws RemoteException {
			// TODO Auto-generated method stub
			byte[] data = cmd.get_byte();
			Mtc100.mSetVar8( nCmdIndex, data, data.length);
			
		}

		@Override
		public BytesCmd getMcuCmd(int nCmdIndex) throws RemoteException {
			// TODO Auto-generated method stub
			android.os.Debug.waitForDebugger();
			int nRet = Mtc100.mGetVar8( nCmdIndex, null);
			
			BytesCmd cmd = new BytesCmd();
			
			if ( nRet == 0 ) {
				cmd.set_byte( null);
			}
			else {
				byte[] data = new byte[nRet];
				Mtc100.mGetVar8( nCmdIndex, data);
				cmd.set_byte(data);
			}
			
			return cmd;
		}

		@Override
		public void setStrValue(int nStrIndex, String str)
				throws RemoteException {
			// TODO Auto-generated method stub
			Mtc100.mSetVarStr( nStrIndex, str);
		}

		@Override
		public String getStrValue(int nStrIndex) throws RemoteException {
			// TODO Auto-generated method stub
			return Mtc100.mGetVarStr( nStrIndex);
		}

		@Override
		public void setIntValue(int nIntIndex, int value)
				throws RemoteException {
			// TODO Auto-generated method stub
			Mtc100.mSetVarInt( nIntIndex, value);
		}

		@Override
		public int getIntValue(int nIntIndex) throws RemoteException {
			// TODO Auto-generated method stub
			return Mtc100.mGetVarInt( nIntIndex);
		}
		
		
	}; 

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.v( TAG, "AlpaMemService onBind!");
		return binder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.v( TAG, "AlpaMemService onUnBind!");
		return super.onUnbind(intent);
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v( TAG, "AlpaMemService oncreate!");
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v( TAG, "AlpaMemService destroy!");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.v( TAG, "AlpaMemService onStart!");
		return super.onStartCommand(intent, flags, startId);
	}
	

}
