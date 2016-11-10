package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorVibratoDepth extends SIDDirectProgram {
	
	public SIDOscillatorVibratoDepth(Object aValue, int anOscillatorId)
	throws SIDException 
	{
		super();
		int v = ((Integer)aValue).intValue(); 
		
		int oscbyte1 = 0x00;
		switch (anOscillatorId){
			case 3: oscbyte1 = 0x01; break;
		}
		int oscbyte2 = 0x59; // default is osc1;
		switch (anOscillatorId){
			case 2:	oscbyte2 = 0x6e; break;
			case 3: oscbyte2 = 0x03; break;
		}
		
		
		add(oscbyte1); add(oscbyte2); add(0x7f); add(0x00); add(v); add(END);
	}
}
