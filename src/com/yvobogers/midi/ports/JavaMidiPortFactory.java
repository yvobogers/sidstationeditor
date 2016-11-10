package com.yvobogers.midi.ports;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.MidiDevice.Info;

import com.yvobogers.midi.util.Logger;

/**
 * singleton
 * 
 * @author teknopipo
 *
 */
public class JavaMidiPortFactory extends AbstractPortFactory {

	private JavaMidiPortFactory(){
		Logger.log("MidiSystem: " + MidiSystem.class);
	}

	public static AbstractPortFactory instance() {
		if (theirInstance == null){
			theirInstance = new JavaMidiPortFactory();
		}
		return theirInstance;
	}

	@Override
	/**
	 * finds all JavaSound MIDI ports and stores them in memory.
	 * individual ports can then be retrieved using getPort(String) method.
	 */
	public MidiPort[] initPorts() {
		try{
			Info[] info = MidiSystem.getMidiDeviceInfo();
			itsMidiPorts = new MidiPort[info.length];
			for (int index=0; index<info.length; index++) {
				MidiDevice dev = MidiSystem.getMidiDevice(info[index]);
				itsMidiPorts[index] = new JavaMidiPort(dev);
			}
		} catch (MidiUnavailableException e) {
			Logger.log("Error determining MIDI ports " + this + ", exiting...");
			e.printStackTrace();
			System.exit(1);
		}

		return itsMidiPorts;
	}
}
