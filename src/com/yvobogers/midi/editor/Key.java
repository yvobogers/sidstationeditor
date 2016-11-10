package com.yvobogers.midi.editor;

import java.awt.Rectangle;

/** Piano & Key code basics taken from javasound demos (c) Sun Microsystems **/

@SuppressWarnings("serial")
public class Key extends Rectangle {
	int noteState = 0;
	int kNum;
	boolean isBlack;

	public Key(int x, int y, int width, int height, int num, boolean black) {
		super(x, y, width, height);
		kNum = num;
		isBlack = black;
	}
	public boolean isNoteOn() {
		return noteState == 1;
	}
	public void on(int vel) {
		try {
			if (isBlack) vel = vel*2;
			setNoteState(1);
			OutputListener.getSelectedOutput().send(
					new byte[]{(byte)144, (byte)kNum, (byte)vel});
		}catch (Exception me) {}
	}
	public void off() {
		try {
			setNoteState(0);
			OutputListener.getSelectedOutput().send(
					new byte[]{(byte)128, (byte)kNum, (byte)0});
		}catch (Exception me) {}
	}
	public void setNoteState(int state) {
		noteState = state;
	}

	public void transpose(int oct) {
		kNum += oct*12;
	}
}

