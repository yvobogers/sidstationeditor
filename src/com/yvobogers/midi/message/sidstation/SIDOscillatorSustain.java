package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorSustain extends SIDDirectProgram {
	
	public SIDOscillatorSustain(Object aValue, int id)
	throws SIDException 
	{
		super();
		int v = ((Integer)aValue).intValue();

		int b = (id == 1) ? 0x4f : ((id==2) ? 0x64 : 0x79);

		add(0x00); add(b); add(0x0f); add(0x00); add(v); add(END);
	}
}
