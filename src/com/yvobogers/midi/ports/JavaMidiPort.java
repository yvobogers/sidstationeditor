package com.yvobogers.midi.ports;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;

import com.yvobogers.midi.util.Logger;

/**
 * implementation of the MidiPort interface for the JavaSound driver.
 *  a JavaMidiPort will construct MidiMessages based on the JavaSound
 * API for transmitting messages.
 * 
 * @author teknopipo
 *
 */
public class JavaMidiPort implements MidiPort {

	private String itsName = "";
	private MidiDevice itsDevice = null;

	public JavaMidiPort(MidiDevice dev){
		itsDevice = dev;
		itsName  = dev.getDeviceInfo().getName();
	}

	@Override
	public String getName() {
		return itsName;
	}

	@Override
	public void send(byte[] data) {
		Logger.log("received bytes: " + data);
		if (itsDevice == null) return;

		if ((data[0] & 0xFF) == 0xF0) {
			byte[] outData = new byte[data.length-1];
			for (int i = 1; i < data.length; i++) outData[i-1] = data[i];
			sendJavaMidi(240, outData);
		} else sendJavaMidi(240, data);
	}

	private void sendJavaMidi(int status, byte[] data) { 
		try{
			MidiMessage msg = null;
			if (status == 0xF0) {
				msg = new javax.sound.midi.SysexMessage();
				((SysexMessage)msg).setMessage(0xF0, data, data.length); 
			} else if (status >= 250) {
				msg = new ShortMessage();
				((ShortMessage)msg).setMessage(status); 
			} else {
				msg = new ShortMessage(); 
				((ShortMessage)msg).setMessage(status, data[0], data[1]);
			}
			itsDevice.open();
			Receiver rec = itsDevice.getReceiver();
			rec.send(msg, TIMESTAMP);
			rec.close();
			itsDevice.close();			
		}  catch (Exception e){
			e.printStackTrace();
		}

	}

	public String toString(){
		return itsName;
	}
}
