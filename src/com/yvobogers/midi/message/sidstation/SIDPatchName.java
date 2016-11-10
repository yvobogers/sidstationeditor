package com.yvobogers.midi.message.sidstation;


public class SIDPatchName extends SIDDirectProgram {

	/**
	 * sends a series of sysex messages to rename a patch
	 * 
	 * @param aValue
	 */
	public SIDPatchName(Object aValue) 
	throws SIDException {
		super();
		
		String patchname = aValue.toString();
		if (patchname.length() > 10 || "".equals(patchname)){
			throw new SIDException("Patchname illegal size");
		}
		
		for (int pos = 0; pos<patchname.length(); pos++){
			
			add(0x00);
			add(pos);
			add(0x7f);
			add(0x00);
			add(patchname.charAt(pos));
			add(END);
			// start the next message except the last round
			if (pos < patchname.length()-1){
				init();
				add(COMMAND_ID);
			}
		}
	}
}
