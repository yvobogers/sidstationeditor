package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorPwmStart extends SIDDirectProgram {
	
	public SIDOscillatorPwmStart(Object aValue, int id)
	throws SIDException 
	{
		super();
		int v = ((Integer)aValue).intValue();

		int b = (id == 1) ? 0x52 : ((id==2) ? 0x67 : 0x7c);

		add(0x00); add(b); add(0x7f); add(0x00); add(v); add(END);
	}
}
