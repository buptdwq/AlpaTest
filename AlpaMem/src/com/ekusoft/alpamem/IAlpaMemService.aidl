package com.ekusoft.alpamem;

import com.ekusoft.alpamem.BytesCmd;

interface IAlpaMemService {

	void setMcuCmd( int nCmdIndex, in BytesCmd cmd, in int nLen);
	BytesCmd getMcuCmd( int nCmdIndex );
	
	void setStrValue( int nStrIndex, String str);
	String getStrValue( int nStrIndex);
	
	void setIntValue( int nIntIndex, int value);
	int  getIntValue( int nIntIndex);
		
}

