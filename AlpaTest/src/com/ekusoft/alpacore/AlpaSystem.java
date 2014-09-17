package com.ekusoft.alpacore;

import android.os.RemoteException;
import android.util.Log;

class AlpaSystem
{
	private static AlpaSystem alpaSystem ;
    
	static final int   CHANGE_SOURCE_REASON_NO_REASON = 0;
	static final int   CHANGE_SOURCE_REASON_BT_INCALL = 1;
    
	public static AlpaSystem getInstance()
	{
		//android.os.Debug.waitForDebugger();
	
	
       if (alpaSystem == null) 
       {
    	   alpaSystem = new AlpaSystem();
       }
       
       Log.v("AlapSystem ", "getInstance" + alpaSystem.toString());
       
       return alpaSystem;	
	}
	
	public void setSystemVol(int nVol) 
	{
		// TODO Auto-generated method stub
		Mtc100.mSetVarInt(Mtc100.DataIndexInt.SYSTEM_VOL, nVol);
	}
	
	public void setMcuVersion(String strVer) 
	{
		// TODO Auto-generated method stub
		Mtc100.mSetVarStr(Mtc100.DataIndexStr.MCU_VERSION, strVer);
	}
	
	public void setMcuCmd(int nCmd, byte[] cmd, int nLen)
	{
		// TODO Auto-generated method stub
		// set the mcu data
		Mtc100.mSetVar8(Mtc100.CmdType.values()[nCmd], cmd, nLen);	
	}

	
	public void setEq(int nType)  
	{
		// TODO Auto-generated method stub
		Mtc100.mSetVarInt(Mtc100.DataIndexInt.EQ_TYPE, nType);
	}
	
	public void setDvdVersion(String strVer) 
	{
		// TODO Auto-generated method stub
		Mtc100.mSetVarStr(Mtc100.DataIndexStr.DVD_VERSION, strVer);
	}
	
	public void setCanVersion(String strVer) 
	{
		// TODO Auto-generated method stub
		Mtc100.mSetVarStr(Mtc100.DataIndexStr.CAN_VERSION, strVer);
	}
	
	
	public void setBtInCall(boolean bInCall) 
	{
		// TODO Auto-generated method stub
		Mtc100.mSetVarInt(Mtc100.DataIndexInt.BT_IN_CALL, (bInCall == true)? 1 :0);
	}
	
	public void setBtConnect(boolean bConnected) 
    {
		// TODO Auto-generated method stub
		Mtc100.mSetVarInt(Mtc100.DataIndexInt.BT_CONNECT, (bConnected == true)? 1 :0);
	}
	
	public boolean isBtInCall()
	{
		// TODO Auto-generated method stub
		int n = Mtc100.mGetVarInt(Mtc100.DataIndexInt.BT_IN_CALL);
		return (n == 0 ? false:true);
	}
	
	public boolean isBtConnect()
	{
		// TODO Auto-generated method stub
		int n = Mtc100.mGetVarInt(Mtc100.DataIndexInt.BT_CONNECT);
		return (n == 0 ? false:true);
	}
	
	public int getSystemVol() 
	{
		// TODO Auto-generated method stub
		int n = Mtc100.mGetVarInt(Mtc100.DataIndexInt.SYSTEM_VOL);
		return n;
	}
	

	public String getMcuVersion() 
	{
		// TODO Auto-generated method stub
		return Mtc100.mGetVarStr(Mtc100.DataIndexStr.MCU_VERSION);
	}
	
	
	public int getMcuCmd(int nCmd, byte[] cmd) 
	{
		// TODO Auto-generated method stub
		int nRet = Mtc100.mGetVar8(Mtc100.CmdType.values()[nCmd], cmd, cmd.length);
		return nRet;
	}
	
	public int getEq()
	{
		// TODO Auto-generated method stub
		return Mtc100.mGetVarInt(Mtc100.DataIndexInt.EQ_TYPE);
	}
	
	public String getDvdVersion() 
	{
		// TODO Auto-generated method stub
		return Mtc100.mGetVarStr(Mtc100.DataIndexStr.DVD_VERSION);
	}
	
