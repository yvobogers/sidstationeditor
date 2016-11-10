package com.yvobogers.midi.message.sidstation;

public class SIDToggleLegato extends SIDToggle {
	
	/**
	 * toggleslegato 
	 * 
	 * @param aValue
	 */
	public SIDToggleLegato(Object aValue){
		super(aValue);
		itsBytes.add(new Byte((byte)0x00));
		itsBytes.add(new Byte((byte)0x16));
		itsBytes.add(new Byte((byte)0x01));
		itsBytes.add(new Byte((byte)0x05));
		super.toggle();
	}
}
