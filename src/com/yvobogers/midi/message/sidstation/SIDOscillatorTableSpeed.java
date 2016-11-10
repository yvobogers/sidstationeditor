package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorTableSpeed extends SIDDirectProgram {
	
	public SIDOscillatorTableSpeed(Object aValue, int id)
	throws SIDException 
	{
		super();
		int v = ((Integer)aValue).intValue(); 
		
		int b1 = (id == 3) ? 1 : 0;
		int b2 = (id == 1) ? 0x5b : ((id==2) ? 0x70 : 0x05);
		
		add(b1); add(b2); add(0x7f); add(0x00); add(v); add(END);
	}
}
