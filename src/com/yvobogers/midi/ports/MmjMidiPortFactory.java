package com.yvobogers.midi.ports;

import com.yvobogers.midi.util.Logger;

import de.humatic.mmj.MidiOutput;

/**
 * singleton
 * 
 * @author teknopipo
 *
 */
public class MmjMidiPortFactory extends AbstractPortFactory {
	
	private MmjMidiPortFactory(){
		Logger.log("MidiSystem: " + de.humatic.mmj.MidiSystem.class);
	}

	public static AbstractPortFactory instance(){
		if (theirInstance == null){
			theirInstance = new MmjMidiPortFactory(); 
		}
		return theirInstance;
	}	
	
	@Override
	public MidiPort[] initPorts() {
		String[] outputNames = de.humatic.mmj.MidiSystem.getOutputs();
		itsMidiPorts = new MidiPort[outputNames.length];
		for (int index=0; index<outputNames.length; index++) {
			MidiOutput output = de.humatic.mmj.MidiSystem.openMidiOutput(index);
			itsMidiPorts[index] = new MmjMidiPort(output);
		}
		return itsMidiPorts;
	}

}
