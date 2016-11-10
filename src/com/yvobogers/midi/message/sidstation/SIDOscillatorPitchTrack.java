package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorPitchTrack extends SIDDirectProgram {
	
	public SIDOscillatorPitchTrack(Object aValue, int anOscillatorId)
	throws SIDException 
	{
		super();
		int v = ((Integer)aValue).intValue(); 
		
		int osc = 0x48; // default is osc1;
		switch (anOscillatorId){
			case 2:	osc = 0x5d; break;
			case 3: osc = 0x72; break;
		}
		
		add(0x00); add(osc); add(0x7f); add(0x00); add(v); add(END);
	}
}
