package com.yvobogers.midi.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import com.yvobogers.midi.message.AbstractMessage;
import com.yvobogers.midi.message.AbstractMessageFactory;
import com.yvobogers.midi.message.MessageException;
import com.yvobogers.midi.util.Constants;
import com.yvobogers.midi.util.Logger;

@SuppressWarnings("serial")
public class Switch extends JCheckBox implements Constants {

	private String itsFunction;

	public Switch(String aFunction) {
		super(aFunction); // creates a checkbox with a label
		itsFunction = aFunction;	

		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean value = isSelected();
				Logger.log("function " + itsFunction + " set to " + value);
				try{
					AbstractMessage m = AbstractMessageFactory.getFactory().
						buildMessage(itsFunction, new Boolean(value));

					// TODO just send the message, not the bytes. 
					// "output" should decode the message if needed - 
					// or for example just print a debug statement.
					send(m.getBytes());

				} catch (MessageException me){
					System.out.println(
							"Coudln't construct message (nothing sent)! " + me);
				}
			}

		});
	}
	
	public String getFunction(){
		return itsFunction;
	}

	protected void send(byte[] data ){
		OutputListener.getSelectedOutput().send(data);
	}				

}
