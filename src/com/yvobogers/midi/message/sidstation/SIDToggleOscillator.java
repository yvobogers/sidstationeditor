package com.yvobogers.midi.message.sidstation;

public class SIDToggleOscillator extends SIDToggle {
	
	/**
	 * toggles a SID oscillator on/off. 
	 * 
	 * @param aValue
	 * @param anOscillatorId int value between 1 and 3
	 */
	public SIDToggleOscillator(Object aValue, int anOscillatorId)
	throws SIDException {
		super(aValue);
		if (anOscillatorId > 3 || anOscillatorId < 1){
			throw new SIDException("anOscillatorId must have a value 1-3"); 
		}
		itsBytes.add(new Byte((byte)0x00));
		itsBytes.add(new Byte((byte)0x16));
		itsBytes.add(new Byte((byte)0x01));
		itsBytes.add(new Byte((byte)(anOscillatorId-1)));
		super.toggle();
	}
}
