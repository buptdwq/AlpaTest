package com.ekusoft.alpacore;


enum McuSouceType
{
	E_MCU_SOURCE_OFF	    (0x00),
	E_MCU_SOURCE_RADIO		(0x01),
	E_MCU_SOURCE_DVD		(0x02),
	E_MCU_SOURCE_SD			(0x03),
	E_MCU_SOURCE_USB		(0x04),
	E_MCU_SOURCE_TV			(0x05),
	E_MCU_SOURCE_CMMB		(0x06),
	E_MCU_SOURCE_AVIN1		(0x07),
	E_MCU_SOURCE_AVIN2		(0x08),
	E_MCU_SOURCE_BT			(0x09),
	E_MCU_SOURCE_IPOD		(0x0A),
	
	E_MCU_SOURCE_CAMERA		(0x0B),	
	E_MCU_SOURCE_ARM_SDHC	(0x0C),
	E_MCU_SOURCE_ARM_USB	(0x0D),
	E_MCU_SOURCE_ARM_SCM	(0x0E),
	E_MCU_SOURCE_CIVIC		(0x0F),
	E_MCU_SOURCE_SYNC		(0x10),
	E_MCU_SOURCE_CARSET		(0x11),
	E_MCU_INVALID_SOURCE	(0xFF);
	
	
	private final int value;
   
	private McuSouceType(int value) 
    {
        this.value = value;
    }
     
    public int getValue() 
    {
        return value;
    }
    

};


