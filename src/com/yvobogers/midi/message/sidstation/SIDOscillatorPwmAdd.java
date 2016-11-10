package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorPwmAdd extends SIDDirectProgram {
	
	public SIDOscillatorPwmAdd(Object aValue, int anOscillatorId)
	throws SIDException 
	{
		super();
		int v = ((Integer)aValue).intValue();

		int osc = 0x53; // default is osc1;
		switch (anOscillatorId){
			case 2:	osc = 0x68; break;
			case 3: osc = 0x7d; break;
		}
		
		add(0x00); add(osc); add(0x7f); add(0x00); add(v); add(END);
	}
}
