package com.yvobogers.midi.editor;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.yvobogers.midi.ports.AbstractPortFactory;

public class Main {

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
  
    }
    
    private static void createAndShowGUI() {
    	AbstractPortFactory pf = AbstractPortFactory.getFactory();
		GUI g = new GUI("SID Editor", pf.initPorts());
		g.init();
		
	}

}
