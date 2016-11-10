package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorPwmLfoDepth extends SIDDirectProgram {
	
	public SIDOscillatorPwmLfoDepth(Object aValue, int id)
	throws SIDException 
	{
		super();
		int v = ((Integer)aValue).intValue();

		int b = (id == 1) ? 0x55 : ((id==2) ? 0x6a : 0x7f);

		add(0x00); add(b); add(0x7f); add(0x00); add(v); add(END);
	}
}
