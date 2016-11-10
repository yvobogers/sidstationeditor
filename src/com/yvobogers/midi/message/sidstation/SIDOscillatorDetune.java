package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorDetune extends SIDDirectProgram {
	
	public SIDOscillatorDetune(Object aValue, int anOscillatorId)
	throws SIDException 
	{
		super();
		int v = ((Integer)aValue).intValue(); // TODO is a signed bit!
		
		int osc = 0x4b; // default is osc1;
		switch (anOscillatorId){
			case 2:	osc = 0x60; break;
			case 3: osc = 0x75; break;
		}
		
		add(0x00); add(osc); add(0x0f); add(0x00); add(v); add(END);
	}
}
