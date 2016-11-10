package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorTranspose extends SIDDirectProgram {
	
	public SIDOscillatorTranspose(Object aValue, int anOscillatorId)
	throws SIDException 
	{
		super();
		int v = ((Integer)aValue).intValue(); // TODO is a signed bit!
		
		int osc = 0x4a; // default is osc1;
		switch (anOscillatorId){
			case 2:	osc = 0x5f; break;
			case 3: osc = 0x74; break;
		}
		
		add(0x00); add(osc); add(0x7f); add(0x00); add(v); add(END);
	}
}
