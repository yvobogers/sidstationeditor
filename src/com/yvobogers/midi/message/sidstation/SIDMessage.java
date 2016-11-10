package com.yvobogers.midi.message.sidstation;

import java.util.Vector;

import com.yvobogers.midi.message.AbstractMessage;

public abstract class SIDMessage extends AbstractMessage {

	protected Vector<Byte> itsBytes = null;
	private static final int[] SYSEX_PATCH_INIT = {
		0xF0, 0x00, 0x20, 0x3c, 0x01, 0x00};
	protected static final int END = 0xF7;
	
	public SIDMessage(){
		// initialize 
		itsBytes = new Vector<Byte>();
		init();
	}
	
	protected void init(){
		for (int b: SYSEX_PATCH_INIT){
			add(b);
		}	
	}
	
	/**
	 * return the raw data of this SIDMessage as a byte array, which could
	 * be transmitted over e.g. a MIDI port.
	 * 
	 * @return
	 */
	public byte[] getBytes(){
		int size = itsBytes.size();
		byte[] result = new byte[size];
		for (int i=0;i<size;i++){
			result[i] = itsBytes.elementAt(i);
		}
		return result;
	}
	
	/**
	 * 
	 * @param aByte an integer between 0x00 and 0x7F.
	 */
	protected void add(int aByte){
		itsBytes.add(new Byte((byte)aByte));
	}
}
