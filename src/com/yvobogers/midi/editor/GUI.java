package com.yvobogers.midi.editor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.yvobogers.midi.ports.MidiPort;
import com.yvobogers.midi.util.Constants;
import com.yvobogers.midi.util.Logger;

@SuppressWarnings("serial")
/**
 * GUI builds all the control panels
 * TODO move the construction of an actual controller fader to a 
 * controllerfactory, so that the GUI doesn't know all these details.
 * that would then also allow for custom comboboxes, for instance, where
 * a particular selection is translated to an int value.
 * 
 * That would also make it easier to switch "views" e.g. switch from 
 * knobs to sliders, to dropdownboxes, etc. 
 */
public class GUI extends JFrame implements Constants {

	private MidiPort[] itsPorts;
	private JComboBox itsOutputSelection;
	
	public GUI(String name, MidiPort[] ports)
	{
		super(name);
		itsPorts = ports;
	}
	public void init()
	{		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainpanel = new JPanel(new BorderLayout());
		JPanel topPanel = new MainPatchSettings();

		JTabbedPane faderPane = new JTabbedPane();

		// add the tabs
		faderPane.addTab("General", new GeneralSettingsPanel());
		faderPane.addTab("Oscillators", new OscillatorSection());
		faderPane.addTab("Filter", new FilterSection());
		faderPane.addTab("LFOs", new LFOSection());
		faderPane.addTab("Table Data", new TableDataSection());
		faderPane.addTab("Patch Control", new RealTimeControlPanel());

		Piano keyboard = new Piano();

		mainpanel.add(topPanel, BorderLayout.NORTH);
		mainpanel.add(faderPane, BorderLayout.CENTER);
		mainpanel.add(keyboard, BorderLayout.SOUTH);

		getContentPane().add(mainpanel);
		pack();

		setResizable(false);
		setVisible(true);

		Logger.log("Window size (w,h) = (" + getWidth() + " , " + getHeight() + ")");
	}

	class GeneralSettingsPanel extends JPanel{
		GeneralSettingsPanel(){
			setBorder(
					new TitledBorder(new LineBorder(null, 1),"Patch settings"));
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

			add(new DCTRLPanel());
			add(new CheckBoxPanel());
		}

		class DCTRLPanel extends JPanel {

			DCTRLPanel(){
				BoxLayout rows = new BoxLayout(this, BoxLayout.Y_AXIS);
				this.setLayout(rows);

				JPanel row1 = new JPanel();
				row1.add(new Knob(PATCH_DCTRL1, 96));
				row1.add(new Knob(PATCH_DCTRL1_LD));
				row1.add(new Knob(PATCH_DCTRL1_LU));
				this.add(row1);

				JPanel row2 = new JPanel();
				row2.add(new Knob(PATCH_DCTRL2, 96));
				row2.add(new Knob(PATCH_DCTRL2_LD));
				row2.add(new Knob(PATCH_DCTRL2_LU));
				this.add(row2);

				JPanel row3 = new JPanel();
				row3.add(new Knob(PATCH_DCTRL3, 96));
				row3.add(new Knob(PATCH_DCTRL3_LD));
				row3.add(new Knob(PATCH_DCTRL3_LU));
				this.add(row3);

				JPanel row4 = new JPanel();
				row4.add(new Knob(PATCH_DCTRL4, 96));
				row4.add(new Knob(PATCH_DCTRL4_LD));
				row4.add(new Knob(PATCH_DCTRL4_LU));
				this.add(row4);

			}
		}

		class CheckBoxPanel extends JPanel{
			CheckBoxPanel(){
				BoxLayout rows = new BoxLayout(this, BoxLayout.Y_AXIS);
				this.setLayout(rows);
				this.add(new Switch(OSC1));
				this.add(new Switch(OSC2));
				this.add(new Switch(OSC3));
				this.add(new Switch(FLT_ENV_INV));
				this.add(new Switch(FLT_SYNC));
				this.add(new Switch(FLT_WRAP));
				this.add(new Switch(OSC_LEGAT));
				this.add(new Switch(OSC_POLY));					
			}
		}
	}

