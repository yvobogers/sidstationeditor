package com.yvobogers.midi.message;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

public class NoteOn extends ShortMessage {

	public NoteOn(){
		super();
		try {
			super.setMessage(ShortMessage.NOTE_ON, 0, 0x3C, 127);
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
