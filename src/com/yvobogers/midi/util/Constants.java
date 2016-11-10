package com.yvobogers.midi.util;

public interface Constants {

	public static final int 
	DEFAULT_KNOB_MAX =127,
	DEFAULT_KNOB_MIN = 0,
	PATCH_NAME_LENGTH = 10;

	public static final String OUTPUT = "Output:";

	public static final String 
	PATCH_NAME = "Patch Name",
	PATCH_DCTRL1 = "Direct Controller 1",
	PATCH_DCTRL1_LD = "DCTRL1 limit down",
	PATCH_DCTRL1_LU = "DCTRL1 limit up",
	PATCH_DCTRL2 = "Direct Controller 2",
	PATCH_DCTRL2_LD = "DCTRL2 limit down",
	PATCH_DCTRL2_LU = "DCTRL2 limit up",
	PATCH_DCTRL3 = "Direct Controller 3",
	PATCH_DCTRL3_LD = "DCTRL3 limit down",
	PATCH_DCTRL3_LU = "DCTRL3 limit up",
	PATCH_DCTRL4 = "Direct Controller 4",
	PATCH_DCTRL4_LD = "DCTRL4 limit down",
	PATCH_DCTRL4_LU = "DCTRL4 limit up",

	FLT_ENV_INV = "Filter Envelope Invert",
	FLT_WRAP = "Filter wrap",
	FLT_SYNC = "Filter sync to Note On",
	FLT_LFO = "LFO to control filter cutoff",

	FLT_RESONANCE = "Resonance",
	FLT_MASK = "Filter Mask",
	FLT3 = "Oscillator 3 routed through filter",
	FLT2 = "Oscillator 2 routed through filter",
	FLT1 = "Oscillator 1 routed through filter",
	FLT_TYPE = "Filtertype",

	FLT_CUTOFF = "Cutoff value for filter",
	FLT_ENVDEP = "Filter envelope depth",
	FLT_ENVATT = "Filter envelope attack",
	FLT_ENVDEC = "Filter envelope decay",
	FLT_ENVSUS = "Filter envelope sustain",
	FLT_ENVREL = "Filter envelope release",
	FLT_LFODEP = "LFO depth for filter",
	FLT_LFOWHD = "Amount the modulation wheel adds to filter LFO",
	PATCH_SYNC_SPEED = "Local update speed for patch",
	PATCH_SYNC_HCUT = "Local Hardcut setting for patch";

	public static final String[] FILTER_PARAMETERS = {
		FLT_CUTOFF, FLT_ENVDEP, FLT_ENVATT, FLT_ENVDEC,
		FLT_ENVSUS, FLT_ENVREL, FLT_LFODEP, FLT_LFOWHD};


	// Oscillator constants
	public static final String 
	OSC_LEGAT = "Portamento Legato On/Off", // not OSC specific
	OSC_POLY = "Poly mode On/Off", // not OSC specific
	OSC3 = "Oscillator 3 active",
	OSC2 = "Oscillator 2 active",
	OSC1 = "Oscillator 1 active",
	OSC_SPWM = "Sychronize PWM-add",
	OSC_GATE = "Use gate or SID envelope",
	OSC_TRACK = "Tracking",
	OSC_ARPSPEED = "Arpeggiator speed",
	OSC_TRANSPOSE = "Transpose",
	OSC_DETUNE = "Detune",
	OSC_PBRANGE = "Pitchbend range",
	OSC_ATTACK = "Attack",
	OSC_DECAY = "Decay",
	OSC_SUSTAIN = "Sustain",
	OSC_RELEASE = "Release",
	OSC_DELAY = "Delay",
	OSC_PWM_START = "Pulsewidth start",
	OSC_PWM_ADD = "Pulsewidth modulation add",
	OSC_PWM_LFO = "Pulsewidth LFO number",
	OSC_PWM_LFODPTH = "Pulsewidth LFO depth",
	OSC_WAVE = "Waveform",
	OSC_RINGM = "Ringmodulation",
	OSC_SYNC = "Synchronization",
	OSC_PORTSPEED = "Portamento speed",
	OSC_VIB_LFO = "Vibrato LFO",
	OSC_VIB_DEPTH = "Vibrato depth",
	OSC_VIB_WHD = "Vibrato modwheel depth",
	OSC_TABLESPEED = "Table speed";


	// Following constants are same for each of the 4 LFOs
	public static final String
	LFO_CTRL_SOURCE = "LFO control source",
	LFO_TYPE = "LFO type",
	LFO_SYNC = "Sync note on",
	LFO_INV = "Invert LFO",
	LFO_ABZ = "Output of LFO is strictly positive",
	LFO_SNOFF = "Sync to note off",
	LFO_CTRL_DEST = "LFO control destination",
	LFO_SPEED = "Speed of LFO",
	LFO_SAMPHOLD = "Sample and hold",
	LFO_DEPTH = "Depth of LFO",
	LFO_ADDLFO = "Add output of this LFO",
	LFO_LACE = "Speed of interlace",
	LFO_LACEWITH = "Use this to lace with",
	LFO_ADDDEPTH = "Amount of added LFO",
	LFO_CTRLVAL = "Amount the CTRL data can affect the depth",
	LFO_FADEIN = "Speed of fade in";		