	class OscillatorSection extends JPanel{

		OscillatorSection(){
			setLayout(new BorderLayout());
			
			// 3 Oscillator panels
			final JPanel[] oscPanel = new JPanel[3];
			JPanel current = new JPanel();
			for (int i=0;i<3;i++){
				JPanel osc = new JPanel();
				oscPanel[i] = osc;
				osc.setBorder(new TitledBorder("Oscillator " + (i+1)));
				
				JPanel checkBoxes = new JPanel();
				checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.Y_AXIS));
				checkBoxes.add(new Switch(OSC_SPWM + (i+1)));
				checkBoxes.add(new Switch(OSC_GATE + (i+1)));
				checkBoxes.add(new Switch(OSC_RINGM + (i+1)));
				checkBoxes.add(new Switch(OSC_SYNC + (i+1)));
				osc.add(checkBoxes);

				JPanel knobRows = new JPanel();
				knobRows.setLayout(new BoxLayout(knobRows, BoxLayout.Y_AXIS));
				osc.add(knobRows);
				JPanel[] rows = new JPanel[4];
				for (int r=0;r<4;r++){
					rows[r]=new JPanel();
					knobRows.add(rows[r]);
				}
				Knob oscTrack = new Knob(OSC_TRACK + (i+1), 0, 99);
				oscTrack.setSpecialLabel(0, "Keytrack");
				rows[0].add(oscTrack);
				Knob arpSpeed = new Knob(OSC_ARPSPEED + (i+1));
				arpSpeed.setSpecialLabel(0, "No arpeggiator");
				rows[0].add(arpSpeed);
				rows[0].add(new Knob(OSC_TRANSPOSE + (i+1), -24, 24));
				rows[0].add(new Knob(OSC_DETUNE + (i+1), -64, 63));
				rows[1].add(new Knob(OSC_PBRANGE + (i+1), 24));
				rows[1].add(new Knob(OSC_ATTACK + (i+1), 15));
				rows[1].add(new Knob(OSC_DECAY + (i+1), 15));
				rows[1].add(new Knob(OSC_SUSTAIN + (i+1), 15));
				rows[1].add(new Knob(OSC_RELEASE + (i+1), 15));
				rows[2].add(new Knob(OSC_DELAY + (i+1)));
				rows[2].add(new Knob(OSC_PWM_START + (i+1)));
				rows[2].add(new Knob(OSC_PWM_ADD + (i+1)));
				rows[2].add(new Knob(OSC_PWM_LFODPTH + (i+1)));
				rows[3].add(new Knob(OSC_PORTSPEED + (i+1)));
				rows[3].add(new Knob(OSC_VIB_DEPTH + (i+1)));
				rows[3].add(new Knob(OSC_VIB_WHD + (i+1)));
				rows[3].add(new Knob(OSC_TABLESPEED + (i+1)));
				
				MappedComboBox pwLfo = new MappedComboBox(OSC_PWM_LFO + (i+1));
				for (int j=1;j<5;j++){
					pwLfo.addItem("LFO "+ j);
				}
				
				MappedComboBox vibLfo = new MappedComboBox(OSC_VIB_LFO + (i+1));
				for (int j=1;j<5;j++){
					vibLfo.addItem("LFO "+ j);
				}
				
				MappedComboBox waveForm = new MappedComboBox(OSC_WAVE + (i+1));
				for (String s : OSC_WAVE_VALUES){
					waveForm.addItem(s);
				};
				
