package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorAttack extends SIDDirectProgram {

	/**
	 * sets oscillator attack to a value in the range 0...15 
	 * 
	 * @param aValue
	 */
	public SIDOscillatorAttack(Object aValue, int anOscillatorId)
	throws SIDException 
	{
		super();

		if (anOscillatorId<1 || anOscillatorId > 3){
			throw new SIDException("Oscillator must have a value 1-3");
		}

		int attackValue = ((Integer)aValue).intValue();
		if (attackValue > 15 || attackValue < 0){
			throw new SIDException("Attack must have a value  0-15");
		}

		int oscillatorByte = 0x4d; // default is osc1;
		switch (anOscillatorId){
		case 2:	
			oscillatorByte = 0x62;
			break;
		case 3:
			oscillatorByte = 0x77;
			break;
		}
		
		add(0x00);
		add(oscillatorByte);
		add(0x0f);
		add(0x00);
		add(attackValue);
		add(END);

	}
}
