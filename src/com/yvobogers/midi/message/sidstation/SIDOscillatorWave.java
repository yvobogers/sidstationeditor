package com.yvobogers.midi.message.sidstation;

import java.util.HashMap;

public class SIDOscillatorWave extends SIDDirectProgram {
	
	// wave mode is determined by a 4-bit value. 
	private static final HashMap<String, Integer> INT_VALUES = 
		new HashMap<String, Integer>();
	static{
		INT_VALUES.put(OSC_WAVE_TRIANGLE, 1);
		INT_VALUES.put(OSC_WAVE_SAW, 2);
		INT_VALUES.put(OSC_WAVE_PULSE, 4);
		INT_VALUES.put(OSC_WAVE_MIXED, 5);
		INT_VALUES.put(OSC_WAVE_NOISE, 8);
	}
	
	public SIDOscillatorWave(Object val, int id)
	throws SIDException 
	{
		super();
		int v = INT_VALUES.get(val).intValue();
		int b1 = (id == 3) ? 1 : 0;
		int b2 = (id == 1) ? 0x56 : ((id==2) ? 0x6b : 0x00);
		
		add(b1); add(b2); add(0x0f); add(0x04); add(v); add(END);
	}
}
