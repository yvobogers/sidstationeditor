package com.yvobogers.midi.message.sidstation;

public class SIDPatchAllClear extends SIDMessage {
	
	private static final int COMMAND_ID = 0x01;
	private static final int MAGIC_BYTE = 0x45;
	private static final int PAD = 0x2d;
	private static final int PAD_SIZE = 14;
	
	public SIDPatchAllClear(){
		super();
		add(COMMAND_ID);
		add(MAGIC_BYTE);
		for (int i=0;i<PAD_SIZE;i++){
			add(PAD);
		}
		add(END);
	}
}
