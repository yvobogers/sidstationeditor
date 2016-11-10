package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorArpSpeed extends SIDDirectProgram {

	/**
	 * sets oscillator attack to a value in the range 0...15 
	 * 
	 * @param aValue
	 */
	public SIDOscillatorArpSpeed(Object aValue, int anOscillatorId)
	throws SIDException 
	{
		super();

		if (anOscillatorId<1 || anOscillatorId > 3){
			throw new SIDException("Oscillator must have a value 1-3");
		}

		int oscillatorByte = 0x49; // default is osc1;
		switch (anOscillatorId){
		case 2:	
			oscillatorByte = 0x5e;
			break;
		case 3:
			oscillatorByte = 0x73;
			break;
		}
		
		add(0x00);
		add(oscillatorByte);
		add(0x0f);
		add(0x00);
		add(((Integer)aValue).intValue());
		add(END);

	}
}
