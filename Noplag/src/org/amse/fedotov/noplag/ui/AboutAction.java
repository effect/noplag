package org.amse.fedotov.noplag.ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
/* package */ class AboutAction extends AbstractAction {
	
	private static final String ABOUT = "Noplag\n\n" 
		+ "Authors: Pavel Fedotov, Andrey Breslav\n" 
		+ "E-mail: fedotov@rain.ifmo.ru\n\n" + "© 2008";

	public AboutAction() {
		super("About...");
	}

	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, ABOUT, "About", JOptionPane.INFORMATION_MESSAGE);
	}

}
