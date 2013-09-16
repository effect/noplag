package org.amse.fedotov.noplag.ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.amse.fedotov.noplag.settings.Settings;

@SuppressWarnings("serial")
/* package */ class AddProgramAction extends AbstractAction {
	
	private static final String ADD_TEXT = "Add Program to Database";
	
	private final ContentManager myManager;
	
	public AddProgramAction(ContentManager manager) {
		putValue(SHORT_DESCRIPTION, ADD_TEXT);
		putValue(SMALL_ICON, Settings.getIcon("add"));
		myManager = manager;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (myManager.getProgram() != null) {
			new Thread(new Runnable() {
				public void run() {
					synchronized (Settings.getDatabase()) {
						Settings.getDatabase().addProgram(myManager.getProgram());	
						myManager.updateAuthors();
					}
				}
			}).start();
		} else {
			JOptionPane.showMessageDialog(null, "You should choose a program to add", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
