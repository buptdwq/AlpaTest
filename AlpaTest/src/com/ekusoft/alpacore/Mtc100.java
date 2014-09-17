package com.ekusoft.alpacore;

import android.R.array;
import android.R.integer;

class Mtc100 
{
	
	public class ShareDataPair 
	{
		public int offset;
		public int size;
	};
	
	public enum CmdType 
	{
		CmdType_0x81,
		CmdType_0x82,
		CmdType_DevStatusCmd_0x83,
		CmdType_McuCfg_0x84,
		CmdType_Radio_0x8A_02,
		CmdType_Radio_0x8A_03,
		CmdType_Radio_0x8A_04,
		CmdType_VOL_0x8C_0x01,
	};
	
	
	public enum DataIndexInt
	{
		BT_IN_CALL,
		BT_CONNECT,
		SYSTEM_VOL,
		EQ_TYPE,
		SYSTEM_MUTE,
		EQ_LOUDNESS,
		SOURCE_INFO,
		VIDEO_CAUTION,
	};
	
	public enum DataIndexStr
	{
		MCU_VERSION,
		DVD_VERSION,
		CAN_VERSION,
	};
	
	

	static public final int MAX_NUM_OF_SHARE_BYTE_BUFF_SIZE = 8192;
	static public final int MAX_NUM_OF_SHARE_BYTE_ITEM_COUNT = 512;
	static public final int MAX_NUM_OF_SHARE_DATA_INT = 512; //�����int�ĸ���
	static public final int MAX_NUM_OF_SHARE_DATA_STR = 128; //�����String�ĸ���
	

	static private ShareDataPair[] allCmdPair = new ShareDataPair[MAX_NUM_OF_SHARE_BYTE_ITEM_COUNT]; 
	static private byte[] shareByteData = new byte[MAX_NUM_OF_SHARE_BYTE_BUFF_SIZE];
	static private int shareByteUsed = 0;
	
	static private int[] shareIntData = new int[MAX_NUM_OF_SHARE_DATA_INT];
	static private String[] shareStrData = new String[MAX_NUM_OF_SHARE_DATA_STR];
	
	
	static public int  mGetVarInt(DataIndexInt nIndex)
	{
		if (  nIndex.ordinal() >= MAX_NUM_OF_SHARE_DATA_INT  ) 
		{
			return 0;
		}
		
		return shareIntData[nIndex.ordinal()];
	}
	
	static public void mSetVarInt(DataIndexInt nIndex, int nValue)
	{
		if (  nIndex.ordinal() >= MAX_NUM_OF_SHARE_DATA_INT  ) 
		{
			return ;
		}
		
		shareIntData[nIndex.ordinal()] = nValue;
	}
	
	static public String  mGetVarStr(DataIndexStr nIndex)
	{
		if (  nIndex.ordinal() >= MAX_NUM_OF_SHARE_DATA_STR  ) 
		{
			return "";
		}
		
		return shareStrData[nIndex.ordinal()];
	}
	
	static public void mSetVarStr(DataIndexStr nIndex, String strValue)
	{
		if (  nIndex.ordinal() >= MAX_NUM_OF_SHARE_DATA_STR  ) 
		{
			return ;
		}
		
		shareStrData[nIndex.ordinal()] = strValue;
	}	
	
	//BYTE data[32] = {0};
	//int nRet = mGetVar8(x, data, sizeof(data));
	//���������Ҫ���nRet�ķ���ֵ,ע��Get�����Ϳ���ʧ��,���ʧ����,ʹ��Ĭ��ֵ�Ϳ�����
	static public int mGetVar8( CmdType nIndex, byte[] outBuff, int nOutBuffLen) {
		
		if (  (nOutBuffLen <= 0)  || (nIndex.ordinal() >= MAX_NUM_OF_SHARE_BYTE_ITEM_COUNT)  ) {
				return 0;
		}


		int nRet = 0;

		nRet = Math.min(nOutBuffLen, allCmdPair[nIndex.ordinal()].size);
		System.arraycopy(shareByteData, allCmdPair[nIndex.ordinal()].offset, outBuff, 0, nOutBuffLen);

		return nRet;
	}

	//��pInBuff,nInBuffLenд�빲���ڴ�,
	//���سɹ�д���ڴ���ֽ���,����0��ʾʧ��
	//BYTE data[32] = {0};
	//int nRet = mSetVar8(x, data, sizeof(data));
	//ASSERT(0 != nRet), ��Ҫ��ASSERT������Ƿ�д��ȥ��
	static public int	mSetVar8( CmdType nIndex, byte[] InBuff, int nInBuffLen) {
		
		if (  (nInBuffLen <= 0)  
				|| (nIndex.ordinal() >= MAX_NUM_OF_SHARE_BYTE_ITEM_COUNT)  )
			{
				return 0;
			}


			int nRet = 0;


			if ( allCmdPair[nIndex.ordinal()].size  > 0) {
				//�Ѿ����ù���,��С�Ѿ��̶�
				
				nRet = Math.min(nInBuffLen, allCmdPair[nIndex.ordinal()].size);
				System.arraycopy( InBuff, 0, shareByteData, allCmdPair[nIndex.ordinal()].offset, nRet);
			}
			else {
				int nLeft = MAX_NUM_OF_SHARE_BYTE_BUFF_SIZE - shareByteUsed;
				
				if ( (nLeft - nInBuffLen) >= 0 )
				{
				
					System.arraycopy( InBuff, 0, shareByteData, shareByteUsed, nInBuffLen);
					

					allCmdPair[nIndex.ordinal()].offset = shareByteUsed;
					allCmdPair[nIndex.ordinal()].size   = nInBuffLen;
					
					nRet = nInBuffLen;
					shareByteUsed += nInBuffLen;
				}
			}


			return nRet;		
	}
	
	
	
}
