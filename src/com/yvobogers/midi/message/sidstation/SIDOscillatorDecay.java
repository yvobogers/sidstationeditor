package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorDecay extends SIDDirectProgram {
	
	public SIDOscillatorDecay(Object aValue, int anOscillatorId)
	throws SIDException 
	{
		super();
		int v = ((Integer)aValue).intValue();

		int osc = 0x4e; // default is osc1;
		switch (anOscillatorId){
			case 2:	osc = 0x63; break;
			case 3: osc = 0x78; break;
		}
		
		add(0x00); add(osc); add(0x0f); add(0x00); add(v); add(END);
	}
}
