package com.ekusoft.alpamem;

import android.os.RemoteException;

import com.ekusoft.alpamem.BytesCmd;


//This is the wrapper class called by the other process

public class CmShareData {


	//this group define must less than (MAX_NUM_OF_SHARE_BYTE_ITEM_COUNT=512)
	public static final int  CmdType_0x81 = 0;
	public static final int  CmdType_0x82 = 1;
	public static final int  CmdType_DevStatusCmd_0x83 = 2;
	public static final int  CmdType_McuCfg_0x84 = 3;
	public static final int  CmdType_Radio_0x8A_02 = 4;
	public static final int  CmdType_Radio_0x8A_03 = 5;
	public static final int  CmdType_Radio_0x8A_04 = 6;
	public static final int  CmdType_VOL_0x8C_0x01 = 7;
	
	//this gourp define must less than ( MAX_NUM_OF_SHARE_DATA_INT = 512)
	public static final int  INT_INDEX_BT_IN_CALL = 0;
	public static final int  INT_INDEX_BT_CONNECT = 1;
	public static final int  INT_INDEX_SYSTEM_VOL = 2;
	public static final int  INT_INDEX_EQ_TYPE = 3;
	public static final int  INT_INDEX_SYSTEM_MUTE = 4;
	public static final int  INT_INDEX_EQ_LOUDNESS = 5;
	public static final int  INT_INDEX_SOURCE_INFO = 6;
	public static final int  INT_INDEX_VIDEO_CAUTION = 7;
	
	//this group define must less than (MAX_NUM_OF_SHARE_DATA_STR = 128)
	public static final int STRING_INDEX_MCU_VERSION = 0;
	public static final int STRING_INDEX_DVD_VERSION = 1;
	public static final int STRING_INDEX_CAN_VERSION = 2;

	public static final int INVALID_RETURN_VALUE = 0xFFFFFFFF; 
	
	private static IAlpaMemService memService = null;
	
	public CmShareData( ) {
		// TODO Auto-generated constructor stub
		
	}
	
	public static void setAlpaMemService( IAlpaMemService service) {
		memService = service;
	}
	
	public static void setSystemVol(int nVol) throws RemoteException 
	{
		// TODO Auto-generated method stub
		if ( memService != null) {
			memService.setIntValue( INT_INDEX_SYSTEM_VOL, nVol);
		}
	}
	
	public static int getSystemVol() throws RemoteException 
	{
		// TODO Auto-generated method stub
		if ( memService != null) {
			return memService.getIntValue( INT_INDEX_SYSTEM_VOL);
		}
		
		return INVALID_RETURN_VALUE;
	}
	
	public static void setMcuVersion(String strVer) throws RemoteException 
	{
		// TODO Auto-generated method stub
		if ( memService != null) {
			memService.setStrValue( STRING_INDEX_MCU_VERSION, strVer);
		}
	}
	
	public static void setMcuCmd(int nCmd, byte[] cmd, int nLen) throws RemoteException
	{
		// TODO Auto-generated method stub
		// set the mcu data
		if ( memService != null) {
			
			BytesCmd bytesCmd = new BytesCmd();
			bytesCmd.set_byte(cmd);
			memService.setMcuCmd(nCmd, bytesCmd, nLen);
		}
	}

	
	public static void setEq(int nType)  throws RemoteException
	{
		// TODO Auto-generated method stub
		if ( memService != null) {
			memService.setIntValue( INT_INDEX_EQ_TYPE, nType);
		}
	}
	
	public static void setDvdVersion(String strVer)   throws RemoteException
	{
		// TODO Auto-generated method stub
		if ( memService != null) {
			memService.setStrValue( STRING_INDEX_DVD_VERSION, strVer);
		}
	}
	
	public static void setCanVersion(String strVer)   throws RemoteException
	{
		// TODO Auto-generated method stub
		if ( memService != null) {
			memService.setStrValue( STRING_INDEX_CAN_VERSION, strVer);
		}
	}
	
	
	public static void setBtInCall(boolean bInCall)   throws RemoteException
	{
		// TODO Auto-generated method stub
		if ( memService != null) {
			memService.setIntValue( INT_INDEX_BT_IN_CALL, (bInCall == true)? 1 :0);
		}
	}
	
	public static void setBtConnect(boolean bConnected)   throws RemoteException
    {
		// TODO Auto-generated method stub
		if ( memService != null) {
			memService.setIntValue( INT_INDEX_BT_CONNECT, ( bConnected == true)? 1 :0);
		}
	}
	
	public static boolean isBtInCall()  throws RemoteException
	{
		// TODO Auto-generated method stub
		if ( memService != null) {
			int n = memService.getIntValue( INT_INDEX_BT_IN_CALL);
			return (n == 0 ? false:true);
		}
		
		return false;
	}
	
	public static boolean isBtConnect()  throws RemoteException
	{
		// TODO Auto-generated method stub
		if ( memService != null) {
			int n = memService.getIntValue( INT_INDEX_BT_CONNECT);
			return (n == 0 ? false:true);
		}
		
		return false;
	}
	
	
	

