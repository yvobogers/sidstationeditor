package com.yvobogers.midi.message;


import javax.sound.midi.ShortMessage;
public class Patch extends ShortMessage {

	public Patch(int patchnr){
		super();
		try{
			super.setMessage(ShortMessage.PROGRAM_CHANGE, patchnr, 0);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