	public static final String 
	VAL_MODWHEEL = "ModWheel", 
	VAL_PITCHBEND = "PitchBend", 
	VAL_VELOCITY = "Velocity", 
	VAL_AFTERTOUCH  ="Aftertouch", 
	VAL_LFOCTRL1 = "LFO_CTRL1 (MIDI_CTRL)", 
	VAL_LFOCTRL2 = "LFO_CTRL2 (MIDI_CTRL)",
	VAL_LFOCTRL3 = "LFO_CTRL3 (MIDI_CTRL)",
	VAL_LFOCTRL4 = "LFO_CTRL4 (MIDI_CTRL)",
	VAL_LFO1 = "LFO1",
	VAL_LFO2 = "LFO2",
	VAL_LFO3 = "LFO3",
	VAL_LFO4 = "LFO4";

	public static final String[] CTRL_SOURCE_VALUES = new String[]{
		VAL_MODWHEEL, 
		VAL_PITCHBEND, 
		VAL_VELOCITY, 
		VAL_AFTERTOUCH, 
		VAL_LFOCTRL1, 
		VAL_LFOCTRL2,
		VAL_LFOCTRL3,
		VAL_LFOCTRL4,
		VAL_LFO1,
		VAL_LFO2,
		VAL_LFO3,
		VAL_LFO4};

	public static final String
	VAL_TRIANGLE = "Triangle",
	VAL_SAW = "Saw",
	VAL_RAMP = "Ramp",
	VAL_PULSE = "Pulse",
	VAL_RANDOM = "Random",
	VAL_FLAT = "Flat";

	public static final String[] LFO_TYPE_VALUES = new String[]{ 
		VAL_TRIANGLE,
		VAL_SAW,
		VAL_RAMP,
		VAL_PULSE,
		VAL_RANDOM,
		VAL_FLAT};

	public static final String
	VAL_NONE = "None",
	VAL_LFO_DEPTH = "LFO Depth",
	VAL_LFO_SPEED = "LFO Speed",
	VAL_LFO_S_H = "LFO S/H",
	VAL_LFO_LACE = "LFO Lace";

	public static final String[] CTRL_DEST_VALUES = new String[]{
		VAL_NONE,
		VAL_LFO_DEPTH,
		VAL_LFO_SPEED,
		VAL_LFO_S_H,
		VAL_LFO_LACE};

	public static final String
	VAL_ZERO = "Zero";

	public static final String[] LACE_WITH_VALUES = new String[]{
		VAL_ZERO,
		VAL_LFO1,
		VAL_LFO2,
		VAL_LFO3,
		VAL_LFO4};

	public static final String[] FLT_LFO_VALUES = new String[]{
		VAL_LFO1,
		VAL_LFO2,
		VAL_LFO3,
		VAL_LFO4
	};

	public static final String 
	FLT_TYPE_OFF = "Off",
	FLT_TYPE_LOW_PASS = "Lowpass",
	FLT_TYPE_BAND_PASS = "Bandpass",
	FLT_TYPE_HIGH_PASS = "Highpass", 
	FLT_TYPE_LOWBAND_PASS = "Low+Bandpass", 
	FLT_TYPE_LOWHIGH_PASS = "Low+Highpass", 
	FLT_TYPE_BANDHIGH_PASS = "Band+Highpass",
	FLT_TYPE_ALL= "All";


	public static final String[] FLT_TYPE_VALUES = new String[]{
		FLT_TYPE_OFF,
		FLT_TYPE_LOW_PASS,
		FLT_TYPE_HIGH_PASS, 
		FLT_TYPE_LOWBAND_PASS ,
		FLT_TYPE_LOWHIGH_PASS,
		FLT_TYPE_BANDHIGH_PASS,
		FLT_TYPE_ALL
	};

	public static final String 
	OSC_WAVE_TRIANGLE= "Triangle",
	OSC_WAVE_SAW = "Saw",
	OSC_WAVE_PULSE = "Pulse",
	OSC_WAVE_MIXED = "Mixed",
	OSC_WAVE_NOISE = "Noise";

	public static final String[] OSC_WAVE_VALUES = new String[]{
		OSC_WAVE_TRIANGLE,
		OSC_WAVE_SAW,
		OSC_WAVE_PULSE, 
		OSC_WAVE_MIXED,
		OSC_WAVE_NOISE
	};

	// HashMap for variables with a range selected values
	//	public static final HashMap<String, String[]> VAR_SELECTIONS =
	//		new HashMap<String, String[]>();
	//	static {
	//		VAR_SELECTIONS.put(FILTERTYPE, FILTER_TYPES);
	//	}

	// TODO TABLE DATA settings

}