	public static String getMcuVersion()   throws RemoteException
	{
		// TODO Auto-generated method stub
		if ( memService != null) {
			return memService.getStrValue(STRING_INDEX_MCU_VERSION);
		}
		
		return "";
	}
	
	
	public static int getMcuCmd(int nCmd, byte[] cmd)   throws RemoteException
	{
		// TODO Auto-generated method stub
		if ( memService != null ) {
			

			BytesCmd cmd1 = memService.getMcuCmd( nCmd);
			byte[] theData = cmd1.get_byte();
			
			
			if ( theData == null) {
				return 0;
			}
			else {
				cmd = theData;
				return cmd.length;
			}
		}
		
		return INVALID_RETURN_VALUE;
	}
	
	public static int getEq()  throws RemoteException
	{
		// TODO Auto-generated method stub
		if ( memService != null) {
			return memService.getIntValue( INT_INDEX_EQ_TYPE);
		}
		
		return INVALID_RETURN_VALUE;
	}
	
	public static String getDvdVersion()   throws RemoteException
	{
		// TODO Auto-generated method stub
		if ( memService != null) {
			return memService.getStrValue( STRING_INDEX_DVD_VERSION);
		}
		
		return "";
	}
	
	public static String getCanVersion()   throws RemoteException
	{
		// TODO Auto-generated method stub
		if ( memService != null) {
			return memService.getStrValue( STRING_INDEX_CAN_VERSION);
		}
		
		return "";
	}
	
	public static void setMute( boolean mute)  throws RemoteException
	{
		if ( memService != null) {
			memService.setIntValue( INT_INDEX_SYSTEM_MUTE, (  mute ? 1:0));
		}
	}
	
	public static boolean isMute()   throws RemoteException
	{
		if ( memService != null ) {
			int n = memService.getIntValue( INT_INDEX_SYSTEM_MUTE);
			return ( n == 1 ? true:false);
		}
		
		return false;
	}
	
	public static void setLoudness( boolean bLoud)  throws RemoteException
	{
		if ( memService != null ) {
			memService.setIntValue( INT_INDEX_EQ_LOUDNESS, (  bLoud ? 1:0));
		}
	}
	
	public static boolean isLoudNess() throws RemoteException
	{
		if ( memService != null ) {
			int n = memService.getIntValue( INT_INDEX_EQ_LOUDNESS);
			return ( n == 1 ? true:false);
		}
		
		return false;
	}
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////共享源 -- 开始////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	public static int  setSourceInfo(byte frontSource, byte rearSource, byte reason) throws RemoteException
	{
		if ( memService != null) {
			int nSourceInfo = memService.getIntValue( INT_INDEX_SOURCE_INFO);
			//存储格式:
			//bit0~3  : reason
			//bit4~10 : current front source
			//bit11~17: current rear source
			//bit18~24: last front source
			//bit25~31: last rear  source

			int nNewSourceInfo = ( (nSourceInfo << 14) & 0xFFFC0000 ) | ( (rearSource & 0x7F) << 11 ) | ( (frontSource & 0x7F) << 4 ) | reason;
			
			memService.setIntValue( INT_INDEX_SOURCE_INFO, nNewSourceInfo);
			return nNewSourceInfo;			
		}

		return 0;
	}
	
	public static int  getSourceInfo() throws RemoteException
	{
		if ( memService != null) {
			return memService.getIntValue( INT_INDEX_SOURCE_INFO);
		}
		
		return 0;
	}
	
	public static int getFrontSource() throws RemoteException
	{
		int nSourceInfo = getSourceInfo();
		
		byte bData =  (byte)( (nSourceInfo >> 4) & 0x7F );
		
		return bData;
	}
	
	public static int getRearSource() throws RemoteException
	{
		int nSourceInfo = getSourceInfo();
		
		byte bData =  (byte)( (nSourceInfo >> 11) & 0x7F );
		
		return bData;
	}
	
	public static int getLastFrontSource() throws RemoteException
	{
		int nSourceInfo = getSourceInfo();
		
		byte bData =  (byte)( (nSourceInfo >> 18) & 0x7F );
		
		return bData;
	}
	
	public static int getLastRearSource() throws RemoteException
	{
		int nSourceInfo = getSourceInfo();
		
		byte bData =  (byte)( (nSourceInfo >> 25) & 0x7F );
		
		return bData;
	}
	
	public static int getChangeSourceReason() throws RemoteException
	{
		int nSourceInfo = getSourceInfo();
		return (nSourceInfo & 0x0F);
	}
	//////////////////////////////////////////////////////////////////////////
	////////////////////////共享源 -- 结束////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	public static void setEnableVideoCaution( boolean bEnable) throws RemoteException
	{
		if ( memService != null) {
			memService.setIntValue( INT_INDEX_VIDEO_CAUTION, bEnable? 1:0);
		}
	}
	
	public static boolean getEnableVideoCaution()  throws RemoteException
	{
		if ( memService != null ) {
			int  n= memService.getIntValue( INT_INDEX_VIDEO_CAUTION);
			return ( n == 1 ? true:false);
		}
		
		return false;
	}

	
}
