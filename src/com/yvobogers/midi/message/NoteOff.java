package com.yvobogers.midi.message;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

public class NoteOff extends ShortMessage {

	public NoteOff(){
		super();
		try {
			setMessage(ShortMessage.NOTE_OFF, 0, 0x3C, 0);
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
