package com.yvobogers.midi.message.sidstation;

public class SIDOscillatorGate extends SIDToggle {
	
	public SIDOscillatorGate(Object aValue, int anOscillatorId)
	throws SIDException 
	{
		super(aValue);

		int osc = 0x47; // default is osc1;
		switch (anOscillatorId){
			case 2:	osc = 0x5c; break;
			case 3: osc = 0x71; break;
		}
		
		add(0x00); add(osc); add(0x01); add(0x01); toggle();
	}
}
