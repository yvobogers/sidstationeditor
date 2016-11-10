package com.yvobogers.midi.message.sidstation;

import java.util.HashMap;

import com.yvobogers.midi.util.Logger;

public class SIDFilterMask extends SIDDirectProgram {

	/**
	 * 
	 * 
	 * @param aValue hashmap of of FLTx -> boolean
	 */
	@SuppressWarnings("unchecked")
	public SIDFilterMask(Object aValue) throws SIDException {
		super();
		
		HashMap<String, Boolean> m = (HashMap<String, Boolean>)aValue;
	
		String mask = "";
		
		mask += m.get(FLT3) ? "1" : "0";
		mask += m.get(FLT2) ? "1" : "0";
		mask += m.get(FLT1) ? "1" : "0";
		
		Logger.log("filters: " + mask);
		
		add(0x00);
		add(0x18);
		add(0x7f);
		add(0x00);
		add(Integer.parseInt(mask, 2));
		add(END);
	}
}
