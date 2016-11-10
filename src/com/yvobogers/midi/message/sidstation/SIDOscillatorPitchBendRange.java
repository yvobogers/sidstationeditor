package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorPitchBendRange extends SIDDirectProgram {
	
	public SIDOscillatorPitchBendRange(Object aValue, int anOscillatorId)
	throws SIDException 
	{
		super();
		int v = ((Integer)aValue).intValue();

		int osc = 0x4c; // default is osc1;
		switch (anOscillatorId){
			case 2:	osc = 0x61; break;
			case 3: osc = 0x76; break;
		}
		
		add(0x00); add(osc); add(0x7f); add(0x00); add(v); add(END);
	}
}
