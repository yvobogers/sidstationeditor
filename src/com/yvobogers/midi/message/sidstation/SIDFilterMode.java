package com.yvobogers.midi.message.sidstation;

import java.util.HashMap;

public class SIDFilterMode extends SIDDirectProgram {
	
	// filter mode is determined by a 3-bit value. Each 
	// bit turns a filter on and off.
	private static final HashMap<String, Integer> INT_VALUES = 
		new HashMap<String, Integer>();
	static{
		INT_VALUES.put(FLT_TYPE_OFF, 0);
		INT_VALUES.put(FLT_TYPE_LOW_PASS, 1);
		INT_VALUES.put(FLT_TYPE_BAND_PASS, 2);
		INT_VALUES.put(FLT_TYPE_LOWBAND_PASS, 3);
		INT_VALUES.put(FLT_TYPE_HIGH_PASS, 4);
		INT_VALUES.put(FLT_TYPE_LOWHIGH_PASS, 5);
		INT_VALUES.put(FLT_TYPE_BANDHIGH_PASS, 6);
		INT_VALUES.put(FLT_TYPE_ALL, 7);
	}
	
	/**
	 * 
	 * @param aValue
	 */
	public SIDFilterMode(Object aValue) throws SIDException {
		super();
		int filterByte = INT_VALUES.get(aValue).intValue();
		if (filterByte < 0 || filterByte > 7){
			throw new SIDException("filterByte must have a value 0-7");
		}
		add(0x00);
		add(0x19);
		add(0x07);
		add(0x00);
		add(filterByte);
		add(END);
	}
}
