package com.yvobogers.midi.ports;

public interface MidiPort {

	public static final long TIMESTAMP = 0;

	/**
	 * an understandable name for this port.
	 * portfactories will use this to search 
	 * @return
	 */
	public String getName();
	
	/**
	 * send some bytes to this port
	 * TODO abstract MidiMessage byte handling 
	 * @param bytes
	 */
	public void send(byte[] data);

}
