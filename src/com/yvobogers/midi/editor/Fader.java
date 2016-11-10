package com.yvobogers.midi.editor;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.yvobogers.midi.message.AbstractMessage;
import com.yvobogers.midi.message.AbstractMessageFactory;
import com.yvobogers.midi.message.MessageException;
import com.yvobogers.midi.util.Constants;
import com.yvobogers.midi.util.Logger;

@SuppressWarnings("serial")
public class Fader extends JPanel implements Constants{

	private JSlider itsSlider = new JSlider();
	private JLabel itsValue = new JLabel();
	private JLabel itsFunction = new JLabel();
	
	private String itsFunctionName; 

	public Fader(String aFunction) {

		itsFunctionName = aFunction;
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		this.setBorder(new BevelBorder(BevelBorder.RAISED));

		itsSlider.setOrientation(SwingConstants.VERTICAL);
		itsFunction.setText(""+ aFunction);
		updateValueLabel();

		// update text label when user moves the fader.
		itsSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				
				int v = itsSlider.getValue();
				
				Logger.log("function " + itsFunctionName + " set to " + v);
				try{
					AbstractMessage m = AbstractMessageFactory.getFactory().
						buildMessage(itsFunctionName, new Integer(v));

					// TODO just send the message, not the bytes. 
					// "output" should decode the message if needed - 
					// or for example just print a debug statement.
					send(m.getBytes());
					
				} catch (MessageException me){
					System.out.println("Coudln't construct message (nothing sent)! " + me);
				}
				
				updateValueLabel();
			}
		});

		this.add(itsFunction, BorderLayout.NORTH);
		this.add(itsSlider, BorderLayout.CENTER);
		this.add(itsValue, BorderLayout.SOUTH);
	}

	private void updateValueLabel(){
		itsValue.setText("" + itsSlider.getValue());
	}

	private void send(byte[] data ){
		OutputListener.getSelectedOutput().send(data);
	}				

}
