package com.yvobogers.midi.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.dreamfabric.DKnob;
import com.yvobogers.midi.message.AbstractMessage;
import com.yvobogers.midi.message.AbstractMessageFactory;
import com.yvobogers.midi.message.MessageException;
import com.yvobogers.midi.util.Constants;
import com.yvobogers.midi.util.Logger;

/**
 * This class presents a knob with a label above it signifying its function
 * and a label underneath it which displays its current value. 
 * 
 * User the DKnob.java class (c) Dreamfabric
 * 
 * @author teknopipo
 */
@SuppressWarnings("serial")
public class Knob extends JPanel implements Constants{

	private DKnob itsKnob = new DKnob();
	private JLabel itsValue = new JLabel();
	private JLabel itsFunction = new JLabel();
	private Hashtable<Integer, String> itsSpecialLabels =
		new Hashtable<Integer, String>();

	private int itsMaxValue = DEFAULT_KNOB_MAX;
	private int itsMinValue = DEFAULT_KNOB_MIN;
	private int itsCurrentValue = 0;

	public Knob(String aFunction) {
		this(aFunction, DEFAULT_KNOB_MIN, DEFAULT_KNOB_MAX);
	}

	public Knob(String aFunction, int aMaxValue) {
		this(aFunction, DEFAULT_KNOB_MIN, aMaxValue);
	}

	public Knob(String aFunction, int aMinValue, int aMaxValue){
		super();
		itsMaxValue = aMaxValue;
		itsMinValue = aMinValue;

		this.setLayout(new BorderLayout());
		BevelBorder bb = new BevelBorder(BevelBorder.RAISED);
		this.setBorder(bb);

		itsFunction.setText(aFunction);
		itsFunction.setHorizontalAlignment(SwingConstants.CENTER);
		itsValue.setHorizontalAlignment(SwingConstants.CENTER);

		// set initial fader value (to zero)
		updateValueLabel();

		// update text label when user moves the fader.
		itsKnob.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {

				int v = getValue();

				// only proceed if value actually did change
				if (itsCurrentValue != v){
					itsCurrentValue = v;
					String f = getFunction();
					Logger.log("function " + f + " set to " + v);
					try{
						AbstractMessage m = AbstractMessageFactory.getFactory().
						buildMessage(f, new Integer(v));

						// TODO just send the message, not the bytes. 
						// "output" should decode the message if needed - 
						// or for example just print a debug statement.

						send(m.getBytes());

					} catch (MessageException me){
						System.out.println("Coudln't construct message (nothing sent)! " + me);
					}

					updateValueLabel();
				}
			}
		});

		this.add(itsFunction, BorderLayout.NORTH);

		// put knob on a gridbag to achieve center alignment
		JPanel knobPanel = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.CENTER;
		gridbag.setConstraints(knobPanel, constraints);
		knobPanel.setLayout(gridbag); 
		itsKnob.setPreferredSize(new Dimension(50, 50));
		knobPanel.add(itsKnob);
		this.add(knobPanel, BorderLayout.CENTER);
		this.add(itsValue, BorderLayout.SOUTH);
	}

	/**
	 * Example:	
	 * 
	 * itsMaxValue = 48
	 * itsMinValue = -48
	 *
	 * range = (max-min) = 96
	 *  
	 *  itsKnob.getValue() = 0.4
	 *  
	 *  returned value:  -48 + 0.4*(KNOB_MAX - KNOB_MIN) = -48 + 36 = -12;
	 *  
	 * @return
	 */
	public int getValue(){

		int range = itsMaxValue - itsMinValue;
		float fraction = itsKnob.getValue();
		float result = itsMinValue + fraction*range;  

		return (int)result;
	}

	/**
	 * used to add custom labels for specific knob settings. E.g. a knob
	 * could have range 0...127 where "0" means "Off", or "64" means "center".
	 * 
	 * @param aValue
	 * @param aLabel
	 */
	public void setSpecialLabel(int aValue, String aLabel){
		itsSpecialLabels.put(aValue, aLabel);
		updateValueLabel();
	}

	public String getFunction(){
		return itsFunction.getText();
	}

	private void updateValueLabel(){
		int value = getValue();
		if (itsSpecialLabels.containsKey(value)){
			itsValue.setText(itsSpecialLabels.get(value));
		}
		else{
			itsValue.setText("" + getValue());
		}
	}

	private void send(byte[] data ){
		OutputListener.getSelectedOutput().send(data);
	}				

}
