package com.yvobogers.midi.message.sidstation;

import java.util.Hashtable;
import java.util.TreeSet;
import java.util.Vector;

import com.yvobogers.midi.util.Constants;
import com.yvobogers.midi.util.Logger;


/**
 * SIDStationPatch is constructed by passing in a hashtable mapping
 * keys to their values.
 * 
 * according to the SID manual.
 * 
 * @author teknopipo
 *
 */
public class SIDStationPatch extends SIDMessage implements Constants{

	private static final byte 
	COMMAND_ID 			= 0x02,
	DUMP_PATCH_VERSION 	= 0x00,
	PAD 				= 0x2d,
	PATCH_DATA_START 	= 0x45;

	private static final int PAD_SIZE = 24;

	private Hashtable<String, Object> itsPatchValues = null;

	/**
	 * constructs a default empty patch
	 */
	public SIDStationPatch(){
		// TODO
	}

	public SIDStationPatch(Hashtable<String, Object> patchValues) 
	throws SIDException 
	{
		super();
		itsPatchValues = patchValues;
		if (patchValues==null){
			throw new SIDException("patchValues is null!");
		}

		Logger.log("########## SID STATION PATCH ##########");
		TreeSet<String> keys = 
			new TreeSet<String>(itsPatchValues.keySet()); 
		for (String name : keys){
			Logger.log(name + ": \t" + itsPatchValues.get(name));
		}

		itsBytes.add(COMMAND_ID);
		itsBytes.add(DUMP_PATCH_VERSION);

		// convert patchValues to byte array
		Vector<Byte> dataBytes = constructDataBytes();

		Logger.log("" + dataBytes.size() + " databytes");

		// calculate and add total patch length
		int patchLength = 36 + dataBytes.size();
		Vector<Byte> lengthBytes = constructLengthBytes(patchLength);
		itsBytes.addAll(lengthBytes);

		for (int i=0;i<PAD_SIZE;i++){
			itsBytes.add(PAD);
		}
		itsBytes.add(PATCH_DATA_START);
		itsBytes.addAll(dataBytes);
		add(END);
	}

	private Vector<Byte> constructDataBytes(){
		Vector<Byte> dataBytes = new Vector<Byte>();

		// first 10 bytes is the name
		byte[] name = new byte[PATCH_NAME_LENGTH];
		name = ((String)itsPatchValues.get(PATCH_NAME)).
		substring(0, PATCH_NAME_LENGTH).getBytes();
		for (byte b : name){
			dataBytes.add(new Byte(b));
		}

		// remaining patch data bytes are transmitted as 2-byte tuples!
		// suppose dctrl1 = 0110 1101
		// dctrl1_highnibble = 0000 0110 = dctrl1 >>> 4
		// dctrl1_lownibble = 0000 1101 = dctrl1 & 0x0F

		int dctrl1 = toint(PATCH_DCTRL1);
		dataBytes.add(highNibble(dctrl1));
		dataBytes.add(lowNibble(dctrl1));

		int dctrl1_ld = toint(PATCH_DCTRL1_LD);
		dataBytes.add(highNibble(dctrl1_ld));
		dataBytes.add(lowNibble(dctrl1_ld));

		int dctrl1_lu = toint(PATCH_DCTRL1_LU);
		dataBytes.add(highNibble(dctrl1_lu));
		dataBytes.add(lowNibble(dctrl1_lu));

		int dctrl2 = toint(PATCH_DCTRL2);
		dataBytes.add(highNibble(dctrl2));
		dataBytes.add(lowNibble(dctrl2));

		int dctrl2_ld = toint(PATCH_DCTRL2_LD);
		dataBytes.add(highNibble(dctrl2_ld));
		dataBytes.add(lowNibble(dctrl2_ld));

		int dctrl2_lu = toint(PATCH_DCTRL2_LU);
		dataBytes.add(highNibble(dctrl2_lu));
		dataBytes.add(lowNibble(dctrl2_lu));

		int dctrl3 = toint(PATCH_DCTRL3);
		dataBytes.add(highNibble(dctrl3));
		dataBytes.add(lowNibble(dctrl3));

		int dctrl3_ld = toint(PATCH_DCTRL3_LD);
		dataBytes.add(highNibble(dctrl3_ld));
		dataBytes.add(lowNibble(dctrl3_ld));

		int dctrl3_lu = toint(PATCH_DCTRL3_LU);
		dataBytes.add(highNibble(dctrl3_lu));
		dataBytes.add(lowNibble(dctrl3_lu));

		int dctrl4 = toint(PATCH_DCTRL4);
		dataBytes.add(highNibble(dctrl4));
		dataBytes.add(lowNibble(dctrl4));

		int dctrl4_ld = toint(PATCH_DCTRL4_LD);
		dataBytes.add(highNibble(dctrl4_ld));
		dataBytes.add(lowNibble(dctrl4_ld));

		int dctrl4_lu = toint(PATCH_DCTRL4_LU);
		dataBytes.add(highNibble(dctrl4_lu));
		dataBytes.add(lowNibble(dctrl4_lu));

		// build PATCH_MODE bit mask
		int patchMode = patchModeBitMask();
		dataBytes.add(highNibble(patchMode));
		dataBytes.add(lowNibble(patchMode));

		// PATCH_MODE2 is not used, set these two bytes to zero 
		dataBytes.add(new Byte((byte)0));
		dataBytes.add(new Byte((byte)0));

		// build PATCH_FLT_TYPLFO bit mask
		int patchFltTypLfo = patchFltTypLfoBitMask();
		dataBytes.add(highNibble(patchFltTypLfo));
		dataBytes.add(lowNibble(patchFltTypLfo));

		// build PATCH_FLT_ACTRS bit mask
		int patchFltActrs = patchFltActrsBitMask();
		dataBytes.add(highNibble(patchFltActrs));
		dataBytes.add(lowNibble(patchFltActrs));

		// add all filter parameters
		for (String s : FILTER_PARAMETERS){
			int f = toint(s);
			dataBytes.add(highNibble(f));
			dataBytes.add(lowNibble(f));
		}

		int localUpdateSpeed = toint(PATCH_SYNC_SPEED);
		dataBytes.add(highNibble(localUpdateSpeed));
		dataBytes.add(lowNibble(localUpdateSpeed));

		int localHardcut = toint(PATCH_SYNC_HCUT);
		dataBytes.add(highNibble(localHardcut));
		dataBytes.add(lowNibble(localHardcut));

		Vector<Byte> oscillatorBytes = constructOscillatorBytes();
		dataBytes.addAll(oscillatorBytes);

		Vector<Byte> lfoBytes = constructLfoBytes();
		dataBytes.addAll(lfoBytes);

		Vector<Byte> tableBytes = constructTableBytes();
		dataBytes.addAll(tableBytes);

		return dataBytes;

	}

