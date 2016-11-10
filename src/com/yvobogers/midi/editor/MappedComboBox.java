package com.yvobogers.midi.editor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.yvobogers.midi.message.AbstractMessage;
import com.yvobogers.midi.message.AbstractMessageFactory;
import com.yvobogers.midi.message.MessageException;
import com.yvobogers.midi.util.Logger;

/**
 * a ComboBox on a JPanel with a listener propagating to messageFty
 * @author teknopipo
 *
 */
@SuppressWarnings("serial")
public class MappedComboBox extends JPanel {

	private JComboBox itsComboBox = new JComboBox();
	private JLabel itsFunction = new JLabel();
//	private String itsFunction;
	private int itsCurrentIndex = 0;

	public MappedComboBox(String aFunction){
		super();
		itsFunction.setText(aFunction);
		this.add(itsFunction);
		this.add(itsComboBox);
		
		itsComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// see if the selection actually changed. sometimes these events 
				// are fired when the GUI is built up.
				int index = itsComboBox.getSelectedIndex(); 
				if (index != itsCurrentIndex){
					// it did
					itsCurrentIndex = index;
					Object value = itsComboBox.getSelectedItem();
					Logger.log("function " + itsFunction.getText() + " set to " + value);
					try{
						AbstractMessage m = AbstractMessageFactory.getFactory().
						buildMessage(itsFunction.getText(), value);

						// TODO just send the message, not the bytes. 
						// "output" should decode the message if needed - 
						// or for example just print a debug statement.
						send(m.getBytes());

					} catch (MessageException me){
						System.out.println(
								"Coudln't construct message (nothing sent)! " + me);
					}
				}
				else {
					// ignore.
				}
			}

		});	
	}

	/**
	 * add an item to the combobox
	 * 
	 * @param name
	 */
	public void addItem(String name){
		itsComboBox.addItem(name);
	}


	private void send(byte[] data ){
		OutputListener.getSelectedOutput().send(data);
	}	
}
