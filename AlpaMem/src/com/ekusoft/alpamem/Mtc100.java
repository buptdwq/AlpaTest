package com.ekusoft.alpamem;


public class Mtc100 
{
	static public class ShareDataPair 
	{
		public ShareDataPair()
		{
			offset = 0;
			size = 0;
		}
		
		public int offset;
		public int size;
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
	
	
	public static int mGetTotalSize() {
		return MAX_NUM_OF_SHARE_BYTE_BUFF_SIZE + MAX_NUM_OF_SHARE_DATA_INT*4;
	}
	
	static public int  mGetVarInt( int nIndex)
	{
		if (  nIndex >= MAX_NUM_OF_SHARE_DATA_INT  ) 
		{
			return 0;
		}
		
		return shareIntData[nIndex];
	}
	
	static public void mSetVarInt( int nIndex, int nValue)
	{
		if (  nIndex >= MAX_NUM_OF_SHARE_DATA_INT  ) 
		{
			return ;
		}
		
		shareIntData[nIndex] = nValue;
	}
	
	static public String  mGetVarStr( int nIndex)
	{
		//android.os.Debug.waitForDebugger(); 
		
		if (  nIndex >= MAX_NUM_OF_SHARE_DATA_STR  ) 
		{
			return "";
		}
		
		return shareStrData[nIndex];
	}
	
	static public void mSetVarStr( int nIndex, String strValue)
	{
		if (  nIndex >= MAX_NUM_OF_SHARE_DATA_STR  ) 
		{
			return ;
		}
		
		shareStrData[nIndex] = strValue;
	}	
	
	//BYTE data[32] = {0};
	//int nRet = mGetVar8(x, data, sizeof(data));
	
	static public int mGetVar8( int nIndex, byte[] outBuff) {
		
		if (  (nIndex >= MAX_NUM_OF_SHARE_BYTE_ITEM_COUNT)  ) 
		{
				return 0;
		}


		int nRet = 0;
		int nRealIndex  = nIndex;
		
		if ( allCmdPair[nRealIndex] == null)
		{
			return 0;
		}
		
		if ( outBuff == null)
		{
			return allCmdPair[nIndex].size;
		}

		nRet = Math.min( outBuff.length, allCmdPair[nIndex].size);
		System.arraycopy(shareByteData, allCmdPair[nIndex].offset, outBuff, 0, outBuff.length);

		return nRet;
	}

	
	static public int	mSetVar8( int nIndex, byte[] InBuff, int nInBuffLen)
	{
		
		if (  (nInBuffLen <= 0)  
				|| (nIndex >= MAX_NUM_OF_SHARE_BYTE_ITEM_COUNT)  )
			{
				return 0;
			}


			int nRet = 0;

			int nRealIndex = nIndex;
			
			if ( allCmdPair[nRealIndex] == null)
			{
				allCmdPair[nRealIndex] = new ShareDataPair();
				allCmdPair[nRealIndex].size = 0;
				allCmdPair[nRealIndex].offset = 0;
			}
			
			if ( allCmdPair[nRealIndex].size  > 0) 
			{
				
				nRet = Math.min(nInBuffLen, allCmdPair[nIndex].size);
				System.arraycopy( InBuff, 0, shareByteData, allCmdPair[nIndex].offset, nRet);
			}
			else 
			{
				int nLeft = MAX_NUM_OF_SHARE_BYTE_BUFF_SIZE - shareByteUsed;
				
				if ( (nLeft - nInBuffLen) >= 0 )
				{
					System.arraycopy( InBuff, 0, shareByteData, shareByteUsed, nInBuffLen);
					
					allCmdPair[nIndex].offset = shareByteUsed;
					allCmdPair[nIndex].size   = nInBuffLen;
					
					nRet = nInBuffLen;
					shareByteUsed += nInBuffLen;
				}
			}


			return nRet;		
	};
	
}
