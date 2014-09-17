package com.ekusoft.alpacore;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AlpaSystemService extends Service {
	
	private final String LOG_VER = "AlpaSystemService";
	
	
	private IAlpaSystemService.Stub binder = new IAlpaSystemService.Stub() 
	{
		
		@Override
		public void setSystemVol(int nVol) throws RemoteException 
		{
			// TODO Auto-generated method stub
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			alpaSys.setSystemVol(nVol);
		}
		
		@Override
		public void setMcuVersion(String strVer) throws RemoteException 
		{
			// TODO Auto-generated method stub
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			alpaSys.setMcuVersion(strVer);
		}
		
		@Override
		public void setMcuCmd(int nCmd, BytesCmd cmd, int nLen) throws RemoteException 
		{
			// TODO Auto-generated method stub
			// set the mcu data
			byte[] data = cmd.get_byte();
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			alpaSys.setMcuCmd( nCmd, data, nLen);
		}

		
		@Override
		public void setEq(int nType) throws RemoteException 
		{
			// TODO Auto-generated method stub
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			alpaSys.setEq(nType);
		}
		
		@Override
		public void setDvdVersion(String strVer) throws RemoteException 
		{
			// TODO Auto-generated method stub
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			alpaSys.setDvdVersion(strVer);
		}
		
		@Override
		public void setCanVersion(String strVer) throws RemoteException 
		{
			// TODO Auto-generated method stub
			android.os.Debug.waitForDebugger();
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			alpaSys.setCanVersion(strVer);
		}
		
		@Override
		public void setBtInCall(boolean bInCall) throws RemoteException 
		{
			// TODO Auto-generated method stub
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			alpaSys.setBtInCall(bInCall);
		}
		
		@Override
		public void setBtConnect(boolean bConnected) throws RemoteException 
		{
			// TODO Auto-generated method stub
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			alpaSys.setBtConnect(bConnected);
		}
		
		@Override
		public boolean isBtInCall() throws RemoteException 
		{
			// TODO Auto-generated method stub
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.isBtInCall();
		}
		
		@Override
		public boolean isBtConnect() throws RemoteException 
		{
			// TODO Auto-generated method stub
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.isBtConnect();
		}
		
		@Override
		public int getSystemVol() throws RemoteException 
		{
			// TODO Auto-generated method stub
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.getSystemVol();
		}
		
		@Override
		public String getMcuVersion() throws RemoteException {
			// TODO Auto-generated method stub
			android.os.Debug.waitForDebugger();
			
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			String str = alpaSys.getMcuVersion();
			
			Log.v(LOG_VER, "getMcuVersion111 = "+str);
			
			//return alpaSys.getMcuVersion();
			return "xxx";
		}
		
		@Override
		public int getMcuCmd(int nCmd, BytesCmd cmd) throws RemoteException 
		{
			// TODO Auto-generated method stub
			byte[] data = cmd.get_byte();
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.getMcuCmd( nCmd, data);
		}
		
		@Override
		public int getEq() throws RemoteException 
		{
			// TODO Auto-generated method stub
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.getEq();
		}
		
		@Override
		public String getDvdVersion() throws RemoteException 
		{
			// TODO Auto-generated method stub
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.getDvdVersion();
		}
		
		@Override
		public String getCanVersion() throws RemoteException 
		{
			// TODO Auto-generated method stub
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.getCanVersion();
		}
		
		@Override
		public void setMute( boolean mute) throws RemoteException
		{
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			alpaSys.setMute(mute);
		}
		
		@Override
		public boolean isMute() throws RemoteException
		{
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.isMute();
		}
		
		@Override
		public void setLoudness( boolean bLoud) throws RemoteException
		{
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			alpaSys.setLoudness(bLoud);
		}
		
		@Override
		public boolean isLoudNess() throws RemoteException
		{
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.isLoudNess();
		}
		
		@Override
		public int getSourceInfo() throws RemoteException
		{
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.getSourceInfo();
		}
		
		@Override
		public int getFrontSource() throws RemoteException
		{
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.getFrontSource().getValue();
		}
		
		@Override
		public int getRearSource() throws RemoteException
		{
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.getRearSource().getValue();
		}
		
		public int getLastFrontSource() throws RemoteException
		{
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.getLastFrontSource().getValue();
		}
		
		@Override
		public int getLastRearSource() throws RemoteException
		{
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.getLastRearSource().getValue();
		}
		
		@Override
		public int getChangeSourceReason() throws RemoteException
		{
			AlpaSystem alpaSys = AlpaSystem.getInstance();
			return alpaSys.getChangeSourceReason();
		}



		@Override
		public void sendMcuCmd(BytesCmd cmd, int nLen) throws RemoteException
		{
			// TODO Auto-generated method stub
			
		}




		
	};
	
	public void onCreate() 
	{
		Log.v("AlpaSystemService", "OnCreate");
		super.onCreate();
		
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.v("AlpaSystemService", "OnBind");
		return binder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}
	
}
