package com.ekusoft.alpamem;

import android.os.Parcel;
import android.os.Parcelable;

public class BytesCmd implements Parcelable
{
	private byte[] _byte;
	
	public BytesCmd()
	{
		// TODO Auto-generated constructor stub
		
	}

	public BytesCmd(Parcel in) 
	{
        readFromParcel(in);
    }


    public byte[] get_byte() 
    {
        return _byte;
    }

    public void set_byte(byte[] _byte) 
    {
    	
        this._byte = _byte;
    }
    
	@Override
	public int describeContents()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		// TODO Auto-generated method stub
		dest.writeInt(_byte.length); 
		dest.writeByteArray(_byte); 
	}

	public void readFromParcel(Parcel in) 
	{
		_byte = new byte[in.readInt()]; 
		in.readByteArray(_byte);
    }
	
	  public static final Parcelable.Creator<BytesCmd> CREATOR = new Parcelable.Creator<BytesCmd>()
	  {
	        public BytesCmd createFromParcel(Parcel in)
	        {
	            return new BytesCmd(in);
	        }

	        public BytesCmd[] newArray(int size) {
	            return new BytesCmd[size];
	        }
	    };
}
