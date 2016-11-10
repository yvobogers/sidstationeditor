package com.yvobogers.midi.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.yvobogers.midi.message.AbstractMessage;
import com.yvobogers.midi.message.AbstractMessageFactory;
import com.yvobogers.midi.message.MessageException;
import com.yvobogers.midi.util.Logger;

/**
 * checkboxes that require the values
 * of other checkboxes which together make up one controller.
 * 
 * can be used to split out backend factory constructs into separate
 * control objects, for example a filter bitmask or 
 * @author teknopipo
 *
 */
@SuppressWarnings("serial")
public class AggregateSwitch extends JPanel {

	private String itsFunction;
	
	private HashMap<String, JCheckBox> itsAggregates = 
		new HashMap<String, JCheckBox>();

	public AggregateSwitch(String aFunction) {
		super(); 
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		itsFunction = aFunction;
	}
	
	public void addCheckBox(String aFunction){
		
		final JCheckBox c = new JCheckBox(aFunction);
		this.add(c);
		itsAggregates.put(aFunction, c);

		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean value = c.isSelected();
				String function = c.getText();
				Logger.log("function " + function + " set to " + value);
				HashMap<String, Boolean> itsValues = 
					new HashMap<String, Boolean>();
				// store own selection 
				itsValues.put(function, value);
				// store selection of aggregates
				for (String f : itsAggregates.keySet()){
					itsValues.put(f, itsAggregates.get(f).isSelected());
				}
				try{
					AbstractMessage m = AbstractMessageFactory.getFactory().
						buildMessage(itsFunction, itsValues);

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

	
	private void send(byte[] data ){
		OutputListener.getSelectedOutput().send(data);
	}	
}
