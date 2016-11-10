package com.yvobogers.midi.message.sidstation;

public class SIDToggleFilterSyncToNoteOn extends SIDToggle {

	/**
	 * toggles if filter syncs to note on events 
	 * 
	 * @param aValue
	 */
	public SIDToggleFilterSyncToNoteOn(Object aValue){
		super(aValue);
		itsBytes.add(new Byte((byte)0x00));
		itsBytes.add(new Byte((byte)0x16));
		itsBytes.add(new Byte((byte)0x01));
		itsBytes.add(new Byte((byte)0x04));
		super.toggle();
	}
}
