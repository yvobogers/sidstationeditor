package com.yvobogers.midi.ports;

import de.humatic.mmj.MidiOutput;

public class MmjMidiPort implements MidiPort {

	private String itsName = "";
	private MidiOutput itsOutput = null;

	public MmjMidiPort(MidiOutput output){
		itsOutput = output;
		itsName = itsOutput.getName();
	}

	@Override
	public String getName() {
		return itsName;
	}

	@Override
	public void send(byte[] data) {
		itsOutput.sendMidi(data); 
	}
	
	public String toString(){
		return itsName;
	}

}
