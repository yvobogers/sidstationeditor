package com.yvobogers.midi.message.sidstation;

public class SIDTogglePolyMode extends SIDToggle {

	/**
	 * toggles between Poly or Single Osciallator Mode 
	 * 
	 * @param aValue
	 */
	public SIDTogglePolyMode(Object aValue){
		super(aValue);
		itsBytes.add(new Byte((byte)0x00));
		itsBytes.add(new Byte((byte)0x16));
		itsBytes.add(new Byte((byte)0x01));
		itsBytes.add(new Byte((byte)0x03));
		super.toggle();
	}
}