	public String getCanVersion() 
	{
		// TODO Auto-generated method stub
		return Mtc100.mGetVarStr(Mtc100.DataIndexStr.CAN_VERSION);
	}
	
	public void setMute( boolean mute)
	{
		Mtc100.mSetVarInt(Mtc100.DataIndexInt.SYSTEM_MUTE, (  mute ? 1:0));
	}
	
	public boolean isMute()
	{
		int n = Mtc100.mGetVarInt(Mtc100.DataIndexInt.SYSTEM_MUTE);
		return ( n == 1 ? true:false);
	}
	
	public void setLoudness( boolean bLoud)
	{
		Mtc100.mSetVarInt(Mtc100.DataIndexInt.EQ_LOUDNESS, (  bLoud ? 1:0));
	}
	
	public boolean isLoudNess()
	{
		int n = Mtc100.mGetVarInt(Mtc100.DataIndexInt.EQ_LOUDNESS);
		return ( n == 1 ? true:false);
	}
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////共享源 -- 开始////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	public int  setSourceInfo(byte frontSource, byte rearSource, byte reason)
	{
		int nSourceInfo = Mtc100.mGetVarInt(Mtc100.DataIndexInt.SOURCE_INFO);

		//存储格式:
		//bit0~3  : reason
		//bit4~10 : current front source
		//bit11~17: current rear source
		//bit18~24: last front source
		//bit25~31: last rear  source

		int nNewSourceInfo = ( (nSourceInfo << 14) & 0xFFFC0000 ) | ( (rearSource & 0x7F) << 11 ) | ( (frontSource & 0x7F) << 4 ) | reason;
		Mtc100.mSetVarInt( Mtc100.DataIndexInt.SOURCE_INFO, nNewSourceInfo);
		return nNewSourceInfo;
	}
	
	public int  getSourceInfo()
	{
		return Mtc100.mGetVarInt( Mtc100.DataIndexInt.SOURCE_INFO);
	}
	
	public McuSouceType getFrontSource()
	{
		int nSourceInfo = getSourceInfo();
		
		byte bData =  (byte)( (nSourceInfo >> 4) & 0x7F );
		
		if ( bData == 0xFF)
		{
			return McuSouceType.E_MCU_INVALID_SOURCE;
		}
		else 
		{
			return McuSouceType.values()[(int)bData];
		}
	}
	
	public McuSouceType getRearSource()
	{
		int nSourceInfo = getSourceInfo();
		
		byte bData =  (byte)( (nSourceInfo >> 11) & 0x7F );
		
		if ( bData == 0xFF)
		{
			return McuSouceType.E_MCU_INVALID_SOURCE;
		}
		else 
		{
			return McuSouceType.values()[(int)bData];
		}
	}
	
	public McuSouceType getLastFrontSource()
	{
		int nSourceInfo = getSourceInfo();
		
		byte bData =  (byte)( (nSourceInfo >> 18) & 0x7F );
		
		if ( bData == 0xFF)
		{
			return McuSouceType.E_MCU_INVALID_SOURCE;
		}
		else 
		{
			return McuSouceType.values()[(int)bData];
		}
	}
	
	public McuSouceType getLastRearSource()
	{
		int nSourceInfo = getSourceInfo();
		
		byte bData =  (byte)( (nSourceInfo >> 25) & 0x7F );
		
		if ( bData == 0xFF)
		{
			return McuSouceType.E_MCU_INVALID_SOURCE;
		}
		else 
		{
			return McuSouceType.values()[(int)bData];
		}
	}
	
	public int getChangeSourceReason()
	{
		int nSourceInfo = getSourceInfo();
		return (nSourceInfo & 0x0F);
	}
	//////////////////////////////////////////////////////////////////////////
	////////////////////////共享源 -- 结束////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	public void setEnableVideoCaution( boolean bEnable)
	{
		Mtc100.mSetVarInt(Mtc100.DataIndexInt.VIDEO_CAUTION, bEnable? 1:0);
	}
	
	public boolean getEnableVideoCaution()
	{
		int n = Mtc100.mGetVarInt(Mtc100.DataIndexInt.VIDEO_CAUTION);
		return ( n == 1 ? true:false);
	}
}
