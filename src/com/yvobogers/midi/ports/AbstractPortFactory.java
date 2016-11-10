package com.yvobogers.midi.ports;

import java.util.prefs.Preferences;

/**
 * abstract base class for concrete factories which produces ports.
 */
public abstract class AbstractPortFactory {

	public static final String JAVAMIDI = "JAVAMIDI";
	public static final String MMJ = "MMJ";
	public static final String DEFAULT_DRIVER = MMJ;

	protected MidiPort[] itsMidiPorts = null;
	protected static AbstractPortFactory theirInstance = null;

	/**
	 * returns a concrete PortFactory implementation based on user preferences.
	 * To change the type of factory that is returned, set the "mididriver" preference.
	 * 
	 *  The default is "MMJ", returning a MmjMidiPortFactory.
	 */
	public static AbstractPortFactory getFactory(){
		Preferences p = Preferences.userRoot();
		String itsDriver = p.get("mididriver", DEFAULT_DRIVER);

		if (MMJ.equals (itsDriver)){
			return MmjMidiPortFactory.instance();
		} 
		else {
			return JavaMidiPortFactory.instance();
		}		
	}

	/**
	 *  find and return the MidiPorts available in the system
	 * @return an array of @MidiPort objects.
	 */
	public abstract MidiPort[] initPorts();
	
	/**
	 * list the names of the MIDIports available in the system.
	 * using these names , a port can later be retrieved using the 
	 * getPort() method. 
	 * 
	 * you need to call initPorts first.
	 */
	public String[] getPortNames() {
		String[] names = null;
		if (itsMidiPorts != null){
			int l = itsMidiPorts.length;
			names = new String[l];
			for (int i=0;i<l;i++){
				names[i]=itsMidiPorts[i].getName();
			}
		}
		return names;
	}

	/**
	 * returns the first available MidiPort where getName() contains 
	 * the search string, or null if none found.
	 * 
	 * if the search string is null, the first port is returned if available.
	 */
	public MidiPort getPort(String aSearchString) {

		if (itsMidiPorts == null || itsMidiPorts.length==0){
			return null;
		}
		MidiPort p = null;

		if (aSearchString == null){
			p = itsMidiPorts[0];
		}
		else {
			for (int i=0;i<itsMidiPorts.length;i++){
				String portname = itsMidiPorts[i].getName();
				if (aSearchString.equals(portname)){
					p = itsMidiPorts[i];
					break;
				}
			}
		}
		return p;
	}
}
