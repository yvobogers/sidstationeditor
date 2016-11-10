package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorVibratoWheelDepth extends SIDDirectProgram {
	
	public SIDOscillatorVibratoWheelDepth(Object val, int id)
	throws SIDException 
	{
		super();
		int v = ((Integer)val).intValue(); 
		int b1 = (id == 3) ? 1 : 0;
		int b2 = (id == 1) ? 0x5a : ((id==2) ? 0x6f : 0x04);
		
		add(b1); add(b2); add(0x7f); add(0x00); add(v); add(END);
	}
}
