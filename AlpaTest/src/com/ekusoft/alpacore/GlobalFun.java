package com.ekusoft.alpacore;

class GlobalFun
{
	public static byte[] toByteArray(int iSource, int iArrayLen) 
	{
	    byte[] bLocalArr = new byte[iArrayLen];
	    for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
	        bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);
	    }
	    return bLocalArr;
	}
	
	// ��byte����bRefArrתΪһ������,�ֽ�����ĵ�λ�����͵ĵ��ֽ�λ
	public static int toInt(byte[] bRefArr) 
	{
	    int iOutcome = 0;
	    byte bLoop;

	    for (int i = 0; i < bRefArr.length; i++) 
	    {
	        bLoop = bRefArr[i];
	        iOutcome += (bLoop & 0xFF) << (8 * i);
	    }
	    
	    return iOutcome;
	}
	
	public static byte  bitPos( byte nPos)
	{
		return (byte)( 1 << nPos );
	}
	
	public static byte bitGet( byte data, byte pos)
	{
		return (byte)( ((data) >> (pos)) & 1);
	}
	
	public static byte bitGetRange( byte data, byte start, byte len)
	{
		return (byte)( ((data) >> (start)) & ((1 << (len)) -1));
	}


	
}
