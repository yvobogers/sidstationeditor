package com.yvobogers.midi.message.sidstation;

public abstract class SIDToggle extends SIDDirectProgram {

	protected int itsToggle = 0; // default false
	
	protected SIDToggle(Object aValue){
		super();
		boolean val = ((Boolean)aValue).booleanValue();
		if (val){
			itsToggle = 0x7F;
		}  // else default
	}
	
	protected void toggle(){
		add(itsToggle);
		add(END);
	}
}
