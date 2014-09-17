package com.ekusoft.alpacore;

import com.ekusoft.alpacore.BytesCmd;

interface IAlpaSystemService {

	void setBtInCall( boolean bInCall);
	boolean isBtInCall();

	void setBtConnect( boolean bConnected);
	boolean isBtConnect();
	
	void setSystemVol( int nVol);
	int  getSystemVol();
	
	void setEq( int nType);
	int getEq();
	
	void setMcuCmd( int nCmd, in BytesCmd cmd, in int nLen);
	
	int getMcuCmd( int nCmd, out BytesCmd cmd);
	
	void setMcuVersion( String strVer);
	String getMcuVersion();
	
	void setCanVersion( String strVer);
	String getCanVersion();
	
	String getDvdVersion();
	void setDvdVersion( String strVer);
	
	void setMute( boolean mute);
	boolean isMute();	
	
	void setLoudness( boolean bLoud);
	boolean isLoudNess();
	
	 int getSourceInfo();
	 int getFrontSource();
	 int getRearSource();
	 int getLastFrontSource();
	 int getLastRearSource();
	 int getChangeSourceReason();
	 
	 void sendMcuCmd( in BytesCmd cmd, in int nLen);
	 
	
}
