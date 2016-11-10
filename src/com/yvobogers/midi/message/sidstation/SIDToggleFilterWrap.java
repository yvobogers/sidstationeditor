package com.yvobogers.midi.message.sidstation;

public class SIDToggleFilterWrap extends SIDToggle {
	
	/**
	 * toggleslegato 
	 * 
	 * @param aValue
	 */
	public SIDToggleFilterWrap(Object aValue){
		super(aValue);
		itsBytes.add(new Byte((byte)0x00));
		itsBytes.add(new Byte((byte)0x16));
		itsBytes.add(new Byte((byte)0x01));
		itsBytes.add(new Byte((byte)0x06));
		super.toggle();
	}
}