	private Vector<Byte> constructLengthBytes(int aLength) throws SIDException {
		Vector<Byte> data = new Vector<Byte>();
		Byte upperBits;
		Byte lowerBits;

		// patch size in bytes is transmitted as two bytes:
		// 
		// %000000aa: aa = upper bits of patch size (in bytes)
		// %0aaaaaaa: aaaaaaa = lower bits of patch size (inbytes)

		// we transform the int into a binary String array to check how
		// many bits we need. could probably be done easier but i'm tired 
		// and can't think.

		String s = Integer.toBinaryString(aLength);
		char[] bitChars = s.toCharArray();
		String upperBitString;
		String lowerBitString;

		if (s.length() == 9){
			// we need both the upper bits, they are the first two bits in the string
			upperBitString = "000000" + bitChars[0] + bitChars[1];
			lowerBitString = s.substring(2);
		} else if (s.length() == 8){
			// we need one upper bit and it is 1.
			upperBitString = "0000000" + bitChars[0];
			lowerBitString = s.substring(1);
		} else {
			// length < 7 bits, should not occur
			throw new SIDException("patch length seems to be too small");
		}

		upperBits = new Byte((byte)Integer.parseInt(upperBitString, 2));
		lowerBits = new Byte((byte)Integer.parseInt(lowerBitString, 2));

		data.add(upperBits);
		data.add(lowerBits);
		return data;
	}

