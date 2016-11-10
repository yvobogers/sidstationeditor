package com.yvobogers.midi.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import com.yvobogers.midi.ports.MidiPort;
import com.yvobogers.midi.util.Constants;
import com.yvobogers.midi.util.Logger;

/**
 * singleton maintaining a reference to one single output port.
 * reference is updated when an actioncommand "changeoutput" is received
 * 
 * @author teknopipo
 *
 */
public class OutputListener implements ActionListener, Constants {

	private static MidiPort theirCurrentOutput = null; // could add a default system out
	private static OutputListener theirInstance = null;
	
	private OutputListener(){
		// private singleton constructor
	}
	
	public static OutputListener instance(){
		if (theirInstance==null){
			theirInstance = new OutputListener();
		}
		return theirInstance;
	}
	
	public static MidiPort getSelectedOutput(){
		return theirCurrentOutput;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == OUTPUT){
			MidiPort p = (MidiPort)((JComboBox)e.getSource()).getSelectedItem();
			Logger.log("setting output to " + p);
			theirCurrentOutput = p;
		}
	}

}
