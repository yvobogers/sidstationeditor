package com.yvobogers.midi.message.sidstation;

import java.util.HashMap;

public class SIDOscillatorPwmLfo extends SIDDirectProgram {
	
	// LFO is determined by a 2bit value
	private static final HashMap<String, Integer> INT_VALUES = 
		new HashMap<String, Integer>();
	static{
		INT_VALUES.put(VAL_LFO1, 0);
		INT_VALUES.put(VAL_LFO2, 1);
		INT_VALUES.put(VAL_LFO3, 2);
		INT_VALUES.put(VAL_LFO4, 3);
	}
	
	public SIDOscillatorPwmLfo(Object aValue, int id)
	throws SIDException 
	{
		super();
		int v = INT_VALUES.get(aValue).intValue();

		int b = (id == 1) ? 0x54 : ((id==2) ? 0x69 : 0x7e);

		add(0x00); add(b); add(0x03); add(0x00); add(v); add(END);
	}
}
