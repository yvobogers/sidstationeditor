package com.yvobogers.midi.message.sidstation;

public class SIDToggleFilterEnvelopeInvert extends SIDToggle {

	public SIDToggleFilterEnvelopeInvert(Object aValue){
		super(aValue);
		itsBytes.add(new Byte((byte)0x00));
		itsBytes.add(new Byte((byte)0x16));
		itsBytes.add(new Byte((byte)0x01));
		itsBytes.add(new Byte((byte)0x07));
		super.toggle();
	}
}
