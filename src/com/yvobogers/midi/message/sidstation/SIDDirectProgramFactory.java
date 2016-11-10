package com.yvobogers.midi.message.sidstation;

import com.yvobogers.midi.message.AbstractMessage;
import com.yvobogers.midi.message.AbstractMessageFactory;
import com.yvobogers.midi.util.Constants;
import com.yvobogers.midi.util.Logger;


/**
 * singleton
 * 
 * @author teknopipo
 *
 */
public class SIDDirectProgramFactory extends AbstractMessageFactory implements Constants {

	private SIDDirectProgramFactory (){
		// singleton
	}

	public static AbstractMessageFactory instance() {
		if (theirInstance == null){
			theirInstance = new SIDDirectProgramFactory();
		}
		return theirInstance;
	}

	@Override
	public AbstractMessage buildMessage(String aFunction, Object aValue) 
	throws SIDException {
		if (("".equals(aFunction)) || (aFunction==null)){
			throw new SIDException(
				"function not defined when building message");
		}
		
		// create a SIDDirectProgram message based on function and value
		else if (PATCH_NAME.equals(aFunction)){
			return new SIDPatchName(aValue);
		}

		// TODO ...
		
		
		else if (OSC1.equals(aFunction)){
			return new SIDToggleOscillator(aValue, 1);
		}
		else if (OSC2.equals(aFunction)){
			return new SIDToggleOscillator(aValue, 2);
		}
		else if (OSC3.equals(aFunction)){
			return new SIDToggleOscillator(aValue, 3);
		}
		else if (OSC_POLY.equals(aFunction)){
			return new SIDTogglePolyMode(aValue);
		}
		else if (FLT_SYNC.equals(aFunction)){
			return new SIDToggleFilterSyncToNoteOn(aValue);
		}
		else if (OSC_LEGAT.equals(aFunction)){
			return new SIDToggleLegato(aValue);
		}
		else if (FLT_WRAP.equals(aFunction)){
			return new SIDToggleFilterWrap(aValue);
		}
		else if (FLT_ENV_INV.equals(aFunction)){
			return new SIDToggleFilterEnvelopeInvert(aValue);
		}
		else if (FLT_MASK.equals(aFunction)){
			return new SIDFilterMask(aValue);
		}
		else if (FLT_RESONANCE.equals(aFunction)){
			return new SIDFilterResonance(aValue);
		}
		else if (FLT_TYPE.equals(aFunction)){
			return new SIDFilterMode(aValue);
		}
		else if (FLT_LFO.equals(aFunction)){
			return new SIDFilterLfo(aValue);
		}
		
		// TODO .....
		
		else if (FLT_CUTOFF.equals(aFunction)){
			return new SIDFilterCutoff(aValue);
		}
		else if (FLT_ENVDEP.equals(aFunction)){
			return new SIDFilterEnvelopeDepth(aValue);
		}
		else if (FLT_ENVATT.equals(aFunction)){
			return new SIDFilterEnvelopeAttack(aValue);
		}
		else if (FLT_ENVDEC.equals(aFunction)){
			return new SIDFilterEnvelopeDecay(aValue);
		}
		else if (FLT_ENVSUS.equals(aFunction)){
			return new SIDFilterEnvelopeSustain(aValue);
		}
		else if (FLT_ENVREL.equals(aFunction)){
			return new SIDFilterEnvelopeRelease(aValue);
		}
		else if(FLT_LFODEP.equals(aFunction)){
			return new SIDFilterLfoDepth(aValue);
		}	
		else if(FLT_LFOWHD.equals(aFunction)){
			return new SIDFilterLfoWheelDepth(aValue);
		}
		else if(PATCH_SYNC_SPEED.equals(aFunction)){
			return new SIDPitchSyncSpeed(aValue);
		}
		else if(PATCH_SYNC_HCUT.equals(aFunction)){
			return new SIDPitchSyncHCut(aValue);
		}

		// remaining functions are specific for an OSC or LFO,
		// last digit indicates which one.
		int id = 1;
		try {
			id= Integer.parseInt(aFunction.substring(
				aFunction.length()-1, aFunction.length()));
			Logger.log("id: "+ id);
		} 
		catch (NumberFormatException nfe){
			throw new SIDException("Failed parsing OSC or LFO index");
		}
		
		if (aFunction.startsWith(OSC_SPWM)){
			return new SIDOscillatorSyncPwmAddToKeyOn(aValue, id);
		}
		else if (aFunction.startsWith(OSC_GATE)){
			return new SIDOscillatorGate(aValue, id);
		}
		else if (aFunction.startsWith(OSC_TRACK)){
			return new SIDOscillatorPitchTrack(aValue, id);
		}
		else if (aFunction.startsWith(OSC_ARPSPEED)){
			return new SIDOscillatorArpSpeed(aValue, id);
		}
		else if (aFunction.startsWith(OSC_TRANSPOSE)){
			return new SIDOscillatorTranspose(aValue, id);
		}
		else if (aFunction.startsWith(OSC_DETUNE)){
			return new SIDOscillatorDetune(aValue, id);
		}
		else if (aFunction.startsWith(OSC_PBRANGE)){
			return new SIDOscillatorPitchBendRange(aValue, id);
		}
		else if (aFunction.startsWith(OSC_ATTACK)){
			return new SIDOscillatorAttack(aValue, id);
		}
		else if (aFunction.startsWith(OSC_DECAY)){
			return new SIDOscillatorDecay(aValue, id);
		}
		else if (aFunction.startsWith(OSC_SUSTAIN)){
			return new SIDOscillatorSustain(aValue, id);
		}
		else if (aFunction.startsWith(OSC_RELEASE)){
			return new SIDOscillatorRelease(aValue, id);
		}
		else if (aFunction.startsWith(OSC_DELAY)){
			return new SIDOscillatorDelay(aValue, id);
		}
		else if (aFunction.startsWith(OSC_PWM_START)){
			return new SIDOscillatorPwmStart(aValue, id);
		}
		else if (aFunction.startsWith(OSC_PWM_ADD)){
			return new SIDOscillatorPwmAdd(aValue, id);
		}
		else if (aFunction.startsWith(OSC_PWM_LFO)){
			return new SIDOscillatorPwmLfo(aValue, id);
		}
		else if (aFunction.startsWith(OSC_PWM_LFODPTH)){
			return new SIDOscillatorPwmLfoDepth(aValue, id);
		}
		else if (aFunction.startsWith(OSC_WAVE)){ // TODO ADD RINGMOD & SYNC
			return new SIDOscillatorWave(aValue, id);
		}
		else if (aFunction.startsWith(OSC_PORTSPEED)){
			return new SIDOscillatorPortamentoSpeed(aValue, id);
		}
		else if (aFunction.startsWith(OSC_VIB_LFO)){
			return new SIDOscillatorVibratoLfo(aValue, id);
		}
		else if (aFunction.startsWith(OSC_VIB_DEPTH)){
			return new SIDOscillatorVibratoDepth(aValue, id);
		}
		else if (aFunction.startsWith(OSC_VIB_WHD)){
			return new SIDOscillatorVibratoWheelDepth(aValue, id);
		}
		else if (aFunction.startsWith(OSC_TABLESPEED)){
			return new SIDOscillatorTableSpeed(aValue, id);
		}
		
		// TODO LFO messages
		
		else throw new SIDException(
			"Don't know how to build a message for " + aFunction);
	}
}
