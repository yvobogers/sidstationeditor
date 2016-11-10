package com.yvobogers.midi.editor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.yvobogers.midi.message.AbstractMessage;
import com.yvobogers.midi.message.AbstractMessageFactory;
import com.yvobogers.midi.message.MessageException;
import com.yvobogers.midi.util.Logger;

/**
 * textfield on a panel which propagates keypresses to a backend but 
 * ignores keyboard entries beyond its max length.
 * 
 * @author teknopipo
 *
 */
@SuppressWarnings("serial")
public class Textfield extends JPanel {

	private JTextField itsTextField;
	private JLabel itsFunction = new JLabel();
	private String itsCache = "";

	public Textfield(String aFunction, int aMaxLength){
		itsTextField = new JTextField(aMaxLength);
		itsTextField.setDocument(new JTextFieldLimit(aMaxLength));
		itsFunction.setText(aFunction);	
		this.add(itsFunction);
		this.add(itsTextField);

		itsTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// see if text actually changed
				String txt = itsTextField.getText();
				if (itsCache.equals(txt))
				{
					Logger.log("nothing changed, ignoring");
				}
				else{
					itsCache = txt;
					try{
						AbstractMessage m = AbstractMessageFactory.getFactory().
						buildMessage(itsFunction.getText(), txt);

						// TODO just send the message, not the bytes. 
						// "output" should decode the message if needed - 
						// or for example just print a debug statement.
						send(m.getBytes());

					} catch (MessageException me){
						System.out.println(
								"Coudln't construct message (nothing sent)! " + me);
					}	
				}
			}

			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
		});
	}

	public void setText(String aText){
		itsTextField.setText(aText);
		itsCache = aText;
	}
	private void send(byte[] data ){
		OutputListener.getSelectedOutput().send(data);
	}		

	private class JTextFieldLimit extends PlainDocument {
		private int limit;

		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		public void insertString
		(int offset, String  str, AttributeSet attr)
		throws BadLocationException {
			if (str == null) return;

			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}

}
