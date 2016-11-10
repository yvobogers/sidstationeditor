package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorSyncPwmAddToKeyOn extends SIDToggle {
	
	public SIDOscillatorSyncPwmAddToKeyOn(Object aValue, int id)
	throws SIDException 
	{
		super(aValue);
		int b = (id == 1) ? 0x47 : ((id==2) ? 0x5c : 0x71);
		add(0x00); add(b); add(0x01); add(0x00); toggle();
	}
}