	private Vector<Byte> constructOscillatorBytes(){

		Vector<Byte> result = new Vector<Byte>();

		// each OSC has 21 settings = 42 bytes
		// add 3*42 = 126 bytes for OSC 1, 2 and 3
		for (int i=1; i<4;i++){

			int flags, track, arpSpeed, transpose, detune, pbrange, attack, 
			decay, sustain, release, delay, pwmStart, pwmAdd, pwmLfo, 
			pwmLfoDepth, wave, portSpeed, vibLfo, vibDepth, vibWhd, 
			tableSpeed;

			String flagsMask = "000000";
			flagsMask += (Boolean)(itsPatchValues.get(OSC_GATE + i)) ? 1 : 0;
			flagsMask += (Boolean)(itsPatchValues.get(OSC_SPWM + i)) ? 1 : 0;
			Logger.log("oscFlagsMask =  " + flagsMask); 

			String waveMask = "";
			int waveform = toint(OSC_WAVE + i);
			switch (waveform){
			case 1: waveMask+="00010"; break;
			case 2: waveMask+="00100"; break;
			case 4: waveMask+="01000"; break;
			case 5: waveMask+="01010"; break;
			case 8: waveMask+="10000"; break;
			}
			waveMask += (Boolean)(itsPatchValues.get(OSC_RINGM + i)) ? 1 : 0;
			waveMask += (Boolean)(itsPatchValues.get(OSC_SYNC + i)) ? 1 : 0;
			waveMask += 0;
			Logger.log("wave mask: " + waveMask);

			flags =  Integer.parseInt(flagsMask, 2);
			track = toint(OSC_TRACK + i);
			arpSpeed = toint(OSC_ARPSPEED + i);
//			transpose = toint(OSC_TRANSPOSE + i); // TODO really signed?
			transpose = 0;
//			detune = toint(OSC_DETUNE + i); // TODO really signed?
			detune = 0;
			pbrange = toint(OSC_PBRANGE + i);
			attack = toint(OSC_ATTACK + i);
			decay = toint(OSC_DECAY + i);
			sustain = toint(OSC_SUSTAIN + i);
			release = toint(OSC_RELEASE + i);
			delay = toint(OSC_DELAY + i);
			pwmStart = toint(OSC_PWM_START + i);
			pwmAdd = toint(OSC_PWM_ADD + i);
			pwmLfo = toint(OSC_PWM_LFO + i);
			pwmLfoDepth = toint(OSC_PWM_LFODPTH + i);
			wave = Integer.parseInt(waveMask,2);
			portSpeed = toint(OSC_PORTSPEED + i);
			vibLfo = toint(OSC_VIB_LFO + i);
			vibDepth = toint(OSC_VIB_DEPTH + i);
			vibWhd = toint(OSC_VIB_WHD + i);
			tableSpeed = toint(OSC_TABLESPEED + i);
			
			Logger.log("result size before adding: " + result.size());
			addHighLow(result, new int[]{flags, track, arpSpeed, transpose, detune, pbrange, attack, 
					decay, sustain, release, delay, pwmStart, pwmAdd, pwmLfo, 
					pwmLfoDepth, wave, portSpeed, vibLfo, vibDepth, vibWhd, 
					tableSpeed});
			Logger.log("result size after adding: " + result.size());
		}
//		for (int i=0; i< 126 ;i++){
//			result.add(new Byte((byte)0));
//		}	
		return result;
	}

	private Vector<Byte> constructLfoBytes(){
		// TODO
		// each LFO has 11 settings = 22 bytes
		// add 4*22 = 88 bytes for LFO1, 2, 3 and 4
		Vector<Byte> result = new Vector<Byte>();
		for (int i=0; i< 88 ;i++){
			result.add(new Byte((byte)0));
		}
		return result;
	}

	private Vector<Byte> constructTableBytes(){
		// TODO
		// for the time being, skip table data.
		// each of the 3 tables only contains $FF (TABLE_END)
		Vector<Byte> result = new Vector<Byte>();
		for (int i=0;i<3;i++){
			result.add(highNibble(0xFF));
			result.add(lowNibble(0xFF));
		}
		return result;
	}

	private Byte highNibble(int aByte){
		return new Byte((byte)(aByte >>> 4));
	}

	private Byte lowNibble(int aByte){
		return new Byte((byte)(aByte & 0x0F));
	}

	private int patchModeBitMask(){
		String mask = "";

		mask += (Boolean)(itsPatchValues.get(FLT_ENV_INV)) ? 1 : 0;
		mask += (Boolean)(itsPatchValues.get(FLT_WRAP)) ? 1 : 0;
		mask += (Boolean)(itsPatchValues.get(OSC_LEGAT)) ? 1 : 0;
		mask += 0;
		mask += (Boolean)(itsPatchValues.get(OSC_POLY)) ? 1 : 0;
		mask += (Boolean)(itsPatchValues.get(OSC3)) ? 1 : 0;
		mask += (Boolean)(itsPatchValues.get(OSC2)) ? 1 : 0;
		mask += (Boolean)(itsPatchValues.get(OSC1)) ? 1 : 0;

		Logger.log("patchModeBitMask =  " + mask); 
		return Integer.parseInt(mask, 2);
	}

	private int patchFltTypLfoBitMask() {
		String mask = "00000000";

		// TODO
		itsPatchValues.get(FLT_LFO);

		return Integer.parseInt(mask, 2);
	}

	private int patchFltActrsBitMask() {
		String mask = "00000000";

		//TODO
		itsPatchValues.get(FLT_RESONANCE);
		itsPatchValues.get(FLT3);
		itsPatchValues.get(FLT2);
		itsPatchValues.get(FLT1);

		return Integer.parseInt(mask, 2);
	}

	private int toint(String aParameter){
		return ((Integer)(itsPatchValues.get(aParameter))).intValue();
	}
	
	private void addHighLow(Vector<Byte> bytes, int[] values){
		for (int i=0;i<values.length;i++){
			bytes.add(highNibble(values[i]));
			bytes.add(lowNibble(values[i]));
		}
	}

}
