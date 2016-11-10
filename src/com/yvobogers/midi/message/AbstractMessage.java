package com.yvobogers.midi.message;

/**
 * marker interface for messages
 * 
 * @author teknopipo
 *
 */
public abstract class AbstractMessage {

	/**
	 *
	 * @return the byte representation of this message 
	 */
	public abstract byte[] getBytes();
}
