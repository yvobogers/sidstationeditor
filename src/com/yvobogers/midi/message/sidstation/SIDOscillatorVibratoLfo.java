package com.yvobogers.midi.message.sidstation;

import java.util.HashMap;

public class SIDOscillatorVibratoLfo extends SIDDirectProgram {
	
	// LFO is determined by a 2bit value
	private static final HashMap<String, Integer> INT_VALUES = 
		new HashMap<String, Integer>();
	static{
		INT_VALUES.put(VAL_LFO1, 0);
		INT_VALUES.put(VAL_LFO2, 1);
		INT_VALUES.put(VAL_LFO3, 2);
		INT_VALUES.put(VAL_LFO4, 3);
	}
	
	public SIDOscillatorVibratoLfo(Object val, int id)
	throws SIDException 
	{
		super();
		int v = INT_VALUES.get(val).intValue();
		int b1 = (id == 3) ? 1 : 0;
		int b2 = (id == 1) ? 0x58 : ((id==2) ? 0x6d : 0x02);
		
		add(b1); add(b2); add(0x03); add(0x00); add(v); add(END);
	}
}
