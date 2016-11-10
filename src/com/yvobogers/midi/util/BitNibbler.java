package com.yvobogers.midi.util;

public class BitNibbler {

	
	public static int highNibble(int aByte){
		return aByte >>> 4;
	}

	public static int lowNibble(int aByte){
		return aByte & 0x0F;
	}
		
	
	public static void main(String[] args){
		
		String s = "11111111";
		
		int i = Integer.parseInt(s,2);
		
		int i1 = highNibble(i);
		int i2 = lowNibble(i);
		
		Logger.log("s: " + s);
		Logger.log("parsedInt: "+ i);
		
		Logger.log("i1: " + i1);
		Logger.log("i1 in binary: " + Integer.toBinaryString(i1));
		
		Logger.log("i1 cast to byte: "+ (byte)i1);
		Logger.log("i1 stored and retrieved in Byte object: " + new Byte((byte)i1).byteValue());
		
		Logger.log("i2: " + i2);
		Logger.log("i2 in binary: " + Integer.toBinaryString(i2));
		
	}
}
