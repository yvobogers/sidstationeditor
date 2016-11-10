package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorPortamentoSpeed extends SIDDirectProgram {
	
	public SIDOscillatorPortamentoSpeed(Object aValue, int anOscillatorId)
	throws SIDException 
	{
		super();
		int v = ((Integer)aValue).intValue(); 
		
		int oscbyte1 = 0x00;
		switch (anOscillatorId){
			case 3: oscbyte1 = 0x01; break;
		}
		int oscbyte2 = 0x57; // default is osc1;
		switch (anOscillatorId){
			case 2:	oscbyte2 = 0x6c; break;
			case 3: oscbyte2 = 0x01; break;
		}
		
		
		add(oscbyte1); add(oscbyte2); add(0x7f); add(0x00); add(v); add(END);
	}
}
