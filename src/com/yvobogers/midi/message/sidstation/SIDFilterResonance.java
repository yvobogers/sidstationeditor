package com.yvobogers.midi.message.sidstation;

public class SIDFilterResonance extends SIDDirectProgram {

	/**
	 * 
	 * 
	 * @param aValue
	 */
	public SIDFilterResonance(Object aValue) throws SIDException {
		super();
		
		int val = ((Integer)aValue).intValue();
		if (val > 15 || val < 0){
			throw new SIDException("Resonance must have a value  0-15");
		}
		
		add(0x00);
		add(0x18);
		add(0x0f);
		add(0x04);
		add(val);
		add(END);
	}
}
