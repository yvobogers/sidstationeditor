package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorRelease extends SIDDirectProgram {
	
	public SIDOscillatorRelease(Object aValue, int id)
	throws SIDException 
	{
		super();
		int v = ((Integer)aValue).intValue();

		int b = (id == 1) ? 0x50 : ((id==2) ? 0x65 : 0x7a);

		add(0x00); add(b); add(0x0f); add(0x00); add(v); add(END);
	}
}
