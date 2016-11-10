package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorDelay extends SIDDirectProgram {
	
	public SIDOscillatorDelay(Object aValue, int anOscillatorId)
	throws SIDException 
	{
		super();
		int v = ((Integer)aValue).intValue();

		int osc = 0x51; // default is osc1;
		switch (anOscillatorId){
			case 2:	osc = 0x66; break;
			case 3: osc = 0x7b; break;
		}
		
		add(0x00); add(osc); add(0x7f); add(0x00); add(v); add(END);
	}
}