				checkBoxes.add(pwLfo);
				checkBoxes.add(vibLfo);
				checkBoxes.add(waveForm);
				osc.setVisible(false);
				current.add(osc);
			}
			// only one OSC panel is visible at a time
			JPanel oscChooserPanel = new JPanel();
			for (int i=0;i<3;i++){
				final int index = i;
				JButton oscBtn = new JButton("Oscillator "+(index+1));
				oscBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// enable the selected OSC
						oscPanel[index].setVisible(true);
						for (int j=0;j<3;j++){
							if (j!=index) oscPanel[j].setVisible(false);
						}
					}
				});
				oscChooserPanel.add(oscBtn);
			}
			oscChooserPanel.add(new JPanel());
			add(oscChooserPanel, BorderLayout.NORTH);
			add(current,BorderLayout.CENTER);

			// make first LFO visible by default
			oscPanel[0].setVisible(true);
		}

	}

	class FilterSection extends JPanel{

		// add all filter parameters
		public FilterSection() {

			final int nrRows = 4;
			BoxLayout cols = new BoxLayout(this, BoxLayout.X_AXIS);
			this.setLayout(cols);
			JPanel left = new JPanel();
			JPanel right = new JPanel();
			add(left);
			add(right);
			
			BoxLayout rightrows = new BoxLayout(right, BoxLayout.Y_AXIS);
			BoxLayout leftrows = new BoxLayout(left, BoxLayout.Y_AXIS);
			right.setLayout(rightrows);
			left.setLayout(leftrows);
			
			JPanel[] panels = new JPanel[nrRows]; 

			for (int i=0;i<nrRows;i++){
				panels[i]= new JPanel();
				for (int j=i*3;j<FILTER_PARAMETERS.length && j<i*3+3;j++){
					panels[i].add(new Knob(FILTER_PARAMETERS[j]));
				}
			}
			panels[nrRows-1].add(new Knob(PATCH_SYNC_SPEED, 50, 200));
			panels[nrRows-1].add(new Knob(PATCH_SYNC_HCUT, 15));
			panels[nrRows-1].add(new Knob(FLT_RESONANCE, 15));

			AggregateSwitch flt = new AggregateSwitch(FLT_MASK);
			flt.addCheckBox(FLT1);
			flt.addCheckBox(FLT2);
			flt.addCheckBox(FLT3);
			left.add(flt);
			
			MappedComboBox filterMode = new MappedComboBox(FLT_TYPE);
			for (String s : FLT_TYPE_VALUES){
				filterMode.addItem(s);
			};
			left.add(filterMode);
			
			MappedComboBox filterLfo = new MappedComboBox(FLT_LFO);
			for (String s : FLT_LFO_VALUES){
				filterLfo.addItem(s);
			};
			left.add(filterLfo);
			
			for (JPanel p : panels) { right.add(p); }
		}
	}


	class LFOSection extends JPanel{
		LFOSection(){
			setLayout(new BorderLayout());

			// 4 LFO panels, only LFO_ADDLFO is custom per panel
			JPanel lfoPanel = new JPanel();
			final JPanel[] lfos = new JPanel[4];
			for (int i=0;i<4;i++){
				JPanel lfo = new JPanel();
				lfos[i] = lfo;
				lfo.setBorder(new TitledBorder("LFO " + (i+1)));
				lfo.setLayout(new BoxLayout(lfo, BoxLayout.Y_AXIS));

				JPanel row1 = new JPanel();
				row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
				JPanel row2 = new JPanel();
				row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS));
				lfo.add(row1);
				lfo.add(row2);

				JPanel checkboxes = new JPanel();
				checkboxes.setLayout(new BoxLayout(checkboxes, BoxLayout.Y_AXIS));
				checkboxes.add(new Switch(LFO_SYNC+(i+1)));
				checkboxes.add(new Switch(LFO_INV+(i+1)));
				checkboxes.add(new Switch(LFO_ABZ+(i+1)));
				checkboxes.add(new Switch(LFO_SNOFF+(i+1)));

				JPanel dropdowns = new JPanel();
				dropdowns.setLayout(new BoxLayout(dropdowns, BoxLayout.Y_AXIS));
				MappedComboBox ctrlSource = new MappedComboBox(LFO_CTRL_SOURCE+(i+1));
				for (int j=0;j<CTRL_SOURCE_VALUES.length;j++){
					ctrlSource.addItem(CTRL_SOURCE_VALUES[j]);
				}
				MappedComboBox lfoType = new MappedComboBox(LFO_TYPE+(i+1));
				for (int j=0;j<LFO_TYPE_VALUES.length;j++){
					lfoType.addItem(LFO_TYPE_VALUES[j]);
				}
				MappedComboBox ctrlDest = new MappedComboBox(LFO_CTRL_DEST+(i+1));
				for (int j=0;j<CTRL_DEST_VALUES.length;j++){
					ctrlDest.addItem(CTRL_DEST_VALUES[j]);
				}
				
				MappedComboBox addLfo = new MappedComboBox(LFO_ADDLFO+(i+1));
				for (int j=0;j<4;j++){
					if (j!=i) {
						addLfo.addItem("LFO" + (j+1));
					}
				}
				
				MappedComboBox laceWithLfo = new MappedComboBox(LFO_LACEWITH+(i+1));
				laceWithLfo.addItem("Zero");
				for (int j=1;j<5;j++){
					laceWithLfo.addItem("LFO" + j);
				}
				
				dropdowns.add(ctrlSource);
				dropdowns.add(ctrlDest);
				dropdowns.add(lfoType);
				dropdowns.add(addLfo);
				dropdowns.add(laceWithLfo);

				JPanel knobs = new JPanel();
				knobs.setLayout(new BoxLayout(knobs, BoxLayout.Y_AXIS));
				JPanel knobrow1 = new JPanel();
				JPanel knobrow2 = new JPanel();
				knobs.add(knobrow1);
				knobs.add(knobrow2);
				knobrow1.add(new Knob(LFO_SPEED+(i+1)));
				Knob sampHold = new Knob(LFO_SAMPHOLD+(i+1));
				sampHold.setSpecialLabel(0, "Off");
				knobrow1.add(sampHold);
				knobrow1.add(new Knob(LFO_DEPTH+(i+1)));
				knobrow1.add(new Knob(LFO_LACE+(i+1)));
				knobrow2.add(new Knob(LFO_ADDDEPTH+(i+1)));
				knobrow2.add(new Knob(LFO_CTRLVAL+(i+1)));
				Knob fadein = new Knob(LFO_FADEIN+(i+1));
				fadein.setSpecialLabel(0, "No fade in");
				knobrow2.add(fadein);

				row1.add(checkboxes);
				row1.add(dropdowns);
				row2.add(knobs);
				lfo.setVisible(false);
				lfoPanel.add(lfo);
			}

			// only one LFO panel is visible at a time
			JPanel lfoChooserPanel = new JPanel();
			for (int i=0;i<4;i++){
				final int index = i;
				JButton lfoBtn = new JButton("LFO"+(index+1));
				lfoBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// enable the selected LFO
						lfos[index].setVisible(true);
						for (int j=0;j<4;j++){
							if (j!=index) lfos[j].setVisible(false);
						}
					}
				});
				lfoChooserPanel.add(lfoBtn);
			}
			lfoChooserPanel.add(new JPanel());
			add(lfoChooserPanel, BorderLayout.NORTH);
			add(lfoPanel,BorderLayout.CENTER);

			// make first LFO visible by default
			lfos[0].setVisible(true);
		}
	}

	class TableDataSection extends JPanel{
		TableDataSection(){
			add(new JLabel("Not yet implemented"));
		}

	}

	class RealTimeControlPanel extends JPanel{

		// TODO 
		public RealTimeControlPanel() {
			// add some faders
			for (int i=0;i<20;i++){
				add(new Fader("" + i));
			}
		}
	}
	
	class MainPatchSettings extends JPanel{
		MainPatchSettings(){
			Textfield patchName = new Textfield(PATCH_NAME, PATCH_NAME_LENGTH);
			patchName.setText("testpatch1");
			this.add(patchName);

			this.add(new JLabel(OUTPUT));
			itsOutputSelection = new JComboBox(itsPorts);
			itsOutputSelection.setActionCommand(OUTPUT);
			itsOutputSelection.addActionListener(OutputListener.instance());
			itsOutputSelection.setSelectedIndex(0);
			this.add(itsOutputSelection);
		}
	}
}
