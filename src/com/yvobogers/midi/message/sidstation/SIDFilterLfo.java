package com.yvobogers.midi.message.sidstation;

import java.util.HashMap;

public class SIDFilterLfo extends SIDDirectProgram {
	
	// LFO is determined by a 2bit value
	private static final HashMap<String, Integer> INT_VALUES = 
		new HashMap<String, Integer>();
	static{
		INT_VALUES.put(VAL_LFO1, 0);
		INT_VALUES.put(VAL_LFO2, 1);
		INT_VALUES.put(VAL_LFO3, 2);
		INT_VALUES.put(VAL_LFO4, 3);
	}
	
	public SIDFilterLfo(Object val	)
	throws SIDException 
	{
		super();
		int v = INT_VALUES.get(val).intValue();
		
		add(0x00); add(0x19); add(0x03); add(0x04); add(v); add(END);
	}
}
